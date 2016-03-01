<#include "_layout.ftl">
 
<@layout ; section> 
  <#if section = "title"> 
    机构树菜单树 - pickles
  <#elseif section = "specific-styles" > 

  <#elseif section = "inline-styles" > 

  <#elseif section = "page-content" > 
								<!-- PAGE CONTENT BEGINS -->

								<div class="row">
									<div class="col-sm-6">
										<div class="widget-box">
											<div class="widget-header header-color-blue2">
												<h4 class="lighter smaller">Choose Categories</h4>
											</div>

											<div class="widget-body">
												<div class="widget-main padding-8">
													<div id="tree1" class="tree"></div>
												</div>
											</div>
										</div>
									</div>

									<div class="col-sm-6">
										<div class="widget-box">
											<div class="widget-header header-color-green2">
												<h4 class="lighter smaller">Browse Files</h4>
											</div>

											<div class="widget-body">
												<div class="widget-main padding-8">
													<div id="tree2" class="tree"></div>
												</div>
											</div>
										</div>
									</div>
								</div>

								<script type="text/javascript">
									var $${base}/static/assets = "${base}/static/assets";//this will be used in fuelux.tree-sampledata.js
								</script>

								<!-- PAGE CONTENT ENDS -->
  
  <#elseif section = "specific-scripts" > 
		<script src="${base}/static/assets/js/fuelux/data/fuelux.tree-sampledata.js"></script>
		<script src="${base}/static/assets/js/fuelux/fuelux.tree.min.js"></script>

  <#elseif section = "inline-scripts" >
		<script type="text/javascript">
			jQuery(function($){

		$('#tree1').ace_tree({
			dataSource: treeDataSource ,
			multiSelect:true,
			loadingHTML:'<div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div>',
			'open-icon' : 'icon-minus',
			'close-icon' : 'icon-plus',
			'selectable' : true,
			'selected-icon' : 'icon-ok',
			'unselected-icon' : 'icon-remove'
		});

		$('#tree2').ace_tree({
			dataSource: treeDataSource2 ,
			loadingHTML:'<div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div>',
			'open-icon' : 'icon-folder-open',
			'close-icon' : 'icon-folder-close',
			'selectable' : false,
			'selected-icon' : null,
			'unselected-icon' : null
		});



		/**
		$('#tree1').on('loaded', function (evt, data) {
		});

		$('#tree1').on('opened', function (evt, data) {
		});

		$('#tree1').on('closed', function (evt, data) {
		});

		$('#tree1').on('selected', function (evt, data) {
		});
		*/
});
		</script>
  <#else> 
    <div>Unsupported section??</div> 
  </#if> 
</@layout>