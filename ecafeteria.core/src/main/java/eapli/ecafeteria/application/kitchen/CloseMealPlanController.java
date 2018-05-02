/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.Kitchen.MealPlan;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author Cerqueira
 */
public class CloseMealPlanController {
    
//    private final MealPlanRepository repository = PersistenceContext.repositories().mealPlan();

//    public Iterable<MealPlan> listMealPlan(){
//        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
//        return this.repository.findAll();
//    }
    
    public void closeMealPlan(MealPlan mealPlan) {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        mealPlan.closeMealPlan();
    }
    
}
