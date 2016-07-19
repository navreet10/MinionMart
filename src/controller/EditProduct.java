package controller;

import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.CartDao;
import dao.OrderDao;
import model.Product;

/**
 * Servlet implementation class UpdateOrder
 */
@WebServlet("/EditProduct")
public class EditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProduct() {
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
			String prodId = request.getParameter("productId");	
			String prodName = request.getParameter("productName");	
			if (prodName == null) {
				Product product = CartDao.getProduct(prodId);
				request.setAttribute("prod", product);
				request.setAttribute("productId", prodId);
			} else {
				Product product = CartDao.getProduct(prodId);
				product.setProddesc(request.getParameter("productDesc"));
				product.setProdname(request.getParameter("productName"));
				product.setProdurl(request.getParameter("productUrl"));
				product.setProdprice(Float.parseFloat((request.getParameter("productPrice"))));
				OrderDao.updateProduct(product);
				request.setAttribute("message", "Updated Successfully");
			}
			
			
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
