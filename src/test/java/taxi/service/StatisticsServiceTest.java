package taxi.service;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

	}

	@Test
	public void testProduceInValidStatistics1_nullDateFrom(){

	}

	@Test
	public void testProduceInValidStatistics1_nullDateTo(){

	}

	@Test
	public void testProduceInValidStatistics1_otherSelection0(){

	}

	@Test
	public void testProduceInValidStatistics1_otherSelection2(){

	}

	@Test
	public void testProduceInValidStatistics1_otherSelection3(){

	}

	//Tests for 2nd type of statistics
	@Test
	public void testProduceValidStatistics2(){

	}

	@Test
	public void testProduceInValidStatistics2_emptyCity(){

	}

	@Test
	public void testProduceInValidStatistics2_nullCity(){

	}

	@Test
	public void testProduceInValidStatistics2_cityNotInDB(){

	}

	//Tests for 3rd type of statistics
	@Test
	public void testProduceValidStatistics3(){

	}

	@Test
	public void testProduceInValidStatistics3_emptyCity(){

	}

	@Test
	public void testProduceInValidStatistics3_nullCity(){

	}	

	@Test
	public void testProduceInValidStatistics3_cityNotInDB(){

	}

	//Tests for 2nd and 3rd type of statistics
	@Test
	public void testProduceInValidStatistics23_otherSelection0(){

	}

	@Test
	public void testProduceInValidStatistics23_otherSelection1(){

	}
}
