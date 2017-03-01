package taxi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import taxi.model.Request;
import taxi.model.Route;
import taxi.persistence.JPAUtil;

public class StatisticsService {

	EntityManager em;

	//getting the current entity manager
	public StatisticsService() {
		em = JPAUtil.getCurrentEntityManager();
	}
	
	/* produceStatistcs method
	 * this method is overloaded
	 * 
	 * This version of method, produces a float sum with all the commisions received 
	 * from the company within a specific date range
	 * The method gets as inputs the selection 
	 *                   (selection 1 = date range, 2 = from city, 3 = to city
	 * and the from range-to range (Dates)
	 * 
	 * we check if any of the inputs is empty/null
	 * then we retrieve from Request table all the requests made between this range
	 * we run into the list of the results (in case it is not empty),
	 * we check if any route is connected with this request,
	 * and then summarize its commision 
	 * 
	 */
	public float produceStatistics(int selection, Date fromRange, Date toRange){
		if(selection == 0 || fromRange == null || toRange == null)
			return 0;

		float sum = 0;
		if(selection == 1){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date d;

			/* we could alternatively fetch the contents of Route and then check the dates from the object Request
			 * we prefer this way instead, to avoid the overhead
			 */
			Query query = em.createQuery("select r from Request r where r.status like :stts and r.dateTime "
					+ "between :frRange and :tRange");
			try {
				d = sdf.parse("1/12/2012");
				query.setParameter("frRange", d);
				d = sdf.parse("31/12/2012");
				query.setParameter("tRange", d);
				query.setParameter(":stts", "DONE");
			}
			catch (ParseException e){
				System.out.println(e.getStackTrace());
				return 0;
			}

			List<Request> reqrslt = query.getResultList();		
			if(!reqrslt.isEmpty())
				for(Request r : reqrslt) {
					if(r.getRoute() != null)
						sum += r.getRoute().getCommision();
				}
		}
		return sum;

	}

	/* produceStatistcs method
	 * this method is overloaded
	 * 
	 * This version of method, returns the total number of total routes made
	 * from a specific city or to a specific city
	 * The method gets as inputs the selection 
	 *                      (selection 1 = date range, 2 = from city, 3 = to city
	 * and the city name
	 * 
	 * we check if any of the inputs is empty/null
	 * then check the selection
	 * we retrieve from Route table all the requests made from/to 
	 * a specific city (according to the selection)
	 * we run into the list of the results (in case it is not empty),
	 * and we return the total number
	 * 
	 * in case the result is empty, the total number of such routes is 0
	 */ 
	public int produceStatistics(int selection, String city){
		String result="";
		List<Route> routerslt = new ArrayList(0);	
		if(selection == 0 || city == null)
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
