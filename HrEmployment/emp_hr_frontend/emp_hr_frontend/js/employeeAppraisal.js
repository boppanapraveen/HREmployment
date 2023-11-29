function openCity(evt, cityName) {
  document.getElementById("Promotion").style.display = "none";
  document.getElementById("Appraisal").style.display = "none";
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
function addAppraisal() {
  var settings = {
    url: "http://localhost:8080/api/appraisals/",
    method: "POST",
    timeout: 0,
    headers: {
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      appraisalDate: document.getElementById("appraisalDate").value,
      rating: document.getElementById("rating").value,
      comments: document.getElementById("comments").value,
      empID: document.getElementById("selectemployeeid").value,
      hrEmpID: localStorage.getItem("emp_id")
    }),
  };

  $.ajax(settings).done(function (response) {
    console.log(response);
  });
}
function addpromotion() {
  var hrEmpID = localStorage.getItem("emp_id");
  var empID = document.getElementById("selectemployeeid2").value;
  var settings = {
    url: "http://localhost:8080/api/promotions/?empID=" + empID + "&hrEmpID="+hrEmpID,
    method: "POST",
    timeout: 0,
    headers: {
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      appraisalDate: document.getElementById("appraisalDate").value,
      rating: document.getElementById("rating").value,
      comments: document.getElementById("comments").value,
      empID: document.getElementById("selectemployeeid2").value,
      hrEmpID: localStorage.getItem("emp_id")
    }),
  };

  $.ajax(settings).done(function (response) {
    console.log(response);
  });
}
function getPromotionValue() {
  var empID = document.getElementById("selectemployeeid3").value;
  var settings = {
    url: "http://localhost:8080/api/promotions/" + empID,
    method: "GET",
    timeout: 0,
  };

  $.ajax(settings).done(function (response) {
    console.log(response);
    document.getElementById("GetDepartmentsTable").style.display = "block";
    let table = document.getElementById("Promotion");
    let row = table.insertRow(-1);
    let c1 = row.insertCell(0);
    c1.innerText = response[0].promotionID;
    let c2 = row.insertCell(1);
    c2.innerText = response[0].promotionDate;
  });
}
function getAppraisalValue() {
  var empID = document.getElementById("selectemployeeid4").value;
  var settings = {
    url: "http://localhost:8080/api/appraisals/" + empID,
    method: "GET",
    timeout: 0,
  };

  $.ajax(settings).done(function (response) {
    console.log(response);
    document.getElementById("Appraisal").style.display = "block";
    let table = document.getElementById("Appraisal");
    let row = table.insertRow(-1);
    let c1 = row.insertCell(0);
    c1.innerText = response[0].appraisalDate;
    let c2 = row.insertCell(1);
    c2.innerText = response[0].comments;
    let c3 = row.insertCell(2);
    c3.innerText = response[0].rating;
  });
}
