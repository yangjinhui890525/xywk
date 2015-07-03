<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/commons/include.jsp"%>
<script type="text/javascript">
		
	var submitNow = function($dialog, $grid, $pjq) {
		var url;

		url = fq.contextPath + '/xy/category/editCategory_ok';
	
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
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
<form method="post" id="form">
<p>
    <label for="name" class="ui-label">资源分类名称:</label>
	<input type="text" value="${category['NAME']}"  class="easyui-validatebox ui-input" type="text" name="name" data-options="required:true">
</p>
<p>
    <label for="ORDER_NUM" class="ui-label">优先级别:</label>
	<input type="text" value="${category['ORDER_NUM']}" class="easyui-validatebox ui-input" type="text" name="order_num" data-options="required:true">
</p>
<input type="hidden" value="${category['PID']}" name="pid">
</form>
</div>


</div>