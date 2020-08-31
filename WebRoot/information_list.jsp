<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bean.information" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>手机信息列表管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<!-- 引入JQuery -->
	<script type="text/javascript" src="jquery-easyui-1.5.4.5/jquery.min.js"></script>
	<!-- 引入EasyUI -->
	<script type="text/javascript" src="jquery-easyui-1.5.4.5/jquery.easyui.min.js"></script>
	<!-- 引入EasyUI的中文国际化js，让EasyUI支持中文 -->
	<script type="text/javascript" src="jquery-easyui-1.5.4.5/locale/easyui-lang-zh_CN.js"></script>
	<!-- 引入EasyUI的样式文件-->
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.4.5/themes/default/easyui.css">
	<!-- 引入EasyUI的图标样式文件-->
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.4.5/themes/icon.css">

  </head>
  <body>
  	<table id="dg" title="手机信息" class="easyui-datagrid" fitColumns="true"
	 pagination="true" rownumbers="true" url="informationServlet">
		<thead>
			<tr>
				<th field="PhoneModel" width="70">手机型号</th>
				<th field="PhoneImei" width="90">手机IMEI号</th>
				<th field="time" width="110">监测时间</th>
				<th field="CpuVersion" width="130">CPU型号</th>
				<th field="CpuUsagePer" width="70">CPU使用率(%)</th>
				<th field="battery" width="40">电量(%)</th>
				<th field="SdFreeSize" width="40">剩余内存</th>
				<th field="RamAverageUsed" width="80">运行内存使用率(%)</th>
				<th field="GpsLongitude" width="70">位置经度</th>
				<th field="GpsLatitude" width="80">位置纬度</th>
			</tr>
		</thead>
	</table>
 
  </body>
</html>
