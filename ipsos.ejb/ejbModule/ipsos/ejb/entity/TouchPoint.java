package ipsos.ejb.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Entity
@NamedQuery(name="findAllTouchPoints", query="SELECT c FROM TouchPoint c ORDER BY c.tpid")
public class TouchPoint {
	@Id
	private int tpid;
	@NotNull
	private String tpname;
	
	public int getTpid() {
		return tpid;
	}
	public void setTpid(int tpid) {
		this.tpid = tpid;
	}
	public String getTpname() {
		return tpname;
	}
	public void setTpname(String tpname) {
		this.tpname = tpname;
	}
	@Override
	public String toString() {
		return "TouchPoint [tpid=" + tpid + ", tpname=" + tpname + "]";
	}

}
