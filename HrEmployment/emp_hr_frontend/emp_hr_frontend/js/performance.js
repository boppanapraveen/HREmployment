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
            $("#performanceReviewForm1").submit(function (event) {
                event.preventDefault();

                var settings = {
                    "url": "http://localhost:8080/api/performance/",
                    "method": "POST",
                    "timeout": 0,
                    "headers": {
                        "Content-Type": "application/json"
                    },
                    "data": JSON.stringify({
                        "reviewDate": $("#reviewDate1").val(),
                        "feedback": $("#feedback1").val(),
                        "performanceRating": $("#performanceRating1").val(),
                        "empID": $("#empID1").val(),
                        "hrEmpID": $("#hrEmpID1").val()
                    }),
                };

                $.ajax(settings).done(function (response) {
                    console.log(response);
                    // Handle the response as needed
                });
            });
        });
        $(document).ready(function () {
            $("#performanceSkillsForm2").submit(function (event) {
                event.preventDefault();

                var settings = {
                    "url": "http://localhost:8080/api/performance/skills",
                    "method": "POST",
                    "timeout": 0,
                    "headers": {
                        "Content-Type": "application/json"
                    },
                    "data": JSON.stringify({
                        "skillName": $("#skillName2").val(),
                        "skillLevel": $("#skillLevel2").val(),
                        "empID": $("#empID2").val()
                    }),
                };

                $.ajax(settings).done(function (response) {
                    console.log(response);
                    // Handle the response as needed
                });
            });
        });
        $(document).ready(function () {
            $("#getPerformanceSkills").click(function () {
            var settings = {
                "url": "http://localhost:8080/api/performance/"+$("#empID").val(),
                "method": "GET",
                "timeout": 0,
            };

            $.ajax(settings).done(function (response) {
                console.log(response);

                // Clear existing data in the table body
                $("#performanceData").empty();

                // Populate the table with the response data
                $.each(response, function (index, review) {
                    $("#performanceData").append(`
                        <tr>
                            <td>${review.reviewID}</td>
                            <td>${review.reviewDate}</td>
                            <td>${review.feedback}</td>
                            <td>${review.performanceRating}</td>
                            <td>${review.employee.empID}</td>
                            <td>${review.employee.firstName} ${review.employee.lastName}</td>
                        </tr>
                    `);
                });
            });
        });
        });
        $(document).ready(function () {
            $("#getPerformanceSkills3").click(function () {
                var settings = {
                    "url": "http://localhost:8080/api/performance/skills/"+$("#empID3").val(),
                    "method": "GET",
                    "timeout": 0,
                };

                $.ajax(settings).done(function (response) {
                    console.log(response);

                    // Clear existing data in the table body
                    $("#performanceSkillsData3").empty();

                    // Iterate over the array and populate the table
                    $.each(response, function (index, skill) {
                        $("#performanceSkillsData3").append(`
                            <tr>
                                <td>${skill.skillID}</td>
                                <td>${skill.skillName}</td>
                                <td>${skill.skillLevel}</td>
                                <td>${skill.employee.empID}</td>
                                <td>${skill.employee.firstName} ${skill.employee.lastName}</td>
                            </tr>
                        `);
                    });
                });
            });
        });