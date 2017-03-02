package taxi.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import taxi.model.Customer;
import taxi.model.Request;
import taxi.model.Route;
import taxi.model.Taxi;
import taxi.persistence.Initializer;
import taxi.persistence.JPAUtil;
import taxi.utils.RequestStatus;

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
	
	//searching for available taxis. valid search
	@Test
	public void testValidSearchTaxi(){

		RequestHandlingService service = new RequestHandlingService();	

		Customer cust = em.find(Customer.class, (long)1);
		List<Taxi> rslt = service.searchTaxi(cust, 10);
		Taxi tx = em.find(Taxi.class, (long)10);


		Assert.assertEquals(tx, rslt.get(0));
	}

	//searching for available taxis. invalid search, range = 0
	@Test
	public void testInValidSearchTaxi_range0(){

		RequestHandlingService service = new RequestHandlingService();	

		Customer cust = em.find(Customer.class, (long)1);
		List<Taxi> rslt = service.searchTaxi(cust, 0);		

		Assert.assertNull(rslt);
	}

	/*searching for available taxis. invalid search, 
	 * range very small in order to have zero available taxis
	 */
	@Test
	public void testInValidSearchTaxi_rangeSmall(){

		RequestHandlingService service = new RequestHandlingService();	

		Customer cust = em.find(Customer.class, (long)1);
		List<Taxi> rslt = service.searchTaxi(cust, 1);		
		List<Taxi> empty = new ArrayList();
		Assert.assertEquals(empty, rslt);
	}

	//searching for available taxis. invalid search, no free taxi
	@Test
	public void testInValidSearchTaxi_noFreeTaxi(){
		RequestHandlingService service = new RequestHandlingService();
		Customer cust = em.find(Customer.class, (long)2);
		List<Taxi> rslt = service.searchTaxi(cust, 3);		
		List<Taxi> empty = new ArrayList();
		Assert.assertEquals(empty, rslt);
	}

	//Tests for start request
	
	//request creation from customer. valid request
	@Test
	public void testPersistValidRequest(){

		RequestHandlingService service = new RequestHandlingService();	

		Customer cust = em.find(Customer.class, (long)1);
		Taxi tx = em.find(Taxi.class, (long)10);

		Request req = service.startRequest(tx, cust);
		Assert.assertNotEquals(0, req.getId());

	}

	//request creation from customer. invalid request, null taxi
	@Test
	public void testPersistInValidRequest_nullTaxi(){

		RequestHandlingService service = new RequestHandlingService();	

		Customer cust = em.find(Customer.class, (long)1);

		Request req = service.startRequest(null, cust);
		Assert.assertNull(req);
	}

	//Tests for request handling
	
	//request accepted by taxi driver
	@Test
	public void testRequestHandling_accept(){

		RequestHandlingService service = new RequestHandlingService();	

		Request req = em.find(Request.class, (long)15);
		Taxi tx = em.find(Taxi.class, (long)10);

		Assert.assertTrue(service.handleRequest(req, tx, "yes"));
		Assert.assertEquals(RequestStatus.ONGOING, req.getStatus());
		Assert.assertFalse(tx.getStatus());

	}

	//request declined by taxi driver
	@Test
	public void testRequestHandling_decline(){

		RequestHandlingService service = new RequestHandlingService();	

		Request req = em.find(Request.class, (long)15);
		Taxi tx = em.find(Taxi.class, (long)10);

		Assert.assertFalse(service.handleRequest(req, tx, "no"));
		Assert.assertEquals(RequestStatus.CANCELED, req.getStatus());
		Assert.assertTrue(tx.getStatus());
	}

	//invalid request handling. decision invalid
	@Test
	public void testRequestHandling_invalidDecision(){
		RequestHandlingService service = new RequestHandlingService();	

		Request req = em.find(Request.class, (long)15);
		Taxi tx = em.find(Taxi.class, (long)10);

		Assert.assertFalse(service.handleRequest(req, tx, "dfjadlgf"));
		Assert.assertEquals(RequestStatus.PENDING, req.getStatus());
		Assert.assertTrue(tx.getStatus());
	}

	//invalid request handling. decision invalid
	@Test
	public void testRequestHandling_accept_custAlreadyCanceled(){
		RequestHandlingService service = new RequestHandlingService();	

		Request req = em.find(Request.class, (long)15);
		Taxi tx = em.find(Taxi.class, (long)10);

		Customer cust = em.find(Customer.class, (long)1);
		Assert.assertTrue(service.cancelRequest(cust, req));
		Assert.assertFalse(service.handleRequest(req, tx, "yes"));		
		Assert.assertEquals(RequestStatus.CANCELED, req.getStatus());
		Assert.assertTrue(tx.getStatus());

	}

	//Tests for Route creating
	
	/*After the acceptance of the request, the route is created and the 
	 * appropriate statuses are checked
	 */
	@Test
	public void testPersistValidRoute(){

		RequestHandlingService service = new RequestHandlingService();	

		Request req = em.find(Request.class, (long)15);
		Taxi tx = em.find(Taxi.class, (long)10);

		Assert.assertTrue(service.handleRequest(req, tx, "yes"));
		Assert.assertEquals(RequestStatus.ONGOING, req.getStatus());
		Assert.assertFalse(tx.getStatus());

		Route route = service.createRoute(req, "VALSAMIKO 3", "XLEMPONA 8", "MATRIX", "ZAION", 87645, 26153);

		Assert.assertNotEquals(0, route.getId());
		Assert.assertEquals(req.getRoute(), route);
	}

	/* After the acceptance of the request, an invalid route is created.
	 * empty from address
	 * statuses do not change
	 */	
	@Test
	public void testPersistInValidRoute_emptyFromAddress(){

		RequestHandlingService service = new RequestHandlingService();	

		Request req = em.find(Request.class, (long)15);
		Taxi tx = em.find(Taxi.class, (long)10);

		Assert.assertTrue(service.handleRequest(req, tx, "yes"));
		Assert.assertEquals(RequestStatus.ONGOING, req.getStatus());
		Assert.assertFalse(tx.getStatus());

		Route route = service.createRoute(req, " ", "XLEMPONA 8", "MATRIX", "ZAION", 87645, 26153);

		Assert.assertNull(route);
	}

	/* After the acceptance of the request, an invalid route is created.
	 * empty from city
	 * statuses do not change
	 */	
	@Test
	public void testPersistInValidRoute_emptyFromCity(){

		RequestHandlingService service = new RequestHandlingService();	

		Request req = em.find(Request.class, (long)15);
		Taxi tx = em.find(Taxi.class, (long)10);

		Assert.assertTrue(service.handleRequest(req, tx, "yes"));
		Assert.assertEquals(RequestStatus.ONGOING, req.getStatus());
		Assert.assertFalse(tx.getStatus());

		Route route = service.createRoute(req, "VALSAMIKO 3", "XLEMPONA 8", " ", "ZAION", 87645, 26153);
		Assert.assertNull(route);
	}

	/* After the acceptance of the request, an invalid route is created.
	 * from zipcode = 0
	 * statuses do not change
	 */	
	@Test
	public void testPersistInValidRoute_emptyFromZipCode(){

		RequestHandlingService service = new RequestHandlingService();	

		Request req = em.find(Request.class, (long)15);
		Taxi tx = em.find(Taxi.class, (long)10);

		Assert.assertTrue(service.handleRequest(req, tx, "yes"));
		Assert.assertEquals(RequestStatus.ONGOING, req.getStatus());
		Assert.assertFalse(tx.getStatus());

		Route route = service.createRoute(req, "VALSAMIKO 3", "XLEMPONA 8", "MATRIX", "ZAION", 0, 26153);
		Assert.assertNull(route);
	}

	/* After the acceptance of the request, an invalid route is created.
	 * empty to address
	 * statuses do not change
	 */	
	@Test
	public void testPersistInValidRoute_emptyToAddress(){
		RequestHandlingService service = new RequestHandlingService();	

		Request req = em.find(Request.class, (long)15);
		Taxi tx = em.find(Taxi.class, (long)10);

		Assert.assertTrue(service.handleRequest(req, tx, "yes"));
		Assert.assertEquals(RequestStatus.ONGOING, req.getStatus());
		Assert.assertFalse(tx.getStatus());

		Route route = service.createRoute(req, "VALSAMIKO 3", " ", "MATRIX", "ZAION", 87645, 26153);
		Assert.assertNull(route);
	}

	/* After the acceptance of the request, an invalid route is created.
	 * empty to city
	 * statuses do not change
	 */	
	@Test
	public void testPersistInValidRoute_emptyToCity(){
		RequestHandlingService service = new RequestHandlingService();	

		Request req = em.find(Request.class, (long)15);
		Taxi tx = em.find(Taxi.class, (long)10);

		Assert.assertTrue(service.handleRequest(req, tx, "yes"));
		Assert.assertEquals(RequestStatus.ONGOING, req.getStatus());
		Assert.assertFalse(tx.getStatus());

		Route route = service.createRoute(req, "VALSAMIKO 3", "XLEMPONA 8", "MATRIX", " ", 87645, 26153);
		Assert.assertNull(route);
	}

	/* After the acceptance of the request, an invalid route is created.
	 * to zipcode = 0
	 * statuses do not change
	 */	
	@Test
	public void testPersistInValidRoute_emptyToZipCode(){
		RequestHandlingService service = new RequestHandlingService();	

		Request req = em.find(Request.class, (long)15);
		Taxi tx = em.find(Taxi.class, (long)10);

		Assert.assertTrue(service.handleRequest(req, tx, "yes"));
		Assert.assertEquals(RequestStatus.ONGOING, req.getStatus());
		Assert.assertFalse(tx.getStatus());

		Route route = service.createRoute(req, "VALSAMIKO 3", "XLEMPONA 8", "MATRIX", "ZAION", 87645, 0);
		Assert.assertNull(route);
	}

	/* After the acceptance of the request, an invalid route is created.
	 * null from address
	 * statuses do not change
	 */	
	@Test
	public void testPersistInValidRoute_nullFromAddress(){
		RequestHandlingService service = new RequestHandlingService();	

		Request req = em.find(Request.class, (long)15);
		Taxi tx = em.find(Taxi.class, (long)10);

		Assert.assertTrue(service.handleRequest(req, tx, "yes"));
		Assert.assertEquals(RequestStatus.ONGOING, req.getStatus());
		Assert.assertFalse(tx.getStatus());

		Route route = service.createRoute(req, null, "XLEMPONA 8", "MATRIX", "ZAION", 87645, 26153);
		Assert.assertNull(route);
	}

	/* After the acceptance of the request, an invalid route is created.
	 * null from city
	 * statuses do not change
	 */	
	@Test
	public void testPersistInValidRoute_nullFromCity(){
		RequestHandlingService service = new RequestHandlingService();	

		Request req = em.find(Request.class, (long)15);
		Taxi tx = em.find(Taxi.class, (long)10);

		Assert.assertTrue(service.handleRequest(req, tx, "yes"));
		Assert.assertEquals(RequestStatus.ONGOING, req.getStatus());
		Assert.assertFalse(tx.getStatus());

		Route route = service.createRoute(req, "VALSAMIKO 3", "XLEMPONA 8", null, "ZAION", 87645, 26153);
		Assert.assertNull(route);
	}

	/* After the acceptance of the request, an invalid route is created.
	 * null to address
	 * statuses do not change
	 */	
	@Test
	public void testPersistInValidRoute_nullToAddress(){
		RequestHandlingService service = new RequestHandlingService();	

		Request req = em.find(Request.class, (long)15);
		Taxi tx = em.find(Taxi.class, (long)10);

		Assert.assertTrue(service.handleRequest(req, tx, "yes"));
		Assert.assertEquals(RequestStatus.ONGOING, req.getStatus());
		Assert.assertFalse(tx.getStatus());

		Route route = service.createRoute(req, "VALSAMIKO 3", null, "MATRIX", "ZAION", 87645, 26153);
		Assert.assertNull(route);
	}

	/* After the acceptance of the request, an invalid route is created.
	 * null to city
	 * statuses do not change
	 */	
	@Test
	public void testPersistInValidRoute_nullToCity(){
		RequestHandlingService service = new RequestHandlingService();	

		Request req = em.find(Request.class, (long)15);
		Taxi tx = em.find(Taxi.class, (long)10);

		Assert.assertTrue(service.handleRequest(req, tx, "yes"));
		Assert.assertEquals(RequestStatus.ONGOING, req.getStatus());
		Assert.assertFalse(tx.getStatus());

		Route route = service.createRoute(req, "VALSAMIKO 3", "XLEMPONA 8", "MATRIX", null, 87645, 26153);
		Assert.assertNull(route);
	}

	//Tests for stop request
	
	/*taxi driver requests to stop the request previously created/started
	 * by stopping the request we update on route the duration, cost and commision
	 */
	@Test
	public void testValidStopRequest(){
		RequestHandlingService service = new RequestHandlingService();	

		Request req = em.find(Request.class, (long)15);
		Taxi tx = em.find(Taxi.class, (long)10);
		
		Assert.assertTrue(service.handleRequest(req, tx, "yes"));
		Assert.assertEquals(RequestStatus.ONGOING, req.getStatus());
		Assert.assertFalse(tx.getStatus());
		
		Route route = service.createRoute(req, "VALSAMIKO 3", "XLEMPONA 8", "MATRIX", "ZAION", 87645, 26153);
		
		Assert.assertNotEquals(0, route.getId());
		Assert.assertEquals(req.getRoute(), route);
		
		Assert.assertTrue(service.stopRequest(req, tx, 15f, 20));		
	}

	/*taxi driver requests to stop the request previously created/started
	 * invalid cost = 0
	 */
	@Test
	public void testInValidStopRequest_0Cost(){
		RequestHandlingService service = new RequestHandlingService();	

		Request req = em.find(Request.class, (long)15);
		Taxi tx = em.find(Taxi.class, (long)10);
		
		Assert.assertTrue(service.handleRequest(req, tx, "yes"));
		Assert.assertEquals(RequestStatus.ONGOING, req.getStatus());
		Assert.assertFalse(tx.getStatus());
		
		Route route = service.createRoute(req, "VALSAMIKO 3", "XLEMPONA 8", "MATRIX", "ZAION", 87645, 26153);
		
		Assert.assertNotEquals(0, route.getId());
		Assert.assertEquals(req.getRoute(), route);
		
		Assert.assertFalse(service.stopRequest(req, tx, 0, 20));
	}

	/*taxi driver requests to stop the request previously created/started
	 * invalid duration = 0
	 */
	@Test
	public void testInValidStopRequest_0Duration(){
		RequestHandlingService service = new RequestHandlingService();	

		Request req = em.find(Request.class, (long)15);
		Taxi tx = em.find(Taxi.class, (long)10);
		
		Assert.assertTrue(service.handleRequest(req, tx, "yes"));
		Assert.assertEquals(RequestStatus.ONGOING, req.getStatus());
		Assert.assertFalse(tx.getStatus());
		
		Route route = service.createRoute(req, "VALSAMIKO 3", "XLEMPONA 8", "MATRIX", "ZAION", 87645, 26153);
		
		Assert.assertNotEquals(0, route.getId());
		Assert.assertEquals(req.getRoute(), route);
		
		Assert.assertFalse(service.stopRequest(req, tx, 15f, 0));	
	}

	//Tests for cancel request
	
	//customer requests to cancel the request previously created
	@Test
	public void testValidCancelRequest(){
		RequestHandlingService service = new RequestHandlingService();	

		Request req = em.find(Request.class, (long)15);
		Taxi tx = em.find(Taxi.class, (long)10);

		Customer cust = em.find(Customer.class, (long)1);
		Assert.assertTrue(service.cancelRequest(cust, req));		
		Assert.assertEquals(RequestStatus.CANCELED, req.getStatus());
	}

	/*customer requests to cancel the request previously created
	 * invalid call, request already done
	 */
	@Test
	public void testInValidCancelRequest_statusDone(){
		RequestHandlingService service = new RequestHandlingService();	

		Request req = em.find(Request.class, (long)11);
		Taxi tx = em.find(Taxi.class, (long)6);

		Customer cust = em.find(Customer.class, (long)1);
		Assert.assertFalse(service.cancelRequest(cust, req));		
		Assert.assertEquals(RequestStatus.DONE, req.getStatus());
	}

	/*customer requests to cancel the request previously created
	 * invalid call, request already canceled
	 */
	@Test
	public void testInValidCancelRequest_statusCanceled(){

		RequestHandlingService service = new RequestHandlingService();	

		Request req = em.find(Request.class, (long)15);
		Taxi tx = em.find(Taxi.class, (long)10);

		Customer cust = em.find(Customer.class, (long)1);
		Assert.assertFalse(service.handleRequest(req, tx, "no"));		
		Assert.assertEquals(RequestStatus.CANCELED, req.getStatus());
		
		Assert.assertFalse(service.cancelRequest(cust, req));		
		Assert.assertEquals(RequestStatus.CANCELED, req.getStatus());
	}
}
