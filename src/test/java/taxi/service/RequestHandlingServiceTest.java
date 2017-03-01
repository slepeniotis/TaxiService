package taxi.service;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import taxi.persistence.Initializer;
import taxi.persistence.JPAUtil;

public class RequestHandlingServiceTest {

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

	//Tests for Taxi search
	@Test
	public void testValidSearchTaxi(){

	}
	
	@Test
	public void testInValidSearchTaxi_range0(){

	}
	
	@Test
	public void testInValidSearchTaxi_rangeBig(){

	}
	
	@Test
	public void testInValidSearchTaxi_noFreeTaxi(){

	}
	
	//Tests for start request
	@Test
	public void testPersistValidRequest(){

	}
	
	@Test
	public void testPersistInValidRequest_nullTaxi(){

	}
	
	//Tests for request handling
	@Test
	public void testRequestHandling_accept(){

	}
	
	@Test
	public void testRequestHandling_decline(){

	}
	
	@Test
	public void testRequestHandling_invalidDecision(){

	}
	
	@Test
	public void testRequestHandling_accept_custAlreadyCanceled(){

	}
	
	//Tests for Route creating
	@Test
	public void testPersistValidRoute(){

	}
	
	@Test
	public void testPersistInValidRoute_emptyFromAddress(){

	}
	
	@Test
	public void testPersistInValidRoute_emptyFromCity(){

	}
	
	@Test
	public void testPersistInValidRoute_emptyFromZipCode(){

	}
	
	@Test
	public void testPersistInValidRoute_emptyToAddress(){

	}
	
	@Test
	public void testPersistInValidRoute_emptyToCity(){

	}
	
	@Test
	public void testPersistInValidRoute_emptyToZipCode(){

	}
	
	@Test
	public void testPersistInValidRoute_nullFromAddress(){

	}
	
	@Test
	public void testPersistInValidRoute_nullFromCity(){

	}
	
	@Test
	public void testPersistInValidRoute_0FromZipCode(){

	}
	
	@Test
	public void testPersistInValidRoute_nullToAddress(){

	}
	
	@Test
	public void testPersistInValidRoute_nullToCity(){

	}
	
	@Test
	public void testPersistInValidRoute_0ToZipCode(){

	}
	
	//Tests for stop request
	@Test
	public void testValidStopRequest(){

	}
	
	@Test
	public void testInValidStopRequest_0Cost(){

	}
	
	@Test
	public void testInValidStopRequest_0Duration(){

	}
	
	//Tests for cancel request
	@Test
	public void testValidCancelRequest(){

	}
	
	@Test
	public void testInValidCancelRequest_statusDone(){

	}
	
	@Test
	public void testInValidCancelRequest_statusCanceled(){

	}
}
