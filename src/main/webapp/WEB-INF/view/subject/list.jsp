<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
						checkbox:true,
						onlyLeafCheck:true,
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
				<div class="xinxi" style="width:100%">
					<div class="searchbg" style="width:100%">
								<<%-- form:hidden path="SEARCH_IA_QC_GUID" id="SEARCH_IA_QC_GUID1"  value=""/> --%>
							<%-- 	<form:hidden  path="" id="SEARCH_IA_QC_AUXID1"  name="SEARCH_IA_QC_AUXID" value="${searchParam.SEARCH_IA_QC_AUXID}"/> --%>
							<%-- 	<form:select path="/xy/dic" name="SEARCH_IA_QUE_TYPE" id="SEARCH_IA_QUE_TYPE" itemLabel="label" itemValue="value"></form:select>
								<form:select path="/xy/dic" name="SEARCH_IA_QUE_DIFFCULT" id="SEARCH_IA_QUE_DIFFCULT" itemLabel="label" itemValue="value"></form:select>
								<form:select path="/xy/dic" name="SEARCH_IA_QUE_TYPE" id="SEARCH_IA_QUE_TYPE" itemLabel="label" itemValue="value"></form:select>
								<form:select path="/xy/dic" name="SEARCH_IA_QUE_SCORE" id="SEARCH_IA_QUE_SCORE" itemLabel="label" itemValue="value"></form:select>
								<form:select path="/xy/dic" name="SEARCH_IA_QUE_CLASS" id="SEARCH_IA_QUE_CLASS" itemLabel="label" itemValue="value"></form:select>
								<form:select path="/xy/dic" name="SEARCH_IA_QUE_CONTENT_TYPE" id="SEARCH_IA_QUE_CONTENT_TYPE" itemLabel="label" itemValue="value"></form:select>
								<form:select path="/xy/dic" name="ORDER_NAME" id="ORDER_NAME" itemLabel="label" itemValue="value"></form:select>
								<form:select path="/xy/dic" name="ORDER_NAME" id="ORDER_NAME" itemLabel="label" itemValue="value"></form:select> --%>
							<%-- 	<form:textarea name="SEARCH_IA_QUE_KEYWORD" value="${searchParam.SEARCH_IA_QC_SUB}" path=""/>
								<form:checkbox name="SEARCH_IA_QC_SUB" value="${searchParam.SEARCH_IA_QC_SUB }" path=""/> --%>
						<!-- 	<s:form method="post" action="question!findListQuestion.action">
								<s:hidden id="SEARCH_IA_QC_GUID1" name="SEARCH_IA_QC_GUID" value="%{#request.searchParam.SEARCH_IA_QC_GUID}"/>
								<s:hidden id="SEARCH_IA_QC_AUXID1"  name="SEARCH_IA_QC_AUXID" value="%{#request.searchParam.SEARCH_IA_QC_AUXID}"/>
								<s:select name="SEARCH_IA_QUE_TYPE" 		list="#request.typeDic" 		listKey="defValue" listValue="name" 	headerKey="" headerValue="EXAM_TYPE" value="#request.searchParam.SEARCH_IA_QUE_TYPE"/>  题型		
								<s:select name="SEARCH_IA_QUE_DIFFCULT" 	list="#request.diffcultDic" 	listKey="defValue" listValue="name" 	headerKey="" headerValue="DIFFCULTY" value="#request.searchParam.SEARCH_IA_QUE_DIFFCULT"/>  难度					
								<s:select name="SEARCH_IA_QUE_SCORE" 		list="#request.scoreDic" 		listKey="defValue" listValue="name" 	headerKey="" headerValue="FRACTION" value="#request.searchParam.SEARCH_IA_QUE_SCORE"/>  分数
								<s:select name="SEARCH_IA_QUE_CLASS" 		list="#request.classDic" 		listKey="defValue" listValue="name"     headerKey="" headerValue="CATEGORY"  value="#request.searchParam.SEARCH_IA_QUE_CLASS"/>所属分类
								<s:select name="SEARCH_IA_QUE_CONTENT_TYPE" list="#request.contentTypeDic"  listKey="defValue" listValue="name"     headerKey="" headerValue="QUESTION_TYPE" value="#request.searchParam.SEARCH_IA_QUE_CONTENT_TYPE"/>试题类型
								<s:select name="ORDER_NAME"  				list="#request.orderName" 		listKey="defValue" listValue="name"	 value="#request.searchParam.ORDER_NAME"/>  
								<s:select name="ORDER_CEND"  				list="#request.orderCend" 		listKey="defValue" listValue="name"	 value="#request.searchParam.ORDER_CEND"/>
								<s:textfield 	 name="SEARCH_IA_QUE_KEYWORD"  value="%{#request.searchParam.SEARCH_IA_QUE_KEYWORD}"/>	
								<s:checkbox name="SEARCH_IA_QC_SUB" value="#request.searchParam.SEARCH_IA_QC_SUB"/>子分类<iac:language key="SUBCATEGORIES"/>
								<s:submit value="SELECT" cssClass="btnbg">查询</s:submit>
							</s:form> -->
						</div>
					</div>	
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