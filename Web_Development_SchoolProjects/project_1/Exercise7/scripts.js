
window.addEventListener('load' , date);{
  console.log("Date loaded")
}

window.addEventListener('load', function(){
  
  let dark = document.getElementById('darkmode_switch');
  dark.addEventListener('click' , darkmode);{
    console.log("Dark mode loaded")
  }
  
});

function darkmode() {

    if(document.querySelector(".moon").alt === 'moon_off'){

        // Change image source path and alternative text
        document.querySelector(".moon").src="./Images/moon_on_2.png";
        document.querySelector(".moon").alt="moon_on";

        // Change background color
        document.body.style.color = "aliceblue";

        // Change background color
        let container = document.body.querySelector("div.grid-container");
        let item1 = document.body.querySelector("div.grid-container div.item1");
        let item2 = document.body.querySelector("div.grid-container div.item-2");
        let item3 = document.body.querySelector("div.grid-container div.item-3");
        let item4 = document.body.querySelector("div.grid-container div.item-4");
        let item5 = document.body.querySelector("div.grid-container div.item-5");

        item1.style.background = "rgb(52, 52, 60)";
        item2.style.background = "rgb(52, 52, 60)";
        item3.style.background = "rgb(52, 52, 60)";
        item4.style.background = "rgb(52, 52, 60)";
        item5.style.background = "rgb(52, 52, 60)";

        item2.querySelector("*").style.background = "rgb(52, 52, 60)";
        container.querySelector("ul.subnavigationlist").style.background = "rgb(52, 52, 60)";
        container.querySelector("nav.sub-nav").style.background = "rgb(52, 52, 60)";
        container.querySelector("div.item-n").style.background = "rgb(52, 52, 60)";

        item3.querySelector("article").style.background = "rgb(52, 52, 60)";
        item4.querySelector("article").style.background = "rgb(52, 52, 60)";
        item5.querySelector("img").style.background = "rgb(52, 52, 60)";
        item5.querySelector("h4").style.background = "rgb(52, 52, 60)";

        links = item5.querySelectorAll("a.source_link");
        for (i = 0; i < links.length; i++) {
          links[i].style.background = "rgb(52, 52, 60)";
          links[i].style.color = "aliceblue";
        }

        links = document.getElementsByTagName("strong");
        for (i = 0; i < links.length; i++) {
          links[i].style.background = "rgb(52, 52, 60)";
        }

        links = document.getElementsByTagName("p");
        for (i = 0; i < links.length; i++) {
          links[i].style.background = "rgb(52, 52, 60)";
        }

        links = document.getElementsByTagName("li");
        for (i = 0; i < links.length; i++) {
          links[i].style.background = "rgb(52, 52, 60)";
        }

        links = document.getElementsByClassName("lists");
        for (i = 0; i < links.length; i++) {
          links[i].style.background = "rgb(52, 52, 60)";
        }

        links = document.getElementsByClassName("practice_list");
        for (i = 0; i < links.length; i++) {
          links[i].style.background = "rgb(52, 52, 60)";
        }

        links = document.getElementsByClassName("img_content");
        for (i = 0; i < links.length; i++) {
          links[i].style.background = "rgb(52, 52, 60)";
        }

        links = document.getElementsByClassName("titles");
        for (i = 0; i < links.length; i++) {
          links[i].style.background = "rgb(52, 52, 60)";
        }

        links = document.getElementsByClassName("sublist-item");
        for (i = 0; i < links.length; i++) {
          links[i].style.background = "rgb(52, 52, 60)";
        }

        links = document.getElementsByClassName("menu_links");
        for (i = 0; i < links.length; i++) {
          links[i].style.color = "aliceblue";
          links[i].style.background = "rgb(52, 52, 60)";
        }

    }else{

        // Change image source path and alternative text
        document.querySelector(".moon").src="./Images/moon_off.png";
        document.querySelector(".moon").alt="moon_off";

        // Change background color
        document.body.style.color = "black";
      
        // Change background color
        let container = document.body.querySelector("div.grid-container");
        let item1 = document.body.querySelector("div.grid-container div.item1");
        let item2 = document.body.querySelector("div.grid-container div.item-2");
        let item3 = document.body.querySelector("div.grid-container div.item-3");
        let item4 = document.body.querySelector("div.grid-container div.item-4");
        let item5 = document.body.querySelector("div.grid-container div.item-5");

        item1.style.background = "lightgray";
        item2.style.background = "lightgray";
        item3.style.background = "lightgray";
        item4.style.background = "lightgray";
        item5.style.background = "lightgray";

        item2.querySelector("*").style.background = "lightgray";
        container.querySelector("ul.subnavigationlist").style.background = "lightgray";
        container.querySelector("nav.sub-nav").style.background = "lightgray";
        container.querySelector("div.item-n").style.background = "lightgray";

        item3.querySelector("article").style.background = "lightgray";
        item4.querySelector("article").style.background = "lightgray";
        item5.querySelector("img").style.background = "lightgray";
        item5.querySelector("h4").style.background = "lightgray";

        links = item5.querySelectorAll("a.source_link");
        for (i = 0; i < links.length; i++) {
          links[i].style.background = "lightgray";
          links[i].style.color = "black";
        }

        links = document.getElementsByTagName("strong");
        for (i = 0; i < links.length; i++) {
          links[i].style.background = "lightgray";
        }

        links = document.getElementsByTagName("p");
        for (i = 0; i < links.length; i++) {
          links[i].style.background = "lightgray";
        }

        links = document.getElementsByTagName("li");
        for (i = 0; i < links.length; i++) {
          links[i].style.background = "lightgray";
        }

        links = document.getElementsByClassName("lists");
        for (i = 0; i < links.length; i++) {
          links[i].style.background = "lightgray";
        }

        links = document.getElementsByClassName("practice_list");
        for (i = 0; i < links.length; i++) {
          links[i].style.background = "lightgray";
        }

        links = document.getElementsByClassName("img_content");
        for (i = 0; i < links.length; i++) {
          links[i].style.background = "lightgray";
        }

        links = document.getElementsByClassName("titles");
        for (i = 0; i < links.length; i++) {
          links[i].style.background = "lightgray";
        }

        links = document.getElementsByClassName("sublist-item");
        for (i = 0; i < links.length; i++) {
          links[i].style.background = "lightgray";
        }

        links = document.getElementsByClassName("menu_links");
        for (i = 0; i < links.length; i++) {
          links[i].style.color = "black";
          links[i].style.background = "lightgray";
        }
    }

}

function date(){

    var date = new Date().toLocaleDateString(); 
    var day = new Date().getDay(); 

    let item5 = document.body.querySelector("div.grid-container div.item-5");
    item5.display = "inline";

    if(day == "1"){

      item5.append(date + " - @ Nikos Siachamis");
      item5.querySelector("img.date_icon").src = "./Images/calendar-icons/42524.png";
      item5.querySelector("img.date_icon").display = "float: right";
        
    }else if(day == "2"){

      item5.append(date + " - @ Nikos Siachamis");
      item5.querySelector("img.date_icon").src = "./Images/calendar-icons/42305.png";
      item5.querySelector("img.date_icon").display = "float: right";
        
    }else if(day == "3"){

      item5.append(date + " - @ Nikos Siachamis");
      item5.querySelector("img.date_icon").src = "./Images/calendar-icons/4379879.png";
      item5.querySelector("img.date_icon").display = "float: right";
        
        
    }else if(day == "4"){

      item5.append(date + " - @ Nikos Siachamis");
      item5.querySelector("img.date_icon").src = "./Images/calendar-icons/42464.png";
      item5.querySelector("img.date_icon").display = "float: right";
        
    }else if(day == "5"){

      item5.append(date + " - @ Nikos Siachamis");
      item5.querySelector("img.date_icon").src = "./Images/calendar-icons/friday-icon-25.png";
      item5.querySelector("img.date_icon").display = "float: right";
        
        
    }else if(day == "6"){

      item5.append(date + " - @ Nikos Siachamis");
      item5.querySelector("img.date_icon").src = "./Images/calendar-icons/img_15441.png";
      item5.querySelector("img.date_icon").display = "float: right";
        
    }else if(day == "7"){

      item5.append(date + " - @ Nikos Siachamis");
      item5.querySelector("img.date_icon").src = "./Images/calendar-icons/42347.png";
      item5.querySelector("img.date_icon").display = "float: right";
        
    }

}