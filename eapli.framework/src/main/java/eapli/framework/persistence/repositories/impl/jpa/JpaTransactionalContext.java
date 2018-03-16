/**
 *
 */
package eapli.framework.persistence.repositories.impl.jpa;

import java.util.HashMap;
import java.util.Map;
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
/* package */ class JpaTransactionalContext implements TransactionalContext {

    private final String persistenceUnitName;
    private static volatile EntityManagerFactory singletonEMF;
    private EntityManager entityManager;
    @SuppressWarnings("rawtypes")
    private final Map properties = new HashMap();

    /**
     *
     * @param persistenceUnitName
     *            the name of the persistence unit to use
     * @param properties
     *            extend properties to override the persistence.xml file
     */
    @SuppressWarnings("rawtypes")
    public JpaTransactionalContext(final String persistenceUnitName, Map properties) {
        if (Strings.isNullOrEmpty(persistenceUnitName)) {
            throw new IllegalArgumentException();
        }
        this.persistenceUnitName = persistenceUnitName;
        this.properties.putAll(properties);
        entityManagerFactory();
    }

    @SuppressWarnings("rawtypes")
    public JpaTransactionalContext(final String persistenceUnitName) {
        this(persistenceUnitName, new HashMap());
    }

    @SuppressWarnings({ "squid:S3346", "squid:S2696" })
    /* package */ final EntityManagerFactory entityManagerFactory() {
        if (singletonEMF == null) {
            assert !Strings.isNullOrEmpty(
                    persistenceUnitName) : "the persistence unit name must be provided";
            Logger.getLogger(this.getClass().getSimpleName()).info("Not runing in container mode.");
            singletonEMF = Persistence.createEntityManagerFactory(persistenceUnitName, properties);
        }
        return singletonEMF;
    }

    /* package */ EntityManager entityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = entityManagerFactory().createEntityManager();
        }
        return entityManager;
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
