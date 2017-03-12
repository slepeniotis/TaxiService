package taxi.resource;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.Status;

import taxi.model.Customer;
import taxi.service.EditAccountService;

import java.net.URI;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;

@Path("login")
public class LoginResource extends AbstractResource {

	@GET
	@Path("CustomerLogin")
	@Produces("aaplication/xml")
	@Consumes("application/x-www-form-urlencoded")
	public Customer CustomerLogin (){
		
		EditAccountService service = new EditAccountService();
		Customer c1 = (Customer)service.changeAddress("Customer", userId, address, city, zipCode);
		
		
	}
}
