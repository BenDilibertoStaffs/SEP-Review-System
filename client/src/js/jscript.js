$(function () {
  //allow for an application to send
//id for submitted customer review
getLoginState();


$("#sign-up_modal button:first").click(reset("#sign-up_modal"));

  $("#Login_modal button:first").click(reset("#Login_modal"));

  $("#add-business_modal button:first").click(reset("#add-business_modal"));

  removeFormDefault();

if(document.getElementsByClassName("review-body")[0]){
getTemplate("#inner-search-section", "restaurant/", "", "Success: has updated the restaurants ","Error: restaurants aren't stored", true);
}

$( ".dropdown-menu button[data-bs-target='#Login_modal']:contains('Sign out')" ).click(function(){
 sessionStorage.removeItem("loggedInRRS") ;
  headerSignoutState();

})


$( "body" ).change(function() {
 getLoginState;
});
function getLoginState(){
if( sessionStorage.getItem("loggedInRRS")){
headerLoginState();


}

else{
 headerSignoutState();

}

}
//document.querySelector("body")
function headerLoginState(){

  $(".dropdown-menu button" ).removeClass("d-none");

  $( ".dropdown-menu button[data-bs-target='#sign-up_modal']" ).addClass("d-none") ;
  $( ".dropdown-menu button[data-bs-target='#Login_modal']:contains('Login')" ).addClass("d-none") ;
  $( "nav a[href='review.html']" ).removeClass("d-none") ;
  $( "a[ href='review.html']:contains('Review')" ).removeClass("d-none") ;
  document.querySelector("h6").innerHTML=  "Welcome " + sessionStorage.getItem("loggedInRRS");
  $("h6").removeClass("d-none");


}

function headerSignoutState(){


  $(".dropdown-menu button" ).addClass("d-none");

  $( ".dropdown-menu button[data-bs-target='#sign-up_modal']" ).removeClass("d-none") ;
  $( ".dropdown-menu button[data-bs-target='#Login_modal']:contains('Login')" ).removeClass("d-none") ;
  $( "nav a[href='review.html']" ).addClass("d-none") ;
  $( "a[ href='review.html']:contains('Review')" ).addClass("d-none") ;
  document.querySelector("h6").innerHTML=  "";
  $("h6").addClass("d-none");



}






function removeFormDefault () {
    $("form.modal").click( function(e){ e.preventDefault()});
    return false;

}







$(".dropdown-menu button:contains('Sign out')" ).click(function () { sessionStorage.clear(); window.location.href = './index.html';
 } );







//put the modal back into the original loading state - no error messages and clear fields
function reset(modal){



return function(){
 let inputs = document.querySelectorAll(modal + " input" );

 for( let i = 0; i < inputs.length; i++){

   inputs[i].value = '';

 }

 $( modal + " .alert-danger" ).addClass("d-none");
 $( modal + " .alert-success" ).addClass("d-none");


}

 }

//when signup button is pressed it creates an account username and password
 $("#submit-sign-up").click(function (e) {


   let passwordFirstEntry = document.getElementById("password-1-sign-up").value + "";
   let passwordSecondEntry = document.getElementById("password-2-sign-up").value + "";

    let usernameEntry = document.getElementById("username-sign-up").value + "";


  if (usernameEntry == " " || usernameEntry == ""){



      $( "#sign-up_modal .alert-danger" ).removeClass("d-none");
      $( "#sign-up_modal .alert-danger" ).text("Error: username must be at least one character");

   }
    else if(usernameEntry != " " && usernameEntry != "" && (passwordFirstEntry == passwordSecondEntry) && passwordFirstEntry != "" && passwordFirstEntry != " "){

    let sign_up = {username: usernameEntry, password: passwordFirstEntry};



    $( "#sign-up_modal .alert-danger:first" ).addClass("d-none");
    ajaxTemplate("#sign-up_modal", "user/", "POST", sign_up, "Success: You have created an account");

    $( "#sign-up_modal .alert-danger" ).text("Error: account already exists");


    $( "#sign-up_modal .alert-danger" ).removeClass("d-none");







   }

    else if(passwordFirstEntry == " " || passwordFirstEntry == ""  ){

      $( "#sign-up_modal .alert-danger" ).removeClass("d-none");

          $( "#sign-up_modal .alert-danger" ).text("Error: password must be at least one character");
}
 // if second entry of password doesn't equal first
   else if(passwordFirstEntry != passwordSecondEntry){


   $( "#sign-up_modal .alert-danger" ).removeClass("d-none");

       $( "#sign-up_modal .alert-danger" ).text("Error: passwords must match");

   }






        });
//when signup button is pressed it creates an account username and password
 $("#submit-login").click(function () {

    let passwordFirstEntry = document.getElementById("password-login").value + "";

    let usernameEntry = document.getElementById("username-login").value + "";

    let login = {username: usernameEntry, password: passwordFirstEntry};


    console.log(usernameEntry);

    if (usernameEntry == " " || usernameEntry == ""){


      $( "#Login_modal .alert-danger" ).text("Error: username must be at least one character");

      $( "#Login_modal .alert-danger" ).removeClass("d-none");



  }

  else if (passwordFirstEntry == " " || passwordFirstEntry == ""){



             $( "#Login_modal .alert-danger" ).text("Error: username and/or password is incorrect");

                          $( "#Login_modal .alert-danger" ).removeClass("d-none");




         }
  else if(usernameEntry != "" && usernameEntry != " "  && passwordFirstEntry != "" && passwordFirstEntry != " ")
 {
    ajaxTemplate("#Login_modal", "auth/", "POST", login, "Success: you are logged in","Error: username and/or password is incorrect");


   }


 });

 $("#submit-create-add-business").click(function (e) {

    let businessEntry = document.getElementById("create-add-business").value + "";
    let iD = getID()  + "";


    let business = {id: iD, name : businessEntry};




    if (businessEntry == " " || businessEntry == ""  ){

              alert("business must have a name")


          $( "#add-business_modal .alert-danger" ).removeClass("d-none");
              $( "#add-business_modal .alert-danger" ).text("Error: restaurant name must be at least one character");


       }
       else
       {
                ajaxTemplate("#add-business_modal", "restaurant/", "GET", "", "Success: restaurant can be added","Error: restaurant name already in use");

                let question = "Can you confirm you want to create a restaurant";

                if(confirm(question)){

                  ajaxTemplate("#add-business_modal", "restaurant/", "POST", business, "Success: you have added a new restaurant","");

                }
                else
                {
                  alert("action cancelled");
                }




       }


 });

 $("#inner-search-section-button").click(function (e) {

    let businessEntry = document.querySelector("#inner-search-section input").value + "";


   restaurant("#inner-search-section", businessEntry,false);


 });

 $("#main-search + button").click(function (e) {

     let businessEntry = document.querySelector("#main-search").value;


     restaurant("#main-search", businessEntry, false  );


  })

 $("#form-customer-review button:first").click(function (e) {

 let isFound = false;
let cuisineInputs  = $("input[name='cuisines']") ;
   let cuisineCollection = [];

      for(let i = 0 ; i < cuisineInputs.length; i++){


         if(cuisineInputs[i].checked){


              isFound = true;
              cuisineCollection.push(cuisineInputs[i].value);
         }

      }
      if(!isFound){
      cuisineCollection = null;
      }
isFound = false;
let valueForMoney  = $("input[name='value-for-money']") ;
let valueForMoneyChecked = "";
let i = 0;
      do{

               if(valueForMoney[i].checked){

                  valueForMoneyChecked = valueForMoney[i].value;
                  idFound = true;

               }

            i++;

        }
       while(!isFound && (i < valueForMoney.length))

 isFound = false;

    let foodQualityInputs  = $("input[name='food-quality']") ;
     foodQualityChecked = "";
    let j = 0;
          do{


                   if(foodQualityInputs[j].checked){

                      foodQualityChecked = foodQualityInputs[j].value;
                      isFound = true;

                   }
                   j++;
            }
           while(!isFound && (j < foodQualityInputs.length))

 isFound = false;

// if at least one cuisine option is checked
 if(cuisineCollection){
let restaurantReviewId =  document.querySelector("#inner-search-input").value;

foodQualityChecked = parseInt(foodQualityChecked,10);
 valueForMoneyChecked = parseInt(valueForMoneyChecked,10);
  alert("food quality " + foodQualityChecked);
 alert("value for money " + valueForMoneyChecked);
  alert("cuisine collection " +  cuisineCollection[0]);

  alert(restaurantReviewId);

 let anID = getID();
   let business = {id: anID, restaurantId: restaurantReviewId, cuisines: cuisineCollection, quality: foodQualityChecked, value: valueForMoneyChecked};
  e.preventDefault();

   ajaxTemplate("", "review/", "POST", business, "Success: you have added a new review","error: review not added");

document.querySelector("#inner-search-input").value = "";

$(".card.col-md-9.mt-5.col-12").addClass("d-none");
   }

   else{
   alert("At least one cuisine must be picked");


   }

   });


 $("#form-customer-review button:first").click(function (e) {

 let isFound = false;
let cuisineInputs  = $("input[name='cuisines']") ;
   let cuisineCollection = [];

      for(let i = 0 ; i < cuisineInputs.length; i++){


         if(cuisineInputs[i].checked){


              isFound = true;
              cuisineCollection.push(cuisineInputs[i].value);
         }

      }
      if(!isFound){
      cuisineCollection = null;
      }
isFound = false;
let valueForMoney  = $("input[name='value-for-money']") ;
let valueForMoneyChecked = "";
let i = 0;
      do{

               if(valueForMoney[i].checked){

                  valueForMoneyChecked = valueForMoney[i].value;
                  idFound = true;

               }

            i++;

        }
       while(!isFound && (i < valueForMoney.length))

 isFound = false;

    let foodQualityInputs  = $("input[name='food-quality']") ;
     foodQualityChecked = "";
    let j = 0;
          do{


                   if(foodQualityInputs[j].checked){

                      foodQualityChecked = foodQualityInputs[j].value;
                      isFound = true;

                   }
                   j++;
            }
           while(!isFound && (j < foodQualityInputs.length))

 isFound = false;

// if at least one cuisine option is checked
 if(cuisineCollection){
let restaurantReviewId =  document.querySelector("#inner-search-input").value;

foodQualityChecked = parseInt(foodQualityChecked,10);
 valueForMoneyChecked = parseInt(valueForMoneyChecked,10);
  alert("food quality " + foodQualityChecked);
 alert("value for money " + valueForMoneyChecked);
  alert("cuisine collection " +  cuisineCollection[0]);

  alert(restaurantReviewId);

 let anID = getID();
   let business = {id: anID, restaurantId: restaurantReviewId, cuisines: cuisineCollection, quality: foodQualityChecked, value: valueForMoneyChecked};
  e.preventDefault();

   ajaxTemplate("", "review/", "POST", business, "Success: you have added a new review","error: review not added");

document.querySelector("#inner-search-input").value = "";

$(".card.col-md-9.mt-5.col-12").addClass("d-none");
   }

   else{
   alert("At least one cuisine must be picked");


   }

   });



function restaurant(modal, businessEntry, isOnLoad){

    let business = { name : businessEntry};

    if (businessEntry == " " || businessEntry == ""  ){

              alert("business must have a name")


       }
       else
       {
                getTemplate(modal, "restaurant/",business,  "Success: the system has that name","Error: name isn't stored",isOnLoad);




       }



}

function getID(){
let today = new Date;
let id = " " + today.getUTCDate() + today.getUTCMonth() +  today.getUTCFullYear() + today.getUTCHours() + today.getUTCMinutes() + today.getUTCSeconds() + today.getUTCMilliseconds() ;
return id;

}



//generic template for ajax get
  function getTemplate(container,mapping, userObject, successMsg, errorMsg, isOnLoad) {
    let json = JSON.stringify(userObject);


     $.ajax({
      url: "http://localhost:1234/api/" + mapping,
      contentType: "application/json",
      method: "GET",
      dataType: "text",

      success: function (data) {


             let isFound = false;



let restaurantReviewId = "";
        if(mapping == "restaurant/" && isOnLoad){


          data = JSON.parse(data);



         let datalist = document.querySelector("#inner-search-section datalist");

         for(let i = 0; i < data.length; i++){
          let option =  document.createElement("option");

            option.value = data[i].name;



            datalist.appendChild(option);

         }


       }
       if(mapping == "restaurant/" && !isOnLoad){
              data = JSON.parse(data);


             let i = 0;
                while(!isFound && i < data.length){


               if(data[i].name === userObject.name){

                alert(userObject.name + " is found" );

                   if(container === "#inner-search-section")
                      document.querySelector("#inner-search-input").value =  data[i].id;

                    isFound = true;

                 }

                 i++;

           }

           if(!isFound){
             if(confirm(userObject.name + " is not found. Do you want to add it?")){
            if(container === "#inner-search-section"){
              let restaurantReviewId = getID();
             }
             ajaxTemplate("#inner-search-section", "restaurant/", "POST",{name: userObject.name, id: restaurantReviewId}, "Success: you have added a new restaurant","Error: restaurant not added");

            let datalist = document.querySelector( "#inner-search-section datalist");

            let option =  document.createElement("option");

            option.value = userObject.name ;

            datalist.appendChild(option);

            alert("business has been added. Resubmit to send review")


            idFound = true;

             document.querySelector("#inner-search-input").value =  data[i].id;








             }

             }

             if(isFound){

             if(container == "#inner-search-section" ){



                $( "div.card.d-none").addClass("see-class");
               $( "div.card.d-none").removeClass("d-none");

              $( "#inner-search-section").addClass("d-none");






                }

               }
             }







      },



      error: function (data) {



       if(mapping == "restaurant/"){


                 alert(successMsg);


      }

      }})





};
//generic template for ajax post, get
  function ajaxTemplate(modal,mapping, ajaxMethod, userObject, successMsg, errorMsg) {
    let json = JSON.stringify(userObject);


     $.ajax({
      url: "http://localhost:1234/api/" + mapping,
      contentType: "application/json",
      method: ajaxMethod,
      data: json,
      dataType: "text",

      success: function (data) {

      if(mapping == "user/"){
      $(modal + " .alert-success" ).removeClass("d-none") ;

      $(modal + " .alert-success" ).text(successMsg + ". " + data);

      $(modal + " .alert-danger" ).addClass("d-none") ;




      }

      else if(mapping == "review/"){

           alert(successMsg);

         }

      else if(mapping == "auth/" ){




          //key is logged in restaurant review system (RRS) key and  username is value)
          sessionStorage.setItem("loggedInRRS", userObject.username);


         $('div[aria-label="Login information incorrect"]' ).addClass("d-none");

         $('div[aria-label="Login information incorrect"]' ).attr("aria-label","Correct login");


          headerLoginState();





      }
      else if(mapping == "restaurant/"){

                 alert(data);



      }

      },

      error: function (data) {

      $( modal + " .alert-danger" ).removeClass("d-none") ;

      if(mapping != "auth/"){

      $(modal + " .alert-danger" ).text(data.message);


      $(modal + " .alert-success" ).addClass("d-none") ;





      }


        else if(mapping == "review/"){

           alert("errorMsg");

         }
      else if(mapping == "auth/" ){

       $('div[aria-label="Login information incorrect"]' ).text("Error: incorrect username and/or password");

       $('div[aria-label="Login information incorrect"]' ).addClass("alert-danger");

       $('div[aria-label="Login information incorrect"]' ).removeClass("alert-success");

       $('div[aria-label="Login information incorrect"]' ).removeClass("d-none");


      }
      else if(mapping == "restaurant/"){


                 alert(successMsg);


      }

     reset(modal);
}})





}});