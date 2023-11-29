$(document).ready(() => {
  if(localStorage.getItem("emp_name") != undefined){
    document.getElementById("emp_name").innerHTML  = "<h3>"+localStorage.getItem("emp_name")+"</h3>";
  }
  if(localStorage.getItem("role") == "emp"){
    $("div#hrclass").hide();
  }
  $("#open-sidebar").click(() => {
    // add class active on #sidebar
    $("#sidebar").addClass("active");
    // show sidebar overlay
    $("#sidebar-overlay").removeClass("d-none");
  });
  $("#sidebar-overlay").click(function () {
    // add class active on #sidebar
    $("#sidebar").removeClass("active");
    // show sidebar overlay
    $(this).addClass("d-none");
  });
});

if(localStorage.getItem("emp_name") != undefined){
  
}

function punchin() {
  var employeeID = localStorage.getItem("emp_id");
  var settings = {
    url: "http://localhost:8080/api/attendence/?employeeID=" + employeeID,
    method: "POST",
    timeout: 0,
  };
  $.ajax(settings).done(function (response) {
    console.log(response);
    alert("punched in successfully");
  });
}
function punchout() {
  var employeeID = localStorage.getItem("emp_id");
  var a = new Date();
  date = a.getFullYear()+"-"+(a.getMonth()+1)+"-"+a.getDate();
  var settings = {
    url: "http://localhost:8080/api/attendence/"+employeeID+"?date="+date,
    method: "PUT",
    timeout: 0,
  };
  $.ajax(settings).done(function (response) {
    console.log(response);
    alert("punched out")
  });
}
