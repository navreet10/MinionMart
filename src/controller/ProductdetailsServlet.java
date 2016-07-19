package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBUtil.Dataget;
import model.Minionreview;
import model.Minionuser;
import model.Product;

/**
 * Servlet implementation class ProductdetailsServlet
 */
@WebServlet("/ProductdetailsServlet")
public class ProductdetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductdetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String productid = (String)request.getParameter("productid");
		
		String method = (String)request.getParameter("method");
		
		if(productid!=null)
		{
			
			long longproductid=Dataget.getprodid(productid);
			
			Product myproduct=Dataget.getproductbyproductid(longproductid);
			
			
			session.setAttribute("myproduct",myproduct );
			session.setAttribute("myproductid", myproduct.getProdid());
			session.setAttribute("myproductname", myproduct.getProdname());
			session.setAttribute("myproductimage", myproduct.getProdurl());
			session.setAttribute("myproductdesc", myproduct.getProddesc());
			session.setAttribute("myproducttype", myproduct.getProdtype().getTypename());
			session.setAttribute("myproductprice", myproduct.getProdprice());
					
			List<Minionreview> reviews =Dataget.getProductReviews(longproductid);
			
			session.setAttribute("Productreviews", reviews);
			
			HashMap<String,String> imageurls=Dataget.getGravatarUrl(reviews);
			HashMap<String,String> happysadurls=Dataget.gethappysadUrl(reviews);
			
			session.setAttribute("happysadurls", happysadurls);
		    session.setAttribute("imageurls",imageurls ); 	
			
			List<model.Prodtype> types=Dataget.getProdtype();	
			List<model.Product> Products=Dataget.getProducts();
			session.setAttribute("Prodtype", types);
			session.setAttribute("Products",Products ); 
			
			
			Minionuser user = (Minionuser) request.getSession().getAttribute("user");
			
			session.setAttribute("user", user);
			session.setAttribute("userid", user.getUserid());
			session.setAttribute("useremail",user.getUseremail());
			session.setAttribute("username", user.getUsername());
			session.setAttribute("userpassword", user.getPwd());
			session.setAttribute("useraddress", user.getUseraddress());
			session.setAttribute("userzipcode", user.getUserzip());
			request.setAttribute("messages", user.getUsername());
			session.setAttribute("images", "https://www.gravatar.com/avatar/"+Util.MD5Util.md5Hex(user.getUseremail())+"?s=80");
			
			
			request.getRequestDispatcher("/productdetails.jsp").forward(request, response);
					
		}
		
		
		if(method!=null)
		{
			if(method.equals("Add"))
			{
				 String addreview= request.getParameter("limitedtextarea");
				 Product myproduct=(Product) request.getSession().getAttribute("myproduct");
				 Minionuser user = (Minionuser) request.getSession().getAttribute("user");
				 
				 Minionreview review=new Minionreview();
				 
				 Date today=new Date();
				 review.setMinionuser(user);
				 review.setProduct(myproduct);
				 review.setReviewtext(addreview);
				 review.setReviewdate(today);
				 review.setIshelpful(0);
				 
				 Dataget.insert(review);
				 
				 List<Minionreview> reviews =Dataget.getProductReviews(myproduct.getProdid());
					
				 session.setAttribute("Productreviews", reviews);
				 HashMap<String,String> imageurls=Dataget.getGravatarUrl(reviews);
					HashMap<String,String> happysadurls=Dataget.gethappysadUrl(reviews);
					
					session.setAttribute("happysadurls", happysadurls);
				    session.setAttribute("imageurls",imageurls ); 	
				 
				 request.getRequestDispatcher("/productdetails.jsp").forward(request, response);
				 
			}
			else if(method.equals("Search"))
			{
				String searchreview= request.getParameter("limitedtextarea");
				 if(searchreview.equals(null))
				 {
					 Product myproduct=(Product) request.getSession().getAttribute("myproduct");
					 List<Minionreview> reviews =Dataget.getProductReviews(myproduct.getProdid());
						
					 session.setAttribute("Productreviews", reviews);
					 HashMap<String,String> imageurls=Dataget.getGravatarUrl(reviews);
						HashMap<String,String> happysadurls=Dataget.gethappysadUrl(reviews);
						
						session.setAttribute("happysadurls", happysadurls);
					    session.setAttribute("imageurls",imageurls ); 	
					 
					 request.getRequestDispatcher("/productdetails.jsp").forward(request, response);
				 }
				 else
				 {
					 List<Minionreview> reviews =Dataget.searchReview(searchreview);
					 session.setAttribute("Productreviews", reviews);
					 HashMap<String,String> imageurls=Dataget.getGravatarUrl(reviews);
						HashMap<String,String> happysadurls=Dataget.gethappysadUrl(reviews);
						
						session.setAttribute("happysadurls", happysadurls);
					    session.setAttribute("imageurls",imageurls ); 	
					 
					 request.getRequestDispatcher("/productdetails.jsp").forward(request, response);
				 }
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
