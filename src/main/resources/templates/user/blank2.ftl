<#include "_layout.ftl">
 
<@layout ; section> 
  <#if section = "title"> 
  	user空白页2 - pickles
  <#elseif section = "specific-styles" > 

  <#elseif section = "inline-styles" > 

  <#elseif section = "page-content" > 

  <#elseif section = "specific-scripts" > 

  <#elseif section = "inline-scripts" >

  <#else> 
    <div>Unsupported section??</div> 
  </#if> 
</@layout>