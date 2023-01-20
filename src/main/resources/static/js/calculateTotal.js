
$("#tractor").change(function () {
    calculeTotal();
});

$("#plow").change(function () {
    calculeTotal();
});

$("#seeder").change(function () {
    calculeTotal();
});

$("#harvester").change(function () {
    calculeTotal();
});

$("#numTractor").change(function () {
    calculeTotal();
});

$("#numPlow").change(function () {
    calculeTotal();
});

$("#numSeeder").change(function () {
    calculeTotal();
});

$("#numHarvester").change(function () {
    calculeTotal();
});

function calculeTotal() {
    var tractorPrice = $("#tractor").find(":selected").attr("price");
    var tractorNum = $("#numTractor").val();
    var plowPrice = $("#plow").find(":selected").attr("price");
    var plowNum = $("#numPlow").val();
    var seederPrice = $("#seeder").find(":selected").attr("price");
    var seederNum = $("#numSeeder").val();
    var harvesterPrice = $("#harvester").find(":selected").attr("price");
    var harvesterNum = $("#numHarvester").val();
    var total = tractorPrice * tractorNum + plowPrice * plowNum + seederPrice * seederNum + harvesterPrice * harvesterNum;
    console.log(total);
    $("#totalCost").text(total);
}
