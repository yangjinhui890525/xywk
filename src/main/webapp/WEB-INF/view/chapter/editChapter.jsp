<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include.jsp"%>
<script type="text/javascript">
	
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
		url = fq.contextPath + '/xy/chapter/saveChapter';
		$.post(url, fq.serializeObject($('form')), function(result) {
			parent.fq.progressBar('close');//关闭上传进度条
			if (result.success) {
				$pjq.messager.alert('提示','操作成功', 'info');
				//$grid.datagrid('reload');
				updateTabs();
				//window.parent.initTree($("#COURSE_ID").val());
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
		<input type="hidden" value="${chapter['COURSE_ID']}" name="COURSE_ID" id="COURSE_ID">
		<input type="hidden" value="${chapter['ID']}" name="ID">
		<input type="hidden" value="${chapter['PID']}" name="PID">
		<fieldset>
			<legend>章节信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>章节名称</th>
					<td><input name="NAME"  type="text" class="easyui-validatebox" data-options="required:true" value="${chapter['NAME']}"/>
					</td>
				</tr>
				<tr>
					<th>描述</th>
					<td><input name="DESCRIPTION" type="text" class="easyui-validatebox" data-options="required:true" value="${chapter['DESCRIPTION']}"/>
					</td>
				</tr>
				
			</table>
		</fieldset>
	</form>
</body>
</html>