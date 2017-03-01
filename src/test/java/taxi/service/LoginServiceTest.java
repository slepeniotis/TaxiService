package taxi.service;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import taxi.persistence.Initializer;
import taxi.persistence.JPAUtil;

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
			
	}
	
	@Test
	public void testInvalidLoginCustomer_userType(){
			
	}
	
	@Test
	public void testInvalidLoginCustomer_emptyUserType(){
			
	}
	
	@Test
	public void testInvalidLoginCustomer_nullUserType(){
			
	}
	
	@Test
	public void testInvalidLoginCustomer_username(){
			
	}
	
	@Test
	public void testInvalidLoginCustomer_emptyUsername(){
			
	}
	
	@Test
	public void testInvalidLoginCustomer_nullUsername(){
			
	}
	
	@Test
	public void testInvalidLoginCustomer_password(){
			
	}
	
	@Test
	public void testInvalidLoginCustomer_emptyPassword(){
			
	}
	
	@Test
	public void testInvalidLoginCustomer_nullPassword(){
			
	}
	
	@Test
	public void testInvalidLoginCustomer_noCoordinates(){
			
	}
	//Tests for Taxi Driver
	@Test
	public void testValidLoginTaxiDr(){
			
	}
	
	@Test
	public void testInvalidLoginTaxiDr_userType(){
			
	}
	
	@Test
	public void testInvalidLoginTaxiDr_emptyUserType(){
			
	}
	
	@Test
	public void testInvalidLoginTaxiDr_nullUserType(){
			
	}
	
	@Test
	public void testInvalidLoginTaxiDr_username(){
			
	}
	
	@Test
	public void testInvalidLoginTaxiDr_emptyUsername(){
			
	}
	
	@Test
	public void testInvalidLoginTaxiDr_nullUsername(){
			
	}
	
	@Test
	public void testInvalidLoginTaxiDr_password(){
			
	}
	
	@Test
	public void testInvalidLoginTaxiDr_emptyPassword(){
			
	}
	
	@Test
	public void testInvalidLoginTaxiDr_nullPassword(){
			
	}
	
	@Test
	public void testInvalidLoginTaxiDr_noCoordinates(){
			
	}
}
