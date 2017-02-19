package gr.aueb.mscis.sample.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.aueb.mscis.sample.model.Movie;
import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;

public class MovieServiceTest {
	
	protected EntityManager em;
	
	@Before
	public void setup(){
		// prepare database for each test
		em = JPAUtil.getCurrentEntityManager();
		Initializer dataHelper = new Initializer();
		dataHelper.prepareData();
		
	}
	
	@After
	public void tearDown(){
		em.close();
	}
	
	@Test
	public void testFindExistingMovie() {
	
		Movie movie = em.find(Movie.class, 1);		
		Assert.assertNotNull(movie);
		Assert.assertEquals("Snowden", movie.getTitle());
		
	}
	
	@Test
	public void testPersistAValidMovie(){
		
		MovieService service = new MovieService();
		Movie newMovie = service.createMovie("Django", 2012, "Tarantino");
	
		Assert.assertNotEquals(0, newMovie.getId());
		em.close();
		
		em = JPAUtil.getCurrentEntityManager();	
		// assertions
		// 5th object created in current test (+ 4 created in previous)
		// don't do this in real tests, use query instead
		Movie savedMovie = em.find(Movie.class, 5); 
		Assert.assertNotNull(savedMovie);
		Assert.assertEquals("Django", savedMovie.getTitle());
		
	}
	
	@Test
	public void testDenyCreationOfInvalidMovie(){
		MovieService service = new MovieService();
		Movie newMovie = service.createMovie(null, 2012, "Tarantino");
	
		Assert.assertNull(newMovie);
	}
	
	@Test
	public void testUpdateMovie_afterFind(){
		
		// create a movie in another use case
		MovieService service = new MovieService();
		Movie newMovie = service.createMovie("Django", 2012, "Tarantino");
		em.close();
		
		int movieId = newMovie.getId();
		
		em = JPAUtil.getCurrentEntityManager();
		// find the movie and update its title
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Movie savedMovie = em.find(Movie.class, movieId);
		Assert.assertNotNull(savedMovie);
		savedMovie.setTitle("Django Unchained");
		tx.commit();
		em.close();
		
		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Movie m = em.find(Movie.class, movieId);
		Assert.assertEquals("Django Unchained", m.getTitle());
		
		
	}
	
	@Test
	public void testMergeMovie_withSavedVersion(){
		
		// Receive a Movie object with assigned id (e.g. from form submission)
		Movie movie = new Movie("Snowden", 2012, "O. Stone");
		movie.setId(1);
		
		// Update database with data in movie object
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Movie savedMovie = em.merge(movie);
		Assert.assertNotNull(savedMovie);
		
		tx.commit();
		em.close();
		
		// assertions
		em = JPAUtil.getCurrentEntityManager();
		Movie m = em.find(Movie.class, 1);
		Assert.assertEquals("O. Stone", m.getDirector());
		Assert.assertEquals(2012, m.getYear());
		
	}

}
