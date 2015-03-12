package ipsos.web;

import ipsos.ejb.bean.SpaceBean;
import ipsos.ejb.entity.Space;

import java.io.Serializable;
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
public class SpaceOverviewView implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB private SpaceBean ejb;
	@Inject SpaceDetailView detail;
	@Inject ContactOverviewView contacts;
	private Logger logger = Logger.getLogger(SpaceOverviewView.class);
	private List<Space> spaces;
	private String searchstring;
	private Space selectedSpace;
	
	public void init() {
		search();
	}
	
	public void search() {
		spaces = ejb.listSpaces(searchstring);
	}

	public void topspaces() {
		spaces = ejb.listTopSpaces();
	}


	public String getSearchstring() {
		return searchstring;
	}

	public void setSearchstring(String searchstring) {
		this.searchstring = searchstring;
	}

	
	public String viewChildren() {
		logger.info("Selected space : " + selectedSpace);
		if (selectedSpace != null) {
			setSpaces(ejb.listChildren(selectedSpace.getSpaceid()));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No row selected"));
		}
		return null;
	}

	public String view() {
		logger.info("Selected space : " + selectedSpace);
		if (this.selectedSpace != null && detail != null) {
			detail.setSpaceid(this.selectedSpace.getSpaceid());
			detail.setSpacename(this.selectedSpace.getSpacename());
			detail.setSpacetype(this.selectedSpace.getSpacetype().getStname());
			detail.setParent(this.selectedSpace.getParent());
			detail.setEdit(true);
			this.selectedSpace = null;
			return "/spacedetail.xhtml";
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No row selected"));
		return null;
	}

	public String create() {
		if (detail != null) {
			detail.setSpaceid(null);
			detail.setSpacename(null);
			detail.setSpacetype(null);
			if (selectedSpace != null) {
				detail.setParent(this.selectedSpace);
			}
			this.selectedSpace = null;
			detail.setEdit(false);
			return "/spacedetail.xhtml";
		} 
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Something very wrong"));
		return null;
	}
	
	public String contacts() {
		logger.info("Selected space : " + selectedSpace);
		if (this.selectedSpace != null && contacts != null) {
			contacts.setSpace(selectedSpace);
			contacts.init();
			this.selectedSpace = null;
			return "/contactoverview.xhtml";
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No row selected"));
		return null;
	}


	public List<Space> getSpaces() {
		return spaces;
	}


	public void setSpaces(List<Space> spaces) {
		this.spaces = spaces;
	}


	public Space getSelectedSpace() {
		return selectedSpace;
	}


	public void setSelectedSpace(Space selectedSpace) {
		this.selectedSpace = selectedSpace;
	}

	
}
