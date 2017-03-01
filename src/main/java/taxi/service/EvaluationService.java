package taxi.service;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import taxi.model.Evaluation;
import taxi.model.Route;
import taxi.model.Taxi;
import taxi.model.TaxiDriver;
import taxi.persistence.JPAUtil;
import taxi.utils.RequestStatus;

public class EvaluationService {

	EntityManager em;

	//getting the current entity manager
	public EvaluationService() {
		em = JPAUtil.getCurrentEntityManager();
	}
	
	/* Evaluation method
	 * createEvaluation gets as inputs the route, which customer evaluates,
	 * the rating and comment
	 * we assume rating can be zero
	 * Since the evaluation is done when calling this method, the user has no access to the date,
	 * the system gives always the current date
	 * 
	 * we check that none of the inputs is empty/null (except of rating) and that the request related with this request is in status done
	 * 
	 * we create the object evaluation using the inputs and inserted in the DB
	 * then we connect this evaluation with the related route
	 * 
	 * then we retrieve the taxi object from within the request 
	 * and search to find its driver
	 * we will use this object to contact the driver, 
	 * in order to inform him that an evaluation was submitted for a route he completed
	 */
	public Evaluation createEvaluation(Route route, int rating, String comment){
		if(route == null || comment == null || route.getReq() == null || route.getReq().getStatus() != RequestStatus.DONE)
			return null;

		Date dateOfEval = new Date();

		Evaluation eval = new Evaluation(rating, comment, dateOfEval);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(eval);
		route.setEval(eval);		
		tx.commit();

		Taxi taxeval = route.getReq().getTaxi();

		Query query = em.createQuery("select taxidr from TaxiDriver taxidr where taxiID = :txid");
		query.setParameter("txid", taxeval.getId());
		TaxiDriver taxdrrslt = (TaxiDriver)query.getSingleResult();

		//inform taxidriver(evaluation)
		taxdrrslt.informTaxiDriver("Customer " + route.getReq().getCustomer().getName() + " submitted an evaluation for request: " + route.getReq().getId());

		return eval;
	}
}
