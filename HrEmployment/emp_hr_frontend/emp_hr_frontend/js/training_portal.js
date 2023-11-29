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
    $("#scriptForm").submit(function (event) {
        event.preventDefault();
        var settings = {
            "url": "http://localhost:8080/api/certification/", // Update the URL accordingly
            "method": "POST",
            "timeout": 0,
            "headers": {
                "Content-Type": "application/json"
            },
            "data": JSON.stringify({
                "trainingName": $("#trainingName").val(),
                "completionDate": $("#completionDate").val(),
                "expirationDate": $("#expirationDate").val(),
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
    $("#scriptForm1").submit(function (event) {
        event.preventDefault();
        var settings = {
            "url": "http://localhost:8080/api/certification/" + $("#trainingID1").val(), // Update the URL accordingly
            "method": "PUT",
            "timeout": 0,
            "headers": {
                "Content-Type": "application/json"
            },
            "data": JSON.stringify({
                "trainingName": $("#trainingName1").val(),
                "completionDate": $("#completionDate1").val(),
                "expirationDate": $("#expirationDate1").val(),
                "hrEmpID": $("#hrEmpID1").val()
            }),
        };
        $.ajax(settings).done(function (response) {
            console.log(response);
            // Handle the response as needed
        });
    });
});
// CORS
$(document).ready(function () {
    $("#scriptForm2").submit(function (event) {
        event.preventDefault();
        var settings = {
            "url": "http://localhost:8080/api/certification/map/" + $("#trainID2").val() + "?empID=" + $("#empID2").val() + "&hrEmpID=" + $("#hrEmpID2").val() + "",
            "method": "PUT",
            "timeout": 0,
        };

        $.ajax(settings).done(function (response) {
            console.log(response);
        });
    });
});

// CORS
$(document).ready(function () {
    var settings = {
        "url": "http://localhost:8080/api/certification/",
        "method": "GET",
        "timeout": 0,
    };

    $.ajax(settings).done(function (response) {
        console.log(response);
    });
    let resp = JSON.stringify([
        {
            "trainingID": 1,
            "trainingName": "Java",
            "completionDate": "17/11/2024",
            "expirationDate": "17/11/2025"
        },
        {
            "trainingID": 3,
            "trainingName": "laravel",
            "completionDate": "17/11/2023",
            "expirationDate": "17/11/2025"
        }
    ]);
    resp = JSON.parse(resp);
    for (let i = 0; i < resp.length; i++) {
        document.querySelector(".table-tb").innerHTML += `<tr>
         <td>`+ resp[i].trainingID + `</td>
     <td>`+ resp[i].trainingName + `</td>
     <td>`+ resp[i].completionDate + `</td>
     <td>`+ resp[i].expirationDate + `</td>
     </tr>`;
    }
});

// CORS
$(document).ready(function () {
    $("#scriptForm4").submit(function (event) {
        event.preventDefault();
        var settings = {
            "url": "http://localhost:8080/api/certification/map/" + $("#trainID4").val() + "&hrEmpID=" + $("#hrEmpID4").val() + "",
            "method": "DELETE",
            "timeout": 0,
        };

        $.ajax(settings).done(function (response) {
            console.log(response);
        });
    });
});