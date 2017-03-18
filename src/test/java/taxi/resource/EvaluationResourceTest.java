package taxi.resource;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;
import org.junit.Test;
import taxi.model.Route;
import taxi.persistence.Initializer;
import taxi.persistence.JPAUtil;

public class EvaluationResourceTest extends TaxiResourceTest {

	Initializer dataHelper;
	protected EntityManager em;

	@Override
	protected Application configure() {
		return new ResourceConfig(EvaluationResource.class, DebugExceptionMapper.class);
	}

	//create valid evaluation
	@Test
	public void testCreateEvaluation(){		
		EntityManager em = JPAUtil.getCurrentEntityManager();
		EvaluationInfo evaluationInfo = new EvaluationInfo((long)19, 4, "Test comment");

		// Submit the updated representation
		Response response =	target("evaluation").request().post(Entity.entity(evaluationInfo, MediaType.APPLICATION_JSON));

		// assertion on request status and database state
		Assert.assertEquals(201, response.getStatus());
		Route route = em.find(Route.class, (long)19);
		em.refresh(route);
		System.out.println(route.getId());
		System.out.println(route.getEval().getComment());
		Assert.assertEquals("Test comment", route.getEval().getComment());
		em.close();
	}
	
	//create evaluation with no route
		@Test
		public void testCreateEvaluation_noRoute(){		
			EntityManager em = JPAUtil.getCurrentEntityManager();
			EvaluationInfo evaluationInfo = new EvaluationInfo(0, 4, "Test comment");

			// Submit the updated representation
			Response response =	target("evaluation").request().post(Entity.entity(evaluationInfo, MediaType.APPLICATION_JSON));

			// assertion on request status and database state
			Assert.assertEquals(404, response.getStatus());
			em.close();
		}
}
