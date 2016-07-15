package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;
import model.Cart;
import model.Minionuser;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
        super();
        // TODO Auto-generated constructor stub
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
		try {
			
			List<Cart> cartItems = CartDao.getCartItems();
			List<Cart> cartUpdated = new ArrayList<Cart>();
			for (Cart cart: cartItems) {
				cart.setQtty(Long.parseLong(request.getParameter("qtty"+cart.getCartid())));
				cartUpdated.add(cart);
			}
			CartDao.order(cartUpdated,((Minionuser)request.getSession().getAttribute("user")).getUsername());
			// set things for shopping
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

}
