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
    $("#salaryForm").submit(function (event) {
        event.preventDefault();

        var settings = {
            "url": "http://localhost:8080/api/salary/",
            "method": "POST",
            "timeout": 0,
            "headers": {
                "Content-Type": "application/json"
            },
            "data": JSON.stringify({
                "salary": $("#salary").val(),
                "startDate": $("#startDate").val(),
                "month": $("#month").val(),
                "endDate": $("#endDate").val(),
                "employeeID": $("#employeeID").val(),
                "hrEmployeeID": $("#hrEmployeeID").val()
            }),
        };

        $.ajax(settings).done(function (response) {
            console.log(response);
            // Handle the response as needed
        });
    });
});
$(document).ready(function () {
    $("#getSalaryDetails").click(function () {
        var settings = {
            "url": "http://localhost:8080/api/salary/"+$("#employeeID1").val(),
            "method": "GET",
            "timeout": 0,
        };

        $.ajax(settings).done(function (response) {
            console.log(response);

            // Clear existing data in the table body
            $("#salaryDetailsData").empty();

            // Populate the table with the response data
            $.each(response, function (index, salary) {
                $("#salaryDetailsData").append(`
                    <tr>
                        <td>${salary.salID}</td>
                        <td>${salary.salary}</td>
                        <td>${salary.startDate}</td>
                        <td>${salary.month}</td>
                        <td>${salary.endDate}</td>
                        <td>${salary.employee.empID}</td>
                        <td>${salary.employee.firstName} ${salary.employee.lastName}</td>
                    </tr>
                `);
            });
        });
    });
});