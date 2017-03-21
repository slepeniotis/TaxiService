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

/**EditAccountResource class implements the REST service for EditAccountService
 * 
 * The base path for this class is /editaccount
 * 
 * @author  Team 4
 * @since   Academic Year 2016-2017
 */
@Path("/editaccount")
public class EditAccountResource extends AbstractResource {

	/**customerAddress method<p>
	 * receives PUT requests at path /editaccount/CustomerAddress/x<p>
	 * where x = numeric customer ID<p>
	 * It is called when a customer needs to change his address
	 * <p>
	 * This method receives as input JSON (includes the new address). This JSON is transformed to CustomerInfo object. 
	 * It also receives as path parameter the customer ID (long)
	 * <p>
	 * The address of the user is changed via calling the service changeAddress with the appropriate inputs.
	 * This call will return the object of the Customer changed.
	 * If the change was successful, the response is 200, or else 406. 
	 * <p>
	 * @param customerInfo type CustomerInfo
	 * @param customerId type long
	 * @return Response
	 */
	@PUT
	@Path("/CustomerAddress/{customerId:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response customerAddress (CustomerInfo customerInfo, @PathParam("customerId") long customerId){

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

	/**taxiDriverAddress method<p>
	 * receives PUT requests at path /editaccount/TaxiDriverAddress/x<p>
	 * where x = numeric taxi driver ID<p>
	 * It is called when a taxi driver needs to change his address
	 * <p>
	 * This method receives as input JSON (includes the new address). This JSON is transformed to TaxiDriverInfo object. 
	 * It also receives as path parameter the taxi driver ID (long)
	 * <p>
	 * The address of the user is changed via calling the service changeAddress with the appropriate inputs.
	 * This call will return the object of the TaxiDriver changed.
	 * If the change was successful, the response is 200, or else 406. 
	 * <p>
	 * @param taxiDriverInfo type TaxiDriverInfo
	 * @param taxiDriverId type long
	 * @return Response
	 */
	@PUT
	@Path("/TaxiDriverAddress/{taxiDriverId:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response taxiDriverAddress (TaxiDriverInfo taxiDriverInfo, @PathParam("taxiDriverId") long taxiDriverId){

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

	/**customerEmail method<p>
	 * receives PUT requests at path /editaccount/CustomerEmail/x<p>
	 * where x = numeric customer ID<p>
	 * It is called when a customer needs to change his email
	 * <p>
	 * This method receives as input JSON (includes the new email). This JSON is transformed to CustomerInfo object. 
	 * It also receives as path parameter the customer ID (long)
	 * <p>
	 * The email of the user is changed via calling the service changeEmail with the appropriate inputs.
	 * This call will return the object of the Customer changed.
	 * If the change was successful, the response is 200, or else 406. 
	 * <p>
	 * @param customerInfo type CustomerInfo
	 * @param customerId type long
	 * @return Response 
	 */
	@PUT
	@Path("CustomerEmail/{customerId:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response customerEmail (CustomerInfo customerInfo, @PathParam("customerId") long customerId){

		EditAccountService service = new EditAccountService();
		Customer c1 = (Customer)service.changeEmail("Customer", customerId, customerInfo.getEmail());

		if(c1 != null){
			return Response.ok().build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	/**taxiDriverEmail method<p>
	 * receives PUT requests at path /editaccount/TaxiDriverEmail/x<p>
	 * where x = numeric taxi driver ID<p>
	 * It is called when a taxi driver needs to change his email
	 * <p>
	 * This method receives as input JSON (includes the new email). This JSON is transformed to TaxiDriverInfo object. 
	 * It also receives as path parameter the taxi driver ID (long)
	 * <p>
	 * The email of the user is changed via calling the service changeEmail with the appropriate inputs.
	 * This call will return the object of the TaxiDriver changed.
	 * If the change was successful, the response is 200, or else 406. 
	 * <p>
	 * @param taxiDriverInfo type TaxiDriverInfo
	 * @param taxiDriverId type long
	 * @return Response
	 */
	@PUT
	@Path("TaxiDriverEmail/{taxiDriverId:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response taxiDriverEmail (TaxiDriverInfo taxiDriverInfo, @PathParam("taxiDriverId") long taxiDriverId){

		EditAccountService service = new EditAccountService();
		TaxiDriver txdr1 = (TaxiDriver)service.changeEmail("Taxi Driver", taxiDriverId, taxiDriverInfo.getEmail());

		if(txdr1 != null){
			return Response.ok().build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	/**customerCreditCard method<p>
	 * receives PUT requests at path /editaccount/CustomerCreditCard/x<p>
	 * where x = numeric customer ID
	 * It is called when a customer needs to change his credit card's info
	 * <p>
	 * This method receives as input JSON (includes the new credit card). This JSON is transformed to CustomerInfo object. 
	 * It also receives as path parameter the customer ID (long)
	 * <p>
	 * The credit card of the user is changed via calling the service changeCreditCard with the appropriate inputs.
	 * This call will return the object of the Customer changed.
	 * If the change was successful, the response is 200, or else 406. 
	 * <p>
	 * @param customerInfo type CustomerInfo
	 * @param customerId type long
	 * @return Response 
	 */
	@PUT
	@Path("CustomerCreditCard/{customerId:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response customerCreditCard (CustomerInfo customerInfo, @PathParam("customerId") long customerId){

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

	/**taxiDriverCreditCard method<p>
	 * receives PUT requests at path /editaccount/TaxiDriverCreditCard/x<p>
	 * where x = numeric taxi driver ID<p>
	 * It is called when a taxi driver needs to change his credit card's info
	 * <p>
	 * This method receives as input JSON (includes the new credit card). This JSON is transformed to TaxiDriverInfo object. 
	 * It also receives as path parameter the taxi driver ID (long)
	 * <p>
	 * The credit card of the user is changed via calling the service changeCreditCard with the appropriate inputs.
	 * This call will return the object of the TaxiDriver changed.
	 * If the change was successful, the response is 200, or else 406. 
	 * <p>
	 * @param taxiDriverInfo type TaxiDriverInfo
	 * @param taxiDriverId type long
	 * @return Response
	 */
	@PUT
	@Path("TaxiDriverCreditCard/{taxiDriverId:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response taxiDriverCreditCard (TaxiDriverInfo taxiDriverInfo, @PathParam("taxiDriverId") long taxiDriverId){

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
