<#assign base="http://localhost" />

<#if Session["userName"]?exists>
    <#assign userName = Session["userName"]>
</#if>

<#if Session["lastLoginIp"]?exists>
    <#assign lastLoginIp = Session["lastLoginIp"]>
</#if>

<#if Session["lastLoginTime"]?exists>
    <#assign lastLoginTime = Session["lastLoginTime"]>
</#if>
