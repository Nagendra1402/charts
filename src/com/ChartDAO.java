package com.fid.collab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fid.collab.ChartBean;

public class ChartDAO {
	 
	Connection con;
	private void connectionHelper() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{

	String url = "jdbc:mysql://localhost:3306/collab";
	Class.forName("com.mysql.jdbc.Driver").newInstance();
    con=null;
    con = DriverManager.getConnection(url, "root", "");
	}  
	@SuppressWarnings("unchecked")
	public List<ChartBean> getAllDetails() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		List<ChartBean> list = new ArrayList<ChartBean>();
		
	    String sql = "SELECT * FROM incident";
	    
	    try {
	    	 connectionHelper();
		     Statement s = con.createStatement();
             ResultSet rs = s.executeQuery(sql);
             while (rs.next()){
        	 list.add(processRow(rs));
             }
             
	       } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				con.close();
			}
	        return list;

}
	protected ChartBean processRow(ResultSet rs) throws SQLException
	{
		ChartBean chartDetails = new ChartBean();
		
		chartDetails.setGroupName(rs.getString(1));
		chartDetails.setTotal(rs.getInt(2));
		chartDetails.setCmCount(rs.getInt(3));
		
		return chartDetails;
   
	}
	
}
