package taxi.service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import taxi.model.Customer;
import taxi.model.Evaluation;
import taxi.model.Route;
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

	@Test
	public void testPersistValidEvaluation(){
		EvaluationService service = new EvaluationService();

		Route route = em.find(Route.class, (long)19);

		Evaluation newEval = service.createEvaluation(route, 4, "Test comment");
		Assert.assertEquals(newEval, route.getEval());		
	}

	@Test
	public void testPersistInValidEvaluation_emptyComment(){
		EvaluationService service = new EvaluationService();

		Route route = em.find(Route.class, (long)19);

		Evaluation newEval = service.createEvaluation(route, 4, " ");
		Assert.assertEquals(newEval, route.getEval());	
	}

	@Test
	public void testPersistInValidEvaluation_nullComment(){
		EvaluationService service = new EvaluationService();

		Route route = em.find(Route.class, (long)19);

		Evaluation newEval = service.createEvaluation(route, 4, null);
		Assert.assertEquals(newEval, route.getEval());	
	}

	@Test
	public void testPersistInValidEvaluation_requestStatus(){
		EvaluationService service = new EvaluationService();

		Route route = em.find(Route.class, (long)24);

		Evaluation newEval = service.createEvaluation(route, 4, "Test comment");
		Assert.assertNull(newEval);
		Assert.assertNull(route.getEval());
	}

	@After
	public void tearDown(){
		em.close();
	}

}
