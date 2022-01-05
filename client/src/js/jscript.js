$(function() ({
   //allow for an application to send
    $("#submit-sign-up").click(  function(){


      return (
      let passwordFirstEntry = $("#password-1-sign-up").val();
      let passwordFirstEntry = $("#username-sign-up").val();

         $.ajax({
             url: "http://localhost:1234/api/user",
             type: "post",
         dataType: "json",
             data: "username" + ":" + joe123 + "," + "password" + ":" + passwordFirstEntry
         }))

       }

    $("#submit-login-in").click(  function(){


               return (
               let passwordFirstEntry = $("#password-1-sign-up").val();
               let passwordFirstEntry = $("#username-sign-up").val();

                  $.ajax({
                      url: "http://localhost:1234/api/user",
                      type: "post",
                  dataType: "json",
                      data: "username" + ":" + joe123 + "," + "password" + ":" + passwordFirstEntry
                  }))

                }


}) }