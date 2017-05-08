/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.domain;

/**
 * A generic interface for checking if an object is identified by a certain T
 *
 * @author Paulo Gandra Sousa
 * @param <T>
 *            the type of the entity's identity. e.g., if an object Person is
 *            identified by an IdCardNumber, the class Person should implement
 *            interface Identifiable<IdCardNumber>
 */
public interface Identifiable<T> {

    /**
     * checks if the object is identified by the passed business id
     *
     * @param id
     *            the identity to check
     * @return true if the object has that identity
     */
    boolean is(T id);

    /**
     * returns the primary <b>business</b> id of the entity
     *
     * @return the primary <b>business</b> id of the entity
     */
    T id();
}
