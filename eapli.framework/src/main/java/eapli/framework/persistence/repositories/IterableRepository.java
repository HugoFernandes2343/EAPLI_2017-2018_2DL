/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.persistence.repositories;

import java.io.Serializable;
import java.util.Iterator;

/**
 * A generic interface for iterable repositories.
 *
 * @param T
 *            the class we want to manage in the repository (a table in the
 *            database)
 * @param K
 *            the class denoting the primary key of the table
 * @author Paulo Gandra Sousa
 */
public interface IterableRepository<T, K extends Serializable> extends DataRepository<T, K>, Iterable<T> {

    Iterator<T> iterator(int pagesize);

    /**
     * returns the first entity according to its "natural" order
     *
     * @return
     */
    T first();

    /**
     * returns the first n entities according to its "natural" order
     *
     * @param n
     * @return
     */
    Iterable<T> first(int n);
}
