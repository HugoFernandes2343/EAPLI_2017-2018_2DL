/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.CloseMealPlanController;
import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.kitchen.MealPlanItem;
import eapli.ecafeteria.persistence.MealPlanItemRepository;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import eapli.framework.util.DateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Cerqueira
 */
public class CloseMealPlanUI extends AbstractUI{
    
    private CloseMealPlanController controller = new CloseMealPlanController();

    @Override
    protected boolean doShow() {
        boolean validar;

        List<MealPlan> lmp = (List) controller.getMealPlans();

        if (lmp.isEmpty()) {
            System.out.println("There are no open menuPlans");
        } else {

            for (int i = 0; i < lmp.size(); i++) {
                System.out.println((i + 1) + "- " + lmp.get(i).toString());
            }

            int y;
            do {
                y = Console.readInteger("Choose the menuPlan you want to close:");
            } while (y < 0 || y > lmp.size());

            MealPlan mp = lmp.get(y - 1);

            validar = controller.validateMealPlan(mp);
            
            if (validar == false) {
                System.out.println("It was not possible to close the menu plan.");
            } else {
                
                    controller.changeStatus(mp);
                try {
                    
                    controller.saveMealPlan(mp);

                } catch (DataConcurrencyException ex) {
                    Logger.getLogger(CloseMealPlanUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DataIntegrityViolationException ex) {
                    Logger.getLogger(CloseMealPlanUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("The menu plan was closed successfully. ");
            }

        }
        return true;
    }

    @Override
    public String headline() {
        return "Close the menu plan for this week's menu.";
    }


}
