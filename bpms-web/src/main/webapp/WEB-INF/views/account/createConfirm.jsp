<div>

    <form:form action="${pageContext.request.contextPath}/account/create" method="post">

        <h3>以下の情報で、アカウントを作成します。よろしければ"create"を押してください</h3>
        <table>
            <tr>
                <td><label for="id">id</label></td>
                <td id="id">${f:h(accountCreateForm.id)}</td>
            </tr>
            <tr>
                <td><label for="name">name</label></td>
                <td id="name">${f:h(accountCreateForm.name)}</td>
            </tr>
            <tr>
                <td><label for="password">password</label></td>
                <td id="password">****</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" name="redoForm" id="back" value="back" />
                    <input type="submit" id="create" value="create" />
                </td>
            </tr>
        </table>
    </form:form>

    <form method="get" action="${pageContext.request.contextPath}/account/create">
        <input type="submit" id="home" name="home" value="Login page" />
    </form>
</div>