package ipsos.web;

import ipsos.ejb.bean.SpaceBean;
import ipsos.ejb.bean.SpaceTypeBean;
import ipsos.ejb.entity.Space;
import ipsos.ejb.entity.SpaceType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

@Named()
@SessionScoped
public class SpaceDetailView implements Serializable {
	private static final long serialVersionUID = 3193006383857093L;
	@EJB private SpaceBean ejb;
	@EJB private SpaceTypeBean stejb;
	@Inject SpaceOverviewView overview;
	private Logger logger = Logger.getLogger(SpaceDetailView.class);
	private String spaceid;
	private String spacename;
	private String spacetype;
	private Space parent;
	private List<String> spacetypes;
	private List<SpaceType> realspacetypes;
	private boolean edit = false;
	
	@PostConstruct
	public void init() {
		realspacetypes = stejb.listSpaceTypes();
		spacetypes = new ArrayList<String>();
		for (SpaceType st : realspacetypes) {
			spacetypes.add(st.getStname());
		}
	}
	
	public void create() {
		logger.info("method create entered");
		try {
			Space space = new Space();
			space.setSpaceid(spaceid);
			space.setSpacename(spacename);
			SpaceType spacetype = null;
			for (SpaceType st : realspacetypes) {
				if (st.getStname().equalsIgnoreCase(this.spacetype)) {
					spacetype = st;
				}
			}
			space.setSpacetype(spacetype);
			space.setParent(parent);
			if (parent != null && parent.getSpacetype().getStid() >= space.getSpacetype().getStid()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Parent " + space.getParent().getSpaceid() + " must be of different type", "Parent " + space.getParent().getSpaceid() + " must be of different type"));
			} else {
				ejb.createSpace(space);
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Space " + spaceid + " created"));
			overview.init();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Space " + spaceid + " allready exists", "Space " + spaceid + " allready exists"));
		}
	}
	public void update() {
		logger.info("method update entered");
		try {
			Space space = new Space();
			space.setSpaceid(spaceid);
			space.setSpacename(spacename);
			SpaceType spacetype = null;
			for (SpaceType st : realspacetypes) {
				if (st.getStname().equalsIgnoreCase(this.spacetype)) {
					spacetype = st;
				}
			}
			space.setSpacetype(spacetype);
			space.setParent(parent);
			if (parent != null && parent.getSpacetype().getStid() >= space.getSpacetype().getStid()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Parent " + space.getParent().getSpaceid() + " must be of different type", "Parent " + space.getParent().getSpaceid() + " must be of different type"));
			} else {
				ejb.updateSpace(space);
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Space " + spaceid + " updated"));
			overview.init();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Space " + spaceid + " does not exist", "Space " + spaceid + " does not exist"));
		}
	}
	public void delete() {
		logger.info("method delete entered");
		try {
			ejb.deleteSpace(spaceid);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Space " + spaceid + " deleted"));
			overview.init();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Space " + spaceid + " does not exist", "Space " + spaceid + " does not exist"));
		}
	}
	
	public boolean isEdit() {
		return edit;
	}
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	
	public boolean isShowCreate() {
		return !edit;
	}
	public boolean isShowUpdate() {
		return edit;
	}
	public boolean isShowDelete() {
		return edit;
	}
	public boolean iseditparent() {
		return parent == null;
	}
	public String getSpaceid() {
		return spaceid;
	}
	public void setSpaceid(String spaceid) {
		this.spaceid = spaceid;
	}
	public String getSpacename() {
		return spacename;
	}
	public void setSpacename(String spacename) {
		this.spacename = spacename;
	}
	public String getSpacetype() {
		return spacetype;
	}
	public void setSpacetype(String spacetype) {
		this.spacetype = spacetype;
	}
	public Space getParent() {
		return parent;
	}
	public void setParent(Space parent) {
		this.parent = parent;
	}
	public List<String> getSpacetypes() {
		return spacetypes;
	}
	public void setSpacetypes(List<String> spacetypes) {
		this.spacetypes = spacetypes;
	}

}
