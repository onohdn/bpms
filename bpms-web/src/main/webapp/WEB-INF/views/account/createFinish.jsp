<div>

    <h3>アカウントが作成されました</h3>
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
    </table>

    <button onclick="location.href='${pageContext.request.contextPath}/login/loginForm'">ログインページへ</button>

</div>
