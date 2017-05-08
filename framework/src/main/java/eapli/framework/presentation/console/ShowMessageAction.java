/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.presentation.console;

import eapli.framework.actions.Action;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class ShowMessageAction implements Action {

    private final String message;

    public ShowMessageAction(String message) {
	this.message = message;
    }

    @Override
    public boolean execute() {
	System.out.println(message);
	return true;
    }
}
