package taxi.service;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import taxi.model.Customer;
import taxi.model.TaxiDriver;
import taxi.persistence.Initializer;
import taxi.persistence.JPAUtil;

public class LoginServiceTest extends TaxiServiceTest{

	//Tests for Customer
	
	//Login for valid customer
	@Test
	public void testValidLoginCustomer(){
				
		LoginService service = new LoginService();	
		Customer cust = (Customer) service.login("Customer"  , "SLEPENIOTIS", "fdkE9skf", 37.9508344, 23.6510941);		
		Assert.assertEquals(cust.getId(), (long)1);
		
	}
	
	//Invalid login with wrong user type
	@Test
	public void testInvalidLoginCustomer_userType(){
		
		LoginService service = new LoginService();		
		Customer cust = (Customer) service.login("TaDriver", "SLEPENIOTIS", "fdkE9skf", 37.9508344, 23.6510941);		
		Assert.assertNull(cust);
			
	}
	
	//Invalid login with empty user type
	@Test
	public void testInvalidLoginCustomer_emptyUserType(){

		LoginService service = new LoginService();		
		Customer cust = (Customer) service.login(" "  , "SLEPENIOTIS", "fdkE9skf", 37.9508344, 23.6510941);		
		Assert.assertNull(cust);
		
	}
	
	//Invalid login with null user type
	@Test
	public void testInvalidLoginCustomer_nullUserType(){
		
		LoginService service = new LoginService();		
		Customer cust = (Customer) service.login(null  , "SLEPENIOTIS", "fdkE9skf", 37.9508344, 23.6510941);		
		Assert.assertNull(cust);
	}
	
	//Invalid login with wrong username
	@Test
	public void testInvalidLoginCustomer_username(){
		
		LoginService service = new LoginService();		
		Customer cust = (Customer) service.login("Customer"  , "xaritos", "fdkE9skf", 37.9508344, 23.6510941);		
		Assert.assertNull(cust);
			
	}
	
	//Invalid login with empty username
	@Test
	public void testInvalidLoginCustomer_emptyUsername(){
		
		LoginService service = new LoginService();		
		Customer cust = (Customer) service.login("Customer"  , " ", "fdkE9skf", 37.9508344, 23.6510941);		
		Assert.assertNull(cust);
	}
	
	//Invalid login with null username
	@Test
	public void testInvalidLoginCustomer_nullUsername(){
			
		LoginService service = new LoginService();		
		Customer cust = (Customer) service.login("Customer"  , null, "fdkE9skf", 37.9508344, 23.6510941);		
		Assert.assertNull(cust);
	}
	
	//Invalid login with wrong password
	@Test
	public void testInvalidLoginCustomer_password(){
		
		LoginService service = new LoginService();		
		Customer cust = (Customer) service.login("Customer"  , "SLEPENIOTIS", "fdk9skf", 37.9508344, 23.6510941);		
		Assert.assertNull(cust);
	}
	
	//Invalid login with empty password
	@Test
	public void testInvalidLoginCustomer_emptyPassword(){
			
		LoginService service = new LoginService();		
		Customer cust = (Customer) service.login("Customer"  , "SLEPENIOTIS", " ", 37.9508344, 23.6510941);
		Assert.assertNull(cust);
	}
	
	//Invalid login with null password
	@Test
	public void testInvalidLoginCustomer_nullPassword(){
			
		LoginService service = new LoginService();		
		Customer cust = (Customer) service.login("Customer"  , "SLEPENIOTIS", null, 37.9508344, 23.6510941);
		Assert.assertNull(cust);
	}
	
	//Invalid login with 0 coordinates
	@Test
	public void testInvalidLoginCustomer_noCoordinates(){
		
		LoginService service = new LoginService();		
		Customer cust = (Customer) service.login("Customer"  , "SLEPENIOTIS", "fdk9skf", 0, 0);	
		Assert.assertNull(cust);
			
	}
	
	//Tests for Taxi Driver
	
	//Login for valid taxi driver
	@Test
	public void testValidLoginTaxiDr(){
			
		LoginService service = new LoginService();		
		TaxiDriver taxiDriver = (TaxiDriver) service.login("Taxi Driver", "MAKXRIS" , "fd8E9skf", 38.0093272, 23.8176902);	
		Assert.assertEquals(taxiDriver.getId(), (long)5);
	}
	
	//Invalid login with wrong user type
	@Test
	public void testInvalidLoginTaxiDr_userType(){
			
		LoginService service = new LoginService();		
		TaxiDriver taxiDriver = (TaxiDriver) service.login("Tariver", "MAKXRIS" , "fd8E9skf", 38.0093272, 23.8176902);	
		Assert.assertNull(taxiDriver);
	}
	
	//Invalid login with empty user type
	@Test
	public void testInvalidLoginTaxiDr_emptyUserType(){
			
		LoginService service = new LoginService();		
		TaxiDriver taxiDriver = (TaxiDriver) service.login(" ", "MAKXRIS" , "fd8E9skf", 38.0093272, 23.8176902);	
		Assert.assertNull(taxiDriver);
	}
	
	//Invalid login with null user type
	@Test
	public void testInvalidLoginTaxiDr_nullUserType(){
		
		LoginService service = new LoginService();		
		TaxiDriver taxiDriver = (TaxiDriver) service.login(null, "MAKXRIS" , "fd8E9skf", 38.0093272, 23.8176902);	
		Assert.assertNull(taxiDriver);
			
	}
	
	//Invalid login with wrong username
	@Test
	public void testInvalidLoginTaxiDr_username(){
		
		LoginService service = new LoginService();		
		TaxiDriver taxiDriver = (TaxiDriver) service.login("Taxi Driver", "MAKRIS" , "fd8E9skf", 38.0093272, 23.8176902);	
		Assert.assertNull(taxiDriver);
			
	}
	
	//Invalid login with empty username
	@Test
	public void testInvalidLoginTaxiDr_emptyUsername(){
		
		LoginService service = new LoginService();		
		TaxiDriver taxiDriver = (TaxiDriver) service.login("Taxi Driver", " " , "fd8E9skf", 38.0093272, 23.8176902);	
		Assert.assertNull(taxiDriver);
			
	}
	
	//Invalid login with null username
	@Test
	public void testInvalidLoginTaxiDr_nullUsername(){
		
		LoginService service = new LoginService();		
		TaxiDriver taxiDriver = (TaxiDriver) service.login("Taxi Driver", null , "fd8E9skf", 38.0093272, 23.8176902);	
		Assert.assertNull(taxiDriver);
			
	}
	
	//Invalid login with wrong password
	@Test
	public void testInvalidLoginTaxiDr_password(){
		
		LoginService service = new LoginService();		
		TaxiDriver taxiDriver = (TaxiDriver) service.login("Taxi Driver", "MAKXRIS" , "fd8E9kf", 38.0093272, 23.8176902);	
		Assert.assertNull(taxiDriver);
			
	}
	
	//Invalid login with empty password
	@Test
	public void testInvalidLoginTaxiDr_emptyPassword(){
		
		LoginService service = new LoginService();		
		TaxiDriver taxiDriver = (TaxiDriver) service.login("Taxi Driver", "MAKXRIS" , " ", 38.0093272, 23.8176902);	
		Assert.assertNull(taxiDriver);
			
	}
	
	//Invalid login with null password
	@Test
	public void testInvalidLoginTaxiDr_nullPassword(){
		
		LoginService service = new LoginService();		
		TaxiDriver taxiDriver = (TaxiDriver) service.login("Taxi Driver", "MAKXRIS" , null, 38.0093272, 23.8176902);	
		Assert.assertNull(taxiDriver);
			
	}
	
	//Invalid login with 0 coordinates
	@Test
	public void testInvalidLoginTaxiDr_noCoordinates(){
		
		LoginService service = new LoginService();		
		TaxiDriver taxiDriver = (TaxiDriver) service.login("Taxi Driver", "MAKXRIS" , "fd8E9skf", 0, 0);
		Assert.assertNull(taxiDriver);
			
	}
}
