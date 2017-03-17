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

public class EditAccountResourceTest  extends TaxiResourceTest  {

	Initializer dataHelper;
	protected EntityManager em;
	
	@Override
	protected Application configure() {
		return new ResourceConfig(EditAccountResource.class, DebugExceptionMapper.class);
	}
	
	//change address of a taxidriver. the address is valid
	@Test
	public void testChangeValidAddressCst(){

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
		CustomerInfo customerInfo = new CustomerInfo("SPYROS", "LEPENIOTIS", "ANDRAS", "SLEPENIOTIS", passwd, d, "KOSTAKI 1", "PEIRAIAS", 13671, "slepen@gmail.com", "MASTERCARD", "1234567891234567", "01/19", "123", 37.9508344, 23.6510941);

		// Submit the updated representation
		Response response =	target("editaccount/customeraddress/1").request().put(Entity.entity(customerInfo, MediaType.APPLICATION_JSON));

		// assertion on request status and database state
		Assert.assertEquals(200, response.getStatus());
		Customer newcustomer = em.find(Customer.class, (long)1);
		em.refresh(newcustomer);		
		Assert.assertEquals("KOSTAKI 1", newcustomer.getAddress());
		em.close();
	}


	//change address of a taxidriver. the address is valid
	@Test
	public void testChangeValidAddressCst_noUID(){

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}

		String passwd = "fdkE9skf";
		CustomerInfo customerInfo = new CustomerInfo("SPYROS", "LEPENIOTIS", "ANDRAS", "SLEPENIOTIS", passwd, d, "KOSTAKI 1", "PEIRAIAS", 13671, "slepen@gmail.com", "MASTERCARD", "1234567891234567", "01/19", "123", 37.9508344, 23.6510941);

		// Submit the updated representation
		Response response = target("editaccount/customeraddress/5").request().put(Entity.entity(customerInfo, MediaType.APPLICATION_JSON));

		// assertion on request status and database state
		Assert.assertEquals(404, response.getStatus());
	}

}
