package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the WISHLIST database table.
 * 
 */
@Entity
@NamedQuery(name="Wishlist.findAll", query="SELECT w FROM Wishlist w")
public class Wishlist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="WISHLIST_WISHID_GENERATOR", sequenceName="WISHLIST_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WISHLIST_WISHID_GENERATOR")
	private long wishid;

	//bi-directional many-to-one association to Minionuser
	@ManyToOne
	@JoinColumn(name="USERID")
	private Minionuser minionuser;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="PRODID")
	private Product product;

	public Wishlist() {
	}

	public long getWishid() {
		return this.wishid;
	}

	public void setWishid(long wishid) {
		this.wishid = wishid;
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