package taxi.resource;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import taxi.model.Customer;
import taxi.model.TaxiDriver;
import taxi.service.LoginService;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;


@Path("/login")
public class LoginResource extends AbstractResource {

	@Context
	UriInfo uriInfo;

	@POST
	@Path("/CustomerLogin")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CustomerLogin (LoginInfo loginInfo){

		LoginService service = new LoginService();		
		Customer c1 = (Customer)service.login(loginInfo.getUserType(), loginInfo.getUsername(), loginInfo.getPassword(), loginInfo.getLat(), loginInfo.getLon());

		if (c1 != null){
			UriBuilder ub = uriInfo.getAbsolutePathBuilder();
			URI newCustomerLoginUri = ub.path(Long.toString(c1.getId())).build();
			return Response.created(newCustomerLoginUri).build();	
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}		
	}

	@POST
	@Path("/TaxiDriverLogin")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response TaxiDriverLogin (LoginInfo loginInfo){

		LoginService service = new LoginService();
		TaxiDriver txdr1 = (TaxiDriver)service.login(loginInfo.getUserType(), loginInfo.getUsername(), loginInfo.getPassword(), loginInfo.getLat(), loginInfo.getLon());


		if (txdr1 != null){
			UriBuilder ub = uriInfo.getAbsolutePathBuilder();
			URI newTaxiDriverLoginUri = ub.path(Long.toString(txdr1.getId())).build();
			return Response.created(newTaxiDriverLoginUri).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}		

	}

}
