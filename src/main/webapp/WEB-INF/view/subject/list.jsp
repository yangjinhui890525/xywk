<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/commons/include.jsp"%>
<script>
$(function() {
	initTree();
});
function initTree() {

	$('#tree').tree(
					{
						checkbox : false,
						url : fq.contextPath+"/xy/subject/getTree",
						animate : true,
						lines : true,
						onClick : function(node) {
						//initResData(node.id, node.text, node.pid);	
					}
					});
}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',title:'资源分类',split:true,border:false" style="width:200px;" title="资源分类">
		<div id="nav"  class="easyui-accordion">
			<ul id="tree" style="padding-top: 10px;"></ul>
		</div>
	</div>
	<div region="center">
		<div data-options="region:'center',border:false" id="flList">
			<%-- <div id="search" class="search" style="height: 54px; display: none;">
				<div style="margin-left: 10px;">
					&nbsp;&nbsp; 名称： <input name="name" id="name" >
					<input type="hidden" value="${CATEGORY_ID}" name="category_id" id="category_id">
					<a href="#" class="easyui-linkbutton" id="searchBtn"onclick="searchList();">查询</a>
				</div>
			</div> --%>
				<!-- <div style="padding:10">
					<table id="courseTable">
						<thead>
							<tr>
								<th data-options="field:'ID',title:'资源科目ID',width:10" sortable="true"></th>
								<th data-options="field:'NAME',title:'名称',width:15" sortable="true"></th>
								<th data-options="field:'CATEGORY_ID',title:'',width:15"></th>
								<th data-options="field:'ORDER_NUM',title:'排序字段',width:10"></th>
								<th data-options="field:'C_URL',title:'资料地址',width:10,formatter:function(value,row,index){if(value==1) return 'Y';else return 'N';}" sortable="true"></th>
								<th data-options="field:'C_ICON',title:'资料图片',width:10"></th>
								<th data-options="field:'DESCRIPTION',title:'描述',width:10"></th>
								<th data-options="field:'C_SIZE',title:'资料大小',width:10"></th>
								<th data-options="field:'C_DOWN_COUNT',title:'下载次数',width:10"></th>
								<th data-options="field:'CONTENT_LIST',title:'目录',width:10"></th>
								<th data-options="field:'TYPE',title:'类型',width:10"></th>
							</tr>
						</thead>	
					</table>
				</div> -->
			
			
		</div>
	</div>

</body>
</html>