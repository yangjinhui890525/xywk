<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.xy.vo.LoginInfo"%>
<%
	String contextPath = request.getContextPath();
	LoginInfo sessionInfo = (LoginInfo) session.getAttribute("managerLoginInfo");
	if(sessionInfo == null){
		sessionInfo = new LoginInfo();
		sessionInfo.setTruename("访客");
	}
	String managerContextPath = "";
%>
<script type="text/javascript" charset="utf-8">
	var managerContextPath = '<%=managerContextPath%>';
	var userId = '<%=sessionInfo.getUserId()%>';
	var lockWindowFun = function() {
		$.post(fq.contextPath + '/'+managerContextPath+'/logout', function(result) {
			$('#loginDialog').dialog('open');
		}, 'json');
	};
	var logoutFun = function() {
		$.post(fq.contextPath + '/'+managerContextPath+'/logout', function(result) {
			location.replace(fq.contextPath + '/'+managerContextPath);
		}, 'json');
	};
	var showMyInfoFun = function() {
		var dialog = parent.fq.modalDialog({
			title : '我的信息',
			url : fq.contextPath + '/system/user/showUser?userId='+userId
		});
	};
</script>
<div id="sessionInfoDiv" style="position: absolute; right: 10px; top: 5px;">
	<%
		if (sessionInfo != null) {
			//out.print(fq.util.base.StringUtil.formateString("欢迎您，{0}", sessionInfo.getLoginname()));
			out.print("欢迎您，"+sessionInfo.getTruename());
		}
	%>
</div>
<div style="position: absolute; right: 0px; bottom: 0px;">
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_pfMenu',iconCls:'ext-icon-rainbow'">更换皮肤</a> <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_kzmbMenu',iconCls:'ext-icon-cog'">控制面板</a> <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_zxMenu',iconCls:'ext-icon-disconnect'">注销</a>
</div>
<div id="layout_north_pfMenu" style="width: 120px; display: none;">
	<div onclick="fq.changeTheme('default');" title="default">default</div>
	<div onclick="fq.changeTheme('gray');" title="gray">gray</div>
	<div onclick="fq.changeTheme('metro');" title="metro">metro</div>
	<div onclick="fq.changeTheme('bootstrap');" title="bootstrap">bootstrap</div>
	<div onclick="fq.changeTheme('black');" title="black">black</div>
	<!-- <div class="menu-sep"></div>
	<div onclick="fq.changeTheme('metro-blue');" title="metro-blue">metro-blue</div>
	<div onclick="fq.changeTheme('metro-gray');" title="metro-gray">metro-gray</div>
	<div onclick="fq.changeTheme('metro-green');" title="metro-green">metro-green</div>
	<div onclick="fq.changeTheme('metro-orange');" title="metro-orange">metro-orange</div>
	<div onclick="fq.changeTheme('metro-red');" title="metro-red">metro-red</div> -->
</div>
<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<div data-options="iconCls:'ext-icon-user_edit'" onclick="$('#passwordDialog').dialog('open');">修改密码</div>
	<div class="menu-sep"></div>
	<div data-options="iconCls:'ext-icon-user'" onclick="showMyInfoFun();">我的信息</div>
</div>
<div id="layout_north_zxMenu" style="width: 100px; display: none;">
	<div data-options="iconCls:'ext-icon-lock'" onclick="lockWindowFun();">锁定窗口</div>
	<div class="menu-sep"></div>
	<div data-options="iconCls:'ext-icon-door_out'" onclick="logoutFun();">退出系统</div>
</div>