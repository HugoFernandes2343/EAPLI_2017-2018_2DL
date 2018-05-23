/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.kitchen.MealPlanItem;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Paulo Jorge
 */
public interface MealPlanItemRepository extends DataRepository<MealPlanItem, Long> {

    public Iterable<MealPlanItem> getMealPlanItemList(MealPlan mp);

    public MealPlanItem findByIDMealPlanItem(Long id);

    public void updateTable(Long id, int quantidades);

    public MealPlanItem findByMeal(Meal m);
}
