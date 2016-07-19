package controller;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.PasswordUtil;

import model.Minionuser;

/**
 * Servlet implementation class userprofileServlet
 */
@WebServlet("/userprofileServlet")
public class userprofileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userprofileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		DBUtil.EmailValidator emailvalidator=new DBUtil.EmailValidator();
	
		try
		{
		
			String username=request.getParameter("username");
			String userpassword=request.getParameter("userpassword");
			String useraddress=request.getParameter("useraddress");
			String useremail=request.getParameter("useremail");
			String userzipcode=request.getParameter("userzipcode");
			
			if(username!=null&&useraddress!=null&&useremail!=null&&userzipcode!=null)
			{
				if(emailvalidator.validate(useremail))
				{
					Minionuser user = (Minionuser) request.getSession().getAttribute("user");
					if(userpassword==null)
					{
						
						user.setUseremail(useremail);
						user.setUsername(username);
						user.setUseraddress(useraddress);
						user.setUserzip(userzipcode);
						
					}else
					{
						user.setUseremail(useremail);
						user.setUsername(username);
						String passsecure=PasswordUtil.getSalt();
						String hashcode=PasswordUtil.hashPasswordPlusSalt(userpassword, passsecure);
						
						user.setPwd(hashcode);
						user.setPwdsecure(passsecure);
						user.setUseraddress(useraddress);
						user.setUserzip(userzipcode);
						
					}
					
					
					DBUtil.Dataget.update(user);

					session.setAttribute("user", user);
					session.setAttribute("userid", user.getUserid());
					session.setAttribute("useremail",user.getUseremail());
					session.setAttribute("username", user.getUsername());
					session.setAttribute("userpassword", user.getPwd());
					session.setAttribute("useraddress", user.getUseraddress());
					session.setAttribute("userzipcode", user.getUserzip());
					session.setAttribute("images", "https://www.gravatar.com/avatar/"+Util.MD5Util.md5Hex(user.getUseremail())+"?s=80");
					request.setAttribute("result", "Update successfully");
					
					request.getServletContext().getRequestDispatcher("/userprofile.jsp").forward(request, response);;
				}
			}
			else
			{
				session.setAttribute("result", "There is a null text");
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
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
