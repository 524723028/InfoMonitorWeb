package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.PageBean;

import com.mysql.jdbc.Connection;



public class InfoDao {
	public ResultSet infoList(Connection con,PageBean pageBean)throws Exception{
		StringBuffer sb=new StringBuffer("select * from tb_infomonitor");
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	public int infoCount(Connection con)throws Exception{
		String sql="select count(*) as total from tb_infomonitor";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}

}
