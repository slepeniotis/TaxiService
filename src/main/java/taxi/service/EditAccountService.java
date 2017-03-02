package taxi.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import taxi.model.Customer;
import taxi.model.TaxiDriver;
import taxi.persistence.JPAUtil;
import taxi.utils.Validators;

public class EditAccountService {

	EntityManager em;

	//getting the current entity manager
	public EditAccountService() {
		em = JPAUtil.getCurrentEntityManager();
	}

	/* Change of address (Taxi Driver/Customer)
	 * given the usertype and the userid, we fetch from the DB the appropriate record 
	 * and change the address 
	 * 
	 * we assume that usertype is given automatically by the application
	 * 
	 * The object which was changed, is returned as result 
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

	/* Change of Credit card (Taxi Driver/Customer)
	 * given the usertype and the userid, we fetch from the DB the appropriate record 
	 * and change the credit card after its validation 
	 * 
	 * we assume that usertype is given automatically by the application
	 * 
	 * The object which was changed, is returned as result 
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

	/* Change of email (Taxi Driver/Customer)
	 * given the usertype and the userid, we fetch from the DB the appropriate record 
	 * and change the email after its validation 
	 * 
	 * we assume that usertype is given automatically by the application
	 * 
	 * The object which was changed, is returned as result 
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
