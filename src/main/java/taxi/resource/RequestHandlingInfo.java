package taxi.resource;

import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlRootElement;

import taxi.model.Customer;
import taxi.model.Request;
import taxi.model.Taxi;

/**
 * The RequestHandlingInfo class implements the xml representation of a request, needed from RequestHandlingResource
 * <p>
 * Every RequestHandlingInfo has:
 * <ul>
 * <li>reqId, 
 * <li>taxiId, 
 * <li>cost,
 * <li>duration,
 * <li>customerId,
 * <li>decision,
 * <li>fromAddress,
 * <li>fromZipCode,
 * <li>fromCity,
 * <li>toAddress,
 * <li>toZipCode,
 * <li>toCity
 * </ul>
 * <p>
 * @author  Team 4
 * @since   Academic Year 2016-2017 
 */
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

	/**
	 * Default constructor
	 * 
	 * @return RequestHandlingInfo
	 */
	public RequestHandlingInfo(){}

	/**
	 * This constructor is used from the service relative to start request handling
	 * 
	 * @param customerId type long
	 * @param taxiId type long
	 * 
	 * @return RequestHandlingInfo
	 */
	public RequestHandlingInfo(long customerId, long taxiId){
		this.customerId = customerId;
		this.taxiId = taxiId;
	}

	/**
	 * This constructor is used from the service relative to handling request 
	 * 
	 * @param reqId type long
	 * @param taxiId type long
	 * @param decision type String
	 * 
	 * @return RequestHandlingInfo
	 */
	public RequestHandlingInfo(long reqId, long taxiId, String decision){
		this.reqId = reqId;
		this.taxiId = taxiId;
		this.decision = decision;
	}

	/**
	 * This constructor is used from the service relative to creating the route of the request 
	 * 
	 * @param reqId type long
	 * @param fromAddress type String
	 * @param fromCity type String
	 * @param fromZipCode type int
	 * @param toAddress type String
	 * @param toCity type String
	 * @param toZipCode type int
	 * 
	 * @return RequestHandlingInfo
	 */
	public RequestHandlingInfo(long reqId, String fromAddress, String fromCity, 
			int fromZipCode, String toAddress, String toCity, int toZipCode){
		this.reqId = reqId;
		this.fromAddress = fromAddress;
		this.fromZipCode = fromZipCode;
		this.fromCity = fromCity;
		this.toAddress = toAddress;
		this.toZipCode = toZipCode;		
		this.toCity = toCity;		
	}

	/**
	 * This constructor is used from the service relative to stopping the request 
	 * 
	 * @param reqId type long
	 * @param taxiId type long
	 * @param cost type float
	 * @param duration type int
	 * 
	 * @return RequestHandlingInfo
	 */
	public RequestHandlingInfo(long reqId, long taxiId, float cost, int duration){
		this.reqId = reqId;
		this.taxiId = taxiId;
		this.cost = cost;
		this.duration = duration;
	}

	/**<h2>getReqId method</h2>
	 * @return RequestHandlingInfo's reqId
	 */
	public long getReqId() {
		return reqId;
	}

	/**<h2>setReqId method</h2>
	 * this method sets the given long to the attribute reqId of the caller object.<p>
	 * @param reqId type long
	 * @return void
	 */
	public void setReqId(long reqId) {
		this.reqId = reqId;
	}

	/**<h2>getTaxiId method</h2>
	 * @return RequestHandlingInfo's taxiId
	 */
	public long getTaxiId() {
		return taxiId;
	}

	/**<h2>setTaxiId method</h2>
	 * this method sets the given long to the attribute taxiId of the caller object.<p>
	 * @param taxiId type long
	 * @return void
	 */
	public void setTaxiId(long taxiId) {
		this.taxiId = taxiId;
	}

	/**<h2>getCost method</h2>
	 * @return RequestHandlingInfo's cost
	 */
	public float getCost() {
		return cost;
	}

	/**<h2>setCost method</h2>
	 * this method sets the given float to the attribute cost of the caller object.<p>
	 * @param cost type float
	 * @return void
	 */
	public void setCost(float cost) {
		this.cost = cost;
	}

	/**<h2>getDuration method</h2>
	 * @return RequestHandlingInfo's duration
	 */
	public int getDuration() {
		return duration;
	}

	/**<h2>setDuration method</h2>
	 * this method sets the given int to the attribute duration of the caller object.<p>
	 * @param duration type integer
	 * @return void
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**<h2>getCustomerId method</h2>
	 * @return RequestHandlingInfo's customerId
	 */
	public long getCustomerId() {
		return customerId;
	}

	/**<h2>setCustomerId method</h2>
	 * this method sets the given long to the attribute customerId of the caller object.<p>
	 * @param customerId type long
	 * @return void
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	/**<h2>getDecision method</h2>
	 * @return RequestHandlingInfo's decision
	 */
	public String getDecision() {
		return decision;
	}

	/**<h2>setDecision method</h2>
	 * this method sets the given String to the attribute decision of the caller object.<p>
	 * @param decision type String
	 * @return void
	 */
	public void setDecision(String decision) {
		this.decision = decision;
	}

	/**<h2>getFromAddress method</h2>
	 * @return RequestHandlingInfo's fromAddress
	 */
	public String getFromAddress() {
		return fromAddress;
	}

	/**<h2>setFromAddress method</h2>
	 * this method sets the given String to the attribute fromAddress of the caller object.<p>
	 * @param fromAddress type String
	 * @return void
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	/**<h2>getFromZipCode method</h2>
	 * @return RequestHandlingInfo's fromZipCode
	 */
	public int getFromZipCode() {
		return fromZipCode;
	}

	/**<h2>setFromZipCode method</h2>
	 * this method sets the given integer to the attribute fromZipCode of the caller object.<p>
	 * @param fromZipCode type int
	 * @return void
	 */
	public void setFromZipCode(int fromZipCode) {
		this.fromZipCode = fromZipCode;
	}

	/**<h2>getFromCity method</h2>
	 * @return RequestHandlingInfo's fromCity
	 */
	public String getFromCity() {
		return fromCity;
	}

	/**<h2>setFromCity method</h2>
	 * this method sets the given String to the attribute fromCity of the caller object.<p>
	 * @param fromCity type String
	 * @return void
	 */
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	/**<h2>getToAddress method</h2>
	 * @return RequestHandlingInfo's toAddress
	 */
	public String getToAddress() {
		return toAddress;
	}

	/**<h2>setToAddress method</h2>
	 * this method sets the given String to the attribute toAddress of the caller object.<p>
	 * @param toAddress type String
	 * @return void
	 */
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	/**<h2>getToZipCode method</h2>
	 * @return RequestHandlingInfo's toZipCode
	 */
	public int getToZipCode() {
		return toZipCode;
	}

	/**<h2>setToZipCode method</h2>
	 * this method sets the given integer to the attribute toZipCode of the caller object.<p>
	 * @param toZipCode type int
	 * @return void
	 */
	public void setToZipCode(int toZipCode) {
		this.toZipCode = toZipCode;
	}

	/**<h2>getToCity method</h2>
	 * @return RequestHandlingInfo's toCity
	 */
	public String getToCity() {
		return toCity;
	}

	/**<h2>setToCity method</h2>
	 * this method sets the given String to the attribute toCity of the caller object.<p>
	 * @param toCity type String
	 * @return void
	 */
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	/**getCustomer method
	 * This method is used for getting a Customer object from a RequestHandlingInfo object<p>
	 * 
	 * @param em type EntityManager
	 * @return Customer
	 */
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

	/**getTaxi method
	 * This method is used for getting a Taxi object from a RequestHandlingInfo object<p>
	 * 
	 * @param em type EntityManager
	 * @return Taxi
	 */
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

	/**getRequest method
	 * This method is used for getting a Request object from a RequestHandlingInfo object<p>
	 * 
	 * @param em type EntityManager
	 * @return Request
	 */
	public Request getRequest(EntityManager em) {

		Request req = null;

		if (reqId != 0) {
			req = em.find(Request.class, (long)reqId);
		}
		else{
			return null;
		}

		return req;
	}
}
