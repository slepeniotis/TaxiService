package taxi.resource;

import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.FormParam;
import java.net.URI;

import taxi.model.Customer;
import taxi.model.TaxiDriver;
import taxi.service.EditAccountService;

@Path("editaccount/{userId}")
public class EditAccountResource extends AbstractResource {

	@Context
	UriInfo uriInfo;
	
	@PUT
	@Path("CustomerAddress")
	@Consumes("application/x-www-form-urlencoded")
	public Response CustomerAddress (@PathParam("userId") long userId, @FormParam("address") String address,
			@FormParam("city") String city, @FormParam("zipCode") int zipCode){
		
		EditAccountService service = new EditAccountService();
		Customer c1 = (Customer)service.changeAddress("Customer", userId, address, city, zipCode);
		
		if(c1 != null){
			UriBuilder ub = uriInfo.getAbsolutePathBuilder();
			URI changedCustomerAddr = ub.path(Long.toString(c1.getId())).build();
			return Response.created(changedCustomerAddr).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Path("TaxiDriverAddress")
	@Consumes("application/x-www-form-urlencoded")
	public Response TaxiDriverAddress (@PathParam("userId") long userId, @FormParam("address") String address,
			@FormParam("city") String city, @FormParam("zipCode") int zipCode){
		
		EditAccountService service = new EditAccountService();
		TaxiDriver txdr1 = (TaxiDriver)service.changeAddress("Taxi Driver", userId, address, city, zipCode);
		
		if(txdr1 != null){
			UriBuilder ub = uriInfo.getAbsolutePathBuilder();
			URI changedDriverAddr = ub.path(Long.toString(txdr1.getId())).build();
			return Response.created(changedDriverAddr).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Path("CustomerEmail")
	@Consumes("application/x-www-form-urlencoded")
	public Response CustomerEmail (@PathParam("userId") long userId, @FormParam("email") String email){
		
		EditAccountService service = new EditAccountService();
		Customer c1 = (Customer)service.changeEmail("Customer", userId, email);
		
		if(c1 != null){
			UriBuilder ub = uriInfo.getAbsolutePathBuilder();
			URI changeCustomerEmail = ub.path(Long.toString(c1.getId())).build();
			return Response.created(changeCustomerEmail).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Path("TaxiDriverEmail")
	@Consumes("application/x-www-form-urlencoded")
	public Response TaxiDriverEmail (@PathParam("userId") long userId, @FormParam("email") String email){
		
		EditAccountService service = new EditAccountService();
		TaxiDriver txdr1 = (TaxiDriver)service.changeEmail("Taxi Driver", userId, email);
		
		if(txdr1 != null){
			UriBuilder ub = uriInfo.getAbsolutePathBuilder();
			URI changeTaxiDriverEmail = ub.path(Long.toString(txdr1.getId())).build();
			return Response.created(changeTaxiDriverEmail).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Path("CustomerCreditCard")
	@Consumes("application/x-www-form-urlencoded")
	public Response CustomerCreditCard (@PathParam("userId") long userId, @FormParam("creditCardType") String creditCardType,
			 @FormParam("creditCardNumber") String creditCardNumber, @FormParam("expiryDate") String expiryDate, 
			 @FormParam("ccv") String ccv){
		
		EditAccountService service = new EditAccountService();
		Customer c1 = (Customer)service.changeCreditCard("Customer", userId, creditCardType, creditCardNumber, expiryDate, ccv);
		
		if(c1 != null){
			UriBuilder ub = uriInfo.getAbsolutePathBuilder();
			URI changeCustomerCredit = ub.path(Long.toString(c1.getId())).build();
			return Response.created(changeCustomerCredit).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Path("TaxiDriverEmail")
	@Consumes("application/x-www-form-urlencoded")
	public Response TaxiDriverEmail (@PathParam("userId") long userId, @FormParam("creditCardType") String creditCardType,
			 @FormParam("creditCardNumber") String creditCardNumber, @FormParam("expiryDate") String expiryDate, 
			 @FormParam("ccv") String ccv){
		
		EditAccountService service = new EditAccountService();
		TaxiDriver txdr1 = (TaxiDriver)service.changeCreditCard("Taxi Driver", userId, creditCardType, creditCardNumber, expiryDate, ccv);
		
		if(txdr1 != null){
			UriBuilder ub = uriInfo.getAbsolutePathBuilder();
			URI changeCustomerCredit = ub.path(Long.toString(txdr1.getId())).build();
			return Response.created(changeCustomerCredit).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
}
