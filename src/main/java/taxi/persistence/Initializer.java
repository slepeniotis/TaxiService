package taxi.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.Date;
import taxi.model.*;
import taxi.utils.AESEncrypt;
import taxi.utils.RequestStatus;
import taxi.utils.Validators;

import java.text.*;


public class Initializer  {


	/**
	 * Remove all data from database.
	 * The functionality must be executed within the bounds of a transaction
	 */
	public static void eraseData() {
		EntityManager em = JPAUtil.getCurrentEntityManager();

		//starting a new transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Query query = null;

		//creating and executing several queries within the same transaction
		query = em.createNativeQuery("delete from evaluation");
		//execute the query given above
		query.executeUpdate();

		query = em.createNativeQuery("delete from route");
		query.executeUpdate();

		query = em.createNativeQuery("delete from customer");
		query.executeUpdate();

		query = em.createNativeQuery("delete from taxi");
		query.executeUpdate();

		query = em.createNativeQuery("delete from taxidriver");
		query.executeUpdate();

		query = em.createNativeQuery("delete from customer");
		query.executeUpdate();

		query = em.createNativeQuery("delete from request");
		query.executeUpdate();

		query = em.createNativeQuery("ALTER SEQUENCE hibernate_sequence RESTART WITH 1");
		query.executeUpdate();

		//save changes from this transaction to the DB
		tx.commit();

	}

	public static void prepareData() throws ParseException{

		//erasing data from the DB
		eraseData();  

		//creating default data

		//start inserting the data (objects) that we have created previously, in the DB
		//the way we are going to insert them is within a transaction
		EntityManager em = JPAUtil.getCurrentEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		//some objects, will need others to exist already

		//1st customer
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}

		//encrypt password
		String passwd = "fdkE9skf";
		if (Validators.validatePassword(passwd)) {
			try {
				passwd = AESEncrypt.encrypt(passwd); 
			}
			catch (Exception e){
				//in case an exception occurs, the password is not changed
				System.out.println(e.getStackTrace());
			}
		}

		//coordinates for PEIRAIAS
		Customer c1 = new Customer("SPYROS", "LEPENIOTIS", "ANDRAS", "SLEPENIOTIS", passwd, d, 37.9508344, 23.6510941, "AGIOY NIKOLAOY 1", "PEIRAIAS", 13671, "slepen@gmail.com", "MASTERCARD", "1234567891234567", "01/19", "123");

		//2nd customer
		try {
			d = sdf.parse("10/1/1985");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}

		//encrypt password
		passwd = "fdkE9skF";
		if (Validators.validatePassword(passwd)) {
			try {
				passwd = AESEncrypt.encrypt(passwd); 
			}
			catch (Exception e){
				//in case an exception occurs, the password is not changed
				System.out.println(e.getStackTrace());
			}
		}

		//coordinates for PAIANIA
		Customer c2 = new Customer("BASILIS", "LAMPRAKAKIS", "ANDRAS", "VLAMPRAKAKIS", passwd, d, 37.95929, 23.8397867, "LORDOY BYRONA 15", "PAIANIA", 85300, "vlamprakakis@gmail.com", "MASTERCARD", "1234567898254567", "08/20", "569");

		//3rd customer
		try {
			d = sdf.parse("15/3/1991");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}

		//encrypt password
		passwd = "fdDE9skF";
		if (Validators.validatePassword(passwd)) {
			try {
				passwd = AESEncrypt.encrypt(passwd); 
			}
			catch (Exception e){
				//in case an exception occurs, the password is not changed
				System.out.println(e.getStackTrace());
			}
		}

		//coordinates for ACHARNES
		Customer c3 = new Customer("AFROKSILANTHI", "EYAGGELOY", "GYNAIKA", "AFREYAGGELOY", passwd, d, 38.0983803, 23.7243561, "NATASAS KARAMANLI 22", "ACHARNES", 66666, "afreyaggeloy@gmail.com", "VISA", "1223555789802545", "10/21", "724");

		//4th customer
		try {
			d = sdf.parse("12/12/1986");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}

		//encrypt password
		passwd = "5dDE9skF";
		if (Validators.validatePassword(passwd)) {
			try {
				passwd = AESEncrypt.encrypt(passwd); 
			}
			catch (Exception e){
				//in case an exception occurs, the password is not changed
				System.out.println(e.getStackTrace());
			}
		}

		//coordinates for PERISTERI
		Customer c4 = new Customer("GIORGOS", "KAVVADIAS", "ANDRAS", "GKAVVADIAS", passwd, d, 38.0155628, 23.6750659, "THEOTOKOPOYLOY 15", "PERISTERI", 54634, "gkavvadias@gmail.com", "VISA", "1223585989822545", "11/21", "984");

		em.persist(c1);
		em.persist(c2);
		em.persist(c3);
		em.persist(c4);
		tx.commit();		

		//taxi & taxi driver objects

		//1st pair
		try {
			d = sdf.parse("12/11/1970");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}

		//encrypt password
		passwd = "fd8E9skf";
		if (Validators.validatePassword(passwd)) {
			try {
				passwd = AESEncrypt.encrypt(passwd); 
			}
			catch (Exception e){
				//in case an exception occurs, the password is not changed
				System.out.println(e.getStackTrace());
			}
		}

		//coordinates for AGIA PARASKEYI
		tx.begin();
		Taxi tx1 = new Taxi("TOYOTA COROLLA", "SEDAN", "AHX0987", "09/2010", 38.0093272, 23.8176902);
		TaxiDriver txdr1 = new TaxiDriver("MAKIS", "XRISTODOYLOPOYLOS", "ANDRAS", "MAKXRIS", passwd, d, "SKYLADIKOY 1", "ETHNIKI ODOS", 11243, "makxris@aueb.gr", "MASTERCARD", "1223585989822541", "01/18", "321", tx1);
		em.persist(txdr1);
		tx.commit();

		//2nd pair
		try {
			d = sdf.parse("11/08/1979");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}

		//encrypt password
		passwd = "a228E9skf";
		if (Validators.validatePassword(passwd)) {
			try {
				passwd = AESEncrypt.encrypt(passwd); 
			}
			catch (Exception e){
				//in case an exception occurs, the password is not changed
				System.out.println(e.getStackTrace());
			}
		}

		//coordinates for PETROYPOLI
		tx.begin();
		Taxi tx2 = new Taxi("MERCENDES CL200", "SEDAN", "AKX8987", "02/2015", 38.0406218, 23.6728584);
		TaxiDriver txdr2 = new TaxiDriver("STAMATIS", "GONIDIS", "ANDRAS", "STGONIDIS", passwd, d, "SKYLADIKOY 2", "ETHNIKI ODOS", 15459, "stgonidis@aueb.gr", "VISA", "1223584638225412", "11/19", "331", tx2);
		em.persist(txdr2);
		tx.commit();

		//3rd pair
		try {
			d = sdf.parse("26/04/1982");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}

		//encrypt password
		passwd = "21Gd8E9skf";
		if (Validators.validatePassword(passwd)) {
			try {
				passwd = AESEncrypt.encrypt(passwd); 
			}
			catch (Exception e){
				//in case an exception occurs, the password is not changed
				System.out.println(e.getStackTrace());
			}
		}

		//coordinates for AIGALEO
		tx.begin();
		Taxi tx3 = new Taxi("SKODA OCTAVIA", "SEDAN", "AIK2976", "10/2016", 37.9924989, 23.6640205);
		TaxiDriver txdr3 = new TaxiDriver("KOSTAS", "KAFASIS", "ANDRAS", "KKAFASIS", passwd, d, "SKYLADIKOY 3", "ETHNIKI ODOS", 35612, "kkafasis@aueb.gr", "MASTERCARD", "1241619569971231", "07/19", "442", tx3);
		em.persist(txdr3);
		tx.commit();

		//request objects

		tx.begin();

		//1st request
		try {
			d = sdf.parse("18/12/2016");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		Request rq1 = new Request(d, tx1, c1);
		em.persist(rq1);

		//2nd request
		try {
			d = sdf.parse("25/02/2017");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		Request rq2 = new Request(d, tx1, c2);
		em.persist(rq2);

		//3rd request
		try {
			d = sdf.parse("02/01/2017");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		Request rq3 = new Request(d, tx2, c3);
		em.persist(rq3);

		//4th request
		try {
			d = sdf.parse("09/04/2016");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		Request rq4 = new Request(d, tx2, c4);
		em.persist(rq4);

		//5th request
		try {
			d = sdf.parse("15/08/2016");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		Request rq5 = new Request(d, tx3, c1);
		em.persist(rq5);

		//6th request
		try {
			d = sdf.parse("17/11/2016");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		Request rq6 = new Request(d, tx3, c2);
		em.persist(rq6);

		//7th request
		try {
			d = sdf.parse("06/02/2017");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		Request rq7 = new Request(d, tx3, c1);
		em.persist(rq7);

		//8th request
		try {
			d = sdf.parse("31/01/2017");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		Request rq8 = new Request(d, tx1, c2);
		em.persist(rq8);
		tx.commit();

		//route objects

		tx.begin();

		//1st route
		Route rt1 = new Route("KOLOKOTRONI 42", "PATISION 136", "ACHARNES", "ATHINA", "13671", "12345", rq1);

		//2nd route
		Route rt2 = new Route("LIMNOU 79", "AGIOY NIKOLAOY 1", "AIGALEO", "KORIDALLOS", "18971", "13345", rq2);

		//3rd route
		Route rt3 = new Route("PALAION PATRON GERMANOY 42", "SKOYZE 136", "ILION", "AGIOS DIMITRIOS", "11671", "12385", rq3);

		//4th route
		Route rt4 = new Route("SKA 2", "MARNIS 16", "NIKAIA", "ATHINA", "65945", "12345", rq6);

		//5th route
		Route rt5 = new Route("AFIDNAION 13", "VELISSARIOY 10", "KERATSINI", "KERAMEIKOS", "18756", "12567", rq8);

		//6th route
		Route rt6 = new Route("GYZI 8", "THEOTOKOPOYLOY 15", "PEIRAIAS", "MAROYSI", "23661", "16987", rq4);

		em.persist(rt1);
		em.persist(rt2);
		em.persist(rt3);
		em.persist(rt4);
		em.persist(rt5);
		em.persist(rt6);

		//connect requests with routes
		rq1.setRoute(rt1);
		rq2.setRoute(rt2);
		rq3.setRoute(rt3);
		rq6.setRoute(rt4);
		rq8.setRoute(rt5);
		rq4.setRoute(rt6);		
		tx.commit();

		//change status of taxi and request according to previews objects
		tx.begin();
		rq1.setStatus(RequestStatus.ONGOING);
		rq2.setStatus(RequestStatus.ONGOING);
		rq3.setStatus(RequestStatus.ONGOING);
		rq6.setStatus(RequestStatus.ONGOING);
		rq8.setStatus(RequestStatus.ONGOING);
		rq4.setStatus(RequestStatus.ONGOING);

		rq1.getTaxi().setStatus(false);
		rq2.getTaxi().setStatus(false);
		rq3.getTaxi().setStatus(false);
		rq6.getTaxi().setStatus(false);
		rq8.getTaxi().setStatus(false);
		rq4.getTaxi().setStatus(false);
		tx.commit();

		//end requests and update taxi's and request's status
		tx.begin();

		//route 1
		rt1.setCost(60);
		rt1.calculateCommision();
		rt1.setDuration(60);
		rt1.getReq().endRequest();

		//route 2
		rt2.setCost(15);
		rt2.calculateCommision();
		rt2.setDuration(30);
		rt2.getReq().endRequest();

		//route 3
		rt3.setCost(5.5f);
		rt3.calculateCommision();
		rt3.setDuration(15);
		rt3.getReq().endRequest();

		//route 4
		rt4.setCost(21.5f);
		rt4.calculateCommision();
		rt4.setDuration(30);
		rt4.getReq().endRequest();
		rt4.getReq().getTaxi().setStatus(true);

		tx.commit();

		//create evaluation		
		tx.begin();

		//1st evaluation
		try {
			d = sdf.parse("26/02/2017");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}		
		Evaluation eval1 = new Evaluation(3, "everything was good", d);
		em.persist(eval1);

		//2nd evaluation
		try {
			d = sdf.parse("20/11/2016");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		Evaluation eval2 = new Evaluation(0, "the scariest trip of my life", d);
		em.persist(eval2);

		rt2.setEval(eval1);
		rt4.setEval(eval2);
		tx.commit();

		System.out.println("Initialization completed!");

		//creating a date for the scope of our testing
		/*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d;
		try {
			d = sdf.parse("21/12/2012");			
		}
		catch (ParseException e){
			d = sdf.parse("21/12/2012");
		}

		//start inserting the data (objects) that we have created previously, in the DB
		//the way we are going to insert them is within a transaction
		EntityManager em = JPAUtil.getCurrentEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		//creating some objects of our model
		//several objects of them, will need others to exist already
		Customer customer = new Customer("makis", "xristodoylopoylos", "gynaika", "mak", "fdkE9skf", d, 37.9909517, 23.6682987, "dfaggaadfadsfada", "fdaafdfa", 13671, "gnyxteridas@gmail.com", "mastercard", "1234567891234567", "01/19", "123");
		//in case an error during validation took place, we need to rollback the transaction
		if (customer.getUsername() != "ERROR"){
			em.persist(customer);
			tx.commit();
		}
		else{
			tx.rollback();
		}

		//each customer is added in separate transactions. this is done due to validation reasons.
		//if we had all the customers to be added at once, it would be possible to have records with same username/email
		tx.begin();
		Customer customer2 = new Customer("mak", "xrist", "gynaika", "mak3", "fdkE9skf", d, 37.9868448, 23.6144057, "dfaggaadfadsfada", "fdaafdfa", 13671, "slepeniotis@gmail.com", "mastercard", "1234567891234567", "01/19", "123");
		//in case an error during validation took place, we need to rollback the transaction
		if (customer2.getUsername() != "ERROR"){			
			em.persist(customer2);
			tx.commit();
		}
		else{
			tx.rollback();
		}

		tx.begin();
		//changing the password of customer2 in order to check if it is encrypted correctly
		Customer customer3 = new Customer("mak", "xrist", "gynaika", "mak", "fdkE9skf", d, 38.098505, 23.6192865, "dfaggaadfadsfada", "fdaafdfa", 13671, "slepeniotis@gmail.com", "mastercard", "1234567891234567", "01/19", "1f3");
		//in case an error during validation took place, we need to rollback the transaction
		if (customer3.getUsername() != "ERROR"){
			em.persist(customer3);
			customer2.setPassword("fdkE9skfs");
			tx.commit();
		}
		else{
			tx.rollback();
		}

		tx.begin();
		Evaluation eval = new Evaluation(3, "djhalfhalcdalr", d);
		em.persist(eval);
		tx.commit();

		tx.begin();
		Taxi taxi = new Taxi("dfadad", "fdafda", "AHX0987", "09/2010", 37.9925288, 23.6465103);
		//in case an error during validation took place, we need to rollback the transaction
		if (taxi.getLicensePlate() != "ERROR"){
			em.persist(taxi);
			tx.commit();
		}
		else{
			tx.rollback();
		}

		tx.begin();
		Request req = new Request(d, taxi, customer);
		em.persist(req);

		Request req2 = new Request(d, taxi, customer2);
		em.persist(req2);

		//Request req3 = new Request(d, taxi, customer3);
		//em.persist(req3);		
		tx.commit();

		tx.begin();
		TaxiDriver taxidr = new TaxiDriver("makis", "xristodoylopoylos", "gynaika", "mak", "fdkE9skf", d, "dfaggaadfadsfada", "fdaafdfa", 13671, "vlabrakakis@aueb.gr", "mastercard", "1234567891234567", "01/45", "123", taxi);
		//in case an error during validation took place, we need to rollback the transaction
		if (taxidr.getUsername() != "ERROR"){
			em.persist(taxidr);
			tx.commit();
		}
		else{
			tx.rollback();
		}

		tx.begin();
		Route route = new Route("Kolokotroni 42", "Patision 136", "Acharnes", "Athense", "13671", "12345", req);
		em.persist(route);
		Route route1 = new Route("Kolokotroni 42", "Patision 136", "Acharnes", "Athense", "13671", "12345", req2);
		em.persist(route1);
		req.setRoute(route);
		req2.setRoute(route1);
		tx.commit();

		tx.begin();
		route.setCost(12041);
		route.calculateCommision();
		route1.setCost(123);
		route1.calculateCommision();
		tx.commit();

		tx.begin();
		route.setEval(eval);
		tx.commit();


		//we are now preparing a query in order to see that the data are correctly inserted in the Customer table.
		//we could do this for all other tables
		Query query = em.createQuery("select cust from Customer cust");
		//the result of the query is inserted in a list of results
		List<Customer> results = query.getResultList();

		//loop inside the list of results and run the method toString() of the class, in which the result of the query belongs
		//in this case, the result list is of type Customer
		for(Customer c : results) {
			System.out.println(c.toString());
		}*/

	}
}
