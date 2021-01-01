<div id="wrapper">
    <h1 id="title">打撃成績計算結果</h1>
    <p>あなたの各種打撃指標は以下のとおりです。</p>
    <spring:message code="batting_average" />：${f:h(calculatedBattingresults.batting_average)}<br>
    <spring:message code="on_base_percentage" />：${f:h(calculatedBattingresults.on_base_percentage)}<br>
    <spring:message code="slugging_percentage" />：${f:h(calculatedBattingresults.slugging_percentage)}<br>
    　 <spring:message code="on_base_plus_slugging" />：${f:h(calculatedBattingresults.on_base_plus_slugging)}<br>
</div>
