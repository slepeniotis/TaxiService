package taxi.service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import taxi.model.Customer;
import taxi.model.TaxiDriver;
import taxi.persistence.Initializer;
import taxi.persistence.JPAUtil;

public class EditAccountServiceTest extends TaxiServiceTest {

	//Tests for taxi driver's address changing

	//change address of a taxidriver. the address is valid
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

	//change address of a taxidriver. user id is invalid.
	@Test
	public void testChangeValidAddressTx_noUID(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeAddress("Taxi Driver", (long)100, "KOSTAKI 1", "PANORAMA", 12345);
		Assert.assertNull(newAddr);
	}

	//change address of a taxidriver. usertype is invalid.
	@Test
	public void testChangeValidAddressTx_noUserType(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newAddr = (TaxiDriver)service.changeAddress("TaxifDriver", (long)5, "KOSTAKI 1", "PANORAMA", 12345);
		Assert.assertNull(newAddr);
	}

	//change address of a taxidriver. the address is invalid. address field = empty
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

	//change address of a taxidriver. the address is invalid. city field = empty
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

	//change address of a taxidriver. the address is invalid. zip code = 0
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

	//change address of a taxidriver. the address is invalid. address field = null
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

	//change address of a taxidriver. the address is invalid. city field = null
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

	//change address of a customer. the address is valid
	@Test
	public void testChangeValidAddressCst(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeAddress("Customer", (long)1, "KOSTAKI 1", "PANORAMA", 12345);
		
		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cst from Customer cst where cst.id = :tid");
		query.setParameter("tid", (long)1);
		Customer customer = (Customer)query.getSingleResult();
		Assert.assertEquals("KOSTAKI 1", customer.getAddress());
	}

	//change address of a customer. user id is invalid.
	@Test
	public void testChangeValidAddressCst_noUID(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeAddress("Customer", (long)100, "KOSTAKI 1", "PANORAMA", 12345);
		Assert.assertNull(newAddr);
	}

	//change address of a customer. usertype is invalid.
	@Test
	public void testChangeValidAddresCst_noUserType(){
		EditAccountService service = new EditAccountService();

		Customer newAddr = (Customer)service.changeAddress("Customerd", (long)1, "KOSTAKI 1", "PANORAMA", 12345);
		Assert.assertNull(newAddr);
	}

	//change address of a customer. the address is invalid. address field = empty
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

	//change address of a customer. the address is invalid. city field = empty
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

	//change address of a customer. the address is invalid. zip code field = 0
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

	//change address of a customer. the address is invalid. address field = null
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

	//change address of a customer. the address is invalid. city field = null
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

	//change Taxi Driver's credit card. credit card's info are valid
	@Test
	public void testChangeValidCreditCardTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver credit = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", "1234567890123456", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1234567890123456", taxiDr.getCreditCardNumber());
	}

	//change Taxi Driver's credit card. userid is invalid.
	@Test
	public void testChangeValidCreditCardTx_noUID(){
		EditAccountService service = new EditAccountService();

		TaxiDriver credit = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)100, "VISA", "1234567890123456", "10/23", "990");
		Assert.assertNull(credit);
	}

	//change Taxi Driver's credit card. user type is invalid.
	@Test
	public void testChangeValidCreditCardTx_noUserType(){
		EditAccountService service = new EditAccountService();

		TaxiDriver credit = (TaxiDriver)service.changeCreditCard("TaxisDriver", (long)5, "VISA", "1234567890123456", "10/23", "990");
		Assert.assertNull(credit);
	}

	//change Taxi Driver's credit card. credit card's info are invalid. card type = empty
	@Test
	public void testChangeInValidCreditCard_emptyTypeTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver credit = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, " ", "1234567890123456", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());
	}

	//change Taxi Driver's credit card. credit card's info are invalid. card number = empty
	@Test
	public void testChangeInValidCreditCard_emptyNumTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver credit = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", " ", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());

	}

	//change Taxi Driver's credit card. credit card's info are invalid. expiry date = empty
	@Test
	public void testChangeInValidCreditCard_emptyDateTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver credit = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", "1234567890123456", " ", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());

	}

	//change Taxi Driver's credit card. credit card's info are invalid. ccv = empty
	@Test
	public void testChangeInValidCreditCard_emptyCcvTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver credit = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", "1234567890123456", "10/23", " ");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());

	}

	//change Taxi Driver's credit card. credit card's info are invalid. card type = null
	@Test
	public void testChangeInValidCreditCard_nullTypeTx(){

		EditAccountService service = new EditAccountService();

		TaxiDriver credit = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, null, "1234567890123456", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());
	}

	//change Taxi Driver's credit card. credit card's info are invalid. card number = null
	@Test
	public void testChangeInValidCreditCard_nullNumTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver credit = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", null, "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());
	}

	//change Taxi Driver's credit card. credit card's info are invalid. date = null
	@Test
	public void testChangeInValidCreditCard_nullDateTx(){

		EditAccountService service = new EditAccountService();

		TaxiDriver credit = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", "1234567890123456", null, "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());
	}

	//change Taxi Driver's credit card. credit card's info are invalid. ccv = null
	@Test
	public void testChangeInValidCreditCard_nullCcvTx(){

		EditAccountService service = new EditAccountService();

		TaxiDriver credit = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", "1234567890123456", "10/23", null);
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());
	}

	//change Taxi Driver's credit card. credit card's info are invalid. date = expired
	@Test
	public void testChangeInValidCreditCard_expiredTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver credit = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", "1234567890123456", "10/16", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());
	}

	//change Taxi Driver's credit card. credit card's info are invalid. wrong date format
	@Test
	public void testChangeInValidCreditCard_dateFormatTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver credit = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", "1234567890123456", "13/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());

	}

	//change Taxi Driver's credit card. credit card's info are invalid. wrong card number format
	@Test
	public void testChangeInValidCreditCard_numberFormatTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver credit = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", "12345678012342356", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());
	}

	//change Taxi Driver's credit card. credit card's info are invalid. card number contains letter
	@Test
	public void testChangeInValidCreditCard_numFormLetterTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver credit = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", "12345678012342f6", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());
	}

	//change Taxi Driver's credit card. credit card's info are invalid. ccv contains letter
	@Test
	public void testChangeInValidCreditCard_ccvFormLetterTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver credit = (TaxiDriver)service.changeCreditCard("Taxi Driver", (long)5, "VISA", "1234567801234246", "10/23", "9f9");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("1223585989822541", taxiDr.getCreditCardNumber());
	}

	//Tests for customer's credit card changing

	//change Customer's credit card. credit card's info are valid.
	@Test
	public void testChangeValidCreditCardCst(){
		EditAccountService service = new EditAccountService();

		Customer credit = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", "1234567890123456", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567890123456", cust.getCreditCardNumber());
	}


	//change Customer's credit card. userid is invalid.
	@Test
	public void testChangeValidCreditCardCst_noUID(){
		EditAccountService service = new EditAccountService();

		Customer credit = (Customer)service.changeCreditCard("Customer", (long)100, "VISA", "1234567890123456", "10/23", "990");
		Assert.assertNull(credit);
	}

	//change Customer's credit card. user type is invalid.
	@Test
	public void testChangeValidCreditCardCst_noUserType(){
		EditAccountService service = new EditAccountService();

		Customer credit = (Customer)service.changeCreditCard("Customerd", (long)1, "VISA", "1234567890123456", "10/23", "990");
		Assert.assertNull(credit);
	}

	//change Customer's credit card. credit card's info are invalid. card type = empty
	@Test
	public void testChangeInValidCreditCard_emptyTypeCst(){
		EditAccountService service = new EditAccountService();

		Customer credit = (Customer)service.changeCreditCard("Customer", (long)1, " ", "1234567890123456", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());
	}

	//change Customer's credit card. credit card's info are invalid. card number = empty
	@Test
	public void testChangeInValidCreditCard_emptyNumCst(){
		EditAccountService service = new EditAccountService();

		Customer credit = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", " ", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());

	}

	//change Customer's credit card. credit card's info are invalid. date = empty
	@Test
	public void testChangeInValidCreditCard_emptyDateCst(){
		EditAccountService service = new EditAccountService();

		Customer credit = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", "1234567890123456", " ", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());

	}

	//change Customer's credit card. credit card's info are invalid. ccv = empty
	@Test
	public void testChangeInValidCreditCard_emptyCcvCst(){
		EditAccountService service = new EditAccountService();

		Customer credit = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", "1234567890123456", "10/23", " ");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());

	}

	//change Customer's credit card. credit card's info are invalid. card type = null
	@Test
	public void testChangeInValidCreditCard_nullTypeCst(){

		EditAccountService service = new EditAccountService();

		Customer credit = (Customer)service.changeCreditCard("Customer", (long)1, null, "1234567890123456", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());
	}

	//change Customer's credit card. credit card's info are invalid. card number = null
	@Test
	public void testChangeInValidCreditCard_nullNumCst(){
		EditAccountService service = new EditAccountService();

		Customer credit = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", null, "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());
	}

	//change Customer's credit card. credit card's info are invalid. date = null
	@Test
	public void testChangeInValidCreditCard_nullDateCst(){

		EditAccountService service = new EditAccountService();

		Customer credit = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", "1234567890123456", null, "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());
	}

	//change Customer's credit card. credit card's info are invalid. ccv = null
	@Test
	public void testChangeInValidCreditCard_nullCcvCst(){

		EditAccountService service = new EditAccountService();

		Customer credit = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", "1234567890123456", "10/23", null);
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());
	}

	//change Customer's credit card. credit card's info are invalid. date expired
	@Test
	public void testChangeInValidCreditCard_expiredCst(){
		EditAccountService service = new EditAccountService();

		Customer credit = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", "1234567890123456", "10/16", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());
	}

	//change Customer's credit card. credit card's info are invalid. wrong date format
	@Test
	public void testChangeInValidCreditCard_dateFormatCst(){
		EditAccountService service = new EditAccountService();

		Customer credit = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", "1234567890123456", "13/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());

	}

	//change Customer's credit card. credit card's info are invalid. wrong card number format
	@Test
	public void testChangeInValidCreditCard_numberFormatCst(){
		EditAccountService service = new EditAccountService();

		Customer credit = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", "12345678012342356", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());
	}

	//change Customer's credit card. credit card's info are invalid. card number contains letter
	@Test
	public void testChangeInValidCreditCard_numFormLetterCst(){
		EditAccountService service = new EditAccountService();

		Customer credit = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", "12345678012342f6", "10/23", "990");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());
	}

	//change Customer's credit card. credit card's info are invalid. ccv contains letter
	@Test
	public void testChangeInValidCreditCard_ccvFormLetterCst(){
		EditAccountService service = new EditAccountService();

		Customer credit = (Customer)service.changeCreditCard("Customer", (long)1, "VISA", "1234567801234246", "10/23", "9f9");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("1234567891234567", cust.getCreditCardNumber());
	}

	//Tests for taxi driver's email changing

	//change Taxi Driver's email. email is valid.
	@Test
	public void testChangeValidEmailTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newEmail = (TaxiDriver)service.changeEmail("Taxi Driver", (long)5, "gkavadias@aueb.gr");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("gkavadias@aueb.gr", taxiDr.getEmail());

	}	

	//change Taxi Driver's email. userid is invalid
	@Test
	public void testChangeValidEmailTx_noUID(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newEmail = (TaxiDriver)service.changeEmail("Taxi Driver", (long)100, "gkavadias@aueb.gr");
		Assert.assertNull(newEmail);

	}		

	//change Taxi Driver's email. user type is invalid
	@Test
	public void testChangeValidEmailTx_noUserType(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newEmail = (TaxiDriver)service.changeEmail("TaxidDriver", (long)5, "gkavadias@aueb.gr");
		Assert.assertNull(newEmail);

	}

	//change Taxi Driver's email. email is invalid. existing email
	@Test
	public void testChangeInValidEmail_existingEmailTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newEmail = (TaxiDriver)service.changeEmail("Taxi Driver", (long)5, "stgonidis@aueb.gr");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("makxris@aueb.gr", taxiDr.getEmail());
	}

	//change Taxi Driver's email. email is invalid. empty email
	@Test
	public void testChangeInValidEmail_emptyEmailTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newEmail = (TaxiDriver)service.changeEmail("Taxi Driver", (long)5, " ");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("makxris@aueb.gr", taxiDr.getEmail());
	}

	//change Taxi Driver's email. email is invalid. null email
	@Test
	public void testChangeInValidEmail_nullEmailTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newEmail = (TaxiDriver)service.changeEmail("Taxi Driver", (long)5, null);
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("makxris@aueb.gr", taxiDr.getEmail());

	}

	//change Taxi Driver's email. email is invalid. email wrong format
	@Test
	public void testChangeInValidEmail_noPatternEmailTx(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newEmail = (TaxiDriver)service.changeEmail("Taxi Driver", (long)5, "stgonidisaueb.gr");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxidr.id = :tid");
		query.setParameter("tid", (long)5);
		TaxiDriver taxiDr = (TaxiDriver)query.getSingleResult();
		Assert.assertEquals("makxris@aueb.gr", taxiDr.getEmail());

	}

	//Tests for customer's email changing

	//change Customer's email. email is valid
	@Test
	public void testChangeValidEmailCst(){
		EditAccountService service = new EditAccountService();

		Customer newEmail = (Customer)service.changeEmail("Customer", (long)1, "gkavadias@aueb.gr");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("gkavadias@aueb.gr", cust.getEmail());

	}	

	//change Customer's email. userid is invalid
	@Test
	public void testChangeValidEmailCst_noUID(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newEmail = (TaxiDriver)service.changeEmail("Customer", (long)100, "gkavadias@aueb.gr");
		Assert.assertNull(newEmail);

	}			

	//change Customer's email. user type is invalid
	@Test
	public void testChangeValidEmailCst_noUserType(){
		EditAccountService service = new EditAccountService();

		TaxiDriver newEmail = (TaxiDriver)service.changeEmail("Customerd", (long)1, "gkavadias@aueb.gr");
		Assert.assertNull(newEmail);

	}

	//change Customer's email. email is invalid. existing email
	@Test
	public void testChangeInValidEmail_existingEmailCst(){
		EditAccountService service = new EditAccountService();

		Customer newEmail = (Customer)service.changeEmail("Customer", (long)1, "vlamprakakis@gmail.com");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("slepen@gmail.com", cust.getEmail());
	}

	//change Customer's email. email is invalid. empty email
	@Test
	public void testChangeInValidEmail_emptyEmailCst(){
		EditAccountService service = new EditAccountService();

		Customer newEmail = (Customer)service.changeEmail("Customer", (long)1, " ");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("slepen@gmail.com", cust.getEmail());
	}

	//change Customer's email. email is invalid. null email
	@Test
	public void testChangeInValidEmail_nullEmailCst(){
		EditAccountService service = new EditAccountService();

		Customer newEmail = (Customer)service.changeEmail("Customer", (long)1, null);
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("slepen@gmail.com", cust.getEmail());

	}

	//change Customer's email. email is invalid. email wrong format
	@Test
	public void testChangeInValidEmail_noPatternEmailCst(){
		EditAccountService service = new EditAccountService();

		Customer newEmail = (Customer)service.changeEmail("Customer", (long)1, "vlamprakakis@gmail.com");
		em.close();

		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select cust from Customer cust where cust.id = :tid");
		query.setParameter("tid", (long)1);
		Customer cust = (Customer)query.getSingleResult();
		Assert.assertEquals("slepen@gmail.com", cust.getEmail());

	}
}
