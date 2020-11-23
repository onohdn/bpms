<div>

    <form:form action="${pageContext.request.contextPath}/account/update" method="post" modelAttribute="accountUpdateForm">

        <h2>更新したいアカウント情報を入力してください。（名前しか更新項目無いけどね）</h2>
        <table>
            <tr>
                <td><form:label path="name" cssErrorClass="error-label">name</form:label></td>
                <td><form:input path="name" cssErrorClass="error-input" />
                    <form:errors path="name" cssClass="error-messages" />
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" name="confirm" id="confirm" value="confirm" /></td>
            </tr>
        </table>
    </form:form>

    <form method="get" action="${pageContext.request.contextPath}/account/update">
        <input type="submit" name="home" id="home" value="home" />
    </form>
</div>
