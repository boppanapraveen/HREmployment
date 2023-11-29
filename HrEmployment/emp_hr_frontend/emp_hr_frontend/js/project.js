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
    $("#projectForm").submit(function (event) {
        event.preventDefault();

        var settings = {
            "url": "http://localhost:8080/api/project/",
            "method": "POST",
            "timeout": 0,
            "headers": {
                "Content-Type": "application/json"
            },
            "data": JSON.stringify({
                "projectName": $("#projectName").val(),
                "description": $("#description").val(),
                "startDate": $("#startDate").val(),
                "endDate": $("#endDate").val(),
                "deptID": $("#deptID").val(),
                "empID": $("#empID").val()
            }),
        };

        $.ajax(settings).done(function (response) {
            console.log(response);
            // Handle the response as needed
        });
    });
});
$(document).ready(function () {
    $("#assignmentForm1").submit(function (event) {
        event.preventDefault();

        var settings = {
            "url": "http://localhost:8080/api/project/assign/",
            "method": "POST",
            "timeout": 0,
            "headers": {
                "Content-Type": "application/json"
            },
            "data": JSON.stringify({
                "projectID": $("#projectID1").val(),
                "description": $("#description1").val(),
                "startDate": $("#startDate1").val(),
                "endDate": $("#endDate1").val(),
                "hrEmpID": $("#hrEmpID1").val(),
                "empID": $("#empID1").val()
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
            "url": "http://localhost:8080/api/project/1",
            "method": "GET",
            "timeout": 0,
        };

        $.ajax(settings).done(function (response) {
            console.log(response);

            // Clear existing data in the table body
            $("#projectData").empty();

            // Iterate over the array and populate the table
            $.each(response, function (index, project) {
                $("#projectData").append(`
                    <tr>
                        <td>${project.projectID}</td>
                        <td>${project.projectName}</td>
                        <td>${project.description}</td>
                        <td>${project.startDate}</td>
                        <td>${project.endDate}</td>
                        <td>${project.department.deptID}</td>
                        <td>${project.department.departmentName}</td>
                        <td>${project.department.location}</td>
                        <td>${project.department.budget}</td>
                    </tr>
                `);
            });
        });
    });
    $(document).ready(function () {
        $("#getAssignedProjectDetails4").click(function () {
            var settings = {
                "url": "http://localhost:8080/api/project/assign/"+$("#empID4").val()+"?projectID="+$("#projectID4").val(),
                "method": "GET",
                "timeout": 0,
            };

            $.ajax(settings).done(function (response) {
                console.log(response);

                // Clear existing data in the table body
                $("#assignedProjectData4").empty();

                // Iterate over the array and populate the table
                $.each(response, function (index, assignment) {
                    $("#assignedProjectData4").append(`
                        <tr>
                            <td>${assignment.assignmentID}</td>
                            <td>${assignment.startDate}</td>
                            <td>${assignment.endDate}</td>
                            <td>${assignment.description}</td>
                            <td>${assignment.employee.empID}</td>
                            <td>${assignment.employee.firstName} ${assignment.employee.lastName}</td>
                            <td>${assignment.projectDetails.projectID}</td>
                            <td>${assignment.projectDetails.projectName}</td>
                            <td>${assignment.projectDetails.department.deptID}</td>
                            <td>${assignment.projectDetails.department.departmentName}</td>
                            <td>${assignment.projectDetails.department.location}</td>
                            <td>${assignment.projectDetails.department.budget}</td>
                        </tr>
                    `);
                });
            });
        });
    });