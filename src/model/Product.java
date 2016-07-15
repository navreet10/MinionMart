package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the PRODUCT database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRODUCT_PRODID_GENERATOR", sequenceName="PRODUCT_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCT_PRODID_GENERATOR")
	private long prodid;

	private String proddesc;

	private String prodname;

	private BigDecimal prodprice;

	private String produrl;

	//bi-directional many-to-one association to Cart
	@OneToMany(mappedBy="product")
	private List<Cart> carts;

	//bi-directional many-to-one association to Minionorder
	@OneToMany(mappedBy="product")
	private List<Minionorder> minionorders;

	//bi-directional many-to-one association to Prodtype
	@ManyToOne
	@JoinColumn(name="TYPEID")
	private Prodtype prodtype;

	//bi-directional many-to-one association to Wishlist
	@OneToMany(mappedBy="product")
	private List<Wishlist> wishlists;

	public Product() {
	}

	public long getProdid() {
		return this.prodid;
	}

	public void setProdid(long prodid) {
		this.prodid = prodid;
	}

	public String getProddesc() {
		return this.proddesc;
	}

	public void setProddesc(String proddesc) {
		this.proddesc = proddesc;
	}

	public String getProdname() {
		return this.prodname;
	}

	public void setProdname(String prodname) {
		this.prodname = prodname;
	}

	public BigDecimal getProdprice() {
		return this.prodprice;
	}

	public void setProdprice(BigDecimal prodprice) {
		this.prodprice = prodprice;
	}

	public String getProdurl() {
		return this.produrl;
	}

	public void setProdurl(String produrl) {
		this.produrl = produrl;
	}

	public List<Cart> getCarts() {
		return this.carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public Cart addCart(Cart cart) {
		getCarts().add(cart);
		cart.setProduct(this);

		return cart;
	}

	public Cart removeCart(Cart cart) {
		getCarts().remove(cart);
		cart.setProduct(null);

		return cart;
	}

	public List<Minionorder> getMinionorders() {
		return this.minionorders;
	}

	public void setMinionorders(List<Minionorder> minionorders) {
		this.minionorders = minionorders;
	}

	public Minionorder addMinionorder(Minionorder minionorder) {
		getMinionorders().add(minionorder);
		minionorder.setProduct(this);

		return minionorder;
	}

	public Minionorder removeMinionorder(Minionorder minionorder) {
		getMinionorders().remove(minionorder);
		minionorder.setProduct(null);

		return minionorder;
	}

	public Prodtype getProdtype() {
		return this.prodtype;
	}

	public void setProdtype(Prodtype prodtype) {
		this.prodtype = prodtype;
	}

	public List<Wishlist> getWishlists() {
		return this.wishlists;
	}

	public void setWishlists(List<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	public Wishlist addWishlist(Wishlist wishlist) {
		getWishlists().add(wishlist);
		wishlist.setProduct(this);

		return wishlist;
	}

	public Wishlist removeWishlist(Wishlist wishlist) {
		getWishlists().remove(wishlist);
		wishlist.setProduct(null);

		return wishlist;
	}

}