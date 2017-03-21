package taxi.model;

//imports for using persistence, List, ArrayList, date
import java.util.List;
import javax.persistence.*;
import java.util.ArrayList;


/**
 * The Taxi class implements all taxis using the taxi service.
 * <p>
 * Every taxi has a list of requests received, since it can receive several requests
 * It also has:
 * <ul>
 * <li>carModel, 
 * <li>carType,
 * <li>licensePlate,
 * <li>carModelDate,
 * <li>locationLat,
 * <li>locationLon,
 * <li>status,
 * </ul>
 * <p>
 * @author  Team 4
 * @since   Academic Year 2016-2017 
 */

//Declaring table id DB with name Taxi
@Entity
@Table(name = "Taxi")
public class Taxi {

	//Declaring Primary surrogate Key as autoincrement
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	//Declaring columns with specific maximum length of characters and NULL/NOT NULL 
	@Column(name = "carModel", length = 30, nullable = false)
	private String carModel;

	@Column(name = "carType", length = 10, nullable = false)
	private String carType;

	@Column(name = "licensePlate", length = 7, nullable = false)
	private String licensePlate;

	@Column(name = "carModelDate", nullable = false)
	private String carModelDate;

	@Column(name = "locationLat", length = 30, nullable = false)
	private double locationLat;

	@Column(name = "locationLon", length = 30, nullable = false)
	private double locationLon;

	@Column(name = "status", nullable = false)
	private boolean status;

	//every taxi can accept several requests
	//fetch type lazy does not fetch all the list. fetching is done only if we ask for it
	//cascade type used here is ALL. Merge, persist, detach, remove, refresh 
	@OneToMany(mappedBy="taxi",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Request> accepts = new ArrayList<Request>();

	/**<h2>The default constructor</h2>
	 * @return an empty Taxi object
	 */
	public Taxi(){}

	/**<h2>The main constructor</h2>
	 * The status of the Taxi is always true (free) at the beginning.
	 * <p>
	 * @param carModel car model of the taxi, type String
	 * @param carType car type of the taxi, type String
	 * @param licensePlate license plate of the taxi, type String
	 * @param carModelDate production date of the taxi, type String
	 * @param locationLat latitude of the taxi, type double
	 * @param locationLon longtitude of the taxi, type double
	 * @return a Taxi object
	 */
	public Taxi(String carModel, String carType, String licensePlate, String carModelDate, double locationLat, double locationLon) {
		//ID is auto generated, so no need to include it here
		this.carModel = carModel;
		this.carType = carType;
		this.licensePlate = licensePlate;
		this.carModelDate = carModelDate;
		this.locationLat = locationLat;
		this.locationLon = locationLon;
		this.status = true;
	}

	/**<h2>getId method</h2>
	 * @return taxi's ID
	 */
	public long getId() {
		return this.id;
	}

	/**<h2>getCarModel method</h2>
	 * @return taxi's car model
	 */
	public String getCarModel() {
		return this.carModel;
	}

	/**<h2>setCarModel method</h2>
	 * this method sets the given String to the attribute carModel of the caller object.<p>
	 * @param carModel type String
	 * @return void
	 */
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	/**<h2>getCarType method</h2>
	 * @return taxi's car type
	 */
	public String getCarType() {
		return this.carType;
	}

	/**<h2>setCarType method</h2>
	 * this method sets the given String to the attribute carType of the caller object.<p>
	 * @param carType type String
	 * @return void
	 */
	public void setCarType(String carType) {
		this.carType = carType;
	}

	/**<h2>getLicensePlate method</h2>
	 * @return taxi's license plate
	 */
	public String getLicensePlate() {
		return this.licensePlate;
	}

	/**<h2>setLicensePlate method</h2>
	 * this method sets the given String to the attribute licensePlate of the caller object.<p>
	 * @param licensePlate type String
	 * @return void
	 */
	public void setLicensePlate(String licensePlate) {		
		this.licensePlate = licensePlate;
	}

	/**<h2>getLocationLat method</h2>
	 * @return taxi's latitude
	 */
	public double getLocationLat() {
		return this.locationLat;
	}

	/**<h2>setLocationLat method</h2>
	 * this method sets the given double to the attribute locationLat of the caller object.<p>
	 * @param locationLat type double
	 * @return void
	 */
	public void setLocationLat(double locationLat) {
		this.locationLat = locationLat;
	}

	/**<h2>getLocationLon method</h2>
	 * @return taxi's longtitude
	 */
	public double getLocationLon() {
		return locationLon;
	}

	/**<h2>setLocationLon method</h2>
	 * this method sets the given double to the attribute locationLon of the caller object.<p>
	 * @param locationLon type double
	 * @return void
	 */
	public void setLocationLon(double locationLon) {
		this.locationLon = locationLon;
	}

	/**<h2>getStatus method</h2>
	 * @return taxi's status
	 */
	public boolean getStatus() {
		return this.status;
	}

	/**<h2>setStatus method</h2>
	 * this method sets the given boolean to the attribute status of the caller object.<p>
	 * @param status type boolean
	 * @return void
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**<h2>getCarModelDate method</h2>
	 * @return taxi's date of production
	 */
	public String getCarModelDate() {
		return this.carModelDate;
	}

	/**<h2>setCarModelDate method</h2>
	 * this method sets the given String to the attribute carModelDate of the caller object.<p>
	 * @param carModelDate type String
	 * @return void
	 */
	public void setCarModelDate(String carModelDate) {
		this.carModelDate = carModelDate;
	}

	/**<h2>getRequest method</h2>
	 * @return a list of taxi's requests
	 */
	public List<Request> getRequest() {
		return this.accepts;
	}

	/**<h2>addRequest method</h2>
	 * this method adds the given Request object to the list of Requests of the caller object.<p>
	 * @param req type Request
	 * @return void
	 */
	public void addRequest(Request accepts) {
		this.accepts.add(accepts);
	}

	/**<h2>toString method</h2>
	 * @return a string including all the information of a Taxi object
	 */
	@Override
	public String toString() {
		String temp = this.id + " " + this.carModel + " " + this.carType + 
				" " + this.licensePlate + " " + this.carModelDate + " " + this.locationLat + " " + this.locationLon + " " + this.status;	        

		for(Request r : accepts) {
			temp += " (" + r.toString() + ")";
		}  

		return temp;
	}

	/**<h2>equals method</h2>
	 * @param obj type Object
	 * @return a boolean whether the given object is the same with the caller object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Taxi other = (Taxi) obj;
		if (carModel == null) {
			if (other.carModel != null)
				return false;
		} 
		else if (!carModel.equals(other.carModel))
			return false;
		if (carType == null) {
			if (other.carType != null)
				return false;
		} 
		else if (!carType.equals(other.carType))
			return false;
		if (licensePlate == null) {
			if (other.licensePlate != null)
				return false;
		} 
		else if (!licensePlate.equals(other.licensePlate))
			return false;
		if (carModelDate == null) {
			if (other.carModelDate != null)
				return false;
		} 
		else if (!carModelDate.equals(other.carModelDate))
			return false;	
		return true;
	}

}