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
function addDepartment() {
  var settings = {
    url: "http://localhost:8080/api/dept/",
    method: "POST",
    timeout: 0,
    headers: {
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      departmentName: document.getElementById("departmentName").value,
      budget: document.getElementById("budget").value,
      location: document.getElementById("location").value,
      employeeID: localStorage.getItem("emp_id")
    }),
  };
  console.log(settings);
  $.ajax(settings).done(function (response) {
    console.log(response);
  });
}
function getAllDepartments(){
  var settings = {
    "url": "http://localhost:8080/api/dept/",
    "method": "GET",
    "timeout": 0,
  };
  
  $.ajax(settings).done(function (response) {
    console.log(response);
    let table = document.getElementById("GetAllDepartmentsTable");
    for(var i=0;i<response.length;i++){
      let row = table.insertRow(-1);
      let c1 = row.insertCell(0);
      c1.innerText = response[i].deptID;
      let c2 = row.insertCell(1);
      c2.innerText = response[i].departmentName;
      let c3 = row.insertCell(2);
      c3.innerText = response[i].location;
      let c4 = row.insertCell(3);
      c4.innerText = response[i].budget;
    }
  });
}
function getDepartmentids(departmentvalue){
  var settings = {
    "url": "http://localhost:8080/api/dept/",
    "method": "GET",
    "timeout": 0,
  };
  
  $.ajax(settings).done(function (response) {
    console.log(response);
    var selectElement = document.getElementById(departmentvalue);
    for (var i = 0; i < response.length; i++) {
      selectElement.add(new Option(response[i].deptID));
    }
  });
}
function getdepartmentvalues(){
  var departmentid = document.getElementById("selectDepartment1").value; 
  var settings = {
    "url": "http://localhost:8080/api/dept/"+departmentid,
    "method": "GET",
    "timeout": 0,
  };
  
  $.ajax(settings).done(function (response) {
    console.log(response);
    document.getElementById("GetDepartmentsTable").style.display = "block";
    let table = document.getElementById("GetDepartmentsTable");
    let row = table.insertRow(-1);
    let c1 = row.insertCell(0);
    c1.innerText = response.deptID;
    let c2 = row.insertCell(1);
    c2.innerText = response.departmentName;
    let c3 = row.insertCell(2);
    c3.innerText = response.location;
    let c4 = row.insertCell(3);
    c4.innerText = response.budget;
  });
}

function updateDepartment(){
  var settings = {
    "url": "http://localhost:8080/api/dept/update/1",
    "method": "PUT",
    "timeout": 0,
    "headers": {
      "Content-Type": "application/json"
    },
    "data": JSON.stringify({
      "departmentName": document.getElementById("updepartmentName").value,
      "budget": document.getElementById("upbudget").value,
      "location": document.getElementById("uplocation").value,
      "employeeID": 1 //localhost
    }),
  };
  
  $.ajax(settings).done(function (response) {
    console.log(response);
  });
}