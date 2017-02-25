package taxi.service;

import java.text.ParseException;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import taxi.model.*;
import taxi.persistence.JPAUtil;

public class TaxiService {

	EntityManager em;

	public TaxiService() {
		em = JPAUtil.getCurrentEntityManager();
	}

	//ΕΓΓΡΑΦΗ
	public Taxi createTaxi(String carModel, String carType, String licensePlate, String carModelDate, String location){

		if(carModel == null || carType == null || licensePlate == null || carModelDate == null || location == null)
			return null;
		
		Taxi taxi = new Taxi(carModel, carType, licensePlate, carModelDate, location);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(taxi);
		tx.commit();

		return taxi;
	}
	
	public TaxiDriver registerTaxiDriver(String name, String surname,String sex, String username, String password, Date dateOfBirth, 
			String address, String city, int zipCode, String email, String creditCardType, String creditCardNumber, 
			String expiryDate, String ccv, Taxi owns){
		
		TaxiDriver taxidr = new TaxiDriver(name, surname, sex, username, password, dateOfBirth, address, city, zipCode, email, creditCardType, creditCardNumber, expiryDate, ccv, owns);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(taxidr);
		tx.commit();

		return taxidr;			

	}
	
	public Customer registerCustomer(String name, String surname, String sex, String username, String password, Date dateOfBirth, 
			String location, String address, String city, int zipCode, String email, String creditCardType, String creditCardNumber, 
			String expiryDate, String ccv){
		
		Customer customer = new Customer(name, surname, sex, username, password, dateOfBirth, 
				location, address, city, zipCode, email, creditCardType, creditCardNumber, 
				expiryDate, ccv);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(customer);
		tx.commit();

		return customer;

	}
	
	//ΤΑΥΤΟΠΟΙΗΣΗ
	public boolean login(String userType, String username, String password){}
	
	
	//ΕΚΤΕΛΕΣΗ ΔΙΑΔΡΟΜΗΣ
	public Request startRequest(Date dateTime, Taxi taxi, Customer customer){

		Request req = new Request(dateTime, taxi, customer);
		//call informtaxidriver()	

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(req);
		tx.commit();

		return req;	

	}

	public boolean handleRequest(Request req, Taxi taxi, String decision){
		
		//if positive, update status request, inform customer
	}
	
	public Route createRoute(Request req, String fromAddress, String toAddress, String fromCity, String toCity, String fromZipCode, String toZipCode) {

		//connect route with request

		Route route = new Route(fromAddress, toAddress, fromCity, toCity, fromZipCode, toZipCode);


		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(route);
		tx.commit();

		return route;	

	}

	public boolean stopRequest(Request req, Taxi taxi){

		//update status (flag 1|0)
		//call method for changing taxi status
		//inform customer(cost)
		//inform customer(evaluation)

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(req);
		tx.commit();

		return true;	

	}

	//ΑΞΙΟΛΟΓΗΣΗ
	public Evaluation createEvaluation(Route route, int rating, String comment, Date dateOfEval){
		
		//inform taxidriver(evaluation)
		return new Evaluation();
	}
	
	//ΣΤΑΤΙΣΤΙΚΑ
	public String produceStatistics(int selection, Date fromRange, Date toRange){}
	
	public String produceStatistics(int selection, String city){}
	
}
