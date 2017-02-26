package taxi.utils;

/* the request has 3 different states:
 * Pending: the request has not been accepted yet by the taxi
 * Ongoing: the request is ongoing
 * Done: the request has ended
 */
public enum RequestStatus {

	PENDING, 
	ONGOING, 
	DONE
}
