package taxi.resource;

import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlRootElement;

import taxi.model.Customer;
import taxi.model.Request;
import taxi.model.Taxi;

@XmlRootElement
public class RequestHandlingInfo {

	private long reqId;
	private long taxiId;
	private float cost;
	private int duration;
	private long customerId;
	private String decision;
	private String fromAddress;
	private int fromZipCode;
	private String fromCity;
	private String toAddress;
	private int toZipCode;
	private String toCity;


	public RequestHandlingInfo(){}

	public RequestHandlingInfo(long taxiId, long customerId){
		this.customerId = customerId;
		this.taxiId = taxiId;
	}

	public RequestHandlingInfo(long reqId, long taxiId, String decision){
		this.reqId = reqId;
		this.taxiId = taxiId;
		this.decision = decision;
	}

	public RequestHandlingInfo(long reqId, String fromAddress, int fromZipCode, 
			String fromCity, String toAddress, int toZipCode, String toCity){
		this.reqId = reqId;
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.toZipCode = toZipCode;
		this.fromZipCode = fromZipCode;
		this.toCity = toCity;
		this.fromCity = fromCity;
	}

	public RequestHandlingInfo(long reqId, long taxiId, float cost, int duration){
		this.reqId = reqId;
		this.taxiId = taxiId;
		this.cost = cost;
		this.duration = duration;
	}

	public long getReqId() {
		return reqId;
	}

	public void setReqId(long reqId) {
		this.reqId = reqId;
	}

	public long getTaxiId() {
		return taxiId;
	}

	public void setTaxiId(long taxiId) {
		this.taxiId = taxiId;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public int getFromZipCode() {
		return fromZipCode;
	}

	public void setFromZipCode(int fromZipCode) {
		this.fromZipCode = fromZipCode;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public int getToZipCode() {
		return toZipCode;
	}

	public void setToZipCode(int toZipCode) {
		this.toZipCode = toZipCode;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public Customer getCustomer(EntityManager em) {

		Customer c = null;

		if (customerId != 0) {
			c = em.find(Customer.class, (long)customerId);
		}
		else{
			return null;
		}

		return c;
	}

	public Taxi getTaxi(EntityManager em) {

		Taxi tx = null;

		if (taxiId != 0) {
			tx = em.find(Taxi.class, (long)taxiId);
		}
		else{
			return null;
		}

		return tx;
	}

	public Request getRequest(EntityManager em) {

		Request req = null;

		if (taxiId != 0) {
			req = em.find(Request.class, (long)reqId);
		}
		else{
			return null;
		}

		return req;
	}
}
