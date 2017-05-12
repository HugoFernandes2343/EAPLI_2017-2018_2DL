/**
 *
 */
package eapli.framework.persistence.repositories.impl.jpa;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.util.Strings;

/**
 * An utility class for providing transactional context to JPA repositories not
 * running in containers. as such, this class creates its own
 * EntityManagerFactory instead of using an injected EMF by the container.
 *
 * @author Paulo Gandra Sousa
 */
public class JpaTransactionalContext implements TransactionalContext {

    private final String persistenceUnitName;
    private static EntityManagerFactory singletonEMF;
    private EntityManager entityManager;

    /**
     *
     * @param persistenceUnitName
     *            the name of the persistence unit to use
     */
    public JpaTransactionalContext(String persistenceUnitName) {
	this.persistenceUnitName = persistenceUnitName;
	entityManagerFactory();
    }

    @SuppressWarnings({ "squid:S3346", "squid:S2696" })
    EntityManagerFactory entityManagerFactory() {
	if (singletonEMF == null) {
	    assert !Strings.isNullOrEmpty(this.persistenceUnitName) : "the persistence unit name must be provided";
	    Logger.getLogger(this.getClass().getSimpleName()).info("Not runing in container mode.");
	    singletonEMF = Persistence.createEntityManagerFactory(this.persistenceUnitName);
	}
	return singletonEMF;
    }

    EntityManager entityManager() {
	if (this.entityManager == null || !this.entityManager.isOpen()) {
	    this.entityManager = entityManagerFactory().createEntityManager();
	}
	return this.entityManager;
    }

    @Override
    public void beginTransaction() {
	final EntityTransaction tx = entityManager().getTransaction();
	tx.begin();
    }

    @Override
    public void commit() {
	entityManager().getTransaction().commit();
    }

    @Override
    public void rollback() {
	entityManager().getTransaction().rollback();
    }

    @Override
    public void close() {
	entityManager().close();
    }
}
