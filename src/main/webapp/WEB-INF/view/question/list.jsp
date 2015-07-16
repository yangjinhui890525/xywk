<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/commons/include.jsp"%>
<style type="text/css">
	.combox{width: 150px;}
}
</style>
<script>
var COURSE_ID=${COURSE_ID}
var flag=false;
$(function() {
	initTree();
});
function initTree() {
	$('#tree').tree(
					{
						checkbox : false,
						url : fq.contextPath+"/xy/chapterunit/getChapterUnitTree?COURSE_ID="+COURSE_ID,
						animate : true,
						lines : true,
						checkbox:true,
						onlyLeafCheck:true,
						onClick : function(node) {
						if(node.attributes.type=="chapterunit")
						{
							flag=true;
							initResData(node.id, node.text, node.pid);	
						}
						else
						{
							$.messager.alert('提示',"请选择知识点",'info');
						}
					}
					});
}
function initResData(id, name, pid) {

	//$("#titleId").hide();
	//$("#search").show();

	var queryData = {
			CHAPTER_UNIT_ID : id,
	};
		loadGrid(queryData);
} 
function loadGrid(queryData){
	 $('#object').datagrid({
			title:'题目列表', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			height:'auto', //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			collapsible:true,//可折叠
			url:fq.contextPath+'/xy/question/getQuestionDataJson', //数据来源
			remoteSort: true, //服务器端排序
			idField:'ID', //主键字段
			queryParams:queryData, //查询条件
			pagination:true, //显示分页
			rownumbers:true, //显示行号
			frozenColumns:[[
			       {field:'ck',checkbox:true}
			]],
			toolbar:[{
				text:'添加',
				iconCls:'icon-add',
				handler:function(){
					var nodes = $('#tree').tree('getChecked');
					var node_select = $('#tree').tree('getSelected');
					var CHAPTER_UNIT_IDs="";
					if(nodes.length>0)
					{
						for(var i=0;i<nodes.length;i++)
						{
							var node=nodes[i];
							CHAPTER_UNIT_IDs=CHAPTER_UNIT_IDs+node.id+",";
						}
					}
					else
					{
						CHAPTER_UNIT_IDs=node_select.id;
					}
					var url='/xy/question/addQuestion?CHAPTER_UNIT_IDs='+CHAPTER_UNIT_IDs+'&COURSE_ID='+COURSE_ID;
					addrow('添加题目',url,'object',900,600);
				}
			},'-',{
				text:'编辑',
				iconCls:'icon-edit',
				handler:function(){
					updaterow('编辑题目','/xy/question/editQuestion','object','ID',900,600);
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					//deleterow(fq.contextPath+'/xy/course/deleteCourse','courseTable','ID');
				}
			},'-'
			],
			
			onBeforeLoad : function(param) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
			onLoadSuccess : function(data) {
				$('#courseTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				$('.iconImg').attr('src', fq.pixel_0);
				parent.$.messager.progress('close');
				var rows= $('#object').datagrid("getRows");
				if(rows.length>0&&flag)
					{
						//setValue(rows,0);
						
					}
			
			}
		});
}
function setValue(rows1,num)
{
	/* var data={QUE_TYPE:"11111"};
	$('#object').datagrid('updateRow',{
		index: 0,
		row: data
	}); */
		var row1=rows1[num];
		
		if(!isNaN(row1.QUE_TYPE))
		{
			var NID=row1.QUE_TYPE+","+row1.QUE_TYPE
			var url=fq.contextPath+"/xy/dic/getValue?DIC_TYPE=QUE_TYPE&NID="+row1.QUE_TYPE;
			$.post(url,function(result){
				var data={};
				data["QUE_TYPE"]=result.NAME;
				$('#object').datagrid('updateRow',{
					index: num,
					row: data
				});
				num=num+1;
				if(num!=rows1.length)
					 setValue(rows1,num);
			});
		}
		
}
function setValue1(value,type,index)
{
	var val="";
	var url=fq.contextPath+"/xy/dic/getValue?DIC_TYPE="+type+"&NID="+value;
	$.post(url,function(result){
		val= result.NAME;
	});
	while(val=="")
	{
		
	}
	return val;
	//return value;
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
								<%-- form:hidden path="SEARCH_IA_QC_GUID" id="SEARCH_IA_QC_GUID1"  value=""/> --%>
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
			<div id="search" class="search1">
				<div style="margin-left: 10px;">
				<input style="width: 150px;" class="easyui-combobox" data-options="valueField:'NID',textField:'NAME'" url="${pageContext.request.contextPath}/xy/dic/type?DIC_TYPE=QUE_TYPE" value="10"/>
				<input style="width: 150px;" class="easyui-combobox" data-options="valueField:'NID',textField:'NAME'" url="${pageContext.request.contextPath}/xy/dic/type?DIC_TYPE=QUE_DIFFCULT" value="10"/>
				<input style="width: 150px;" class="easyui-combobox" data-options="valueField:'NID',textField:'NAME'" url="${pageContext.request.contextPath}/xy/dic/type?DIC_TYPE=QUE_SCORE" value="1"/>
				<input style="width: 150px;" class="easyui-combobox" data-options="valueField:'NID',textField:'NAME'" url="${pageContext.request.contextPath}/xy/dic/type?DIC_TYPE=QUE_CLASS" value="1"/>
				<input style="width: 150px;" class="easyui-combobox" data-options="valueField:'NID',textField:'NAME'" url="${pageContext.request.contextPath}/xy/dic/type?DIC_TYPE=QUE_CONTENT_TYPE" value="1"/>
				<input style="width: 150px;" class="easyui-combobox" data-options="valueField:'NID',textField:'NAME'" url="${pageContext.request.contextPath}/xy/dic/type?DIC_TYPE=ORDER_NAME_QUE" value="1"/>
				<input style="width: 150px;" class="easyui-combobox" data-options="valueField:'NID',textField:'NAME'" url="${pageContext.request.contextPath}/xy/dic/type?DIC_TYPE=ORDER_CEND" value="1"/>
					<a href="#" class="easyui-linkbutton" id="searchBtn"onclick="searchList();">查询</a>
				</div>
			</div>
			<div style="padding:10">
					<table id="object">
						<thead>
							<tr>
								<th data-options="field:'ID',title:'ID',width:10" sortable="true"></th>
								<th data-options="field:'QUE_TYPE',title:'题型',width:10" sortable="true"></th>
								<th data-options="field:'QUE_DIFFCULT',title:'难度',width:15" sortable="true"></th>
								<th data-options="field:'QUE_SCORE',title:'分数',width:15"></th>
								<th data-options="field:'USED_COUNT',title:'使用次数',width:10"></th>
								<th data-options="field:'CONTENT_URL',title:'题干简要',width:10"></th>
							</tr>
						</thead>	
					</table>
			</div>
			
			
		</div>
	</div>

</body>
</html>