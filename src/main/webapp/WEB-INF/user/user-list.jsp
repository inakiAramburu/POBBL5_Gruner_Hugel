<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/jsp/include/header.jsp" />

    <table>
        <caption>Users List</caption>
        <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Username</th>
                <th>Password</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
            </tr>
        </tbody>
    </table>
</body>

<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />