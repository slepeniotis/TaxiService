package taxi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import taxi.model.Request;
import taxi.model.Route;
import taxi.persistence.JPAUtil;
import taxi.utils.RequestStatus;

/**
* The StatisticsService class implements the functions regarding statistics production.
* 
* @author  Team 4
* @since   Academic Year 2016-2017 
*/
public class StatisticsService {

	EntityManager em;

	//getting the current entity manager
	public StatisticsService() {
		em = JPAUtil.getCurrentEntityManager();
	}
	
	/** produceStatistcs method
	 * This method is overloaded
	 * <p>
	 * This version of method, produces a float which holds the total of all the commissions 
	 * received from the company within a specific date range.
	 * The method gets as inputs:
	 * <ul>
	 * <li>the selection (1 = date range, 2 = from city, 3 = to city)
	 * <li>the from range (Date) and
	 * <li>to range (Date)
	 * </ul>
	 * <b>Note:</b> For this version of method, only selection = 1 is applicable
	 * <p>
	 * The method:
	 * <ul>
	 * <li>At first, all the requests made within the given range are fetched from Request table
	 * <li>In case the result set is not empty, the route connected with the request is fetched,
	 * <li>from the route, the commission is fetched and summarized with the final result.
	 * <p>
	 * @param selection type int
	 * @param fromRange type Date
	 * @param toRange type Date
	 * @return double
	 */
	public double produceStatistics(int selection, Date fromRange, Date toRange){
		if(selection == 0 || fromRange == null || toRange == null)
			return 0;

		double sum = 0;
		if(selection == 1){			

			/* we could alternatively fetch the contents of Route and then check the dates from the object Request
			 * we prefer this way instead, to avoid the overhead
			 */
			Query query = em.createQuery("select r from Request r where r.status like :stts and r.dateTime "
					+ "between :frRange and :tRange");
			
				query.setParameter("frRange", fromRange);			
				query.setParameter("tRange", toRange);
				query.setParameter("stts", RequestStatus.DONE);			

			List<Request> reqrslt = query.getResultList();		
			if(!reqrslt.isEmpty())
				for(Request r : reqrslt) {
					if(r.getRoute() != null){
						sum += r.getRoute().getCommision();
					}						
				}
			}
		return sum;
		}

	/** produceStatistcs method
	 * This method is overloaded
	 * <p>
	 * This version of method, returns the total number of total routes made from a specific city or to a specific city
	 * he method gets as inputs:
	 * <ul>
	 * <li>the selection (1 = date range, 2 = from city, 3 = to city)
	 * <li>the city
	 * </ul>
	 * <b>Note:</b> For this version of method, only selection = 2 & 3 are applicable
	 * <p>
	 * The method:
	 * <ul>
	 * <li>Checks the selection
	 * <li>according to the selection (2 or 3) the method fetches from route table all the routes made from or to a specific city accordingly.
	 * <li>in case the result set is empty, then the result is 0. Else... 
	 * <li>from those routes, the request connected with them (if any) is fetched and counted.
	 * </ul>
	 * <p>
	 * @param selection type int
	 * @param city type String
	 * @return int
	 */ 
	public int produceStatistics(int selection, String city){
		String result="";
		List<Route> routerslt = new ArrayList(0);	
		if(selection == 0 || city == null || city == " ")
			return 0;		

		if(selection == 2){

			Query query = em.createQuery("select r from Route r where r.fromCity LIKE :ct");
			query.setParameter("ct", city);
			routerslt = query.getResultList();	
			if(!routerslt.isEmpty()){
				result += "Total requests from city " + city + " is: " + routerslt.size() + "\n";
				for(Route r : routerslt)
					result += "Request ID: " + r.getReq().getId() + "\n";
			}
			else{
				result += "Total requests from city " + city + " is: 0\n";
			}
		}
		else if(selection == 3){
			Query query = em.createQuery("select r from Route r where r.toCity LIKE :ct");
			query.setParameter("ct", city);		
			routerslt = query.getResultList();	
			if(!routerslt.isEmpty()){
				result += "Total requests to city " + city + " is: " + routerslt.size() + "\n";
				for(Route r : routerslt)
					result += "Request ID: " + r.getReq().getId() + "\n";
			}
			else{
				result += "Total requests to city " + city + " is: 0\n";
			}
		}

		System.out.println(result);
		return routerslt.size();
	}
}
