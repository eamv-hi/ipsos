package ipsos.ejb.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Entity
@NamedQuery(name="findAllSpaceTypes", query="SELECT c FROM SpaceType c ORDER BY c.stid")
public class SpaceType {
	@Id
	private int stid;
	@NotNull
	private String stname;
	
	public int getStid() {
		return stid;
	}
	public void setStid(int stid) {
		this.stid = stid;
	}
	public String getStname() {
		return stname;
	}
	public void setStname(String stname) {
		this.stname = stname;
	}
	@Override
	public String toString() {
		return "SpaceType [stid=" + stid + ", stname=" + stname + "]";
	}
	

}
