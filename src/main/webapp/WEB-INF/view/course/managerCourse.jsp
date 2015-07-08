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
	gctgId = id;
	gpartId = pid;

	$("#titleId").hide();
	$("#search").show();

	var queryData = {
		category_id : id,
		class_id : pid
	};
	$("#category_id").val(id);
		//getData(queryData);	
		loadGrid(queryData);
} 
 
 function getData(queryData) {
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
 
 function loadGrid(queryData){
	 $('#courseTable').datagrid({
			title:'资源科目列表', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			height:'auto', //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			collapsible:true,//可折叠
			url:fq.contextPath+'/xy/course/getCourseDataJson', //数据来源
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
					addrow('添加资源科目','/xy/course/editCourse?CATEGORY_ID='+queryData.category_id,'courseTable',400,300);
				}
			},'-',{
				text:'编辑',
				iconCls:'icon-edit',
				handler:function(){
					updaterow('编辑添加资源科目','/xy/course/editCourse','courseTable','ID',400,300);
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					deleterow(fq.contextPath+'/xy/course/deleteCourse','courseTable','ID');
				}
			},'-',{
				text:'设置用户状态',
				iconCls:'icon-edit',
				handler:function(){
					updateValidrow('manager/user/setUserValid','courseTable','ID');
				}
			},'-',
			{
				text:'章节管理',
				iconCls:'icon-add',
				handler:function(){
					var rows = $('#courseTable').datagrid('getSelections');
					if(rows.length==0){
						$.messager.alert('提示',"请选择要编辑的数据",'info');
						return;
					}
					if(rows.length > 1){
						$.messager.alert('提示',"只能选择一行数据进行编辑",'info');
						return;
					}
					
					var event={COURSE_ID:rows[0]['ID'],name:rows[0]['NAME']}
					operation(event);
				}},'-'
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
			}
		});
 }
 function operation(event) {
		var name = event.name;
		var COURSE_ID = event.COURSE_ID;
		var encname = encodeURI(name);
		var url = fq.contextPath+'/xy/chapter/openChataterList?COURSE_ID='+COURSE_ID;
		//self.parent.addTab(name+'资源管理',url,'');
		var tabs = parent.$('#mainTabs');
		var opts = {
			title : name+"的章节管理",
			closable : true,
			/* iconCls : node.iconCls, */
			content : fq.formatString('<iframe src="{0}" allowTransparency="true" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>', url),
			border : false,
			fit : true
		};
		if (tabs.tabs('exists', name+"的章节管理")) {
			tabs.tabs('select', name+"的章节管理");
		} else {
			tabs.tabs('add', opts);
		}
	}
 function searchList() {
		var NAME = $("#name").val();
		var category_id=$("#category_id").val();
		var queryData = {
			NAME : NAME,
			category_id : category_id,
		};
		loadGrid(queryData)
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
			<div id="search" class="search" style="height: 54px; display: none;">
				<div style="margin-left: 10px;">
					&nbsp;&nbsp; 名称： <input name="name" id="name" >
					<input type="hidden" value="${CATEGORY_ID}" name="category_id" id="category_id">
					<a href="#" class="easyui-linkbutton" id="searchBtn"onclick="searchList();">查询</a>
				</div>
			</div>

		<!-- 	<table id="dataGrid">
				<h2 id="titleId" align="center">请点击左侧课堂板书、补充资料、课后练习及其他获取资源！</h2>
			</table> -->
			
				<div style="padding:10">
					<table id="courseTable">
						<thead>
							<tr>
								<th data-options="field:'ID',title:'资源科目ID',width:10" sortable="true"></th>
								<th data-options="field:'NAME',title:'名称',width:15" sortable="true"></th>
								<!-- <th data-options="field:'CATEGORY_ID',title:'',width:15"></th> -->
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
				</div>
			
			
		</div>
	</div>

</body>
</html>