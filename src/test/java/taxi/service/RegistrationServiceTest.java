package taxi.service;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import taxi.persistence.Initializer;
import taxi.persistence.JPAUtil;

public class RegistrationServiceTest {

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

	//Tests for Taxi
	@Test
	public void testPersistAValidTaxi(){

	}

	@Test
	public void testPersistAnInvalidTaxi(){

	}

	@Test
	public void testPersistAnInvalidTaxi_exLicence(){

	}

	@Test
	public void testPersistAnInvalidTaxi_invLicence(){

	}

	@Test
	public void testPersistAnInvTaxi_invDateAfter(){

	}

	@Test
	public void testPersistAnInvTaxi_invDateFormatLetter(){

	}

	@Test
	public void testPersistAnInvTaxi_invDateFormatMonthNum(){

	}
	
	@Test
	public void testPersistAnInvTaxi_noCoordinates(){

	}

	//Tests for Taxi Driver
	@Test
	public void testPersistAValidTaxiDr(){

	}

	@Test
	public void testPersistAnInvTaxiDr_nullTaxi(){

	}

	@Test
	public void testPersistAnInvTaxiDr(){

	}

	@Test
	public void testPersistAnInvTaxiDr_connectedTaxi(){

	}

	@Test
	public void testPersistAnInvTaxiDr_existingUsername(){

	}

	@Test
	public void testPersistAnInvTaxiDr_nullPasswd(){

	}

	@Test
	public void testPersistAnInvTaxiDr_smallPasswd(){

	}

	@Test
	public void testPersistAnInvTaxiDr_noUpperPasswd(){

	}

	@Test
	public void testPersistAnInvTaxiDr_noNumPasswd(){

	}

	@Test
	public void testPersistAnInvTaxiDr_existingEmail(){

	}

	@Test
	public void testPersistAnInvTaxiDr_emptyEmail(){

	}

	@Test
	public void testPersistAnInvTaxiDr_noPatternEmail(){

	}

	@Test
	public void testPersistAnInvTaxiDr_expiredCrdtCrd(){

	}

	@Test
	public void testPersistAnInvTaxiDr_dateFormatCrdtCrd(){

	}

	@Test
	public void testPersistAnInvTaxiDr_numberFormatCrdtCrd(){

	}

	@Test
	public void testPersistAnInvTaxiDr_numFormLetterCrdtCrd(){

	}

	@Test
	public void testPersistAnInvTaxiDr_ccvFormLetterCrdtCrd(){

	}

	@Test
	public void testPersistAnInvTaxiDr_afterDateOfBirth(){

	}


	//Tests for Customer
	@Test
	public void testPersistAValidCustomer(){

	}

	@Test
	public void testPersistAnInvCustomer(){

	}

	@Test
	public void testPersistAnInvCustomer_existingUsername(){

	}

	@Test
	public void testPersistAnInvCustomer_nullPasswd(){

	}

	@Test
	public void testPersistAnInvCustomer_smallPasswd(){

	}

	@Test
	public void testPersistAnInvCustomer_noUpperPasswd(){

	}

	@Test
	public void testPersistAnInvCustomer_noNumPasswd(){

	}

	@Test
	public void testPersistAnInvCustomer_existingEmail(){

	}

	@Test
	public void testPersistAnInvCustomer_emptyEmail(){

	}

	@Test
	public void testPersistAnInvCustomer_noPatternEmail(){

	}

	@Test
	public void testPersistAnInvCustomer_expiredCrdtCrd(){

	}

	@Test
	public void testPersistAnInvCustomer_dateFormatCrdtCrd(){

	}

	@Test
	public void testPersistAnInvCustomer_numberFormatCrdtCrd(){

	}

	@Test
	public void testPersistAnInvCustomer_numFormLetterCrdtCrd(){

	}

	@Test
	public void testPersistAnInvCustomer_ccvFormLetterCrdtCrd(){

	}

	@Test
	public void testPersistAnInvCustomer_afterDateOfBirth(){

	}
	
	@Test
	public void testPersistAnInvCustomer_noCoordinates(){

	}

}
