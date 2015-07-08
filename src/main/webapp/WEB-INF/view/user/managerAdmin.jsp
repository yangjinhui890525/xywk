<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include.jsp"%>
 <script type="text/javascript">
 $(function() {
	 loadGrid();
 });
 
 
 function loadGrid(queryData){
	 $("#search").show();
	 $('#userTable').datagrid({
			title:'管理员列表', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			height:'auto', //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			collapsible:true,//可折叠
			url:"${pageContext.request.contextPath}/xy/user/getAdminUserList", //数据来源
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
					addrow('添加管理员','/xy/user/editAdminUser','userTable',600,400);
				}
			},'-',{
				text:'编辑',
				iconCls:'icon-edit',
				handler:function(){
					updaterow('编辑用户','/xy/user/editAdminUser','userTable','ID',400,300);
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					deleterow('${pageContext.request.contextPath}/xy/user/deleteAdminUser','userTable','ID');
				}
			},'-',{
				text:'设置用户状态',
				iconCls:'icon-edit',
				handler:function(){
					updateValidrow('manager/user/setUserValid','userTable','ID');
				}
			},'-'
			],
			
			onBeforeLoad : function(param) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
			onLoadSuccess : function(data) {
				$('#userTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				$('.iconImg').attr('src', fq.pixel_0);
				parent.$.messager.progress('close');
			}
		});
 }
 function searchList() {
		var USERNAME = $("#USERNAME").val();
		var TRUENAME=$("#TRUENAME").val();
		var queryData = {
				USERNAME : USERNAME,
				TRUENAME : TRUENAME,
		};
		loadGrid(queryData)
	}
 


	</script>
	
	<div id="search" class="search" style="height: 54px; display: none;">
				<div style="margin-left: 10px;">
					&nbsp;&nbsp; 用户名： <input name="USERNAME" id="USERNAME" >
					&nbsp;&nbsp; 姓名： <input name="TRUENAME" id="TRUENAME" >
					<a href="#" class="easyui-linkbutton" id="searchBtn"onclick="searchList();">查询</a>
				</div>
	</div>
	<div style="padding:10">
		<table id="userTable">
			<thead>
				<tr>
					<th data-options="field:'ID',title:'管理员ID',width:10" sortable="true"></th>
					<th data-options="field:'USERNAME',title:'用户名',width:15" sortable="true"></th>
					<th data-options="field:'TRUENAME',title:'姓名',width:15"></th>
					<th data-options="field:'MPHONE',title:'手机号码',width:10"></th>
					<th data-options="field:'IS_VALID',title:'是否有效',width:10,formatter:function(value,row,index){if(value==1) return 'Y';else return 'N';}" sortable="true"></th>
				</tr>
			</thead>	
		</table>
	</div>

