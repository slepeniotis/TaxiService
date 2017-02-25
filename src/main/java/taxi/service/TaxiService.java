package taxi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.Assert;

import gr.aueb.mscis.sample.model.Movie;
import taxi.model.*;
import taxi.persistence.JPAUtil;
import taxi.utils.AESEncrypt;
import taxi.utils.RequestState;

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

		if(name == null || surname == null || sex == null || username == null 
				|| password == null || dateOfBirth == null || address == null || 
				city == null || zipCode == 0 || email == null || creditCardType == null
				|| creditCardNumber == null || expiryDate == null || ccv == null || owns == null)
			return null;

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

		if(name == null || surname == null || sex == null || username == null 
				|| password == null || dateOfBirth == null || address == null || 
				city == null || zipCode == 0 || email == null || creditCardType == null
				|| creditCardNumber == null || expiryDate == null || ccv == null || location == null)
			return null;

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
	public boolean login(String userType, String username, String password){
		boolean result = false;

		if(userType == null || username == null || password == null)
			return result;

		if(userType == "customer"){
			String pasEncr;
			Query query = em.createQuery("select cust from Customer cust where username like :usrnm AND password like :passwd");
			query.setParameter("usrnm", username);
			try{
				pasEncr = AESEncrypt.encrypt(password);
			}
			catch(Exception e){
				return result;
			}

			query.setParameter("passwd", pasEncr);
			List<Customer> results = query.getResultList();

			if(results.isEmpty()){
				return result;
			}

			result = true;
		}
		else if(userType == "taxidriver"){
			String pasEncr;
			Query query = em.createQuery("select taxidr from taxidriver taxidr where username like :usrnm AND password like :passwd");
			query.setParameter("usrnm", username);
			try{
				pasEncr = AESEncrypt.encrypt(password);
			}
			catch(Exception e){
				return result;
			}

			query.setParameter("passwd", pasEncr);
			List<TaxiDriver> results = query.getResultList();

			if(results.isEmpty()){
				return result;
			}

			result = true;

		}	

		return result;
	}


	//ΕΚΤΕΛΕΣΗ ΔΙΑΔΡΟΜΗΣ
	public Request startRequest(Date dateTime, Taxi taxi, Customer customer){

		if(dateTime == null || taxi == null || customer == null)
			return null;

		Request req = new Request(dateTime, taxi, customer);
		Query query = em.createQuery("select taxidr from taxidriver taxidr where taxi = :taxiid");
		query.setParameter("taxiid", taxi.getId());
		TaxiDriver txdrrslt = (TaxiDriver)query.getSingleResult();

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(req);
		tx.commit();

		txdrrslt.informTaxiDriver("New Request from " + customer.getName() + "!");

		return req;	

	}

	public boolean handleRequest(Request req, Taxi taxi, String decision){
		if(req == null || taxi == null || decision == null)
			return false;

		boolean result = false;

		if(decision == "yes"){
			em = JPAUtil.getCurrentEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();			
			req.setStatus(RequestState.ONGOING);
			taxi.setStatus(false);
			tx.commit();
			em.close();		

			req.getCustomer().informCustomer("Request accepted from Taxi " + taxi.getId());

			result = true;
		}
		else if(decision == "no"){
			req.getCustomer().informCustomer("Request denied from Taxi " + taxi.getId());

			result = false;
		}

		return result;
	}

	public Route createRoute(Request req, String fromAddress, String toAddress, String fromCity, String toCity, String fromZipCode, String toZipCode) {

		if(req == null || fromAddress == null || toAddress == null || fromCity == null || toCity == null || fromZipCode == null || toZipCode == null)
			return null;

		Route route = new Route(fromAddress, toAddress, fromCity, toCity, fromZipCode, toZipCode);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(route);
		req.setRoute(route);
		tx.commit();

		return route;	

	}

	public boolean stopRequest(Request req, Taxi taxi, float cost){

		if(req == null || taxi == null)
			return false;

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//call method for changing taxi status
		taxi.setStatus(true);
		//update status
		req.setStatus(RequestState.DONE);
		//update cost and commision
		req.getRoute().setCost(cost);
		req.getRoute().calculateCommision();
		tx.commit();		

		req.getCustomer().informCustomer("The final cost of request: " + req.getId() + " was: " + req.getRoute().getCost());
		req.getCustomer().informCustomer("Please take a minute to evaluate our service");

		return true;	

	}

	//ΑΞΙΟΛΟΓΗΣΗ
	public Evaluation createEvaluation(Route route, int rating, String comment, Date dateOfEval){
		if(route == null || comment == null || dateOfEval == null)
			return null;

		Evaluation eval = new Evaluation(rating, comment, dateOfEval);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(eval);
		route.setEval(eval);
		tx.commit();

		Query query = em.createQuery("select req from request req where routeid = :rtid");
		query.setParameter("rtid", route.getId());
		Request reqrslt = (Request)query.getSingleResult();

		Taxi taxeval = reqrslt.getTaxi();

		query = em.createQuery("select taxidr from taxidriver taxidr where taxiid = :txid");
		query.setParameter("txid", taxeval.getId());
		TaxiDriver taxdrrslt = (TaxiDriver)query.getSingleResult();

		//inform taxidriver(evaluation)
		taxdrrslt.informTaxiDriver("Customer " + reqrslt.getCustomer().getName() + " submitted an evaluation for request: " + reqrslt.getId());

		return eval;
	}

	//ΣΤΑΤΙΣΤΙΚΑ
	public float produceStatistics(int selection, Date fromRange, Date toRange){
		if(selection == 0 || fromRange == null || toRange == null)
			return 0;
		float sum = 0;
		if(selection == 1){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date d;

			Query query = em.createQuery("select r from Request r where r.dateTime between :frRange and :tRange");
			try {
				d = sdf.parse("1/12/2012");
				query.setParameter("frRange", d);
				d = sdf.parse("31/12/2012");
				query.setParameter("tRange", d);
			}
			catch (ParseException e){
				System.out.println(e.getStackTrace());
			}		

			List<Request> reqrslt = query.getResultList();		
			for(Request r : reqrslt) {
				sum += r.getRoute().getCommision();
			}
		}
		return sum;

	}

	public int produceStatistics(int selection, String city){
		if(selection == 0 || city == null)
			return 0;
		
		int sum =0;
		
		if(selection == 2){			
			
			Query query = em.createQuery("select r from Request r, Route rt where r.Route.id = rt.id AND rt.fromCity LIKE :ct");
			query.setParameter("ct", "Acharnes");
			List<Request> reqrslt = query.getResultList();		
			for(Request r : reqrslt) {
				System.out.println(r.toString());
			}
		}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		else if(selection == 3){
			
		}
		
		return sum;






	}

}
