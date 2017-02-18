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
	private Taxi taxi;
	private Customer customer;

	public Request(int id, Date dateTime) {
		this.id = id;
		this.dateTime = dateTime;
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

	public void startKoursa() {
		throw new UnsupportedOperationException();
	}

	public void endtKoursa() {
		throw new UnsupportedOperationException();
	}

	public void calculateStatistics() {
		throw new UnsupportedOperationException();
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
}