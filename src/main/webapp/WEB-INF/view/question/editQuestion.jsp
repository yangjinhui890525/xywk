<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/commons/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
var submitNow = function($dialog, $grid, $pjq) {
	var url;

	url = fq.contextPath + '/xy/question/saveQuestion';

	$.post(url, getData(fq.serializeObject($('form'))), function(result) {
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
function ChangeType(type)
{
	$.ajax({
		type:"post",
		url:fq.contextPath+"/xy/question/getAnster?type="+type+"&QUESTION_ID="+$("#ID").val(),
		success:function(data)
		{
			$("#answer").html(data);
		}
	});
		
}
$(document).ready(function(){
	var num=${question.QUE_TYPE};
	ChangeType(num);
	$('#QUE_TYPE').combobox({
	    onChange:function(newValue,oldValue){
	    	ChangeType(newValue);
	    }
	});
});
function getData(data)
{
	data['CONTENT_TEXT']=CKEDITOR.instances.CONTENT_TEXT.getData();
	return data;
}
</script>
</head>
<body>
<div>
	<form method="post" class="form">
	<input type="hidden" value="${question['CHAPTER_UNIT_IDs']}" name="CHAPTER_UNIT_IDs">
	<input type="hidden" value="${question['COURSE_ID']}" name="COURSE_ID">
	<input type="hidden" value="${question.ID}" name="ID" ID="ID">
	<input type="hidden" value="${question.CONTENT_URL}" name="CONTENT_URL" >
		<fieldset>
			<legend>题目信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					题型:<input name="QUE_TYPE" id="QUE_TYPE" style="width: 90px;" class="easyui-combobox" data-options="valueField:'DEF_VALUE',textField:'NAME'" url="${pageContext.request.contextPath}/xy/dic/type?DIC_TYPE=QUE_TYPE" value="${question.QUE_TYPE}"/>
					 难度:<input name="QUE_DIFFCULT" style="width: 90px;" class="easyui-combobox" data-options="valueField:'DEF_VALUE',textField:'NAME'" url="${pageContext.request.contextPath}/xy/dic/type?DIC_TYPE=QUE_DIFFCULT" value="${question.QUE_DIFFCULT}"/>
					分数:<input name="QUE_SCORE" style="width: 90px;" class="easyui-combobox" data-options="valueField:'DEF_VALUE',textField:'NAME'" url="${pageContext.request.contextPath}/xy/dic/type?DIC_TYPE=QUE_SCORE" value="1"/>
					  所属分类:<input name="QUE_CLASS" style="width: 90px;" class="easyui-combobox" data-options="valueField:'DEF_VALUE',textField:'NAME'" url="${pageContext.request.contextPath}/xy/dic/type?DIC_TYPE=QUE_CLASS" value="1"/>
					试题类型:<input name="QUE_CONTENT_TYPE" style="width: 90px;" class="easyui-combobox" data-options="valueField:'DEF_VALUE',textField:'NAME'" url="${pageContext.request.contextPath}/xy/dic/type?DIC_TYPE=QUE_CONTENT_TYPE" value="1"/>
				</tr>
				<tr>
					<ckeditor:editor editor="CONTENT_TEXT" basePath="../../commons/ckeditor/" value="${question.CONTENT_TEXT}"/>
				</tr>
				<tr id="answer">
				</tr>
			</table>
		</fieldset>
	</form>
</div>
</body>
</html>