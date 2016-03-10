<#include "../_layout.ftl">
 
<@layout ; section> 
  <#if section = "title"> 
  用户组管理 - pickles
  <#elseif section = "specific-styles" > 
		<link rel="stylesheet" href="${base}/static/assets/css/jquery-ui-1.10.3.full.min.css" />
		<link rel="stylesheet" href="${base}/static/assets/css/datepicker.css" />
		<link rel="stylesheet" href="${base}/static/assets/css/ui.jqgrid.css" />
		
  <#elseif section = "inline-styles" > 

  <#elseif section = "page-content" > 
								<!-- PAGE CONTENT BEGINS -->

								<div class="alert alert-info">
									<i class="icon-hand-right"></i>
										注意：用户组不能删除，可以设置为无效。
									<button class="close" data-dismiss="alert">
										<i class="icon-remove"></i>
									</button>
								</div>
								
    							<div id="dialogMessage" title="消息" style="display: none;"></div>

								<table id="grid-table"></table>

								<div id="grid-pager"></div>

								<script type="text/javascript">
									var $path_base = "/api/admin/groups";
								</script>

								<!-- PAGE CONTENT ENDS -->
								
  <#elseif section = "specific-scripts" > 
		<script src="${base}/static/assets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="${base}/static/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
		<script src="${base}/static/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
		<script src="${base}/static/assets/js/MyJqueryMethod.js"></script>

  <#elseif section = "inline-scripts" >
		<script type="text/javascript">
			// var grid_data = ${(groups)!};
			// var grid_data = eval('(' + ${(groups)!} + ')');
			
			jQuery(function($) {
				var grid_selector = "#grid-table";
				var pager_selector = "#grid-pager";
			
				jQuery(grid_selector).jqGrid({
					// data: grid_data,
					url: $path_base+"?TOKEN=${(TOKEN)!}",
					datatype: "json",
					height: 300,
					colNames:['ID','用户组编码', '用户组名','管理员','手机号码','注册日期','是否有效','备注'],
					colModel:[						
						{name:'id',index:'id', width:50, 
								editable: true,editoptions:{readonly:true}},
						{name:'code',index:'code', width:50,
								editable: true,editoptions:{size:"16",maxlength:"16"},
								editrules: { edithidden: true, required: true}},
						{name:'groupName',index:'groupName', width:50,
								editable: true, editrules: { edithidden: true, required: true}},
						{name:'adminName',index:'adminName', width:50,
								editable: true, editrules: { edithidden: true, required: true}},
						{name:'phone',index:'phone', width:50,
								editable: true, editrules: { edithidden: true, required: true, custom: true, custom_func: formCheck }},
						{name:'registerDate',index:'registerDate', width:50,
								formatter:'date',formatoptions:{srcformat: 'Y-m-d H:i:s', newformat: 'Y-m-d H:i:s'},
								editable: false, editoptions:{readonly:true}},
						{name:'status',index:'status', width:50,
								editable: true,edittype:"checkbox",editoptions: {value:"Yes:No",defaultValue:"No"},unformat: aceSwitch},
						{name:'note',index:'note', width:150, 
								sortable:false,editable: true,edittype:"textarea", editoptions:{rows:"2",cols:"20"}} 
					], 
			
					viewrecords : true,
					rowNum:10,
					rowList:[10,20,30],
					pager : pager_selector,
					altRows: true,
					//toppager: true,
					
					multiselect: true,
					//multikey: "ctrlKey",
			        multiboxonly: true,
			
					loadComplete : function() {
						var table = this;
						setTimeout(function(){
							styleCheckbox(table);
							
							updateActionIcons(table);
							updatePagerIcons(table);
							enableTooltips(table);
						}, 0);
					},
			
					editurl: $path_base+"/save?TOKEN=${(TOKEN)!}",
					caption: "用户组管理",			
					autowidth: true
			
				});
			
				//enable search/filter toolbar
				//jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})
			
				//switch element when editing inline
				function aceSwitch( cellvalue, options, cell ) {
					setTimeout(function(){
						$(cell) .find('input[type=checkbox]')
								.wrap('<label class="inline" />')
							.addClass('ace ace-switch ace-switch-5')
							.after('<span class="lbl"></span>');
					}, 0);
				}
				//enable datepicker
				function pickDate( cellvalue, options, cell ) {
					setTimeout(function(){
						$(cell) .find('input[type=text]')
								.datepicker({format:'yyyy-mm-dd' , autoclose:true}); 
					}, 0);
				}
			
			
				//navButtons
				jQuery(grid_selector).jqGrid('navGrid',pager_selector,
					{ 	//navbar options
						edit: true,
						editicon : 'icon-pencil blue',
						add: true,
						addicon : 'icon-plus-sign purple',
						del: false,  // 用户组不能删除
						delicon : 'icon-trash red',
						search: false, //这个查询过于复杂
						searchicon : 'icon-search orange',
						refresh: true,
						refreshicon : 'icon-refresh green',
						view: true,
						viewicon : 'icon-zoom-in grey',
					},
					{
						//edit record form
						closeAfterEdit: true,
						recreateForm: true,
						beforeShowForm : function(e) {
							var form = $(e[0]);
							form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
							style_edit_form(form);
						},
						afterSubmit: function(response) {
						    var result = eval('(' + response.responseText + ')');
						    if (result.code == 1) {
						        alert(result.message);
						        // TODO 刷新页面数据 --修改页面数据即可
						    } else {
						        alert(result.message);
						    }
						    return [result.code, result.message, result.groupId] 
						}
					},
					{
						//new record form
						closeAfterAdd: true,
						recreateForm: true,
						viewPagerButtons: false,
						beforeShowForm : function(e) {
							var form = $(e[0]);
							form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
							style_edit_form(form);
						},
						afterSubmit: function(response) {
						    var result = eval('(' + response.responseText + ')');
						    if (result.code == 1) {
						        alert(result.message);
						        // 刷新页面数据--只是用于演示，应该使用异步数据刷新
						        location.reload();
						    } else {
						        alert(result.message);
						    }
						    return [result.code, result.message, result.groupId] 
						}
					},
					{
						//delete record form
						recreateForm: true,
						beforeShowForm : function(e) {
							var form = $(e[0]);
							if(form.data('styled')) return false;
							
							form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
							style_delete_form(form);
							
							form.data('styled', true);
						},
						onClick : function(e) {
							alert(1);
						}
					},
					{
						//search form
						recreateForm: true,
						afterShowSearch: function(e){
							var form = $(e[0]);
							form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
							style_search_form(form);
						},
						afterRedraw: function(){
							style_search_filters($(this));
						}
						,
						multipleSearch: true,
						/**
						multipleGroup:true,
						showQuery: true
						*/
					},
					{
						//view record form
						recreateForm: true,
						beforeShowForm: function(e){
							var form = $(e[0]);
							form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
						}
					}
				)
			
			
				
				function style_edit_form(form) {
					//enable datepicker on "sdate" field and switches for "status" field
					form.find('input[name=sdate]').datepicker({format:'yyyy-mm-dd' , autoclose:true})
					    .end().find('input[name=status]')
							  .addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');
			
					//update buttons classes
					var buttons = form.next().find('.EditButton .fm-button');
					buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
					buttons.eq(0).addClass('btn-primary').prepend('<i class="icon-ok"></i>');
					buttons.eq(1).prepend('<i class="icon-remove"></i>')
					
					buttons = form.next().find('.navButton a');
					buttons.find('.ui-icon').remove();
					buttons.eq(0).append('<i class="icon-chevron-left"></i>');
					buttons.eq(1).append('<i class="icon-chevron-right"></i>');		
				}
			
				function style_delete_form(form) {
					var buttons = form.next().find('.EditButton .fm-button');
					buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
					buttons.eq(0).addClass('btn-danger').prepend('<i class="icon-trash"></i>');
					buttons.eq(1).prepend('<i class="icon-remove"></i>')
				}
				
				function style_search_filters(form) {
					form.find('.delete-rule').val('X');
					form.find('.add-rule').addClass('btn btn-xs btn-primary');
					form.find('.add-group').addClass('btn btn-xs btn-success');
					form.find('.delete-group').addClass('btn btn-xs btn-danger');
				}
				function style_search_form(form) {
					var dialog = form.closest('.ui-jqdialog');
					var buttons = dialog.find('.EditTable')
					buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'icon-retweet');
					buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'icon-comment-alt');
					buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'icon-search');
				}
				
				function beforeDeleteCallback(e) {
					var form = $(e[0]);
					if(form.data('styled')) return false;
					
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
					style_delete_form(form);
					
					form.data('styled', true);
				}
				
				function beforeEditCallback(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
					style_edit_form(form);
				}
			
			
			
				//it causes some flicker when reloading or navigating grid
				//it may be possible to have some custom formatter to do this as the grid is being created to prevent this
				//or go back to default browser checkbox styles for the grid
				function styleCheckbox(table) {
				/**
					$(table).find('input:checkbox').addClass('ace')
					.wrap('<label />')
					.after('<span class="lbl align-top" />')
			
			
					$('.ui-jqgrid-labels th[id*="_cb"]:first-child')
					.find('input.cbox[type=checkbox]').addClass('ace')
					.wrap('<label />').after('<span class="lbl align-top" />');
				*/
				}
				
			
				//unlike navButtons icons, action icons in rows seem to be hard-coded
				//you can change them like this in here if you want
				function updateActionIcons(table) {
					/**
					var replacement = 
					{
						'ui-icon-pencil' : 'icon-pencil blue',
						'ui-icon-trash' : 'icon-trash red',
						'ui-icon-disk' : 'icon-ok green',
						'ui-icon-cancel' : 'icon-remove red'
					};
					$(table).find('.ui-pg-div span.ui-icon').each(function(){
						var icon = $(this);
						var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
						if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
					})
					*/
				}
				
				//replace icons with FontAwesome icons like above
				function updatePagerIcons(table) {
					var replacement = 
					{
						'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
						'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
						'ui-icon-seek-next' : 'icon-angle-right bigger-140',
						'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
					};
					$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
						var icon = $(this);
						var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
						
						if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
					})
				}
			
				function enableTooltips(table) {
					$('.navtable .ui-pg-button').tooltip({container:'body'});
					$(table).find('.ui-pg-div').tooltip({container:'body'});
				}
			
				//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
			
			
			});
		</script>		
		
		<script type="text/javascript">
        //弹出form验证  'ID','用户组编码', '用户组名','管理员','手机号码','注册日期','是否有效','备注'
        function formCheck(value, colname) {
            var result = [true, ""];
            switch (colname) {
                case "用户组编码":
                    if (flag != 0) {
                        checkCode(value);
                        if (flag == 0) {
                            result = [false, colname + "不可重复！"];
                        }
                    }
                    break;
                case "用户组名":
                    if (flag != 0) {
                        checkGroupName(value);
                        if (flag == 0) {
                            result = [false, colname + "不可重复！"];
                        }
                    }
                    break;
                case "手机号码":
                   	// if (!validForm("phone").test(value)) {
                    // 匹配13，14，15，17，18开头的手机号码！
                    var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;                    
                    if (!reg.test(value)) {
                        result = [false, colname + "应为有效手机号码！"];
                    }
                    break;
                default:
                    result = [false, colname + "——" + value];
                    break;
            }
            return result;
        }
        
        var flag = 0;
        
        // TODO 表单校验可以写为一个方法
        function checkName(nameValue) {
            var success = function (request, textStatus) {
                flag = request;
                alert(flag);
            }
            AjaxRequestByData(false, "#", { action: "checkName", name: nameValue }, success);
            // TODO 暂时写为true
            true;
        }
        function checkGroupName(nameValue) {
            var success = function (request, textStatus) {
                flag = request;
                alert(flag);
            }
            AjaxRequestByData(false, "#", { action: "checkGroupName", name: nameValue }, success);
            // TODO 暂时写为true
            true;
        }
        
		</script>
        
  <#else> 
    <div>Unsupported section??</div> 
  </#if> 
</@layout>