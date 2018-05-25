/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.movement;

import eapli.ecafeteria.app.user.console.presentation.CafeteriaUserBaseUI;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.application.cafeteriauser.ConsultMovementsController;
import eapli.ecafeteria.domain.movement.Movement;
import eapli.framework.util.Console;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author João Vieira
 */
public class ConsultMovementsUI extends CafeteriaUserBaseUI {

    private final ConsultMovementsController theController = new ConsultMovementsController();

    @Override
    protected boolean doShow() {
        System.out.println("+= Your Movements =+");
        try {
            Iterable<Movement> movementList = this.theController.findUserMovements();

            List<Movement> history = new ArrayList<>();
            for (Movement m : movementList) {
                history.add(m);
            }

            for (int i = history.size() - 1; i >= 0; i--) {
                System.out.println(history.remove(i).toString());
            }

            Console.readLine("");
        } catch (IllegalStateException ex) {
            System.out.println(ex.getMessage());
        }

        return true;
    }

    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }

}
