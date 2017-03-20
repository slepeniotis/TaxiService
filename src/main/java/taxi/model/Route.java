package taxi.model;

//imports for using persistence
import javax.persistence.*;

/**
* The Route class implements all routes requested by customers, and connected with their requests.
* <p>
* Every Route has only one request and only one evaluation
* It also has:
* <ul>
* <li>the the origin address of the route,
* <li>the the origin city of the route,
* <li>the the origin zip code of the route,
* <li>the the destination address of the route,
* <li>the the destination city of the route,
* <li>the the destination zip code of the route, 
* <li>the the duration of the route,
* <li>the the cost of the route and
* <li>the the commission for the taxi service
* </ul>
* 
* @author  Team 4
* @since   Academic Year 2016-2017 
*/

//Declaring table id DB with name Route
@Entity
@Table(name = "Route")
public class Route {

	//Declaring Primary surrogate Key as autoincrement
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	//Declaring columns with specific maximum length of characters and NULL/NOT NULL 
	@Column(name = "fromAddress", length = 256, nullable = false)
	private String fromAddress;

	//Declaring columns with specific maximum length of characters and NULL/NOT NULL 
	@Column(name = "fromCity", length = 256, nullable = false)
	private String fromCity;

	//Declaring columns with specific maximum length of characters and NULL/NOT NULL 
	@Column(name = "fromZipCode", length = 256, nullable = false)
	private int fromZipCode;

	@Column(name = "toAddress", length = 256, nullable = false)
	private String toAddress;

	@Column(name = "toCity", length = 256, nullable = false)
	private String toCity;

	@Column(name = "toZipCode", length = 256, nullable = false)
	private int toZipCode;	

	@Column(name = "duration", length = 5, nullable = true)
	private int duration;

	@Column(name = "cost", length = 5, nullable = true)
	private float cost;

	@Column(name = "commision", length = 5, nullable = true)
	private float commision;

	//each route can have only one evaluation or none
	//fetch type lazy does not fetch the object evaluation
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.REMOVE)
	@JoinColumn(name="evalID")
	private Evaluation eval;
	
	//each route has one request
	@OneToOne(optional=false, mappedBy="route", fetch=FetchType.EAGER, targetEntity=Request.class)
	private Request req; 

	/**<h2>The default constructor</h2>
	 * @return an empty Route object
	 */
	public Route(){}
	
	/**<h2>The main constructor</h2>
	 * Cost and duration is always zero at the beginning.
	 * Commission is always calculated from the cost.
	 * <p>
	 * @param fromAddress origin address, type String
	 * @param toAddress destination address, type String
	 * @param fromCity origin city, type String
	 * @param toCity destination city, type String
	 * @param fromZipCode origin zip code, type int
	 * @param toZipCode destination zip code, type int
	 * @param req the request for which this route was created, type Request
	 * 
	 * @return a Route object
	 */
	public Route(String fromAddress, String toAddress, String fromCity, String toCity, int fromZipCode, int toZipCode, Request req) {	
		//ID is auto generated, so no need to include it here
		//Route will have origin and destination
		//duration, cost and commission have to be zero since the request is not yet done
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.fromCity = fromCity;
		this.toCity = toCity;
		this.fromZipCode = fromZipCode;
		this.toZipCode = toZipCode;
		this.duration = 0;
		this.cost = 0;
		this.calculateCommision();
		this.req = req;
	}

	/**<h2>getId method</h2>
	 * @return route's ID
	 */
	public long getId() {
		return id;
	}

	/**<h2>getFromAddress method</h2>
	 * @return route's origin address
	 */
	public String getFromAddress() {
		return fromAddress;
	}

	/**<h2>setFromAddress method</h2>
	 * this method sets the given String to the attribute fromAddress of the caller object.
	 * @param fromAddress type String
	 * @return void
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	/**<h2>getToAddress method</h2>
	 * @return route's destination address
	 */
	public String getToAddress() {
		return toAddress;
	}

	/**<h2>setToAddress method</h2>
	 * this method sets the given String to the attribute toAddress of the caller object.
	 * @param toAddress type String
	 * @return void
	 */
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	/**<h2>getFromCity method</h2>
	 * @return route's origin city
	 */
	public String getFromCity() {
		return fromCity;
	}

	/**<h2>setFromCity method</h2>
	 * this method sets the given String to the attribute fromCity of the caller object.
	 * @param fromCity type String
	 * @return void
	 */
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	/**<h2>getToCity method</h2>
	 * @return route's destination city
	 */
	public String getToCity() {
		return toCity;
	}

	/**<h2>setToCity method</h2>
	 * this method sets the given String to the attribute toCity of the caller object.
	 * @param toCity type String
	 * @return void
	 */
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	/**<h2>getFromZipCode method</h2>
	 * @return route's origin zip code
	 */
	public int getFromZipCode() {
		return fromZipCode;
	}

	/**<h2>setFromZipCode method</h2>
	 * this method sets the given integer to the attribute fromZipCode of the caller object.
	 * @param fromZipCode type int
	 * @return void
	 */
	public void setFromZipCode(int fromZipCode) {
		this.fromZipCode = fromZipCode;
	}

	/**<h2>getToZipCode method</h2>
	 * @return route's destination zip code
	 */
	public int getToZipCode() {
		return toZipCode;
	}

	/**<h2>setToZipCode method</h2>
	 * this method sets the given integer to the attribute toZipCode of the caller object.
	 * @param toZipCode type int
	 * @return void
	 */
	public void setToZipCode(int toZipCode) {
		this.toZipCode = toZipCode;
	}

	/**<h2>getDuration method</h2>
	 * @return route's duration
	 */
	public int getDuration() {
		return duration;
	}

	/**<h2>setDuration method</h2>
	 * this method sets the given integer to the attribute duration of the caller object.
	 * @param duration type int
	 * @return void
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**<h2>getCost method</h2>
	 * @return route's cost
	 */
	public float getCost() {
		return cost;
	}
	
	/**<h2>setCost method</h2>
	 * this method sets the given float to the attribute cost of the caller object.
	 * In case the cost parameter is less or equal to zero, the cost of the caller object is set to 0. 
	 * @param cost type float
	 * @return void
	 */
	public void setCost(float cost) {
		if (cost > 0) {
			this.cost = cost;
		}
		else {
			this.cost = 0;
		}
	}
	
	/**<h2>getCommision method</h2>
	 * @return route's commission
	 */
	public float getCommision() {
		return commision;
	}	

	/**<h2>getEval method</h2>
	 * @return route's evaluation
	 */
	public Evaluation getEval() {
		return eval;
	}

	/**<h2>setEval method</h2>
	 * this method sets the given evaluation object to the attribute eval of the caller object. 
	 * @param eval type Evaluation
	 * @return void
	 */
	public void setEval(Evaluation eval) {
		this.eval = eval;
	}
	
	/**<h2>getReq method</h2>
	 * @return route's request
	 */
	public Request getReq() {
		return req;
	}
	
	/**<h2>setReq method</h2>
	 * this method sets the given request object to the attribute req of the caller object. 
	 * @param req type Request
	 * @return void
	 */
	public void setReq(Request req) {
		this.req = req;
	}

	/**<h2>calculateCommision method</h2>
	 * This method calculates the commission based on the cost of the route.
	 * In case the cost is 0, then commission is also set to 0. 
	 * @return void
	 */
	public void calculateCommision(){
		if (this.cost == 0){
			this.commision = 0;
		}
		else{
			this.commision = this.cost*0.05f;
		}
	}

	/**<h2>toString method</h2>
	 * @return a string including all the information of a Request object
	 */
	@Override
	public String toString() {
		String temp = this.id + " " + this.fromAddress + this.fromCity + " " + this.fromZipCode + " \n" + this.toAddress + " " + this.toCity + " " + this.toZipCode + " \n" + this.duration + " " + this.cost + " " + this.commision;

		if (this.eval != null){
			temp += "\n (" + this.eval.toString() + ")";
		}

		return temp;
	}	
}
