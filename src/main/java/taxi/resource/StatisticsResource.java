package taxi.resource;

import javax.ws.rs.Path;
import taxi.service.StatisticsService;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import java.util.Date;

@Path("/statistics")
public class StatisticsResource extends AbstractResource {

	@GET
	@Path("/commision")
	@Produces(MediaType.APPLICATION_JSON)
	public StatisticsInfo statisticsCommision (@QueryParam("fromRange") Date fromRange,	@QueryParam("toRange") Date toRange){
		
		StatisticsService service = new StatisticsService();
		double stats = service.produceStatistics(1, fromRange, toRange);
		
		StatisticsInfo statisticsInfo = new StatisticsInfo(stats, 1);

		return statisticsInfo;
	}
	
	@GET
	@Path("/fromCity")
	@Produces(MediaType.APPLICATION_JSON)
	public StatisticsInfo statisticsFromCity (@QueryParam("city") String city){
		
		StatisticsService service = new StatisticsService();
		int stats = service.produceStatistics(2, city);
		
		StatisticsInfo statisticsInfo = new StatisticsInfo(stats, 2);

		return statisticsInfo;
	}
	
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
