package DBUtil;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.*;

public class DBUtil {
	
	private static final EntityManagerFactory emf=Persistence.createEntityManagerFactory("MinionMart");
	public static EntityManagerFactory getEmFactory(){
		return emf;
	}

}
