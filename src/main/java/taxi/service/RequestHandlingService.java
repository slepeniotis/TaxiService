package taxi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import taxi.model.Customer;
import taxi.model.Request;
import taxi.model.Route;
import taxi.model.Taxi;
import taxi.model.TaxiDriver;
import taxi.persistence.JPAUtil;
import taxi.utils.CoordinateCalc;
import taxi.utils.RequestStatus;

public class RequestHandlingService {

	EntityManager em;

	//getting the current entity manager
	public RequestHandlingService() {
		em = JPAUtil.getCurrentEntityManager();
	}
	
	/* Search Taxi method
	 * (we can check for lat/lon from google maps and see also the distance between)
	 * we do not need seperate method for "selecting taxi" since its function is 
	 * similar with startRequest
	 * 
	 * we are receiving as input the customer who is searching for a taxi 
	 * and the range in kilometers in which the search is applicable
	 * if none of the inputs is null, we are creating an empty list to store the results,
	 * and an object of type CoordinateCalc which we will use 
	 * for calculating the distance between two set of coordinates
	 * 
	 * Then we retrieve from the DB all the contents of table taxi where the taxi is free.
	 * we check if the result is empty.
	 * then we run into the result checking its distance from the customer.
	 * If the distance is within the range set by the customer, 
	 * this taxi is inserted in the list result
	 */
	public List<Taxi> searchTaxi(Customer customer, int range){
		if(customer == null || range == 0)
			return null;

		CoordinateCalc cc = new CoordinateCalc();
		List<Taxi> taxlst = new ArrayList(0);
		Query query = em.createQuery("select taxi from Taxi taxi where taxi.status = :stts");
		query.setParameter("stts", true);
		List<Taxi> taxirslt = query.getResultList();
		if(taxirslt.isEmpty())
			return null;

		for(Taxi t : taxirslt)
			if(cc.calculateDistanceInKilometer(customer.getLocationLat(), customer.getLocationLon(), 
					t.getLocationLat(), t.getLocationLon()) <= range)
				taxlst.add(t);

		return taxlst;
	}

	//REQUEST EXECUTION METHODS

	/* startRequest is triggered by the customer.
	 * we are getting as input the current date (auto from the system), 
	 * the taxi selected by the customer for this request an the customer
	 * we check if any of the inputs is empty/null
	 * 
	 * then we are creating a Request object with the inputs.
	 * using the taxi object, we are retrieving its driver. 
	 * This information will be used later in order to inform taxi driver 
	 * about this new request.
	 * 
	 * we insert the new request in the DB, 
	 * and also in the list of Requests made by the customer in general
	 * At the end we communicate with the driver, and return the request object created
	 */
	public Request startRequest(Taxi taxi, Customer customer){

		if(taxi == null || customer == null)
			return null;

		Date dateTime = new Date();
		Request req = new Request(dateTime, taxi, customer);
		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxiID = :taxiid");
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

	/* handleRequest is triggered by the taxi driver
	 * it gets as inputs the request send previously by the customer, 
	 * the taxi and the decision (accept or deny the request) of the taxi driver
	 * 
	 *  the method returns true in case the request was accepted 
	 *  or false in case it was denied or any error occured
	 *  
	 *  first we are checking if any of the inputs is empty/null
	 *  we then check driver's decision.
	 *  - "yes": we update the status of the request from pending to ongoing 
	 *              in case it is not already canceled by the customer
	 *           we update the status of the taxi from true (available) to false (booked)
	 *           we inform customer that his request was accepted
	 *  - "no" : we update the status of the request from pending to canceled
	 *           we inform customer that his request was denied
	 */
	public boolean handleRequest(Request req, Taxi taxi, String decision){
		if(req == null || taxi == null || decision == null || decision == " ")
			return false;

		boolean result = false;

		if(decision == "yes"){
			if(req.getStatus() != RequestStatus.CANCELED){
				em = JPAUtil.getCurrentEntityManager();

				EntityTransaction tx = em.getTransaction();
				tx.begin();
				req.setStatus(RequestStatus.ONGOING);
				taxi.setStatus(false);
				taxi.addRequest(req);
				tx.commit();

				req.getCustomer().informCustomer("Request accepted from Taxi " + taxi.getId());
				result = true;
			}
			
		}
		else if(decision == "no"){
			em = JPAUtil.getCurrentEntityManager();

			EntityTransaction tx = em.getTransaction();
			tx.begin();
			req.setStatus(RequestStatus.CANCELED);			
			tx.commit();
			
			req.getCustomer().informCustomer("Request denied from Taxi " + taxi.getId());

		}

		return result;
	}

	/* createRoute is triggered by the customer after his request is accepted by the driver
	 * The inputs are the request object, and the information of the route (origin-destination)
	 * we ensure that none of the inputs is empty/null
	 * 
	 * we are creating a new route and inserted in the DB
	 * we also update the route field in object request
	 * 
	 * the route created is returned as a result
	 * 
	 */
	public Route createRoute(Request req, String fromAddress, String toAddress, String fromCity, String toCity, int fromZipCode, int toZipCode) {

		if(req == null || fromAddress == null || toAddress == null || fromCity == null 
				|| toCity == null || fromZipCode == 0 || toZipCode == 0 
				|| fromAddress == " " || toAddress == " " || fromCity == " " || toCity == " ")
			return null;

		Route route = new Route(fromAddress, toAddress, fromCity, toCity, fromZipCode, toZipCode, req);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(route);
		req.setRoute(route);
		tx.commit();

		return route;	

	}

	/* stopRequest is triggered from the driver, when the customer get aboard
	 * it has as inputs the request object, the taxi and the cost set by the driver
	 * we ensure none of the inputs is empty/null
	 * 
	 * we then change the status of the taxi from false (booked) to true (free)
	 * we update the status of the request to done
	 * we update the cost of the route (got from within the request object)
	 * we calculate the commision of the route
	 * we inform customer about the final cost and ask him to evaluate the service
	 * 
	 * the method returns true in case everything went ok
	 * 
	 */
	public boolean stopRequest(Request req, Taxi taxi, float cost, int duration){

		if(req == null || taxi == null || cost == 0 || duration == 0)
			return false;

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//call method for changing taxi status
		taxi.setStatus(true);
		//update status
		req.endRequest();
		//update cost and commision
		req.getRoute().setCost(cost);
		req.getRoute().calculateCommision();
		req.getRoute().setDuration(duration);
		tx.commit();		

		req.getCustomer().informCustomer("The final cost of request: " + req.getId() + " was: " + req.getRoute().getCost());
		req.getCustomer().informCustomer("Please take a minute to evaluate our service");

		return true;	

	}
	
	/* Cancel Request
	 * we assume that customer can only cancel a request he already made
	 * and it is not already canceled or done
	 * we receive the customer object and the request made
	 *  
	 * we inform taxi driver that the request has been canceled
	 *  
	 * after the successful canceling, we get true
	 */
	public boolean cancelRequest(Customer customer, Request req){	
		boolean result = false;
		if(req.getStatus() != RequestStatus.CANCELED && req.getStatus() != RequestStatus.DONE){
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			req.setStatus(RequestStatus.CANCELED);
			result = true;
			tx.commit();

			Taxi taxi = req.getTaxi();

			Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxiID = :txid");
			query.setParameter("txid", taxi.getId());
			TaxiDriver taxdrrslt = (TaxiDriver)query.getSingleResult();

			//inform taxidriver(evaluation)
			taxdrrslt.informTaxiDriver("Customer " + req.getCustomer().getName() + " canceled the request: " + req.getId());

		}

		return result;
	}
}
