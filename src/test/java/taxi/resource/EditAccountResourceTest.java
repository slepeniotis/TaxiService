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

	//change address of a customer. the address is valid
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
		Response response =	target("editaccount/CustomerAddress/1").request().put(Entity.entity(customerInfo, MediaType.APPLICATION_JSON));

		// assertion on request status and database state
		Assert.assertEquals(200, response.getStatus());
		Customer newcustomer = em.find(Customer.class, (long)1);
		em.refresh(newcustomer);		
		Assert.assertEquals("KOSTAKI 1", newcustomer.getAddress());
		em.close();
	}


	//change address of a customer. the address is valid the uid is invalid
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
		Response response = target("editaccount/CustomerAddress/5").request().put(Entity.entity(customerInfo, MediaType.APPLICATION_JSON));

		// assertion on request status and database state
		Assert.assertEquals(404, response.getStatus());
	}

	//change address of a taxi driver. the address is valid
	@Test
	public void testChangeValidAddressTx(){

		EntityManager em = JPAUtil.getCurrentEntityManager();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("12/11/1970");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}

		TaxiDriver tx = em.find(TaxiDriver.class, (long)5);
		TaxiDriverInfo taxiDriverInfo = TaxiDriverInfo.wrap(tx);
		taxiDriverInfo.setAddress("KOSTAKI 1");

		// Submit the updated representation
		Response response =	target("editaccount/TaxiDriverAddress/5").request().put(Entity.entity(taxiDriverInfo, MediaType.APPLICATION_JSON));

		// assertion on request status and database state
		Assert.assertEquals(200, response.getStatus());
		TaxiDriver newTaxiDriver = em.find(TaxiDriver.class, (long)5);
		em.refresh(newTaxiDriver);		
		Assert.assertEquals("KOSTAKI 1", newTaxiDriver.getAddress());
		em.close();
	}


	//change address of a taxi driver. the address is valid the uid is invalid
	@Test
	public void testChangeValidAddressTx_noUID(){

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("12/11/1970");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}

		TaxiDriverInfo taxiDriverInfo = new TaxiDriverInfo("MAKIS", "XRISTODOYLOPOYLOS", "ANDRAS", "MAKXRIS", d, "KOSTAKI 1", "ETHNIKI ODOS", 11243, "makxris@aueb.gr", "MASTERCARD", "1223585989822541", "01/18", "321");

		// Submit the updated representation
		Response response = target("editaccount/TaxiDriverAddress/1").request().put(Entity.entity(taxiDriverInfo, MediaType.APPLICATION_JSON));

		// assertion on request status and database state
		Assert.assertEquals(404, response.getStatus());
	}
	
	//change email of a customer. the email is valid
		@Test
		public void testChangeValidEmailCst(){

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
			CustomerInfo customerInfo = new CustomerInfo("SPYROS", "LEPENIOTIS", "ANDRAS", "SLEPENIOTIS", passwd, d, "AGIOY NIKOLAOY 1", "PEIRAIAS", 13671, "otinanai@gmail.com", "MASTERCARD", "1234567891234567", "01/19", "123", 37.9508344, 23.6510941);

			// Submit the updated representation
			Response response =	target("editaccount/CustomerEmail/1").request().put(Entity.entity(customerInfo, MediaType.APPLICATION_JSON));

			// assertion on request status and database state
			Assert.assertEquals(200, response.getStatus());
			Customer newcustomer = em.find(Customer.class, (long)1);
			em.refresh(newcustomer);		
			Assert.assertEquals("otinanai@gmail.com", newcustomer.getEmail());
			em.close();
		}


		//change email of a customer. the email is valid the uid is invalid
		@Test
		public void testChangeValidEmailCst_noUID(){

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date d = new Date();
			try {
				d = sdf.parse("21/12/1980");			
			}
			catch (ParseException e){
				System.out.println(e.getStackTrace());
			}

			String passwd = "fdkE9skf";
			CustomerInfo customerInfo = new CustomerInfo("SPYROS", "LEPENIOTIS", "ANDRAS", "SLEPENIOTIS", passwd, d, "AGIOY NIKOLAOY 1", "PEIRAIAS", 13671, "otinanai@gmail.com", "MASTERCARD", "1234567891234567", "01/19", "123", 37.9508344, 23.6510941);

			// Submit the updated representation
			Response response = target("editaccount/CustomerEmail/5").request().put(Entity.entity(customerInfo, MediaType.APPLICATION_JSON));

			// assertion on request status and database state
			Assert.assertEquals(404, response.getStatus());
		}		

		//change email of a taxi driver. the email is valid
		@Test
		public void testChangeValidEmailTx(){

			EntityManager em = JPAUtil.getCurrentEntityManager();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date d = new Date();
			try {
				d = sdf.parse("12/11/1970");			
			}
			catch (ParseException e){
				System.out.println(e.getStackTrace());
			}

			TaxiDriver tx = em.find(TaxiDriver.class, (long)5);
			TaxiDriverInfo taxiDriverInfo = TaxiDriverInfo.wrap(tx);
			taxiDriverInfo.setEmail("otinanai@gmail.com");

			// Submit the updated representation
			Response response =	target("editaccount/TaxiDriverEmail/5").request().put(Entity.entity(taxiDriverInfo, MediaType.APPLICATION_JSON));

			// assertion on request status and database state
			Assert.assertEquals(200, response.getStatus());
			TaxiDriver newTaxiDriver = em.find(TaxiDriver.class, (long)5);
			em.refresh(newTaxiDriver);		
			Assert.assertEquals("otinanai@gmail.com", newTaxiDriver.getEmail());
			em.close();
		}


		//change email of a taxi driver. the email is valid the uid is invalid
		@Test
		public void testChangeValidEmailTx_noUID(){

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date d = new Date();
			try {
				d = sdf.parse("12/11/1970");			
			}
			catch (ParseException e){
				System.out.println(e.getStackTrace());
			}

			TaxiDriverInfo taxiDriverInfo = new TaxiDriverInfo("MAKIS", "XRISTODOYLOPOYLOS", "ANDRAS", "MAKXRIS", d, "SKYLADIKOY 1", "ETHNIKI ODOS", 11243, "otinanai@gmail.com", "MASTERCARD", "1223585989822541", "01/18", "321");

			// Submit the updated representation
			Response response = target("editaccount/TaxiDriverEmail/1").request().put(Entity.entity(taxiDriverInfo, MediaType.APPLICATION_JSON));

			// assertion on request status and database state
			Assert.assertEquals(404, response.getStatus());
		}		
		
		//change credit card of a customer. the credit card is valid
				@Test
				public void testChangeValidCreditCardNumCst(){

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
					CustomerInfo customerInfo = new CustomerInfo("SPYROS", "LEPENIOTIS", "ANDRAS", "SLEPENIOTIS", passwd, d, "AGIOY NIKOLAOY 1", "PEIRAIAS", 13671, "slepen@gmail.com", "MASTERCARD", "7894561237894561", "01/19", "123", 37.9508344, 23.6510941);

					// Submit the updated representation
					Response response =	target("editaccount/CustomerCreditCard/1").request().put(Entity.entity(customerInfo, MediaType.APPLICATION_JSON));

					// assertion on request status and database state
					Assert.assertEquals(200, response.getStatus());
					Customer newcustomer = em.find(Customer.class, (long)1);
					em.refresh(newcustomer);		
					Assert.assertEquals("7894561237894561", newcustomer.getCreditCardNumber());
					em.close();
				}


				//change credit card of a customer. the email is credit card the uid is invalid
				@Test
				public void testChangeValidCreditCardNumCst_noUID(){

					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date d = new Date();
					try {
						d = sdf.parse("21/12/1980");			
					}
					catch (ParseException e){
						System.out.println(e.getStackTrace());
					}

					String passwd = "fdkE9skf";
					CustomerInfo customerInfo = new CustomerInfo("SPYROS", "LEPENIOTIS", "ANDRAS", "SLEPENIOTIS", passwd, d, "AGIOY NIKOLAOY 1", "PEIRAIAS", 13671, "slepen@gmail.com", "MASTERCARD", "7894561237894561", "01/19", "123", 37.9508344, 23.6510941);

					// Submit the updated representation
					Response response = target("editaccount/CustomerCreditCard/5").request().put(Entity.entity(customerInfo, MediaType.APPLICATION_JSON));

					// assertion on request status and database state
					Assert.assertEquals(404, response.getStatus());
				}			
				
				

				//change credit card of a taxi driver. the credit card is valid
				@Test
				public void testChangeValidCreditCardNumTx(){

					EntityManager em = JPAUtil.getCurrentEntityManager();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date d = new Date();
					try {
						d = sdf.parse("12/11/1970");			
					}
					catch (ParseException e){
						System.out.println(e.getStackTrace());
					}

					TaxiDriver tx = em.find(TaxiDriver.class, (long)5);
					TaxiDriverInfo taxiDriverInfo = TaxiDriverInfo.wrap(tx);
					taxiDriverInfo.setCreditCardNumber("7894561237894561");

					// Submit the updated representation
					Response response =	target("editaccount/TaxiDriverCreditCard/5").request().put(Entity.entity(taxiDriverInfo, MediaType.APPLICATION_JSON));

					// assertion on request status and database state
					Assert.assertEquals(200, response.getStatus());
					TaxiDriver newTaxiDriver = em.find(TaxiDriver.class, (long)5);
					em.refresh(newTaxiDriver);		
					Assert.assertEquals("7894561237894561", newTaxiDriver.getCreditCardNumber());
					em.close();
				}


				//change credit card of a taxi driver. the email is credit card the uid is invalid
				@Test
				public void testChangeValidCreditCardNumTx_noUID(){

					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date d = new Date();
					try {
						d = sdf.parse("12/11/1970");			
					}
					catch (ParseException e){
						System.out.println(e.getStackTrace());
					}

					TaxiDriverInfo taxiDriverInfo = new TaxiDriverInfo("MAKIS", "XRISTODOYLOPOYLOS", "ANDRAS", "MAKXRIS", d, "SKYLADIKOY 1", "ETHNIKI ODOS", 11243, "makxris@aueb.gr", "MASTERCARD", "7894561237894561", "01/18", "321");

					// Submit the updated representation
					Response response = target("editaccount/TaxiDriverCreditCard/1").request().put(Entity.entity(taxiDriverInfo, MediaType.APPLICATION_JSON));

					// assertion on request status and database state
					Assert.assertEquals(404, response.getStatus());
				}
				

}
