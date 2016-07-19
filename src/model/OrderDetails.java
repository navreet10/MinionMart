package model;

import java.util.List;

public class OrderDetails {
	private  Minionorder order;
	private List<Product> prods;
	public Minionorder getOrder() {
		return order;
	}
	public void setOrder(Minionorder order) {
		this.order = order;
	}
	public List<Product> getProds() {
		return prods;
	}
	public void setProds(List<Product> prods) {
		this.prods = prods;
	}	

}
