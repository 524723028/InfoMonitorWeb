package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DatabaseUtil;

import bean.CommonResponse;



import constants.DBNames;

import net.sf.json.JSONObject;

public class infomoniter_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public infomoniter_servlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
		System.out.println("不支持GET方法;");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request. setCharacterEncoding("UTF-8");
		BufferedReader read = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = read.readLine()) != null) {
			sb.append(line);
		}
		String str = sb.toString();
		String req = URLDecoder.decode(str, "UTF-8");
		System.out.println(req);  
		
		// 第一步：获取 客户端 发来的请求，恢复其Json格式——>需要客户端发请求时也封装成Json格式  
		 JSONObject object = JSONObject.fromObject(req); 
		// requestCode暂时用不上  
	    // 注意下边用到的2个字段名称requestCode、requestParam要和客户端CommonRequest封装时候的名字一致  
	    String requestCode = object.getString("requestCode");  
	    JSONObject requestParam = object.getJSONObject("requestParam");  
	        
	     // 第二步：将Json转化为别的数据结构方便使用或者直接使用（此处直接使用），进行业务处理，生成结果  
	     // 拼接SQL查询语句 
	    String sql_select = String.format("SELECT * FROM %s WHERE PhoneModel='%s'", 
	    		 DBNames.Table_Infomonitor,   
	                requestParam.getString("PhoneModel"));
	    System.out.println(sql_select);  
	    
	    // 自定义的结果信息类  
	    CommonResponse res = new CommonResponse(); 
	    try {
	    	ResultSet result_select = DatabaseUtil.query(sql_select); // 数据库查询操作
	    	
	    	if (result_select.next()) {
	    		String sql_update = String.format("UPDATE %s SET " +
	    				"PhoneImei = '%s', " +
	    				"time = '%s', " +
	    				"CpuVersion = '%s'," +
	    				"CpuNumCore = '%s'," +
	    				"CpuUsagePer = '%s'," +
	    				"CpuMaxFreq = '%s'," +
	    				"CpuMinFreq = '%s'," +
	    				
	    				"AndroidVersion = '%s'," +
	    				"battery = '%s'," +	
	    				
						"SdTotalSize = '%s'," +
						"SdFreeSize = '%s'," +	
						
						"RamTotalSize = '%s'," +
						"RamUsedSize = '%s'," +
						"RamFreeSize = '%s'," +
						"RamAverageUsed = '%s'," +	
						
						"GpsLongitude = '%s'," +
						"GpsLatitude = '%s'," +	
						
						"NetType = '%s'," +
						"TotalRxBytes = '%s'" +		
						
	    				"WHERE PhoneModel= '%s'",
	    				DBNames.Table_Infomonitor,
	    				requestParam.getString("PhoneImei"),
	    				requestParam.getString("time"),
	    				requestParam.getString("CpuVersion"),
	    				requestParam.getString("CpuNumCore"),
	    				requestParam.getString("CpuUsagePer"),
	    				requestParam.getString("CpuMaxFreq"),
	    				requestParam.getString("CpuMinFreq"),
	    				
	    				requestParam.getString("AndroidVersion"),
	    				requestParam.getString("battery"),
	    				
	    				requestParam.getString("SdTotalSize"),
	    				requestParam.getString("SdFreeSize"),
	    				
	    				requestParam.getString("RamTotalSize"),
	    				requestParam.getString("RamUsedSize"),
	    				requestParam.getString("RamFreeSize"),
	    				requestParam.getString("RamAverageUsed"),
	    				
	    				requestParam.getString("GpsLongitude"),
	    				requestParam.getString("GpsLatitude"),
	    				
	    				requestParam.getString("NetType"),
	    				requestParam.getString("TotalRxBytes"),
	    				
	    				requestParam.getString("PhoneModel"));
	    		System.out.println(sql_update);  
	    		
	    				//int result_update = DatabaseUtil.update(sql_update); // 数据库更新操作
	    				//System.out.println("更新成功了吗？" + result_update );  
	    	
	    		if (DatabaseUtil.update(sql_update) > 0) {
	    			res.setResult("111", "更新数据成功"); 
		    		//res.getProperty().put("custId", result.getString("_id"));
	    		}else {
	    			res.setResult("000", "更新数据失败"); 
	    		}
	    		
	    		
	    	}else {
	    		String sql_insert = String.format("INSERT INTO %s " +
	    				"(PhoneModel,PhoneImei, time, CpuVersion, CpuNumCore, CpuUsagePer, CpuMaxFreq, CpuMinFreq, " +
	    				"AndroidVersion, battery, " +
	    				"SdTotalSize, SdFreeSize, " +
	    				"RamTotalSize, RamUsedSize, RamFreeSize, RamAverageUsed, " +
	    				"GpsLongitude, GpsLatitude, " +
	    				"NetType, TotalRxBytes) " +
	    				"VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
	    				DBNames.Table_Infomonitor,
	    				requestParam.getString("PhoneModel"),
	    				requestParam.getString("PhoneImei"),
	    				requestParam.getString("time"),
	    				requestParam.getString("CpuVersion"),
	    				requestParam.getString("CpuNumCore"),
	    				requestParam.getString("CpuUsagePer"),
	    				requestParam.getString("CpuMaxFreq"),
	    				requestParam.getString("CpuMinFreq"),
	    				
	    				requestParam.getString("AndroidVersion"),
	    				requestParam.getString("battery"),
	    				
	    				requestParam.getString("SdTotalSize"),
	    				requestParam.getString("SdFreeSize"),
	    				
	    				requestParam.getString("RamTotalSize"),
	    				requestParam.getString("RamUsedSize"),
	    				requestParam.getString("RamFreeSize"),
	    				requestParam.getString("RamAverageUsed"),
	    				
	    				requestParam.getString("GpsLongitude"),
	    				requestParam.getString("GpsLatitude"),
	    				
	    				requestParam.getString("NetType"),
	    				requestParam.getString("TotalRxBytes"));
	    		
	    		//DatabaseUtil.update(sql_insert); // 数据库插入操作
	    		//System.out.println("插入成功了吗？"); 
	    		
	    		if (DatabaseUtil.update(sql_insert) > 0) {
	    			res.setResult("111", "上传数据成功"); 
		    		//res.getProperty().put("custId", result.getString("_id"));
	    		}else {
	    			res.setResult("000", "上传数据失败"); 
	    		}
	    			
	    	}
	    }catch (SQLException e) {  
            res.setResult("300", "出现错误");  
            e.printStackTrace();  
        }  
	    
	 // 第三步：将结果封装成Json格式准备返回给客户端，但实际网络传输时还是传输json的字符串  
        // 和我们之前的String例子一样，只是Json提供了特定的字符串拼接格式  
        // 因为服务端JSon是用到经典的第三方JSon包，功能强大，不用像Android中那样自己手动转，直接可以从Bean转到JSon格式  
        String resStr = JSONObject.fromObject(res).toString();  
        System.out.println(resStr);  
        response.setContentType("text/html;charset=utf-8"); // 设置响应报文的编码格式
        response.getWriter().append(resStr).flush();  
	    
	        
	}
	

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
