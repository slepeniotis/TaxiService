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
import org.junit.Assert;
import org.junit.Test;

import taxi.model.Customer;
import taxi.model.TaxiDriver;
import taxi.persistence.Initializer;
import taxi.persistence.JPAUtil;
import taxi.utils.AESEncrypt;

public class RegistrationResourceTest extends TaxiResourceTest {

	Initializer dataHelper;
	protected EntityManager em;

	@Override
	protected Application configure() {
		return new ResourceConfig(RegistrationResource.class, DebugExceptionMapper.class);
	}

	//create valid customer
	@Test
	public void testRegisterCustomer_succ(){		
		EntityManager em = JPAUtil.getCurrentEntityManager();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}

		String passwd = "fdkE9skf";			
		CustomerInfo customerInfo = new CustomerInfo("TASOS", "GRIGORIOY", "ANDRAS", 
				"TGRIGORIOY", passwd, d, "AGIOY NIKOLAOY 1", "PEIRAIAS", 13671, "tgrigorioy@gmail.com", 
				"MASTERCARD", "1234567891234567", "01/19", "123", 37.9508344, 23.6510941);

		// Submit the updated representation
		Response response =	target("register/customer").request().post(Entity.entity(customerInfo, MediaType.APPLICATION_JSON));

		// assertion on request status and database state
		Assert.assertEquals(201, response.getStatus());

		Query query = em.createQuery("select cust from Customer cust where username like :usrnm");
		query.setParameter("usrnm", "TGRIGORIOY");
		Customer rsltcst = (Customer)query.getSingleResult();
		Assert.assertEquals("tgrigorioy@gmail.com", rsltcst.getEmail());			
		em.close();
	}

	//create invalid customer
	@Test
	public void testRegisterCustomer_unsucc(){		
		EntityManager em = JPAUtil.getCurrentEntityManager();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}

		String passwd = "fdkE9skf";			
		CustomerInfo customerInfo = new CustomerInfo("TASOS", "GRIGORIOY", "ANDRAS", 
				"SLEPENIOTIS", passwd, d, "AGIOY NIKOLAOY 1", "PEIRAIAS", 13671, "tgrigorioy@gmail.com", 
				"MASTERCARD", "1234567891234567", "01/19", "123", 37.9508344, 23.6510941);

		// Submit the updated representation
		Response response =	target("register/customer").request().post(Entity.entity(customerInfo, MediaType.APPLICATION_JSON));

		// assertion on request status and database state
		Assert.assertEquals(406, response.getStatus());
		em.close();
	}

	//create a valid pair of taxi and taxi driver
	@Test
	public void testRegisterTaxi_succ(){		
		EntityManager em = JPAUtil.getCurrentEntityManager();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}

		String passwd = "fdkE9skf";			
		TaxiInfo taxiInfo = new TaxiInfo("TASOS", "GRIGORIOY", "ANDRAS", "TGRIGORIOY", passwd, d, 
				"AGIOY NIKOLAOY 1", "PEIRAIAS", 13671, "tgrigorioy@gmail.com", "MASTERCARD", 
				"1234567891234567", "01/19", "123", "TOYOTA YARIS", "CITY", "YYY1111", "09/2010", 37.9508344, 23.6510941);

		// Submit the updated representation
		Response response =	target("register/taxi").request().post(Entity.entity(taxiInfo, MediaType.APPLICATION_JSON));

		// assertion on request status and database state
		Assert.assertEquals(201, response.getStatus());

		Query query = em.createQuery("select tx from TaxiDriver tx where username like :usrnm");
		query.setParameter("usrnm", "TGRIGORIOY");
		TaxiDriver rsltx = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("tgrigorioy@gmail.com", rsltx.getEmail());	
		Assert.assertEquals("YYY1111",rsltx.getOwns().getLicensePlate());
		em.close();
	}

	//create an invalid pair of taxi and taxi driver. taxi driver username exists
	@Test
	public void testRegisterTaxi_unsuccUser(){		

		EntityManager em = JPAUtil.getCurrentEntityManager();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}

		String passwd = "fdkE9skf";			
		TaxiInfo taxiInfo = new TaxiInfo("TASOS", "GRIGORIOY", "ANDRAS", "MAKXRIS", passwd, d, 
				"AGIOY NIKOLAOY 1", "PEIRAIAS", 13671, "tgrigorioy@gmail.com", "MASTERCARD", 
				"1234567891234567", "01/19", "123", "TOYOTA YARIS", "CITY", "YYY1111", "09/2010", 37.9508344, 23.6510941);

		// Submit the updated representation
		Response response =	target("register/taxi").request().post(Entity.entity(taxiInfo, MediaType.APPLICATION_JSON));

		// assertion on request status and database state
		Assert.assertEquals(406, response.getStatus());
		em.close();
	}

	//create an invalid pair of taxi and taxi driver, taxi license plate exists
	@Test
	public void testRegisterTaxi_unsuccLicense(){		

		EntityManager em = JPAUtil.getCurrentEntityManager();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}

		String passwd = "fdkE9skf";			
		TaxiInfo taxiInfo = new TaxiInfo("TASOS", "GRIGORIOY", "ANDRAS", "TGRIGORIOY", passwd, d, 
				"AGIOY NIKOLAOY 1", "PEIRAIAS", 13671, "tgrigorioy@gmail.com", "MASTERCARD", 
				"1234567891234567", "01/19", "123", "TOYOTA YARIS", "CITY", "AHX0987", "09/2010", 37.9508344, 23.6510941);

		// Submit the updated representation
		Response response =	target("register/taxi").request().post(Entity.entity(taxiInfo, MediaType.APPLICATION_JSON));

		// assertion on request status and database state
		Assert.assertEquals(406, response.getStatus());
		em.close();
	}

}
