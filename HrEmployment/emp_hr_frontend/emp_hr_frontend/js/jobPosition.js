function openCity(evt, cityName) {
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
function addPosition() {
  var settings = {
    url: "http://localhost:8080/api/jobposition/",
    method: "POST",
    timeout: 0,
    headers: {
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      jobTitle: document.getElementById("jobTitle").value,
      description: document.getElementById("description").value,
      salaryRange: document.getElementById("salaryRange").value,
      empID: localStorage.getItem("emp_id"),
    }),
  };

  $.ajax(settings).done(function (response) {
    console.log(response);
  });
}
function getPositionids(id) {
  var settings = {
    url: "http://localhost:8080/api/jobposition/",
    method: "GET",
    timeout: 0,
  };

  $.ajax(settings).done(function (response) {
    var selectElement = document.getElementById(id);
    for (var i = 0; i < response.length; i++) {
      selectElement.add(new Option(response[i].jobPositionID));
    }
  });
}
function deletePosition() {
  var postionid = document.getElementById("selectpositionid1").value;
  var empID = localStorage.getItem("emp_id");
  var settings = {
    url: "http://localhost:8080/api/jobposition/" + empID + "?jobId=" + postionid,
    method: "DELETE",
    timeout: 0,
    headers: {
      "Content-Type": "application/json",
    },
  };
  console.log(settings);
  $.ajax(settings).done(function (response) {
    console.log(response);
  });
}
function updatePosition() {
  var postionid = document.getElementById("selectpositionid2").value;
  var settings = {
    url: "http://localhost:8080/api/jobposition/update/" + postionid,
    method: "PUT",
    timeout: 0,
    headers: {
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      jobTitle: document.getElementById("ujobTitle").value,
      description: document.getElementById("udescription").value,
      salaryRange: document.getElementById("usalaryRange").value,
      empID: localStorage.getItem("emp_id")
    }),
  };
  console.log(settings);
  $.ajax(settings).done(function (response) {
    console.log(response);
  });
}
function getAllPositions() {
  var settings = {
    url: "http://localhost:8080/api/jobposition/",
    method: "GET",
    timeout: 0,
  };

  $.ajax(settings).done(function (response) {
    console.log(response);
    let table = document.getElementById("GetAllPositionsTable");
    for(var i=0;i<response.length;i++){
      let row = table.insertRow(-1);
      let c1 = row.insertCell(0);
      c1.innerText = response[i].jobPositionID;
      let c2 = row.insertCell(1);
      c2.innerText = response[i].jobTitle;
      let c3 = row.insertCell(2);
      c3.innerText = response[i].description;
      let c4 = row.insertCell(3);
      c4.innerText = response[i].salaryRange;
    }
  });
}
