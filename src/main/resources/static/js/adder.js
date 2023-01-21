window.onload = function() {
    alert ("okey");
    var el = document.getElementById("AddTractor");
    el.addEventListener("click", adField());

}

function adField() {
    alert ("okey");
    var el = document.getElementById("TractorForm");
    el.insertAdjacentHTML('afterend', '<label className="mb-3 col-8 d-flex justify-content-right">Tractor: <select className="col-6 bcream rounded-pill border border-dark" th:field="*{tractorName}" id="tractor"th:required="required"> <option className="bcream rounded-pill border border-dark" th:each="tractor : ${tractors}"th:value="${tractor.tractorName}" th:text="${tractor.tractorName}"></option> </select> <input className="d-flex justify-content-end col-1 bcream rounded-pill border border-dark" type="number"id="numTractor" th:field="*{numTractor}" min="0" th:required="required"/> </label>');

}
