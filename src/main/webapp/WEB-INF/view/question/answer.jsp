<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${type==10}">
<p style="margin-top: 10px;">
	<input type="radio" name="IS_RIGHT" class="align_middle" value="0">
	<input type="text" name="ITEM_CONTENT_0" value="" 	class="easyui-validatebox span4"  id="0" style="width:90%">
</p>
<p>
	<input type="radio" name="IS_RIGHT" class="align_middle" value="1">
	<input type="text" name="ITEM_CONTENT_1" value="" 	class="easyui-validatebox span4"  id="1" style="width:90%">
</p>
<p>
	<input type="radio" name="IS_RIGHT" class="align_middle" value="2">
	<input type="text" name="ITEM_CONTENT_1" value="" 	class="easyui-validatebox span4"  id="2" style="width:90%">
</p>
<p>
	<input type="radio" name="IS_RIGHT" class="align_middle" value="3">
	<input type="text" name="ITEM_CONTENT_2" value="" 	class="easyui-validatebox span4"  id="3" style="width:90%">
 </p>
</c:if>
<c:if test="${type==20}">
	<p style="margin-top: 10px;">
	<input type="checkbox" name="IS_RIGHT" class="align_middle" value="0" >
	<input type="text" name="ITEM_CONTENT_0" value="" 	class="easyui-validatebox span4"  id="0" style="width:90%" data-options="required:true">
</p>
<p>
	<input type="checkbox" name="IS_RIGHT" class="align_middle" value="1" >
	<input type="text" name="ITEM_CONTENT_1" value="" 	class="easyui-validatebox span4"  id="1" style="width:90%" data-options="required:true">
</p>
<p>
	<input type="checkbox" name="IS_RIGHT" class="align_middle" value="2">
	<input type="text" name="ITEM_CONTENT_1" value="" 	class="easyui-validatebox span4"  id="2" style="width:90%" data-options="required:true">
</p>
<p>
	<input type="checkbox" name="IS_RIGHT" class="align_middle" value="3" >
	<input type="text" name="ITEM_CONTENT_2" value="" 	class="easyui-validatebox span4"  id="3" style="width:90%" data-options="required:true">
 </p>
</c:if>

<c:if test="${type==30}">
<p style="margin-top: 10px;">
	<input type="radio" name="IS_RIGHT" class="align_middle" value="0" >
	<input type="text" name="ITEM_CONTENT_0" value="" 	class="easyui-validatebox span4"  id="0" style="width:90%" data-options="required:true">
</p>
<p>
	<input type="radio" name="IS_RIGHT" class="align_middle" value="1" >
	<input type="text" name="ITEM_CONTENT_1" value="" 	class="easyui-validatebox span4"  id="1" style="width:90%" data-options="required:true">
</p>
</c:if>
<c:if test="${type==40}">
<p style="margin-top: 10px;">
	答案：
</p>
<p>
	<input type="text" name="ITEM_CONTENT_0" value="" 	class="easyui-validatebox span4"  id="1" style="width:90%" data-options="required:true">
</p>
</c:if>
<c:if test="${type==50}">
<p style="margin-top: 10px;">
	答案：
</p>
<p>
	<textarea name="ITEM_CONTENT_0" cols="5" rows="6" id="0" style="width:100%;height: 50px;" data-options="required:true"></textarea>
</p>
</c:if>

