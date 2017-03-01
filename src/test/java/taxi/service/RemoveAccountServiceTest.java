package taxi.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import taxi.model.TaxiDriver;
import taxi.persistence.Initializer;
import taxi.persistence.JPAUtil;

public class RemoveAccountServiceTest {

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

	//Tests taxi driver removal
	@Test
	public void testRemoveAValidTaxiDriver(){		
		
	}
	
	//Tests customer removal
	@Test
	public void testRemoveAValidCustomer(){

	}
}
