package taxi.resource;

import javax.xml.bind.annotation.XmlRootElement;

import taxi.model.Route;

@XmlRootElement
public class EvaluationInfo {

	private long id;
	
	private Route route;
			
			private int rating;

			private String comment;			
		
		public EvaluationInfo() {

		}

		public EvaluationInfo(long id, Route route, int rating, String comment){
			this.id = id;
		}

		public EvaluationInfo(Route route, int rating, String comment) {
			super();
			this.route = route;
			this.rating = rating;
			this.comment = comment;
		}

		public Route getRoute() {
			return route;
		}

		public void setRoute(Route route) {
			this.route = route;
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

		
		
		
}
