package taxi.service;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import taxi.model.Customer;
import taxi.model.Taxi;
import taxi.model.TaxiDriver;
import taxi.persistence.JPAUtil;
import taxi.utils.AESEncrypt;
import taxi.utils.Validators;

/**
* The RegistrationService class implements the functions regarding customer, taxi and taxi driver registration.
* 
* @author  Team 4
* @since   Academic Year 2016-2017 
*/

public class RegistrationService {

	EntityManager em;

	//getting the current entity manager
	public RegistrationService() {
		em = JPAUtil.getCurrentEntityManager();
	}

	/** createTaxi method
	 * This method gets as parameters:
	 * <ul>
	 * <li>the carModel
	 * <li>the carType
	 * <li>the licensePlate
	 * <li>the carModelDate
	 * <li>the latitude
	 * <li>the longtitude
	 * </ul>
	 * <p>
	 * The method:
	 * <ul>
	 * <li>Validates the license plate
	 * <li>Validates the car model date
	 * <li>Creates the new taxi object and inserts it in the DB
	 * <li>Finally it returns the new object
	 * </ul>
	 * <b>Note:</b> It is assumed that coordinates are fetched from GPS
	 * <p>
	 * @param carModel type String
	 * @param carType type String
	 * @param licensePlate type String
	 * @param carModelDate type String
	 * @param latitude type double
	 * @param longtitude type double
	 * @return Taxi
	 */
	public Taxi createTaxi(String carModel, String carType, String licensePlate, String carModelDate, double locationLat, double locationLon){

		if(carModel == null || carType == null || licensePlate == null || carModelDate == null 
				|| carModel == " " || carType == " " || licensePlate == " " || carModelDate == " " 
				|| locationLat == 0 || locationLon == 0)
			return null;


		//validations
		if (Validators.validateLicensePlate(licensePlate)){
			if (Validators.validateCarModelDate(carModelDate)){
				Taxi taxi = new Taxi(carModel, carType, licensePlate, carModelDate, locationLat, locationLon);

				EntityTransaction tx = em.getTransaction();
				tx.begin();
				em.persist(taxi);
				tx.commit();

				return taxi;
			}
			else {
				//in case carModelDate validation fails
				System.out.println("Car model date is invalid");
				return null;
			}
		}
		else{
			//in case licensePlate already exists
			System.out.println("Car already connected with other driver, or license plate is invalid");
			return null;
		}
	}

	/** registerTaxiDriver method
	 * This method gets as parameters:
	 * <ul>
	 * <li>the name
	 * <li>the surname
	 * <li>the sex
	 * <li>the username
	 * <li>the password
	 * <li>the dateOfBirth
	 * <li>the address
	 * <li>the city
	 * <li>the zipCode
	 * <li>the email
	 * <li>the creditCardType
	 * <li>the creditCardNumber
	 * <li>the expiryDate
	 * <li>the ccv
	 * <li>the taxi he owns
	 * </ul>
	 * <p>
	 * The method:
	 * <ul>
	 * <li>Validates username
	 * <li>Validates password and then encrypts it
	 * <li>Validates email
	 * <li>Validates credit card
	 * <li>Validates date of birth
	 * <li>Validates that the taxi is not already defined from other driver
	 * <li>Creates the new taxi driver object and inserts it in the DB
	 * <li>Finally it returns the new object
	 * <p>
	 * @param name type String
	 * @param surname type String
	 * @param sex type String
	 * @param username type String
	 * @param password type String
	 * @param dateOfBirth type Date
	 * @param address type String
	 * @param city type String
	 * @param zipCode type int
	 * @param email type String
	 * @param creditCardType type String
	 * @param creditCardNumber type String
	 * @param expiryDate type String
	 * @param ccv type String
	 * @param owns type Taxi
	 * @return TaxiDriver
	 */
	public TaxiDriver registerTaxiDriver(String name, String surname, String sex, String username, String password, Date dateOfBirth, 
			String address, String city, int zipCode, String email, String creditCardType, String creditCardNumber, 
			String expiryDate, String ccv, Taxi owns){

		if(name == null || surname == null || sex == null || username == null 
				|| password == null || dateOfBirth == null || address == null || 
				city == null || zipCode == 0 || email == null || creditCardType == null
				|| creditCardNumber == null || expiryDate == null || ccv == null 
				|| name == " " || surname == " " || sex == " " || username == " " 
				|| password == " " || address == " " || city == " " 
				|| email == " " || creditCardType == " " || creditCardNumber == " " 
				|| expiryDate == " " || ccv == " " || owns == null)
			return null;



		//validations
		if (Validators.validateUsernameTx(username)){
			if (Validators.validatePassword(password)){
				try {
					password = AESEncrypt.encrypt(password);
				}
				catch (Exception e){
					System.out.println(e.getStackTrace());
					//in case an exception occurs
					return null;
				}
				if (Validators.validateEmailTx(email)){
					if (Validators.validateCreditCard(creditCardNumber, expiryDate, ccv)){
						if (Validators.validateDateOfBirth(dateOfBirth)){
							if (Validators.validateTaxi(owns)){
								TaxiDriver taxidr = new TaxiDriver(name, surname, sex, username, 
										password, dateOfBirth, address, city, zipCode, email, 
										creditCardType, creditCardNumber, expiryDate, ccv, owns);

								EntityTransaction tx = em.getTransaction();
								tx.begin();
								em.persist(taxidr);
								tx.commit();

								return taxidr;
							}
							else {
								System.out.println("Taxi already defined");
								//in case the Taxi is already defined from other driver
								return null;
							}
						}
						else {
							System.out.println("Date of birth is invalid");	
							//in case Date of birth is invalid
							return null;
						}
					}
					else {
						System.out.println("Credit Card's details are invalid");
						//in case any of credit card's information is invalid
						return null;
					}
				}
				else {
					System.out.println("Email already in use");
					//in case the email is already in use
					return null;
				}				
			}
			else {
				System.out.println("Invalid password");
				//in case the password is invalid,
				return null;
			}
		}
		else {
			//in case username already exists
			System.out.println("Username already in use");
			return null;
		}
	}

	/** registerCustomer method
	 * This method gets as parameters:
	 * <ul>
	 * <li>the name
	 * <li>the surname
	 * <li>the sex
	 * <li>the username
	 * <li>the password
	 * <li>the dateOfBirth
	 * <li>the latitude
	 * <li>the longtitude
	 * <li>the address
	 * <li>the city
	 * <li>the zipCode
	 * <li>the email
	 * <li>the creditCardType
	 * <li>the creditCardNumber
	 * <li>the expiryDate
	 * <li>the ccv
	 * </ul>
	 * <p>
	 * The method:
	 * <ul>
	 * <li>Validates username
	 * <li>Validates password and then encrypts it
	 * <li>Validates email
	 * <li>Validates credit card
	 * <li>Validates date of birth
	 * <li>Creates the new customer object and inserts it in the DB
	 * <li>Finally it returns the new object
	 * <b>Note:</b> It is assumed that coordinates are fetched from GPS 
	 *  <p>
	 * @param name type String
	 * @param surname type String
	 * @param sex type String
	 * @param username type String
	 * @param password type String
	 * @param dateOfBirth type Date
	 * @param locationLat type double
	 * @param locationLon type double
	 * @param address type String
	 * @param city type String
	 * @param zipCode type int
	 * @param email type String
	 * @param creditCardType type String
	 * @param creditCardNumber type String
	 * @param expiryDate type String
	 * @param ccv type String
	 * @param owns type Taxi
	 * @return Customer
	 */
	public Customer registerCustomer(String name, String surname, String sex, String username, String password, Date dateOfBirth, 
			double locationLat, double locationLon, String address, String city, int zipCode, String email, String creditCardType, String creditCardNumber, 
			String expiryDate, String ccv){

		if(name == null || surname == null || sex == null || username == null 
				|| password == null || dateOfBirth == null || address == null || 
				city == null || zipCode == 0 || email == null || creditCardType == null
				|| creditCardNumber == null || expiryDate == null || ccv == null 
				|| name == " " || surname == " " || sex == " " || username == " " 
				|| password == " " || address == " " || city == " " || email == " " 
				|| creditCardType == " " || creditCardNumber == " " || expiryDate == " " 
				|| ccv == " " || locationLat == 0 || locationLon == 0)
			return null;
			

		//validations
		if (Validators.validateUsernameCst(username)){
			if (Validators.validatePassword(password)){
				try {
					password = AESEncrypt.encrypt(password); 
				}
				catch (Exception e){
					System.out.println(e.getStackTrace());
					//in case an exception occurs
					return null;
				}
				if (Validators.validateEmailCst(email)){
					if (Validators.validateCreditCard(creditCardNumber, expiryDate, ccv)){
						if (Validators.validateDateOfBirth(dateOfBirth)){
							Customer customer = new Customer(name, surname, sex, username, password, dateOfBirth, 
									locationLat, locationLon, address, city, zipCode, email, creditCardType, creditCardNumber, 
									expiryDate, ccv);

							EntityTransaction tx = em.getTransaction();
							tx.begin();
							em.persist(customer);
							tx.commit();

							return customer;
						}							
						else {
							System.out.println("Date of birth is invalid");
							//in case Date of birth is invalid
							return null;
						}						
					}
					else {
						System.out.println("Credit Card's details are invalid");
						//in case any of credit card's information is invalid
						return null;
					}
				}
				else {
					System.out.println("Email already in use");
					//in case the email is already in use
					return null;
				}
			}
			else {
				System.out.println("Invalid password");
				//in case the password is invalid
				return null;
			}		
		}
		else {
			System.out.println("Username already exists");
			//in case username already exists
			return null;
		}
	}
}
