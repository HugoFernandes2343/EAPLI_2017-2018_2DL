/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.persistence.repositories.impl.jpa;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 * An utility class for implementing JPA repositories not running in containers
 * and not relying on external transaction managers. This class' methods
 * initiate an explicit transaction and commit in the end of the method. check
 * JpaBaseRepository if you want to have transaction control outside of the base
 * class (for instance, when using a JPA container). If you are not using a
 * container and all your controllers update just one aggregate, use this class
 * that explicitly opens and closes a transaction in each method.
 *
 *
 * @author Paulo Gandra Sousa
 * @param <T>
 *            the entity type managed by this repository (a table in the
 *            database)
 * @param <K>
 *            the primary key of the table
 */
public class JpaTransactionalRepository<T, K extends Serializable> extends JpaNotRunningInContainerRepository<T, K> {

    public JpaTransactionalRepository(String persistenceUnitName) {
	super(new JpaTransactionalContext(persistenceUnitName));
    }

    JpaTransactionalRepository(String persistenceUnitName, Class<T> classz) {
	super(new JpaTransactionalContext(persistenceUnitName), classz);
    }

    /**
     * removes the object from the persistence storage. the object reference is
     * still valid but the persisted entity is/will be deleted
     *
     * @param entity
     * @throws DataIntegrityViolationException
     */
    @Override
    public void delete(T entity) throws DataIntegrityViolationException {
	if (entity == null) {
	    throw new IllegalArgumentException();
	}

	final EntityManager em = entityManager();
	try {
	    final EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    super.delete(entity);
	    tx.commit();
	} finally {
	    // we are closing the entity manager here because this code is
	    // running in a non-container managed way. if it was the case to be
	    // running under an application server with a JPA container and
	    // managed transactions/sessions, one should not be doing this
	    em.close();
	}
    }

    /**
     * Removes the entity with the specified ID from the repository.
     *
     * @param entityId
     * @throws DataIntegrityViolationException
     * @throws UnsuportedOperationException
     *             if the delete operation makes no sense for this repository
     */
    @Override
    public void delete(K entityId) throws DataIntegrityViolationException {
	final EntityManager em = entityManager();
	try {
	    final EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    super.delete(entityId);
	    tx.commit();
	} finally {
	    // we are closing the entity manager here because this code is
	    // running in a non-container managed way. if it was the case to be
	    // running under an application server with a JPA container and
	    // managed transactions/sessions, one should not be doing this
	    em.close();
	}
    }

    /**
     * adds <b>and commits</b> a new entity to the persistence store
     *
     * @param entity
     * @return the newly created persistent object
     * @throws DataIntegrityViolationException
     */
    @Override
    public T create(T entity) throws DataIntegrityViolationException {
	if (entity == null) {
	    throw new IllegalArgumentException();
	}

	final EntityManager em = entityManager();
	try {
	    final EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    em.persist(entity);
	    tx.commit();
	} catch (final PersistenceException ex) {
	    // TODO need to check and make sure we only throw
	    // DataIntegrityViolationException if we get sql state 23505
	    throw new DataIntegrityViolationException(ex);
	} finally {
	    // we are closing the entity manager here because this code is
	    // running in a non-container managed way. if it was the case to be
	    // running under an application server with a JPA container and
	    // managed transactions/sessions, one should not be doing this.
	    em.close();
	}
	return entity;
    }

    /**
     * Inserts or updates an entity <b>and commits</b>.
     *
     * note that you should reference the return value to use the persisted
     * entity, as the original object passed as argument might be copied to a
     * new object
     *
     * check <a href=
     * "http://blog.xebia.com/2009/03/23/jpa-implementation-patterns-saving-detached-entities/"
     * > JPA implementation patterns</a> for a discussion on saveOrUpdate()
     * behavior and merge()
     *
     * @param entity
     * @return the persisted entity - might be a different object than the
     *         parameter
     * @throws eapli.framework.persistence.DataConcurrencyException
     * @throws DataIntegrityViolationException
     */
    @Override
    @SuppressWarnings("squid:S1226")
    public T save(T entity) throws DataConcurrencyException, DataIntegrityViolationException {
	if (entity == null) {
	    throw new IllegalArgumentException();
	}

	// the following code attempts to do a save or update
	// this could be made more efficient if we check if the entity has an
	// autogenerated id
	final EntityManager em = entityManager();
	assert em != null;
	try {
	    final EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    entity = em.merge(entity);
	    tx.commit();
	} catch (final PersistenceException ex) {
	    if (ex.getCause() instanceof OptimisticLockException) {
		throw new DataConcurrencyException(ex);
	    }
	    // TODO need to check and make sure we only throw
	    // DataIntegrityViolationException if we get sql state 23505
	    throw new DataIntegrityViolationException(ex);
	} finally {
	    // we are closing the entity manager here because this code is
	    // running in a non-container managed way. if it was the case to be
	    // running under an application server with a JPA container and
	    // managed transactions/sessions, one should not be doing this
	    em.close();
	}

	return entity;
    }
}
