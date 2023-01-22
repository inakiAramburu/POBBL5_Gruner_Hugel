window.onload = function () {

    var sliderTrigger = document.getElementById("slider-trigger");
    var slider = document.getElementById("slider-content");

    sliderTrigger.addEventListener("click", function () {
        if (slider.classList.contains("active")) {
            slider.classList.remove("active");
        } else {
            slider.classList.add("active");
        }
        alert("alo");
    });
}

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