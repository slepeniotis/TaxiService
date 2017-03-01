package taxi.service;

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

		if(userType == null || username == null || password == null || newLat ==0 || newLon == 0)
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
			Customer rsltcst = (Customer)query.getSingleResult();

			if(rsltcst.equals(null)){
				return result;
			}

			EntityTransaction tx = em.getTransaction();
			tx.begin();				
			rsltcst.setLocationLat(newLat);
			rsltcst.setLocationLon(newLon);			
			tx.commit();

			result = rsltcst;
		}
		else if(userType == "Taxi Driver"){
			String pasEncr;
			Query query = em.createQuery("select taxidr from taxidriver taxidr where username like :usrnm AND password like :passwd");
			query.setParameter("usrnm", username);
			try{
				pasEncr = AESEncrypt.encrypt(password);
			}
			catch(Exception e){
				return result;
			}

			query.setParameter("passwd", pasEncr);
			TaxiDriver rslttxdr = (TaxiDriver)query.getSingleResult();

			if(rslttxdr.equals(null)){
				return result;
			}

			EntityTransaction tx = em.getTransaction();
			tx.begin();				
			rslttxdr.getOwns().setLocationLat(newLat);
			rslttxdr.getOwns().setLocationLon(newLon);			
			tx.commit();

			result = rslttxdr;

		}

		return result;
	}
}
