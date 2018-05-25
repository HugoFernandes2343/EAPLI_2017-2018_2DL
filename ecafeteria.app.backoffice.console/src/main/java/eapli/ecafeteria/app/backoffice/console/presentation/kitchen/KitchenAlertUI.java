/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.kitchen.KitchenAlertGenerationController;
import eapli.ecafeteria.domain.kitchen.KitchenAlertWatchdog;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.presentation.console.AbstractUI;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author 1150425
 */
public abstract class KitchenAlertUI extends AbstractUI implements Observer {

    private KitchenAlertWatchdog watchdog;
    
    private Meal m;

    public void defineObservable(KitchenAlertWatchdog watchdog) {
        this.watchdog = watchdog;
    }

    @Override
    public void update(Observable o, Object arg) {
        KitchenAlertGenerationController kag = new KitchenAlertGenerationController();
        this.m = watchdog.sendMeal();
        kag.setMeal(this.m);
        kag.checkThreshold();
    }

    @Override
    protected void drawFormTitle(String title) {
        // drawFormBorder();
        final String titleBorder = BORDER.substring(0, 2) + " " + title;
        System.out.println(titleBorder);
        drawFormBorder();
    }

    @Override
    public String headline() {
        return "eCafeteria Back Office [@" + AuthorizationService.session().authenticatedUser().id()
                + "]";
    }

}
