package taxi.resource;

import javax.ws.rs.Path;
import taxi.service.StatisticsService;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.GET;
import java.util.Date;

@Path("statistics")
public class StatisticsResource extends AbstractResource {

	@GET
	@Path("commision")
	@Produces("text/HTML")
	public String statisticsCommision (@QueryParam("fromRange") Date fromRange,	@QueryParam("toRange") Date toRange){
		
		StatisticsService service = new StatisticsService();
		double stats = service.produceStatistics(1, fromRange, toRange);
		
		if(stats != 0){
			return String.valueOf(stats);
		}
		else{
			return String.valueOf(0);
		}
	}
	
	@GET
	@Path("fromCity")
	@Produces("text/HTML")
	public String statisticsFromCity (@QueryParam("city") String city){
		
		StatisticsService service = new StatisticsService();
		int stats = service.produceStatistics(2, city);
		
		if(stats != 0){
			return String.valueOf(stats);
		}
		else{
			return String.valueOf(0);
		}
	}
	
	@GET
	@Path("toCity")
	@Produces("text/HTML")
	public String statisticsToCity (@QueryParam("city") String city){
		
		StatisticsService service = new StatisticsService();
		int stats = service.produceStatistics(3, city);
		
		if(stats != 0){
			return String.valueOf(stats);
		}
		else{
			return String.valueOf(0);
		}
	}
}
