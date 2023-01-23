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

var interval = 10000;
var timer1, timer2, timer3;

//Pause & Edit
$("#play").on("click", function () {
    $("#pause").prop("disabled", false);
    $("#play").prop("disabled", true);
    $("#rewind").prop("disabled", false);
    $("#button-open").prop("disabled", true);
    startSimulation();
    changeBalance();
    changeOperationTable();
    changeLandTable();
});

$("#pause").prop("disabled", true);

$("#pause").on("click", function () {
    $("#pause").prop("disabled", true);
    $("#play").prop("disabled", false);
    $("#rewind").prop("disabled", true);
    $("#button-open").prop("disabled", false);
    pauseSimulation();
    clearTimeout(timer1);
    clearTimeout(timer2);
    clearTimeout(timer3);
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
            timer1 = setTimeout(changeBalance, 60000);
        }
    });
}

//Change the operation table
function changeOperationTable() {
    $.ajax({
        url: "/changeOperationTable",
        type: "GET",
        success: function (data) {
            $('.operations-list').html(data);
        },
        error: function (e) {
            alert("An error ocurred trying to load the operations");
        },
        complete: function (data) {
            // Schedule the next
            timer2 = setTimeout(changeOperationTable, interval);
        }
    });
}

//Change the land table
function changeLandTable() {
    $.ajax({
        url: "/changeLandTable",
        type: "GET",
        success: function (data) {
            alert(data)
            $('.lands-list').html(data);
        },
        error: function (e) {
            alert("An error ocurred trying to load the lands");
        },
        complete: function (data) {
            // Schedule the next
            timer3 = setTimeout(changeLandTable, interval);
        }
    });
}