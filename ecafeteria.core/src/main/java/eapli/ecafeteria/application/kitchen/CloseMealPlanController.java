/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.kitchen.MealPlanItem;
import eapli.ecafeteria.domain.kitchen.MealPlanState;
import eapli.ecafeteria.persistence.MealPlanItemRepository;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.List;

/**
 *
 * @author Cerqueira
 */
public class CloseMealPlanController {

    private Iterable<MealPlan> lmp;

    private final MealPlanRepository mpr = PersistenceContext.repositories().mealPlan();

    private final MealPlanItemRepository mpir = PersistenceContext.repositories().mealPlanItemRepository();

    public Iterable<MealPlan> getMealPlansInProgress() {
        lmp = mpr.findAllMealPlanInProgress();
        return lmp;
    }

    public boolean validateMealPlan(MealPlan mp) {
        return mp.isInTime(this.mpr);
    }

    public void saveMealPlan(MealPlan mp) throws DataConcurrencyException, DataIntegrityViolationException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        mpr.save(mp);
    }

    public void closeMealPlan(MealPlan mp) {
        mp.closeMealPlan();
    }

}
