package gr.aueb.mscis.sample.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import gr.aueb.mscis.sample.model.Category;
import gr.aueb.mscis.sample.model.Movie;


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
    

    public void prepareData() {

        eraseData();                      

        Movie terminator = new Movie("Snowden", 2016, "Oliver Stone");
        Movie inception = new Movie("Inception", 2010, "Christopher Nolan");
        
        Category cat = new Category();
        Category cat2 = new Category();
        cat.setDescription("Drama");
        cat2.setDescription("Science Fiction");
       
        EntityManager em = JPAUtil.getCurrentEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        em.persist(terminator);
        em.persist(inception);
        em.persist(cat);
        em.persist(cat2);
        
        tx.commit();
    
    }
}
