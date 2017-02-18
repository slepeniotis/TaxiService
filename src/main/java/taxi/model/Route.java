package taxi.model;

import javax.persistence.*;

@Entity
@Table(name = "Route")
public class Route {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "from", length = 256, nullable = false)
	private String from;
	
	@Column(name = "to", length = 256, nullable = false)
	private String to;
	
	@Column(name = "duration", length = 5, nullable = true)
	private int duration;
	
	@Column(name = "cost", length = 5, nullable = true)
	private float cost;
	
	@Column(name = "commision", length = 5, nullable = true)
	private float commision;
	
	@OneToOne
	@JoinColumn(name="evalID")
	private Evaluation eval;
	
	public Route(){}
	public Route(String from, String to, int duration, float cost, float commision) {		
		this.from = from;
		this.to = to;
		this.duration = duration;
		this.cost = cost;
		this.calculateCommision();
		}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setCommision(float commision) {
		this.commision = commision;
	}
	
	public Evaluation getEval() {
		return eval;
	}

	public void setEval(Evaluation eval) {
		this.eval = eval;
	}

	public void calculateCommision(){
		
	}
	
	public void calculateStatistics(){
		
	}
}
