package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the MINIONUSER database table.
 * 
 */
@Entity
@NamedQuery(name="Minionuser.findAll", query="SELECT m FROM Minionuser m")
public class Minionuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MINIONUSER_USERID_GENERATOR", sequenceName="MINIONUSER_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MINIONUSER_USERID_GENERATOR")
	private long userid;

	private String pwd;

	private String pwdsecure;

	private String useraddress;

	private String useremail;

	private String username;

	private String userzip;

	//bi-directional many-to-one association to Cart
	@OneToMany(mappedBy="minionuser")
	private List<Cart> carts;

	//bi-directional many-to-one association to Userorder
	@OneToMany(mappedBy="minionuser")
	private List<Userorder> userorders;

	//bi-directional many-to-one association to Wishlist
	@OneToMany(mappedBy="minionuser")
	private List<Wishlist> wishlists;

	public Minionuser() {
	}

	public long getUserid() {
		return this.userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPwdsecure() {
		return this.pwdsecure;
	}

	public void setPwdsecure(String pwdsecure) {
		this.pwdsecure = pwdsecure;
	}

	public String getUseraddress() {
		return this.useraddress;
	}

	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}

	public String getUseremail() {
		return this.useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserzip() {
		return this.userzip;
	}

	public void setUserzip(String userzip) {
		this.userzip = userzip;
	}

	public List<Cart> getCarts() {
		return this.carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public Cart addCart(Cart cart) {
		getCarts().add(cart);
		cart.setMinionuser(this);

		return cart;
	}

	public Cart removeCart(Cart cart) {
		getCarts().remove(cart);
		cart.setMinionuser(null);

		return cart;
	}

	public List<Userorder> getUserorders() {
		return this.userorders;
	}

	public void setUserorders(List<Userorder> userorders) {
		this.userorders = userorders;
	}

	public Userorder addUserorder(Userorder userorder) {
		getUserorders().add(userorder);
		userorder.setMinionuser(this);

		return userorder;
	}

	public Userorder removeUserorder(Userorder userorder) {
		getUserorders().remove(userorder);
		userorder.setMinionuser(null);

		return userorder;
	}

	public List<Wishlist> getWishlists() {
		return this.wishlists;
	}

	public void setWishlists(List<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	public Wishlist addWishlist(Wishlist wishlist) {
		getWishlists().add(wishlist);
		wishlist.setMinionuser(this);

		return wishlist;
	}

	public Wishlist removeWishlist(Wishlist wishlist) {
		getWishlists().remove(wishlist);
		wishlist.setMinionuser(null);

		return wishlist;
	}

}