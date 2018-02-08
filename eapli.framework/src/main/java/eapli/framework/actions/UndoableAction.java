/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.actions;

/**
 * a generic Action interface (the Command pattern) for actions that can be
 * undone.
 *
 * @author Paulo Gandra Sousa
 */
public interface UndoableAction extends Action {
	/**
	 *
	 */
	void undo();
}
