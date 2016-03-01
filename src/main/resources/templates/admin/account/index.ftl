<#include "../_layout.ftl">
 
<@layout ; section> 
  <#if section = "title"> 
    用户管理 - pickles
  <#elseif section = "specific-styles" > 

  <#elseif section = "inline-styles" > 

  <#elseif section = "page-content" > 
								<!-- PAGE CONTENT BEGINS add by zhangmz 2016-01-27-->
								
							        <div class="container">
							            <!-- left, vertical navbar & content -->
							            <div class="row">
							            <!-- content -->
							                <div class="col-md-12">
							                    <div class="row">
							                        <div class="col-lg-12">
							                            <div class="page-header">
							                                <h1>用户列表管理</h1>
							                            </div>
							                        </div>
							                    </div>
							
							                    <div class="row">
							                        <div class="text-center" style="padding: 10px 20px 20px;">						
													    <div class="row">
															<div class="offset6">
																<form class="form-search form-group" action="${base}/admin/accounts?TOKEN=${(TOKEN)!}" method="post">
																 	<label>手机号：</label> <input type="text" name="phone"   class="input-medium search-query"  value="${(queryParam.phone)!}"> 
																 	<label>Email：</label> <input type="text" name="email"   class="input-medium search-query"  value="${(queryParam.email)!}"> 
																 	<label>姓名：</label> <input type="text" name="name"   class="input-medium search-query"  value="${(queryParam.name)!}"> 
																    <button type="submit" class="btn" id="search_btn">查找</button>
															    </form>
														    </div>
														</div>
														
														<#if message??>
															<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
													    </#if>
								    
							                            <div class="panel panel-default">
							                                <div class="panel-heading text-left">
							                                    <div class="text-muted bootstrap-admin-box-title">查询结果 - [<a href="${base}/admin/accounts/add?TOKEN=${(TOKEN)!}">新增</a>]</div>
							                                </div>
							                                
															<#if pageInfo??>
													        <table id="contentTable" class="table bootstrap-admin-table-with-actions">
													            <thead>
																	<tr>
																		<th>ID</th>
																		<th>姓名</th>
																		<th>手机号</th>
																		<th>Email</th>
																		<th>注册日期</th>
																		<th>操作</th>
																	</tr>
																</thead>		
													            
													            <tbody>
													                <#list pageInfo.list as account>
													                <tr>
													                    <td>${account.id}</td>
													                    <td>${account.name}</td>
													                    <td>${account.phone}</td>
													                    <td>${account.email}</td>
													                    <td>${(account.registerDate?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
													                    <td class="visible-md visible-lg hidden-sm hidden-xs btn-group">
													                    	<a href="${base}/admin/accounts/view/${account.id}?TOKEN=${(TOKEN)!}">
								                                                <button class="btn btn-xs btn-info">
								                                                    <i class="icon-pencil"></i>
								                                                    	修改
								                                                </button>
								                                            </a>
								                                            <a href="${base}/admin/accounts/delete/${account.id}?TOKEN=${(TOKEN)!}">
								                                                <button class="btn btn-xs btn-danger">
								                                                    <i class="icon-trash"></i>
								                                                    	删除
								                                                </button>
								                                            </a>
													                    </td>
													                </tr>
													                </#list>
													            </tbody>
													        </table>
													    
															<table class="table table-striped table-bordered table-condensed">
													            <tr>
													                <#if pageInfo.hasPreviousPage>
													                    <td>
													                        <a href="${base}/admin/accounts?TOKEN=${(TOKEN)!}&page=1&rows=${pageInfo.pageSize}&name=${(queryParam.name)!}&email=${(queryParam.email)!}">首页</a>
													                    </td>
													                    <td>
													                        <a href="${base}/admin/accounts?TOKEN=${(TOKEN)!}&page=${pageInfo.prePage}&rows=${pageInfo.pageSize}&name=${(queryParam.name)!}&email=${(queryParam.email)!}">前一页</a>
													                    </td>
													                </#if>
													                <ul class="nav navbar-nav">
													                <#list pageInfo.navigatepageNums as nav>
													                    <#if nav == pageInfo.pageNum>
													                        <td class="active"><strong>${nav}</strong></td>
													                    </#if>
													                    <#if nav != pageInfo.pageNum>
													                        <td>
													                            <a href="${base}/admin/accounts?TOKEN=${(TOKEN)!}&page=${nav}&rows=${pageInfo.pageSize}&name=${(queryParam.name)!}&email=${(queryParam.email)!}">${nav}</a>
													                        </td>
													                    </#if>
													                </#list>
													                </ul>
													                <#if pageInfo.hasNextPage>
													                    <td>
													                        <a href="${base}/admin/accounts?TOKEN=${(TOKEN)!}&page=${pageInfo.nextPage}&rows=${pageInfo.pageSize}&name=${(queryParam.name)!}&email=${(queryParam.email)!}">下一页</a>
													                    </td>
													                    <td>
													                        <a href="${base}/admin/accounts?TOKEN=${(TOKEN)!}&page=${pageInfo.pages}&rows=${pageInfo.pageSize}&name=${(queryParam.name)!}&email=${(queryParam.email)!}">尾页</a>
													                    </td>
													                </#if>
													            </tr>
													        </table>					        
													    </#if>
													</div>
													</div>
							                    </div>
							                </div>
							            </div>
							        </div>

								<!-- PAGE CONTENT ENDS -->
  <#elseif section = "specific-scripts" > 

  <#elseif section = "inline-scripts" >

  <#else> 
    <div>Unsupported section??</div> 
  </#if> 
</@layout>
