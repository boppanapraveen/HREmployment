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
function addhealthrecord() {
  var settings = {
    url: "http://localhost:8080/api/health/",
    method: "POST",
    timeout: 0,
    headers: {
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      medicalCondition: document.getElementById("medicalCondition").value,
      medications: document.getElementById("medications").value,
      allergies: document.getElementById("allergies").value,
      field: document.getElementById("field").value,
      empID: document.getElementById("selectemployeeid1").value,
    }),
  };

  $.ajax(settings).done(function (response) {
    console.log(response);
  });
}
function fetchrecord() {
  var empID = document.getElementById("selectemployeeid2").value;
  var settings = {
    url: "http://localhost:8080/api/health/"+empID,
    method: "GET",
    timeout: 0,
  };

  $.ajax(settings).done(function (response) {
    console.log(response);
    document.getElementById("GetDepartmentsTable").style.display = "block";
    let table = document.getElementById("GetDepartmentsTable");
    let row = table.insertRow(-1);
    let c1 = row.insertCell(0);
    c1.innerText = response.medicalCondition;
    let c2 = row.insertCell(1);
    c2.innerText = response.medications;
    let c3 = row.insertCell(2);
    c3.innerText = response.allergies;
    let c4 = row.insertCell(3);
    c4.innerText = response.field;
  });
}
