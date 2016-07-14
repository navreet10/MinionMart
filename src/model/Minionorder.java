package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MINIONORDER database table.
 * 
 */
@Entity
@NamedQuery(name="Minionorder.findAll", query="SELECT m FROM Minionorder m")
public class Minionorder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MINIONORDER_ORDERID_GENERATOR", sequenceName="MINIONORDER_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MINIONORDER_ORDERID_GENERATOR")
	private long orderid;

	private String ordername;

	private BigDecimal qtty;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="PRODID")
	private Product product;

	public Minionorder() {
	}

	public long getOrderid() {
		return this.orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	public String getOrdername() {
		return this.ordername;
	}

	public void setOrdername(String ordername) {
		this.ordername = ordername;
	}

	public BigDecimal getQtty() {
		return this.qtty;
	}

	public void setQtty(BigDecimal qtty) {
		this.qtty = qtty;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}