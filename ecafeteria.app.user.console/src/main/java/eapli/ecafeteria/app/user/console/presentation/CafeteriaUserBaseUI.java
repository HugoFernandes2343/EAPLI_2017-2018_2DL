/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation;

import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.movement.MovementBuilder;
import eapli.framework.presentation.console.AbstractUI;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author mcn
 */
public abstract class CafeteriaUserBaseUI extends AbstractUI implements Observer {

    protected abstract CafeteriaUserBaseController controller();
    
    private MovementBuilder mv;

    public void defineObservable(MovementBuilder mv) {
        this.mv = mv;
    }
    
    public String showBalance() {
        return "CURRENT BALANCE OF YOUR USERCARD: " + controller().balance().toString();
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   " + showBalance();
    }

    @Override
    protected void drawFormTitle(String title) {
        // drawFormBorder();
        final String titleBorder = BORDER.substring(0, 2) + " " + title;
        System.out.println(titleBorder);
        drawFormBorder();
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("******************************************************\n");
        System.out.println("**********************Alert Trigerred*****************\n");
        System.out.println("******************************************************\n");
    }

    public void defineObserver(MovementBuilder mvb) {
        mvb.addObserver(this);
    }
   
}
