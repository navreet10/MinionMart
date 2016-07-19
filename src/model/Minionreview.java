package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MINIONREVIEW database table.
 * 
 */
@Entity
@NamedQuery(name="Minionreview.findAll", query="SELECT m FROM Minionreview m")
public class Minionreview implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MINIONREVIEW_REVIEWID_GENERATOR", sequenceName="MINIONREVIEW_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MINIONREVIEW_REVIEWID_GENERATOR")
	private long reviewid;

	private long ishelpful;

	@ManyToOne
	@JoinColumn(name="PRODID")
	private Product product;

	@Temporal(TemporalType.DATE)
	private Date reviewdate;

	private String reviewtext;

	@ManyToOne
	@JoinColumn(name="USERID")
	private Minionuser minionuser;

	public Minionreview() {
	}

	public long getReviewid() {
		return this.reviewid;
	}

	public void setReviewid(long reviewid) {
		this.reviewid = reviewid;
	}

	public long getIshelpful() {
		return this.ishelpful;
	}

	public void setIshelpful(long ishelpful) {
		this.ishelpful = ishelpful;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getReviewdate() {
		return this.reviewdate;
	}

	public void setReviewdate(Date reviewdate) {
		this.reviewdate = reviewdate;
	}

	public String getReviewtext() {
		return this.reviewtext;
	}

	public void setReviewtext(String reviewtext) {
		this.reviewtext = reviewtext;
	}

	public Minionuser getMinionuser() {
		return this.minionuser;
	}

	public void setMinionuser(Minionuser minionuser) {
		this.minionuser = minionuser;
	}
}