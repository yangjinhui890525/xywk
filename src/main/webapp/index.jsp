<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ page language="java" import="com.fredck.FCKeditor.*" %>
<html>
<script type="text/javascript" src="commons/ckeditor/ckeditor.js"></script>
<body>
<form action="xy/chapterunit/create" method="post">
			<p>
				<label for="editor2">Editor 1:</label>
	<!-- 			<textarea cols="80" id="editor2" name="editor2" rows="10"></textarea> -->
				<ckeditor:editor editor="editor2" basePath="/commons/ckeditor/" value=" "></ckeditor:editor>
			</p>
			<p>
				<input type="submit" value="Submit" />
			</p>
		</form>
		<ckeditor:replace replace="editor2" basePath="commons/ckeditor/" />
		<!-- <div align="center"><input type="submit" value="add " name="cmdok">&nbsp;<input type="reset" value="ret" name="cmdcancel"></div> -->
</body>	
<script type="text/javascript">
	
</script>
</html>
