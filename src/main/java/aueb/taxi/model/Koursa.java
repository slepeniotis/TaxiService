package aueb.taxi.model;

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


public class Koursa {
	private int id;
	private String from;
	private String to;
	private Date dateTime;
	private int duration;
	private float cost;
	private Float commision;
	public Taxi unnamed_Taxi;
	public Customer unnamed_Customer;

	public Koursa(int id, String from, String to, Date dateTime, int duration, float cost, float commision) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.dateTime = dateTime;
		this.duration = duration;
		this.cost = cost;
		this.commision = commision;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFrom() {
		return this.from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return this.to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public float getCost() {
		return this.cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public Float getCommision() {
		return this.commision;
	}

	public void setCommision(Float commision) {
		this.commision = commision;
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

	public void setUnnamed_Taxi_(Taxi aUnnamed_Taxi_) {
		this.unnamed_Taxi = aUnnamed_Taxi_;
	}

	public Taxi getUnnamed_Taxi_() {
		return this.unnamed_Taxi;
	}

	public void setUnnamed_Customer_(Customer aUnnamed_Customer_) {
		this.unnamed_Customer = aUnnamed_Customer_;
	}

	public Customer getUnnamed_Customer_() {
		return this.unnamed_Customer;
	}
}