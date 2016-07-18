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
import model.Wishlist;

/**
 * Servlet implementation class DeletefromWish
 */
@WebServlet("/DeletefromWish")
public class DeletefromWish extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletefromWish() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String wishId = request.getParameter("wishid");	
		Wishlist wish=dao.CartDao.getWishbywishid(wishId);
		
		CartDao.delete(wish);
		
		List<Wishlist> items = CartDao.getWishItems();
		// set things for shopping
		session.setAttribute("items", items);
		request.getRequestDispatcher("/wishList.jsp").forward(request, response);
	}

}
