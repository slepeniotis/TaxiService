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
import taxi.utils.RequestStatus;
import taxi.utils.Validators;

public class TaxiService {

	EntityManager em;

	//getting the current entity manager
	public TaxiService() {
		em = JPAUtil.getCurrentEntityManager();
	}

	//REGISTRATION METHODS

	/* Registration for Taxi and TaxiDriver
	 * we receive all the information needed for the creation of a taxi.
	 * we check if any value is empty/null. we assume that location is fetched from GPS
	 * The taxi is then created and inserted in the DB.
	 * 
	 * the object taxi which was created is returned as a result
	 */
	public Taxi createTaxi(String carModel, String carType, String licensePlate, String carModelDate, double locationLat, double locationLon){

		if(carModel == null || carType == null || licensePlate == null || carModelDate == null || locationLat == 0 || locationLon == 0)
			return null;


		//validations
		if (Validators.validateLicensePlate(licensePlate))
			if (Validators.validateCarModelDate(carModelDate)){
				Taxi taxi = new Taxi(carModel, carType, licensePlate, carModelDate, locationLat, locationLon);

				EntityTransaction tx = em.getTransaction();
				tx.begin();
				em.persist(taxi);
				tx.commit();

				return taxi;
			}
			else {
				//in case licensePlate already exists
				System.out.println("Car already connected with other driver, or license plate is invalid");
				return null;
			}
		else{
			//in case carModelDate validation fails
			System.out.println("Car model date is invalid");
			return null;
		}
	}

	/* We receive all the information needed for the creation of a taxi driver.
	 * we check if any value is empty/null. we assume that taxi of the driver is already created
	 * The taxi driver is then created and inserted in the DB.
	 * 
	 * the object taxi driver which was created is returned as a result
	 */
	public TaxiDriver registerTaxiDriver(String name, String surname,String sex, String username, String password, Date dateOfBirth, 
			String address, String city, int zipCode, String email, String creditCardType, String creditCardNumber, 
			String expiryDate, String ccv, Taxi owns){

		if(name == null || surname == null || sex == null || username == null 
				|| password == null || dateOfBirth == null || address == null || 
				city == null || zipCode == 0 || email == null || creditCardType == null
				|| creditCardNumber == null || expiryDate == null || ccv == null || owns == null)
			return null;



		//validations
		if (Validators.validateUsername(username)){
			if (Validators.validatePassword(password)){
				try {
					password = AESEncrypt.encrypt(password);
				}
				catch (Exception e){
					System.out.println(e.getStackTrace());
					//in case an exception occurs
					return null;
				}
				if (Validators.validateEmail(email)){
					if (Validators.validateCreditCard(creditCardNumber, expiryDate, ccv)){
						if (Validators.validateDateOfBirth(dateOfBirth)){
							if (Validators.validateTaxi(owns)){
								TaxiDriver taxidr = new TaxiDriver(name, surname, sex, username, 
										password, dateOfBirth, address, city, zipCode, email, 
										creditCardType, creditCardNumber, expiryDate, ccv, owns);

								EntityTransaction tx = em.getTransaction();
								tx.begin();
								em.persist(taxidr);
								tx.commit();

								return taxidr;
							}
							else {
								System.out.println("Taxi already defined");
								//in case the Taxi is already defined from other driver
								return null;
							}
						}
						else {
							System.out.println("Date of birth is invalid");	
							//in case Date of birth is invalid
							return null;
						}
					}
					else {
						System.out.println("Credit Card's details are invalid");
						//in case any of credit card's information is invalid
						return null;
					}
				}
				else {
					System.out.println("Email already in use");
					//in case the email is already in use
					return null;
				}				
			}
			else {
				System.out.println("Invalid password");
				//in case the password is invalid,
				return null;
			}
		}
		else {
			//in case username already exists
			System.out.println("Username already in use");
			return null;
		}
	}

	/* Registration for Customer
	 * we receive all the information needed for the creation of a customer.
	 * we check if any value is empty/null. we assume that location is fetched from GPS
	 * The customer is then created and inserted in the DB.
	 * 
	 * the object customer which was created is returned as a result
	 */
	public Customer registerCustomer(String name, String surname, String sex, String username, String password, Date dateOfBirth, 
			double locationLat, double locationLon, String address, String city, int zipCode, String email, String creditCardType, String creditCardNumber, 
			String expiryDate, String ccv){

		if(name == null || surname == null || sex == null || username == null 
				|| password == null || dateOfBirth == null || address == null || 
				city == null || zipCode == 0 || email == null || creditCardType == null
				|| creditCardNumber == null || expiryDate == null || ccv == null || locationLat == 0 || locationLon == 0)
			return null;

		//validations
		if (Validators.validateUsername(username)){
			if (Validators.validatePassword(password)){
				try {
					password = AESEncrypt.encrypt(password); 
				}
				catch (Exception e){
					System.out.println(e.getStackTrace());
					//in case an exception occurs
					return null;
				}
				if (Validators.validateEmail(email)){
					if (Validators.validateCreditCard(creditCardNumber, expiryDate, ccv)){
						if (Validators.validateDateOfBirth(dateOfBirth)){
							Customer customer = new Customer(name, surname, sex, username, password, dateOfBirth, 
									locationLat, locationLon, address, city, zipCode, email, creditCardType, creditCardNumber, 
									expiryDate, ccv);

							EntityTransaction tx = em.getTransaction();
							tx.begin();
							em.persist(customer);
							tx.commit();

							return customer;
						}							
						else {
							System.out.println("Date of birth is invalid");
							//in case Date of birth is invalid
							return null;
						}						
					}
					else {
						System.out.println("Credit Card's details are invalid");
						//in case any of credit card's information is invalid
						return null;
					}
				}
				else {
					System.out.println("Email already in use");
					//in case the email is already in use
					return null;
				}
			}
			else {
				System.out.println("Invalid password");
				//in case the password is invalid
				return null;
			}		
		}
		else {
			System.out.println("Username already exists");
			//in case username already exists
			return null;
		}
	}

	/* Identification Method
	 * We are receiving as input the user type which wants to be signed in (selected by the user).
	 * We also receive the username, password and new coordinates
	 * we check if any of the inputs is empty/null
	 * then we check the login type. 
	 * - In case it is for customer, then we search in the table of customers 
	 *   to find the customer with the specific username and password (encrypted)
	 * - In case it is for taxi driver, then we search in the table of taxi drivers 
	 *   to find the customer with the specific username and password (encrypted) 
	 * If the user is not found, or any exception is raised, the return object is null, 
	 * or else the found user object
	 */	
	public Object login(String userType, String username, String password, double newLat, double newLon){
		Object result = null;

		if(userType == null || username == null || password == null || newLat ==0 || newLon == 0)
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
			
			EntityTransaction tx = em.getTransaction();
			tx.begin();				
			rsltcst.setLocationLat(newLat);
			rsltcst.setLocationLon(newLon);			
			tx.commit();

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

			EntityTransaction tx = em.getTransaction();
			tx.begin();				
			rslttxdr.getOwns().setLocationLat(newLat);
			rslttxdr.getOwns().setLocationLon(newLon);			
			tx.commit();
			
			result = rslttxdr;

		}

		return result;
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
	 * Then we retrieve from the DB all the contents of table taxi.
	 * we check if the result is empty.
	 * then we run into the result checking first of all if the taxi is free,
	 * and then its distance from the customer.
	 * If the distance is within the range set by the customer, 
	 * this taxi is inserted in the list result
	 */
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
			if(t.getStatus())
				if(cc.calculateDistanceInKilometer(customer.getLocationLat(), customer.getLocationLon(), 
						t.getLocationLat(), t.getLocationLon()) <= range)
					taxlst.add(t);

		return taxlst;
	}

	//REQUEST EXECUTION METHODS

	/* startRequest is triggered by the customer.
	 * we are getting as input the current date, 
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

	/* handleRequest is triggered by the taxi driver
	 * it gets as inputs the request send previously by the customer, 
	 * the taxi and the decision (accept or deny the request) of the taxi driver
	 * 
	 *  the method returns true in case the request was accepted 
	 *  or false in case it was denied or any error occured
	 *  
	 *  first we are checking if any of the inputs is empty/null
	 *  we thecn check driver's decision.
	 *  - "yes": we update the status of the request from pending to ongoing
	 *           we update the status of the taxi from true (available) to false (booked)
	 *           we inform customer that his request was accepted
	 *  - "no" : we inform customer that his request was denied
	 */
	public boolean handleRequest(Request req, Taxi taxi, String decision){
		if(req == null || taxi == null || decision == null)
			return false;

		boolean result = false;

		if(decision == "yes"){
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
		else if(decision == "no"){
			req.getCustomer().informCustomer("Request denied from Taxi " + taxi.getId());

			result = false;
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
	public boolean stopRequest(Request req, Taxi taxi, float cost){

		if(req == null || taxi == null || cost == 0)
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
		tx.commit();		

		req.getCustomer().informCustomer("The final cost of request: " + req.getId() + " was: " + req.getRoute().getCost());
		req.getCustomer().informCustomer("Please take a minute to evaluate our service");

		return true;	

	}

	/* Evaluation method
	 * createEvaluation gets as inputs the route, which customer evaluates,
	 * the rating and comment
	 * we assume rating can be zero
	 * Since the evaluation is done when calling this method, the user has no access to the date,
	 * the system gives always the current date
	 * 
	 * we check that none of the inputs is empty/null (except of rating) and that the request related with this request is in status done
	 * 
	 * we create the object evaluation using the inputs and inserted in the DB
	 * then we connect this evaluation with the related route
	 * 
	 * then we retrieve the taxi object from within the request 
	 * and search to find its driver
	 * we will use this object to contact the driver, 
	 * in order to inform him that an evaluation was submitted for a route he completed
	 */
	public Evaluation createEvaluation(Route route, int rating, String comment){
		if(route == null || comment == null || route.getReq() == null || route.getReq().getStatus() != RequestStatus.DONE)
			return null;

		Date dateOfEval = new Date();

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

	/* produceStatistcs method
	 * this method is overloaded
	 * 
	 * This version of method, produces a float sum with all the commisions received 
	 * from the company within a specific date range
	 * The method gets as inputs the selection 
	 *                   (selection 1 = date range, 2 = from city, 3 = to city
	 * and the from range-to range (Dates)
	 * 
	 * we check if any of the inputs is empty/null
	 * then we retrieve from Request table all the requests made between this range
	 * we run into the list of the results (in case it is not empty),
	 * we check if any route is connected with this request,
	 * and then summarize its commision 
	 * 
	 */
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
				return 0;
			}	

			List<Request> reqrslt = query.getResultList();		
			if(!reqrslt.isEmpty())
				for(Request r : reqrslt) {
					if(r.getRoute() != null)
						sum += r.getRoute().getCommision();
				}
		}
		return sum;

	}

	/* produceStatistcs method
	 * this method is overloaded
	 * 
	 * This version of method, produces a String with information about total routes made
	 * from a specific city or to a specific city
	 * The method gets as inputs the selection 
	 *                      (selection 1 = date range, 2 = from city, 3 = to city
	 * and the city name
	 * 
	 * we check if any of the inputs is empty/null
	 * then check the selection
	 * we retrieve from Route table all the requests made from/to 
	 * a specific city (according to the selection)
	 * we run into the list of the results (in case it is not empty),
	 * and we return a string containing the total number of those routes, 
	 * along with the ID of the request related to them
	 * 
	 * in case the result is empty, the total number of such routes is 0
	 */ 
	public String produceStatistics(int selection, String city){
		String result="";
		if(selection == 0 || city == null)
			return result;		

		if(selection == 2){

			Query query = em.createQuery("select r from Route r where r.fromCity LIKE :ct");
			query.setParameter("ct", city);
			List<Route> routerslt = query.getResultList();	
			if(!routerslt.isEmpty()){
				result += "Total requests from city " + city + " is: " + routerslt.size() + "\n";
				for(Route r : routerslt)
					result += "Request ID: " + r.getReq().getId() + "\n";
			}
			else{
				result += "Total requests from city " + city + " is: 0\n";
			}
		}
		else if(selection == 3){
			Query query = em.createQuery("select r from Route r where r.toCity LIKE :ct");
			query.setParameter("ct", city);
			List<Route> routerslt = query.getResultList();	
			if(!routerslt.isEmpty()){
				result += "Total requests to city " + city + " is: " + routerslt.size() + "\n";
				for(Route r : routerslt)
					result += "Request ID: " + r.getReq().getId() + "\n";
			}
			else{
				result += "Total requests to city " + city + " is: 0\n";
			}
		}

		return result;
	}

	/* Change of Taxi belonging to a Taxi Driver
	 * we assume that the taxi driver requests for change for his taxi
	 * we receive all the information needed for the creation of the new taxi and the taxi driver.
	 * we check if any value is empty/null. we assume that location is fetched from GPS
	 * we then create the new taxi and remove the old one
	 * 
	 * the object taxi which was created is returned as a result
	 */
	public Taxi changeTaxi(TaxiDriver taxidriver, String carModel, String carType, String licensePlate, String carModelDate, double locationLat, double locationLon){
		if(carModel == null || carType == null || licensePlate == null || carModelDate == null || locationLat == 0 || locationLon == 0)
			return null;


		//validations
		if (Validators.validateLicensePlate(licensePlate))
			if (Validators.validateCarModelDate(carModelDate)){
				Taxi newTaxi = new Taxi(carModel, carType, licensePlate, carModelDate, locationLat, locationLon);
				Taxi oldTaxi = taxidriver.getOwns();

				EntityTransaction tx = em.getTransaction();
				tx.begin();
				em.persist(newTaxi);
				taxidriver.setOwns(newTaxi);
				em.remove(oldTaxi);
				tx.commit();				

				//TI GINETAI ME TA REQUESTS EDO ?
				
				return newTaxi;
			}
			else {
				//in case licensePlate already exists
				System.out.println("Car already connected with other driver, or license plate is invalid");
				return null;
			}
		else{
			//in case carModelDate validation fails
			System.out.println("Car model date is invalid");
			return null;
		}
	}

	/* Remove of Taxi Driver
	 * we assume that taxi driver requests his own removal
	 * we receive the taxi driver object
	 *  
	 * after the successful removal, we get true
	 */
	public boolean removeDriver(TaxiDriver taxidriver){	
		EntityTransaction tx = em.getTransaction();
		tx.begin();				
		em.remove(taxidriver);
		tx.commit();
		
		return true;
	}
	
	/* Remove of Customer
	 * we assume that customer requests his own removal
	 * we receive the customer object
	 *  
	 * after the successful removal, we get true
	 */
	public boolean removeCustomer(Customer customer){	
		EntityTransaction tx = em.getTransaction();
		tx.begin();				
		em.remove(customer);
		tx.commit();
		
		//TI GINETAI ME TA REQUESTS EDO ?
		
		return true;
	}
	
	/* Remove of Request
	 * we assume that customer can only remove the request he already made
	 * we receive the customer object and the request made
	 *  
	 * First of all we remove the request from the list of requests from customer and taxi
	 * then we inform taxi driver that the request has been canceled
	 * at the end we remove the request from the db
	 * 
	 * after the successful removal, we get true
	 */
	public boolean removeRequest(Customer customer, Request req){	
		boolean result = false;
		EntityTransaction tx = em.getTransaction();
		tx.begin();				
		
		if(customer.removeRequest(req)){
			if(req.getTaxi().removeRequest(req)){
				em.remove(req);
			}
			result = true;
		}					
		tx.commit();
		
		return result;
	}

}
