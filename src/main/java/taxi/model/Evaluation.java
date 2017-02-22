package taxi.model;

//imports for using persistence, date.
import java.util.Date;
import javax.persistence.*;

/**
 * 
 * @author nyxteridas
 * 
 * μέθοδος για εμφάνιση του evaluation
 *
 */

//Declaring table id DB with name Evaluation
@Entity
@Table(name = "Evaluation")
public class Evaluation {
	
	//Declaring Primary surrogate Key as autoincrement
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//Declaring columns with specific maximum length of characters and NULL/NOT NULL 
	@Column(name = "rating", length = 5, nullable = false)
	private int rating;
	
	@Column(name = "comment", length = 512, nullable = false)
	private String comment;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = false)
	private Date dateOfEval;
	
	//constructors for Evaluation
	public Evaluation() {}
	public Evaluation(int rating, String comment, Date dateOfEval) {
		//ID is auto generated, so no need to include it here
		this.rating = rating;
		this.comment = comment;
		this.dateOfEval = dateOfEval;
	}

	//get/set methods in order to have access in private attributes
	public long getId() {
		return this.id;
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
	
	//override of toString method from Object
		@Override
		public String toString() {
	        return this.id + " " + this.rating + " " + this.comment + " " + this.dateOfEval;
	    }	

}