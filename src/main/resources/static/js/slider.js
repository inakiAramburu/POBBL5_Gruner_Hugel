function startSimulation() {
    $.ajax({
        url: "/startSimulation",
        type: "GET",
        cache: false,
        timeout: 1000,
        dataType: "text",
        success: function (data) {
            console.log("Simulation started");
        },
        error: function (e) {
            alert("An error ocurred trying to start Simulation");
        }
    });
}

function pauseSimulation() {
    $.ajax({
        url: "/pauseSimulation",
        type: "GET",
        cache: false,
        timeout: 1000,
        dataType: "text",
        success: function (data) {
            console.log("Simulation paused");
        },
        error: function (e) {
            alert("An error ocurred trying to start Simulation");
        }
    });
}

var interval = 600000;
var timer;

//Pause & Edit
$("#play").on("click", function () {
    $("#pause").prop("disabled", false);
    $("#play").prop("disabled", true);
    $("#rewind").prop("disabled", false);
    $("#button-open").prop("disabled", true);
    startSimulation();
    changeBalance();
    changeTables();
});

$("#pause").prop("disabled", true);

$("#pause").on("click", function () {
    $("#pause").prop("disabled", true);
    $("#play").prop("disabled", false);
    $("#rewind").prop("disabled", true);
    $("#button-open").prop("disabled", false);
    pauseSimulation();
    clearTimeout(timer);
    clearTimeout(timer2);
});

$("#rewind").on("click", function () {
    changeSpeed();
});

//Change speed
function changeSpeed() {
    var speed = $('#speed').html();
    alert(speed);
    $.ajax({
        url: "/changeAcceleration",
        type: "GET",
        cache: false,
        timeout: 1000,
        data: { value: speed },
        dataType: "text",
        success: function (data) {
            $('#speed').text(data);
        },
        error: function (e) {
            alert("An error ocurred trying to load the lands");
        }
    });
}

//Change balance
function changeBalance() {
    $.ajax({
        url: "/changeBalance",
        type: "GET",
        cache: false,
        timeout: 1000,
        dataType: "text",
        success: function (data) {
            $('#balance').text(data);
        },
        error: function (e) {
            alert("An error ocurred trying to change de balance");
        },
        complete: function (data) {
            // Schedule the next
            timer = setTimeout(changeBalance, interval);
        }
    });
}

//Change the operation table
function changeTables() {
    $.get("changeLandTable").done(function (fragment) {
        $("#lands-list").replaceWith(fragment);
    });

    $.get("changeOperationTable").done(function (fragment) {
        $("#operations-list").replaceWith(fragment);
    });

    timer2 = setTimeout(changeTables, 5000);
}