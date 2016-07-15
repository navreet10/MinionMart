package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBUtil.Dataget;
import model.Cart;
import model.Minionuser;
import model.Product;
import model.Wishlist;

/**
 * Servlet implementation class ShoppingServlet
 */
@WebServlet("/ShoppingServlet")
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String method = request.getParameter("method");
		String typeid = (String)request.getParameter("typeid");
		String productid = (String)request.getParameter("productid");
		
		
		
		if(typeid.equals(null)!=true)
		{
			long longtypeid=Dataget.getprodtypeid(typeid);	
			List<model.Product> Products=Dataget.getProductsbytypeid(longtypeid);
			session.setAttribute("Products",Products );
			request.getRequestDispatcher("/Shopping.jsp").forward(request, response);
		}
		else if(method.equals(null)!=true)
		{
			if(method.equals("AddtoCart"))
			{
				String prodid=(String)request.getParameter("prodid");
				long longprodid=Dataget.getprodid(prodid);
				String userid=(String)session.getAttribute("userid");
				long longuserid=Dataget.getuserid(userid);
				Product prod=Dataget.getproductbyproductid(longprodid);
				Minionuser user=Dataget.getuserbyuserid(longuserid);
				
				Cart cart=new Cart();
				
				cart.setMinionuser(user);
				cart.setProduct(prod);
				cart.setQtty(new BigDecimal(1));
				cart.setActive(new BigDecimal(0));
			
				Dataget.insert(cart);
				
				request.getRequestDispatcher("/Shopping.jsp").forward(request, response);
							
			}
			if(method.equals("AddtoWish"))
			{
				
				String prodid=(String)request.getParameter("prodid");
				long longprodid=Dataget.getprodid(prodid);
				String userid=(String)session.getAttribute("userid");
				long longuserid=Dataget.getuserid(userid);
				Product prod=Dataget.getproductbyproductid(longprodid);
				Minionuser user=Dataget.getuserbyuserid(longuserid);
				
				Wishlist wish=new Wishlist();
				
				wish.setMinionuser(user);
				wish.setProduct(prod);
				
				Dataget.insert(wish);
				
				request.getRequestDispatcher("/Shopping.jsp").forward(request, response);
				
			}
			
		}
		else if(productid.equals(null)!=true)
		{
			long longproductid=Dataget.getprodid(productid);
			
			Product myproduct=Dataget.getproductbyproductid(longproductid);
			
			session.setAttribute("myproduct",myproduct );
			
			request.getRequestDispatcher("/Shopping.jsp").forward(request, response);
			
			
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
