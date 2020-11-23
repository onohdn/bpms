<div>

    <form:form action="${pageContext.request.contextPath}/account/update" method="post">

        <h3>アカウント情報を以下のように更新します。よろしければ"update"ボタンを押してください</h3>
        <table>
            <tr>
                <td><label for="name">name</label></td>
                <td id="name">${f:h(accountUpdateForm.name)}</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" name="redoAccountForm" id="back" value="back" />
                    <input type="submit" id="update" value="update" />
                </td>
            </tr>
        </table>
    </form:form>

    <form method="get" action="${pageContext.request.contextPath}/account/update">
        <input type="submit" id="home" name="home" value="home" />
    </form>
</div>