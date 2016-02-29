<#include "_layout.ftl">
 
<@layout ; section> 
  <#if section = "title"> 
  	空白页2 - pickles
  <#elseif section = "page-content" > 
	<div></div> 
  <#elseif section = "specific-scripts" > 
    <div></div> 
  <#elseif section = "inline-scripts" >
    <div></div> 
  <#else> 
    <div>Unsupported section??</div> 
  </#if> 
</@layout>