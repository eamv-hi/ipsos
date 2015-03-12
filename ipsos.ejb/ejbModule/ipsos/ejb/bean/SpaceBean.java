package ipsos.ejb.bean;

import ipsos.ejb.entity.Space;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class GlobalBean
 */
@Stateless
@LocalBean
public class SpaceBean {
	@PersistenceContext private EntityManager em;
	
	public void createSpace(Space space) {
		em.persist(space);
	}
	
	public Space readSpace(String spaceid) {
		return em.find(Space.class, spaceid);
	}
	
	public void updateSpace(Space space) {
		Space entity = readSpace(space.getSpaceid());
		entity.setSpacename(space.getSpacename());
		entity.setSpacetype(space.getSpacetype());
		entity.setParent(space.getParent());
	}
	
	public void deleteSpace(String spaceid) {
		Space entity = readSpace(spaceid);
		em.remove(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<Space> listTopSpaces() {
		return em.createNamedQuery("findAllTopSpaces").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Space> listChildren(String parentid) {
		Query query = em.createNamedQuery("findAllChildren");
		query.setParameter("parentid", parentid);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Space> listSpaces(String search) {
		Query query = em.createNamedQuery("findSpaces");
		query.setParameter("search", "%" + search.toUpperCase() + "%");
		return query.getResultList();
	}


}
