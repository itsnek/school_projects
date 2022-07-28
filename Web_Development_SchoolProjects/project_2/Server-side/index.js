const express = require('express');
const path = require('path');
const fav_books = require('./models/favourite_books');

const port = 8080;
var allData = fav_books.findAll();
const app = express();

// Express-Handlebars
var exphbs = require('express-handlebars');
app.engine('handlebars', exphbs());
app.set('view engine', 'handlebars')

app.listen( port, () => console.log('Listening at port 8080') );
app.use(express.static('../Client-side/public'));

// parse url-encoded content from body
app.use(express.urlencoded({ extended: true }))

// parse application/json content from body
app.use(express.json())

// Goes to favourite books page
app.get('/Favourite_books', function(req, res){
    let bookList = allData;

    res.render('list_books',{
        title: "My favourite books.",
        favourite_books : bookList
    })

})

// Goes to the edit page, shows the known data
app.get('/Favourite_books/edit/:workId', function(req, res){

    let workId = req.params.workId;
    let book;

    for(i = 0; i < allData.length; i++){
        if(allData[i].workId === workId) book = allData[i];
    }

    res.render('form_book',{
      title : book.title,
      author : book.author,
      code : book.workId,
      description: book.description
    })

})


// Delete an item from favourites
app.delete('/Favourite_books/delete/:workId', function(req, res){
    
    let workId = req.params.workId;

    fav_books.deleteItem(workId);
    allData = fav_books.findAll();

    res.send("DELETE Request Called")

})

// serve Home.html as content root
app.get('/', function(req, res){

    var options = {
        root: path.join(__dirname, '../Client-side/public')
    }

    res.sendFile('Home.html', options, function(err){
        console.log(err)
    })
})

app.get('/Home', function(req, res){

    var options = {
        root: path.join(__dirname, '../Client-side/public')
    }

    res.sendFile('Home.html', options, function(err){
        console.log(err)
    })
})

// Add or change an item in favourites
app.post('/api', (request, response) => {

    const data = request.body;

    let FOUND = false;
    let fav_book = null;


    for(i = 0; i < allData.length; i++){

        if(allData[i].workId == data.code){

            if(allData[i].title != data.title || allData[i].author != data.author || data.description != null){

                fav_book = fav_books.create(allData[i].cover, data.title, data.author, data.code, data.description);

                fav_books.edit(i,fav_book);

                FOUND = true;

            }else{
                response.status(410).send("Book already exists in favourites.");
                FOUND = true;
            }

        }
    }

    if(!FOUND){

        const fav_book = fav_books.create(data.image, data.title, data.author, data.code);

        fav_books.save(fav_book);
        response.json(allData);
    
    }

    allData = fav_books.findAll();
    console.log(allData);
    
});
