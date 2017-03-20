package taxi.model;

//imports for using persistence, date.
import java.util.Date;
import javax.persistence.*;

import taxi.utils.RequestStatus;


/**
* The Request class implements all requests made from a customer to a taxi.
* <p>
* Every request is connected with a taxi, a customer and a route
* It also has:
* <ul>
* <li>the date/time in which the request was made, 
* <li>the status of the request (enum RequestStatus: PENDING, ONGOING, CANCELED, DONE)
* </ul>
* 
* @author  Team 4
* @since   Academic Year 2016-2017 
*/

//Declaring table id DB with name Request
@Entity
@Table(name = "Request")
public class Request {

	//Declaring Primary surrogate Key as autoincrement
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	//Declaring columns with specific maximum length of characters and NULL/NOT NULL 
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateTime", nullable = false)
	private Date dateTime;

	//false represents that the request is either not finished or not accepted
	//true represents that the request is executed
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private RequestStatus status;

	//One request has a reference to a specific taxiID, customerID and routeID. The routeID can be also null
	//fetch type EAGER does fetch the taxi object
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="taxiId", nullable = false)
	private Taxi taxi;

	//fetch type EAGER does fetch the customer object
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="customerId", nullable = false)
	private Customer customer;

	//fetch type lazy does not fetch the object Route
	//we don't use any kind of cascade here, since the route is not necessarily set
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.REMOVE)
	@JoinColumn(name="routeId", nullable = true)
	private Route route;

	/**<h2>The default constructor</h2>
	 * @return an empty Request object
	 */
	public Request(){}
	
	/**<h2>The main constructor</h2>
	 * The route is going to be connected with the Request in a later phase.
	 * The status of the request is always PENDING at the beginning.
	 * <p>
	 * @param dateTime date the request was made, type Date
	 * @param taxi taxi object which received the request, type Taxi
	 * @param customer customer object which made the request, type Customer
	 * 
	 * @return a Request object
	 */
	public Request(Date dateTime, Taxi taxi, Customer customer) {
		//ID is auto generated, so no need to include it here
		//at the beggining, the status of all requests is set to false
		//also, a Route is not assigned yet from the customers

		this.dateTime = dateTime;
		this.status = RequestStatus.PENDING;
		this.taxi = taxi;
		this.customer = customer;	
	}

	/**<h2>getId method</h2>
	 * @return request's ID
	 */
	public long getId() {
		return this.id;
	}

	/**<h2>getDateTime method</h2>
	 * @return request's dateTime
	 */
	public Date getDateTime() {
		return this.dateTime;
	}

	/**<h2>setDateTime method</h2>
	 * this method sets the given Date to the attribute dateTime of the caller object.
	 * @param dateTime type Date
	 * @return void
	 */
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	/**<h2>getStatus method</h2>
	 * @return request's status
	 */
	public RequestStatus getStatus() {
		return status;
	}

	/**<h2>setStatus method</h2>
	 * this method sets the given RequestStatus to the attribute status of the caller object.
	 * @param status type RequestStatus
	 * @return void
	 */
	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	/**<h2>setTaxi method</h2>
	 * this method sets the given Taxi to the attribute taxi of the caller object.
	 * @param taxi type Taxi
	 * @return void
	 */
	public void setTaxi(Taxi taxi) {
		this.taxi = taxi;
	}

	/**<h2>getTaxi method</h2>
	 * @return request's taxi
	 */
	public Taxi getTaxi() {
		return this.taxi;
	}

	/**<h2>setCustomer method</h2>
	 * this method sets the given Customer to the attribute customer of the caller object.
	 * @param customer type Customer
	 * @return void
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**<h2>getCustomer method</h2>
	 * @return request's customer
	 */
	public Customer getCustomer() {
		return this.customer;
	}

	/**<h2>setRoute method</h2>
	 * this method sets the given Route to the attribute route of the caller object.
	 * @param route type Route
	 * @return void
	 */
	public void setRoute(Route route) {
		this.route = route;
	}

	/**<h2>getRoute method</h2>
	 * @return request's route
	 */
	public Route getRoute() {
		return this.route;
	}

	/**startRequest method
	 * sets the route to the already made request
	 * @param route type Route
	 * @return void
	 */
	public void startRequest(Route route) {
		this.setRoute(route);
	}

	/**endRequest method
	 * sets the status of the request to DONE
	 * @return void
	 */
	public void endRequest() {
		this.status = RequestStatus.DONE;
	}

	/**<h2>toString method</h2>
	 * @return a string including all the information of a Request object
	 */
	@Override
	public String toString() {
		String temp = this.id + " " + this.dateTime + " " + this.status + "\n (" + this.taxi.toString() + ")\n (" + this.customer.toString() + ")";
		if (this.route != null){
			temp += "\n (" + this.route.toString() + ")";
		}

		return temp;
	}	

}