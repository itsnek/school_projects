// Event listener that loads the function after all the high-level code is loaded,e.g. html elements
window.addEventListener('load' , date);{
  console.log("Date loaded")
}

window.addEventListener('load', function(){

  let dark = document.getElementById('submit_button');
  dark.addEventListener('click' , tester);{
    console.log("Dark mode loaded")
  }

  let check_f = document.getElementById('checkbox_first');
  check_f.addEventListener('click' , myFunction);{
    console.log("Show password loaded")
  }

  let check_s = document.getElementById('checkbox_second');
  check_s.addEventListener('click' , myFunction);{
    console.log("Show password confirmation loaded")
  }

});


// Function that changes the colors of th website to darker colors when the switch with the moon is pressed.
function darkmode(filename) {

    // Set local variables
    let main = document.body;
    let container = document.body.querySelector("div.grid-container");
    let item1 = document.body.querySelector("div.grid-container div.item1");
    let item2 = document.body.querySelector("div.grid-container div.item-2");
    let item3 = document.body.querySelector("div.grid-container div.item-3");
    let item4 = document.body.querySelector("div.grid-container div.item-4");
    let item5 = document.body.querySelector("div.grid-container div.item-5");
    let itemn = document.body.querySelector("div.grid-container div.item-n");

    if(document.querySelector(".moon").alt === 'moon_off'){

        // Change image source path and alternative text
        document.querySelector(".moon").src="./Images/moon_on_2.png";
        document.querySelector(".moon").alt="moon_on";

        // Change font color
        main.style.color = "aliceblue";

        item1.style.background = "rgb(52, 52, 60)";
        item5.style.background = "rgb(52, 52, 60)";
        itemn.style.background = "rgb(52, 52, 60)";
        item5.querySelector("img").style.background = "rgb(52, 52, 60)";
        item5.querySelector("h4").style.background = "rgb(52, 52, 60)";

        if(filename != 'register'){

          item2.style.background = "rgb(52, 52, 60)";
          item3.style.background = "rgb(52, 52, 60)";
          item4.style.background = "rgb(52, 52, 60)";
          
          item2.querySelector("*").style.background = "rgb(52, 52, 60)";
          item3.querySelector("article").style.background = "rgb(52, 52, 60)";
          item4.querySelector("article").style.background = "rgb(52, 52, 60)";
          container.querySelector("ul.subnavigationlist").style.background = "rgb(52, 52, 60)";
          container.querySelector("nav.sub-nav").style.background = "rgb(52, 52, 60)";

        }else{

          item1.querySelector("p.subtitle").style.background = "rgb(52, 52, 60)";
          itemn.querySelector("form").style.background = "rgb(52, 52, 60)";
          container.querySelector("div.questionary").style.background = "rgb(52, 52, 60)";
          main.querySelector("div.labels#yesno div.answer").style.background = "rgb(52, 52, 60)";
          main.querySelector("details.intro").style.background = "rgb(52, 52, 60)";
          main.querySelector("details.intro summary").style.background = "rgb(52, 52, 60)";
          // container.getElementsByClassName("star").style.background = "rgb(52, 52, 60)";

          let container_fieldset = container.querySelectorAll("fieldset");
          links = container.querySelectorAll("fieldset div");
          for (i = 0; i < links.length; i++) {
            links[i].style.background = "rgb(52, 52, 60)";
            links[i].querySelector("*").style.background = "rgb(52, 52, 60)";
          }

          links = container_fieldset;
          for (i = 0; i < links.length; i++) {
            links[i].style.background = "rgb(52, 52, 60)";
            links[i].querySelector("*").style.background = "rgb(52, 52, 60)";
          }

          links = document.getElementsByClassName("labels");
          for (i = 0; i < links.length; i++) {
            links[i].style.background = "rgb(52, 52, 60)";
            links[i].querySelector("*").style.background = "rgb(52, 52, 60)";
          }
          
          links = document.getElementsByTagName("input");
          for (i = 0; i < links.length; i++) {
            links[i].style.color = "aliceblue";
            links[i].style.background = "rgb(52, 52, 60)";
          }

          links = document.getElementsByClassName("password");
          for (i = 0; i < links.length; i++) {
            links[i].style.color = "aliceblue";
            links[i].style.background = "rgb(52, 52, 60)";
          }

          links = document.body.querySelectorAll("label.answer");
          for (i = 0; i < links.length; i++) {
            links[i].style.color = "aliceblue";
            links[i].style.background = "rgb(52, 52, 60)";
          }

          links = container.querySelectorAll("span.star");
          for (i = 0; i < links.length; i++) {
            links[i].style.background = "rgb(52, 52, 60)";
          }

        }

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
        item1.style.background = "lightgray";
        item5.style.background = "lightgray";
        itemn.style.background = "lightgray";
        item5.querySelector("img").style.background = "lightgray";
        item5.querySelector("h4").style.background = "lightgray";

        if(filename != 'register'){

          item2.style.background = "lightgray";
          item3.style.background = "lightgray";
          item4.style.background = "lightgray";

          item2.querySelector("*").style.background = "lightgray";
          item3.querySelector("article").style.background = "lightgray";
          item4.querySelector("article").style.background = "lightgray";
          container.querySelector("ul.subnavigationlist").style.background = "lightgray";
          container.querySelector("nav.sub-nav").style.background = "lightgray";

        }else{

          item1.querySelector("p.subtitle").style.background = "lightgray";
          itemn.querySelector("form").style.background = "lightgray";
          container.querySelector("div.questionary").style.background = "lightgray";
          main.querySelector("div.labels#yesno div.answer").style.background = "lightgray";
          main.querySelector("details.intro").style.background = "lightgray";
          main.querySelector("details.intro summary").style.background = "lightgray";

          let container_fieldset = container.querySelectorAll("fieldset");
          links = container.querySelectorAll("fieldset div");
          for (i = 0; i < links.length; i++) {
            links[i].style.background = "lightgray";
            links[i].querySelector("*").style.background = "lightgray";
          }

          links = container_fieldset;
          for (i = 0; i < links.length; i++) {
            links[i].style.background = "lightgray";
            links[i].querySelector("*").style.background = "lightgray";
          }

          links = document.getElementsByClassName("labels");
          for (i = 0; i < links.length; i++) {
            links[i].style.background = "lightgray";
            links[i].querySelector("*").style.background = "lightgray";
          }
          
          links = document.getElementsByTagName("input");
          for (i = 0; i < links.length; i++) {
            links[i].style.color = "black";
            links[i].style.background = "lightgray";
          }

          links = document.getElementsByClassName("password");
          for (i = 0; i < links.length; i++) {
            links[i].style.color = "black";
            links[i].style.background = "lightgray";
          }

          links = document.body.querySelectorAll("label.answer");
          for (i = 0; i < links.length; i++) {
            links[i].style.color = "black";
            links[i].style.background = "lightgray";
          }

          links = container.querySelectorAll("span.star");
          for (i = 0; i < links.length; i++) {
            links[i].style.background = "lightgray";
          }

          document.getElementById("submit_button").style.background = "rgb(52, 52, 60)";
          document.getElementById("submit_button").style.color = "aliceblue";

        }
        
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

// Function that changes the image on footer according to day.
function date(){

  var date = new Date().toLocaleDateString(); 
  var day = new Date().getDay(); 

  let item5 = document.body.querySelector("div.grid-container div.item-5");
  item5.display = "inline";

  if(day == "1"){

    item5.append(date + " - @ Nikos Siachamis");
    item5.querySelector("img.date_icon").src = "./Images/calendar-icons/42524.png";
      
  }else if(day == "2"){

    item5.append(date + " - @ Nikos Siachamis");
    item5.querySelector("img.date_icon").src = "./Images/calendar-icons/42305.png";
      
  }else if(day == "3"){

    item5.append(date + " - @ Nikos Siachamis");
    item5.querySelector("img.date_icon").src = "./Images/calendar-icons/4379879.png";
      
      
  }else if(day == "4"){

    item5.append(date + " - @ Nikos Siachamis");
    item5.querySelector("img.date_icon").src = "./Images/calendar-icons/42464.png";
      
  }else if(day == "5"){

    item5.append(date + " - @ Nikos Siachamis");
    item5.querySelector("img.date_icon").src = "./Images/calendar-icons/friday-icon-25.png";      
      
  }else if(day == "6"){

    item5.append(date + " - @ Nikos Siachamis");
    item5.querySelector("img.date_icon").src = "./Images/calendar-icons/img_15441.png";
      
  }else if(day == "7"){

    item5.append(date + " - @ Nikos Siachamis");
    item5.querySelector("img.date_icon").src = "./Images/calendar-icons/42347.png";
      
  }

}

var y1,y2;

function myFunction(){
    var x;
    let checkbox_first = document.getElementById("checkbox_first")
    let checkbox_second = document.getElementById("checkbox_second")
    let password = document.getElementById("password")
    let password_confirmation = document.getElementById("password_confirmation")

    if(checkbox_first.checked == true && checkbox_second.checked != true && y1 != "text"){
        x = password;
    }else if(checkbox_first.checked == false && y1 == "text"){
        x = password;
    }else if(checkbox_first.checked == true && checkbox_second.checked == true && y2 == "text"){
      x = password;
    }else if(checkbox_second.checked == true && checkbox_first.checked == true ){
      x = password_confirmation;
    }else if(checkbox_second.checked == true && y2 != "text"){
        x = password_confirmation;
    }else if(checkbox_second.checked == false && y2 == "text" ){
        x = password_confirmation;
    }

    if (x.type === "password") {
        x.type = "text";
    }else {
        x.type = "password";
    }

    if (x.id === "password") {
        y1 = x.type;
    }else if(x.id === "password_confirmation"){
        y2 = x.type;
    }

}

function tester() {

  let inpObj1 = document.getElementById("password");
  if (!inpObj1.checkValidity()) {
    alert(inpObj1.validationMessage + "\nError at : " + inpObj1.name);
  } else {
  } 

  let inpObj2 = document.getElementById("password_confirmation");
  if (!inpObj2.checkValidity()) {
    alert(inpObj2.validationMessage + "\nError at : " + inpObj2.name);
  } else {
  }

  var txt=""
  if (document.getElementById("date").validity.rangeUnderflow) {
    txt = "Value too small";
    alert(txt + "\nError at : " + document.getElementById("telephone").name);
  }

  var txt = "";
  if (document.getElementById("date").validity.rangeOverflow) {
    txt = "Value too large";
    alert(txt + "\nError at : " + document.getElementById("lname").name);
  }

  var txt=""
  if (document.getElementById("telephone").validity.rangeUnderflow) {
    txt = "Value too small";
    alert(txt + "\nError at : " + document.getElementById("telephone").name);
  }

  var txt=""
  if (document.getElementById("telephone").validity.rangeOverflow) {
    txt = "Value too large";
    alert(txt + "\nError at : " + document.getElementById("telephone").name);
  }

  var txt = "";
  if (document.getElementById("lname").validity.rangeUnderflow) {
    txt = "Value too small";
    alert(txt + "\nError at : " + document.getElementById("lname").name);
  }

  var txt = "";
  if (document.getElementById("lname").validity.rangeOverflow) {
    txt = "Value too large";
    alert(txt + "\nError at : " + document.getElementById("lname").name);
  }

  // Works ok but needs to put the 2 first checkValidity
  // in comments, or else in the next submit throughs an alert for no reason.
  if(inpObj1.value.match(inpObj2.value)){
    inpObj1.setCustomValidity("")
  }else{
    inpObj1.setCustomValidity("The passwords don't match!")
    inpObj1.reportValidity();
  }

} 