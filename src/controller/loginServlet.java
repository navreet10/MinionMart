package controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBUtil.DBUtil;
import DBUtil.Dataget;
import Util.Email;
import dao.OrderDao;
import model.*;

/**
 * Servlet implementation class ProcessForm
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public loginServlet() {
		super();
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

		HttpSession session = request.getSession();
		if(session.getAttribute("incorrectLogins") != null) {
			int incorrectLogins= (Integer) session.getAttribute("incorrectLogins");
		session.setAttribute("incorrectLogins", incorrectLogins +1);
		} else {
			session.setAttribute("incorrectLogins", 0);
		}	
		

		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		if (request.getParameter("register") != null) {
			request.getRequestDispatcher("/Register.jsp").forward(request, response);
		} else {

			try {

				String name = request.getParameter("email");
				String password = request.getParameter("password");
				model.Minionuser user = Dataget.getUserByName(name);

				if (Dataget.isValidUser(name, password)) {
					List<model.Prodtype> types = Dataget.getProdtype();
					List<model.Product> Products = Dataget.getProducts();
					session.setAttribute("user", user);
					session.setAttribute("userid", user.getUserid());
					session.setAttribute("useremail", user.getUseremail());
					session.setAttribute("username", user.getUsername());
					session.setAttribute("userpassword", user.getPwd());
					session.setAttribute("useraddress", user.getUseraddress());
					session.setAttribute("userzipcode", user.getUserzip());
					request.setAttribute("messages", user.getUsername());
					session.setAttribute("images",
							"https://www.gravatar.com/avatar/" + Util.MD5Util.md5Hex(user.getUseremail()) + "?s=80");

					session.setAttribute("Prodtype", types);
					session.setAttribute("Products", Products);
					if (name.equals("Admin")) {
						List<Prodtype> prodtypes= OrderDao.getAllTypes();
						session.setAttribute("types", prodtypes);
						request.getRequestDispatcher("/admin.jsp").forward(request, response);
					} else {
						request.getRequestDispatcher("/Shopping.jsp").forward(request, response);
					}

				} else {
					int incorrectLogins= (Integer) session.getAttribute("incorrectLogins");
					session.setAttribute("incorrectLogins", incorrectLogins +1);
					if (incorrectLogins +1 > 3) {
						System.out.println("sending email....");
						Email.SendEmail("", "", "More than three Logins", 
								"User " + name + " entered password incorrectly more than three times", true);
					}
					request.setAttribute("loginerror", "The user is not valid");

					request.getRequestDispatcher("/login.jsp").forward(request, response);

				}

			} catch (Exception e) {
				e.printStackTrace();
				String message1 = "There is no match";
				System.out.println(e.getMessage());
				request.setAttribute("loginerror", message1);
				request.getRequestDispatcher("/login.jsp").forward(request, response);

			} finally {
				em.close();

			}
		}

	}

}
