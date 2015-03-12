package ipsos.web.rest;

import ipsos.ejb.bean.ContactTypeBean;
import ipsos.ejb.entity.ContactType;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/contacttype")
public class ContactTypeRestAccess {
	@EJB private ContactTypeBean ejb;
	
	@GET
	@Path("listcontacttypes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ContactType> listContactTypes() {
		return ejb.listContactTypes();
	}

	@GET
	@Path("readcontacttype/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	public ContactType readContactType(@PathParam("key") int ctid) {
		return ejb.readContactType(ctid);
	}


}
