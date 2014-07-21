package com.fid.collab;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fid.collab.ChartDAO;
import com.fid.collab.ChartBean;

@Path("/chart")
public class ChartResource {

	ChartDAO chartDAO = new ChartDAO();
	ImDAO imDAO = new ImDAO();

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ChartBean> getChartDetails() throws SQLException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		return chartDAO.getAllDetails();
	}

	@Path("/IM")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ImBean> getImTickets() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		return imDAO.getListOfTickets();

	}

	@Path("/ImCount")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ImCountBean> getImCount() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		return imDAO.getTicketCount();
	}
	
	@GET
	@Path("{severity}")
    public List<SeverityCountBean> getSeverityCount(@PathParam("severity") String severity) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
	   return imDAO.getSeverityCount(severity);
    }
}
