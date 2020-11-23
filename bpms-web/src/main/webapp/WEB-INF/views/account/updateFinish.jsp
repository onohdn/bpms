<div>

    <h3>アカウント情報を更新しました</h3>
    <table>
        <tr>
            <td><label for="id">id</label></td>
            <td id="id">${f:h(accountUpdateForm.id)}</td>
        </tr>
        <tr>
            <td><label for="name">name</label></td>
            <td id="name">${f:h(accountUpdateForm.name)}</td>
        </tr>
    </table>

    <button onclick="location.href='${pageContext.request.contextPath}/'">BPMSのホームへ</button>
    
</div>
