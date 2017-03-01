package taxi.service;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import taxi.persistence.Initializer;
import taxi.persistence.JPAUtil;

public class EvaluationServiceTest {

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
	
	@Test
	public void testPersistValidEvaluation(){
			
	}
	
	@Test
	public void testPersistInValidEvaluation_emptyComment(){
			
	}
	
	@Test
	public void testPersistInValidEvaluation_nullComment(){
			
	}
	
	@Test
	public void testPersistInValidEvaluation_requestStatus(){
			
	}
	
}
