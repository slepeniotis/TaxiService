package taxi.service;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;

import taxi.persistence.*;

public abstract class TaxiServiceTest {

	Initializer dataHelper;
	protected EntityManager em;

	public TaxiServiceTest() {
		super();
	}

	@Before
	public final void setUp() {
		dataHelper = new Initializer();
		try{
			dataHelper.prepareData();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		em = JPAUtil.getCurrentEntityManager();
	}
	
	@After
	public final void tearDown() {
		em.close();
	}

}