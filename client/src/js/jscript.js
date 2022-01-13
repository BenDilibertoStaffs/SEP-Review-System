$(function () {
  //allow for an application to send




function removeFormDefault () {
    e.preventDefault();
    return false;
  }

  function allowFormDefault () {
      return true;
    }
close("#sign-up_modal");

close("#login_modal" );

function close(modal){

  $(modal + " button:first").click(function () {

         reset(modal, "Success: You have created an account");

      }

   )
}

//put the modal back into the original loading state - no error messages and clear fields
function reset(modal){
$(modal + " .alert-success:first" ).addClass("d-none");

  $(modal + " .alert-danger:first").addClass("d-none");


 $(modal + " input" ).val('');

 }



//when signup button is pressed it creates an account username and password
 $("#submit-sign-up").click(function (e) {

    let passwordFirstEntry = $("#password-1-sign-up").val() + "";

    let passwordSecondEntry = $("#password-2-sign-up").val() + "";


    let isSuccessful = false;

    let usernameEntry = $("#username-sign-up").val() + "";
  if (!usernameEntry){


       reset("#sign-up_modal" );

      $( "#sign-up_modal .alert-danger" ).removeClass("d-none");
          $( "#sign-up_modal .alert-danger" ).text("Error: username must be at least one character");

   }
    else if(usernameEntry && (passwordFirstEntry == passwordSecondEntry) && (passwordFirstEntry !== "")){

    let sign_up = {username: usernameEntry, password: passwordFirstEntry};


    $("#sign-up_modal " + ".form:first").on("click", removeFormDefault) ;

    $( "#sign-up_modal .alert-danger:first" ).addClass("d-none");
    ajaxTemplate("#sign-up_modal", "user/", "POST", sign_up, "Success: You have created an account");

      reset("#sign-up_modal");

    }
//if no password is entered
    else if(passwordFirstEntry === ""){
       reset("#sign-up_modal");

    $( "#sign-up_modal .alert-danger" ).removeClass("d-none");
    $( "#sign-up_modal .alert-danger" ).text("Error: password must be at least one character");





   }
   else if(passwordFirstEntry != passwordSecondEntry){
   $( "#sign-up_modal .alert-danger" ).removeClass("d-none");
       $( "#sign-up_modal .alert-danger" ).text("Error: passwords must match");
   }






 });
//when signup button is pressed it creates an account username and password
 $("#submit-login").click(function (e) {

    let passwordFirstEntry = $("#password-login").val() + " ";

    let usernameEntry = $("#username-login").val() + " ";


    let login = {username: usernameEntry, password: passwordFirstEntry};


    ajaxTemplate("#login_modal", "auth/", "POST", login, "","Error: Username and/or password is incorrect");

    reset("#login_modal");
 });



//generic template for ajax post, get
  function ajaxTemplate(modal,mapping, ajaxMethod, userObject, successMsg) {
    let json = JSON.stringify(userObject);


     $.ajax({
      url: "http://localhost:1234/api/" + mapping,
      contentType: "application/json",
      method: ajaxMethod,
      data: json,
      dataType: "text",

      success: function (data) {
      $(modal + " .alert-success" ).removeClass("d-none") ;

      $(modal + " .alert-success" ).text(successMsg + ". " + data);

      $(modal + " .alert-danger" ).addClass("d-none") ;

      },

      error: function (data) {

      $( modal + " .alert-danger" ).removeClass("d-none") ;
      $(modal + " .alert-danger" ).text(data.message);


      }
    });
  }


});
