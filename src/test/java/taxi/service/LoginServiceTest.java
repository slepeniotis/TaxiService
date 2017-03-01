package taxi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import taxi.model.Customer;
import taxi.model.TaxiDriver;
import taxi.persistence.Initializer;
import taxi.persistence.JPAUtil;
import taxi.utils.AESEncrypt;
import taxi.utils.Validators;

public class LoginServiceTest {

	protected EntityManager em;
	
	@Before
	public void setup(){
		// prepare database for each test
		em = JPAUtil.getCurrentEntityManager();
		Initializer dataHelper = new Initializer();
		try{
			dataHelper.prepareData();
		}
		catch (Exception e){
			System.out.println(e.getStackTrace());
		}
		
	}
	
	@After
	public void tearDown(){
		em.close();
	}
	
	//Tests for Customer
	@Test
	public void testValidLoginCustomer(){
				
		LoginService service = new LoginService();	
		Customer cust = (Customer) service.login("Customer"  , "SLEPENIOTIS", "fdkE9skf", 37.9508344, 23.6510941);		
		Assert.assertEquals(cust, cust);
		
	}
	
	@Test
	public void testInvalidLoginCustomer_userType(){
		
		LoginService service = new LoginService();		
		Customer cust = (Customer) service.login("TaxiDriver"  , "svlachopoulos", "1234", 0, 0);		
		Assert.assertEquals(cust, cust);
			
	}
	
	@Test
	public void testInvalidLoginCustomer_emptyUserType(){

		LoginService service = new LoginService();		
		Customer cust = (Customer) service.login(""  , "svlachopoulos", "1234", 0, 0);		
		Assert.assertEquals(cust, cust);
		
	}
	
	@Test
	public void testInvalidLoginCustomer_nullUserType(){
		
		LoginService service = new LoginService();		
		Customer cust = (Customer) service.login("NULL"  , "svlachopoulos", "1234", 0, 0);		
		Assert.assertEquals(cust, cust);
	}
	
	@Test
	public void testInvalidLoginCustomer_username(){
		
		LoginService service = new LoginService();		
		Customer cust = (Customer) service.login("Vlachos"  , "xaritos", "1234", 0, 0);		
		Assert.assertEquals(cust, cust);
			
	}
	
	@Test
	public void testInvalidLoginCustomer_emptyUsername(){
		
		LoginService service = new LoginService();		
		Customer cust = (Customer) service.login("Vlachos"  , " ", "1234", 0, 0);		
		Assert.assertEquals(cust, cust);
	}
	
	@Test
	public void testInvalidLoginCustomer_nullUsername(){
			
		LoginService service = new LoginService();		
		Customer cust = (Customer) service.login("Vlachos"  , "NULL", "1234", 0, 0);		
		Assert.assertEquals(cust, cust);
	}
	
	@Test
	public void testInvalidLoginCustomer_password(){
		
		LoginService service = new LoginService();		
		Customer cust = (Customer) service.login("Vlachos"  , "xaritos", "123qwe!@#", 0, 0);		
		Assert.assertEquals(cust, cust);
	}
	
	@Test
	public void testInvalidLoginCustomer_emptyPassword(){
			
		LoginService service = new LoginService();		
		Customer cust = (Customer) service.login("Vlachos"  , "xaritos", "", 0, 0);		
		Assert.assertEquals(cust, cust);
	}
	
	@Test
	public void testInvalidLoginCustomer_nullPassword(){
			
		LoginService service = new LoginService();		
		Customer cust = (Customer) service.login("Vlachos"  , "xaritos", "NULL", 0, 0);		
		Assert.assertEquals(cust, cust);
	}
	
	@Test
	public void testInvalidLoginCustomer_noCoordinates(){
		
		LoginService service = new LoginService();		
		Customer cust = (Customer) service.login("Vlachos"  , "xaritos", "NULL", 0, 0);		
		Assert.assertEquals(cust, cust);
			
	}
	//Tests for Taxi Driver
	@Test
	public void testValidLoginTaxiDr(){
			
		LoginService service = new LoginService();		
		TaxiDriver taxiDriver = (TaxiDriver) service.login("TaxiDriver", "MAKXRIS" , "fd8E9skf", 38.0093272, 23.8176902);	
		Assert.assertEquals(taxiDriver, taxiDriver);
	}
	
	@Test
	public void testInvalidLoginTaxiDr_userType(){
			
		LoginService service = new LoginService();		
		TaxiDriver taxiDriver = (TaxiDriver) service.login("TaxiDriver", "MAKXRIS" , "fd8E9skf", 38.0093272, 23.8176902);	
		Assert.assertEquals(taxiDriver, taxiDriver);
	}
	
	@Test
	public void testInvalidLoginTaxiDr_emptyUserType(){
			
		LoginService service = new LoginService();		
		TaxiDriver taxiDriver = (TaxiDriver) service.login("", "MAKXRIS" , "fd8E9skf", 38.0093272, 23.8176902);	
		Assert.assertEquals(taxiDriver, taxiDriver);
	}
	
	@Test
	public void testInvalidLoginTaxiDr_nullUserType(){
		
		LoginService service = new LoginService();		
		TaxiDriver taxiDriver = (TaxiDriver) service.login("NULL", "MAKXRIS" , "fd8E9skf", 38.0093272, 23.8176902);	
		Assert.assertEquals(taxiDriver, taxiDriver);
			
	}
	
	@Test
	public void testInvalidLoginTaxiDr_username(){
		
		LoginService service = new LoginService();		
		TaxiDriver taxiDriver = (TaxiDriver) service.login("TaxiDriver", "SPYROS" , "fd8E9skf", 38.0093272, 23.8176902);	
		Assert.assertEquals(taxiDriver, taxiDriver);
			
	}
	
	@Test
	public void testInvalidLoginTaxiDr_emptyUsername(){
		
		LoginService service = new LoginService();		
		TaxiDriver taxiDriver = (TaxiDriver) service.login("TaxiDriver", "" , "fd8E9skf", 38.0093272, 23.8176902);	
		Assert.assertEquals(taxiDriver, taxiDriver);
			
	}
	
	@Test
	public void testInvalidLoginTaxiDr_nullUsername(){
		
		LoginService service = new LoginService();		
		TaxiDriver taxiDriver = (TaxiDriver) service.login("TaxiDriver", "NULL" , "fd8E9skf", 38.0093272, 23.8176902);	
		Assert.assertEquals(taxiDriver, taxiDriver);
			
	}
	
	@Test
	public void testInvalidLoginTaxiDr_password(){
		
		LoginService service = new LoginService();		
		TaxiDriver taxiDriver = (TaxiDriver) service.login("TaxiDriver", "MAKXRIS" , "123qwe!@#", 38.0093272, 23.8176902);	
		Assert.assertEquals(taxiDriver, taxiDriver);
			
	}
	
	@Test
	public void testInvalidLoginTaxiDr_emptyPassword(){
		
		LoginService service = new LoginService();		
		TaxiDriver taxiDriver = (TaxiDriver) service.login("TaxiDriver", "MAKXRIS" , "", 38.0093272, 23.8176902);	
		Assert.assertEquals(taxiDriver, taxiDriver);
			
	}
	
	@Test
	public void testInvalidLoginTaxiDr_nullPassword(){
		
		LoginService service = new LoginService();		
		TaxiDriver taxiDriver = (TaxiDriver) service.login("TaxiDriver", "MAKXRIS" , "NULL", 38.0093272, 23.8176902);	
		Assert.assertEquals(taxiDriver, taxiDriver);
			
	}
	
	@Test
	public void testInvalidLoginTaxiDr_noCoordinates(){
		
		LoginService service = new LoginService();		
		TaxiDriver taxiDriver = (TaxiDriver) service.login("TaxiDriver", "MAKXRIS" , "fd8E9skf", 0, 0);	
		Assert.assertEquals(taxiDriver, taxiDriver);
			
	}
}
