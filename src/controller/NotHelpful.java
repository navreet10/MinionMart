package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBUtil.Dataget;
import model.Minionreview;
import model.Product;

/**
 * Servlet implementation class NotHelpful
 */
@WebServlet("/NotHelpful")
public class NotHelpful extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotHelpful() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String reviewid=(String) request.getParameter("reviewid");
		long longreviewid=Dataget.getprodreviewid(reviewid);
		
		Minionreview review=Dataget.getreviewbyreviewid(longreviewid);
		
		long helpfulcount=review.getIshelpful();
		
		review.setIshelpful(helpfulcount-1);
		
		Dataget.update(review);
		
		Product myproduct=(Product) request.getSession().getAttribute("myproduct");
		List<Minionreview> reviews =Dataget.getProductReviews(myproduct.getProdid());
		
		session.setAttribute("Productreviews", reviews);
		HashMap<String,String> imageurls=Dataget.getGravatarUrl(reviews);
		HashMap<String,String> happysadurls=Dataget.gethappysadUrl(reviews);
		
		session.setAttribute("happysadurls", happysadurls);
	    session.setAttribute("imageurls",imageurls ); 	
		request.getRequestDispatcher("/productdetails.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
