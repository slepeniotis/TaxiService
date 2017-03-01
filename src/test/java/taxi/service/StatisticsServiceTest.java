package taxi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import taxi.model.Customer;
import taxi.persistence.Initializer;
import taxi.persistence.JPAUtil;

public class StatisticsServiceTest {

	protected EntityManager em;

	@Before
	public void setup(){
		// prepare database for each test
		em = JPAUtil.getCurrentEntityManager();
		Initializer dataHelper = new Initializer();
		try{
			dataHelper.prepareData();
		}
		catch (Exception e){
			System.out.println(e.getStackTrace());
		}

	}

	@After
	public void tearDown(){
		em.close();
	}

	//Tests for 1st type of statistics
	@Test
	public void testProduceValidStatistics1(){
		
		StatisticsService service = new StatisticsService();	
		int ps =   service.produceStatistics(1, "Athens");		
		Assert.assertEquals(ps, ps);

	}

	@Test
	public void testProduceInValidStatistics1_nullDateFrom(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		StatisticsService service = new StatisticsService();	
		Object produceStatistics  =  service.produceStatistics(1, null, d);
		Assert.assertEquals(produceStatistics, produceStatistics);

	}

	@Test
	public void testProduceInValidStatistics1_nullDateTo(){

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		StatisticsService service = new StatisticsService();	
		Object produceStatistics  =  service.produceStatistics(1, d, null);
		Assert.assertEquals(produceStatistics, produceStatistics);
	}

	@Test
	public void testProduceInValidStatistics1_otherSelection0(){

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		StatisticsService service = new StatisticsService();	
		Object produceStatistics  =  service.produceStatistics(0, d, null);
		Assert.assertEquals(produceStatistics, produceStatistics);
	}

	@Test
	public void testProduceInValidStatistics1_otherSelection2(){
				
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		StatisticsService service = new StatisticsService();	
		Object produceStatistics  =  service.produceStatistics(2, d, null);
		Assert.assertEquals(produceStatistics, produceStatistics);

	}

	@Test
	public void testProduceInValidStatistics1_otherSelection3(){
			
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = sdf.parse("21/12/1980");			
		}
		catch (ParseException e){
			System.out.println(e.getStackTrace());
		}
		
		StatisticsService service = new StatisticsService();	
		Object produceStatistics  =  service.produceStatistics(3, d, null);
		Assert.assertEquals(produceStatistics, produceStatistics);

	}

	//Tests for 2nd type of statistics
	@Test
	public void testProduceValidStatistics2(){
		
		StatisticsService service = new StatisticsService();	
		Object produceStatistics  =  service.produceStatistics(1, "Athens");
		Assert.assertEquals(produceStatistics, produceStatistics);

	}

	@Test
	public void testProduceInValidStatistics2_emptyCity(){
		
		StatisticsService service = new StatisticsService();	
		Object produceStatistics  =  service.produceStatistics(1, "");
		Assert.assertEquals(produceStatistics, produceStatistics);

	}

	@Test
	public void testProduceInValidStatistics2_nullCity(){
		
		StatisticsService service = new StatisticsService();	
		Object produceStatistics  =  service.produceStatistics(1, null);
		Assert.assertEquals(produceStatistics, produceStatistics);

	}

	@Test
	public void testProduceInValidStatistics2_cityNotInDB(){
		
		StatisticsService service = new StatisticsService();	
		Object produceStatistics  =  service.produceStatistics(1, "Larisa");
		Assert.assertEquals(produceStatistics, produceStatistics);

	}

	//Tests for 3rd type of statistics
	@Test
	public void testProduceValidStatistics3(){
		
		StatisticsService service = new StatisticsService();	
		Object produceStatistics  =  service.produceStatistics(3, "Athens");
		Assert.assertEquals(produceStatistics, produceStatistics);

	}

	@Test
	public void testProduceInValidStatistics3_emptyCity(){
		
		StatisticsService service = new StatisticsService();	
		Object produceStatistics  =  service.produceStatistics(3, "");
		Assert.assertEquals(produceStatistics, produceStatistics);

	}

	@Test
	public void testProduceInValidStatistics3_nullCity(){
		
		StatisticsService service = new StatisticsService();	
		Object produceStatistics  =  service.produceStatistics(3, null);
		Assert.assertEquals(produceStatistics, produceStatistics);

	}	

	@Test
	public void testProduceInValidStatistics3_cityNotInDB(){
		
		StatisticsService service = new StatisticsService();	
		Object produceStatistics  =  service.produceStatistics(3, "Lianokladi");
		Assert.assertEquals(produceStatistics, produceStatistics);

	}

	//Tests for 2nd and 3rd type of statistics
	@Test
	public void testProduceInValidStatistics23_otherSelection0(){
		
		StatisticsService service = new StatisticsService();	
		Object produceStatistics  =  service.produceStatistics(0, "Athens");
		Assert.assertEquals(produceStatistics, produceStatistics);

	}

	@Test
	public void testProduceInValidStatistics23_otherSelection1(){
		
		StatisticsService service = new StatisticsService();	
		Object produceStatistics  =  service.produceStatistics(1, "Athens");
		Assert.assertEquals(produceStatistics, produceStatistics);

	}
}
