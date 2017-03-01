package taxi.model;

//imports for using persistence, List, ArrayList, date
import java.util.List;
import javax.persistence.*;
import java.util.ArrayList;


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

	//constructors for Taxi
	public Taxi(){}
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

	//get/set methods in order to have access in private attributes
	public long getId() {
		return this.id;
	}

	public String getCarModel() {
		return this.carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCarType() {
		return this.carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getLicensePlate() {
		return this.licensePlate;
	}

	public void setLicensePlate(String licensePlate) {		
		this.licensePlate = licensePlate;
	}

	public double getLocationLat() {
		return this.locationLat;
	}

	public void setLocationLat(double locationLat) {
		this.locationLat = locationLat;
	}

	public double getLocationLon() {
		return locationLon;
	}

	public void setLocationLon(double locationLon) {
		this.locationLon = locationLon;
	}

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getCarModelDate() {
		return this.carModelDate;
	}

	public void setCarModelDate(String carModelDate) {
		this.carModelDate = carModelDate;
	}

	public List<Request> getRequest() {
		return this.accepts;
	}

	public void addRequest(Request accepts) {
		this.accepts.add(accepts);
	}

	//override of toString method from Object
	@Override
	public String toString() {
		String temp = this.id + " " + this.carModel + " " + this.carType + 
				" " + this.licensePlate + " " + this.carModelDate + " " + this.locationLat + " " + this.locationLon + " " + this.status;	        

		for(Request r : accepts) {
			temp += " (" + r.toString() + ")";
		}  

		return temp;
	}

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