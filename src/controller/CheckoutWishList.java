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
import model.Cart;
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
		

		String checkout=request.getParameter("checkout");
		
		if(checkout!=null)
		{	
		try {
			
			List<Wishlist> wishItems = CartDao.getWishItems();		
			
			long orderid=CartDao.orderWish(wishItems,((Minionuser)request.getSession().getAttribute("user")).getUsername());
			
			request.setAttribute("message", "Order placed successfully");
			request.getRequestDispatcher("Shopping.jsp").forward(request, response);
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
			HttpSession session = request.getSession();
			List<Wishlist> items = CartDao.getWishItems();
			// set things for shopping
			session.setAttribute("items", items);
			request.getRequestDispatcher("wishList.jsp").forward(request, response);
		}
	}

}