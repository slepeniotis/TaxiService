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

	public Customer createCustomer(String name, String surname, String sex, String username, String password, Date dateOfBirth, 
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

	public TaxiDriver createTaxiDriver(String name, String surname,String sex, String username, String password, Date dateOfBirth, 
			String address, String city, int zipCode, String email, String creditCardType, String creditCardNumber, 
			String expiryDate, String ccv, Taxi owns){

		TaxiDriver taxidr = new TaxiDriver(name, surname, sex, username, password, dateOfBirth, address, city, zipCode, email, creditCardType, creditCardNumber, expiryDate, ccv, owns);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(taxidr);
		tx.commit();

		return taxidr;			

	}

	public Taxi createTaxi(String carModel, String carType, String licensePlate, Date carModelDate, String location){

		Taxi taxi = new Taxi("dfadad", "fdafda", "347932", "09/2012", "fdafadfaea");



		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(taxi);
		tx.commit();

		return taxi;	

	}

	public Route createRoute(String fromAddress, String toAddress, String fromCity, String toCity, String fromZipCode, String toZipCode) {

		Route route = new Route(fromAddress, toAddress, fromCity, toCity, fromZipCode, toZipCode);


		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(route);
		tx.commit();

		return route;	

	}

	public Request createRequest(Date dateTime, Taxi taxi, Customer customer){

		Request req = new Request(dateTime, taxi, customer);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(req);
		tx.commit();

		return req;	

	}

	public Evaluation createEvaluation(int rating, String comment, Date dateOfEval){
		return new Evaluation();
	}
}
