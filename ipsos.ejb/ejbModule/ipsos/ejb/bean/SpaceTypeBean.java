package ipsos.ejb.bean;

import ipsos.ejb.entity.SpaceType;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class SpaceTypeBean
 */
@Stateless
@LocalBean
public class SpaceTypeBean {
	@PersistenceContext private EntityManager em;
	
	public void createSpaceType(SpaceType st) {
		em.persist(st);
	}
	
	public SpaceType readSpaceType(int stid) {
		return em.find(SpaceType.class, stid);
	}
	
	public void updateSpaceType(SpaceType st) {
		SpaceType entity = readSpaceType(st.getStid());
		entity.setStname(st.getStname());
	}
	
	public void deleteSpaceType(int stid) {
		SpaceType entity = readSpaceType(stid);
		em.remove(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpaceType> listSpaceTypes() {
		return em.createNamedQuery("findAllSpaceTypes").getResultList();
	}

}
