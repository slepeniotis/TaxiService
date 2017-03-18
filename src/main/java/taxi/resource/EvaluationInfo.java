package taxi.resource;

import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlRootElement;

import taxi.model.Route;

@XmlRootElement
public class EvaluationInfo {

	private long id;

	private long routeId;

	private int rating;

	private String comment;			

	public EvaluationInfo() {

	}

	public EvaluationInfo(long id, long routeId, int rating, String comment){
		this(routeId, rating, comment);
		this.id = id;
	}

	public EvaluationInfo(long routeId, int rating, String comment) {
		super();
		this.routeId = routeId;
		this.rating = rating;
		this.comment = comment;
	}

	public long getRouteId() {
		return routeId;
	}

	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Route getRoute(EntityManager em) {
		
		Route route = null;

		if (routeId != 0) {
			route = em.find(Route.class, routeId);
		}

		return route;
	}




}
