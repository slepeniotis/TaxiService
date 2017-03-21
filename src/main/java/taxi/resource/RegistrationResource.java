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

/**RegistrationResource class implements the REST service for RegistrationService<p>
 * 
 * The base path for this class is /register
 * 
 * @author  Team 4
 * @since   Academic Year 2016-2017
 */
@Path("/register")
public class RegistrationResource extends AbstractResource {

	@Context
	UriInfo uriInfo;

	/**registerTaxi method<p>
	 * receives POST requests at path /register/taxi<p>
	 * This method is called when a taxi and a taxi driver are registered to the system.<p>
	 * It receives as input JSON. This JSON is transformed to TaxiInfo object<p>
	 * 
	 * The registration of a taxi and a taxi driver are done calling the services createTaxi and registerTaxiDriver. 
	 * If the registration was successful for both, the response is 201, or else 404. 
	 * <p>
	 * @param taxiInfo type TaxiInfo
	 * @return Response
	 */
	@POST
	@Path("/taxi")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerTaxi(TaxiInfo taxiInfo){

		RegistrationService service = new RegistrationService();
		Taxi taxi = service.createTaxi(taxiInfo.getCarModel(), taxiInfo.getCarType(), taxiInfo.getLicensePlate(), taxiInfo.getCarModelDate(), 
				taxiInfo.getLocationLat(), taxiInfo.getLocationLon());
		TaxiDriver taxiDriver;

		if(taxi != null){
			taxiDriver = service.registerTaxiDriver(taxiInfo.getName(), taxiInfo.getSurname(),
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

	/**registerCustomer method<p>
	 * receives POST requests at path /register/customer<p>
	 * This method is called when a customer is registered to the system.<p>
	 * It receives as input JSON. This JSON is transformed to CustomerInfo object<p>
	 * 
	 * The registration of a customer is done calling the service registerCustomer. 
	 * If the registration was successful, the response is 201, or else 404. 
	 * <p>
	 * @param customerInfo type CustomerInfo
	 * @return Response
	 */
	@POST
	@Path("/customer")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerCustomer(CustomerInfo customerInfo){

		RegistrationService service = new RegistrationService();
		Customer customer = service.registerCustomer(customerInfo.getName(), customerInfo.getSurname(), 
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
