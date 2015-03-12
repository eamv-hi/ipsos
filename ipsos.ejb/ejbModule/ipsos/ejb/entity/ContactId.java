package ipsos.ejb.entity;

import java.io.Serializable;

public class ContactId implements Serializable {
	private static final long serialVersionUID = 1L;
	private String spaceid;
	private int ctid;
	private int tpid;
	private String email;
	
	public String getSpaceid() {
		return spaceid;
	}
	public void setSpaceid(String spaceid) {
		this.spaceid = spaceid;
	}
	public int getCtid() {
		return ctid;
	}
	public void setCtid(int ctid) {
		this.ctid = ctid;
	}
	public int getTpid() {
		return tpid;
	}
	public void setTpid(int tpid) {
		this.tpid = tpid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ctid;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((spaceid == null) ? 0 : spaceid.hashCode());
		result = prime * result + tpid;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactId other = (ContactId) obj;
		if (ctid != other.ctid)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (spaceid == null) {
			if (other.spaceid != null)
				return false;
		} else if (!spaceid.equals(other.spaceid))
			return false;
		if (tpid != other.tpid)
			return false;
		return true;
	}
	

}
