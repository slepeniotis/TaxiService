package taxi.model;

//imports for using persistence, date.
import java.util.Date;
import javax.persistence.*;

//Declaring table id DB with name Request
@Entity
@Table(name = "Request")
public class Request {
	
	//Declaring Primary surrogate Key as autoincrement
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//Declaring columns with specific maximum length of characters and NULL/NOT NULL 
	@Column(name = "dateTime", length = 20, nullable = false)
	private Date dateTime;
	
	//false represents that the request is either not finished or not accepted
	//true represents that the request is executed
	@Column(name = "status", length = 1, nullable = false)
	private Boolean status;
	
	//One request has a reference to a specific taxiID, customerID and routeID. The routeID can be also null
	@ManyToOne
    @JoinColumn(name="taxiId")
	private Taxi taxi;
	
	@ManyToOne
    @JoinColumn(name="customerId")
	private Customer customer;
	
	@OneToOne
    @JoinColumn(name="routeId")
	private Route route;
	
	//constructors for Request
	public Request(){}
	public Request(Date dateTime, Taxi taxi, Customer customer) {
		//ID is auto generated, so no need to include it here
		//at the beggining, the status of all requests is set to false
		//also, a Route is not assigned yet from the customers
		
		this.dateTime = dateTime;
		this.status = false;
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
	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
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

	public void endtRequest() {
		this.status = true;
	}
	
	//override of toString method from Object
		@Override
		public String toString() {
	        String temp = this.id + " " + this.dateTime + " " + this.status + " (" + this.taxi.toString() + ") (" + this.customer.toString() + ") ";
	        if (this.route != null){
	        	temp += " (" + this.route.toString() + ")";
	        }
	        
	        return temp;
	    }	

}