/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.visitor;

/**
 * a visitor (GoF)
 *
 * @author Paulo Gandra Sousa
 * @param <T>
 *            the specific type we want to visit
 */
public interface Visitor<T> {

    /**
     * visits a specific object
     *
     * @param visitee
     *            the object to visit
     */
    void visit(T visitee);

    /**
     * an handler that is called prior to visiting an object
     *
     * @param visitee
     */
    default void beforeVisiting(T visitee) {
    }

    /**
     * an handler that is called after visiting an object
     *
     * @param visitee
     */
    default void afterVisiting(T visitee) {
    }
}
