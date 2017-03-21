package taxi.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import taxi.model.Customer;
import taxi.model.TaxiDriver;
import taxi.persistence.JPAUtil;
import taxi.utils.AESEncrypt;

/**
* The LoginService class implements the functions regarding customer and taxi driver login.
* 
* @author  Team 4
* @since   Academic Year 2016-2017 
*/

public class LoginService {
	
	EntityManager em;

	//getting the current entity manager
	public LoginService() {
		em = JPAUtil.getCurrentEntityManager();
	}
	
	/** login Method, applicable for both Taxi Driver and Customer
	 * This method gets as parameters:
	 * <ul>
	 * <li>the usertype (Customer or Taxi Driver)
	 * <li>the username
	 * <li>the password
	 * <li>the latitude and
	 * <li>the longtitude
	 * </ul>
	 * <p>
	 * The method:
	 * <ul>
	 * <li>Checks whether the usertype is customer or taxi driver
	 * <li>Fetches from the appropriate table the customer or taxi driver object having the given username.
	 * <li>If the username does not exist, returns a null object
	 * <li>In case the username is found, it checks whether the given password mathces with the stored.
	 * <li>If the passwords match do not match, returns a null object. Else...
	 * <li>The login is successful and the latitude/longtitude of the customer or taxi are updated
	 * </ul>
	 * <b>Note:</b> The method returns object in order to avoid several similar methods for TaxiDriver and Customer objects
	 * <p>
	 * @param userType type String
	 * @param username type String
	 * @param password type String
	 * @param newLat type double
	 * @param newLon type double
	 * @return Object
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
