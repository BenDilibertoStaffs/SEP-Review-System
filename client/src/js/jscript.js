$(function () {
  //allow for an application to send
getLoginState();
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
    $("form").click( function(e){ e.preventDefault()});
    return false;

}







$(".dropdown-menu button:contains('Sign out')" ).click(function () { sessionStorage.clear()  } );


  document.querySelector( "#Login_modal button").addEventListener("click",
        function(){ reset("#Login_modal")});







//put the modal back into the original loading state - no error messages and clear fields
function reset(modal){




 let inputs = document.querySelectorAll(modal + " input" );

 console.log(inputs);

 for( let i = 0; i < inputs.length; i++){
   inputs[i].value = "";
 }





 }

//when signup button is pressed it creates an account username and password
 $("#submit-sign-up").click(function (e) {


   let passwordFirstEntry = document.getElementById("password-1-sign-up").value;
   let passwordSecondEntry = document.getElementById("password-2-sign-up").value;

    let usernameEntry = document.getElementById("username-sign-up").value;

    let isSuccessful = false;

  if (usernameEntry == " " || usernameEntry == ""){



      $( "#sign-up_modal .alert-danger" ).removeClass("d-none");
      $( "#sign-up_modal .alert-danger" ).text("Error: username must be at least one character");

   }
    else if(usernameEntry != " " && usernameEntry != "" && (passwordFirstEntry == passwordSecondEntry) && passwordFirstEntry != "" && passwordFirstEntry != " "){

    let sign_up = {username: usernameEntry, password: passwordFirstEntry};



    $( "#sign-up_modal .alert-danger:first" ).addClass("d-none");
    ajaxTemplate("#sign-up_modal", "user/", "POST", sign_up, "Success: You have created an account");


    $( "#sign-up_modal .alert-danger" ).removeClass("d-none");
    $( "#sign-up_modal .alert-danger" ).text("Error: password must be at least one character");





   }
   // if second entry of password doesn't equal first
   else if(passwordFirstEntry != passwordSecondEntry){


   $( "#sign-up_modal .alert-danger" ).removeClass("d-none");

       $( "#sign-up_modal .alert-danger" ).text("Error: passwords must match");

   }



        reset("#sign-up_modal");


        });
//when signup button is pressed it creates an account username and password
 $("#submit-login").click(function (e) {

    let passwordFirstEntry = document.getElementById("password-login").value;

    let usernameEntry = document.getElementById("username-login").value;


    let login = {username: usernameEntry, password: passwordFirstEntry};




    if (usernameEntry == " " || usernameEntry == ""){



      $( "#login_modal .alert-danger" ).removeClass("d-none");
      $( "#login_modal .alert-danger" ).text("Error: username must be at least one character");
      reset("#login_modal" );

  }
  else
 {
    ajaxTemplate("#login_modal", "auth/", "POST", login, "Success: you are logged in","Error: Username and/or password is incorrect");
    reset("#login_modal" );


   }

   document.getElementById("password-login").value = "";

    document.getElementById("username-login").value = "";
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

                  reset("#add-business_modal" );

 });


function getID(){
let today = new Date;
let id = " " + today.getUTCDate() + today.getUTCMonth() +  today.getUTCFullYear() + today.getUTCHours() + today.getUTCMinutes() + today.getUTCSeconds() + today.getUTCMilliseconds() ;
return id;

}

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
      else if(mapping == "auth/" ){




          //key is logged in restaurant review system (RRS) key and  username is value)
          sessionStorage.setItem("loggedInRRS", userObject.username);

          headerLoginState();





      }
      else if(mapping == "restaurant/"){

                 alert(data);

         headerLoginState();

      }

      },

      error: function (data) {

      $( modal + " .alert-danger" ).removeClass("d-none") ;

      if(mapping != "auth/"){

      $(modal + " .alert-danger" ).text(data.message);

      }
      else if(mapping == "auth/" ){

       $('div[aria-label="Login information incorrect"]' ).text("Error: incorrect username and/or password");

       $('div[aria-label="Login information incorrect"]' ).addClass("alert-danger");

       $('div[aria-label="Login information incorrect"]' ).removeClass("alert-success");

       $('div[aria-label="Login information incorrect"]' ).removeClass("d-none");

        reset(modal);

      }
      else if(mapping == "restaurant/"){


                 alert(successMsg);


      }}})



close("#sign-up_modal");

  close("#login_modal");

  close("#add-business_modal");

  removeFormDefault();


}});
