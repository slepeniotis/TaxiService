package taxi.resource;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;
import org.junit.Test;

import taxi.model.Customer;
import taxi.model.TaxiDriver;
import taxi.persistence.Initializer;
import taxi.persistence.JPAUtil;

public class LoginResourceTest extends TaxiResourceTest{

	Initializer dataHelper;
	protected EntityManager em;

	@Override
	protected Application configure() {
		return new ResourceConfig(LoginResource.class, DebugExceptionMapper.class);
	}

	@Test
	public void CustomerLoginCst_succ() {

		EntityManager em = JPAUtil.getCurrentEntityManager();
		// Create a login info object and submit
		String passwd = "fdkE9skf";
		LoginInfo loginInfo = new LoginInfo("SLEPENIOTIS", passwd, 37.959874144, 23.6510941);

		Response response = target("login/CustomerLogin").request().post(Entity.entity(loginInfo, MediaType.APPLICATION_JSON));

		// Check status and database state
		Assert.assertEquals(201, response.getStatus());
		
		Query query = em.createQuery("select cust from Customer cust where username like :usrnm");
		query.setParameter("usrnm", "SLEPENIOTIS");
		Customer rsltcst = (Customer)query.getSingleResult();
		em.refresh(rsltcst);
		Assert.assertEquals(Double.toString(37.959874144), Double.toString(rsltcst.getLocationLat()));			
		em.close();		
	}

	@Test
	public void CustomerLoginCst_wrongPass() {	
		// Create a login info object and submit

		LoginInfo loginInfo = new LoginInfo("SLEPENIOTIS", "pass", 37.9508344, 23.6510941);

		Response response = target("login/CustomerLogin").request().post(Entity.entity(loginInfo, MediaType.APPLICATION_JSON));

		// Check status and database state
		Assert.assertEquals(404, response.getStatus());
	}

	@Test
	public void TaxiDriverLoginTx_succ() {

		EntityManager em = JPAUtil.getCurrentEntityManager();
		// Create a login info object and submit
		String passwd = "fd8E9skf";
		LoginInfo loginInfo = new LoginInfo("MAKXRIS", passwd, 37.959874144, 23.6510941);

		Response response = target("login/TaxiDriverLogin").request().post(Entity.entity(loginInfo, MediaType.APPLICATION_JSON));

		// Check status and database state
		Assert.assertEquals(201, response.getStatus());
		
		Query query = em.createQuery("select tx from TaxiDriver tx where username like :usrnm");
		query.setParameter("usrnm", "MAKXRIS");
		TaxiDriver rslttx = (TaxiDriver)query.getSingleResult();
		em.refresh(rslttx);
		em.refresh(rslttx.getOwns());
		Assert.assertEquals(Double.toString(37.959874144), Double.toString(rslttx.getOwns().getLocationLat()));			
		em.close();
	}

	@Test
	public void TaxiDriverLoginTx_wrongPass() {	
		// Create a login info object and submit

		LoginInfo loginInfo = new LoginInfo("XRISTODOYLOPOYLOS", "pass", 37.9508344, 23.6510941);

		Response response = target("login/TaxiDriverLogin").request().post(Entity.entity(loginInfo, MediaType.APPLICATION_JSON));

		// Check status and database state
		Assert.assertEquals(404, response.getStatus());
	}

}