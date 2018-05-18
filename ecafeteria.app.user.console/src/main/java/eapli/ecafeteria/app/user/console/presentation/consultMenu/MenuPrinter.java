/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eapli.ecafeteria.app.user.console.presentation.consultMenu;


import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Joel Costa <1130270@isep.ipp.pt>
 */
public class MenuPrinter implements Visitor<Menu>{

    @Override
    public void visit(Menu visitee) {
        //Does not work due to changes to menu.
        //Menu does no longer have a meal list
        //If you want to search meal by menu use the MealRepsitory method that searches by menu
    }

    @Override
    public void beforeVisiting(Menu visitee) {
        // nothing to do
    }

    @Override
    public void afterVisiting(Menu visitee) {
        // nothing to do
    }

}
