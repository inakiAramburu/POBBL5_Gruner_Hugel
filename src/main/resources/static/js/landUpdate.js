function updateCoordinates() {
    $.get("maper").done(function (fragment) {
        $("#lat").replaceWith(fragment);
        //$("#lng").replaceWith(fragment);
    });
}