package ipsos.web;

import ipsos.ejb.entity.Contact;

public class ContactWrapper {
	private int id;
	private Contact contact;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
