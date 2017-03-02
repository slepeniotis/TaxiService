package taxi.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import taxi.model.Customer;
import taxi.model.TaxiDriver;
import taxi.persistence.JPAUtil;
import taxi.utils.AESEncrypt;

public class LoginService {
	
	EntityManager em;

	//getting the current entity manager
	public LoginService() {
		em = JPAUtil.getCurrentEntityManager();
	}
	
	/* Identification Method
	 * We are receiving as input the user type which wants to be signed in (selected by the user).
	 * We also receive the username, password and new coordinates
	 * we check if any of the inputs is empty/null
	 * then we check the login type. 
	 * - In case it is for customer, then we search in the table of customers 
	 *   to find the customer with the specific username and password (encrypted)
	 * - In case it is for taxi driver, then we search in the table of taxi drivers 
	 *   to find the customer with the specific username and password (encrypted) 
	 * If the user is not found, or any exception is raised, the return object is null, 
	 * or else the found user object
	 */	
	public Object login(String userType, String username, String password, double newLat, double newLon){
		Object result = null;

		if(userType == null || username == null || password == null 
				|| userType == " " || username == " " || password == " " 
				|| newLat == 0 || newLon == 0)
			return result;

		if(userType == "Customer"){
			String pasEncr;
			Query query = em.createQuery("select cust from Customer cust where username like :usrnm AND password like :passwd");
			query.setParameter("usrnm", username);
			try{
				pasEncr = AESEncrypt.encrypt(password);
			}
			catch(Exception e){
				return result;
			}

			query.setParameter("passwd", pasEncr);
			List<Customer> rsltcst = query.getResultList();

			if(rsltcst.isEmpty()){
				return result;
			}

			EntityTransaction tx = em.getTransaction();
			tx.begin();				
			rsltcst.get(0).setLocationLat(newLat);
			rsltcst.get(0).setLocationLon(newLon);			
			tx.commit();

			result = rsltcst.get(0);
		}
		else if(userType == "Taxi Driver"){
			String pasEncr;
			Query query = em.createQuery("select taxidr from TaxiDriver taxidr where username like :usrnm AND password like :passwd");
			query.setParameter("usrnm", username);
			try{
				pasEncr = AESEncrypt.encrypt(password);
			}
			catch(Exception e){
				return result;
			}

			query.setParameter("passwd", pasEncr);
			List<TaxiDriver> rslttxdr = query.getResultList();

			if(rslttxdr.isEmpty()){
				return result;
			}

			EntityTransaction tx = em.getTransaction();
			tx.begin();				
			rslttxdr.get(0).getOwns().setLocationLat(newLat);
			rslttxdr.get(0).getOwns().setLocationLon(newLon);			
			tx.commit();

			result = rslttxdr.get(0);

		}

		return result;
	}
}
