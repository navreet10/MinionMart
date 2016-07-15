package dao;

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
        try{
            TypedQuery<Product> query = em.createQuery(qString,Product.class);
            query.setParameter("prodId", Long.parseLong(prodId));
            prod = query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
		return prod;
	}

	public static List<Cart> getCartItems() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Cart> items = null;
        String qString = "select b from cart b";
        
        try{
            TypedQuery<Cart> query = em.createQuery(qString,Cart.class);
            
            items = query.getResultList();
            
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
        return items;  
	}

	public static void order(List<Cart> cartUpdated, String username) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try{
			String qString = "select b from userorder b where userId = "
					+ "(select a.userid from minionuser a where username = :uaername) ";
			TypedQuery<Userorder> query = em.createQuery(qString,Userorder.class);
			Userorder userorder = query.getSingleResult();
			long orderid = userorder.getUserorderid() +1;
		for (Cart cart: cartUpdated) {
	       Minionorder order = new Minionorder();	       
	       order.setOrdername(username+orderid);
	       order.setProduct(cart.getProduct());
	       order.setQtty(cart.getQtty());
	       EntityTransaction trans = em.getTransaction();
		}
		
        
            
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
		
	}

}
