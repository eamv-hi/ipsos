package ipsos.web;

import ipsos.ejb.bean.ContactBean;
import ipsos.ejb.entity.Contact;
import ipsos.ejb.entity.Space;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

@Named
@SessionScoped
public class ContactOverviewView implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB private ContactBean ejb;
	@Inject ContactDetailView detail;
	private Logger logger = Logger.getLogger(ContactOverviewView.class);
	private List<ContactWrapper> contacts;
	private Space space;
	private ContactWrapper selectedContact;
	
	public void init() {
		List<Contact> contacts = ejb.listContacts(space.getSpaceid());
		this.contacts = new ArrayList<ContactWrapper>();
		int i=0;
		for (Contact contact : contacts) {
			ContactWrapper cw = new ContactWrapper();
			cw.setId(i++);
			cw.setContact(contact);
			this.contacts.add(cw);
		}
	}

	public String view() {
		logger.info("Selected contact : " + selectedContact);
		if (this.selectedContact != null && detail != null) {
			detail.setSpace(this.space);
			detail.setEmail(this.selectedContact.getContact().getEmail());
			detail.setCt(this.selectedContact.getContact().getCttype().getCtname());
			detail.setTp(this.selectedContact.getContact().getTp().getTpname());
			detail.setEdit(true);
			detail.init();
			this.selectedContact = null;
			return "/contactdetail.xhtml";
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No row selected"));
		return null;
	}

	public String create() {
		if (detail != null) {
			detail.setSpace(this.space);
			detail.setEmail(null);
			detail.setCt(null);
			detail.setTp(null);
			this.selectedContact = null;
			detail.setEdit(false);
			detail.init();
			this.selectedContact = null;
			return "/contactdetail.xhtml";
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Something very wrong"));
		return null;
	}

	public List<ContactWrapper> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactWrapper> contacts) {
		this.contacts = contacts;
	}

	public ContactWrapper getSelectedContact() {
		return selectedContact;
	}

	public void setSelectedContact(ContactWrapper selectedContact) {
		this.selectedContact = selectedContact;
	}

	public Space getSpace() {
		return space;
	}

	public void setSpace(Space space) {
		this.space = space;
	}
	



	
}
