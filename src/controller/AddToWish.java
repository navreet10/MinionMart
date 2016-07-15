package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBUtil.Dataget;
import model.Minionuser;
import model.Product;
import model.Wishlist;

/**
 * Servlet implementation class AddToWish
 */
@WebServlet("/AddToWish")
public class AddToWish extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToWish() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String prodid=(String)request.getParameter("prodId");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
