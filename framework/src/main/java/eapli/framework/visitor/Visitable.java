/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.visitor;

/**
 * an interface for the visitee role of the visitor (GoF) pattern
 *
 * @author Paulo Gandra Sousa
 * @param <T>
 *            the specific type that is visitable
 */
@FunctionalInterface
public interface Visitable<T> {
    /**
     * allows the object to decide if it accepts the visitor. in case the object
     * accepts the visitor it must call the visit() method of the visitor
     *
     * @param visitor
     */
    void accept(Visitor<T> visitor);
}
