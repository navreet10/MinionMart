package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CART database table.
 * 
 */
@Entity
@NamedQuery(name="Cart.findAll", query="SELECT c FROM Cart c")
public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CART_CARTID_GENERATOR", sequenceName="CART_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CART_CARTID_GENERATOR")
	private long cartid;

	private BigDecimal active;

	private Long qtty;

	//bi-directional many-to-one association to Minionuser
	@ManyToOne
	@JoinColumn(name="USERID")
	private Minionuser minionuser;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="PRODID")
	private Product product;

	public Cart() {
	}

	public long getCartid() {
		return this.cartid;
	}

	public void setCartid(long cartid) {
		this.cartid = cartid;
	}

	public BigDecimal getActive() {
		return this.active;
	}

	public void setActive(BigDecimal active) {
		this.active = active;
	}

	public Long getQtty() {
		return this.qtty;
	}

	public void setQtty(Long qtty) {
		this.qtty = qtty;
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