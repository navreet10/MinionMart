import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import DBUtil.Dataget;
import model.Product;

public class Test {

	@org.junit.Test
	public void test() throws NoSuchAlgorithmException {
		
		//test user and password
		String name="Admin";
		
		String pass="admin123!";
		boolean isvalid=Dataget.isValidUser(name, pass);
		System.out.println(isvalid);
		
		//test get products
		
		List<Product> post =Dataget.getProducts();
		for(Product p: post)
		{
			System.out.println(p.getProdname());
		}
		
		
		
		
	}

}
