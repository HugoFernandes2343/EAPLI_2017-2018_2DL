/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.actions;

/**
 * a generic Action interface (the Command pattern)
 *
 * @author Paulo Gandra Sousa
 */
@FunctionalInterface
public interface Action {
    /**
     *
     * @return true if this "scope" should end
     */
    boolean execute();
}
