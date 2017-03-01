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

public class RegistrationService {

	EntityManager em;

	//getting the current entity manager
	public RegistrationService() {
		em = JPAUtil.getCurrentEntityManager();
	}

	//REGISTRATION METHODS

	/* Registration for Taxi and TaxiDriver
	 * we receive all the information needed for the creation of a taxi.
	 * we check if any value is empty/null. we assume that location is fetched from GPS
	 * The taxi is then created and inserted in the DB.
	 * 
	 * the object taxi which was created is returned as a result
	 */
	public Taxi createTaxi(String carModel, String carType, String licensePlate, String carModelDate, double locationLat, double locationLon){

		if(carModel == null || carType == null || licensePlate == null || carModelDate == null || locationLat == 0 || locationLon == 0)
			return null;


		//validations
		if (Validators.validateLicensePlate(licensePlate))
			if (Validators.validateCarModelDate(carModelDate)){
				Taxi taxi = new Taxi(carModel, carType, licensePlate, carModelDate, locationLat, locationLon);

				EntityTransaction tx = em.getTransaction();
				tx.begin();
				em.persist(taxi);
				tx.commit();

				return taxi;
			}
			else {
				//in case licensePlate already exists
				System.out.println("Car already connected with other driver, or license plate is invalid");
				return null;
			}
		else{
			//in case carModelDate validation fails
			System.out.println("Car model date is invalid");
			return null;
		}
	}

	/* We receive all the information needed for the creation of a taxi driver.
	 * we check if any value is empty/null. we assume that taxi of the driver is already created
	 * The taxi driver is then created and inserted in the DB.
	 * 
	 * the object taxi driver which was created is returned as a result
	 */
	public TaxiDriver registerTaxiDriver(String name, String surname,String sex, String username, String password, Date dateOfBirth, 
			String address, String city, int zipCode, String email, String creditCardType, String creditCardNumber, 
			String expiryDate, String ccv, Taxi owns){

		if(name == null || surname == null || sex == null || username == null 
				|| password == null || dateOfBirth == null || address == null || 
				city == null || zipCode == 0 || email == null || creditCardType == null
				|| creditCardNumber == null || expiryDate == null || ccv == null || owns == null)
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

	/* Registration for Customer
	 * we receive all the information needed for the creation of a customer.
	 * we check if any value is empty/null. we assume that location is fetched from GPS
	 * The customer is then created and inserted in the DB.
	 * 
	 * the object customer which was created is returned as a result
	 */
	public Customer registerCustomer(String name, String surname, String sex, String username, String password, Date dateOfBirth, 
			double locationLat, double locationLon, String address, String city, int zipCode, String email, String creditCardType, String creditCardNumber, 
			String expiryDate, String ccv){

		if(name == null || surname == null || sex == null || username == null 
				|| password == null || dateOfBirth == null || address == null || 
				city == null || zipCode == 0 || email == null || creditCardType == null
				|| creditCardNumber == null || expiryDate == null || ccv == null || locationLat == 0 || locationLon == 0)
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
