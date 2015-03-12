package ipsos.web.rest;

import ipsos.ejb.bean.ContactBean;
import ipsos.ejb.entity.Contact;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/contact")
public class ContactRestAccess {
	@EJB private ContactBean ejb;
	
	@GET
	@Path("listspace/{space}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contact> listContacts(@PathParam("space") String spaceid) {
		return ejb.listContacts(spaceid);
	}

	@GET
	@Path("listspace/{space}/{tpid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contact> listContactsTp(@PathParam("space") String spaceid, @PathParam("tpid") int tpid) {
		return ejb.listContacts(spaceid, tpid);
	}

	@GET
	@Path("listspace/{space}/{tpid}/{ctid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contact> listContactsTpCt(@PathParam("space") String spaceid, @PathParam("tpid") int tpid, @PathParam("ctid") int ctid) {
		return ejb.listContacts(spaceid, tpid, ctid);
	}

}
