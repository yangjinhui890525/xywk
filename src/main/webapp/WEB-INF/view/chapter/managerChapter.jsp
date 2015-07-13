<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/commons/include.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
var COURSE_ID=${COURSE_ID};
$(function() {
	var queryData = {
			COURSE_ID : COURSE_ID
		};
	initTree(COURSE_ID);
	//loadGrid(queryData);
});
function initTree(COURSE_ID) {
	$('#tree').tree(
					{
						checkbox : false,
						url : fq.contextPath+"/xy/chapter/getChapterTree?COURSE_ID="+COURSE_ID,
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
function initResData(id,text,pid)
{
	if(pid==-1)
		$.messager.alert('提示',"请选择一个章节分类",'info');
	var queryData={
		PID:id
	};	
	$("#search").show();
	$("#PID").val(id);
	loadGrid(queryData);
}
function loadGrid(queryData){
	 $('#chapterTable').datagrid({
			title:'知识点列表', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			height:'auto', //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			collapsible:true,//可折叠
			url:fq.contextPath+'/xy/chapterunit/getChapterUnitList', //数据来源
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
					addrow('添加知识点','/xy/chapterunit/editChapterUnit?CHAPTER_ID='+queryData.PID+"&COURSE_ID="+COURSE_ID,'chapterTable',850,600);
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					deleterow(fq.contextPath+'/xy/chapterunit/deleteChapterUnit','chapterTable','ID');
				}
			},'-'
			],
			
			onBeforeLoad : function(param) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
			onLoadSuccess : function(data) {
				$('#chapterTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				$('.iconImg').attr('src', fq.pixel_0);
				parent.$.messager.progress('close');
			}
		});
}
function addFun()
{
	var COURSE_ID=${COURSE_ID};
	var node = $('#tree').tree('getSelected');
	if(node==null)
	{
		$.messager.alert('提示',"请选择一个章节分类",'info');
		return;
	}
	var pid=node.id;
	if(node.pid==-1)
		pid=0
	var url = "/xy/chapter/editChapterList?COURSE_ID="+${COURSE_ID}+"&PID="+pid;
	addrow('添加章节',url,'tree',600,400);
}
function editFun()
{
	var COURSE_ID=${COURSE_ID};
	var node = $('#tree').tree('getSelected');
	if(node==null)
	{
		$.messager.alert('提示',"请选择一个章节分类",'info');
		return;
	}
	if(node.pid==node.id)
	{
		$.messager.alert('提示',"请选择一个章节分类",'info');
		return;
	}
	var url = "/xy/chapter/editChapterList?COURSE_ID="+${COURSE_ID}+"&ID="+node.id;
	addrow('添加章节',url,'tree',600,400);
}
function deleteFun()
{
	var COURSE_ID=${COURSE_ID};
	var node = $('#tree').tree('getSelected');
	if(node==null)
	{
		$.messager.alert('提示',"请选择一个章节分类",'info');
		return;
	}
	if(node.pid==node.id)
	{
		$.messager.alert('提示',"请选择一个章节分类!",'info');
		return;
	}
	var url =fq.contextPath+"/xy/chapter/deleteChapterList?ID="+node.id;
	$.messager.confirm('提示','确定要删除吗?',function(result){
        if (result){
        	$.post(url,function(data){
        		initTree(COURSE_ID)
        		$.messager.alert('提示','数据删除成功','info');
        	});
        }});

}
function searchList() {
	var PID=$("#PID").val();
	var name=$("#name").val();
	var queryData = {
		PID:PID,
		NAME:name
	};
	loadGrid(queryData);
}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',title:'章节分类',split:true,border:false" style="width:200px;" title="章节分类">
		<div style="text-align: center; padding-top: 10px;">
			<button onclick="addFun();">添加</button>
			&nbsp;&nbsp;
			<button onclick="editFun();">编辑</button>
			&nbsp;&nbsp;
			<button onclick="deleteFun();">删除</button>
		</div>
		<div id="nav"  class="easyui-accordion">
			<ul id="tree" style="padding-top: 10px;"></ul>
		</div>
	</div>
<div region="center">
	<div data-options="region:'center',border:false" id="flList">
		<div id="search" class="search" style="height: 54px; display: none;">
						<div style="margin-left: 10px;">
							<input type="hidden" name="PID" value="" id="PID">
							&nbsp;&nbsp; 名称： <input name="name" id="name">
							<a href="#" class="easyui-linkbutton" id="searchBtn"onclick="searchList();">查询</a>
						</div>
		
		</div>
		<div style="padding:10">
			<table id="chapterTable">
				<thead>
					<tr>
						<th data-options="field:'ID',title:'ID',width:10" sortable="true"></th>
						<th data-options="field:'NAME',title:'知识点名称',width:15" sortable="true"></th>
						<th data-options="field:'P_URL',title:'知识点URL',width:15" sortable="true"></th>
						<th data-options="field:'DESCRIPTION',title:'描述',width:10"></th>
					</tr>
				</thead>	
			</table>
		</div>
	</div>
</div>
</body>
</html>