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
		System.out.println("��֧��GET����;");
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
		
		// ��һ������ȡ �ͻ��� ���������󣬻ָ���Json��ʽ����>��Ҫ�ͻ��˷�����ʱҲ��װ��Json��ʽ  
		 JSONObject object = JSONObject.fromObject(req); 
		// requestCode��ʱ�ò���  
	    // ע���±��õ���2���ֶ�����requestCode��requestParamҪ�Ϳͻ���CommonRequest��װʱ�������һ��  
	    String requestCode = object.getString("requestCode");  
	    JSONObject requestParam = object.getJSONObject("requestParam");  
	        
	     // �ڶ�������Jsonת��Ϊ������ݽṹ����ʹ�û���ֱ��ʹ�ã��˴�ֱ��ʹ�ã�������ҵ�������ɽ��  
	     // ƴ��SQL��ѯ��� 
	    String sql_select = String.format("SELECT * FROM %s WHERE PhoneModel='%s'", 
	    		 DBNames.Table_Infomonitor,   
	                requestParam.getString("PhoneModel"));
	    System.out.println(sql_select);  
	    
	    // �Զ���Ľ����Ϣ��  
	    CommonResponse res = new CommonResponse(); 
	    try {
	    	ResultSet result_select = DatabaseUtil.query(sql_select); // ���ݿ��ѯ����
	    	
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
	    		
	    				//int result_update = DatabaseUtil.update(sql_update); // ���ݿ���²���
	    				//System.out.println("���³ɹ�����" + result_update );  
	    	
	    		if (DatabaseUtil.update(sql_update) > 0) {
	    			res.setResult("111", "�������ݳɹ�"); 
		    		//res.getProperty().put("custId", result.getString("_id"));
	    		}else {
	    			res.setResult("000", "��������ʧ��"); 
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
	    		
	    		//DatabaseUtil.update(sql_insert); // ���ݿ�������
	    		//System.out.println("����ɹ�����"); 
	    		
	    		if (DatabaseUtil.update(sql_insert) > 0) {
	    			res.setResult("111", "�ϴ����ݳɹ�"); 
		    		//res.getProperty().put("custId", result.getString("_id"));
	    		}else {
	    			res.setResult("000", "�ϴ�����ʧ��"); 
	    		}
	    			
	    	}
	    }catch (SQLException e) {  
            res.setResult("300", "���ִ���");  
            e.printStackTrace();  
        }  
	    
	 // ���������������װ��Json��ʽ׼�����ظ��ͻ��ˣ���ʵ�����紫��ʱ���Ǵ���json���ַ���  
        // ������֮ǰ��String����һ����ֻ��Json�ṩ���ض����ַ���ƴ�Ӹ�ʽ  
        // ��Ϊ�����JSon���õ�����ĵ�����JSon��������ǿ�󣬲�����Android�������Լ��ֶ�ת��ֱ�ӿ��Դ�Beanת��JSon��ʽ  
        String resStr = JSONObject.fromObject(res).toString();  
        System.out.println(resStr);  
        response.setContentType("text/html;charset=utf-8"); // ������Ӧ���ĵı����ʽ
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
