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


/**
* The RequestHandlingService class implements the functions regarding the request's life cycle.
* 
* @author  Team 4
* @since   Academic Year 2016-2017 
*/

public class RequestHandlingService {

	EntityManager em;

	//getting the current entity manager
	public RequestHandlingService() {
		em = JPAUtil.getCurrentEntityManager();
	}
	
	/** searchTaxi method
	 * This method gets as parameters:
	 * <ul>
	 * <li>the customer object and
	 * <li>the range in kilometers
	 * </ul>
	 * The method:
	 * <ul>  
	 * <li>Creates an empty list of Taxis to store the results of the search
	 * <li>Creates a CoordinateCalc object, which will be used to calculate the distance between customer and taxi
	 * <li>Fetches from the table Taxi all the taxis which are free (status = true)
	 * <li>In case the result set is empty, the list of results has 0 elements
	 * <li>Otherwise, the distance between customer and each taxi is calculated.
	 * <li>If the distance is less or equal to the given range, then this taxi is inserted in the result list.
	 * </ul>
	 * 
	 * @param customer type Customer
	 * @param range type int
	 * @return List<Taxi>
	 */
	public List<Taxi> searchTaxi(Customer customer, int range){
		if(customer == null || range == 0)
			return null;

		CoordinateCalc cc = new CoordinateCalc();
		List<Taxi> taxlst = new ArrayList(0);
		Query query = em.createQuery("select tx from Taxi tx where tx.status = :stts");
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


	/** startRequest method
	 * This method gets as parameters:
	 * <ul>
	 * <li>the taxi object selected by the customer for this request and
	 * <li>the customer object
	 * </ul>
	 * The method:
	 * <ul>  
	 * <li>Uses the current date as the date that the request was made
	 * <li>Creates a request with that date, the taxi and the customer and inserts it to the DB
	 * <li>Fetches from the table TaxiDriver the taxi driver to whom the given taxi belongs
	 * <li>Informs taxi driver for this request
	 * <li>The created request is returned
	 * </ul>
	 *  
	 * @param taxi type Taxi
	 * @param customer type Customer
	 * @return Request
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

	/** handleRequest method
	 * This method gets as parameters:
	 * <ul>
	 * <li>the request object made by the customer,
	 * <li>the Taxi object with which the request is connected and
	 * <li>the decision of the taxi driver (whether he will undertake this request or not)
	 * </ul>
	 * The method:
	 * <ul>
	 * <li>In case the decision is 'yes":
	 * <ul>
	 * <li>The status of the request is updated from pending to ongoing (in case it is not already canceled by the customer)
	 * <li>The status of the taxi is updated from true (available) to false (booked)
	 * <li>The customer is informed that his request was accepted
	 * </ul>
	 * <li>In case the decision is "no":
	 * <ul>
	 * <li>The status of the request is updated from pending to canceled
	 * <li>The customer is informed that his request was denied
	 * </ul>
	 * </ul> 
	 * The method returns true in case the request was accepted or false in case it was denied or any error occurred
	 *  
	 * @param req type Request
	 * @param taxi type Taxi
	 * @param decision type String
	 * @return boolean
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
