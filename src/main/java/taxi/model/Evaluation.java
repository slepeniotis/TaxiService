package taxi.model;

import java.util.Date;

import javax.persistence.*;

/**
 * 
 * @author nyxteridas
 * date μόνο ως ημερομηνία
 * 
 * μέθοδος για εμφάνιση του evaluation
 *
 */

@Entity
@Table(name = "Evaluation")
public class Evaluation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "rating", length = 5, nullable = false)
	private int rating;
	
	@Column(name = "comment", length = 512, nullable = false)
	private String comment;
	
	@Column(name = "date", length = 10, nullable = false)
	private Date dateOfEval;

	public Evaluation() {}
	public Evaluation(int rating, String comment, Date dateOfEval) {
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

}