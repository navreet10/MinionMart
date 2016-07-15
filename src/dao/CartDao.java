package dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import DBUtil.DBUtil;
import model.Cart;
import model.Minionorder;
import model.Product;
import model.Userorder;

public class CartDao {

	public static void insertCart(Cart cart) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(cart);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static Product getProduct(String prodId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "select b from Product b where b.prodid = :prodId";

		Product prod = new Product();
		try {
			TypedQuery<Product> query = em.createQuery(qString, Product.class);
			query.setParameter("prodId", Long.parseLong(prodId));
			prod = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return prod;
	}

	public static List<Cart> getCartItems() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<Cart> items = null;
		String qString = "select b from Cart b";

		try {
			TypedQuery<Cart> query = em.createQuery(qString, Cart.class);

			items = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return items;
	}

	public static void order(List<Cart> cartUpdated, String username) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			String qString = "select b from Userorder b where b.minionuser.userid = "
					+ "(select a.userid from minionuser a where a.username = :username) ";
			TypedQuery<Userorder> query = em.createQuery(qString, Userorder.class);
			query.setParameter("username", username);
			Userorder userorder = query.getSingleResult();
			long orderid = userorder.getUserorderid() + 1;
			for (Cart cart : cartUpdated) {
				Minionorder order = new Minionorder();
				order.setOrdername(username + orderid);
				order.setProduct(cart.getProduct());
				order.setQtty(cart.getQtty());
				EntityTransaction trans = em.getTransaction();
				try {
					trans.begin();
					em.persist(order);
					trans.commit();
				} catch (Exception e) {
					trans.rollback();
				}
			}
			userorder.setOrdercount(new BigDecimal(orderid));
			EntityTransaction trans = em.getTransaction();
			try {
				trans.begin();
				em.merge(userorder);
				trans.commit();
			} catch (Exception e) {
				trans.rollback();
			}
			em.createQuery("DELETE FROM Cart").executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}

	}

}
