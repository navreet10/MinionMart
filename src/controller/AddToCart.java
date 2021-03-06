package controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;
import model.Cart;
import model.Minionuser;
import model.Product;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public AddToCart() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Minionuser minionuser = (Minionuser) request.getSession().getAttribute("user");
			String prodId = request.getParameter("prodId");		
			Cart cart = new Cart();
			cart.setActive(new BigDecimal(0));
			cart.setMinionuser(minionuser);
			cart.setQtty(1L);
			Product prod = CartDao.getProduct(prodId);
			cart.setProduct(prod);
			CartDao.insertCart(cart);
			// set things for shopping
			request.setAttribute("message", "Added to cart successfully");
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
