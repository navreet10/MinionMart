package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import DBUtil.DBUtil;
import model.Minionorder;
import model.Minionuser;
import model.Prodtype;
import model.Product;

public class OrderDao {


	public static void updateProduct(Product product) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
				trans.begin();
	            em.merge(product);
	            trans.commit();			
			
		} catch (Exception e) {
			trans.rollback();
		} finally {
			em.close();
		}
		
	}

	public static List<Minionorder> getOrder(String ordername) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "select b from Minionorder b where b.ordername =:ordername";
        
        List<Minionorder> orders = null;
        try{
            TypedQuery<Minionorder> query = em.createQuery(qString,Minionorder.class);
            query.setParameter("ordername", ordername);
            orders = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
		return orders;
	}

	public static void updateOrderStatus(List<Minionorder> orders, String status) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			for (Minionorder order: orders) {
				order.setStatus(status);
				trans.begin();
	            em.merge(order);
	            trans.commit();	
			}
						
			
		} catch (Exception e) {
			trans.rollback();
		} finally {
			em.close();
		}
		
	}

	public static List<Prodtype> getAllTypes() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "select b from Prodtype b";
        
        List<Prodtype> types = null;
        try{
            TypedQuery<Prodtype> query = em.createQuery(qString,Prodtype.class);
            types = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
		return types;
		
	}

	public static Prodtype getType(String typeId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "select b from Prodtype b where b.typeid = :typeid";
        
        Prodtype type = null;
        try{
            TypedQuery<Prodtype> query = em.createQuery(qString,Prodtype.class);
            query.setParameter("typeid", Long.parseLong(typeId));
            type = query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
		return type;
	}

	public static void addProduct(Product product) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(product);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
		} finally {
			em.close();
		}
		
	}
	public static Minionorder getorderByorderid(long orderid) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "Select u from Minionorder u where u.orderid = :orderid";
		TypedQuery<Minionorder> q = em.createQuery(qString, Minionorder.class);
		q.setParameter("orderid", orderid);
		Minionorder order = null;
		try {
			order = q.getSingleResult();
		} catch (NoResultException e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return order;
	}
	
	public static List<Minionorder> getordersByordername(String ordername) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "Select u from Minionorder u where u.ordername = :ordername";
		TypedQuery<Minionorder> q = em.createQuery(qString, Minionorder.class);
		q.setParameter("ordername", ordername);
		List<Minionorder> orders = null;
		try {
			orders = q.getResultList();
		} catch (NoResultException e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return orders;
	}
	
	public static float getpricetotal(List<Minionorder> orders)
	{
		float price=0;
		for(Minionorder order: orders)
		{
			price+=order.getProduct().getProdprice()*order.getQtty();
		}
		return price;
	}
	
	
	
	

}
