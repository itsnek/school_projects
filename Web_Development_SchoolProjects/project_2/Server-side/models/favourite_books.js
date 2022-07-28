const favourite_books = [];

class favourite_book{
    constructor(cover,title,author,workId,description){
        this.cover = cover;
        this.title = title;
        this.author = author;
        this.workId = workId;
        this.description = description;
    }
}

function create(cover, title, author, workId, description){
    return new favourite_book(cover, title, author, workId, description);
}

function findAll(){

    return [... favourite_books]

}

function save(fav_book){

    favourite_books.push(fav_book);

}

function edit(i,fav_book){

    favourite_books.splice(i, 1);
    favourite_books.splice(i, 0, fav_book);

}

function search(fav_book){

    for(i = 0; i < favourite_books.length; i++){
        if(favourite_books[i].workId === fav_book.workId){
            return favourite_books[i];
        }else return null;
    }

}

function deleteItem(id){

    let index = -1;

    for(i = 0; i < favourite_books.length; i++){
        if(favourite_books[i].workId == id){
            index = favourite_books.indexOf(favourite_books[i]);
        }
    }

    if (index > -1) {
        favourite_books.splice(index, 1);
    }

}

module.exports = {
    findAll : findAll,
    "create" : create,
    "save" : save,
    "edit" : edit,
    "search" : search,
    "deleteItem" : deleteItem
}