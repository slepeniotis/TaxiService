package taxi.resource;

import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlRootElement;

import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.ISBN;
import com.mgiandia.library.domain.Publisher;

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

	public void setRoute(long routeId) {
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
	
	public Route getRoute(EntityManager em) {
		
		Route route = null;

		if (routeId != 0) {
			route = em.find(Route.class, routeId);
		}

		return route;
	}




}
