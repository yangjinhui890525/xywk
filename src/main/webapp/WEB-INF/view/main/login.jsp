<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>系统登录</title>
<%@include file="/commons/include.jsp"%>
<script type="text/javascript">
	$(function() {

		var loginFun = function() {
			var $form = $("#loginForm");//选中的tab里面的form
			if ($form.form('validate')) {
				$('#loginBtn').linkbutton('disable');
				$.post(fq.contextPath + '/admin/login', $form.serialize(), function(result) {
					if (result.status == 200) {
						location.replace(fq.contextPath + '/system/main');
					} else {
						var msg = "用户无效";
						if (result.status == 400){
							msg = "用户不存在";
						}
						if (result.status == 401){
							msg = "密码错误";
						}
						if (result.status == 402){
							msg = "用户已过期";
						}
						if (result.status == 403){
							msg = "用户无效";
						}
						if (result.status == 405){
							msg = "无权限登陆";
						}
						$.messager.alert('提示',msg, 'error', function() {
							$('#loginBtn').linkbutton('enable');
						});
					}
				}, 'json');
			}
		};

		$('#loginDialog').show().dialog({
			modal : false,
			closable : false,
			iconCls : 'ext-icon-lock_open',
			buttons : [ {
				text : '重置',
				width:80,
				handler : function() {
					clearForm();
				}
			}, {
				id : 'loginBtn',
				text : '登录',
				width:80,
				handler : function() {
					loginFun();
				}
			} ],
			onOpen : function() {
				$('form :input:first').focus();
				$('form :input').keyup(function(event) {
					if (event.keyCode == 13) {
						loginFun();
					}
				});
			}
		});

	});
	
	function clearForm(){
		$('#loginForm').form('clear');
	}
</script>
</head>
<body>
	<div id="loginDialog" title="系统登录" style="display: none; width: 320px; height: 180px; overflow: hidden;">
				<form method="post" class="form" id="loginForm">
					<table class="table" style="width: 100%; height: 100%;margin-top: 20px;">
						<tr>
							<th width="50">用户名</th>
							<td><input name="username" class="easyui-validatebox" data-options="required:true" style="width: 210px;" /></td>
						</tr>
						<tr>
							<th>密码</th>
							<td><input name="password" type="password" class="easyui-validatebox" data-options="required:true"  style="width: 210px;" /></td>
						</tr>
					</table>
				</form>
	</div>
</body>
</html>