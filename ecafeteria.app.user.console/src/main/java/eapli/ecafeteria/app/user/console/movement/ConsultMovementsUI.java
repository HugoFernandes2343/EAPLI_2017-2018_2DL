/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.movement;

import eapli.ecafeteria.application.cafeteriauser.ConsultMovementsController;
import eapli.ecafeteria.domain.movement.Movement;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 *
 * @author João Vieira
 */
public class ConsultMovementsUI extends AbstractUI{

    private final ConsultMovementsController theController = new ConsultMovementsController();
    
     protected Controller controller() {
        return this.theController;
    }
    
    @Override
    protected boolean doShow() {
        System.out.println("+= Your Movements =+");
        try{
        Iterable<Movement> movementList = this.theController.findUserMovements();
        
        
        for(Movement m : movementList){
            System.out.println(m);
        }
        
        Console.readLine("");
        }catch(IllegalStateException ex){
            System.out.println(ex.getMessage());
        }
        
        return true;
    }

    @Override
    public String headline() {
        return "Consult Movements";
    }
    
}
