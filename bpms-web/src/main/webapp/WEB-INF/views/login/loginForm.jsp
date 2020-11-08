    <div id="wrapper">
        <h3>Login with ID and Password</h3>

        <c:if test="${param.containsKey('error')}">
            <t:messagesPanel messagesType="error"
                messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION" />
        </c:if>

        <form:form action="${pageContext.request.contextPath}/login">
            <table>
                <tr>
                    <td><label for="id">ID:</label></td>
                    <td><input type="text" id="username"
                        name="username" value="demo">(demo)</td>
                </tr>
                <tr>
                    <td><label for="password">Password:</label></td>
                    <td><input type="password" id="password"
                        name="password" value="demo" />(demo)</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><input name="submit" type="submit" value="Login" /></td>
                </tr>
            </table>
        </form:form>
    </div>