/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.presentation.console;

import eapli.framework.actions.Action;

/**
 * allows for the composition of menus (Composite pattern)
 *
 * @author Paulo Gandra Sousa
 */
public class SubMenu extends MenuItem {

    public SubMenu(int option, Menu menu, Action a) {
	super(option, menu.title(), a);
    }
}
