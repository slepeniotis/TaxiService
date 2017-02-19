package taxi.model;

import java.util.Date;

import javax.persistence.*;

/**
 * 
 * @author nyxteridas
 *  
 *
 */


@Entity
@Table(name = "Request")
public class Request {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "dateTime", length = 20, nullable = false)
	private Date dateTime;
	
	@Column(name = "status", length = 1, nullable = false)
	private Boolean status;
	
	@ManyToOne
    @JoinColumn(name="taxiId")
	private Taxi taxi;
	
	@ManyToOne
    @JoinColumn(name="customerId")
	private Customer customer;
	
	@ManyToOne
    @JoinColumn(name="routeId")
	private Route route;
	
	public Request(){}
	public Request(Date dateTime, Boolean status, Taxi taxi, Customer customer, Route route) {
		this.dateTime = dateTime;
		this.status = status;
		this.taxi= taxi;
		this.customer =customer;
		this.route = route;	
		
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public void startRequest() {
		
	}

	public void endtRequest() {
		
	}

}