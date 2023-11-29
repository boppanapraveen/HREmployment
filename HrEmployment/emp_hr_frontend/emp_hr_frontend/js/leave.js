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
            $("#leaveForm1").submit(function (event) {
                event.preventDefault();

                var settings = {
                    "url": "http://localhost:8080/api/leave/",
                    "method": "POST",
                    "timeout": 0,
                    "headers": {
                        "Content-Type": "application/json"
                    },
                    "data": JSON.stringify({
                        "leaveType": $("#leaveType1").val(),
                        "startDate": $("#startDate1").val(),
                        "endDate": $("#endDate1").val(),
                        "employeeID": $("#employeeID1").val()
                    }),
                };

                $.ajax(settings).done(function (response) {
                    console.log(response);
                    // Handle the response as needed
                });
            });
        });
        $(document).ready(function () {
            $("#updateLeaveForm2").submit(function (event) {
                event.preventDefault();

                var settings = {
                    "url": "http://localhost:8080/api/leave/",
                    "method": "PUT",
                    "timeout": 0,
                    "headers": {
                        "Content-Type": "application/json"
                    },
                    "data": JSON.stringify({
                        "hrEmployeeID": $("#hrEmployeeID2").val(),
                        "status": $("#status2").val(),
                        "comments": $("#comments2").val(),
                        "leaveRequestID": $("#leaveRequestID2").val()
                    }),
                };

                $.ajax(settings).done(function (response) {
                    console.log(response);
                    // Handle the response as needed
                });
            });
        });
        $(document).ready(function () {
            $("#leaveForm3").submit(function (event) {
                event.preventDefault();

                var settings = {
                    "url": "http://localhost:8080/api/leave/"+$("#leaveID3").val(),
                    "method": "PUT",
                    "timeout": 0,
                    "headers": {
                        "Content-Type": "application/json"
                    },
                    "data": JSON.stringify({
                        "leaveType": $("#leaveType3").val(),
                        "startDate": $("#startDate3").val(),
                        "endDate": $("#endDate3").val(),
                        "employeeID": $("#employeeID3").val()
                    }),
                };

                $.ajax(settings).done(function (response) {
                    console.log(response);
                    // Handle the response as needed
                });
            });
        });
        $(document).ready(function () {
                var settings = {
                    "url": "http://localhost:8080/api/leave/all/1",
                    "method": "GET",
                    "timeout": 0,
                };

                $.ajax(settings).done(function (response) {
                    console.log(response);

                    // Clear existing data in the table body
                    $("#leaveRequestsData").empty();

                    // Populate the table with the response data
                    $.each(response, function (index, leaveRequest) {
                        $("#leaveRequestsData").append(`
                            <tr>
                                <td>${leaveRequest.leaveID}</td>
                                <td>${leaveRequest.leaveType}</td>
                                <td>${leaveRequest.startDate}</td>
                                <td>${leaveRequest.endDate}</td>
                                <td>${leaveRequest.employee.empID}</td>
                                <td>${leaveRequest.status}</td>
                                <td>${leaveRequest.comments}</td>
                            </tr>
                        `);
                    });
                });
        });
        $(document).ready(function () {
            $("#getLeaveDetails4").click(function () {
                var settings = {
                    "url": "http://localhost:8080/api/leave/"+$("#employeeID4").val(),
                    "method": "GET",
                    "timeout": 0,
                };

                $.ajax(settings).done(function (response) {
                    console.log(response);

                    // Clear existing data in the table body
                    $("#leaveRequestsData4").empty();

                    // Populate the table with the response data
                    $.each(response, function (index, leaveRequest) {
                        $("#leaveRequestsData4").append(`
                            <tr>
                                <td>${leaveRequest.leaveID}</td>
                                <td>${leaveRequest.leaveType}</td>
                                <td>${leaveRequest.startDate}</td>
                                <td>${leaveRequest.endDate}</td>
                                <td>${leaveRequest.employee.empID}</td>
                                <td>${leaveRequest.status}</td>
                                <td>${leaveRequest.comments}</td>
                            </tr>
                        `);
                    });
                });
        });
    });