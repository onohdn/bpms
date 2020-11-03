<div id="wrapper">
    <h1 id="title">打撃成績計算</h1>
    <p>以下に打撃成績を入力し、「計算」ボタンをクリックしてください。</p>
    <form:form action="${pageContext.request.contextPath}/bpms/create"
    			method="post" modelAttribute="bpmsForm">
    			打席　：<form:input path="plate_appearance"/><br>
    			単打　：<form:input path="hit"/><br>
    			二塁打：<form:input path="two_base_hit"/><br>
    			三塁打：<form:input path="three_base_hit"/><br>
    			本塁打：<form:input path="home_run"/><br>
    			四球　：<form:input path="walks"/><br>
    			死球　：<form:input path="hit_by_pitch"/><br>
    			犠打　：<form:input path="sacrifice_bunt"/><br>
    			犠飛　：<form:input path="sacrifice_fly"/><br>
    			失策　：<form:input path="faux_pas"/><br>
    			<form:button>計算</form:button>
    </form:form>
</div>
