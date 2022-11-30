<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/include/header.jsp" />

    <main>

        <section class="card">
            <h2 class="card-title">Login user</h2>
            <form class="card-body" action="/login" method="post">
                <label>
                    Username: 
                    <input	type="text"
                        name="username"
                        required
                        placeholder="Username"/>
                </label>
                <label>
                    Password:
                    <input	type="password"
                        name="password"
                        required
                        placeholder="Password"/>
                </label>
                <button id="login_button" type="submit" id="login">Login</button>
            </form>
        </section>
    </main>
    
</body>

<jsp:include page="/WEB-INF/include/footer.jsp" />