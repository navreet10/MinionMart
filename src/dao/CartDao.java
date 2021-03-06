package dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import DBUtil.DBUtil;
import DBUtil.Dataget;
import model.Cart;
import model.Minionorder;
import model.Product;
import model.Userorder;
import model.Wishlist;

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


        String qString = "select b from Product b where b.prodid =:prodId";
        
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
	
	public static Cart getCartbyCartid(String cartid) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();


        String qString = "select b from Cart b where b.cartid =:cartid";
        
        Cart cart = new Cart();
        try{
            TypedQuery<Cart> query = em.createQuery(qString,Cart.class);
            query.setParameter("cartid", Long.parseLong(cartid));
            cart = query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }

	

		return cart;
	}
	
	
	public static Wishlist getWishbywishid(String wishid) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();


        String qString = "select b from Wishlist b where b.wishid =:wishid";
        
        Wishlist wish = new Wishlist();
        try{
            TypedQuery<Wishlist> query = em.createQuery(qString,Wishlist.class);
            query.setParameter("wishid", Long.parseLong(wishid));
            wish = query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }

	

		return wish;
	}

	 public static void delete(Cart cart) {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        EntityTransaction trans = em.getTransaction();
	        try {
	            trans.begin();
	            em.remove(em.merge(cart));
	            trans.commit();
	        } catch (Exception e) {
	            System.out.println(e);
	            trans.rollback();
	        } finally {
	            em.close();
	        }
	    }
	
	 public static void delete(Wishlist wish) {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        EntityTransaction trans = em.getTransaction();
	        try {
	            trans.begin();
	            em.remove(em.merge(wish));
	            trans.commit();
	        } catch (Exception e) {
	            System.out.println(e);
	            trans.rollback();
	        } finally {
	            em.close();
	        }
	    }
	
	
	public static List<Cart> getCartItems() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<Cart> items = null;
		String qString = "select b from Cart b where b.active =0";

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



	public static String order(List<Cart> cartUpdated, String username) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		long orderid = 0;

		try {
			String qString = "select b from Userorder b where b.minionuser.userid = "
					+ "(select a.userid from Minionuser a where a.username = :username) ";
			TypedQuery<Userorder> query = em.createQuery(qString, Userorder.class);
			query.setParameter("username", username);

			List<Userorder> usors = query.getResultList();
			 Userorder userorder = null;
			if (usors == null || usors.size()==0) {
				userorder = new Userorder();
				userorder.setMinionuser(Dataget.getUserByName(username));
				userorder.setOrdercount(1l);
				orderid = 1;
			} else {
				userorder = usors.get(0);
				orderid = userorder.getOrdercount() + 1;
			}
			 
			for (Cart cart : cartUpdated) {
				Minionorder order = new Minionorder();
				order.setOrdername(username + orderid);
				order.setProduct(cart.getProduct());
				order.setQtty(cart.getQtty());
				order.setStatus("New");
				EntityTransaction trans = em.getTransaction();
				try {
					trans.begin();
					em.persist(order);
					trans.commit();
				} catch (Exception e) {
					trans.rollback();
				}
			}
			userorder.setOrdercount(orderid);
			EntityTransaction trans = em.getTransaction();
			try {
				trans.begin();
				em.merge(userorder);
				trans.commit();
			} catch (Exception e) {
				trans.rollback();
			}
			
			updateCart();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}

		return username + orderid;

	}

	public static void updateCart() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			List<Cart> items = getCartItems();
			for (Cart cart: items) {
				cart.setActive(new BigDecimal(1));
				trans.begin();
	            em.merge(cart);
	            trans.commit();
			}
			
		} catch (Exception e) {
			trans.rollback();
		} finally {
			em.close();
		}
		
	}


	public static String orderWish(List<Wishlist> wishItems, String username) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		long orderid =0;

		try {
			String qString = "select b from Userorder b where b.minionuser.userid = "
					+ "(select a.userid from Minionuser a where a.username = :username) ";
			TypedQuery<Userorder> query = em.createQuery(qString, Userorder.class);
			query.setParameter("username", username);

			List<Userorder> usors = query.getResultList();
			 Userorder userorder = null;
			if (usors == null || usors.size()==0) {
				userorder = new Userorder();
				userorder.setMinionuser(Dataget.getUserByName(username));
				userorder.setOrdercount(1);
				orderid = 1;
			} else {
				userorder = usors.get(0);
				orderid = userorder.getOrdercount() + 1;
			}
			 
			for (Wishlist wish : wishItems) {
				Minionorder order = new Minionorder();
				order.setOrdername(username + orderid);
				order.setProduct(wish.getProduct());
				order.setQtty(1l);
				order.setStatus("New");
				EntityTransaction trans = em.getTransaction();
				try {
					trans.begin();
					em.persist(order);
					trans.commit();
				} catch (Exception e) {
					trans.rollback();
				}
			}
			userorder.setOrdercount(orderid);
			EntityTransaction trans = em.getTransaction();
			try {
				trans.begin();
				em.merge(userorder);
				trans.commit();
			} catch (Exception e) {
				trans.rollback();
			}
			
			deleteWishList();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return username + orderid;

	}

	private static void deleteWishList() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
        	List<Wishlist> list = getWishItems();
        	for(Wishlist wish: list) {
        		trans.begin();
                em.remove(em.merge(wish));
                trans.commit();
        	}
            
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
		
	}

	public static List<Wishlist> getWishItems() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<Wishlist> items = null;
		String qString = "select b from Wishlist b";

		try {
			TypedQuery<Wishlist> query = em.createQuery(qString, Wishlist.class);

			items = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return items;
	}

}
