/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.framework.domain.ddd;

import eapli.framework.domain.Identifiable;

/**
 * An entity is a Domain-Driven Design pattern for concepts in the domain which
 * have a thread of continuity which needs to be tracked. these concepts matter
 * by their identity and we need to track them continuously. instance equality
 * must be done thru the identity of the objects and we cannot loose track or
 * allow duplication of an entity
 *
 * Typical examples are:
 * <ol>
 * <li>Product
 * <li>Person
 * <li>Account
 * </ol>
 *
 * @param <I> the type of the primary <b>business</b> id of the entity
 * @author Paulo Gandra Sousa
 */
public interface DomainEntity<I> extends Identifiable<I> {

    /**
     * Entities are compared by identity only. no need to compare all fields of
     * the object. you can use the domainEntityHelper.areEqual() method as
     * default implementation
     *
     * see sameAs().
     *
     * @param other
     * @return
     */
    @Override
    boolean equals(Object other);

    /**
     * hash code of this object according to java rules. i.e., the same fields
     * used in equals() should be used in hashCode().
     *
     * see <a href=
     * "http://stackoverflow.com/questions/113511/best-implementation-for-hashcode-method">
     * stack overflow</a> for a nice discussion about hashCode() and equals()
     *
     * @return
     */
    @Override
    int hashCode();

    /**
     * Entities are compared by identity only, so equals() only compares
     * identities. in some situations however we might want to compare the
     * content of the object.
     *
     * @param other
     * @return
     */
    boolean sameAs(Object other);
}
