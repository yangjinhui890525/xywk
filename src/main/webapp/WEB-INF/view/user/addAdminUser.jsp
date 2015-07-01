<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/commons/include.jsp"%>
<script type="text/javascript">
	
	var submitNow = function($dialog, $grid, $pjq) {
		var url;

		url = fq.contextPath + '/xy/user/saveAdminUser';
	
		$.post(url, fq.serializeObject($('form')), function(result) {
			parent.fq.progressBar('close');//关闭上传进度条
			if (result.success) {
				$pjq.messager.alert('提示','操作成功', 'info');
				$grid.datagrid('reload');
				$dialog.dialog('destroy');
			} else {
				$pjq.messager.alert('提示','操作失败', 'error');
			}
		}, 'json');
	};
	
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			submitNow($dialog, $grid, $pjq);
		}
	};
	
	
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>管理员信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>用户名</th>
					<td><input name="USERNAME"  type="text" class="easyui-validatebox" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<th>密码</th>
					<td><input name="PASSWORD" type="password" class="easyui-validatebox" data-options="required:true"/>
					</td>
				</tr>
				<tr>
				<th>姓名</th>
				<td><input name="TRUENAME" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>手机号码</th>
					<td>
						<input name="MPHONE" type="text"/>
					</td>
				</tr>
				
			</table>
		</fieldset>
	</form>
</body>
</html>