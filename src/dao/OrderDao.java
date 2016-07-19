package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import DBUtil.DBUtil;
import model.Minionorder;
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

	public static List<Minionorder> getOrder(String orderId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "select b from Minionorder b where b.orderid =:orderId";
        
        List<Minionorder> orders = null;
        try{
            TypedQuery<Minionorder> query = em.createQuery(qString,Minionorder.class);
            query.setParameter("orderId", Long.parseLong(orderId));
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

}
