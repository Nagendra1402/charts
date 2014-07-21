package com.fid.collab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImDAO {
	Connection con;

	private void connectionHelper() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {

		String url = "jdbc:oracle:thin:@//opitwqwlk1.fmr.com:1521/DIPIT_Q1";
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		con = null;
		con = DriverManager.getConnection(url, "RPTSMREAD", "READSMRPT");
	}
	
	public List<ImBean> getListOfTickets() throws SQLException {
		
		List<ImBean> imList=new ArrayList<ImBean>();
		
		String imQuery="select P_NUMBER,ASSIGNMENT,BRIEF_DESCRIPTION,SEVERITY from PROBSUMMARY where ASSIGNMENT in ('wireless','ecawebservices','palm','cfu-martini','ibgfits','sfdb_support') and (trunc(sysdate)-trunc(open_time)) <= 1";
		try {
			connectionHelper();

		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(imQuery);
		while(rs.next()){
			imList.add(processRow(rs));
			System.out.println(processRow(rs));
		}
		}
		catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			con.close();
		}
		return imList;
		
	}
	
	public List<ImCountBean> getTicketCount() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		List<ImCountBean> imCount=new ArrayList<ImCountBean>();
		
		String imQuery="select count(*) as IMCOUNT,ASSIGNMENT from PROBSUMMARY  where ASSIGNMENT in ('wireless','ecawebservices','palm','cfu-martini','ibgfits','sfdb_support')and (trunc(sysdate)-trunc(open_time)) <= 1 group by ASSIGNMENT";
		connectionHelper();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(imQuery);
		while(rs.next()){
			imCount.add(processCount(rs));
		}
		con.close();
		return imCount;
		
	}
	
	public List<SeverityCountBean> getSeverityCount(String severity) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		List<SeverityCountBean> severityCountList = new ArrayList<SeverityCountBean>();
		
		String severityQuery="select count(*) as SEVERITYCOUNT,ASSIGNMENT from PROBSUMMARY  where SEVERITY = '"+severity+"' and ASSIGNMENT in ('wireless','ecawebservices','palm','cfu-martini','ibgfits','sfdb_support') and (trunc(sysdate)-trunc(open_time)) <= 1 group by ASSIGNMENT";
		connectionHelper();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(severityQuery);
		while(rs.next()){
			severityCountList.add(processServerity(rs));
		}
		
		return severityCountList;
	}

	private SeverityCountBean processServerity(ResultSet rs) throws SQLException {
		SeverityCountBean severityCountBean = new SeverityCountBean();
		severityCountBean.setSeverityCount(rs.getInt(1));
		severityCountBean.setAssignmentGroup(rs.getString(2));
		return severityCountBean;

	}

	private ImCountBean processCount(ResultSet rs) throws SQLException {
	
		ImCountBean imCountBean=new ImCountBean();
		imCountBean.setImCount(rs.getInt(1));
		imCountBean.setAssginmentGroup(rs.getString(2));
		return imCountBean;
	}

	private ImBean processRow(ResultSet rs) throws SQLException {
		ImBean imBean=new ImBean();
		imBean.setImNumber(rs.getString(1));
		imBean.setAssignmentGroup(rs.getString(2));
		imBean.setDescription(rs.getString(3));
		imBean.setSeverity(rs.getString(4));
		return imBean;
	}
	
}
