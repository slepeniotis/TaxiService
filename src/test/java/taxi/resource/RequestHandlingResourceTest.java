package taxi.resource;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;
import org.junit.Test;

import taxi.model.Customer;
import taxi.model.Request;
import taxi.model.Route;
import taxi.model.Taxi;
import taxi.model.TaxiDriver;
import taxi.persistence.Initializer;
import taxi.persistence.JPAUtil;
import taxi.service.RequestHandlingService;
import taxi.utils.RequestStatus;

public class RequestHandlingResourceTest extends TaxiResourceTest {

	Initializer dataHelper;
	protected EntityManager em;

	@Override
	protected Application configure() {
		return new ResourceConfig(RequestHandlingResource.class, DebugExceptionMapper.class);
	}

	/*searching for available taxis. valid search, 
	 * range 10km
	 */
	@Test
	public void testSearchTaxi(){
		EntityManager em = JPAUtil.getCurrentEntityManager();

		List<TaxiInfo> taxiInfo = target("request/search").queryParam("customerId", "1").
				queryParam("range", "10").request().get(new GenericType<List<TaxiInfo>>() {});

		Taxi tx = em.find(Taxi.class, (long)10);
		
		Query query = em.createQuery("select tx from Taxi tx where tx.licensePlate = :license");
		query.setParameter("license", taxiInfo.get(0).getLicensePlate());
		Taxi taxirslt = (Taxi)query.getSingleResult();		
		Assert.assertEquals(tx, taxirslt);
		em.close();
	}	

	/*searching for available taxis. invalid search, 
	 * range 0
	 */
	@Test
	public void testInValidSearchTaxi_range0(){

		List<TaxiInfo> taxiInfo = target("request/search").queryParam("customerId", "1").
				queryParam("range", "0").request().
				get(new GenericType<List<TaxiInfo>>() {});

		List<TaxiInfo> empty = new ArrayList();
		Assert.assertEquals(empty, taxiInfo);
	}

	/*searching for available taxis. invalid search, 
	 * range very small in order to have zero available taxis
	 */
	@Test
	public void testInValidSearchTaxi_rangeSmall(){

		List<TaxiInfo> taxiInfo = target("request/search").queryParam("customerId", "1").
				queryParam("range", "1").request().
				get(new GenericType<List<TaxiInfo>>() {});

		List<TaxiInfo> empty = new ArrayList();
		Assert.assertEquals(empty, taxiInfo);
	}	
	
	/* Start Request from a customer to an available taxi.
	 * valid request
	 */
	@Test
	public void testStartRequest_succ(){
		
		RequestHandlingInfo requestHandlingInfo = new RequestHandlingInfo((long)1, (long)10);
		
		Response response = target("request/startrequest").request().post(Entity.entity(requestHandlingInfo, MediaType.APPLICATION_JSON));
		Assert.assertEquals(201, response.getStatus());
	}
	
	/* Start Request from a customer to an available taxi.
	 * customer and taxi are null
	 */
	@Test
	public void testStartRequest_unsucc(){
		
		RequestHandlingInfo requestHandlingInfo = new RequestHandlingInfo((long)0, (long)0);
		
		Response response = target("request/startrequest").request().
				post(Entity.entity(requestHandlingInfo, MediaType.APPLICATION_JSON));
		Assert.assertEquals(406, response.getStatus());
	}	
	
	/* Handle Request from a taxi. Decision yes
	 */
	@Test
	public void testHandleRequest_succ(){
		EntityManager em = JPAUtil.getCurrentEntityManager();
		
		RequestHandlingInfo requestHandlingInfo = new RequestHandlingInfo((long)15, (long)10, "yes");
		
		Response response =	target("request/handlerequest/15").request().
				put(Entity.entity(requestHandlingInfo, MediaType.APPLICATION_JSON));

		Assert.assertEquals(200, response.getStatus());
		Request req = em.find(Request.class, (long)15);
		em.refresh(req);	
		Taxi tx = em.find(Taxi.class, (long)10);	
		em.refresh(tx);	
		Assert.assertEquals(RequestStatus.ONGOING, req.getStatus());
		Assert.assertFalse(tx.getStatus());		
		em.close();
	}
	
	/* Handle Request from a taxi. Decision no
	 * unsuccessful
	 */
	@Test
	public void testNotHandleRequest_succ(){
		EntityManager em = JPAUtil.getCurrentEntityManager();
		
		RequestHandlingInfo requestHandlingInfo = new RequestHandlingInfo((long)15, (long)10, "no");
		
		Response response =	target("request/nothandlerequest/15").request().
				put(Entity.entity(requestHandlingInfo, MediaType.APPLICATION_JSON));

		Assert.assertEquals(406, response.getStatus());
		Request req = em.find(Request.class, (long)15);
		em.refresh(req);	
		Taxi tx = em.find(Taxi.class, (long)10);	
		em.refresh(tx);	
		Assert.assertEquals(RequestStatus.CANCELED, req.getStatus());
		Assert.assertTrue(tx.getStatus());
		em.close();
	}
	
	/* Handle Request from a taxi. Decision yes, null taxi
	 * unsuccessful
	 */
	@Test
	public void testHandleRequest_unsucc(){
		EntityManager em = JPAUtil.getCurrentEntityManager();
		
		RequestHandlingInfo requestHandlingInfo = new RequestHandlingInfo((long)15, (long)0, "yes");
		
		Response response =	target("request/handlerequest/15").request().
				put(Entity.entity(requestHandlingInfo, MediaType.APPLICATION_JSON));

		Assert.assertEquals(406, response.getStatus());
		Request req = em.find(Request.class, (long)15);
		em.refresh(req);
		Assert.assertEquals(RequestStatus.PENDING, req.getStatus());
		em.close();
	}
	
	/* Handle Request from a taxi. Decision no, null taxi
	 * unsuccessful
	 */
	@Test
	public void testNotHandleRequest_unsucc(){
		EntityManager em = JPAUtil.getCurrentEntityManager();
		
		RequestHandlingInfo requestHandlingInfo = new RequestHandlingInfo((long)15, (long)0, "no");
		
		Response response =	target("request/nothandlerequest/15").request().
				put(Entity.entity(requestHandlingInfo, MediaType.APPLICATION_JSON));

		Assert.assertEquals(406, response.getStatus());
		Request req = em.find(Request.class, (long)15);
		em.refresh(req);
		Assert.assertEquals(RequestStatus.PENDING, req.getStatus());
		em.close();
	}
	
	/* Handle Request from a taxi. Decision yes
	 */
	@Test
	public void testHandleRequest_wrongIdPath(){
		EntityManager em = JPAUtil.getCurrentEntityManager();
		
		RequestHandlingInfo requestHandlingInfo = new RequestHandlingInfo((long)15, (long)10, "yes");
		
		Response response =	target("request/handlerequest/14").request().
				put(Entity.entity(requestHandlingInfo, MediaType.APPLICATION_JSON));

		Assert.assertEquals(406, response.getStatus());
		Request req = em.find(Request.class, (long)15);
		em.refresh(req);	
		Taxi tx = em.find(Taxi.class, (long)10);	
		em.refresh(tx);	
		Assert.assertEquals(RequestStatus.PENDING, req.getStatus());
		Assert.assertTrue(tx.getStatus());		
		em.close();
	}
	
	/* Handle Request from a taxi. Decision no
	 * unsuccessful
	 */
	@Test
	public void testNotHandleRequest_wrongIdPath(){
		EntityManager em = JPAUtil.getCurrentEntityManager();
		
		RequestHandlingInfo requestHandlingInfo = new RequestHandlingInfo((long)15, (long)10, "no");
		
		Response response =	target("request/nothandlerequest/14").request().
				put(Entity.entity(requestHandlingInfo, MediaType.APPLICATION_JSON));

		Assert.assertEquals(406, response.getStatus());
		Request req = em.find(Request.class, (long)15);
		em.refresh(req);	
		Taxi tx = em.find(Taxi.class, (long)10);	
		em.refresh(tx);	
		Assert.assertEquals(RequestStatus.PENDING, req.getStatus());
		Assert.assertTrue(tx.getStatus());		
		em.close();
	}
	
	/* Create route for request from a customer.
	 * valid route
	 */
	@Test
	public void testCreateRoute_succ(){
		EntityManager em = JPAUtil.getCurrentEntityManager();
		
		RequestHandlingInfo requestHandlingInfo = 
				new RequestHandlingInfo((long)15, "VALSAMIKO 3", "MATRIX", 87645, 
						"XLEMPONA 8", "ZAION", 26153);
		
		Response response = target("request/createRoute").request().
				post(Entity.entity(requestHandlingInfo, MediaType.APPLICATION_JSON));
		Assert.assertEquals(201, response.getStatus());		
		Request req = em.find(Request.class, (long)15);
		em.refresh(req);
		em.refresh(req.getRoute());
		Assert.assertNotNull(req.getRoute());
		Assert.assertEquals("VALSAMIKO 3", req.getRoute().getFromAddress());
		Assert.assertEquals("MATRIX", req.getRoute().getFromCity());
		Assert.assertEquals(87645, req.getRoute().getFromZipCode());
		Assert.assertEquals("XLEMPONA 8", req.getRoute().getToAddress());
		Assert.assertEquals("ZAION", req.getRoute().getToCity());
		Assert.assertEquals(26153, req.getRoute().getToZipCode());
		em.close();
	}
	
	/* Create route for request from a customer.
	 * invalid route, request = 0
	 */
	@Test
	public void testCreateRoute_unsucc(){
		
		RequestHandlingInfo requestHandlingInfo = 
				new RequestHandlingInfo((long)0, "VALSAMIKO 3", "MATRIX", 87645, 
						"XLEMPONA 8", "ZAION", 26153);
		
		Response response = target("request/createRoute").request().
				post(Entity.entity(requestHandlingInfo, MediaType.APPLICATION_JSON));
		Assert.assertEquals(406, response.getStatus());
	}
	
	/* Stop Request from a taxi. 
	 * successful
	 */
	@Test
	public void testStopRequest_succ(){
		EntityManager em = JPAUtil.getCurrentEntityManager();
		
		RequestHandlingInfo requestHandlingInfo = 
				new RequestHandlingInfo((long)15, "VALSAMIKO 3", "MATRIX", 87645, 
						"XLEMPONA 8", "ZAION", 26153);
		
		Response response = target("request/createRoute").request().
				post(Entity.entity(requestHandlingInfo, MediaType.APPLICATION_JSON));
		Assert.assertEquals(201, response.getStatus());	
		
		requestHandlingInfo = new RequestHandlingInfo((long)15, (long)10, 15.5f, 30);
		
		response = target("request/stoprequest/15").request().
				put(Entity.entity(requestHandlingInfo, MediaType.APPLICATION_JSON));
		Assert.assertEquals(200, response.getStatus());
		
		Request req = em.find(Request.class, (long)15);
		em.refresh(req);
		Route route = req.getRoute();
		em.refresh(route);
		Taxi tx = em.find(Taxi.class, (long)10);	
		em.refresh(tx);
			
		Assert.assertTrue(tx.getStatus());
		Assert.assertEquals(30, route.getDuration());
		Assert.assertEquals(15.5, route.getCost(), 0.001);
		Assert.assertEquals(RequestStatus.DONE, req.getStatus());
		em.close();
	}
	
	/* Stop Request from a taxi. 
	 * unsuccessful. null request
	 */
	@Test
	public void testStopRequest_unsucc(){
		
		RequestHandlingInfo requestHandlingInfo = 
				new RequestHandlingInfo((long)15, "VALSAMIKO 3", "MATRIX", 87645, 
						"XLEMPONA 8", "ZAION", 26153);
		
		Response response = target("request/createRoute").request().
				post(Entity.entity(requestHandlingInfo, MediaType.APPLICATION_JSON));
		Assert.assertEquals(201, response.getStatus());	
		
		requestHandlingInfo = new RequestHandlingInfo((long)0, (long)10, 15.5f, 30);
		
		response = target("request/stoprequest/15").request().
				put(Entity.entity(requestHandlingInfo, MediaType.APPLICATION_JSON));
		Assert.assertEquals(406, response.getStatus());		
	}
	
	/* Stop Request from a taxi. 
	 * unsuccessful. null request
	 */
	@Test
	public void testStopRequest_wrongIdPath(){
		
		RequestHandlingInfo requestHandlingInfo = 
				new RequestHandlingInfo((long)15, "VALSAMIKO 3", "MATRIX", 87645, 
						"XLEMPONA 8", "ZAION", 26153);
		
		Response response = target("request/createRoute").request().
				post(Entity.entity(requestHandlingInfo, MediaType.APPLICATION_JSON));
		Assert.assertEquals(201, response.getStatus());	
		
		requestHandlingInfo = new RequestHandlingInfo((long)0, (long)10, 15.5f, 30);
		
		response = target("request/stoprequest/14").request().
				put(Entity.entity(requestHandlingInfo, MediaType.APPLICATION_JSON));
		Assert.assertEquals(406, response.getStatus());		
	}
	
	/* Cancel Request.
	 * successful
	 */
	@Test
	public void testCancelRequest_succ(){
		EntityManager em = JPAUtil.getCurrentEntityManager();
		
		RequestHandlingInfo requestHandlingInfo = new RequestHandlingInfo();
		requestHandlingInfo.setCustomerId((long)1);
		requestHandlingInfo.setReqId((long)15);
		
		Response response =	target("request/cancelrequest/15").request().
				put(Entity.entity(requestHandlingInfo, MediaType.APPLICATION_JSON));

		Assert.assertEquals(200, response.getStatus());
		Request req = em.find(Request.class, (long)15);
		em.refresh(req);
		Assert.assertEquals(RequestStatus.CANCELED, req.getStatus());	
		em.close();
	}
	
	/* Cancel Request.
	 * unsuccessful, request already done
	 */
	@Test
	public void testCancelRequest_unsucc(){
		EntityManager em = JPAUtil.getCurrentEntityManager();
		
		RequestHandlingInfo requestHandlingInfo = new RequestHandlingInfo();
		requestHandlingInfo.setCustomerId((long)1);
		requestHandlingInfo.setReqId((long)11);
		
		Response response =	target("request/cancelrequest/15").request().
				put(Entity.entity(requestHandlingInfo, MediaType.APPLICATION_JSON));

		Assert.assertEquals(406, response.getStatus());
		Request req = em.find(Request.class, (long)11);
		em.refresh(req);
		Assert.assertEquals(RequestStatus.DONE, req.getStatus());	
		em.close();
	}
	
	/* Cancel Request.
	 * unsuccessful, wrong request id in path
	 */
	@Test
	public void testCancelRequest_wrongIdPath(){
		
		RequestHandlingInfo requestHandlingInfo = new RequestHandlingInfo();
		requestHandlingInfo.setCustomerId((long)1);
		requestHandlingInfo.setReqId((long)15);
		
		Response response =	target("request/cancelrequest/14").request().
				put(Entity.entity(requestHandlingInfo, MediaType.APPLICATION_JSON));

		Assert.assertEquals(406, response.getStatus());
	}
}
