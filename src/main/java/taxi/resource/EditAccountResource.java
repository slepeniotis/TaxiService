package taxi.resource;

import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import taxi.model.Customer;
import taxi.model.TaxiDriver;
import taxi.service.EditAccountService;
import javax.ws.rs.PathParam;

@Path("/editaccount")
public class EditAccountResource extends AbstractResource {
	
	@PUT
	@Path("/CustomerAddress/{customerId:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CustomerAddress (CustomerInfo customerInfo, @PathParam("customerId") long customerId){
		
		EditAccountService service = new EditAccountService();
		Customer c1 = (Customer)service.changeAddress("Customer", customerId, customerInfo.getAddress(), 
				customerInfo.getCity(), customerInfo.getZipCode());
		
		if(c1 != null){
			return Response.ok().build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Path("/TaxiDriverAddress/{taxiDriverId:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response TaxiDriverAddress (TaxiDriverInfo taxiDriverInfo, @PathParam("taxiDriverId") long taxiDriverId){
		
		EditAccountService service = new EditAccountService();
		TaxiDriver txdr1 = (TaxiDriver)service.changeAddress("Taxi Driver", taxiDriverId, taxiDriverInfo.getAddress(), 
				taxiDriverInfo.getCity(), taxiDriverInfo.getZipCode());
		
		if(txdr1 != null){
			return Response.ok().build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Path("CustomerEmail/{customerId:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CustomerEmail (CustomerInfo customerInfo, @PathParam("customerId") long customerId){
		
		EditAccountService service = new EditAccountService();
		Customer c1 = (Customer)service.changeEmail("Customer", customerId, customerInfo.getEmail());
		
		if(c1 != null){
			return Response.ok().build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Path("TaxiDriverEmail/{taxiDriverId:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response TaxiDriverEmail (TaxiDriverInfo taxiDriverInfo, @PathParam("taxiDriverId") long taxiDriverId){
		
		EditAccountService service = new EditAccountService();
		TaxiDriver txdr1 = (TaxiDriver)service.changeEmail("Taxi Driver", taxiDriverId, taxiDriverInfo.getEmail());
		
		if(txdr1 != null){
			return Response.ok().build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Path("CustomerCreditCard/{customerId:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CustomerCreditCard (CustomerInfo customerInfo, @PathParam("customerId") long customerId){
		
		EditAccountService service = new EditAccountService();
		Customer c1 = (Customer)service.changeCreditCard("Customer", customerId, customerInfo.getCreditCardType(), 
				customerInfo.getCreditCardNumber(), customerInfo.getExpiryDate(), customerInfo.getCcv());
		
		if(c1 != null){
			return Response.ok().build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Path("TaxiDriverCreditCard/{taxiDriverId:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response TaxiDriverCreditCard (TaxiDriverInfo taxiDriverInfo, @PathParam("taxiDriverId") long taxiDriverId){
		
		EditAccountService service = new EditAccountService();
		TaxiDriver txdr1 = (TaxiDriver)service.changeCreditCard("Taxi Driver", taxiDriverId, taxiDriverInfo.getCreditCardType(), 
				taxiDriverInfo.getCreditCardNumber(), taxiDriverInfo.getExpiryDate(), taxiDriverInfo.getCcv());
		
		if(txdr1 != null){
			return Response.ok().build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
}