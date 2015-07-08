<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include.jsp"%>
<style type="text/css">
body {
	font-size: 14px;
	color: #000;
	font-family: Microsoft Yahei, arial, verdana, sans-serif;
}

html>body {
	font-size: 16px;
}

.toolbar-btn {
	display: inline-block;
	vertical-align: top;
	width: auto;
	min-width: 100px;
	line-height: 30px;
	font-size: 14px;
	font-family: Microsoft Yahei;
	padding: 0;
	margin: 0 4px;
	text-align: center;
}

.datagrid-btn-separator {
	float: left;
	height: 30px;
	border-left: 1px solid #ccc;
	border-right: 1px solid #fff;
	margin: 2px 1px;
}
</style>
<script>
$(function() {
	//var pid=${pid}
	getData();
	//loadResource();
});
/*  function loadResource()
{
	$('#resource').panel({
		fit:true,
		title: '资源分类',
		 tools: [{
		text:'添加',
		iconCls:'icon-add',
		handler:function(){$('#add_resource').dialog('open');}
		}
		], 
		onBeforeLoad : function(param) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
		},
		});
	} */
 $('#add_resource').dialog({
	    title:"title",
	    buttons : [ {
			text:'关闭',
			iconCls:'icon-no',
			handler:function(){
				alert("aaa");
			}
			} ]
	});
	function getData() {
		var url = fq.contextPath+'/xy/category/getCategoryData';
		var pid=${pid};
		$.getJSON(url, {pid:pid}
			, function(json) {
			$("#dataTB tr").eq(1).nextAll().remove(); //将除模板行的tr删除
			var size = json.length;
			var row;
			var optrow;
			for (var i = 1; i <= size; i++) {
				var tag = i % 4;
				//alert(tag);
				if (tag == 1) {
					row = $("#dataTr").clone();
					row.show();
					row.appendTo("#dataTB");

					optrow = $("#dataTropt").clone();
					optrow.show();
					optrow.appendTo("#dataTB");
				}
				row.find("#td" + tag).show();
				row.find("#sp" + tag).text(json[i - 1].NAME);
				row.find("#tdiv" + tag).click({
					name : json[i - 1].NAME,
					back : json[i - 1].ID,
					pid:json[i - 1].PID
				}, operation);

				optrow.find("#tdopt" + tag).show();
				optrow.find("#ck" + tag).val(json[i - 1].ID);
			}

		});
	}
	function operation(event) {
		var name = event.data.name;
		var id = event.data.back;
		var pid = event.data.pid;
		var encname = encodeURI(name);
		var url = fq.contextPath+'/xy/category/getCategoryList?pid='+id;
		//self.parent.addTab(name+'资源管理',url,'');
		var tabs = parent.$('#mainTabs');
		var opts = {
			title : name,
			closable : true,
			/* iconCls : node.iconCls, */
			content : fq.formatString('<iframe src="{0}" allowTransparency="true" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>', url),
			border : false,
			fit : true
		};
		if (tabs.tabs('exists', name)) {
			tabs.tabs('select', name);
		} else {
			tabs.tabs('add', opts);
		}
	}
	
	function addFun() {
		var url = fq.contextPath+'/xy/category/addCategory?pid=${pid}';
		/* parent.$.modalDialog({ */
			dialog=parent.fq.modalDialog_NoFrame({
			title : '添加资源分类',
			width : 400,
			height : 300,
			href : url,
			buttons : [{
				text:'取消',
				iconCls:'icon-no',
				handler:function(){
					dialog.window('close');}},
			           {
				text : '添加',
				iconCls:'icon-add',
				handler : function() {
					var f = dialog.find('#form');
					f.submit();
				}
			} 
]
		});
	}
		
		function add(){
			var url = '/xy/category/addCategory?pid=${pid}';
			addrow('添加资源分类',url,'dataTB',600,400);
		}
		function editFun()
		{
		 	var size=$('input[type="checkbox"]:checked').length;
			if(size==0)
			{
				$.messager.alert('提示','请选择一个资源分类', 'info');
			}
			else if(size>1)
			{
				$.messager.alert('提示','只能选择一个资源分类', 'info');
			}
			else
			{
				var url = '/xy/category/editCategory?id='+$('input[type="checkbox"]:checked').val();
				editrow('编辑资源分类',url,'dataTB',600,400);
			}

		}
		function deleteFun()
		{
			var size=$('input[type="checkbox"]:checked').length;
			if(size==0)
			{
				$.messager.alert('提示','请选择一个资源分类', 'info');
			}
			else 
			{
				$.messager.confirm("确认是否删除","删除信息",function(flag){
					if(flag)
					{
						var value="";
						$('input[type="checkbox"]:checked').each(function(){
							value=value+$(this).val()+",";
						});
						var url =  fq.contextPath+'/xy/category/deleteCategory?ids='+value;
						$.ajax({
								type:"post",
								url:url,
								success:function(data)
								{
									if(data.success)
									{
										//  var currTab =  self.parent.$('#mainTabs').tabs('getSelected'); //获得当前tab
										updateTabs();	
	
									}
									else
									{
										$.messager.alert('提示','删除资源分类失败', 'info');
										
									}
								}
							
							});
						
					}
					else
					{
					}
				});
			}
		}
		
</script>
<div>
	<div style="width: 100%; text-align: center;">
		<table
			style="text-align: center; width: 100%; background-color: #F4F4F4;">
			<tr>
				<td align="center">
					<table style="text-align: center;">
						<tr style="line-height: 30px;">
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a onclick="add();" href="javascript:void(0);"
								class="toolbar-btn"
								data-options="plain:true,iconCls:'pencil_add'">添加资源分类</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a onclick="editFun();" href="javascript:void(0);"
								class="toolbar-btn"
								data-options="plain:true,iconCls:'pencil_add'">编辑资源分类</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a onclick="deleteFun();" href="javascript:void(0);"
								class="toolbar-btn"
								data-options="plain:true,iconCls:'pencil_add'">删除资源分类</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		
		<table style="width: 100%; margin-top: 20px;" id="dataTB">
			<tr align="center" id="dataTr" style="display: none;">
				<td id="td1" style="display: none; cursor: pointer;">
					<div id="tdiv1" style="position: relative; width: 124px; height: 175px;"
						title="点击进行目录及资源管理">
						<img src="${pageContext.request.contextPath}/images/book.jpg"
							width="124" height="176" alt=""> <span id="sp1"
							style="position: absolute; top: 60px; left: 5px; right: 5px; font-weight: bold;">
						</span>
					</div>
				</td>
				<td id="td2" style="display: none; cursor: pointer;">
					<div id="tdiv2" style="position: relative; width: 124px; height: 175px;"
						title="点击进行目录及资源管理">
						<img src="${pageContext.request.contextPath}/images/book.jpg"
							width="124" height="176" alt=""> <span id="sp2"
							style="position: absolute; top: 60px; left: 5px; right: 5px; font-weight: bold;">
						</span>
					</div>
				</td>
				<td id="td3" style="display: none; cursor: pointer;">
					<div id="tdiv3" style="position: relative; width: 124px; height: 175px;"
						title="点击进行目录及资源管理">
						<img src="${pageContext.request.contextPath}/images/book.jpg"
							width="124" height="176" alt=""> <span id="sp3"
							style="position: absolute; top: 60px; left: 5px; right: 5px; font-weight: bold;">
						</span>
					</div>
				</td>
				<td id="td0" style="display: none; cursor: pointer;">
					<div id="tdiv0" style="position: relative; width: 124px; height: 175px;"
						title="点击进行目录及资源管理">
						<img src="${pageContext.request.contextPath}/images/book.jpg"
							width="124" height="176" alt=""> <span id="sp0"
							style="position: absolute; top: 60px; left: 5px; right: 5px; font-weight: bold;">
						</span>
					</div>
				</td>
			</tr>
			<tr align="center" id="dataTropt" style="display: none;">
				<td id="tdopt1" style="display: none; padding-bottom: 30px;"><input
					type="checkbox" id="ck1" name="category_name"></td>
				<td id="tdopt2" style="display: none; padding-bottom: 30px;"><input
					type="checkbox" id="ck2" name="category_name"></td>
				<td id="tdopt3" style="display: none; padding-bottom: 30px;"><input
					type="checkbox" id="ck3" name="category_name"></td>
				<td id="tdopt0" style="display: none; padding-bottom: 30px;"><input
					type="checkbox" id="ck0" name="category_name"></td>

			</tr>
		</table>
    </div>
</div>