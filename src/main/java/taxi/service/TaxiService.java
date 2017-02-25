package taxi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import gr.aueb.mscis.sample.model.Movie;
import taxi.model.*;
import taxi.persistence.JPAUtil;

public class TaxiService {

	
	EntityManager em;
	
	public TaxiService() {
		em = JPAUtil.getCurrentEntityManager();
	}
	
	public Customer createCustomer(String name, String surname, String sex, String username, String password, Date dateOfBirth, 
			String location, String address, String city, int zipCode, String creditCardType, String creditCardNumber, 
			String expiryDate, String ccv){
		
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date d;
			try {
				d = sdf.parse("21/12/2012");
			}
			catch (ParseException e){
				System.out.println("Η ημερομηνία δεν είναι σωστή");
			}
		 
			Customer customer = new Customer("makis", "xristodoylopoylos", "gynaika", "mak", "mak", d, "location", "dfaggaadfadsfada", "fdaafdfa", 13671, "mastercard", "1234567891234567", "01/19", "123");
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(customer);
		tx.commit();
		
		return customer;
		
	}
	
	public TaxiDriver createTaxiDriver(String name, String surname,String sex, String username, String password, Date dateOfBirth, 
			String address, String city, int zipCode, String email, String creditCardType, String creditCardNumber, 
			String expiryDate, String ccv, Taxi owns){
			
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date d;
				try {
					d = sdf.parse("21/12/2012");
				}
				catch (ParseException e){
					System.out.println("Η ημερομηνία δεν είναι σωστή");
				}
			 
			TaxiDriver taxidr = new TaxiDriver("makis", "xristodoylopoylos", "gynaika", "mak", "fdkE9skf", d, "dfaggaadfadsfada", "fdaafdfa", 13671, "vlabrakakis@aueb.gr", "mastercard", "1234567891234567", "01/45", "123", owns);
				
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(taxidr);
			tx.commit();
			
			return taxidr;			
		
	}
	
	public Taxi createTaxi(String carModel, String carType, String licensePlate, Date carModelDate, String location)  throws ParseException{
			
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date d;
				try {
					d = sdf.parse("21/12/2012");
				}
				catch (ParseException e) {
					d = sdf.parse("21/12/2012");
				}
			 
			Taxi taxi = new Taxi("dfadad", "fdafda", "347932", d, "fdafadfaea");
			
			
			
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(taxi);
			tx.commit();
			
			return taxi;	
		
	}

	public Route createRoute(String from, String to){
		
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date d;
			try {
				d = sdf.parse("21/12/2012");
			}
			catch (ParseException e){
				System.out.println("Η ημερομηνία δεν είναι σωστή");
			}
		 
			Route route = new Route("from", "to");
		
		
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(route);
		tx.commit();
		
		return route;	
	
}

	public Request createRequest(Date dateTime, Taxi taxi, Customer customer){
		
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date d;
			try {
				d = sdf.parse("21/12/2012");
			}
			catch (ParseException e){
				System.out.println("Η ημερομηνία δεν είναι σωστή");
			}
		 
			Request req = new Request(d, taxi, customer);
		
		
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(req);
		tx.commit();
		
		return req;	
	
	}
	
}
