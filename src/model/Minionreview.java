package model;

import java.io.Serializable;
import javax.persistence.*;
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

	@Temporal(TemporalType.DATE)
	private Date reviewdate;

	private String reviewtext;

	//bi-directional many-to-one association to Minionuser
	@ManyToOne
	@JoinColumn(name="USERID")
	private Minionuser minionuser;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="PRODID")
	private Product product;

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

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}