/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.framework.domain.ddd;

import java.io.Serializable;

/**
 * A value object is a Domain-Driven Design pattern for domain concepts which do
 * not have a thread of continuity neither need to be tracked by their identity
 * but for the value of its attributes. These are <b>immutable</b> objects which
 * can be freely shared and discarded and replaced by another instance. Equality
 * is done thru comparison of the attributes values.
 *
 * Typical examples are:
 * <p>
 * - Address (in most scenarios)
 * <p>
 * - Color
 * <p>
 * - CustomerNumber
 * <p>
 * - Money
 *
 * @author Paulo Gandra Sousa
 */
public interface ValueObject extends Serializable {

    /**
     * returns a representation of this value object as a String.
     *
     * @return
     */
    @Override
    String toString();

    /**
     * Value objects are compared by the values of its properties
     *
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other);

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
    public int hashCode();
}
