/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.ElaborateMealPlanController;
import eapli.framework.application.Controller;

/**
 *
 * @author Paulo Jorge
 */
public class ElaborateUI {
    
    private final ElaborateMealPlanController theController = new ElaborateMealPlanController();

    
    public void show(){
        System.out.println(theController.fetchAvailableMenus()); 
    }
}
