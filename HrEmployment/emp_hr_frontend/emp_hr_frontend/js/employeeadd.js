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
function addhr() {
  var settings = {
    url: "http://localhost:8080/employee/",
    method: "POST",
    timeout: 0,
    headers: {
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      firstName: document.getElementById("firstName").value,
      lastName: document.getElementById("lastName").value,
      dateOfBirth: document.getElementById("dateOfBirth").value,
      gender: document.getElementById("gender").value,
      email: document.getElementById("email").value,
      address: document.getElementById("address").value,
      phone: document.getElementById("phone").value,
      dateOfJoining: document.getElementById("dateOfJoining").value,
      role: "HR",
      password: document.getElementById("password").value,
    }),
  };
  console.log(settings);
  $.ajax(settings).done(function (response) {
    console.log(response);
  });
}

function addemployee() {
  var settings = {
    url: "http://localhost:8080/employee/",
    method: "POST",
    timeout: 0,
    headers: {
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      firstName: document.getElementById("efirstName").value,
      lastName: document.getElementById("elastName").value,
      dateOfBirth: document.getElementById("edateOfBirth").value,
      gender: document.getElementById("egender").value,
      email: document.getElementById("eemail").value,
      address: document.getElementById("eaddress").value,
      phone: document.getElementById("ephone").value,
      dateOfJoining: document.getElementById("edateOfJoining").value,
      role: "emp",
      deptName: document.getElementById("edeptName").value,
      jobTitle: document.getElementById("ejobTitle").value,
      password: document.getElementById("epassword").value,
    }),
  };
  console.log(settings);
  $.ajax(settings).done(function (response) {
    console.log(response);
  });
}
function getEmployeeIds(idpass) {
  var id = localStorage.getItem("emp_id");
  var settings = {
    url: "http://localhost:8080/employee/all/" + id,
    method: "GET",
    timeout: 0,
  };

  $.ajax(settings).done(function (response) {
    console.log(response);
    var selectElement = document.getElementById(idpass);
    for (var i = 0; i < response.length; i++) {
      selectElement.add(new Option(response[i].empID));
    }
  });
}
function searchemployee() {
  var id = document.getElementById("selectemployeeid").value;
  var settings = {
    url: "http://localhost:8080/employee/" + id,
    method: "GET",
    timeout: 0,
  };

  $.ajax(settings).done(function (response) {
    console.log(response);
    document.getElementById("GetDepartmentsTable").style.display = "block";
    let table = document.getElementById("GetDepartmentsTable");
    let row = table.insertRow(-1);
    let c1 = row.insertCell(0);
    c1.innerText = response.firstName;
    let c2 = row.insertCell(1);
    c2.innerText = response.lastName;
    let c3 = row.insertCell(2);
    c3.innerText = response.dateOfBirth;
    let c4 = row.insertCell(3);
    c4.innerText = response.gender;
    let c5 = row.insertCell(4);
    c5.innerText = response.email;
    let c6 = row.insertCell(5);
    c6.innerText = response.address;
    let c7 = row.insertCell(6);
    c7.innerText = response.dateOfJoining;
    let c8 = row.insertCell(7);
    c8.innerText = response.role;
  });
}
function terminateemployee() {
  var settings = {
    url: "http://localhost:8080/employee/terminate",
    method: "PUT",
    timeout: 0,
    headers: {
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      empId: document.getElementById("selectemployeeid").value,
      hrEmpID: localStorage.getItem("emp_id"),
      reason: document.getElementById("reason").value,
    }),
  };
  console.log(settings);
  $.ajax(settings).done(function (response) {
    console.log(response);

  });
}