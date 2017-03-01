package taxi.service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.aueb.mscis.sample.model.Movie;
import gr.aueb.mscis.sample.service.MovieService;
import taxi.model.Taxi;
import taxi.model.TaxiDriver;
import taxi.persistence.Initializer;
import taxi.persistence.JPAUtil;

public class EditAccountServiceTest {

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

	//Tests for Taxi changing
	/*@Test
	public void testChangeValidTaxi(){
		
		EditAccountService service = new EditAccountService();
		
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", 5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();		
		Taxi newTaxi = service.changeTaxi(taxiDr, "TOYOTA YARIS", "SPORT", "YOX4454", "10/1999", 27.094837, 35.23123);
						
		Assert.assertNotEquals(0, newTaxi.getId());
		Assert.assertNotEquals(0, taxiDr.getOwns().getId());
		em.close();
		
		em = JPAUtil.getCurrentEntityManager();	

	}

	@Test
	public void testChangeInValidTaxi_nullNewTaxi(){

	}	
	
	@Test
	public void testChangeInValidTaxi_emptyNewTaxi(){

	}

	@Test
	public void testChangeInValidTaxi_exLicence(){

	}

	@Test
	public void testChangeInValidTaxi_invLicence(){

	}	

	@Test
	public void testChangeInValidTaxi_invDateAfter(){

	}

	@Test
	public void testChangeInValidTaxi_invDateFormatLetter(){

	}

	@Test
	public void testChangeInValidTaxi_invDateFormatMonthNum(){

	}
	
	@Test
	public void testChangeInValidTaxi_noCoordinates(){

	}*/

	//Tests for address changing
	@Test
	public void testChangeValidAddress(){
		EditAccountService service = new EditAccountService();
						
		TaxiDriver newAddr = (TaxiDriver)service.changeAddress("Taxi Driver", (long)5, "KOSTAKI 1", "PANORAMA", 12345);
		em.close();
		
		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("KOSTAKI 1", taxiDr.getAddress());
	}

	/*@Test
	public void testChangeInValidAddress_emptyAddress(){

	}

	@Test
	public void testChangeInValidAddress_emptyCity(){

	}

	@Test
	public void testChangeInValidAddress_emptyZipCode(){

	}

	@Test
	public void testChangeInValidAddress_nullAddress(){

	}

	@Test
	public void testChangeInValidAddress_nullCity(){

	}

	@Test
	public void testChangeInValidAddress_nullZipCode(){

	}

	//Tests for credit card changing
	@Test
	public void testChangeValidCreditCard(){

	}

	@Test
	public void testChangeInValidCreditCard_emptyType(){

	}

	@Test
	public void testChangeInValidCreditCard_emptyNum(){

	}

	@Test
	public void testChangeInValidCreditCard_emptyDate(){

	}

	@Test
	public void testChangeInValidCreditCard_emptyCcv(){

	}
	
	@Test
	public void testChangeInValidCreditCard_nullType(){

	}

	@Test
	public void testChangeInValidCreditCard_nullNum(){

	}

	@Test
	public void testChangeInValidCreditCard_nullDate(){

	}

	@Test
	public void testChangeInValidCreditCard_nullCcv(){

	}

	@Test
	public void testChangeInValidCreditCard_expired(){

	}

	@Test
	public void testChangeInValidCreditCard_dateFormat(){

	}

	@Test
	public void testChangeInValidCreditCard_numberFormat(){

	}

	@Test
	public void testChangeInValidCreditCard_numFormLetter(){

	}

	@Test
	public void testChangeInValidCreditCard_ccvFormLetter(){

	}

	//Tests for email changing
	@Test
	public void testChangeValidEmail(){

	}
	
	@Test
	public void testChangeInValidEmail_existingEmail(){

	}

	@Test
	public void testChangeInValidEmail_emptyEmail(){

	}
	
	@Test
	public void testChangeInValidEmail_nullEmail(){

	}

	@Test
	public void testChangeInValidEmail_noPatternEmail(){

	}*/
}
