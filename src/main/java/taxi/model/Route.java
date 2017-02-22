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
	@Column(name = "origin", length = 256, nullable = false)
	private String from;
	
	@Column(name = "destination", length = 256, nullable = false)
	private String to;
	
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
	public Route(String from, String to) {	
		//ID is auto generated, so no need to include it here
		//Route will have origin and destination
		//duration, cost and commission have to be zero since the request is not yet done
		this.from = from;
		this.to = to;
		this.duration = 0;
		this.cost = 0;
		this.calculateCommision();
		}
	
	//get/set methods in order to have access in private attributes
	public long getId() {
		return id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
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
	        String temp = this.id + " " + this.from + " " + this.to + " " + this.duration + " " + this.cost + " " + this.commision;
			
	        if (this.eval != null){
	        	temp += " (" + this.eval.toString() + ")";
	        }
	        
	        return temp;
	    }	
}
