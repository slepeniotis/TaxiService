package taxi.resource;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import taxi.persistence.*;

public abstract class TaxiResourceTest extends JerseyTest {

	Initializer dataHelper;

	public TaxiResourceTest() {
		super();
	}

	public TaxiResourceTest(TestContainerFactory testContainerFactory) {
		super(testContainerFactory);
	}

	public TaxiResourceTest(Application jaxrsApplication) {
		super(jaxrsApplication);
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
		dataHelper = new Initializer();
		dataHelper.prepareData();
	}
}
