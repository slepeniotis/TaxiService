package taxi.utils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import taxi.model.Customer;
import taxi.model.Taxi;
import taxi.model.TaxiDriver;
import taxi.persistence.JPAUtil;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

/**The Validators class implements all the validation checks for the taxi service.
 *  
 * @author  Team 4
 * @since   Academic Year 2016-2017 
 */
public class Validators {

	/** validateUsernameCst method 
	 * This method is used for validating username of a customer.
	 * Searches in the appropriate table of the DB to find if another user with the same username exists.
	 * In case the username does not already exist, the validation is successful (true).
	 * 
	 * @param username type String
	 * @return boolean true if validation is successful/false if it is not
	 */
	public static boolean validateUsernameCst(String username){
		//it is not allowed to have a username "ERROR" as this is reserved word for our implementation
		if(username == " " || username == "ERROR")
			return false;

		EntityManager em = JPAUtil.getCurrentEntityManager();

		Query query = em.createQuery("select cust from Customer cust where username like :usrnm");
		query.setParameter("usrnm", username); 
		List<Customer> results = query.getResultList();

		if(!results.isEmpty()){
			return false;
		}		

		return true;

	}

	/** validateUsernameTx method 
	 * This method is used for validating username of a taxi driver.
	 * Searches in the appropriate table of the DB to find if another user with the same username exists.
	 * In case the username does not already exist, the validation is successful (true).
	 * 
	 * @param username type String
	 * @return boolean true if validation is successful/false if it is not
	 */
	public static boolean validateUsernameTx(String username){
		//it is not allowed to have a username "ERROR" as this is reserved word for our implementation
		if(username == " " || username == "ERROR")
			return false;

		EntityManager em = JPAUtil.getCurrentEntityManager();

		Query query = em.createQuery("select taxdr from TaxiDriver taxdr where username like :usrnm");
		query.setParameter("usrnm", username);  
		query.setMaxResults(1);
		List<Customer> results = query.getResultList();

		if(!results.isEmpty()){
			return false;
		}

		return true;

	}

	/** validatePassword method 
	 * This method is used for validating password.
	 * <ul>
	 * <li>At first it creates two patterns: one for capital letters and one for numeric digits.
	 * <li>Then it checks the length of the password: It is not allowed to be empty and less than 8 characters.
	 * <li>After this, it checks if the password has at least one capital letter and one number.
	 * </ul> 
	 * 
	 * @param password type String
	 * @return boolean true if validation is successful/false if it is not
	 */
	public static boolean validatePassword(String password){
		final Pattern hasUppercase = Pattern.compile("[A-Z]");
		final Pattern hasNumber = Pattern.compile("\\d");

		/* if the check was password.length() < 8 || password.length() < 1
		 * the case where the password is empty, would have been missed
		 */
		if (password == null || password.length() < 1 || password.length() < 8)
			return false;
		else if (!hasUppercase.matcher(password).find())
			return false;
		else if (!hasNumber.matcher(password).find())
			return false;

		return true;
	}

	/** validateEmailCst method 
	 * This method is used for validating customer's email.
	 * <ul>
	 * <li>At first creates a pattern which checks that the email has the appropriate format: user@domain.xx.
	 * <li>If the email matches that pattern, it checks if this email exists already in the table of the DB.
	 * <li>If the email does not exist, then the validation is completed successfully (true)
	 * </ul>
	 * 
	 * @param email type String
	 * @return boolean true if validation is successful/false if it is not
	 */
	public static boolean validateEmailCst(String email){
		if(email == " ")
			return false;

		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern p = Pattern.compile(ePattern);
		Matcher m = p.matcher(email);
		EntityManager em = JPAUtil.getCurrentEntityManager();
		StackTraceElement ste[] = Thread.currentThread().getStackTrace(); 

		if(m.matches()){
			Query query = em.createQuery("select cust from Customer cust where email = :eml");
			query.setParameter("eml", email);  
			query.setMaxResults(1);
			List<Customer> results = query.getResultList();

			if(!results.isEmpty()){
				return false;
			}			
			return true;
		}

		return false;

	}

	/** validateEmailTx method
	 * This method is used for validating taxi driver's email.
	 * <ul>
	 * <li>At first creates a pattern which checks that the email has the appropriate format: user@domain.xx.
	 * <li>If the email matches that pattern, it checks if this email exists already in the table of the DB.
	 * <li>If the email does not exist, then the validation is completed successfully (true) 
	 * </ul>
	 * 
	 * @param email type String
	 * @return boolean true if validation is successful/false if it is not
	 */
	public static boolean validateEmailTx(String email){
		if(email == " ")
			return false;

		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern p = Pattern.compile(ePattern);
		Matcher m = p.matcher(email);
		EntityManager em = JPAUtil.getCurrentEntityManager();
		StackTraceElement ste[] = Thread.currentThread().getStackTrace(); 

		if(m.matches()){
			Query query = em.createQuery("select taxdr from TaxiDriver taxdr where email = :eml");
			query.setParameter("eml", email);  
			query.setMaxResults(1);
			List<Customer> results = query.getResultList();

			if(!results.isEmpty()){
				return false;
			}			
			return true;
		}

		return false;

	}

	/** validateCreditCard method
	 * This method is used for validating credit card.
	 * Receives as parameters all the relevant information of a credit card, except the type.
	 * <ul>
	 * <li>At the first step checks if the credit card number has 16 characters and the ccv 3 characters
	 * <li>At the second step checks that credit card number and ccv consist of numbers 
	 * <li>Expiry date is represented as MM/YY. String that includes the expiry date, is splitted to month and year.
	 * <li>Then this date is checked whether has passed or not
	 * </ul>
	 * 
	 * @param creditCardNumber type String
	 * @param expiryDate type String
	 * @param ccv type String
	 * @return boolean true if validation is successful/false if it is not
	 */
	public static boolean validateCreditCard(String creditCardNumber, String expiryDate, String ccv){

		if(creditCardNumber.length() != 16 || ccv.length() != 3)
			return false;
		if (!creditCardNumber.matches("[0-9]+"))
			return false;
		if(!ccv.matches("[0-9]+"))
			return false;

		String month = expiryDate.substring(0, 2);
		String year = expiryDate.substring(3, 5);
		
		if(!month.matches("[0-9]+"))
			return false;
		if(!year.matches("[0-9]+"))
			return false;
		
		if(Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12)
			return false;

		//getting current month and year in two digits
		Calendar now = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yy");
		String curYear = sdf.format(Calendar.getInstance().getTime());
		sdf = new SimpleDateFormat("MM");
		String curMonth = sdf.format(Calendar.getInstance().getTime());

		//comparing expiry year with current year
		if (Integer.parseInt(year) == Integer.parseInt(curYear))
			//comparing expiry month with current month
			if ((Integer.parseInt(month) > Integer.parseInt(curMonth)))
				return true;

		if (Integer.parseInt(year) > Integer.parseInt(curYear))
			return true;

		return false;

	}

	/** validateDateOfBirth method
	 * This method is used for validating date of birth.
	 * Receives as parameter the date of birth of a user.
	 * Then is checked if this date is before the current date.
	 *  
	 * @param DateOfBirth type Date
	 * @return boolean true if validation is successful/false if it is not  
	 */
	public static boolean validateDateOfBirth(Date DateOfBirth){

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();

		if(currentDate.before(DateOfBirth) || currentDate.equals(DateOfBirth))				
			return false;

		return true;
	}

	/** validateLicensePlate method
	 * This method is used for validating taxi.
	 * Receives as parameter the license plate of a taxi.
	 * It is assumed that a license plate consists of 3 capital letters and 4 numbers.
	 * <ul>
	 * <li>At first step the length of the license plate is checked. It has to be 7 and not empty
	 * <li>Secondly, it is splitted to its letters and numbers.
	 * <li>We then assure that the letters variable has no numbers, and that the numbers variable has only numbers
	 * </ul>
	 *   
	 * @param licensePlate type String
	 * @return boolean true if validation is successful/false if it is not  
	 */
	public static boolean validateLicensePlate(String licensePlate){
		final Pattern hasNumber = Pattern.compile("\\d");
		final Pattern hasLowercase = Pattern.compile("[a-z]");
		final Pattern hasUppercase = Pattern.compile("[A-Z]");
		
		if (licensePlate.length() != 7)
			return false;

		String letters = licensePlate.substring(0, 3);
		String numbers = licensePlate.substring(3,7);		

		if (hasNumber.matcher(letters).find())
			return false;
		else if (hasLowercase.matcher(numbers).find() || hasUppercase.matcher(numbers).find())
			return false;	
		
		EntityManager em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxi from Taxi taxi where licensePlate like :licplt");	
		query.setParameter("licplt", licensePlate);
		List<Taxi> result = query.getResultList();
		
		if(result.isEmpty())
			return true;

		return false;
	}

	/** validateCarModelDate method
	 * This method is used for validating car model production date.
	 * Receives as parameter the carModelDate of a taxi.
	 * It is assumed that a car model date has the format MM/YYYY.
	 * <ul>
	 * <li>At first step the length of the car model date is checked. It has to be 7 and not empty
	 * <li>Secondly, the date is splitted to month and year.
	 * <li>Then this date is checked whether has passed or not
	 * 
	 * @param carModelDate type String
	 * @return boolean true if validation is successful/false if it is not  
	 */
	public static boolean validateCarModelDate(String carModelDate){

		if(carModelDate.length() != 7)
			return false;

		String month = carModelDate.substring(0, 2);
		String year = carModelDate.substring(3, 7);	

		if (!month.matches("[0-9]+"))
			return false;
		else if (!year.matches("[0-9]+"))
			return false;

		if(Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12)
			return false;

		//getting current month and year in two digits
		Calendar now = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String curYear = sdf.format(Calendar.getInstance().getTime());
		sdf = new SimpleDateFormat("MM");
		String curMonth = sdf.format(Calendar.getInstance().getTime());

		if(Integer.parseInt(curYear) == Integer.parseInt(year)){
			if(Integer.parseInt(curMonth) < Integer.parseInt(month)){
				return false;
			}
		}
		else if(Integer.parseInt(curYear) < Integer.parseInt(year))
			return false;


		return true;
	}

	/** validateTaxi method
	 * This method is used for validating that a taxi is not already defined.
	 * Receives as parameter the taxi object.
	 * Then a check is made within the table TaxiDriver that this object is not already defined from other driver, using the equals method
	 *   
	 * @param taxi type Taxi
	 * @return boolean true if validation is successful/false if it is not  
	 */
	public static boolean validateTaxi(Taxi taxi){

		EntityManager em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxdr from TaxiDriver taxdr");		
		List<TaxiDriver> results = query.getResultList();

		if(results.isEmpty())
			return true;

		for(TaxiDriver td : results)
			if(td.getOwns().equals(taxi))
				return false;

		return true;		
	}
}
