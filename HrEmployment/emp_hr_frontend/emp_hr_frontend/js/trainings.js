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
    $("#trainingForm").submit(function (event) {
        event.preventDefault();

        var settings = {
            "url": "http://localhost:8080/api/training/",
            "method": "POST",
            "timeout": 0,
            "headers": {
                "Content-Type": "application/json"
            },
            "data": JSON.stringify({
                "trainingName": $("#trainingName").val(),
                "trainingDate": $("#trainingDate").val(),
                "empID": $("#empID").val(),
                "hrEmpID": $("#hrEmpID").val()
            }),
        };

        $.ajax(settings).done(function (response) {
            console.log(response);
            // Handle the response as needed
        });
    });
});
$(document).ready(function () {
    $("#getTrainingHistory").click(function () {
        var settings = {
            "url": "http://localhost:8080/api/training/"+$("#empID1").val(),
            "method": "GET",
            "timeout": 0,
        };

        $.ajax(settings).done(function (response) {
            console.log(response);

            // Clear existing data in the table body
            $("#trainingHistoryData").empty();

            // Populate the table with the response data
            $.each(response, function (index, trainingHistory) {
                $("#trainingHistoryData").append(`
                    <tr>
                        <td>${trainingHistory.employeeTraingHistoryID}</td>
                        <td>${trainingHistory.trainingName}</td>
                        <td>${trainingHistory.trainingDate}</td>
                        <td>${trainingHistory.employee.empID}</td>
                        <td>${trainingHistory.employee.firstName} ${trainingHistory.employee.lastName}</td>
                        <td>${trainingHistory.employee.trainingCertification.completionDate}</td>
                        <td>${trainingHistory.employee.trainingCertification.expirationDate}</td>
                    </tr>
                `);
            });
        });
    });
});
$(document).ready(function () {
    $("#deleteTrainingForm2").submit(function (event) {
        event.preventDefault();

        var empID = $("#empID2").val();

        var settings = {
            "url": "http://localhost:8080/api/training/"+$("#trainID2").val()+"?empID=" + empID,
            "method": "DELETE",
            "timeout": 0,
        };

        $.ajax(settings).done(function (response) {
            console.log(response);
            // Handle the response as needed
        });
    });
});