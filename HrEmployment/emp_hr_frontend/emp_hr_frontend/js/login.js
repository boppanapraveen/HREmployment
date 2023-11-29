function auth(){
    var settings = {
        "url": "http://localhost:8080/employee/authenticate",
        "method": "POST",
        "timeout": 0,
        "headers": {
          "Content-Type": "application/json"
        },
        "data": JSON.stringify({
          "email": $("#mail").val(),
          "password": $("#exampleInputPassword1").val()
        }),
      };
      
      $.ajax(settings).done(function (response) {
        console.log(response);
     
          localStorage.setItem("emp_id",response.empID);
          localStorage.setItem("role",response.role);
          window.location.replace(
              "../dashboard.html",
            );        
      }
      );
      $.ajax(settings).fail(function (response) {
          alert("login failed");
      }
      );
}