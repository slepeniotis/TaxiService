package taxi.resource;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;
import taxi.model.Evaluation;
import taxi.model.Route;
import taxi.service.EvaluationService;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;

/**EvaluationResource class implements the REST service for EvaluationService<p>
 * 
 * The base path for this class is /evaluation
 * 
 * @author  Team 4
 * @since   Academic Year 2016-2017
 */
@Path("/evaluation")
public class EvaluationResource extends AbstractResource {

	@Context
	UriInfo uriInfo;

	/**createEval method<p>
	 * receives POST requests at path /evaluation<p>
	 * This method receives as input JSON<p>
	 * This JSON is transformed to EvaluationInfo object<p>
	 * 
	 * The Evaluation object is created calling the service createEvaluation with the appropriate inputs. 
	 * If the Evaluation is created successfully, the response is 201, or else 404. 
	 * <p>
	 * @param evaluationInfo type EvaluationInfo
	 * @return Response
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEval(EvaluationInfo evaluationInfo){
		EntityManager em = getEntityManager();
		Route route = evaluationInfo.getRoute(em);

		EvaluationService service = new EvaluationService();
		Evaluation eval = service.createEvaluation(route, evaluationInfo.getRating(), evaluationInfo.getComment());

		if (eval != null){
			UriBuilder ub = uriInfo.getAbsolutePathBuilder();
			URI newEvalUri = ub.path(Long.toString(eval.getId())).build();
			return Response.created(newEvalUri).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}