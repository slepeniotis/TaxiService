package taxi.resource;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;


import taxi.model.Customer;
import taxi.model.Request;
import taxi.model.Route;
import taxi.model.Taxi;
import taxi.service.RequestHandlingService;

import javax.ws.rs.POST;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;

/**RequestHandlingResource class implements the REST service for RequestHandlingService<p>
 * 
 * The base path for this class is /request
 * 
 * @author  Team 4
 * @since   Academic Year 2016-2017
 */
@Path("/request")
public class RequestHandlingResource extends AbstractResource {

	@Context
	UriInfo uriInfo;

	/**searchTaxi method<p>
	 * receives GET requests at path /request/search<p>
	 * This method is called when the customer searches for a taxi in a specified range. <p>
	 * 
	 * It produces as output a list of TaxiInfo object. This list of objects is transformed to JSON. 
	 * The method also receives as query parameters the range defined from the customer (in kilometers), and the customer ID.
	 * <p>
	 * The results are produced calling the service searchTaxi with the appropriate parameters.
	 * <p>
	 * @param customerId type String
	 * @param range type String
	 * @return List<TaxiInfo>
	 */
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TaxiInfo> searchTaxi(@QueryParam("customerId") String customerId, @QueryParam("range") String range) {

		Long custId = Long.parseLong(customerId);
		int rangInt = Integer.parseInt(range);

		EntityManager em = getEntityManager();
		RequestHandlingService service = new RequestHandlingService();

		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setId(custId);
		Customer customer = customerInfo.getCustomer(em);

		List<Taxi> taxi = service.searchTaxi(customer, rangInt);
		List<TaxiInfo> taxiInfo;
		if(taxi != null){
			taxiInfo = TaxiInfo.wrapTaxi(taxi);
		}
		else{
			taxiInfo = new ArrayList<>(0);
		}

		return taxiInfo;
	}	

	/**startRequest method<p>
	 * receives POST requests at path /request/startrequest<p>
	 * This method receives as input JSON. This JSON is transformed to RequestHandlingInfo object
	 * <p>
	 * The method is called once the customer has selected a taxi for his request. 
	 * The request is started calling the service startRequest with the appropriate inputs. 
	 * This call will return the object of the newly created request. 
	 * If the request was started successfully, the response is 201, or else 404.  
	 * <p>
	 * @param requestHandlingInfo type RequestHandlingInfo
	 * @return Response
	 */
	@POST
	@Path("/startrequest")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response startRequest(RequestHandlingInfo requestHandlingInfo) {

		EntityManager em = getEntityManager();
		Taxi taxi = requestHandlingInfo.getTaxi(em);
		Customer customer = requestHandlingInfo.getCustomer(em);

		RequestHandlingService service = new RequestHandlingService();
		Request req = service.startRequest(taxi, customer);
		em.close();

		if(req != null){
			UriBuilder ub = uriInfo.getAbsolutePathBuilder();
			URI newRequestUri = ub.path(Long.toString(req.getId())).build();
			return Response.created(newRequestUri).build();
		}
		else{
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}		
	}

	/**handleRequest method<p>
	 * receives PUT requests at path /request/handlerequest/x<p>
	 * where x = numeric request ID<p>
	 * It is called when a taxi driver accepts a request made by a customer<p>
	 * <p>
	 * This method receives as input JSON (includes the relative information of the request and taxi). This JSON is transformed to RequestHandlingInfo object. 
	 * It also receives as path parameter the request ID (long)
	 * <p>
	 * The request is handled via calling the service handleRequest with the appropriate inputs. 
	 * This call will return a boolean (true if everything went alright, false if something went wrong). 
	 * If the update of the request was successful, the response is 200, or else 406. 
	 * <p>
	 * @param requestId type long
	 * @param requestHandlingInfo type RequestHandlingInfo
	 * @return Response
	 */
	@PUT
	@Path("/handlerequest/{requestId:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response handleRequest(@PathParam("requestId") long requestId, RequestHandlingInfo requestHandlingInfo) {

		EntityManager em = getEntityManager();
		Taxi taxi = requestHandlingInfo.getTaxi(em);
		boolean result = false;
		if(requestId != requestHandlingInfo.getReqId()){
			em.close();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		else{
			Request req = requestHandlingInfo.getRequest(em);

			RequestHandlingService service = new RequestHandlingService();
			result = service.handleRequest(req, taxi, "yes");

			if(result)
				return Response.ok().build();
			else{
				em.close();			
				return Response.status(Status.NOT_ACCEPTABLE).build();
			}
		}		
	}	

	/**notHandleRequest method<p>
	 * receives PUT requests at path /request/nothandlerequest/x<p>
	 * where x = numeric request ID<p>
	 * It is called when a taxi driver denies a request made by a customer<p>
	 * 
	 * This method receives as input JSON (includes the relative information of the request and taxi). This JSON is transformed to RequestHandlingInfo object. 
	 * It also receives as path parameter the request ID (long)
	 * <p>
	 * The request is denied via calling the service handleRequest with the appropriate inputs. 
	 * This call will return a boolean (true if everything went alright, false if something went wrong). 
	 * If the update of the request was successful, the response is 200, or else 406. 
	 * <p>
	 * @param requestId type long
	 * @param requestHandlingInfo type RequestHandlingInfo
	 * @return Response
	 */
	@PUT
	@Path("/nothandlerequest/{requestId:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response notHandleRequest(@PathParam("requestId") long requestId, RequestHandlingInfo requestHandlingInfo) {

		EntityManager em = getEntityManager();
		Taxi taxi = requestHandlingInfo.getTaxi(em);
		boolean result = false;
		if(requestId != requestHandlingInfo.getReqId()){
			em.close();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		else{
			Request req = requestHandlingInfo.getRequest(em);

			RequestHandlingService service = new RequestHandlingService();
			result = service.handleRequest(req, taxi, "no");

			if(result)
				return Response.ok().build();
			else{
				em.close();			
				return Response.status(Status.NOT_ACCEPTABLE).build();
			}
		}		
	}

	/**createRoute method<p>
	 * receives POST requests at path /request/createRoute<p>
	 * This method receives as input JSON. This JSON is transformed to RequestHandlingInfo object<p>
	 * 
	 * The method is called once the customer has been informed that the driver accepted the request, and then needs to add the desired route. 
	 * The route is created calling the service createRoute with the appropriate inputs. 
	 * This call will return the object of the newly created route. 
	 * If the route was started successfully, the response is 201, or else 404. 
	 * <p>
	 * @param requestHandlingInfo type RequestHandlingInfo
	 * @return Response
	 */
	@POST
	@Path("/createRoute")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createRoute(RequestHandlingInfo requestHandlingInfo) {

		EntityManager em = getEntityManager();
		Request req = requestHandlingInfo.getRequest(em);

		RequestHandlingService service = new RequestHandlingService();
		Route route = service.createRoute(req, requestHandlingInfo.getFromAddress(), requestHandlingInfo.getToAddress(),
				requestHandlingInfo.getFromCity(), requestHandlingInfo.getToCity(), requestHandlingInfo.getFromZipCode(), requestHandlingInfo.getToZipCode());

		if(route != null){
			UriBuilder ub = uriInfo.getAbsolutePathBuilder();
			URI newRouteUri = ub.path(Long.toString(route.getId())).build();
			return Response.created(newRouteUri).build();
		}
		else{
			em.close();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}		
	}

	/**stopRequest method<p>
	 * receives PUT requests at path /request/stoprequest/x<p>
	 * where x = numeric request ID<p>
	 * It is called when a taxi driver informs the system that the request was done<p>
	 * 
	 * This method receives as input JSON (includes the relative information of the request and taxi). This JSON is transformed to RequestHandlingInfo object. 
	 * It also receives as path parameter the request ID (long)
	 * <p>
	 * The request status is updated to done via calling the service stopRequest with the appropriate inputs. 
	 * This call will return a boolean (true if everything went alright, false if something went wrong). 
	 * If the update of the request was successful, the response is 200, or else 406. 
	 * <p>
	 * @param requestId type long
	 * @param requestHandlingInfo type RequestHandlingInfo
	 * @return Response
	 */
	@PUT
	@Path("/stoprequest/{requestId:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response stopRequest(@PathParam("requestId") long requestId, RequestHandlingInfo requestHandlingInfo) {

		EntityManager em = getEntityManager();
		Taxi taxi = requestHandlingInfo.getTaxi(em);
		boolean result = false;
		if(requestId != requestHandlingInfo.getReqId()){
			em.close();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		else{
			Request req = requestHandlingInfo.getRequest(em);

			RequestHandlingService service = new RequestHandlingService();
			result = service.stopRequest(req, taxi, requestHandlingInfo.getCost(), requestHandlingInfo.getDuration());

			if(result)
				return Response.ok().build();
			else {
				em.close();
				return Response.status(Status.NOT_ACCEPTABLE).build();
			}
		}		
	}

	/**cancelRequest method<p>
	 * receives PUT requests at path /request/cancelrequest/x<p>
	 * where x = numeric request ID<p>
	 * It is called when a customer wishes to cancel a request he previously made<p>
	 * 
	 * This method receives as input JSON (includes the relative information of the request and customer). This JSON is transformed to RequestHandlingInfo object. 
	 * It also receives as path parameter the request ID (long)
	 * <p>
	 * The request status is updated to canceled via calling the service cancelRequest with the appropriate inputs. 
	 * This call will return a boolean (true if everything went alright, false if something went wrong). 
	 * If the update of the request was successful, the response is 200, or else 406. 
	 * <p>
	 * @param requestId type long
	 * @param requestHandlingInfo type RequestHandlingInfo
	 * @return Response
	 */
	@PUT
	@Path("/cancelrequest/{requestId:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cancelRequest(@PathParam("requestId") long requestId, RequestHandlingInfo requestHandlingInfo) {

		EntityManager em = getEntityManager();
		Customer customer = requestHandlingInfo.getCustomer(em);
		boolean result = false;
		if(requestId != requestHandlingInfo.getReqId()){
			em.close();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		else{
			Request req = requestHandlingInfo.getRequest(em);

			RequestHandlingService service = new RequestHandlingService();
			result = service.cancelRequest(customer, req);

			if(result)
				return Response.ok().build();
			else{
				em.close();
				return Response.status(Status.NOT_ACCEPTABLE).build();
			}				
		}		
	}
}
