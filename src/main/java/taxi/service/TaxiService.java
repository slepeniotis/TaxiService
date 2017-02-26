package taxi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import taxi.model.*;
import taxi.persistence.JPAUtil;
import taxi.utils.AESEncrypt;
import taxi.utils.CoordinateCalc;
import taxi.utils.RequestState;

public class TaxiService {

	EntityManager em;

	public TaxiService() {
		em = JPAUtil.getCurrentEntityManager();
	}

	//ΕΓΓΡΑΦΗ
	public Taxi createTaxi(String carModel, String carType, String licensePlate, String carModelDate, double locationLat, double locationLon){

		if(carModel == null || carType == null || licensePlate == null || carModelDate == null || locationLat == 0 || locationLon == 0)
			return null;

		Taxi taxi = new Taxi(carModel, carType, licensePlate, carModelDate, locationLat, locationLon);

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
			double locationLat, double locationLon, String address, String city, int zipCode, String email, String creditCardType, String creditCardNumber, 
			String expiryDate, String ccv){

		if(name == null || surname == null || sex == null || username == null 
				|| password == null || dateOfBirth == null || address == null || 
				city == null || zipCode == 0 || email == null || creditCardType == null
				|| creditCardNumber == null || expiryDate == null || ccv == null || locationLat == 0 || locationLon == 0)
			return null;

		Customer customer = new Customer(name, surname, sex, username, password, dateOfBirth, 
				locationLat, locationLon, address, city, zipCode, email, creditCardType, creditCardNumber, 
				expiryDate, ccv);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(customer);
		tx.commit();

		return customer;

	}

	//ΤΑΥΤΟΠΟΙΗΣΗ
	public Object login(String userType, String username, String password){
		Object result = null;

		if(userType == null || username == null || password == null)
			return result;

		if(userType == "Customer"){
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
			Customer rsltcst = (Customer)query.getSingleResult();

			if(rsltcst.equals(null)){
				return result;
			}

			result = rsltcst;
		}
		else if(userType == "Taxi Driver"){
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
			TaxiDriver rslttxdr = (TaxiDriver)query.getSingleResult();

			if(rslttxdr.equals(null)){
				return result;
			}

			result = rslttxdr;

		}

		return result;
	}

	//ΑΝΑΖΗΤΗΣΗ ΤΑΞΙ, we can check for lat/lon from google maps and see also the distance between
	//we do not need seperate method for "selecting taxi" since its function is similar with startRequest
	public List<Taxi> searchTaxi(Customer customer, int range){
		if(customer == null || range == 0)
			return null;
		
		CoordinateCalc cc = new CoordinateCalc();
		List<Taxi> taxlst = new ArrayList();
		Query query = em.createQuery("select taxi from Taxi taxi");		
		List<Taxi> taxirslt = query.getResultList();
		if(taxirslt.isEmpty())
			return null;
		
		for(Taxi t : taxirslt)
			if(cc.calculateDistanceInKilometer(customer.getLocationLat(), customer.getLocationLon(), 
					t.getLocationLat(), t.getLocationLon()) <= range)
				taxlst.add(t);
		
		return taxlst;
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
		customer.addRequest(req);
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
			taxi.addRequest(req);
			tx.commit();	

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

		Route route = new Route(fromAddress, toAddress, fromCity, toCity, fromZipCode, toZipCode, req);

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
	//rating can be zero
	public Evaluation createEvaluation(Route route, int rating, String comment, Date dateOfEval){
		if(route == null || comment == null || dateOfEval == null || route.getReq() == null || route.getReq().getStatus() != RequestState.DONE)
			return null;

		Evaluation eval = new Evaluation(rating, comment, dateOfEval);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(eval);
		route.setEval(eval);		
		tx.commit();

		Taxi taxeval = route.getReq().getTaxi();

		Query query = em.createQuery("select taxidr from taxidriver taxidr where taxiid = :txid");
		query.setParameter("txid", taxeval.getId());
		TaxiDriver taxdrrslt = (TaxiDriver)query.getSingleResult();

		//inform taxidriver(evaluation)
		taxdrrslt.informTaxiDriver("Customer " + route.getReq().getCustomer().getName() + " submitted an evaluation for request: " + route.getReq().getId());

		return eval;
	}

	//ΣΤΑΤΙΣΤΙΚΑ (selection 1 = date range, 2 = from city, 3 = to city
	public float produceStatistics(int selection, Date fromRange, Date toRange){
		if(selection == 0 || fromRange == null || toRange == null)
			return 0;
		float sum = 0;
		if(selection == 1){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date d;

			/* we could alternatively fetch the contents of Route and then check the dates from the object Request
			 * we prefer this way instead, to avoid the overhead
			 */
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
				if(r.getRoute() != null)
					sum += r.getRoute().getCommision();
			}
		}
		return sum;

	}

	public String produceStatistics(int selection, String city){
		String result="";
		if(selection == 0 || city == null)
			return result;		

		if(selection == 2){

			Query query = em.createQuery("select r from Route r where r.fromCity LIKE :ct");
			query.setParameter("ct", city);
			List<Route> routerslt = query.getResultList();		
			result += "Total requests from city " + city + " is: " + routerslt.size() + "\n"; 
			for(Route r : routerslt)
				result += "Request ID: " + r.getReq().getId() + "\n";			
		}
		else if(selection == 3){
			Query query = em.createQuery("select r from Route r where r.toCity LIKE :ct");
			query.setParameter("ct", city);
			List<Route> routerslt = query.getResultList();		
			result += "Total requests to city " + city + " is: " + routerslt.size() + "\n"; 
			for(Route r : routerslt)
				result += "Request ID: " + r.getReq().getId() + "\n";
		}

		return result;
	}
	
	
	//delete objects	
	//test cases

}
