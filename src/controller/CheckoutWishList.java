package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDao;
import dao.OrderDao;
import model.Cart;
import model.Minionorder;
import model.Minionuser;
import model.Wishlist;

@WebServlet("/CheckoutWishList")
public class CheckoutWishList extends HttpServlet{
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutWishList(){
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String checkout=request.getParameter("checkout");
		
		if(checkout!=null)
		{	
		try {
			
			List<Wishlist> wishItems = CartDao.getWishItems();		
			
			String ordername=CartDao.orderWish(wishItems,((Minionuser)request.getSession().getAttribute("user")).getUsername());
			
			request.setAttribute("message", "Order placed successfully");
			
			List<Minionorder> orders=OrderDao.getOrder(ordername);
			float price=OrderDao.getpricetotal(orders);
			float tax=(float) (price*0.06);
			float shippingprice=(float) 5.6;
			float total=price+shippingprice+tax;
			session.setAttribute("ordername", ordername);
			session.setAttribute("price", price);
			session.setAttribute("tax", tax);
			session.setAttribute("shippingprice", shippingprice);
			session.setAttribute("total", total);
			
			request.getRequestDispatcher("order.jsp").forward(request, response);
		} catch (NullPointerException e) {
			e.printStackTrace();
			request.setAttribute("message", "Something went wrong!!");
			request.getRequestDispatcher("Shopping.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Something went wrong!!");
			request.getRequestDispatcher("Shopping.jsp").forward(request, response);
		}
		}
		else
		{
			
			List<Wishlist> items = CartDao.getWishItems();
			// set things for shopping
			session.setAttribute("items", items);
			request.getRequestDispatcher("wishList.jsp").forward(request, response);
		}
	}

}