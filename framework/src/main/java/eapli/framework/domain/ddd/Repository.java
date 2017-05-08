/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.framework.domain.ddd;

import java.io.Serializable;
import java.util.Optional;

import eapli.framework.persistence.DataIntegrityViolationException;

/**
 * A repository is a domain-driven design pattern to abstract the details of
 * persisting domain objects. it exposes a pure domain based interface without
 * leaking details of the implementation of the actual persistence mechanism.
 * there should be one repository per aggregate root
 *
 * @author Paulo Gandra Sousa
 * @param <T>
 *            the type of the entity
 * @param <I>
 *            the type of the entity's identity
 */
public interface Repository<T extends AggregateRoot<?>, I extends Serializable> extends Iterable<T> {

    /**
     * returns the first n entities according to its "natural" order
     *
     * @param n
     * @return
     */
    Iterable<T> first(int n);

    /**
     * returns a page of results using the natural order of the collection
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Iterable<T> page(int pageNumber, int pageSize);

    /**
     * reads an entity given its ID
     *
     * @param identity
     * @return
     */
    Optional<T> read(I identity);

    /**
     * inserts or updates an entity
     *
     * @param entity
     * @return the persisted entity - might be a different object than the
     *         parameter
     */
    T save(T entity);

    /**
     * adds a new entity to the repository. if the entity already exists it will
     * throw an exception. formally, the exception will be throw if there is
     * already an entity with the same business identity.
     *
     * i.e., Any x such that x.equals(e) is true
     *
     * FIXME check which exception to throw. it should not be a persistence
     * layer exception.
     *
     * @param entity
     * @return
     */
    boolean add(T entity) throws DataIntegrityViolationException;

    /**
     * updates an existing entity in the repository
     *
     * @param entity
     * @return
     */
    T update(T entity);

    /**
     * checks for the existence of an entity with the provided ID.
     *
     * @param identity
     * @return
     */
    boolean contains(T entity);

    /**
     * checks for the existence of an entity with the provided ID.
     *
     * @param identity
     * @return
     */
    boolean contains(I identity);

    /**
     * returns the number of entities in the repository.
     *
     * @return
     */
    long size();
}
