<#assign base="http://192.168.0.145" />
<#--<#assign base="http://47.93.241.17" />-->
<#if Session["proUrl"]?exists>
    <#assign base = Session["proUrl"]>
<#elseif Application["proUrl"]?exists>
    <#assign base = Application["proUrl"]>
</#if>
<#assign base="http://192.168.0.145" />

<#if Session["userName"]?exists>
    <#assign userName = Session["userName"]>
</#if>

<#if Session["lastLoginIp"]?exists>
    <#assign lastLoginIp = Session["lastLoginIp"]>
</#if>

<#if Session["lastLoginTime"]?exists>
    <#assign lastLoginTime = Session["lastLoginTime"]>
</#if>
