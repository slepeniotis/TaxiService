package taxi.model;

import java.util.Date;

/**
 * 
 * @author nyxteridas
 * το ID πρέπει να είναι autoincrement
 * αλλαγή των vectors σε λίστες
 *  
 * constructor χωρίς κόμιστρο, κόστος, διάρκεια επειδή στην έναρξη δεν τα ξέρουμε.
 *
 */


public class Request {
	private int id;
	private Date dateTime;
	private Boolean status;
	private Taxi taxi;
	private Customer customer;
	private Route route;
	
	public Request(int id, Date dateTime,Boolean status, Taxi taxi, Customer customer, Route route) {
		this.id = id;
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