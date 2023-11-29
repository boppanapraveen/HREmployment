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
      $(document).ready(function () {
          $("#payrollForm").submit(function (event) {
              event.preventDefault();
  
              var settings = {
                  "url": "http://localhost:8080/api/payroll/",
                  "method": "POST",
                  "timeout": 0,
                  "headers": {
                      "Content-Type": "application/json"
                  },
                  "data": JSON.stringify({
                      "deductionType": $("#deductionType").val(),
                      "rate": $("#rate").val(),
                      "effectiveDate": $("#effectiveDate").val(),
                      "employeeID": $("#employeeID").val(),
                      "hrEmployeeID": $("#hrEmployeeID").val(),
                      "month": $("#month").val()
                  }),
              };
              console.log(settings.data);
              $.ajax(settings).done(function (response) {
                  console.log(response);
                  // Handle the response as needed
              });
          });
      });
  
      $(document).ready(function () {
              $("#getPayrollDetails").click(function () {
                  var settings = {
                      "url": "http://localhost:8080/api/payroll/all/"+$("#hrEmployeeID1").val(),
                      "method": "GET",
                      "timeout": 0,
                  };
  
                  $.ajax(settings).done(function (response) {
                      console.log(response);
  
                      // Clear existing data in the table body
                      $("#payrollDetailsData").empty();
  
                      // Populate the table with the response data
                      $.each(response, function (index, payroll) {
                          $("#payrollDetailsData").append(`
                              <tr>
                                  <td>${payroll.deductionID}</td>
                                  <td>${payroll.deductionType}</td>
                                  <td>${payroll.rate}</td>
                                  <td>${payroll.effectiveDate}</td>
                                  <td>${payroll.salaryRecord.employee.empID}</td>
                                  <td>${payroll.month}</td>
                              </tr>
                          `);
                      });
                  });
              });
          });
          $(document).ready(function () {
      $("#getPayrollDetails2").click(function () {
          var settings = {
              "url": "http://localhost:8080/api/payroll/"+$("#employeeID2").val(),
              "method": "GET",
              "timeout": 0,
          };

          $.ajax(settings).done(function (response) {
              console.log(response);

              // Clear existing data in the table body
              $("#payrollDetailsData2").empty();

              // Populate the table with the response data
              $.each(response, function (index, payroll) {
                  $("#payrollDetailsData2").append(`
                      <tr>
                          <td>${payroll.deductionID}</td>
                          <td>${payroll.deductionType}</td>
                          <td>${payroll.rate}</td>
                          <td>${payroll.effectiveDate}</td>
                          <td>${payroll.salaryRecord.employee.empID}</td>
                          <td>${payroll.month}</td>
                      </tr>
                  `);
              });
          });
      });
  });
  $(document).ready(function () {
      $("#payrollForm3").submit(function (event) {
          event.preventDefault();

          var settings = {
              "url": "http://localhost:8080/api/payroll/"+$("#deductionID3").val(),
              "method": "PUT",
              "timeout": 0,
              "headers": {
                  "Content-Type": "application/json"
              },
              "data": JSON.stringify({
                  "deductionType": $("#deductionType3").val(),
                  "rate": $("#rate3").val(),
                  "effectiveDate": $("#effectiveDate3").val(),
                  "employeeID": $("#employeeID3").val(),
                  "hrEmployeeID": $("#hrEmployeeID3").val(),
                  "month": $("#month3").val()
              }),
          };

          $.ajax(settings).done(function (response) {
              console.log(response);
              // Handle the response as needed
          });
      });
  });