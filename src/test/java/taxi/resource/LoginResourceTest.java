package taxi.resource;

import javax.persistence.EntityManager;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;
import taxi.persistence.Initializer;

public class LoginResourceTest extends TaxiResourceTest {

	Initializer dataHelper;
	protected EntityManager em;

	@Override
	protected Application configure() {
		return new ResourceConfig(LoginResource.class, DebugExceptionMapper.class);
	}
}
