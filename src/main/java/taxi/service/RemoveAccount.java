package taxi.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import taxi.model.Customer;
import taxi.model.TaxiDriver;
import taxi.persistence.JPAUtil;

public class RemoveAccount {

	EntityManager em;

	//getting the current entity manager
	public RemoveAccount() {
		em = JPAUtil.getCurrentEntityManager();
	}
	
	/* Remove of Taxi Driver
	 * we assume that taxi driver requests his own removal
	 * we receive the taxi driver object
	 *  
	 * after the successful removal, we get true
	 */
	public boolean removeDriver(TaxiDriver taxidriver){	
		EntityTransaction tx = em.getTransaction();
		tx.begin();				
		em.remove(taxidriver);
		tx.commit();

		return true;
	}

	/* Remove of Customer
	 * we assume that customer requests his own removal
	 * we receive the customer object
	 *  
	 * after the successful removal, we get true
	 */
	public boolean removeCustomer(Customer customer){	
		EntityTransaction tx = em.getTransaction();
		tx.begin();				
		em.remove(customer);
		tx.commit();

		//TI GINETAI ME TA REQUESTS EDO ?

		return true;
	}
}
