package taxi.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import taxi.model.*;
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

		query = em.createNativeQuery("ALTER SEQUENCE hibernate_sequence RESTART WITH 1");
		query.executeUpdate();

		//save changes from this transaction to the DB
		tx.commit();

	}

	public static void prepareData() throws ParseException{

		//erasing data from the DB
		eraseData();  

		//creating a date for the scope of our testing
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
		Customer customer = new Customer("makis", "xristodoylopoylos", "gynaika", "mak", "fdkE9skf", d, "location", "dfaggaadfadsfada", "fdaafdfa", 13671, "gnyxteridas@gmail.com", "mastercard", "1234567891234567", "01/19", "123");
		em.persist(customer);
		tx.commit();
		
		tx.begin();
		Customer customer2 = new Customer("mak", "xrist", "gynaika", "mak3", "fdkE9skf", d, "location", "dfaggaadfadsfada", "fdaafdfa", 13671, "slepeniotis@gmail.com", "mastercard", "1234567891234567", "01/19", "123");
		em.persist(customer2);
		tx.commit();
		
		tx.begin();
		//changing the password of customer2 in order to check if it is encrypted correctly
		Customer customer3 = new Customer("mak", "xrist", "gynaika", "mak2", "fdkE9skf", d, "location", "dfaggaadfadsfada", "fdaafdfa", 13671, "slepeniotis@gmail.com", "mastercard", "1234567891234567", "01/19", "123");
		em.persist(customer3);
		customer2.setPassword("fdkE9skfs");
		tx.commit();
			
		tx.begin();
		Evaluation eval = new Evaluation(3, "djhalfhalcdalr", d);
		em.persist(eval);
		
		Taxi taxi = new Taxi("dfadad", "fdafda", "347932", d, "fdafadfaea");
		em.persist(taxi);
		
		Request req = new Request(d, taxi, customer);
		em.persist(req);
		
		Request req2 = new Request(d, taxi, customer2);
		em.persist(req2);
		
		Request req3 = new Request(d, taxi, customer3);
		em.persist(req3);		
		
		TaxiDriver taxidr = new TaxiDriver("makis", "xristodoylopoylos", "gynaika", "mak", "fdkE9skf", d, "dfaggaadfadsfada", "fdaafdfa", 13671, "vlabrakakis@aueb.gr", "mastercard", "1234567891234567", "01/45", "123", taxi);
		em.persist(taxidr);
		
		Route route = new Route("from", "to");
		em.persist(route);

		/*Category cat = new Category();
		 * Category cat2 = new Category();
		 * cat.setDescription("Drama");
		 * cat2.setDescription("Science Fiction");
		 */
					
		//the transaction is now committed
		tx.commit();
			
		
		//we are now preparing a query in order to see that the data are correctly inserted in the DB
		Query query = em.createQuery("select cust from Customer cust");
		//the result of the query is inserted in a list of results
		List<Customer> results = query.getResultList();

		//loop inside the list of results and run the method toString() of the class, in which the result of the query belongs
		//in this case, the result list is of type Customer
		for(Customer c : results) {
			System.out.println(c.toString());
		}
		
		em.close();

	}

	public static void main (String [] args) {
		try {
			prepareData();
		}
		catch (ParseException e){
			System.out.println("poytana date");
		}
	}
}
