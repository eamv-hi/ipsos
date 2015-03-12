package ipsos.ejb.bean;

import ipsos.ejb.entity.Contact;
import ipsos.ejb.entity.ContactId;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class ContactBean
 */
@Stateless
@LocalBean
public class ContactBean {
	@PersistenceContext private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Contact> listContacts(String spaceid) {
		return em.createNamedQuery("findAllContacts").setParameter("spaceid", spaceid).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Contact> listContacts(String spaceid, int tpid) {
		Query query = em.createNamedQuery("findAllContactsTouchpoint");
		query.setParameter("spaceid", spaceid);
		query.setParameter("tpid", tpid);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Contact> listContacts(String spaceid, int tpid, int ctid) {
		Query query = em.createNamedQuery("findAllContactsTouchpointType");
		query.setParameter("spaceid", spaceid);
		query.setParameter("tpid", tpid);
		query.setParameter("ctid", ctid);
		return query.getResultList();
	}

	
	public void createContact(Contact contact) {
		em.persist(contact);
	}

	public Contact readContact(ContactId contactid) {
		return em.find(Contact.class, contactid);
	}
	
	public void deleteContact(ContactId contactid) {
		em.remove(readContact(contactid));
	}


}
