package taxi.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import taxi.model.*;
import java.text.*;



public class Initializer  {


    /**
     * Remove all data from database.
     * The functionality must be executed within the bounds of a transaction
     */
    public void  eraseData() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
       
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = null;

        query = em.createNativeQuery("delete from movies");
        query.executeUpdate();
        
        query = em.createNativeQuery("delete from categories");
        query.executeUpdate();
        
        query = em.createNativeQuery("ALTER SEQUENCE hibernate_sequence RESTART WITH 1");
        query.executeUpdate();
        
        tx.commit();
        
    }
    
  public static void prepareData() throws ParseException{
	  
  //eraseData();  
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date d;
	try {
		d = sdf.parse("21/12/2012");
	}
	catch (ParseException e){
		d = sdf.parse("21/12/2012");
	}
	
	
    Customer customer = new Customer("makis", "xristodoylopoylos", "gynaika", "mak", "mak", d, "dfaggaadfadsfada", "fdaafdfa", 13671, "mastercard", 123456710, d, 123);
    Evaluation eval = new Evaluation(3, "djhalfhalcdalr", d);
    Taxi taxi = new Taxi("dfadad", "fdafda", "347932", d, "fdafadfaea");
    TaxiDriver taxidr = new TaxiDriver("makis", "xristodoylopoylos", "gynaika", "mak", "mak", d, "dfaggaadfadsfada", "fdaafdfa", 13671, "mastercard", 123456710, d, 123, taxi);
    Route route = new Route("from", "to", 0, 1.5f);
    Request req = new Request(d, true, taxi, customer, route);
    System.out.println("objects");
    
    /*Category cat = new Category();
    Category cat2 = new Category();
    cat.setDescription("Drama");
    cat2.setDescription("Science Fiction");*/
   
    EntityManager em = JPAUtil.getCurrentEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    System.out.println("begin");
    
    em.persist(customer);
    em.persist(eval);
    em.persist(req);
    em.persist(route);
    em.persist(taxi);
    em.persist(taxidr);
    System.out.println("persist");
    
    tx.commit();
    System.out.println("commit");
    
    Query query = em.createQuery("select cust from Customer cust");
    List<Customer> results = query.getResultList();
    
    
    

}

public static void main (String [] args) {
	try {
		prepareData();
		System.out.println("telepse to prepare");
	}
	catch (ParseException e){
		System.out.println("poytana date");
	}
}
}
