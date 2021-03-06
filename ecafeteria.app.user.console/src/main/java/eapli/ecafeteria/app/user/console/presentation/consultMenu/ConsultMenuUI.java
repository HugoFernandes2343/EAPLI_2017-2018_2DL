/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.consultMenu;

import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;
import eapli.ecafeteria.application.menu.ListMenuController;

/**
 *
 * @author Joel Costa <1130270@isep.ipp.pt>
 */
public class ConsultMenuUI extends AbstractListUI<Menu> {

    private final ListMenuController theController = new ListMenuController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected Iterable<Menu> elements() {
        return this.theController.listAll();
    }

    @Override
    protected Visitor<Menu> elementPrinter() {
        return new MenuPrinter();
    }

    @Override
    protected String elementName() {
        return "consult the menu of the current and next week";
    }

    @Override
    protected String listHeader() {
         return "Menu:";
    }

}
