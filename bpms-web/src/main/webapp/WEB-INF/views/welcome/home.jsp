<sec:authentication property="principal.user" var="user" />

<div id="wrapper">
    <h1 id="title">Hello world!</h1>
    <p>The time on the server is ${serverTime}.</p>
    <p>Welcome ${f:h(user.name)} !!</p>
    <a href="./bpms/">打撃成績計算ページへ</a>
    
    <p>
    <form:form action="${pageContext.request.contextPath}/logout">
    	<button type="submit">Logout</button>
    </form:form>
    </p>
</div>
