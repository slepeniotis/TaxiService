package taxi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import taxi.persistence.Initializer;
import taxi.persistence.JPAUtil;

public class StatisticsServiceTest extends TaxiServiceTest{

	//Tests for 1st type of statistics
	
	/* produce statictics, type 1
	 * valid creation of statistics
	 * checks total commisions received in a specific date range
	 */
	@Test
	public void testProduceValidStatistics1(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dFrom = new Date();
		Date dTo = new Date();
		try {
			dFrom = sdf.parse("01/01/2010");
			dTo = sdf.parse("01/03/2017");
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}	
		
		StatisticsService service = new StatisticsService();	
		double ps = service.produceStatistics(1, dFrom, dTo);		
		Assert.assertEquals(5.10000005364418, ps, 0.001);

	}

	//invalid request for production, null from date 
	@Test
	public void testProduceInValidStatistics1_nullDateFrom(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dTo = new Date();
		try {
			dTo = sdf.parse("01/03/2017");
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}	
		
		StatisticsService service = new StatisticsService();	
		double ps = service.produceStatistics(1, null, dTo);		
		Assert.assertEquals(0, ps, 0.001);
	}

	//invalid request for production, null to date
	@Test
	public void testProduceInValidStatistics1_nullDateTo(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dFrom = new Date();
		try {
			dFrom = sdf.parse("01/03/2017");
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}	
		
		StatisticsService service = new StatisticsService();	
		double ps = service.produceStatistics(1, dFrom, null);		
		Assert.assertEquals(0, ps, 0.001);
	}
	
	//invalid request for production, empty result in the specific range
	@Test
	public void testProduceInValidStatistics1_emptyDateFromTo(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dTo = new Date();
		Date dFrom = new Date();
		try {
			dTo = sdf.parse("01/03/1990");
			dFrom = sdf.parse("01/03/1980");
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}	
		
		StatisticsService service = new StatisticsService();	
		double ps = service.produceStatistics(1, dFrom, dTo);		
		Assert.assertEquals(0, ps, 0.001);
	}

	//invalid request for production, wrong selection = 0
	@Test
	public void testProduceInValidStatistics1_otherSelection0(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dFrom = new Date();
		Date dTo = new Date();
		try {
			dFrom = sdf.parse("01/01/2010");
			dTo = sdf.parse("01/03/2017");
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}	
		
		StatisticsService service = new StatisticsService();	
		double ps = service.produceStatistics(0, dFrom, dTo);		
		Assert.assertEquals(0, ps, 0.001);
	}

	//invalid request for production, wrong selection = 2
	@Test
	public void testProduceInValidStatistics1_otherSelection2(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dFrom = new Date();
		Date dTo = new Date();
		try {
			dFrom = sdf.parse("01/01/2010");
			dTo = sdf.parse("01/03/2017");
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}	
		
		StatisticsService service = new StatisticsService();	
		double ps = service.produceStatistics(2, dFrom, dTo);		
		Assert.assertEquals(0, ps, 0.001);

	}

	//invalid request for production, wrong selection = 3
	@Test
	public void testProduceInValidStatistics1_otherSelection3(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dFrom = new Date();
		Date dTo = new Date();
		try {
			dFrom = sdf.parse("01/01/2010");
			dTo = sdf.parse("01/03/2017");
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}	
		
		StatisticsService service = new StatisticsService();	
		double ps = service.produceStatistics(3, dFrom, dTo);		
		Assert.assertEquals(0, ps, 0.001);

	}

	//Tests for 2nd type of statistics
	
	/* produce statictics, type 2
	 * valid creation of statistics
	 * checks total routes from a specific city
	 */
	@Test
	public void testProduceValidStatistics2(){
		
		StatisticsService service = new StatisticsService();	
		int result  =  service.produceStatistics(2, "ACHARNES");
		Assert.assertEquals(1, result);

	}

	//invalid production, empty city
	@Test
	public void testProduceInValidStatistics2_emptyCity(){
		
		StatisticsService service = new StatisticsService();	
		int result  =  service.produceStatistics(2, " ");
		Assert.assertEquals(0, result);

	}

	//invalid production, null city
	@Test
	public void testProduceInValidStatistics2_nullCity(){
		
		StatisticsService service = new StatisticsService();	
		int result  =  service.produceStatistics(2, null);
		Assert.assertEquals(0, result);

	}

	//invalid production, city does not exist in DB
	@Test
	public void testProduceInValidStatistics2_cityNotInDB(){
		
		StatisticsService service = new StatisticsService();	
		int result  =  service.produceStatistics(2, "LARISA");
		Assert.assertEquals(0, result);

	}

	//Tests for 3rd type of statistics
	
	/* produce statictics, type 3
	 * valid creation of statistics
	 * checks total routes to a specific city
	 */
	@Test
	public void testProduceValidStatistics3(){
		
		StatisticsService service = new StatisticsService();	
		int result  =  service.produceStatistics(3, "ATHINA");
		Assert.assertEquals(2, result);

	}

	//invalid production, empty city
	@Test
	public void testProduceInValidStatistics3_emptyCity(){
		
		StatisticsService service = new StatisticsService();	
		int result  =  service.produceStatistics(3, " ");
		Assert.assertEquals(0, result);

	}
	
	//invalid production, null city
	@Test
	public void testProduceInValidStatistics3_nullCity(){
		
		StatisticsService service = new StatisticsService();	
		int result  =  service.produceStatistics(3, null);
		Assert.assertEquals(0, result);

	}	

	//invalid production, city does not exist in DB
	@Test
	public void testProduceInValidStatistics3_cityNotInDB(){
		
		StatisticsService service = new StatisticsService();	
		int result  =  service.produceStatistics(3, "LARISA");
		Assert.assertEquals(0, result);

	}

	//Tests for 2nd and 3rd type of statistics
	
	//invalid production, wrong selection = 0
	@Test
	public void testProduceInValidStatistics23_otherSelection0(){
		
		StatisticsService service = new StatisticsService();	
		int result  =  service.produceStatistics(0, "ATHINA");
		Assert.assertEquals(0, result);

	}

	//invalid production, wrong selection = 1
	@Test
	public void testProduceInValidStatistics23_otherSelection1(){
		
		StatisticsService service = new StatisticsService();	
		int result  =  service.produceStatistics(1, "ATHINA");
		Assert.assertEquals(0, result);

	}
}
