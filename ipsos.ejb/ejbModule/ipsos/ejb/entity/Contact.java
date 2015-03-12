package ipsos.ejb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@IdClass(ContactId.class)
@NamedQueries({
	@NamedQuery(name="findAllContacts", query="SELECT c FROM Contact c WHERE c.spaceid = :spaceid"),
	@NamedQuery(name="findAllContactsTouchpoint", query="SELECT c FROM Contact c WHERE c.spaceid = :spaceid AND c.tpid = :tpid"),
	@NamedQuery(name="findAllContactsTouchpointType", query="SELECT c FROM Contact c WHERE c.spaceid = :spaceid AND c.tpid = :tpid AND c.ctid = :ctid")
})
public class Contact {
	
	@Id
	@Column(name = "spaceid", insertable = false, updatable = false)
	private String spaceid;
	@Id
	@Column(name = "ctid", insertable = false, updatable = false)
	private int ctid;
	@Id
	@Column(name = "tpid", insertable = false, updatable = false)
	private int tpid;
	@Id
	private String email;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="spaceid", insertable = false, updatable = false)
	private Space space;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ctid", insertable = false, updatable = false)
	private ContactType cttype;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tpid", insertable = false, updatable = false)
	private TouchPoint tp;

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

	public ContactType getCttype() {
		return cttype;
	}

	public void setCttype(ContactType cttype) {
		this.cttype = cttype;
	}

	public TouchPoint getTp() {
		return tp;
	}

	public void setTp(TouchPoint tp) {
		this.tp = tp;
	}

	public String getSpaceid() {
		return spaceid;
	}

	public void setSpaceid(String spaceid) {
		this.spaceid = spaceid;
	}

	public Space getSpace() {
		return space;
	}

	public void setSpace(Space space) {
		this.space = space;
	}

	@Override
	public String toString() {
		return "Contact [spaceid=" + spaceid + ", ctid=" + ctid + ", tpid="
				+ tpid + ", email=" + email + ", space=" + space + ", cttype="
				+ cttype + ", tp=" + tp + "]";
	}



}
