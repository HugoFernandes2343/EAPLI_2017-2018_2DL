/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.persistence.repositories;

import java.io.Serializable;
import java.util.Optional;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 * A generic interface for repositories.
 *
 * @param T
 *            the class we want to manage in the repository (a table in the
 *            database)
 * @param K
 *            the class denoting the primary key of the entity in the database
 * @author Paulo Gandra Sousa
 */
public interface DataRepository<T, K extends Serializable> extends Iterable<T> {

    /**
     * removes the specified entity from the repository.
     *
     * @param entity
     * @throws DataIntegrityViolationException
     * @throws UnsuportedOperationException
     *             if the delete operation makes no sense for this repository
     */
    void delete(T entity) throws DataIntegrityViolationException;

    /**
     * Removes the entity with the specified primary key from the repository.
     *
     * @param entity
     * @throws DataIntegrityViolationException
     * @throws UnsuportedOperationException
     *             if the delete operation makes no sense for this repository
     */
    void delete(K entityId) throws DataIntegrityViolationException;

    /**
     * Saves an entity either by creating it or updating it in the persistence
     * store.
     *
     * @param entity
     * @return
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException
     */
    T save(T entity) throws DataConcurrencyException, DataIntegrityViolationException;

    /**
     * gets all entities from the repository.
     *
     * @return
     */
    Iterable<T> findAll();

    /**
     * gets the entity with the specified primary key
     *
     * @param id
     * @return
     */
    Optional<T> findOne(K id);

    /**
     * returns the number of entities in the repository.
     *
     * @return
     */
    long count();
}
