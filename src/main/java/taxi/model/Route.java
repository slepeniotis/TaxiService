package taxi.model;


public class Route {
	
	private int id;
	private String from;
	private String to;
	private int duration;
	private float cost;
	private float commision;
	private Evaluation eval;
	
	public Route(int id, String from, String to, int duration, float cost, float commision) {
		
		this.id = id;
		this.from = from;
		this.to = to;
		this.duration = duration;
		this.cost = cost;
		this.commision = commision;
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
