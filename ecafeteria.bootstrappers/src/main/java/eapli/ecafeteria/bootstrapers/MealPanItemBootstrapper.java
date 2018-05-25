/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.kitchen.RegisterMealPlanItemController;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Norberto Sousa - 1120608
 */
public class MealPanItemBootstrapper implements Action {

    private final RegisterMealPlanItemController cont = new RegisterMealPlanItemController();
    private final MealRepository mRP = PersistenceContext.repositories().meals();

    @Override
    public boolean execute() {
        ArrayList<Meal> list = new ArrayList<>();
        Calendar tomorrow = DateTime.now();
        tomorrow.add(Calendar.DATE, 1);
        list = (ArrayList<Meal>) mRP.findMealsByDateAndMealType(tomorrow, new MealType(MealType.MealTypes.LUNCH));
        Meal m1 = null;
        Meal m2 = null;
        Meal m3 = null;
        for (Meal m : list) {
            if (m.dish().is(Designation.valueOf("Hamburger"))) {
                m1 = m;
            }
            if (m.dish().is(Designation.valueOf("picanha"))) {
                m2 = m;
            }
            if (m.dish().is(Designation.valueOf("tofu grelhado"))) {
                m3 = m;
            }
        }
        
        try {
            cont.registerMealPlanItem(m1, 5);
            cont.registerMealPlanItem(m2, 5);
            cont.registerMealPlanItem(m3, 5);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            System.out.println("Erro a registar MealPlanItems");
        }
        
        return true;
    }
   

}
