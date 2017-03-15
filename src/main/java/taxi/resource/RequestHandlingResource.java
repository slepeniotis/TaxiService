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

@Path("request")
public class RequestHandlingResource extends AbstractResource {

	@Context
	UriInfo uriInfo;
	
	@GET
	@Path("search")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TaxiInfo> searchTaxi(@QueryParam("customerId") long customerId, @QueryParam("range") int range) {
		
		EntityManager em = getEntityManager();
		RequestHandlingService service = new RequestHandlingService();
		
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setId(customerId);
		Customer customer = customerInfo.getCustomer(em);
		em.close();
				
		List<Taxi> taxi = service.searchTaxi(customer, range);
		List<TaxiInfo> taxiInfo;
		if(taxi != null){
			taxiInfo = TaxiInfo.wrapTaxi(taxi);
		}
		else{
			taxiInfo = new ArrayList<>(0);
		}

		return taxiInfo;
	}	
	
	@POST
	@Path("startrequest")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response startRequest(RequestHandlingInfo requestHandlingInfo) {

		EntityManager em = getEntityManager();
		Taxi taxi = requestHandlingInfo.getTaxi(em);
		Customer customer = requestHandlingInfo.getCustomer(em);
		em.close();
		
		RequestHandlingService service = new RequestHandlingService();
		Request req = service.startRequest(taxi, customer);
		
		if(req != null){
			UriBuilder ub = uriInfo.getAbsolutePathBuilder();
			URI newRequestUri = ub.path(Long.toString(req.getId())).build();
			return Response.created(newRequestUri).build();
		}
		else{
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}		
	}
		
	@PUT
	@Path("handlerequest/{requestId:[0-9]+}")
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
			em.close();
			
			RequestHandlingService service = new RequestHandlingService();
			result = service.handleRequest(req, taxi, requestHandlingInfo.getDecision());
			
			if(result)
				return Response.ok().build();
			else
				return Response.status(Status.NOT_ACCEPTABLE).build();
		}		
	}
	
	
	@POST
	@Path("createRoute")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createRoute(RequestHandlingInfo requestHandlingInfo) {

		EntityManager em = getEntityManager();
		Request req = requestHandlingInfo.getRequest(em);
		em.close();
		
		RequestHandlingService service = new RequestHandlingService();
		Route route = service.createRoute(req, requestHandlingInfo.getFromAddress(), requestHandlingInfo.getToAddress(),
				requestHandlingInfo.getFromCity(), requestHandlingInfo.getToCity(), requestHandlingInfo.getFromZipCode(), requestHandlingInfo.getToZipCode());
		
		if(route != null){
			UriBuilder ub = uriInfo.getAbsolutePathBuilder();
			URI newRouteUri = ub.path(Long.toString(route.getId())).build();
			return Response.created(newRouteUri).build();
		}
		else{
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}		
	}
	
	@PUT
	@Path("stoprequest/{requestId:[0-9]+}")
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
			em.close();
			
			RequestHandlingService service = new RequestHandlingService();
			result = service.stopRequest(req, taxi, requestHandlingInfo.getCost(), requestHandlingInfo.getDuration());
			
			if(result)
				return Response.ok().build();
			else
				return Response.status(Status.NOT_ACCEPTABLE).build();
		}		
	}
	
	@PUT
	@Path("cancelrequest/{requestId:[0-9]+}")
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
			em.close();
			
			RequestHandlingService service = new RequestHandlingService();
			result = service.cancelRequest(customer, req);
			
			if(result)
				return Response.ok().build();
			else
				return Response.status(Status.NOT_ACCEPTABLE).build();
		}		
	}
}
