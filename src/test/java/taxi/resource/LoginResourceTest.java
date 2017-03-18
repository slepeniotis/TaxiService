package taxi.resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import taxi.model.Customer;
import taxi.model.TaxiDriver;
import taxi.persistence.Initializer;
import taxi.persistence.JPAUtil;
import taxi.service.EditAccountService;

public class LoginResourceTest extends TaxiResourceTest{

	Initializer dataHelper;
	protected EntityManager em;

	@Override
	protected Application configure() {
		return new ResourceConfig(EditAccountResource.class, DebugExceptionMapper.class);
	}
	
	@Test
	public void CustomerLogin() {

	
		// Create a login info object and submit
		String passwd = "fdkE9skf";
		LoginInfo loginInfo = new LoginInfo("SLEPENIOTIS", passwd, 37.9508344, 23.6510941, "Customer");

		Response response = target("createLogin/CustomerLogin").request().post(Entity.entity(loginInfo, MediaType.APPLICATION_JSON));

		// Check status and database state
		Assert.assertEquals(201, response.getStatus());
	

	}
	
	@Test
	public void CustomerLoginCst_noUID() {

	
		// Create a login info object and submit
		
		LoginInfo loginInfo = new LoginInfo("SLEPENIOTIS", "pass", 37.9508344, 23.6510941, "Customer");

		Response response = target("createLogin/CustomerLogin").request().post(Entity.entity(loginInfo, MediaType.APPLICATION_JSON));

		// Check status and database state
		Assert.assertEquals(404, response.getStatus());
	

	}
	
}