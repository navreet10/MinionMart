package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PRODTYPE database table.
 * 
 */
@Entity
@NamedQuery(name="Prodtype.findAll", query="SELECT p FROM Prodtype p")
public class Prodtype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRODTYPE_TYPEID_GENERATOR", sequenceName="PRODTYPE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODTYPE_TYPEID_GENERATOR")
	private long typeid;

	private String typedesc;

	private String typename;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="prodtype")
	private List<Product> products;

	public Prodtype() {
	}

	public long getTypeid() {
		return this.typeid;
	}

	public void setTypeid(long typeid) {
		this.typeid = typeid;
	}

	public String getTypedesc() {
		return this.typedesc;
	}

	public void setTypedesc(String typedesc) {
		this.typedesc = typedesc;
	}

	public String getTypename() {
		return this.typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setProdtype(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setProdtype(null);

		return product;
	}

}