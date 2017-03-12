package taxi.resource;

import javax.persistence.EntityManager;

import taxi.persistence.JPAUtil;

public class AbstractResource {

	protected EntityManager getEntityManager() {

		return JPAUtil.getCurrentEntityManager();

	}

}
