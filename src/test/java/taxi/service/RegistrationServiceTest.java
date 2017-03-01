package taxi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import taxi.model.Customer;
import taxi.model.Route;
import taxi.model.Taxi;
import taxi.model.TaxiDriver;
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

	//Tests for Taxi
	@Test
	public void testPersistAValidTaxi(){
		RegistrationService service = new RegistrationService();

		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "11/2013", 35.2341223, 27.234325);
		Assert.assertNotEquals(0, newTaxi.getId());
	}

	@Test
	public void testPersistAnInvalidTaxi(){
		RegistrationService service = new RegistrationService();

		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", " ", "11/2013", 35.2341223, 27.234325);
		Assert.assertNull(newTaxi);

	}

	@Test
	public void testPersistAnInvalidTaxi_exLicence(){
		RegistrationService service = new RegistrationService();

		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "AHX0987", "11/2013", 35.2341223, 27.234325);
		Assert.assertNull(newTaxi);
	}

	@Test
	public void testPersistAnInvalidTaxi_invLicence(){
		RegistrationService service = new RegistrationService();

		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "AH00987", "11/2013", 35.2341223, 27.234325);
		Assert.assertNull(newTaxi);
	}

	@Test
	public void testPersistAnInvTaxi_invDateAfter(){
		RegistrationService service = new RegistrationService();

		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "11/2017", 35.2341223, 27.234325);
		Assert.assertNull(newTaxi);
	}

	@Test
	public void testPersistAnInvTaxi_invDateFormatLetter(){
		RegistrationService service = new RegistrationService();

		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "1f/2017", 35.2341223, 27.234325);
		Assert.assertNull(newTaxi);
	}

	@Test
	public void testPersistAnInvTaxi_invDateFormatMonthNum(){
		RegistrationService service = new RegistrationService();

		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "13/2017", 35.2341223, 27.234325);
		Assert.assertNull(newTaxi);
	}

	@Test
	public void testPersistAnInvTaxi_noCoordinates(){
		RegistrationService service = new RegistrationService();

		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "11/2013", 0, 0);
		Assert.assertNull(newTaxi);
	}

	//Tests for Taxi Driver
	@Test
	public void testPersistAValidTaxiDr(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "11/2013", 35.2341223, 27.234325);
		
		TaxiDriver newTaxiDr = service.registerTaxiDriver("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", 
				"Tdkfo9e3", d,  "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "1234567890123456", 
				"10/23", "334", newTaxi);
		
		Assert.assertNotEquals(0, newTaxiDr.getId());
	}

	@Test
	public void testPersistAnInvTaxiDr_nullTaxi(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		TaxiDriver newTaxiDr = service.registerTaxiDriver("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", 
				"Tdkfo9e3", d,  "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "1234567890123456", 
				"10/23", "334", null);
		
		Assert.assertNull(newTaxiDr);
	}

	@Test
	public void testPersistAnInvTaxiDr(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "11/2013", 35.2341223, 27.234325);
		
		TaxiDriver newTaxiDr = service.registerTaxiDriver("NIKOS", "NIKOLAOY", "ANDRAS", " ", 
				"Tdkfo9e3", d,  "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "1234567890123456", 
				"10/23", "334", newTaxi);
		
		Assert.assertNull(newTaxiDr);
	}

	@Test
	public void testPersistAnInvTaxiDr_connectedTaxi(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Taxi newTaxi = em.find(Taxi.class, (long)6);
		
		TaxiDriver newTaxiDr = service.registerTaxiDriver("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", 
				"Tdkfo9e3", d,  "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "1234567890123456", 
				"10/23", "334", newTaxi);
		
		Assert.assertNull(newTaxiDr);
	}

	@Test
	public void testPersistAnInvTaxiDr_existingUsername(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "11/2013", 35.2341223, 27.234325);
		
		TaxiDriver newTaxiDr = service.registerTaxiDriver("NIKOS", "NIKOLAOY", "ANDRAS", "MAKXRIS", 
				"Tdkfo9e3", d,  "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "1234567890123456", 
				"10/23", "334", newTaxi);
		
		Assert.assertNull(newTaxiDr);
	}

	@Test
	public void testPersistAnInvTaxiDr_nullPasswd(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "11/2013", 35.2341223, 27.234325);
		
		TaxiDriver newTaxiDr = service.registerTaxiDriver("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", 
				null, d,  "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "1234567890123456", 
				"10/23", "334", newTaxi);
		
		Assert.assertNull(newTaxiDr);
	}

	@Test
	public void testPersistAnInvTaxiDr_smallPasswd(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "11/2013", 35.2341223, 27.234325);
		
		TaxiDriver newTaxiDr = service.registerTaxiDriver("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", 
				"Tdkf93", d,  "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "1234567890123456", 
				"10/23", "334", newTaxi);
		
		Assert.assertNull(newTaxiDr);
	}

	@Test
	public void testPersistAnInvTaxiDr_noUpperPasswd(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "11/2013", 35.2341223, 27.234325);
		
		TaxiDriver newTaxiDr = service.registerTaxiDriver("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", 
				"tdkfo9e3", d,  "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "1234567890123456", 
				"10/23", "334", newTaxi);
		
		Assert.assertNull(newTaxiDr);
	}

	@Test
	public void testPersistAnInvTaxiDr_noNumPasswd(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "11/2013", 35.2341223, 27.234325);
		
		TaxiDriver newTaxiDr = service.registerTaxiDriver("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", 
				"Tdkfoeef", d,  "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "1234567890123456", 
				"10/23", "334", newTaxi);
		
		Assert.assertNull(newTaxiDr);
	}

	@Test
	public void testPersistAnInvTaxiDr_existingEmail(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "11/2013", 35.2341223, 27.234325);
		
		TaxiDriver newTaxiDr = service.registerTaxiDriver("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", 
				"Tdkfo9e3", d,  "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "makxris@aueb.gr", "VISA", "1234567890123456", 
				"10/23", "334", newTaxi);
		
		Assert.assertNull(newTaxiDr);
	}

	@Test
	public void testPersistAnInvTaxiDr_emptyEmail(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "11/2013", 35.2341223, 27.234325);
		
		TaxiDriver newTaxiDr = service.registerTaxiDriver("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", 
				"Tdkfo9e3", d,  "KOLOPETINITSAS 2", "DRAPETSONA", 23456, " ", "VISA", "1234567890123456", 
				"10/23", "334", newTaxi);
		
		Assert.assertNull(newTaxiDr);
	}

	@Test
	public void testPersistAnInvTaxiDr_noPatternEmail(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "11/2013", 35.2341223, 27.234325);
		
		TaxiDriver newTaxiDr = service.registerTaxiDriver("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", 
				"Tdkfo9e3", d,  "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikolaougmail.com", "VISA", "1234567890123456", 
				"10/23", "334", newTaxi);
		
		Assert.assertNull(newTaxiDr);
	}

	@Test
	public void testPersistAnInvTaxiDr_expiredCrdtCrd(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "11/2013", 35.2341223, 27.234325);
		
		TaxiDriver newTaxiDr = service.registerTaxiDriver("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", 
				"Tdkfo9e3", d,  "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikolaou@gmail.com", "VISA", "1234567890123456", 
				"10/16", "334", newTaxi);
		
		Assert.assertNull(newTaxiDr);
	}

	@Test
	public void testPersistAnInvTaxiDr_dateFormatCrdtCrd(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "11/2013", 35.2341223, 27.234325);
		
		TaxiDriver newTaxiDr = service.registerTaxiDriver("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", 
				"Tdkfo9e3", d,  "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikolaou@gmail.com", "VISA", "1234567890123456", 
				"1f/18", "334", newTaxi);
		
		Assert.assertNull(newTaxiDr);
	}

	@Test
	public void testPersistAnInvTaxiDr_numberFormatCrdtCrd(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "11/2013", 35.2341223, 27.234325);
		
		TaxiDriver newTaxiDr = service.registerTaxiDriver("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", 
				"Tdkfo9e3", d,  "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikolaou@gmail.com", "VISA", "123456789013456", 
				"10/18", "334", newTaxi);
		
		Assert.assertNull(newTaxiDr);
	}

	@Test
	public void testPersistAnInvTaxiDr_numFormLetterCrdtCrd(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "11/2013", 35.2341223, 27.234325);
		
		TaxiDriver newTaxiDr = service.registerTaxiDriver("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", 
				"Tdkfo9e3", d,  "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "123456789f123456", 
				"10/23", "334", newTaxi);
		
		Assert.assertNull(newTaxiDr);
	}

	@Test
	public void testPersistAnInvTaxiDr_ccvFormLetterCrdtCrd(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "11/2013", 35.2341223, 27.234325);
		
		TaxiDriver newTaxiDr = service.registerTaxiDriver("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", 
				"Tdkfo9e3", d,  "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "1234567890123456", 
				"10/23", "3f4", newTaxi);
		
		Assert.assertNull(newTaxiDr);
	}

	@Test
	public void testPersistAnInvTaxiDr_afterDateOfBirth(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/2017");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "11/2013", 35.2341223, 27.234325);
		
		TaxiDriver newTaxiDr = service.registerTaxiDriver("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", 
				"Tdkfo9e3", d,  "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "1234567890123456", 
				"10/23", "334", newTaxi);
		
		Assert.assertNull(newTaxiDr);
	}


	//Tests for Customer
	@Test
	public void testPersistAValidCustomer(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Customer newCust = service.registerCustomer("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", "Tdkfo9e3", d, 
				35.643543, 27.5324534, "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "1234567890123456", 
				"10/23", "334");
		
		Assert.assertNotEquals(0, newCust.getId());
	}

	@Test
	public void testPersistAnInvCustomer(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Customer newCust = service.registerCustomer("NIKOS", "NIKOLAOY", "ANDRAS", " ", "Tdkfo9e3", d, 
				35.643543, 27.5324534, "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "1234567890123456", 
				"10/23", "334");
		
		Assert.assertNull(newCust);
	}

	@Test
	public void testPersistAnInvCustomer_existingUsername(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Customer newCust = service.registerCustomer("NIKOS", "NIKOLAOY", "ANDRAS", "AFREYAGGELOY", "Tdkfo9e3", d, 
				35.643543, 27.5324534, "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "1234567890123456", 
				"10/23", "334");
		
		Assert.assertNull(newCust);
	}

	@Test
	public void testPersistAnInvCustomer_nullPasswd(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Customer newCust = service.registerCustomer("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", null, d, 
				35.643543, 27.5324534, "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "1234567890123456", 
				"10/23", "334");
		
		Assert.assertNull(newCust);
	}

	@Test
	public void testPersistAnInvCustomer_smallPasswd(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Customer newCust = service.registerCustomer("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", "Tdk9e3", d, 
				35.643543, 27.5324534, "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "1234567890123456", 
				"10/23", "334");
		
		Assert.assertNull(newCust);
	}

	@Test
	public void testPersistAnInvCustomer_noUpperPasswd(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Customer newCust = service.registerCustomer("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", "sdkfo9e3", d, 
				35.643543, 27.5324534, "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "1234567890123456", 
				"10/23", "334");
		
		Assert.assertNull(newCust);
	}

	@Test
	public void testPersistAnInvCustomer_noNumPasswd(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Customer newCust = service.registerCustomer("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", "Tdkfoaes", d, 
				35.643543, 27.5324534, "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "1234567890123456", 
				"10/23", "334");
		
		Assert.assertNull(newCust);
	}

	@Test
	public void testPersistAnInvCustomer_existingEmail(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Customer newCust = service.registerCustomer("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", "Tdkfo9e3", d, 
				35.643543, 27.5324534, "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "afreyaggeloy@gmail.com", "VISA", "1234567890123456", 
				"10/23", "334");
		
		Assert.assertNull(newCust);
	}

	@Test
	public void testPersistAnInvCustomer_emptyEmail(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Customer newCust = service.registerCustomer("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", "Tdkfo9e3", d, 
				35.643543, 27.5324534, "KOLOPETINITSAS 2", "DRAPETSONA", 23456, " ", "VISA", "1234567890123456", 
				"10/23", "334");
		
		Assert.assertNull(newCust);
	}

	@Test
	public void testPersistAnInvCustomer_noPatternEmail(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Customer newCust = service.registerCustomer("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", "Tdkfo9e3", d, 
				35.643543, 27.5324534, "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmailcom", "VISA", "1234567890123456", 
				"10/23", "334");
		
		Assert.assertNull(newCust);
	}

	@Test
	public void testPersistAnInvCustomer_expiredCrdtCrd(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Customer newCust = service.registerCustomer("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", "Tdkfo9e3", d, 
				35.643543, 27.5324534, "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "1234567890123456", 
				"10/16", "334");
		
		Assert.assertNull(newCust);
	}

	@Test
	public void testPersistAnInvCustomer_dateFormatCrdtCrd(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Customer newCust = service.registerCustomer("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", "Tdkfo9e3", d, 
				35.643543, 27.5324534, "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "1234567890123456", 
				"10/g3", "334");
		
		Assert.assertNull(newCust);
	}

	@Test
	public void testPersistAnInvCustomer_numberFormatCrdtCrd(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Customer newCust = service.registerCustomer("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", "Tdkfo9e3", d, 
				35.643543, 27.5324534, "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "123456790123456", 
				"10/23", "334");
		
		Assert.assertNull(newCust);
	}

	@Test
	public void testPersistAnInvCustomer_numFormLetterCrdtCrd(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Customer newCust = service.registerCustomer("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", "Tdkfo9e3", d, 
				35.643543, 27.5324534, "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "12345s7890123456", 
				"10/23", "334");
		
		Assert.assertNull(newCust);
	}

	@Test
	public void testPersistAnInvCustomer_ccvFormLetterCrdtCrd(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Customer newCust = service.registerCustomer("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", "Tdkfo9e3", d, 
				35.643543, 27.5324534, "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "1234567890123456", 
				"10/23", "3s4");
		
		Assert.assertNull(newCust);
	}

	@Test
	public void testPersistAnInvCustomer_afterDateOfBirth(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/2018");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Customer newCust = service.registerCustomer("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", "Tdkfo9e3", d, 
				35.643543, 27.5324534, "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "1234567890123456", 
				"10/23", "334");
		
		Assert.assertNull(newCust);
	}

	@Test
	public void testPersistAnInvCustomer_noCoordinates(){
		RegistrationService service = new RegistrationService();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		Customer newCust = service.registerCustomer("NIKOS", "NIKOLAOY", "ANDRAS", "NEWUSER", "Tdkfo9e3", d, 
				0, 0, "KOLOPETINITSAS 2", "DRAPETSONA", 23456, "nnikol@gmail.com", "VISA", "1234567890123456", 
				"10/23", "334");
		
		Assert.assertNull(newCust);
	}

	@After
	public void tearDown(){
		em.close();
	}

}
