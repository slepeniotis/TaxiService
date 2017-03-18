package taxi.resource;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;
import taxi.model.TaxiDriver;
import taxi.model.Customer;
import taxi.model.Taxi;
import taxi.service.RegistrationService;
import java.net.URI;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;

@Path("/register")
public class RegistrationResource extends AbstractResource {

	@Context
	UriInfo uriInfo;

	@POST
	@Path("/taxi")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerTaxi(TaxiInfo taxiInfo){

		RegistrationService service = new RegistrationService();
		Taxi taxi = (Taxi)service.createTaxi(taxiInfo.getCarModel(), taxiInfo.getCarType(), taxiInfo.getLicensePlate(), taxiInfo.getCarModelDate(), 
				taxiInfo.getLocationLat(), taxiInfo.getLocationLon());
		TaxiDriver taxiDriver;

		if(taxi != null){
			taxiDriver = (TaxiDriver)service.registerTaxiDriver(taxiInfo.getName(), taxiInfo.getSurname(),
					taxiInfo.getSex(), taxiInfo.getUsername(), taxiInfo.getPassword(), taxiInfo.getDateOfBirth(), 
					taxiInfo.getAddress(), taxiInfo.getCity(), taxiInfo.getZipCode(), taxiInfo.getEmail(), 
					taxiInfo.getCreditCardType(), taxiInfo.getCreditCardNumber(), taxiInfo.getExpiryDate(), 
					taxiInfo.getCcv(), taxi);

			if(taxiDriver != null){
				UriBuilder ub = uriInfo.getAbsolutePathBuilder();
				URI newTaxiUri = ub.path(Long.toString(taxi.getId())).build();
				return Response.created(newTaxiUri).build();
			}
			else{
				return Response.status(Status.NOT_ACCEPTABLE).build();
			}
		}
		else{
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
	}

	@POST
	@Path("/customer")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerCustomer(CustomerInfo customerInfo){

		RegistrationService service = new RegistrationService();
		Customer customer = (Customer)service.registerCustomer(customerInfo.getName(), customerInfo.getSurname(), 
				customerInfo.getSex(), customerInfo.getUsername(), customerInfo.getPassword(), customerInfo.getDateOfBirth(), 
				customerInfo.getLocationLat(), customerInfo.getLocationLon(), customerInfo.getAddress(), 
				customerInfo.getCity(), customerInfo.getZipCode(), customerInfo.getEmail(), customerInfo.getCreditCardType(), 
				customerInfo.getCreditCardNumber(), customerInfo.getExpiryDate(), customerInfo.getCcv());

		if(customer != null){
			UriBuilder ub = uriInfo.getAbsolutePathBuilder();
			URI newTaxiUri = ub.path(Long.toString(customer.getId())).build();
			return Response.created(newTaxiUri).build();
		}
		else{
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
	}
}
