package ipsos.ejb.bean;

import ipsos.ejb.entity.TouchPoint;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class TouchPointBean
 */
@Stateless
@LocalBean
public class TouchPointBean {
	@PersistenceContext private EntityManager em;
	
	public void createTouchPoint(TouchPoint tp) {
		em.persist(tp);
	}
	
	public TouchPoint readTouchPoint(int tpid) {
		return em.find(TouchPoint.class, tpid);
	}
	
	public void updateTouchPoint(TouchPoint tp) {
		TouchPoint entity = readTouchPoint(tp.getTpid());
		entity.setTpname(tp.getTpname());
	}
	
	public void deleteTouchPoint(int tpid) {
		TouchPoint entity = readTouchPoint(tpid);
		em.remove(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<TouchPoint> listTouchPoints() {
		return em.createNamedQuery("findAllTouchPoints").getResultList();
	}

}
