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

/**LoginResource class implements the REST service for LoginService<p>
 * 
 * The base path for this class is /login
 * 
 * @author  Team 4
 * @since   Academic Year 2016-2017
 */
@Path("/login")
public class LoginResource extends AbstractResource {

	@Context
	UriInfo uriInfo;

	/**customerLogin method<p>
	 * receives POST requests at path /login/CustomerLogin<p>
	 * This method receives as input JSON. This JSON is transformed to LoginInfo object<p>
	 * 
	 * The user is logged in calling the service login with the appropriate inputs. 
	 * This call will return the object of the Customer logged in. 
	 * If the login was successful, the response is 201, or else 404. 
	 * <p>
	 * @param loginInfo type LoginInfo
	 * @return Response
	 */
	@POST
	@Path("/CustomerLogin")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response customerLogin (LoginInfo loginInfo){

		LoginService service = new LoginService();		
		Customer c1 = (Customer)service.login("Customer", loginInfo.getUsername(), loginInfo.getPassword(), loginInfo.getLat(), loginInfo.getLon());

		if (c1 != null){
			UriBuilder ub = uriInfo.getAbsolutePathBuilder();
			URI newCustomerLoginUri = ub.path(Long.toString(c1.getId())).build();
			return Response.created(newCustomerLoginUri).build();	
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}		
	}

	/**taxiDriverLogin method<p>
	 * receives POST requests at path /login/TaxiDriverLogin<p>
	 * This method receives as input JSON. This JSON is transformed to LoginInfo object<p>
	 * 
	 * The user is logged in calling the service login with the appropriate inputs. 
	 * This call will return the object of the TaxiDriver logged in. 
	 * If the login was successful, the response is 201, or else 404. 
	 * <p>
	 * @param loginInfo type LoginInfo
	 * @return Response
	 */
	@POST
	@Path("/TaxiDriverLogin")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response taxiDriverLogin (LoginInfo loginInfo){

		LoginService service = new LoginService();
		TaxiDriver txdr1 = (TaxiDriver)service.login("Taxi Driver", loginInfo.getUsername(), loginInfo.getPassword(), loginInfo.getLat(), loginInfo.getLon());


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
