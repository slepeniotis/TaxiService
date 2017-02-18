package taxi.model;
import java.util.ArrayList;

public class Route {
	
	private int id;
	private String from;
	private String to;
	private int duration;
	private float cost;
	private float commision;
	private ArrayList<Evaluation> Evaluation;
	
	public Route(int id, String from, String to, int duration, float cost, float commision,
			ArrayList<taxi.model.Evaluation> evaluation) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.duration = duration;
		this.cost = cost;
		this.commision = commision;
		Evaluation = evaluation;
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

	public void setCost(float cost) {
		this.cost = cost;
	}

	public float getCommision() {
		return commision;
	}

	public void setCommision(float commision) {
		this.commision = commision;
	}

	public ArrayList<Evaluation> getEvaluation() {
		return Evaluation;
	}

	public void setEvaluation(ArrayList<Evaluation> evaluation) {
		Evaluation = evaluation;
	}

	public void calculateCommision(){
		
	}
	
	public void calculateStatistics(){
		
	}
}
