$(function () {
  //allow for an application to send

  $("form").click(function (e) {
    e.preventDefault();
    return false;
  });

close("#sign-up_modal", "Success: You have created an account" );


function close(modal, successMsg){

  $(modal + " button:first").click(function () {

         reset(modal, "Success: You have created an account");

      }

   )
}

//put the modal back into the original loading state - no error messages and clear fields
function reset(modal, successMsg){
$(modal + " .alert-success:first" ).addClass("d-none");

  $(modal + " .alert-danger:first").addClass("d-none");


  $(modal + " .alert-success:first" ).empty();

 $(modal + " .alert-success:first" ).text(successMsg);

 $(modal + " input" ).val('');

 }



//when signup button is pressed it creates an account username and password
 $("#submit-sign-up").click(function (e) {

    let passwordFirstEntry = $("#password-1-sign-up").val() + " ";

    let passwordSecondEntry = $("#password-2-sign-up").val() + " ";


    let isSuccessful = false;

    let usernameEntry = $("#username-sign-up").val() + " ";

    if(passwordFirstEntry == passwordSecondEntry){

    let sign_up = {username: usernameEntry, password: passwordFirstEntry};

    reset("#sign-up_modal");

    ajaxTemplate("#sign-up_modal", "user/", "POST", sign_up, "Success: You have created an account");
    }
    else{

    $( "#sign-up_modal .alert-danger" ).removeClass("d-none");
    $( "#sign-up_modal .alert-danger" ).text("Error: the passwords don't match");
   }

   reset("#sign-up_modal", "Success: You have created an account" );
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
      },

      error: function (data) {

      $( modal + " .alert-danger" ).removeClass("d-none") ;



      }
    });
  }


});
