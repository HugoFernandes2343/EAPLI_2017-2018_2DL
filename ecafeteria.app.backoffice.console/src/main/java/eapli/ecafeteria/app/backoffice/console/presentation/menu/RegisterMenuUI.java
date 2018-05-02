/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.ecafeteria.application.meal.ListMealService;
import eapli.ecafeteria.application.menu.RegisterMenuController;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class RegisterMenuUI extends AbstractUI {

    private RegisterMenuController rmc = new RegisterMenuController();
    private ListMealService lms = new ListMealService();

    @Override
    protected boolean doShow() {

        System.out.println(headline() + "\n\n");

        List<Meal> lm = (List<Meal>) lms.allMeals();

        if (lm.isEmpty()) {
            System.out.println("No working menus at the moment.");
            return false;
        } else {
            int op = -1;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (op != 0) {

                SelectWidget<Meal> mchooser = new SelectWidget("\n\nMeals available to publish:\n", lm);
                mchooser.show();

                rmc.addMeal(mchooser.selectedElement());
                
                
                System.out.println("0 para terminar - outro número para continuar");
                
                
                
                System.out.print("Enter String");
                
                try {
                    op= Integer.parseInt(br.readLine());
                } catch (IOException ex) {
                    Logger.getLogger(RegisterMenuUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
            }

            try {
                rmc.saveMenu();
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                Logger.getLogger(PublishMenuUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        return false;
       
       
    }

    @Override
    public String headline() {
        return "REGISTER MENU";

    }

}
