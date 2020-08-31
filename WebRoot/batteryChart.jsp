<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>ECharts</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	 <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="height:500px"></div>
    <!-- ECharts单文件引入 -->
    <script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
     <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
       <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
       <script type="text/javascript" src="<%=basePath%>js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript">
    
    var myChart;
    function initChart(){
    	
    	 // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载
                'echarts/chart/line'   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                myChart = ec.init(document.getElementById('main')); 
                
                var option = {
                	title: {
                		text:'设备电量信息'
                	},
                    tooltip: {//图标悬停的提示内容
                    	trigger: 'axis',
                        show: true,
                        formatter: '{c}%'
                    },
                    legend: {
                        data:['电量']
                    },
                    toolbox: {
        				show : true,
        				feature : {
            				mark : {show: true},
            				dataView : {show: true, readOnly: false},
            				magicType : {show: true, type: ['line', 'bar']},
            				restore : {show: true},
            				saveAsImage : {show: true}
        				}
    				},
                    
                    xAxis : [
                        {
                            type : 'category',
                            data :[20,30,40],
                            axisLabel:{  
                         		interval:0,//横轴信息全部显示  
		                        //rotate:-30,//-30度角倾斜显示  
		                    }  
                        
                        }
                    ],
                    
                    yAxis : [
                        {
                        axisLabel: {
                			formatter: '{value} %'
            				},
                        
                            type : 'value'
                            
                        }
                    ],
                    series : [
                        {
                            "name":"电量",
                            "type":"bar",
                            "data":[20,30,40],
                            
                            markPoint : {
                				data : [
                    				{type : 'max', name: '最大值'},
                    				{type : 'min', name: '最小值'}
               				 	]
            				},
            				
            				markLine : {
                				data : [
                    				{type : 'average', name: '平均值'}
                				]
           					 },
            				
                            itemStyle: {
            					normal: {
            						color:'#00DDDD',
                					label: {
                    					show: true,
                    					positiong: 'top',
                    					formatter: '{c}%'
               								 }
            							}
        							} 
                        }      
                    ]
                    
                };
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
                
                //通过Ajax获取数据
                $.ajax({
                    type : "post",
                    async : false, //同步执行
                    url : "http://localhost:8080/Information_web/chartServlet?flag=getData",
                    data : {},
                    dataType : "json", //返回数据形式为json
                    success : function(result) {
                    if (result) {
                    	//var t = ""+ result.yAxisData;
                          //alert(result.battery_yAxisData);
                         myChart.setOption({        //加载数据图表
                         
                         xAxis: [{
                                 // 根据名字对应到相应的系列
                                 name: '手机型号',
                                 data: result.xAxisData
                             }],
                         
                         
                              series: [{
                                 // 根据名字对应到相应的系列
                                 name: '电量',
                                 data: result.battery_yAxisData
                             }] 
                             
                         });
                    }
                },
                error : function(errorMsg) {
                    alert("不好意思,图表请求数据失败啦!");
                    myChart.hideLoading();
                }
               });
                
               
                
            }//function(ec)的另外一个花括号
        );//require的另外一个括号
     
    }//function(initChart)的另外一个花括号
    

        $(function (){
        	initChart();
        	//getData();
        	
        });
       
    </script>
  	
  </body>
</html>
