package taxi.model;

//imports for using persistence, List, ArrayList, date
import java.util.List;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;


//Declaring table id DB with name Taxi
@Entity
@Table(name = "Taxi")
public class Taxi {
	
	//Declaring Primary surrogate Key as autoincrement
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//Declaring columns with specific maximum length of characters and NULL/NOT NULL 
	@Column(name = "carModel", length = 10, nullable = false)
	private String carModel;
	
	@Column(name = "carType", length = 10, nullable = false)
	private String carType;
	
	@Column(name = "licensePlate", length = 7, nullable = false)
	private String licensePlate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "carModelDate", nullable = false)
	private Date carModelDate;
	
	@Column(name = "location", length = 30, nullable = false)
	private String location;
		
	@Column(name = "status", nullable = false)
	private boolean status;
	
	//every taxi can accept several requests
	//fetch type lazy does not fetch all the list. fetching is done only if we ask for it
	//cascade type used here is ALL. Merge, persist, detach, remove, refresh 
	@OneToMany(mappedBy="taxi",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Request> accepts = new ArrayList<Request>();
	
	//constructors for Taxi
	public Taxi(){}
	public Taxi(String carModel, String carType, String licensePlate, Date carModelDate, String location) {
		//ID is auto generated, so no need to include it here
		this.carModel = carModel;
		this.carType = carType;
		this.licensePlate = licensePlate;
		this.carModelDate = carModelDate;
		this.location = location;
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
	
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public Date getCarModelDate() {
		return this.carModelDate;
	}
	
	public void setCarModelDate(Date carModelDate) {
		this.carModelDate = carModelDate;
	}
	
	public List<Request> getRequest() {
		return this.accepts;
	}
	
	public void addRequest(Request req) {
		this.accepts.add(req);
	}
	
	//override of toString method from Object
		@Override
		public String toString() {
	        String temp = this.id + " " + this.carModel + " " + this.carType + 
	        		" " + this.licensePlate + " " + this.carModelDate + " " + this.location + " " + this.status;	        
	        
	        for(Request r : accepts) {
	            temp += " (" + r.toString() + ")";
	        }  
	        
	        return temp;
	    }	
	
}