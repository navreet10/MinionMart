package controller;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.PasswordUtil;


/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				HttpSession session = request.getSession();		
				
				DBUtil.EmailValidator emailvalidator=new DBUtil.EmailValidator();
				String method=request.getParameter("method");
				if(method.equals("Create"))
				{
				try
				{
					String username=request.getParameter("username");
					String userpassword=request.getParameter("userpassword");
					String useraddress=request.getParameter("useraddress");
					String useremail=request.getParameter("useremail");
					String userzipcode=request.getParameter("userzipcode");
					
					if(username!=null&&userpassword!=null&&useraddress!=null&&useremail!=null&&userzipcode!=null)
					{
						if(emailvalidator.validate(useremail))
						{
							model.Minionuser user=new model.Minionuser();
							user.setUseremail(useremail);
							user.setUsername(username);
			
							String passsecure=PasswordUtil.getSalt();
							String hashcode=PasswordUtil.hashPasswordPlusSalt(userpassword, passsecure);
							user.setPwd(hashcode);
							user.setPwdsecure(passsecure);
							
							user.setUseraddress(useraddress);
							user.setUserzip(userzipcode);
							
							DBUtil.Dataget.insert(user);
							
							
							request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);;
							
						}
						else
						{
							request.setAttribute("result", "The email is not valided");
						}
					}
					else
					{
						request.setAttribute("result", "There is a null text");
					}
					
					
				}
				catch(Exception e){
					e.printStackTrace();
				}
				}
				else if(method.equals("Back"))
				{
					request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);;
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
