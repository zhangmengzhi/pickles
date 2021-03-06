<#include "_layout.ftl">
 
<@layout ; section> 
  <#if section = "title"> 
  Bootstrap网格 - pickles
  <#elseif section = "specific-styles" > 

  <#elseif section = "inline-styles" > 
		<!-- inline styles related to this page -->

		<style>
			.center {
				text-align:center;
			}
			.center [class*="col-"] {
				margin-top:2px;
				margin-bottom:2px;
				padding-top:4px;
				padding-bottom:4px;
			
				position:relative;
				text-overflow:ellipsis;
			}
			.center [class*="col-"]  span{
			  position:relative;
			  z-index:2;
				
			  display: inline-block;
			  overflow: hidden;
			  text-overflow: ellipsis;
			  white-space: nowrap;
			
			  width: 100%;
			}
			.center [class*="col-"]:before {
				position:absolute;
				top:0; bottom:0;
				left:2px; right:2px;
				content:"";
				display:block;
				border:1px solid #DDD;
				z-index: 1;
			}
			
			.center [class*="col-"]:hover:before {
				background-color:#FCE6A6;
				border-color:#EFD27A;
			}
		</style>

  <#elseif section = "page-content" > 
								<!-- PAGE CONTENT BEGINS -->

								<div class="center">
									<div class="row">
										<div class="col-xs-12">
											<span>.col-xs-12</span>
										</div>
									</div>

									<div class="row">
										<div class="col-xs-1">
											<span>.col-xs-1</span>
										</div>

										<div class="col-xs-11">
											<span>.col-xs-11</span>
										</div>
									</div>

									<div class="row">
										<div class="col-xs-6 col-sm-2">
											<span>.col-xs-6.col-sm-2</span>
										</div>

										<div class="col-xs-6 col-sm-10">
											<span>.col-xs-6.col-sm-10</span>
										</div>
									</div>

									<div class="row">
										<div class="col-xs-12 col-lg-6">
											<span>.col-xs-12.col-lg-6</span>
										</div>

										<div class="col-xs-12 col-lg-6">
											<span>.col-xs-12.col-lg-6</span>
										</div>
									</div>

									<div class="row">
										<div class="col-xs-4">
											<span>.col-xs-4</span>
										</div>

										<div class="col-xs-8">
											<span>.col-xs-8</span>
										</div>
									</div>

									<div class="row">
										<div class="col-xs-5">
											<span>.col-xs-5</span>
										</div>

										<div class="col-xs-7">
											<span>.col-xs-7</span>
										</div>
									</div>

									<div class="row">
										<div class="col-xs-6">
											<span>.col-xs-6</span>
										</div>

										<div class="col-xs-6">
											<span>.col-xs-6</span>
										</div>
									</div>

									<div class="row">
										<div class="col-xs-7">
											<span>.col-xs-7</span>
										</div>

										<div class="col-xs-5">
											<span>.col-xs-5</span>
										</div>
									</div>

									<div class="row">
										<div class="col-xs-8">
											<span>.col-xs-8</span>
										</div>

										<div class="col-xs-4">
											<span>.col-xs-4</span>
										</div>
									</div>

									<div class="row">
										<div class="col-xs-9">
											<span>.col-xs-9</span>
										</div>

										<div class="col-xs-3">
											<span>.col-xs-3</span>
										</div>
									</div>

									<div class="row">
										<div class="col-xs-10">
											<span>.col-xs-10</span>
										</div>

										<div class="col-xs-2">
											<span>.col-xs-2</span>
										</div>
									</div>

									<div class="row">
										<div class="col-xs-11">
											<span>.col-xs-11</span>
										</div>

										<div class="col-xs-1">
											<span>.col-xs-1</span>
										</div>
									</div>

									<div class="row">
										<div class="col-xs-12">
											<span>.col-xs-12</span>
										</div>
									</div>
								</div>

								<!-- PAGE CONTENT ENDS -->
  <#elseif section = "specific-scripts" > 
    <div></div> 
  <#elseif section = "inline-scripts" >
    <div></div> 
  <#else> 
    <div>Unsupported section??</div> 
  </#if> 
</@layout>