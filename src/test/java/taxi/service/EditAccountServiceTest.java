package taxi.service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import taxi.model.Customer;
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

	//Tests for taxi driver's address changing
	@Test
	public void testChangeValidAddressTx(){
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

	@Test
	public void testChangeInValidAddress_emptyAddressTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeAddress("Taxi Driver", (long)5, " ", "PANORAMA", 12345);
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("SKYLADIKOY 1", taxiDr.getAddress());
	}

	@Test
	public void testChangeInValidAddress_emptyCityTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeAddress("Taxi Driver", (long)5, "KOSTAKI 1", " ", 12345);
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("ETHNIKI ODOS", taxiDr.getCity());
	}

	@Test
	public void testChangeInValidAddress_emptyZipCodeTx(){

		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeAddress("Taxi Driver", (long)5, "KOSTAKI 1", "ETFDAF", 0);
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals(11243, taxiDr.getZipCode());
	}

	@Test
	public void testChangeInValidAddress_nullAddressTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeAddress("Taxi Driver", (long)5, null, "PANORAMA", 12345);
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("SKYLADIKOY 1", taxiDr.getAddress());
	}

	@Test
	public void testChangeInValidAddress_nullCityTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeAddress("Taxi Driver", (long)5, "KOSTAKI 1", null, 12345);
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("ETHNIKI ODOS", taxiDr.getCity());

	}

	//Tests for customer's address changing
	@Test
	public void testChangeValidAddressCst(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeAddress("Customer", (long)1, "KOSTAKI 1", "PANORAMA", 12345);
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cst from Customer cst where cst.id = :tid");
		query.setParameter("tid", (long)1);
		Customer customer = (Customer)query.getSingleResult();
		Assert.assertEquals("KOSTAKI 1", customer.getAddress());
	}

	@Test
	public void testChangeInValidAddress_emptyAddressCst(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeAddress("Customer", (long)1, " ", "PANORAMA", 12345);
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cst from Customer cst where cst.id = :tid");
		query.setParameter("tid", (long)1);
		Customer customer = (Customer)query.getSingleResult();
		Assert.assertEquals("AGIOY NIKOLAOY 1", customer.getAddress());
	}

	@Test
	public void testChangeInValidAddress_emptyCityCst(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeAddress("Customer", (long)1, "KOSTAKI 1", " ", 12345);
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cst from Customer cst where cst.id = :tid");
		query.setParameter("tid", (long)1);
		Customer customer = (Customer)query.getSingleResult();
		Assert.assertEquals("PEIRAIAS", customer.getCity());
	}

	@Test
	public void testChangeInValidAddress_emptyZipCodeCst(){

		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeAddress("Customer", (long)1, "KOSTAKI 1", "PANORAMA", 0);
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cst from Customer cst where cst.id = :tid");
		query.setParameter("tid", (long)1);
		Customer customer = (Customer)query.getSingleResult();
		Assert.assertEquals(13671, customer.getZipCode());
	}

	@Test
	public void testChangeInValidAddress_nullAddressCst(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeAddress("Customer", (long)1, null, "PANORAMA", 12345);
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cst from Customer cst where cst.id = :tid");
		query.setParameter("tid", (long)1);
		Customer customer = (Customer)query.getSingleResult();
		Assert.assertEquals("AGIOY NIKOLAOY 1", customer.getAddress());
	}

	@Test
	public void testChangeInValidAddress_nullCityCst(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeAddress("Customer", (long)1, "KOSTAKI 1", null, 12345);
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cst from Customer cst where cst.id = :tid");
		query.setParameter("tid", (long)1);
		Customer customer = (Customer)query.getSingleResult();
		Assert.assertEquals("PEIRAIAS", customer.getCity());

	}

	//Tests for taxi driver's credit card changing
	@Test
	public void testChangeValidCreditCardTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", "1234567890123456", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1234567890123456", taxiDr.getCreditCardNumber());
	}

	@Test
	public void testChangeInValidCreditCard_emptyTypeTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, " ", "1234567890123456", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());
	}

	@Test
	public void testChangeInValidCreditCard_emptyNumTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", " ", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());

	}

	@Test
	public void testChangeInValidCreditCard_emptyDateTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", "1234567890123456", " ", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());

	}

	@Test
	public void testChangeInValidCreditCard_emptyCcvTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", "1234567890123456", "10/23", " ");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());

	}

	@Test
	public void testChangeInValidCreditCard_nullTypeTx(){

		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, null, "1234567890123456", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());
	}

	@Test
	public void testChangeInValidCreditCard_nullNumTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", null, "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());
	}

	@Test
	public void testChangeInValidCreditCard_nullDateTx(){

		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", "1234567890123456", null, "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());
	}

	@Test
	public void testChangeInValidCreditCard_nullCcvTx(){

		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", "1234567890123456", "10/23", null);
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());
	}

	@Test
	public void testChangeInValidCreditCard_expiredTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", "1234567890123456", "10/16", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());
	}

	@Test
	public void testChangeInValidCreditCard_dateFormatTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", "1234567890123456", "13/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());

	}

	@Test
	public void testChangeInValidCreditCard_numberFormatTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", "12345678012342356", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());
	}

	@Test
	public void testChangeInValidCreditCard_numFormLetterTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", "12345678012342f6", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());
	}

	@Test
	public void testChangeInValidCreditCard_ccvFormLetterTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", "1234567801234246", "10/23", "9f9");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());
	}

	//Tests for customer's credit card changing
	@Test
	public void testChangeValidCreditCardCst(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", "1234567890123456", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567890123456", cust.getCreditCardNumber());
	}

	@Test
	public void testChangeInValidCreditCard_emptyTypeCst(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeCreditCard("Customer", (long)1, " ", "1234567890123456", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());
	}

	@Test
	public void testChangeInValidCreditCard_emptyNumCst(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", " ", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());

	}

	@Test
	public void testChangeInValidCreditCard_emptyDateCst(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", "1234567890123456", " ", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());

	}

	@Test
	public void testChangeInValidCreditCard_emptyCcvCst(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", "1234567890123456", "10/23", " ");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());

	}

	@Test
	public void testChangeInValidCreditCard_nullTypeCst(){

		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeCreditCard("Customer", (long)1, null, "1234567890123456", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());
	}

	@Test
	public void testChangeInValidCreditCard_nullNumCst(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", null, "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());
	}

	@Test
	public void testChangeInValidCreditCard_nullDateCst(){

		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", "1234567890123456", null, "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());
	}

	@Test
	public void testChangeInValidCreditCard_nullCcvCst(){

		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", "1234567890123456", "10/23", null);
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());
	}

	@Test
	public void testChangeInValidCreditCard_expiredCst(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", "1234567890123456", "10/16", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());
	}

	@Test
	public void testChangeInValidCreditCard_dateFormatCst(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", "1234567890123456", "13/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());

	}

	@Test
	public void testChangeInValidCreditCard_numberFormatCst(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", "12345678012342356", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());
	}

	@Test
	public void testChangeInValidCreditCard_numFormLetterCst(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", "12345678012342f6", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());
	}

	@Test
	public void testChangeInValidCreditCard_ccvFormLetterCst(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", "1234567801234246", "10/23", "9f9");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());
	}

	//Tests for taxi driver's email changing
	@Test
	public void testChangeValidEmailTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeEmail("Taxi Driver", (long)5, "gkavadias@aueb.gr");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("gkavadias@aueb.gr", taxiDr.getEmail());

	}

	@Test
	public void testChangeInValidEmail_existingEmailTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeEmail("Taxi Driver", (long)5, "stgonidis@aueb.gr");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("makxris@aueb.gr", taxiDr.getEmail());
	}

	@Test
	public void testChangeInValidEmail_emptyEmailTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeEmail("Taxi Driver", (long)5, " ");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("makxris@aueb.gr", taxiDr.getEmail());
	}

	@Test
	public void testChangeInValidEmail_nullEmailTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeEmail("Taxi Driver", (long)5, null);
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("makxris@aueb.gr", taxiDr.getEmail());

	}

	@Test
	public void testChangeInValidEmail_noPatternEmailTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeEmail("Taxi Driver", (long)5, "stgonidisaueb.gr");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("makxris@aueb.gr", taxiDr.getEmail());

	}

	//Tests for customer's email changing
	@Test
	public void testChangeValidEmailCst(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeEmail("Customer", (long)1, "gkavadias@aueb.gr");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("gkavadias@aueb.gr", cust.getEmail());

	}

	@Test
	public void testChangeInValidEmail_existingEmailCst(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeEmail("Customer", (long)1, "vlamprakakis@gmail.com");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("slepen@gmail.com", cust.getEmail());
	}

	@Test
	public void testChangeInValidEmail_emptyEmailCst(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeEmail("Customer", (long)1, " ");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("slepen@gmail.com", cust.getEmail());
	}

	@Test
	public void testChangeInValidEmail_nullEmailCst(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeEmail("Customer", (long)1, null);
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("slepen@gmail.com", cust.getEmail());

	}

	@Test
	public void testChangeInValidEmail_noPatternEmailCst(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeEmail("Customer", (long)1, "vlamprakakis@gmail.com");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("slepen@gmail.com", cust.getEmail());

	}

	@After
	public void tearDown(){
		em.close();
	}
}
