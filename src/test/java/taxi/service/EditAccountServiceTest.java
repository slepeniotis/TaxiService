package taxi.service;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;

import taxi.persistence.Initializer;
import taxi.persistence.JPAUtil;

public class EditAccountServiceTest {

	protected EntityManager em;
	
	@Before
	public void setup(){
		// prepare database for each test
		em = JPAUtil.getCurrentEntityManager();
		Initializer dataHelper = new Initializer();
		try{
			dataHelper.prepareData();
		}
		catch (Exception e){
			System.out.println(e.getStackTrace());
		}
		
	}
	
	@After
	public void tearDown(){
		em.close();
	}
}
