<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/commons/include.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
$(function() {
	var COURSE_ID=${COURSE_ID};
	var queryData = {
			COURSE_ID : COURSE_ID
		};
	$("#search").show();
	loadGrid(queryData);
});
function loadGrid(queryData){
	 $('#chapterTable').datagrid({
			title:'章节列表', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			height:'auto', //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			collapsible:true,//可折叠
			url:fq.contextPath+'/xy/chapter/getChapterList', //数据来源
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
					addrow('添加章节','/xy/chapter/editChapterList?COURSE_ID='+queryData.COURSE_ID,'chapterTable',400,300);
				}
			},'-',{
				text:'编辑',
				iconCls:'icon-edit',
				handler:function(){
					updaterow('编辑章节','/xy/chapter/editChapterList','chapterTable','ID',400,300);
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					deleterow(fq.contextPath+'/xy/chapter/deleteChapter','chapterTable','ID');
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
</script>
</head>
<body>
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
					</select> &nbsp; &nbsp; 
					<a href="#" class="easyui-linkbutton" id="searchBtn"onclick="searchList();">查询</a>
				</div>

</div>
<div style="padding:10">
	<table id="chapterTable">
		<thead>
			<tr>
				<th data-options="field:'ID',title:'章节ID',width:10" sortable="true"></th>
				<th data-options="field:'NAME',title:'名称',width:15" sortable="true"></th>
				<th data-options="field:'DESCRIPTION',title:'描述',width:10"></th>
			</tr>
		</thead>	
	</table>
</div>
</body>
</html>