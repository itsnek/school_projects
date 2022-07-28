var bookList;

window.addEventListener('load', function(){

    let submit_btn = document.getElementById('submit_btn');
    if(submit_btn!=null){
        submit_btn.addEventListener('click' , GetRequest);{
            console.log("Request function loaded.")
        }
    }

    let search = document.getElementById('search');
    if(search!=null){
        search.addEventListener('search' , GetRequest);{
            console.log("Search function loaded.")
        }
    }

    let search_icon = document.getElementById('search_icon');
    if(search_icon!=null){
        search_icon.src = "../css/images/search-icon.png";
    }
  
    let bins = document.getElementsByClassName("bin_icon");
    if(bins!=null){
        for(i = 0; i < bins.length; i++){
            bins[i].src = "../css/images/1345823.png";
        }
    }

    let edits = document.getElementsByClassName("edit_icon");
    if(edits!=null){
        for(i = 0; i < edits.length; i++){
            edits[i].src = "../css/images/Edit_icon_(the_Noun_Project_30184).svg.png";
        }
    }

    bookList = document.getElementById("booklist");

});

$(document).on('input', '#search_fav', function(e){

    setTimeout(function(){
        let content = e.target.value;
        formatElements(content);
    },500)

});

$(document).on('click', '.save_label', function(e){

    let title,author,code,image;
    let id = e.target.id;
    let work = document.getElementById("work_"+id.charAt(id.length-1));
    console.log("work_"+id.charAt(id.length-1));

    if(work.querySelector(".save_label").innerText == "Remove from favourites"){
        let details = work.getElementsByClassName("details");
        for (i = 0; i < details.length; i += 2) {
            author = details[i].innerText.substring(8,details[i].innerText.length);
            code = details[i+1].innerText.substring(6,details[i].innerText.length-1);
        }

        work.querySelector(".heart").src = "../css/images/cce9a9a4a3d74bfce45f8b568d8c6e6d_empty.png";
        work.querySelector(".heart").alt="empty_heart_icon";
        work.querySelector(".save_label").innerHTML = "Add to favourites";

        deleteRequest('./Favourite_books/delete/'+code);
        alert("Book removed from favourites");

    }
    else{

        let imageC = work.getElementsByClassName("cover");
        let titleObj = work.getElementsByClassName("title");
        let details = work.getElementsByClassName("details");


        for (i = 0; i < imageC.length; i++) {
            image = imageC[i].src;
        }
    
        for (i = 0; i < titleObj.length; i++) {
            title = titleObj[i].innerText;
        }
    
        for (i = 0; i < details.length; i += 2) {
            author = details[i].innerText.substring(8,details[i].innerText.length);
            code = details[i+1].innerText.substring(6,details[i].innerText.length-1);
        }

        const data = {image, title, author, code}; 

        PostRequest(data);

        work.querySelector(".heart").src = "../css/images/cce9a9a4a3d74bfce45f8b568d8c6e6d_red.png";
        work.querySelector(".heart").alt="red_heart_icon";
        work.querySelector(".save_label").innerHTML = "Remove from favourites";
            
        // alert("Book added to favourites");
        console.log("Save book function loaded.")

    }
    
});

function addData(obj){

    // create a list with templates
    var templates = {}  

    templates.work = Handlebars.compile(`{{#each work}}
                                                <li id="work_{{@index}}" class="work">

                                                        {{#if titles.isbn.1.$}}
                                                            <img class="cover" src="https://reststop.randomhouse.com/resources/titles/{{titles.isbn.0.$}}" width="125" height="auto"></img>
                                                        
                                                            {{else}} <img class="cover" src="https://reststop.randomhouse.com/resources/titles/{{titles.isbn.$}}" width="125" height="auto"></img>
                                                        
                                                        {{/if}}
                                                        
                                                        <div class= "work-details">

                                                            <div class="data">
                                                                <h2 class="title"> {{titleweb}} </h2>
                                                                <p class="details"> Author: {{authorweb}} </p>
                                                                <p class="details"> Code: {{workid}} </p>
                                                            </div>

                                                            <div class="add_fav">

                                                                <img class="heart" src="../css/images/cce9a9a4a3d74bfce45f8b568d8c6e6d_empty.png"></img>
                                                                <a id="save_{{@index}}"  class="save_label"> Add to favourites <a>

                                                            </div>

                                                        </div> 

                                                </li>
                                            {{/each}}`);

    // execute the compiled template and return the content
    let data = templates.work(obj);

    return data;
}

function getInfo(){
    let url = 'https://reststop.randomhouse.com/resources/works/?start=0&expandLevel=1&search=' + document.getElementById('search').value;
    return url;
}

// 'https://reststop.randomhouse.com/resources/works/?start=0&max=3&expandLevel=1&search=Grisham'
function GetRequest(){

    let myHeaders = new Headers();
    myHeaders.append('accept','application/json');
    myHeaders.append('accept','image/*');

    const url = getInfo();

    const init = {
        method: 'GET',
        headers: myHeaders
    };

    fetch(url, init)
        .then( response => response.json() )
        .then(obj => {
            console.log('Received object',obj);
            document.getElementById('search_results').innerHTML = addData(obj);
        })
        .catch(error => {
            console.log(error)
        })

}

function PostRequest(obj){
    
    let myHeaders = new Headers();
    myHeaders.append('Content-Type','application/json');
    myHeaders.append('accept','text/plain');


    let init = {
        method: "POST",
        headers: myHeaders,
        body: JSON.stringify(obj)
    }

    // console.log(init.body);
    fetch('/api', init)
    .then( response => 
    {

        if(response.status == 410){
            alert("Book already exists");
            return 0;
        }else{
            return response.json();
        }
        
    })
    .then(obj => {
        console.log('Received response',obj);
        if(obj == 0) return 0;
    })
    .catch(error => {
        console.log(error);
        return 0;
    })

}

function deleteRequest(url){

    let myHeaders = new Headers();
    myHeaders.append('accept','application/json');

    let init = {
        method: "DELETE",
        headers: myHeaders,
    }

    fetch(url, init)
    .then( response => response.json() )
    .then(obj => {
        console.log('Received response',obj);
    })
    .catch(error => {
        console.log(error);
    })

}

function formatElements(selector){
    let fav_book = bookList.getElementsByClassName('fav_book');

    for(i = 0; i< fav_book.length; i++){
        let filter = fav_book[i].getElementsByClassName('filter');

        if(filter[0].innerText.toLowerCase().indexOf(selector.toLowerCase()) > -1 ){
            fav_book[i].style.display = "";
        }else{
            fav_book[i].style.display = "none";
        }
        
    }

}

function change(){

    let title = document.getElementById("title").value;
    let author = document.getElementById("author").value;
    let code = document.getElementById("code").value;
    let description = document.getElementById("description").value;


    const data = {title, author, code, description}; 

    PostRequest(data);

}