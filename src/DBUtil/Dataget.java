package DBUtil;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Cart;
import model.Minionuser;
import model.Prodtype;
import model.Product;
import model.Wishlist;



public class Dataget {
	
	public static Minionuser getUserByEmail(String email)
	{
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		String qString="Select u from Minionuser u "+"where u.useremail=:useremail";
		TypedQuery<Minionuser> q=em.createQuery(qString,Minionuser.class);
		q.setParameter("useremail", email);
		Minionuser user=null;
		try
		{
			user=q.getSingleResult();
		}catch(NoResultException e)
		{
		   System.out.println(e);
		}finally
		{
			em.close();
		}
		return user;
	}
	public static boolean isValidUser(String email, String pass)
	{
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		String qString="Select count(b.userid) from Minionuser b "
				+"where b.useremail=:useremail"
				+ " and b.pwd=:userpass";
		TypedQuery<Long> q=em.createQuery(qString,Long.class);
		boolean result=false;
		q.setParameter("useremail", email);
		q.setParameter("userpass", pass);
		try
		{
			long userid=q.getSingleResult();
			if(userid>0)
			{
				result=true;
			}
		}catch(Exception e)
		{
			result=false;
		}
		finally
		{
			em.close();
		}
		return result;
	}
	public static List<Prodtype> getProdtype()
	{
		
		EntityManager em=DBUtil.getEmFactory().createEntityManager();

		String qString="Select p from Prodtype p";
	
		Query q=em.createQuery(qString);
		
		
		List<Prodtype> post=new ArrayList<Prodtype>();	
		
		try
		{
			post=q.getResultList();
			
			
		}
		catch(NoResultException e)
		{
		   System.out.println(e);
		}catch(Exception e)
		{
		   
			e.printStackTrace();
		}finally
		{
			em.close();
		}
		return post;
	}
	public static List<Product> getProducts()
	{
		
		EntityManager em=DBUtil.getEmFactory().createEntityManager();

		String qString="Select p from Product p";
	
		Query q=em.createQuery(qString);
		
		
		List<Product> post=new ArrayList<Product>();	
		
		try
		{
			post=q.getResultList();
			
			
		}
		catch(NoResultException e)
		{
		   System.out.println(e);
		}catch(Exception e)
		{
		   
			e.printStackTrace();
		}finally
		{
			em.close();
		}
		return post;
	}
	public static List<Product> getProductsbytypeid(long typeid)
	{
		
		EntityManager em=DBUtil.getEmFactory().createEntityManager();

		String qString="Select p from Product p where p.prodtype.typeid=: typeid";
	
		Query q=em.createQuery(qString);
		q.setParameter("type", typeid);
		List<Product> post=new ArrayList<Product>();	
		
		try
		{
			post=q.getResultList();
			
			
		}
		catch(NoResultException e)
		{
		   System.out.println(e);
		}catch(Exception e)
		{
		   
			e.printStackTrace();
		}finally
		{
			em.close();
		}
		return post;
	}
	 public static long getprodtypeid (String typeid)
	    {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        List<Long> typeids=new ArrayList<Long>();
	        String qString = "select b.typeid from Prodtype b";
	        long longtypeid=0;
	        try{
	            Query query = em.createQuery(qString,Prodtype.class);           
	            typeids=query.getResultList();
	            
	            for(long a: typeids)
	            {
	            	if(Long.toString(a).equals(typeid))
	            	{
	            		longtypeid=a;
	            	}
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        finally{
	                em.close();
	            }
	        return longtypeid;    
	    }
	 public static long getprodid (String prodid)
	    {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        List<Long> prodids=new ArrayList<Long>();
	        String qString = "select b.prodid from Product b";
	        long longprodid=0;
	        try{
	            Query query = em.createQuery(qString,Prodtype.class);           
	            prodids=query.getResultList();
	            
	            for(long a: prodids)
	            {
	            	if(Long.toString(a).equals(prodid))
	            	{
	            		longprodid=a;
	            	}
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        finally{
	                em.close();
	            }
	        return longprodid;    
	    }
	 public static long getuserid (String userid)
	    {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        List<Long> userids=new ArrayList<Long>();
	        String qString = "select b.userid from Minionuser b";
	        long longuserid=0;
	        try{
	            Query query = em.createQuery(qString,Prodtype.class);           
	            userids=query.getResultList();
	            
	            for(long a: userids)
	            {
	            	if(Long.toString(a).equals(userid))
	            	{
	            		longuserid=a;
	            	}
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        finally{
	                em.close();
	            }
	        return longuserid;    
	    }
	 public static Product getproductbyproductid(long productid)
	    {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        Product product = null;
	        String qString = "select b from Product b where b.prodid = :productid";
	        
	        try{
	            TypedQuery<Product> query = em.createQuery(qString,Product.class);
	            query.setParameter("productid", productid);
	            product = query.getSingleResult();
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        finally{
	                em.close();
	            }
	        return product;    
	    }
	 public static Prodtype getProdtypebytypeid(long typeid)
	    {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        Prodtype type = null;
	        String qString = "select b from Prodtype b where b.typeid = :typeid";
	        
	        try{
	            TypedQuery<Prodtype> query = em.createQuery(qString,Prodtype.class);
	            query.setParameter("typeid", typeid);
	            type = query.getSingleResult();
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        finally{
	                em.close();
	            }
	        return type;    
	    }
	 public static Minionuser getuserbyuserid(long userid)
	    {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        Minionuser user = null;
	        String qString = "select b from Minionuser b where b.userid = :userid";
	        
	        try{
	            TypedQuery<Minionuser> query = em.createQuery(qString,Minionuser.class);
	            query.setParameter("userid", userid);
	            user = query.getSingleResult();
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        finally{
	                em.close();
	            }
	        return user;    
	    }
	 
	 public static void insert(Cart cart) {
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
	 public static void insert(Wishlist wish) {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        EntityTransaction trans = em.getTransaction();
	        try {
	            trans.begin();
	            em.persist(wish);
	            trans.commit();
	        } catch (Exception e) {
	            trans.rollback();
	        } finally {
	            em.close();
	        }
	    }
	
	
	
	

}
