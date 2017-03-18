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


@Path("evaluation")
public class EvaluationResource extends AbstractResource {

	@Context
	UriInfo uriInfo;
	
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