<#include "../_layout.ftl">
 
<@layout ; section> 
  <#if section = "title"> 
    用户信息 - pickles
  <#elseif section = "specific-styles" > 

  <#elseif section = "inline-styles" > 
  
  <#elseif section = "page-content" >
								<!-- PAGE CONTENT BEGINS add by zhangmz 2016-01-27-->
								
							        <div class="container">
							            <!-- left, vertical navbar & content -->
							            <div class="row">
							            <!-- content -->
							                <div class="col-md-10">
							                    <div class="row">
							                        <div class="col-lg-12">
							                            <div class="page-header">
							                                <h1>用户信息</h1>
							                            </div>
							                        </div>
							                    </div>
							
							                    <#if message??>
													<div id="message" class="alert alert-warning"><button data-dismiss="alert" class="close">×</button>${message}</div>
												</#if>
												
												<div class="row">
													<form id="register-form" role="form" action="${base}/admin/accounts/save?TOKEN=${(TOKEN)!}" method="post" class="form-horizontal">
												    	<input type="hidden" name="id" value="${(account.id)!}"/>
														<div class="form-group">
															<label class="col-sm-2 control-label" for="phone">手机号：</label>
															<div class="col-sm-10">
																<input class="form-control" id="phone" name="phone" value="${(account.phone)!}"/>
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label" for="email">E-Mail：</label>
															<div class="col-sm-10">
																<input class="form-control" id="email" name="email" value="${(account.email)!}"/>
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label" for="name">用户名：</label>
															<div class="col-sm-10">
																<input class="form-control" id="name" name="name" value="${(account.name)!}"/>
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label" for="password">密码：</label>
															<div class="col-sm-10">
																<input class="form-control" id="password" name="password" type="password" value=""/>
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label" for="confirm_password">确认密码：</label>
															<div class="col-sm-10">
																<input class="form-control" id="confirm_password" name="confirm_password" type="password" />
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label" for="groupId">用户组：</label>
															<div class="col-sm-10">
																<select class="form-control" id="groupId" name="groupId" value="${(account.groupId)!}">
																	<option value="-1">&nbsp;</option>
																</select>
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label" for="status">用户状态：</label>
															<div class="col-sm-10">
																<input class="form-control" id="status" name="status" value="${(account.status)!}"/>
															</div>
														</div>
														<div class="form-group">
															<label for="registerDate" class="col-sm-2 control-label">注册时间:</label>
															<div class="input-group date form_datetime col-sm-10" data-date-format="yyyy-MM-dd HH:mm:ss" data-link-field="registerDate">
																<input type="text" id="registerDate" name="registerDate" readonly="readonly" value="${(account.registerDate?string("yyyy-MM-dd HH:mm:ss"))!''}" class="form-control"/>
															</div>
														</div>
														<div class="control-group">
															<label for="registerDate" class="col-sm-2 control-label"></label>
															<p class="help-block class="col-sm-10"">TODO 表单检验，密码为空的处理。</p>
														</div>
														<div class="form-group">
															<div class="col-md-offset-2 col-md-10">
																<button type="submit" class="btn btn-primary btn-sm">保存</button>
																<button type="reset" class="btn btn-primary btn-sm">重置</button>
																<button type="button" class="btn btn-primary btn-sm" onclick="location='${base}/admin/accounts?TOKEN=${(TOKEN)!}' ">取消</button>
															</div>
														</div>
														<input type="hidden" name="groupId" value="${(account.groupId)!}"/>
													</form>
												</div>
							
							                </div>
							            </div>
							        </div>

								<!-- PAGE CONTENT ENDS -->
  <#elseif section = "specific-scripts" > 

  <#elseif section = "inline-scripts" >  
  
  <script type="text/javascript">
			
  			$.get("${base}/api/admin/groups/idNames?TOKEN=${(TOKEN)!}", function(data){
  				var idNames = eval(data);
				coverjsontoselect(idNames);    
			});
			 
			function coverjsontoselect(nodearray){ 	
				var tempselect=$("#groupId"); 
				tempselect.empty();  
				for(var i=0;i<nodearray.length;i++){ 
					// alert(nodearray[i].id+" " + nodearray[i].name)  
					tempselect.append("<option value='"+ nodearray[i].id +"'>"+ nodearray[i].name +"</option>"); 
				} 
				// 设置选中值
				tempselect.val(${(account.groupId)!});
			};
	        
  </script> 

  <#else> 
    <div>Unsupported section??</div> 
  </#if> 
</@layout>