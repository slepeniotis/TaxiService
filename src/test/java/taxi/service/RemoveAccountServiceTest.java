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
	private TaxiDriver taxidrv;

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
		
		RemoveAccountService service = new RemoveAccountService();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)9);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Boolean rem  = service.removeDriver(taxiDr) ;
		em.close();
		Assert.assertEquals("SKYLADIKOY 3", taxiDr.getAddress());
		// assertions
		em = JPAUtil.getCurrentEntityManager();
		
		//Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		//query.setParameter("tid", (long)9);
		//TaxiDriver taxiDrv = (TaxiDriver)query.getSingleResult();
		//Assert.assertEquals("dfaggaadfadsfada", taxiDr.getAddress())
		//Assert.assertEquals(0, author.getBooks().size());
		
	/*	EntityTransaction tx = em.getTransaction();
		tx.begin();				
		em.remove(taxiDr);
		tx.commit();*/
		
		
	}
	
	//Tests customer removal
	@Test
	public void testRemoveAValidCustomer(){

	}
}
