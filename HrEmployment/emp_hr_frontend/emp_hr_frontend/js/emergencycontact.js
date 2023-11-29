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
function getEmployeeIds(empID) {
  var id = localStorage.getItem("emp_id");
  var settings = {
    url: "http://localhost:8080/employee/all/" + id,
    method: "GET",
    timeout: 0,
  };

  $.ajax(settings).done(function (response) {
    console.log(response);
    var selectElement = document.getElementById(empID);
    for (var i = 0; i < response.length; i++) {
      selectElement.add(new Option(response[i].empID));
    }
  });
}
function addEmergencyContact() {
  var settings = {
    url: "http://localhost:8080/api/contacts/",
    method: "POST",
    timeout: 0,
    headers: {
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      name: document.getElementById("name").value,
      relation: document.getElementById("relation").value,
      contactNo: document.getElementById("contactNo").value,
      empID: localStorage.getItem("emp_id")
    }),
  };

  $.ajax(settings).done(function (response) {
    console.log(response);
  });
}
function updateEmergencyContact() {
  var hrId = localStorage.getItem("emp_id");
  var settings = {
    url: "http://localhost:8080/api/contacts/update/" + hrId,
    method: "PUT",
    timeout: 0,
    headers: {
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      name: document.getElementById("uname").value,
      relation: document.getElementById("urelation").value,
      contactNo: document.getElementById("ucontactNo").value,
      empID: localStorage.getItem("emp_id")
    }),
  };

  $.ajax(settings).done(function (response) {
    console.log(response);
  });
}
function deletecontact() {
  var contactNo = document.getElementById("upempid").value;
  var settings = {
    url: "http://localhost:8080/api/contacts/" + contactNo,
    method: "DELETE",
    timeout: 0,
  };

  $.ajax(settings).done(function (response) {
    console.log(response);
  });
}

function getAllEmergencyDetails() {
  var empID = document.getElementById("selectemployeeid3").value;
  var settings = {
    url: "http://localhost:8080/api/contacts/" + empID,
    method: "GET",
    timeout: 0,
  };

  $.ajax(settings).done(function (response) {
    console.log(response);
    document.getElementById("GetDepartmentsTable").style.display = "block";
    let table = document.getElementById("GetDepartmentsTable");
    let row = table.insertRow(-1);
    let c1 = row.insertCell(0);
    c1.innerText = response[0].name;
    let c2 = row.insertCell(1);
    c2.innerText = response[0].relation;
    let c3 = row.insertCell(2);
    c3.innerText = response[0].contactNo;
  });
}

function getEmerdetails(){
  var empid = localStorage.getItem("emp_id");
  var settings = {
    "url": "http://localhost:8080/api/contacts/"+empid,
    "method": "GET",
    "timeout": 0,
  };
  
  $.ajax(settings).done(function (response) {
    console.log(response);
    document.getElementById("upempid").innerHTML = "Name: "+response[0].empEmrID;
    document.getElementById("upname").innerHTML = "Name: "+response[0].name;
    document.getElementById("uprelation").innerHTML = "Name: "+response[0].relation;
    document.getElementById("upcontactNo").innerHTML = "Name: "+response[0].contactNo;
  });
}
