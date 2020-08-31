<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>手机信息管理系统主界面</title>
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
	
	<script type="text/javascript">
		$(function(){
			//数据
			var treeData=[{
				text:"信息列表显示",
				children:[{
					text:"信息列表显示",
					attributes:{
						url:"information_list.jsp"
					}
				}]
		
			},{
				text:"图表分析",
				children:[{
					text:"电量信息图表分析",
					attributes:{
						url:"batteryChart.jsp"
					}
				},{
					text:"CPU使用率图表分析",
					attributes:{
						url:"CpuUsagePerChart.jsp"
					}
				},{
					text:"RAM使用率图表分析",
					attributes:{
						url:"RamUsagePerChart.jsp"
					}
				}]
		
			}];
			
			//实例化数菜单
			$("#tree").tree({
				data:treeData,
				line:true,
				//与openTab有关的方法
				onClick:function(node){
					if(node.attributes){
						openTab(node.text,node.attributes.url);
					}
				}
			});
			
			//新增Tab
			function openTab(text, url) {
				if($("#tabs").tabs('exists',text)){
					$("#tabs").tabs('select',text);
				}else{
					//嵌套frame
					var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src="+url+"></iframe>";
					$("#tabs").tabs('add',{
						title:text,
						//实现Tabs可关闭
						closable:true,
						content:content
					});
				}
				
			}
		});
	
	</script>

  </head>
  
  <body class="easyui-layout">
  	<div region="north" style="height: 80px;background-color: #E0EDFF">
  		<img src="images/main.jpg">
  	</div>
  	
  	<div region="center">
  		<div class="easyui-tabs" fit="true" border="false" id="tabs">
  			<div title="首页">
  				<div align="center" style="padding-top: 100px"><font color="#E0EDFF" size="10">欢迎使用</font></div>
  			</div>
  		</div>
  	</div>
  	
  	<div region="west" style="width: 175px;" title="导航菜单" split="true">
  		<ul id="tree"></ul>
  	</div>
  	<div region="south" style="height: 25px;" align="center">版权所有</div>
  </body>
</html>
