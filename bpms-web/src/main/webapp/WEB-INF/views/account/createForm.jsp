<div>

    <form:form action="${pageContext.request.contextPath}/account/create" method="post" modelAttribute="accountCreateForm">

        <h2>新規作成するアカウント情報を入力してください</h2>
        <table>
            <tr>
                <td><form:label path="id" cssErrorClass="error-label">id</form:label></td>
                <td><form:input path="id" cssErrorClass="error-input" />
                    <form:errors path="id" cssClass="error-messages" />
                </td>
            </tr>
            <tr>
                <td><form:label path="name" cssErrorClass="error-label">name</form:label></td>
                <td><form:input path="name" cssErrorClass="error-input" />
                    <form:errors path="name" cssClass="error-messages" />
                </td>
            </tr>
            <tr>
                <td><form:label path="password" cssErrorClass="error-label">password</form:label></td>
                <td><form:password path="password" cssErrorClass="error-input" />
                    <form:errors path="password" cssClass="error-messages" />
                </td>
            </tr>
            <tr>
                <td><form:label path="confirmPassword" cssErrorClass="error-label">password (confirm)</form:label></td>
                <td><form:password path="confirmPassword" cssErrorClass="error-input" />
                    <form:errors path="confirmPassword" cssClass="error-messages" />
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" name="confirm" id="confirm" value="confirm" /></td>
            </tr>
        </table>
    </form:form>

    <form method="get" action="${pageContext.request.contextPath}/account/create">
        <input type="submit" name="home" id="home" value="Login page" />
    </form>
</div>
