package taxi.model;

//imports for using persistence
import javax.persistence.*;

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
	private String fromZipCode;
	
	@Column(name = "toAddress", length = 256, nullable = false)
	private String toAddress;
	
	@Column(name = "toCity", length = 256, nullable = false)
	private String toCity;
	
	@Column(name = "toZipCode", length = 256, nullable = false)
	private String toZipCode;
	
	
	@Column(name = "duration", length = 5, nullable = true)
	private int duration;
	
	@Column(name = "cost", length = 5, nullable = true)
	private float cost;
	
	@Column(name = "commision", length = 5, nullable = true)
	private float commision;
	
	//each route can have only one evaluation or none
	//fetch type lazy does not fetch the object evaluation
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="evalID")
	private Evaluation eval;
	
	//constructors for Route
	public Route(){}
	public Route(String fromAddress, String toAddress, String fromCity, String toCity, String fromZipCode, String toZipCode) {	
		//ID is auto generated, so no need to include it here
		//Route will have origin and destination
		//duration, cost and commission have to be zero since the request is not yet done
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.duration = 0;
		this.cost = 0;
		this.calculateCommision();
		this.fromCity = fromCity;
		this.toCity = toCity;
		this.fromZipCode = fromZipCode;
		this.toZipCode = toZipCode;
		}
	
	//get/set methods in order to have access in private attributes
	public long getId() {
		return id;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	
	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	
	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return fromCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	
	public String getFromZipCode() {
		return fromZipCode;
	}

	public void setFromZipCode(String fromZipCode) {
		this.fromZipCode = fromZipCode;
	}
	
	public String getToZipCode() {
		return toZipCode;
	}

	public void setToZipCode(String toZipCode) {
		this.toZipCode = toZipCode;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public float getCost() {
		return cost;
	}
	
	public void setCost(float cost) {
		if (cost > 0) {
			this.cost = cost;
		}
		else {
			this.cost = 0;
		}
	}
	
	public Evaluation getEval() {
		return eval;
	}

	public void setEval(Evaluation eval) {
		this.eval = eval;
	}
		
	//methods for calculating commision and statistics
	public void calculateCommision(){
		if (this.cost == 0){
			this.commision = 0;
		}
		else{
			this.commision = this.cost*0.05f;
		}
	}
	
	public void calculateStatistics(){
		
	}
	
	//override of toString method from Object
		@Override
		public String toString() {
	        String temp = this.id + " " + this.fromAddress + " " + this.toAddress + " " + this.fromCity + " " + this.toCity + " " + this.fromZipCode + " " + this.toZipCode + " " + this.duration + " " + this.cost + " " + this.commision;
			
	        if (this.eval != null){
	        	temp += " (" + this.eval.toString() + ")";
	        }
	        
	        return temp;
	    }	
}
