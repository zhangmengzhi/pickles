<#include "_layout.ftl">
 
<@layout ; section> 
  <#if section = "title"> 
		Dropzone.js - pickles
  <#elseif section = "specific-styles" > 
	&nbsp;
  <#elseif section = "inline-styles" > 
	&nbsp;
  <#elseif section = "page-content" > 
								<!-- PAGE CONTENT BEGINS -->

								<div class="alert alert-info">
									<i class="icon-hand-right"></i>

									Please note that demo server is not configured to save uploaded files, therefore you may get an error message.
									<button class="close" data-dismiss="alert">
										<i class="icon-remove"></i>
									</button>
								</div>

								<div id="dropzone">
									<form action="//dummy.html" class="dropzone">
										<div class="fallback">
											<input name="file" type="file" multiple="" />
										</div>
									</form>
								</div><!-- PAGE CONTENT ENDS -->
  <#elseif section = "specific-scripts" >
		<script src="${base}/static/assets/js/dropzone.min.js"></script>

  <#elseif section = "inline-scripts" >

		<script type="text/javascript">
			jQuery(function($){
			
			try {
			  $(".dropzone").dropzone({
			    paramName: "file", // The name that will be used to transfer the file
			    maxFilesize: 0.5, // MB
			  
				addRemoveLinks : true,
				dictDefaultMessage :
				'<span class="bigger-150 bolder"><i class="icon-caret-right red"></i> Drop files</span> to upload \
				<span class="smaller-80 grey">(or click)</span> <br /> \
				<i class="upload-icon icon-cloud-upload blue icon-3x"></i>'
			,
				dictResponseError: 'Error while uploading file!',
				
				//change the previewTemplate to use Bootstrap progress bars
				previewTemplate: "<div class=\"dz-preview dz-file-preview\">\n  <div class=\"dz-details\">\n    <div class=\"dz-filename\"><span data-dz-name></span></div>\n    <div class=\"dz-size\" data-dz-size></div>\n    <img data-dz-thumbnail />\n  </div>\n  <div class=\"progress progress-small progress-striped active\"><div class=\"progress-bar progress-bar-success\" data-dz-uploadprogress></div></div>\n  <div class=\"dz-success-mark\"><span></span></div>\n  <div class=\"dz-error-mark\"><span></span></div>\n  <div class=\"dz-error-message\"><span data-dz-errormessage></span></div>\n</div>"
			  });
			} catch(e) {
			  alert('Dropzone.js does not support older browsers!');
			}
			
			});
		</script>
<#else> 
    <div>Unsupported section??</div> 
  </#if> 
</@layout>
