package DBUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import Util.PasswordUtil;
import model.Cart;
import model.Minionreview;
import model.Minionuser;
import model.Prodtype;
import model.Product;
import model.Wishlist;

public class Dataget {

	public static Minionuser getUserByEmail(String email) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "Select u from Minionuser u " + "where u.useremail=:useremail";
		TypedQuery<Minionuser> q = em.createQuery(qString, Minionuser.class);
		q.setParameter("useremail", email);
		Minionuser user = null;
		try {
			user = q.getSingleResult();
		} catch (NoResultException e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return user;
	}
	
	public static Minionuser getUserByName(String name) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "Select u from Minionuser u where u.username = :name";
		TypedQuery<Minionuser> q = em.createQuery(qString, Minionuser.class);
		q.setParameter("name", name);
		Minionuser user = null;
		try {
			user = q.getSingleResult();
		} catch (NoResultException e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return user;
	}

	public static boolean isValidUser(String name, String pass) throws NoSuchAlgorithmException {
		boolean result = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "Select b from Minionuser b where b.username = :name";
		try {
			TypedQuery<Minionuser> q = em.createQuery(qString, Minionuser.class);

			q.setParameter("name", name);
			Minionuser user = q.getSingleResult();
			String hashCode = PasswordUtil.hashPasswordPlusSalt(pass, user.getPwdsecure());
		
			if (user.getPwd().equals(hashCode)) {
				result = true;
			}
		} catch (Exception e) {
			result = false;
		} finally {
			em.close();
		}
		return result;
	}

	public static List<Prodtype> getProdtype() {

		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		String qString = "Select p from Prodtype p";

		Query q = em.createQuery(qString);

		List<Prodtype> post = new ArrayList<Prodtype>();

		try {
			post = q.getResultList();

		} catch (NoResultException e) {
			System.out.println(e);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			em.close();
		}
		return post;
	}

	public static List<Product> getProducts() {

		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		String qString = "Select p from Product p";

		Query q = em.createQuery(qString);

		List<Product> post = new ArrayList<Product>();

		try {
			post = q.getResultList();

		} catch (NoResultException e) {
			System.out.println(e);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			em.close();
		}
		return post;
	}
	
	public static List<Minionreview> getProductReviews(long productid) {

		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		String qString = "Select p from Minionreview p where p.product.prodid=:productid";

		Query q = em.createQuery(qString);
		q.setParameter("productid", productid);
		List<Minionreview> reviews = new ArrayList<Minionreview>();

		try {
			reviews = q.getResultList();

		} catch (NoResultException e) {
			System.out.println(e);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			em.close();
		}
		return reviews;
	}
	
	public static List<Minionreview> getReviews() {

		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		String qString = "Select p from Minionreview p ";

		Query q = em.createQuery(qString);
		List<Minionreview> reviews = new ArrayList<Minionreview>();

		try {
			reviews = q.getResultList();

		} catch (NoResultException e) {
			System.out.println(e);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			em.close();
		}
		return reviews;
	}
	
	public static List<Minionreview> getMyReviews(long userid) {

		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		String qString = "Select p from Minionreview p where p.minionuser.userid=:userid ";

		Query q = em.createQuery(qString);
		q.setParameter("userid", userid);
		List<Minionreview> reviews = new ArrayList<Minionreview>();

		try {
			reviews = q.getResultList();

		} catch (NoResultException e) {
			System.out.println(e);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			em.close();
		}
		return reviews;
	}
	
	public static void insert(Minionuser user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(user);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
	public static void update(Minionuser user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
	
	
	public static void insert(Minionreview review) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(review);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
	public static void update(Minionreview review) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(review);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
	
	


	public static List<Product> getProductsbytypeid(long typeid) {

		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		String qString = "Select p from Product p where p.prodtype.typeid=:typeid";

		Query q = em.createQuery(qString);
		q.setParameter("typeid", typeid);
		List<Product> post = new ArrayList<Product>();

		try {
			post = q.getResultList();

		} catch (NoResultException e) {
			System.out.println(e);
		} catch (Exception e) {


			e.printStackTrace();
		} finally {
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
	 public static long getprodreviewid (String reviewid)
	    {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        List<Long> reviewids=new ArrayList<Long>();
	        String qString = "select b.reviewid from Minionreview b";
	        long longreviewid=0;
	        try{
	            Query query = em.createQuery(qString,Prodtype.class);           
	            reviewids=query.getResultList();
	            
	            for(long a: reviewids)
	            {
	            	if(Long.toString(a).equals(reviewid))
	            	{
	            		longreviewid=a;
	            	}
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        finally{
	                em.close();
	            }
	        return longreviewid;    
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
	        String qString = "select b from Product b where b.prodid =:productid";
	        
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
	 
	 public static Minionreview getreviewbyreviewid(long reviewid)
	    {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        Minionreview review = null;
	        String qString = "select b from Minionreview b where b.reviewid =:reviewid";
	        
	        try{
	            TypedQuery<Minionreview> query = em.createQuery(qString,Minionreview.class);
	            query.setParameter("reviewid", reviewid);
	            review = query.getSingleResult();
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        finally{
	                em.close();
	            }
	        return review;    
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
	 public static List<Product> searchProducts (String search)
	    {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        List<Product> searchposts = null;
	        String qString = "select b from Product b "
	                + "where b.prodname like :search";
	        
	        try{
	            TypedQuery<Product> query = em.createQuery(qString,Product.class);
	            query.setParameter("search", "%" + search + "%");
	            searchposts = query.getResultList();
	        }catch (Exception e){
	            e.printStackTrace();
	        }finally{
	            em.close();
	        }return searchposts;
	    }
	 public static List<Minionreview> searchReview (String search)
	    {
		 
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        List<Minionreview> searchposts = null;
	        String qString = "select b from Minionreview b "
	                + "where b.reviewtext like :search";
	        
	        try{
	            TypedQuery<Minionreview> query = em.createQuery(qString,Minionreview.class);
	            query.setParameter("search", "%" + search.replaceAll("[^a-zA-Z ]", "").toLowerCase() + "%");
	            searchposts = query.getResultList();
	        }catch (Exception e){
	            e.printStackTrace();
	        }finally{
	            em.close();
	        }return searchposts;
	    }
	 public static void delete(Minionreview review) {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        EntityTransaction trans = em.getTransaction();
	        try {
	            trans.begin();
	            em.remove(em.merge(review));
	            trans.commit();
	        } catch (Exception e) {
	            System.out.println(e);
	            trans.rollback();
	        } finally {
	            em.close();
	        }
	    }
	 public static HashMap<String,String> gethappysadUrl(List<Minionreview> allreviews) throws IOException
	    {
	    	HashMap<String,String> urls=new HashMap<String,String>();
	    	Util.Sentiment sent=new Util.Sentiment();
	    	sent.SentimentInit();
	    	int moody=0;
	    	for(Minionreview review: allreviews)
	    	{
	    		moody= sent.DefineMoody(sent.gethappyCount(review.getReviewtext().replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+")), sent.getSadCount(review.getReviewtext().replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+")));
	    		 if(moody==1)
			      {
	    			 urls.put(review.getReviewtext(), "images/happy.png");
			      }	
			      else if(moody==-1)
			      {
			    	  urls.put(review.getReviewtext(), "images/sad.jpg");
			      }
			      else if(moody==0)
			      {
			    	  urls.put(review.getReviewtext(), "images/neatral.png");
			    	  
			      }
	    	} 	
	    			
	    	return urls;
	    	
	    }
	 public static HashMap<String,String> getGravatarUrl(List<Minionreview> allreviews)
	    {
	    	HashMap<String,String> urls=new HashMap<String,String>();
	    		
	    	for(Minionreview review: allreviews)
	    	{
	    		urls.put(review.getMinionuser().getUseremail(), "https://www.gravatar.com/avatar/"+Util.MD5Util.md5Hex(review.getMinionuser().getUseremail())+"?s=80");
	    		
	    	} 	
	    			
	    	return urls;
	    	
	    }
	
	


}
