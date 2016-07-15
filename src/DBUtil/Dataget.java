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

import model.Minionuser;
import model.Prodtype;
import model.Product;



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
	 
	
	
	
	
	

}
