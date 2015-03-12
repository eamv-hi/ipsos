package ipsos.web.rest;

import ipsos.ejb.bean.SpaceBean;
import ipsos.ejb.entity.Space;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/space")
public class SpaceRestAccess {
	@EJB private SpaceBean ejb;
	
	@GET
	@Path("listtopspaces")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Space> listSpaces() {
		return ejb.listTopSpaces();
	}

	@GET
	@Path("readspace/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	public Space readSpace(@PathParam("key") String spaceid) {
		return ejb.readSpace(spaceid);
	}

	@GET
	@Path("listchildren/{parentid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Space> listChildren(@PathParam("parentid") String parentid) {
		return ejb.listChildren(parentid);
	}


}
