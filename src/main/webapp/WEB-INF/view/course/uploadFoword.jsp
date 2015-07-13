<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
 </head>
  		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-2.0.0.js"></script>
		<script src="<%=request.getContextPath() %>/js/jquery.ocupload.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=request.getContextPath() %>/js/jquery.bpopup.min.js" type="text/javascript" charset="utf-8"></script>
  		
  <body style="font-size: 12px;">
<style>
<!--
.option_btn_g{width:66px; height:25px; background:#4a8227; color:#edf5ff; line-height:25px; cursor:pointer;}
-->
</style>
  
   <script type="text/javascript">
   $(function() {
		
		//点击导入按钮事件(******)
		$("#btnImportFromFile").upload({
	        action: '${pageContext.request.contextPath}/xy/course/uploadFile',
	        name: 'file',
	        iframeName: 'ImportFromFile',
	        params: {},
	        onSelect: function (self, element) {
	            this.autoSubmit = false;
				this.submit();
	        },
	        onSubmit: function (self, element) {
	        },
	        onComplete: function (data, self, element) {
	        	var strs=data.split("|");
//	        	window.returnValue = strs[0];
//	        	alert(strs[1]);
	        	getReturnValue(strs[0]);
	        }
	    });
		
		function getReturnValue(name){
			if(window.ActiveXObject){ //IE
				window.returnValue = name;
				window.close();
			}else{ //非IE
				if(window.opener) {
					window.opener.setValue(name) ;
				}
				window.close();
			}
		}
		
	});
	</script>
		
		<input id="btnImportFromFile" class="option_btn_g" type="button" value="选择文件">(支持100M文件)
		<div id="result"></div>
  </body>
</html>
