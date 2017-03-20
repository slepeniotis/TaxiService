package taxi.model;

//imports for using persistence, date.
import java.util.Date;
import javax.persistence.*;

/**
* The Evaluation class implements all evaluations made by customers, for a pair of Request-Route.
* <p>
* Every evaluation consists of a rating, a comment and the date of evaluation
*  
* @author  Team 4
* @since   Academic Year 2016-2017 
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

	/**<h2>The default constructor</h2>
	 * @return an empty Evaluation object
	 */
	public Evaluation() {}
	
	/**<h2>The main constructor</h2>
	 * @param rating rating of the evaluation, type int
	 * @param comment comment of the evaluation, type String
	 * @param dateOfEval date that the evaluation, was made type Date 
	 * @return an Evaluation object
	 */
	public Evaluation(int rating, String comment, Date dateOfEval) {
		//ID is auto generated, so no need to include it here
		this.rating = rating;
		this.comment = comment;
		this.dateOfEval = dateOfEval;
	}

	/**<h2>getId method</h2>
	 * @return evaluation's ID
	 */
	public long getId() {
		return this.id;
	}

	/**<h2>getRating method</h2>
	 * @return evaluation's rating
	 */
	public int getRating() {
		return this.rating;
	}

	/**<h2>setRating method</h2>
	 * this method sets the given integer to the attribute rating of the caller object.
	 * @param rating type int
	 * @return void
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**<h2>getComment method</h2>
	 * @return evaluation's comment
	 */
	public String getComment() {
		return this.comment;
	}

	/**<h2>setComment method</h2>
	 * this method sets the given String to the attribute comment of the caller object.
	 * @param comment type String
	 * @return void
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**<h2>getDateOfEval method</h2>
	 * @return evaluation's date
	 */
	public Date getDateOfEval() {
		return this.dateOfEval;
	}

	/**<h2>setDateOfEval method</h2>
	 * this method sets the given Date to the attribute dateOfEval of the caller object.
	 * @param dateOfEval type Date
	 * @return void
	 */
	public void setDateOfEval(Date dateOfEval) {
		this.dateOfEval = dateOfEval;
	}

	/**<h2>toString method</h2>
	 * @return a string including all the information of an Evaluation object
	 */
	@Override
	public String toString() {
		return this.id + " " + this.rating + " " + this.comment + " " + this.dateOfEval.toString();
	}

}