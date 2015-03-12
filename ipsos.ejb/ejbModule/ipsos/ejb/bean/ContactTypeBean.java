package ipsos.ejb.bean;

import ipsos.ejb.entity.ContactType;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class ContactTypeBean
 */
@Stateless
@LocalBean
public class ContactTypeBean {
	@PersistenceContext private EntityManager em;
	
	public void createContactType(ContactType ct) {
		em.persist(ct);
	}
	
	public ContactType readContactType(int ctid) {
		return em.find(ContactType.class, ctid);
	}
	
	public void updateContactType(ContactType ct) {
		ContactType entity = readContactType(ct.getCtid());
		entity.setCtname(ct.getCtname());
	}
	
	public void deleteContactType(int ctid) {
		ContactType entity = readContactType(ctid);
		em.remove(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<ContactType> listContactTypes() {
		return em.createNamedQuery("findAllContactTypes").getResultList();
	}

}
