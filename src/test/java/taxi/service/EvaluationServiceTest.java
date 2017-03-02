package taxi.service;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

	//create a valid Evaluation
	@Test
	public void testPersistValidEvaluation(){
		EvaluationService service = new EvaluationService();

		Route route = em.find(Route.class, (long)19);

		Evaluation newEval = service.createEvaluation(route, 4, "Test comment");
		Assert.assertEquals(newEval, route.getEval());		
	}

	//create invalid Evaluation. comment field is empty
	@Test
	public void testPersistInValidEvaluation_emptyComment(){
		EvaluationService service = new EvaluationService();

		Route route = em.find(Route.class, (long)19);

		Evaluation newEval = service.createEvaluation(route, 4, " ");
		Assert.assertEquals(newEval, route.getEval());	
	}

	//create invalid Evaluation. comment field is null
	@Test
	public void testPersistInValidEvaluation_nullComment(){
		EvaluationService service = new EvaluationService();

		Route route = em.find(Route.class, (long)19);

		Evaluation newEval = service.createEvaluation(route, 4, null);
		Assert.assertEquals(newEval, route.getEval());	
	}

	//create invalid Evaluation. the route to which the evaluation refers, is still ongoing
	@Test
	public void testPersistInValidEvaluation_requestStatus(){
		EvaluationService service = new EvaluationService();

		Route route = em.find(Route.class, (long)24);

		Evaluation newEval = service.createEvaluation(route, 4, "Test comment");
		Assert.assertNull(newEval);
		Assert.assertNull(route.getEval());
	}
	
	//create invalid Evaluation. the route is null
		@Test
		public void testPersistInValidEvaluation_routeNull(){
			EvaluationService service = new EvaluationService();

			Evaluation newEval = service.createEvaluation(null, 4, "Test comment");
			Assert.assertNull(newEval);
		}

	@After
	public void tearDown(){
		em.close();
	}

}
