<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/jsp/include/header.jsp" />

    <form action="/user/list" method="post">
        <div><input name="firstName" placeholder="First Name"></div>
        <div><input name="lastName" placeholder="Last Name"></div>
        <div><input name="email" placeholder="Email"></div>
        <div><input name="username" placeholder="Username"></div>
        <div><input name="password" type="password" placeholder="Password"></div>
        <div><input type="Submit"></div>
    </form>
</body>

<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />