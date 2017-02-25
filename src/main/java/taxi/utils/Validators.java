package taxi.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import taxi.model.Customer;
import taxi.persistence.JPAUtil;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;

//class which includes all the methods related to validation checks
public class Validators {

	/* Method validating username.
	 * At first checks from which class it was called, Customer or TaxiDriver
	 * Then searches in the appropriate table of the DB to find if another user 
	 * with the same username exists.
	 * In case the username does not already exist, the validation was successful (true).
	 */
	public static boolean validateUsername(String username){
		//it is not allowed to have a username "ERROR" as this is reserved word for our implementation
		if(username == null || username == " " || username == "ERROR")
			return false;

		EntityManager em = JPAUtil.getCurrentEntityManager();

		StackTraceElement ste[] = Thread.currentThread().getStackTrace();
		if(ste[2].getClassName() == "taxi.model.Customer"){

			Query query = em.createQuery("select cust from Customer cust where username like :usrnm");
			query.setParameter("usrnm", username); 
			List<Customer> results = query.getResultList();

			if(!results.isEmpty()){
				return false;
			}

		}
		else if(ste[2].getClassName() == "taxi.model.TaxiDriver"){
			Query query = em.createQuery("select taxdr from TaxiDriver taxdr where username like :usrnm");
			query.setParameter("usrnm", username);  
			query.setMaxResults(1);
			List<Customer> results = query.getResultList();

			if(!results.isEmpty()){
				return false;
			}
		}

		return true;

	}

	/* Method validating password.
	 * At first creates two patterns: one for capital letters and one for numeric digits.
	 * Then it checks the length of the password: It is not allowed to be empty and less 
	 * than 8 characters.
	 * After this it checks if the password has at least one capital letter and one number. 
	 * If the checks are passed, the validation was completed successfully (true) 
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

	/* Method validating email.
	 * At first creates a pattern which checks that the email has the appropriate format: user@domain.xx.
	 * If the email matches that pattern, it checks from which class it was called, Customer or TaxiDriver.
	 * Then it checks if this email exists already in the appropriate table of the DB.
	 * If the email does not exist, then the validation was completed successfully (true) 
	 */
	public static boolean validateEmail(String email){
		if(email == null || email == " ")
			return false;

		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern p = Pattern.compile(ePattern);
		Matcher m = p.matcher(email);
		EntityManager em = JPAUtil.getCurrentEntityManager();
		StackTraceElement ste[] = Thread.currentThread().getStackTrace(); 

		if(m.matches()){
			if(ste[2].getClassName() == "taxi.model.Customer"){

				Query query = em.createQuery("select cust from Customer cust where email = :eml");
				query.setParameter("eml", email);  
				query.setMaxResults(1);
				List<Customer> results = query.getResultList();

				if(!results.isEmpty()){
					return false;
				}

			}
			else if(ste[2].getClassName() == "taxi.model.TaxiDriver"){
				Query query = em.createQuery("select taxdr from TaxiDriver taxdr where email = :eml");
				query.setParameter("eml", email);  
				query.setMaxResults(1);
				List<Customer> results = query.getResultList();

				if(!results.isEmpty()){
					return false;
				}
			}
			return true;
		}

		return false;

	}

	/* Method validating credit card.
	 * This method receives as parameters all the relevant information of a credit card, except the type.
	 * At the first step checks if the credit card number has 16 characters and the ccv 3 characters
	 * At the second step checks that credit card number and ccv are consist of numbers 
	 * 
	 * Expiry date is represented as MM/YY.
	 * We split the string to month and year and then we check that the credit card has not expired
	 *  
	 * If the checks are passed, the validation was completed successfully (true) 
	 */
	public static boolean validateCreditCard(String creditCardNumber, String expiryDate, String ccv){

		if(creditCardNumber == null || ccv == null || creditCardNumber.length() != 16 || ccv.length() != 3)
			return false;
		if (!creditCardNumber.matches("[0-9]+"))
			return false;
		if(!ccv.matches("[0-9]+"))
			return false;

		String month = expiryDate.substring(0, 2);
		String year = expiryDate.substring(3, 5);

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

	/* Method validating taxi.
	 * This method receives as parameter the license plate of a taxi.
	 * We assume that a license plate consists of 3 capital letters and 4 numbers
	 * At first step we are checking the length of the license plate. It has to be 7 and not empty
	 * Secondly, we split the license plate to its letters and numbers
	 * We then assure that the letters variable has no numbers, and that the numbers variable has only numbers
	 *   
	 * If the checks are passed, the validation was completed successfully (true) 
	 */
	public static boolean validateLicensePlate(String licensePlate){

		if (licensePlate == null || licensePlate.length() != 7)
			return false;

		String letters = licensePlate.substring(0, 3);
		String numbers = licensePlate.substring(3,7);		

		if (letters.matches("[0-9]+"))
			return false;
		else if (!numbers.matches("[0-9]+"))
			return false;		

		return true;
	}
}
