package controller;

import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.OrderDao;
import model.Prodtype;
import model.Product;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProduct() {
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
			String name = request.getParameter("productName");	
			String desc = request.getParameter("productDesc");	
			String url = request.getParameter("productUrl");	
			String price = request.getParameter("productPrice");	
			String typeId = request.getParameter("typeId");	
			Prodtype type = OrderDao.getType(typeId);
				Product product = new Product();
				product.setProddesc(desc);
				product.setProdname(name);
				product.setProdurl(url);
				product.setProdprice(Float.parseFloat(price));
				product.setProdtype(type);
				OrderDao.addProduct(product);
				request.setAttribute("message", "Updated Successfully");
			
			
			
			request.getRequestDispatcher("admin.jsp").forward(request, response);

		} catch (NullPointerException e) {
			e.printStackTrace();
			request.setAttribute("message", "Something went wrong!!");
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Something went wrong!!");
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}
	}

}
