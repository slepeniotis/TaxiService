package taxi.resource;

import javax.ws.rs.Path;
import taxi.service.StatisticsService;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**StatisticsResource class implements the REST service for StatisticsService<p>
 * 
 * The base path for this class is /statistics
 * 
 * @author  Team 4
 * @since   Academic Year 2016-2017
 */
@Path("/statistics")
public class StatisticsResource extends AbstractResource {

	/**statisticsCommision method<p>
	 * receives GET requests at path /statistics/commision<p>
	 * This method is called when type 1 of statistics are needed. 
	 * It produces as output a StatisticsInfo object. This object is transformed to JSON. 
	 * The method also receives as query parameters the dates for which will produce the statistics.
	 * <p>
	 * The results are produced calling the service produceStatistics with the appropriate parameters.
	 * <p>
	 * @param fromRange type String
	 * @param toRange type String
	 * @return StatisticsInfo
	 */
	@GET
	@Path("/commision")
	@Produces(MediaType.APPLICATION_JSON)
	public StatisticsInfo statisticsCommision (@QueryParam("fromRange") String fromRange, @QueryParam("toRange") String toRange){

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dFrom = new Date();
		Date dTo = new Date();
		try {
			dFrom = sdf.parse(fromRange);
			dTo = sdf.parse(toRange);
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}		

		StatisticsService service = new StatisticsService();
		double stats = service.produceStatistics(1, dFrom, dTo);

		StatisticsInfo statisticsInfo = new StatisticsInfo(stats, 1);

		return statisticsInfo;
	}

	/**statisticsFromCity method<p>
	 * receives GET requests at path /statistics/fromCity<p>
	 * This method is called when type 2 of statistics are needed. 
	 * It produces as output a StatisticsInfo object. This object is transformed to JSON. 
	 * The method also receives as query parameter the origin city for which will produce the statistics.
	 * <p>
	 * The results are produced calling the service produceStatistics with the appropriate parameters.
	 * <p>
	 * @param city type String
	 * @return StatisticsInfo 
	 */
	@GET
	@Path("/fromCity")
	@Produces(MediaType.APPLICATION_JSON)
	public StatisticsInfo statisticsFromCity (@QueryParam("city") String city){

		StatisticsService service = new StatisticsService();
		int stats = service.produceStatistics(2, city);

		StatisticsInfo statisticsInfo = new StatisticsInfo(stats, 2);

		return statisticsInfo;
	}

	/**statisticsToCity method<p>
	 * receives GET requests at path /statistics/toCity<p>
	 * This method is called when type 3 of statistics are needed. 
	 * It produces as output a StatisticsInfo object. This object is transformed to JSON. 
	 * The method also receives as query parameter the destination city for which will produce the statistics.
	 * <p>
	 * The results are produced calling the service produceStatistics with the appropriate parameters.
	 * <p>
	 * @param city type String
	 * @return StatisticsInfo 
	 */
	@GET
	@Path("/toCity")
	@Produces(MediaType.APPLICATION_JSON)
	public StatisticsInfo statisticsToCity (@QueryParam("city") String city){

		StatisticsService service = new StatisticsService();
		int stats = service.produceStatistics(3, city);

		StatisticsInfo statisticsInfo = new StatisticsInfo(stats, 3);

		return statisticsInfo;
	}
}
