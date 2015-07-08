<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include.jsp"%>
<script type="text/javascript">
	
	var submitNow = function($dialog, $grid, $pjq) {
		var url;

		url = fq.contextPath + '/xy/course/editCourse_ok';
	
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
<div>
	<form method="post" class="form">
	<input type="hidden" value="${course['ID']}" name="ID">
	<input type="hidden" value="${course['CATEGORY_ID']}" name="CATEGORY_ID">
		<fieldset>
			<legend>资源分类信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>资源科目名称</th>
					<td><input name="NAME"  type="text" class="easyui-validatebox" data-options="required:true" value="${course['NAME']}"/>
					</td>
				</tr>
				<tr>
					<th>描述:</th>
					<td><input name="DESCRIPTION" type="text" class="easyui-validatebox" data-options="required:true" value="${course['DESCRIPTION']}"/>
					</td>
				</tr>
				<tr>
					<th>优先级别:</th>
					<td><input name="ORDER_NUM" type="text" class="easyui-validatebox" data-options="required:true" value="${course['ORDER_NUM']}"/>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</div>
</body>
</html>