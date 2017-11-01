<#assign base="http://localhost" />

<#if Session["userName"]?exists>
    <#assign userName = Session["userName"]>
</#if>
