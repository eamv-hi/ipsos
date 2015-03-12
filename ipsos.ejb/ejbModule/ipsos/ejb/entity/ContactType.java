package ipsos.ejb.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Entity
@NamedQuery(name="findAllContactTypes", query="SELECT c FROM ContactType c ORDER BY c.ctid")
public class ContactType {
	@Id
	private int ctid;
	@NotNull
	private String ctname;
	
	public int getCtid() {
		return ctid;
	}
	public void setCtid(int ctid) {
		this.ctid = ctid;
	}
	public String getCtname() {
		return ctname;
	}
	public void setCtname(String ctname) {
		this.ctname = ctname;
	}
	@Override
	public String toString() {
		return "ContactType [ctid=" + ctid + ", ctname=" + ctname + "]";
	}

}
