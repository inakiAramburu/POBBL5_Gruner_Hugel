window.onload = function() {

    var form = document.getElementById("form");

    var buttonCreate = document.getElementById("create").onclick = function(){
        form.action = "@{/create}";
    }
    var buttonUpdate = document.getElementById("update").onclick = function(){
        form.action = "@{/update}";
    }
}