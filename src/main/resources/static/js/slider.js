window.onload = function() {

    var sliderTrigger = document.getElementById("slider-trigger");
    var slider = document.getElementById("slider-content");

    sliderTrigger.addEventListener("click", function() {
        if(slider.classList.contains("active")){  
            slider.classList.remove("active"); 
        }else{  
            slider.classList.add("active"); 
        }
        alert("alo");
    });
}