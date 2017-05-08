/**
 *
 */
package eapli.framework.persistence.repositories.impl.jpa;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import eapli.framework.persistence.repositories.TransactionalContext;

/**
 * An utility class for implementing JPA repositories not running in containers.
 * as such, this class creates its own EntityManagerFactory instead of using an
 * injected EMF by the container. This classes assumes that transaction
 * management is done outside of the class so callers of the repository must
 * first initiate a transaction and explicitly end it; typically this will be a
 * responsibility of the controller by calling the methods of the
 * TransactionalContext interface.
 *
 * @author Paulo Gandra Sousa
 * @param <T>
 *            the entity type managed by this repository (a table in the
 *            database)
 * @param <K>
 *            the primary key of the table
 */
public class JpaNotRunningInContainerRepository<T, K extends Serializable> extends JpaBaseRepository<T, K> {

    private JpaTransactionalContext txContext;

    /**
     *
     */
    public JpaNotRunningInContainerRepository(TransactionalContext txCtx) {
	super();
	setTxCtx(txCtx);
    }

    JpaNotRunningInContainerRepository(TransactionalContext txCtx, Class<T> classz) {
	super(classz);
	setTxCtx(txCtx);
    }

    private void setTxCtx(TransactionalContext txCtx) {
	if (txCtx == null || !(txCtx instanceof JpaTransactionalContext)) {
	    throw new IllegalArgumentException();
	}
	this.txContext = (JpaTransactionalContext) txCtx;
    }

    @Override
    @SuppressWarnings("squid:S3346")
    protected EntityManagerFactory entityManagerFactory() {
	return this.txContext.entityManagerFactory();
    }

    @Override
    protected EntityManager entityManager() {
	return this.txContext.entityManager();
    }
}
