package taxi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import taxi.model.Customer;
import taxi.model.Taxi;
import taxi.model.TaxiDriver;
import taxi.persistence.Initializer;
import taxi.persistence.JPAUtil;

public class RegistrationServiceTest extends TaxiServiceTest{

	//Tests for Taxi
	
	//register a valid taxi
	@Test
	public void testPersistAValidTaxi(){
		RegistrationService service = new RegistrationService();

		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "11/2013", 35.2341223, 27.234325);
		Assert.assertNotEquals(0, newTaxi.getId());
	}

	//invalid taxi registration. empty license plate
	@Test
	public void testPersistAnInvalidTaxi(){
		RegistrationService service = new RegistrationService();

		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", " ", "11/2013", 35.2341223, 27.234325);
		Assert.assertNull(newTaxi);

	}
	
	//invalid taxi registration. null license plate
	@Test
	public void testPersistAnInvalidTaxi_nullLicense(){
		RegistrationService service = new RegistrationService();

		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", null, "11/2013", 35.2341223, 27.234325);
		Assert.assertNull(newTaxi);

	}
	
	//invalid taxi registration. null car model
	@Test
	public void testPersistAnInvalidTaxi_nullModel(){
		RegistrationService service = new RegistrationService();

		Taxi newTaxi = service.createTaxi(null, "SPORT", "IHX0989", "11/2013", 35.2341223, 27.234325);
		Assert.assertNull(newTaxi);

	}
	
	//invalid taxi registration. null car type
	@Test
	public void testPersistAnInvalidTaxi_nullType(){
		RegistrationService service = new RegistrationService();

		Taxi newTaxi = service.createTaxi("SKODA FABIA", null, "IHX0989", "11/2013", 35.2341223, 27.234325);
		Assert.assertNull(newTaxi);

	}
	
	//invalid taxi registration. null date
	@Test
	public void testPersistAnInvalidTaxi_nullDate(){
		RegistrationService service = new RegistrationService();

		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", null, 35.2341223, 27.234325);
		Assert.assertNull(newTaxi);

	}
	
	//invalid taxi registration. empty car model
	@Test
	public void testPersistAnInvalidTaxi_emptyModel(){
		RegistrationService service = new RegistrationService();

		Taxi newTaxi = service.createTaxi(" ", "SPORT", "IHX0989", "11/2013", 35.2341223, 27.234325);
		Assert.assertNull(newTaxi);

	}
	
	//invalid taxi registration. empty car type
	@Test
	public void testPersistAnInvalidTaxi_emptyType(){
		RegistrationService service = new RegistrationService();

		Taxi newTaxi = service.createTaxi("SKODA FABIA", " ", "IHX0989", "11/2013", 35.2341223, 27.234325);
		Assert.assertNull(newTaxi);

	}
	
	//invalid taxi registration. empty date
	@Test
	public void testPersistAnInvalidTaxi_emptyDate(){
		RegistrationService service = new RegistrationService();

		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", " ", 35.2341223, 27.234325);
		Assert.assertNull(newTaxi);

	}	

	//invalid taxi registration. existing license plate
	@Test
	public void testPersistAnInvalidTaxi_exLicence(){
		RegistrationService service = new RegistrationService();

		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "AHX0987", "11/2013", 35.2341223, 27.234325);
		Assert.assertNull(newTaxi);
	}

	//invalid taxi registration. invalid license plate format
	@Test
	public void testPersistAnInvalidTaxi_invLicence(){
		RegistrationService service = new RegistrationService();

		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "AH00987", "11/2013", 35.2341223, 27.234325);
		Assert.assertNull(newTaxi);
	}

	//invalid taxi registration. carModelDate is after the current date
	@Test
	public void testPersistAnInvTaxi_invDateAfter(){
		RegistrationService service = new RegistrationService();

		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "11/2017", 35.2341223, 27.234325);
		Assert.assertNull(newTaxi);
	}

	//invalid taxi registration. invalid date has letter
	@Test
	public void testPersistAnInvTaxi_invDateFormatLetter(){
		RegistrationService service = new RegistrationService();

		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "1f/2017", 35.2341223, 27.234325);
		Assert.assertNull(newTaxi);
	}

	//invalid taxi registration. invalid date. month does not exist
	@Test
	public void testPersistAnInvTaxi_invDateFormatMonthNum(){
		RegistrationService service = new RegistrationService();

		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "13/2017", 35.2341223, 27.234325);
		Assert.assertNull(newTaxi);
	}

	//invalid taxi registration. 0 coordinates
	@Test
	public void testPersistAnInvTaxi_noCoordinates(){
		RegistrationService service = new RegistrationService();

		Taxi newTaxi = service.createTaxi("SKODA FABIA", "SPORT", "IHX0989", "11/2013", 0, 0);
		Assert.assertNull(newTaxi);
	}

	//Tests for Taxi Driver
	
	//create a valid taxi driver
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

	//invalid taxi driver registration. null taxi
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

	//invalid taxi driver registration. invalid taxi driver info
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

	//invalid taxi driver registration. taxi already connected to other taxi driver
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

	//invalid taxi driver registration. existing username
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

	//invalid taxi driver registration. null password
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

	//invalid taxi driver registration. small password
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

	//invalid taxi driver registration. no upper case in password
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
	
	//invalid taxi driver registration. no numbers in password
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

	//invalid taxi driver registration. existing email
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

	//invalid taxi driver registration. empty email
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

	//invalid taxi driver registration. email format error
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

	//invalid taxi driver registration. credit card expired
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

	//invalid taxi driver registration. credit card date format error
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

	//invalid taxi driver registration. credit card number format error
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

	//invalid taxi driver registration. credit card number contains letter
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

	//invalid taxi driver registration. credit card ccv contains letter
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

	//invalid taxi driver registration. date of birth after current date
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
	
	//valid customer registration
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

	//Invalid customer registration. customer's info is invalid
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

	//Invalid customer registration. existing username
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

	//Invalid customer registration. null password
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

	//Invalid customer registration. small password
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

	//Invalid customer registration. no upper case letter in password
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

	//Invalid customer registration. no number in password
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

	//Invalid customer registration. existing email
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

	//Invalid customer registration. empty email
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

	//Invalid customer registration. email format error
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

	//Invalid customer registration. credit card expired
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

	//Invalid customer registration. credit card date format error
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

	//Invalid customer registration. credit card number format error
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

	//Invalid customer registration. credit card number contains letter
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

	//Invalid customer registration. credit card ccv contains letter
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

	//Invalid customer registration. date of birth is after current date
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

	//Invalid customer registration. 0 coordinates
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

}
