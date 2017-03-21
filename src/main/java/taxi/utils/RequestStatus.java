package taxi.utils;

/**The enumerator is used within the request in order to define the 4 different states of a request:
 * <ul>
 * <li>Pending: the request has not been accepted yet by the taxi
 * <li>Ongoing: the request is ongoing
 * <li>Done: the request has ended
 * <li>Canceled: the request was canceled
 * </ul>
 * <p>
 * @author  Team 4
 * @since   Academic Year 2016-2017 
 */
public enum RequestStatus {

	PENDING, 
	ONGOING, 
	DONE,
	CANCELED
}
