/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.domain.ddd;

import java.io.Serializable;

import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Paulo Gandra Sousa
 */
public interface DeleteableRepository<T extends AggregateRoot<?>, I extends Serializable> extends Repository<T, I> {

    /**
     * removes the specified entity from the repository.
     *
     * @param entity
     * @throws DataIntegrityViolationException
     * @throws UnsuportedOperationException
     *             if the delete operation makes no sense for this repository
     */
    void remove(T entity) throws DataIntegrityViolationException;

    /**
     * Removes the entity with the specified primary key from the repository.
     *
     * @param entity
     * @throws DataIntegrityViolationException
     * @throws UnsuportedOperationException
     *             if the delete operation makes no sense for this repository
     */
    void remove(I entityId) throws DataIntegrityViolationException;
}
