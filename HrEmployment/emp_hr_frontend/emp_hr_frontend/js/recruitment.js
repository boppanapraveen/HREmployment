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
function createrequirement() {
  var settings = {
    url: "http://localhost:8080/api/recruitment/",
    method: "POST",
    timeout: 0,
    headers: {
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      jobTitle: document.getElementById("jobTitle").value,
      applicationDate: document.getElementById("applicationDate").value,
      interviewDate: document.getElementById("interviewDate").value,
      candidateName: document.getElementById("candidateName").value,
      empID: 1,
    }),
  };
  console.log(settings);
  $.ajax(settings).done(function (response) {
    console.log(response);
  });
}
function getRecruitmentids(id) {
  var hrempID=  localStorage.getItem("emp_id");
  var settings = {
    url: "http://localhost:8080/api/recruitment/"+hrempID+"/all", //change
    method: "GET",
    timeout: 0,
  };

  $.ajax(settings).done(function (response) {
    console.log(response);
    var selectElement = document.getElementById(id);
    for (var i = 0; i < response.length; i++) {
      selectElement.add(new Option(response[i].recruitmentID));
    }
  });
}
function getRecruitmentValues() {
  var settings = {
    url:
      "http://localhost:8080/api/recruitment/" +
      document.getElementById("selectrequirementid1").value,
    method: "GET",
    timeout: 0,
  };

  $.ajax(settings).done(function (response) {
    console.log(response);
    document.getElementById("GetDepartmentsTable").style.display = "block";
    let table = document.getElementById("GetDepartmentsTable");
    let row = table.insertRow(-1);
    let c1 = row.insertCell(0);
    c1.innerText =  response.jobTitle;
    let c2 = row.insertCell(1);
    c2.innerText = response.applicationDate;
    let c3 = row.insertCell(2);
    c3.innerText = response.interviewDate;
    let c4 = row.insertCell(3);
    c4.innerText = response.candidateName;
  });
}
function updateRequirement() {
  // var id = document.getElementById("selectrequirementid2").value;
  var settings = {
    url: "http://localhost:8080/api/recruitment/1",
    method: "PUT",
    timeout: 0,
    headers: {
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      jobTitle: document.getElementById("ujobTitle").value,
      applicationDate: document.getElementById("uapplicationDate").value,
      interviewDate: document.getElementById("uinterviewDate").value,
      candidateName: document.getElementById("ucandidateName").value,
      empID: document.getElementById("selectrequirementid2").value
      // jobTitle: "Jr.Dev",
      // applicationDate: "7-oct-2023",
      // interviewDate: "12-ocr-2023",
      // candidateName: "updated John Doe",
      // empID: 1,
    }),
  };
  console.log(settings);
  $.ajax(settings).done(function (response) {
    console.log(response);
  });
}
function changeStatus() {
  var settings = {
    url: "http://localhost:8080/api/recruitment/",
    method: "PUT",
    timeout: 0,
    headers: {
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      status: document.getElementById("status").value,
      recruitmentID: document.getElementById("selectrequirementid3").value,
      empID: localStorage.getItem("emp_id")
    }),
  };

  $.ajax(settings).done(function (response) {
    console.log(response);
  });
}
