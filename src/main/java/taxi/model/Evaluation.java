package taxi.model;

import java.util.Date;

/**
 * 
 * @author nyxteridas
 * το ID πρέπει να είναι autoincrement
 * αλλαγή των vectors σε λίστες
 * date μόνο ως ημερομηνία
 * 
 * μέθοδος για εμφάνιση του evaluation
 *
 */

public class Evaluation {
	private int id;
	private int rating;
	private String comment;
	private Date dateOfEval;

	public Evaluation(int id, int rating, String comment, Date dateOfEval) {
		this.id = id;
		this.rating = rating;
		this.comment = comment;
		this.dateOfEval = dateOfEval;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Date getDateOfEval() {
		return this.dateOfEval;
	}

	public void setDateOfEval(Date dateOfEval) {
		this.dateOfEval = dateOfEval;
	}

	public void newEvaluation() {
	
	}

}