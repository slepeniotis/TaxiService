package taxi.resource;

import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlRootElement;

import taxi.model.Route;

/**
 * The EvaluationInfo class implements the xml representation of an Evaluation, needed from EvaluationResource
 * <p>
 * Every EvaluationInfo has:
 * <ul>
 * <li>id
 * <li>routeId
 * <li>rating
 * <li>comment
 * </ul>
 * <p>
 * @author  Team 4
 * @since   Academic Year 2016-2017 
 */
@XmlRootElement
public class EvaluationInfo {

	private long id;

	private long routeId;

	private int rating;

	private String comment;			

	public EvaluationInfo() {}

	/**This constructor is used when the id of the object is known
	 * 
	 * @param id type long
	 * @param routeId type long
	 * @param rating type int
	 * @param comment type String
	 */
	public EvaluationInfo(long id, long routeId, int rating, String comment){
		this(routeId, rating, comment);
		this.id = id;
	}

	/**This constructor is used when the id of the object is unknown
	 * 
	 * @param routeId type long
	 * @param rating type int
	 * @param comment type String
	 */
	public EvaluationInfo(long routeId, int rating, String comment) {
		super();
		this.routeId = routeId;
		this.rating = rating;
		this.comment = comment;
	}

	/**<h2>getRouteId method</h2>
	 * @return the Route ID with which the evaluation is connected
	 */
	public long getRouteId() {
		return routeId;
	}

	/**<h2>setRouteId method</h2>
	 * this method sets the given long to the attribute routeId of the caller object.<p>
	 * @param routeId type long
	 * @return void
	 */
	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}

	/**<h2>getRating method</h2>
	 * @return Evaluation's rating
	 */
	public int getRating() {
		return rating;
	}

	/**<h2>setRating method</h2>
	 * this method sets the given integer to the attribute rating of the caller object.<p>
	 * @param rating type int
	 * @return void
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**<h2>getId method</h2>
	 * @return Evaluation's comment
	 */
	public String getComment() {
		return comment;
	}

	/**<h2>setComment method</h2>
	 * this method sets the given String to the attribute comment of the caller object.<p>
	 * @param comment type String
	 * @return void
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**<h2>getId method</h2>
	 * @return Evaluation's ID
	 */
	public long getId() {
		return id;
	}

	/**<h2>setId method</h2>
	 * this method sets the given long to the attribute id of the caller object.<p>
	 * @param id type long
	 * @return void
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**getRoute method
	 * This method is used for getting a Route object from a RouteInfo object<p>
	 * 
	 * @param em type EntityManager
	 * @return Route
	 */
	public Route getRoute(EntityManager em) {

		Route route = null;

		if (routeId != 0) {
			route = em.find(Route.class, routeId);
		}

		return route;
	}




}
