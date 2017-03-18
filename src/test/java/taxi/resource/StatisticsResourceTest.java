package taxi.resource;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;
import org.junit.Test;
import taxi.persistence.Initializer;

public class StatisticsResourceTest extends TaxiResourceTest {

	Initializer dataHelper;
	protected EntityManager em;

	@Override
	protected Application configure() {
		return new ResourceConfig(StatisticsResource.class, DebugExceptionMapper.class);
	}


	//get successful statistics for commissions in a range of dates
	@Test
	public void testStatisticsCommission(){

		StatisticsInfo statisticsInfo = target("statistics/commision").queryParam("fromRange", "01/01/2010").
				queryParam("toRange", "01/03/2017").request().get(new GenericType<StatisticsInfo>() {});

		// assertion on request status and database state
		Assert.assertEquals(5.10000005364418, statisticsInfo.getTotalCommisions(), 0.001);
	}

	//get successful statistics for routes started from a specific city
	@Test
	public void testStatisticsFromCity(){

		StatisticsInfo statisticsInfo = target("statistics/fromCity").queryParam("city", "ACHARNES")
				.request().get(new GenericType<StatisticsInfo>() {});

		// assertion on request status and database state
		Assert.assertEquals(1, statisticsInfo.getNumOfRoutes());
	}

	//get successful statistics for routes started to a specific city
	@Test
	public void testStatisticsToCity(){

		StatisticsInfo statisticsInfo = target("statistics/toCity").queryParam("city", "ATHINA")
				.request().get(new GenericType<StatisticsInfo>() {});

		// assertion on request status and database state
		Assert.assertEquals(2, statisticsInfo.getNumOfRoutes());
	}
}
