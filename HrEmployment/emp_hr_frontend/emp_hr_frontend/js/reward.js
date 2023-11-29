function openCity(evt, cityName) {
  document.getElementById("GetDepartmentsTable").style.display = "none";
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(cityName).style.display = "block";
  evt.currentTarget.className += " active";
}
function getEmployeeIds(selectelement) {
  var id = localStorage.getItem("emp_id");
  var settings = {
    url: "http://localhost:8080/employee/all/" + id,
    method: "GET",
    timeout: 0,
  };

  $.ajax(settings).done(function (response) {
    console.log(response);
    var selectElement = document.getElementById(selectelement);
    for (var i = 0; i < response.length; i++) {
      selectElement.add(new Option(response[i].empID));
    }
  });
}
function addreward(){
  var hrEmpID = localStorage.getItem("emp_id");
  var settings = {
    "url": "http://localhost:8080/api/rewards/",
    "method": "POST",
    "timeout": 0,
    "headers": {
      "Content-Type": "application/json"
    },
    "data": JSON.stringify({
      "recognitionType":  document.getElementById("recognitionType").value,
      "description":  document.getElementById("description").value,
      "empID": document.getElementById("selectemployeeid1").value,
      "hrEmpID": hrEmpID
    }),
  };
  
  $.ajax(settings).done(function (response) {
    console.log(response);
  });
}
function fetchreward(){
  var empID = document.getElementById("selectemployeeid2").value;
  var settings = {
    "url": "http://localhost:8080/api/rewards/"+empID,
    "method": "GET",
    "timeout": 0,
  };
  
  $.ajax(settings).done(function (response) {
    console.log(response);
    document.getElementById("GetDepartmentsTable").style.display = "block";
    let table = document.getElementById("GetDepartmentsTable");
    let row = table.insertRow(-1);
    let c1 = row.insertCell(0);
    c1.innerText = response[0].recognitionType;
    let c2 = row.insertCell(1);
    c2.innerText = response[0].description;
  });
}