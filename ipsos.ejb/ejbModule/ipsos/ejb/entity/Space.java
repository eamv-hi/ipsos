package ipsos.ejb.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
	@NamedQuery(name="findSpaces", query="SELECT c FROM Space c WHERE UPPER(c.spacename) LIKE :search OR UPPER(c.spaceid) LIKE :search ORDER BY c.spacetype"),
	@NamedQuery(name="findAllChildren", query="SELECT c FROM Space c WHERE c.parent.spaceid = :parentid"),
	@NamedQuery(name="findAllTopSpaces", query="SELECT c FROM Space c WHERE c.parent = null")})
public class Space {
	@Id
	private String spaceid;
	@NotNull
	private String spacename;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="spacetype")
	private SpaceType spacetype;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="parentid")
	private Space parent;
	
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
	public Space getParent() {
		return parent;
	}
	public void setParent(Space parent) {
		this.parent = parent;
	}
	public SpaceType getSpacetype() {
		return spacetype;
	}
	public void setSpacetype(SpaceType spacetype) {
		this.spacetype = spacetype;
	}
	@Override
	public String toString() {
		return "Space [spaceid=" + spaceid + ", spacename=" + spacename
				+ ", spacetype=" + spacetype + ", parent=" + parent + "]";
	}
	

}
