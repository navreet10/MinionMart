package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDao;
import model.Cart;

/**
 * Servlet implementation class DeletefromCart
 */
@WebServlet("/DeletefromCart")
public class DeletefromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletefromCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		String cartId = request.getParameter("cartid");	
		Cart cart=dao.CartDao.getCartbyCartid(cartId);
		
		CartDao.delete(cart);
		
		List<Cart> items = CartDao.getCartItems();
		
		// set things for shopping
		session.setAttribute("items", items);
		request.getRequestDispatcher("/viewCart.jsp").forward(request, response);
		
	}

}
