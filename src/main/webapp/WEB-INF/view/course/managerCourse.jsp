<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/commons/include.jsp"%>
<script>
$(function() {
	initTree();
});
function initTree() {

	$('#tree').tree(
					{
						checkbox : false,
						url : fq.contextPath+"/xy/category/getCategoryTree",
						animate : true,
						lines : true,
						onClick : function(node) {
						/* 	var isAdd = node.attributes.isAddRes;
							if (isAdd == 2) {
								var npid = node.attributes.pid;
								initResData(node.id, node.text, npid);
								pid = -1;
							} else {
								Open(node.id, node.text);
							}

						},
						onBeforeExpand : function(node, param) {
							//$('#taskTree').tree('options').url = ctx + "/rims/rescue/loadRescueTaskTreeRootNodes.do?parentId="+node.id;                
						} */
						//alert("aaa");
						initResData(node.id, node.text, node.pid);	
					
					}
					});
}
 function initResData(id, name, pid) {
	 alert(id);
	gctgId = id;
	gpartId = pid;

	$("#titleId").hide();
	$("#search").show();

	var queryData = {
		category_id : id,
		class_id : pid
	};
	
	if(id >= 100){
		getVideoData(queryData);
	}else{
		getData(queryData);	
	}
	
} 
 
 function getData(queryData) {
	    alert("come");
		var url = fq.contextPath+'/xy/course/getCourseDataJson';
		$('#dataGrid').datagrid(
						{
							url : url,
							fitColumns : true,
							border : false,
							pagination : true,
							idField : 'ID',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							remoteSort : true, //服务器端排序
							sortName : 'ID',
							queryParams : queryData, //查询条件
							loadMsg : '数据装载中......',
							sortOrder : 'desc',
							checkOnSelect : false,
							selectOnCheck : false,
							rownumbers : true,
							nowrap : false,
							frozenColumns : [ [ {
								field : 'CK',
								checkbox : true
							}, {
								field : 'ID',
								title : '编号',
								width : 100,
								hidden : true
							}, {
								field : 'NAME',
								title : '名称',
								width : 150,
								sortable : true
							} ] ],
							columns : [ [
									{
										field : 'C_URL',
										title : '附件名称',
										width : 250,
										sortable : true
									},
								
									{
										field : 'C_ICON',
										title : '状态',
										width : 50,
										sortable : true
										/* formatter : function(value, row, index) {
											if (value == 1) {
												return "已推送";
											} else {
												return "待推送";
											}
										} */
									}
									/* {
										field : 'action',
										title : '操作',
										width : 80,
										formatter : function(value, row, index) {
											var e = '<a href="#" mce_href="#" onclick="editRes(\''
													+ row.id + '\')">编辑</a> ';
											var r = '<a href="#" mce_href="#" onclick="deleteRes(\''+ row.id+ '\')">删除</a> ';
											var t = '<a href="#" mce_href="#" onclick="sendFun(\''
													+ row.id + '\')">推送</a> ';
											return e + r + t;
										}
									} */ ] ],
							toolbar : '#toolbar',
							onLoadSuccess : function() {
								$('#searchForm table').show();
								//		parent.$.messager.progress('close');

								//		$(this).datagrid('tooltip');
							},
							onBeforeLoad:function(){
								$('#dataGrid').datagrid('loadData',{total:0,rows:[]});
							},
							onRowContextMenu : function(e, rowIndex, rowData) {
								e.preventDefault();
								$(this).datagrid('unselectAll').datagrid(
										'uncheckAll');
								$(this).datagrid('selectRow', rowIndex);
								$('#menu').menu('show', {
									left : e.pageX,
									top : e.pageY
								});
							}

						});
	}
 
 
</script>
<html>
<head></head>
<body class="easyui-layout">
	<div data-options="region:'west',title:'目录管理',split:true,border:false" style="width:200px;" title="目录管理">

	<!-- 	<div style="text-align: center; padding-top: 10px;">
			<button onclick="addFun();">添加</button>
			&nbsp;&nbsp;
			<button onclick="editFun();">编辑</button>
			&nbsp;&nbsp;
			<button onclick="deleteFun();">删除</button>
		</div> -->

		<div id="nav"  class="easyui-accordion">
			<ul id="tree" style="padding-top: 10px;"></ul>
		</div>
	</div>
	<div region="center">
		<div data-options="region:'center',border:false" id="flList">

			<div id="search" class="search" style="height: 100px; display: none;">
				<div style="margin-left: 10px;">

					时间： <input name="START_TIME" id="START_TIME"
						onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
					&nbsp;至&nbsp; <input name="END_TIME" id="END_TIME"
						onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'});">&nbsp;
					&nbsp;&nbsp;<br> 名称： <input name="name" id="name">
					&nbsp;&nbsp; 状态： <select name="status" id="status"
						style="width: 160px; height: 40px;">
						<option value="-1">&nbsp;</option>
						<option value="0">待推送</option>
						<option value="1">已推送</option>
					</select> &nbsp; &nbsp; <!-- <a href="#" class="easyui-linkbutton" id="searchBtn"
						onclick="searchList();">查询</a> -->
				</div>

			</div>

			<table id="dataGrid">
				<h2 id="titleId" align="center">请点击左侧课堂板书、补充资料、课后练习及其他获取资源！</h2>
			</table>
		</div>
	</div>

</body>
</html>