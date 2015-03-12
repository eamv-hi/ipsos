package ipsos.web.rest;

import ipsos.ejb.bean.TouchPointBean;
import ipsos.ejb.entity.TouchPoint;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/touchpoint")
public class TouchPointRestAccess {
	@EJB private TouchPointBean ejb;
	
	@GET
	@Path("listtouchpoints")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TouchPoint> listTouchPoints() {
		return ejb.listTouchPoints();
	}

	@GET
	@Path("readtouchpoint/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	public TouchPoint readTouchPoint(@PathParam("key") int tpid) {
		return ejb.readTouchPoint(tpid);
	}


}
