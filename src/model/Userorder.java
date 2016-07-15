package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the USERORDER database table.
 * 
 */
@Entity
@NamedQuery(name="Userorder.findAll", query="SELECT u FROM Userorder u")
public class Userorder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USERORDER_USERORDERID_GENERATOR", sequenceName="USERORDER_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USERORDER_USERORDERID_GENERATOR")
	private long userorderid;

	private BigDecimal ordercount;

	//bi-directional many-to-one association to Minionuser
	@ManyToOne
	@JoinColumn(name="USERID")
	private Minionuser minionuser;

	public Userorder() {
	}

	public long getUserorderid() {
		return this.userorderid;
	}

	public void setUserorderid(long userorderid) {
		this.userorderid = userorderid;
	}

	public BigDecimal getOrdercount() {
		return this.ordercount;
	}

	public void setOrdercount(BigDecimal ordercount) {
		this.ordercount = ordercount;
	}

	public Minionuser getMinionuser() {
		return this.minionuser;
	}

	public void setMinionuser(Minionuser minionuser) {
		this.minionuser = minionuser;
	}

}