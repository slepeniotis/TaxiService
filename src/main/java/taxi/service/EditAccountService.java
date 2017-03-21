package taxi.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import taxi.model.Customer;
import taxi.model.TaxiDriver;
import taxi.persistence.JPAUtil;
import taxi.utils.Validators;


/**
* The EditAccountService class implements the functions regarding customer and taxi driver account editing.
* 
* @author  Team 4
* @since   Academic Year 2016-2017 
*/
public class EditAccountService {

	EntityManager em;

	//getting the current entity manager
	public EditAccountService() {
		em = JPAUtil.getCurrentEntityManager();
	}

	/** changeAddress method, applicable for both Taxi Driver and Customer
	 * This method gets as parameters:
	 * <ul>
	 * <li>the usertype (Customer or Taxi Driver)
	 * <li>the userid
	 * <li>the new address
	 * <li>the new city and
	 * <li>the new zip code
	 * </ul>
	 * <p>
	 * The appropriate record is fetched from the DB.
	 * The object which was changed, is returned as result.
	 * <b>Note:</b> The method returns object in order to avoid several similar methods for TaxiDriver and Customer objects
	 * <p>
	 * @param userType type String
	 * @param userId type long
	 * @param address type String
	 * @param city type String
	 * @param zipCode type int
	 * @return Object
	 */
	public Object changeAddress(String userType, long userId, String address, String city, int zipCode){

		if(address == null || address == " " || city == null || city == " " || zipCode == 0)
			return null;

		if(userType == "Customer") {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			Customer savedCustomer = em.find(Customer.class, userId);
			if(savedCustomer != null){
				savedCustomer.setAddress(address);
				savedCustomer.setCity(city);
				savedCustomer.setZipCode(zipCode);
			}
			tx.commit();
			return savedCustomer;			
		}
		else if (userType == "Taxi Driver"){
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			TaxiDriver savedTaxiDriver = em.find(TaxiDriver.class, userId);
			if(savedTaxiDriver != null){
				savedTaxiDriver.setAddress(address);
				savedTaxiDriver.setCity(city);
				savedTaxiDriver.setZipCode(zipCode);
			}
			tx.commit();
			return savedTaxiDriver;
		}
		return null;
	}

	/** changeCreditCard method, applicable for both Taxi Driver and Customer
	 * This method gets as parameters:
	 * <ul>
	 * <li>the usertype (Customer or Taxi Driver)
	 * <li>the userid
	 * <li>the new creditCardType
	 * <li>the new creditCardNumber
	 * <li>the new expiryDate and
	 * <li>the new ccv
	 * </ul>
	 * <p>
	 * The appropriate record is fetched from the DB.
	 * After the validation, the credit card is changed.
	 * The object which was changed, is returned as result.
	 * <b>Note:</b> The method returns object in order to avoid several similar methods for TaxiDriver and Customer objects
	 * <p>
	 * @param userType type String
	 * @param userId type long
	 * @param creditCardType type String
	 * @param creditCardNumber type String
	 * @param expiryDate type String
	 * @param ccv type String
	 * @return Object
	 */
	public Object changeCreditCard(String userType, long userId, String creditCardType, String creditCardNumber, 
			String expiryDate, String ccv){

		if(creditCardType == null || creditCardNumber == null || expiryDate == null || ccv == null
				|| creditCardType == " " || creditCardNumber == " " || expiryDate == " " || ccv == " ")
			return null;

		if(userType == "Customer") {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			Customer savedCustomer = em.find(Customer.class, userId);
			if(savedCustomer != null){
				if (Validators.validateCreditCard(creditCardNumber, expiryDate, ccv)){
					savedCustomer.setCreditCardType(creditCardType);
					savedCustomer.setCreditCardNumber(creditCardNumber);
					savedCustomer.setExpiryDate(expiryDate);
					savedCustomer.setCcv(ccv);
				}
			}
			tx.commit();
			return savedCustomer;			
		}
		else if (userType == "Taxi Driver"){
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			TaxiDriver savedTaxiDriver = em.find(TaxiDriver.class, userId);
			if(savedTaxiDriver != null){
				if (Validators.validateCreditCard(creditCardNumber, expiryDate, ccv)){
					savedTaxiDriver.setCreditCardType(creditCardType);
					savedTaxiDriver.setCreditCardNumber(creditCardNumber);
					savedTaxiDriver.setExpiryDate(expiryDate);
					savedTaxiDriver.setCcv(ccv);
				}
			}
			tx.commit();
			return savedTaxiDriver;
		}
		return null;
	}

	/** changeEmail method, applicable for both Taxi Driver and Customer
	 * This method gets as parameters:
	 * <ul>
	 * <li>the usertype (Customer or Taxi Driver)
	 * <li>the userid
	 * <li>the new email
	 * </ul>
	 * <p>
	 * The appropriate record is fetched from the DB.
	 * After the validation, the email is changed.
	 * The object which was changed, is returned as result.
	 * <b>Note:</b> The method returns object in order to avoid several similar methods for TaxiDriver and Customer objects
	 * <p>
	 * @param userType type String
	 * @param userId type long
	 * @param email type String
	 * @return Object
	 */
	public Object changeEmail(String userType, long userId, String email){

		if(email == null || email == " ")
			return null;

		if(userType == "Customer") {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			Customer savedCustomer = em.find(Customer.class, userId);
			if(savedCustomer != null){
				if (Validators.validateEmailCst(email)){
					savedCustomer.setEmail(email);
				}
			}
			tx.commit();
			return savedCustomer;			
		}
		else if (userType == "Taxi Driver"){
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			TaxiDriver savedTaxiDriver = em.find(TaxiDriver.class, userId);
			if(savedTaxiDriver != null){
				if (Validators.validateEmailTx(email)){
					savedTaxiDriver.setEmail(email);
				}
			}
			tx.commit();
			return savedTaxiDriver;
		}
		return null;
	}
}
