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
public class ShowVerticalSubMenuAction implements Action {

    private final Menu menu;
    private final VerticalMenuRenderer renderer;

    public ShowVerticalSubMenuAction(Menu menu) {
	this.menu = menu;
	this.renderer = new VerticalMenuRenderer(menu);
    }

    @Override
    public boolean execute() {
	System.out.println("\n>> " + this.menu.title());
	this.renderer.show();
	return false;
    }
}
