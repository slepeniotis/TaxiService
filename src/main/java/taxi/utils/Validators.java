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

public class Validators {
	public static boolean validateUsername(String username){
		EntityManager em = JPAUtil.getCurrentEntityManager();
		//starting a new transaction
		EntityTransaction tx = em.getTransaction();
		
		StackTraceElement ste[] = Thread.currentThread().getStackTrace();        
        if(ste[2].toString() == "Customer"){
        	
    		Query query = em.createQuery("select cust from Customer cust where username = :usrnm");
    		query.setParameter("usrnm",username);  
    		query.setMaxResults(1);
    		List<Customer> results = query.getResultList();
    		
    		if(!results.isEmpty()){
    			return false;
    		}
    		
        }
        else if(ste[2].toString() == "TaxiDriver"){
        	Query query = em.createQuery("select taxdr from TaxiDriver taxdr where username = :usrnm");
    		query.setParameter("usrnm",username);  
    		query.setMaxResults(1);
    		List<Customer> results = query.getResultList();
    		
    		if(!results.isEmpty()){
    			return false;
    		}
        }
        
        return true;
		
	}
	
	public static boolean validatePassword(String password){
		final Pattern hasUppercase = Pattern.compile("[A-Z]");
		final Pattern hasNumber = Pattern.compile("\\d");
		
		if (password.length() < 1 || password.length() < 8)
			return false;
		else if (!hasUppercase.matcher(password).find())
			return false;
		else if (!hasNumber.matcher(password).find())
			return false;			
    		
    	return true;
	}
	
	public static boolean validateEmail(String email){
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
	}
	
	public static boolean validateCreditCard(String creditCardNumber, String expiryDate, String ccv){
		
		if(creditCardNumber.length() < 16 || ccv.length() < 3)
			return false;
		
		String month = expiryDate.substring(0, 2);
		String year = expiryDate.substring(3, 5);
		
		Calendar now = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yy");
		String curYear = sdf.format(Calendar.getInstance().getTime());
		sdf = new SimpleDateFormat("MM");
		String curMonth = sdf.format(Calendar.getInstance().getTime());
		
		if (Integer.parseInt(year) == Integer.parseInt(curYear))
			if ((Integer.parseInt(month) > Integer.parseInt(curMonth)))
				return true;
		
		if (Integer.parseInt(year) > Integer.parseInt(curYear))
			return true;
		
		return false;
		
	}
}
