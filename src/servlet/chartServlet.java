package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DbUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import bean.information;

import com.mysql.jdbc.Connection;

public class chartServlet extends HttpServlet {
	DbUtil dbUtil=new DbUtil();
	public chartServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
		
		
		//请求转到information_list.jsp
		//request.getRequestDispatcher("Chart.jsp").forward(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con=null;
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("utf-8");  
			response.setCharacterEncoding("utf-8");   
			String flag = request.getParameter("flag");
			if( "getData".equals(flag) ){
				//Class.forName("com.mysql.jdbc.Driver");
				//String url = "jdbc:mysql://localhost:3306/infomonitor_sql?characterEncoding=UTF-8"; // 数据库的Url
				//Connection mConnection = (Connection) DriverManager.getConnection(url, "root", "590058920");
				con=(Connection) dbUtil.getCon();
				Statement stmt = con.createStatement();
				
				String query_sql = "select * from tb_infomonitor";
				ResultSet rs = stmt.executeQuery(query_sql);
				
			
				List<String> xAxisData = new ArrayList<String>();  
				List<String> battery_yAxisData = new ArrayList<String>();  
				List<String> CpuUsagePer_yAxisData = new ArrayList<String>();  
				List<String> RamUsagePer_yAxisData = new ArrayList<String>();  
				while(rs.next()) {
					xAxisData.add(rs.getString("PhoneModel"));
					battery_yAxisData.add(rs.getString("battery"));
					CpuUsagePer_yAxisData.add(rs.getString("CpuUsagePer"));
					RamUsagePer_yAxisData.add(rs.getString("RamAverageUsed"));
					
				}
				
				JSONObject jsonObject = new JSONObject(); 
				jsonObject.put("xAxisData", xAxisData); 
				jsonObject.put("battery_yAxisData", battery_yAxisData); 
				jsonObject.put("CpuUsagePer_yAxisData", CpuUsagePer_yAxisData); 
				jsonObject.put("RamUsagePer_yAxisData", RamUsagePer_yAxisData); 
				System.out.println(jsonObject);
				
				PrintWriter out=response.getWriter();
				//发送给前台  
				out.write(jsonObject.toString());  
				out.flush();
				out.close();
				
				//request.setAttribute("data", jsonObject);
				rs.close();
				stmt.close();
				con.close();
			}/*else if("forbaterry".equals(flag)){
				request.getRequestDispatcher("batteryChart.jsp").forward(request, response);
			}else if("forCpuUsagePer".equals(flag)){
				request.getRequestDispatcher("CpuUsagePerChart.jsp").forward(request, response);
			}else if("forRamUsagePer".equals(flag)){
				request.getRequestDispatcher("RamUsagePerChart.jsp").forward(request, response);
			}
			
			*/
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
		
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
