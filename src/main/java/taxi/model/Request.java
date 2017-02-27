package taxi.model;

//imports for using persistence, date.
import java.util.Date;
import javax.persistence.*;

import taxi.utils.RequestStatus;

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
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="routeId", nullable = true)
	private Route route;

	//constructors for Request
	public Request(){}
	public Request(Date dateTime, Taxi taxi, Customer customer) {
		//ID is auto generated, so no need to include it here
		//at the beggining, the status of all requests is set to false
		//also, a Route is not assigned yet from the customers

		this.dateTime = dateTime;
		this.status = RequestStatus.PENDING;
		this.taxi = taxi;
		this.customer = customer;	
	}

	//get/set methods in order to have access in private attributes
	public long getId() {
		return this.id;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public void setTaxi(Taxi taxi) {
		this.taxi = taxi;
	}

	public Taxi getTaxi() {
		return this.taxi;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Route getRoute() {
		return this.route;
	}

	//methods for starting request and ending
	//during startRequest, a route has to be set

	public void startRequest(Route route) {
		this.setRoute(route);
	}

	public void endRequest() {
		this.status = RequestStatus.DONE;
	}

	//override of toString method from Object
	@Override
	public String toString() {
		String temp = this.id + " " + this.dateTime + " " + this.status + "\n (" + this.taxi.toString() + ")\n (" + this.customer.toString() + ")";
		if (this.route != null){
			temp += "\n (" + this.route.toString() + ")";
		}

		return temp;
	}	

}