/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.application.menu.ListMenuService;
import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.kitchen.MealPlanItem;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Paulo Jorge
 */
public class ElaborateMealPlanController implements Controller {

    private final ListMenuService listMenuService = new ListMenuService();
    private final MealPlanRepository repository = PersistenceContext.repositories().mealPlan();
    private List<Menu> listaMenu = new ArrayList<>();
    private List<MealPlan> listMealPlan = new ArrayList<>();
    private Menu menu;
    private MealPlan mp;
    private MealPlanItem mpi;

    public List<Menu> fetchAvailableMenus() {
        listaMenu = new ArrayList<>((Collection<? extends Menu>) listMenuService.allMenus());
        return listaMenu;
    }

    public List<MealPlan> findAllMealPlanInProgress() {
        listMealPlan = (List<MealPlan>) repository.findAllMealPlanInProgress();
        return listMealPlan;
    }

    public Menu selectMenu(long id) {
        menu = listMenuService.findByID(id);
        return menu;
    }

    public MealPlan selectMealPlan(String name) {
        mp = repository.findByIDMealPlan(Designation.valueOf(name));
        return mp;
    }

    public List<Meal> getMealsfromMenu() {
        List<Meal> listMeals;
        listMeals = (List<Meal>) PersistenceContext.repositories().meals().findMealByMenu(menu);
        return listMeals;
    }

    public MealPlan createElaborateMealplan(String name) {
        MealPlan mealplan = new MealPlan(menu, Designation.valueOf(name));
        return mealplan;
    }

    public void insertQuantityMeal(int quantidade, Meal meal, MealPlan mp) throws DataConcurrencyException, DataIntegrityViolationException {
        MealPlanItem mealPlanItem = new MealPlanItem(meal, quantidade);
        mp.addItemToList(mealPlanItem);
    }

    public void saveMealPlan(MealPlan mp) throws DataConcurrencyException, DataIntegrityViolationException {
        repository.save(mp);
    }

    public boolean verificarIDMenu(long id) {
        for (Menu m : listaMenu) {
            long valor = m.id();
            if (valor == id) {
                return true;
            }
        }
        return false;
    }

    public boolean verificarIDMealPlan(String id) {

        for (MealPlan mealPlan : listMealPlan) {
            Designation valor = mealPlan.id();
            if (valor.equals(Designation.valueOf(id))) {
                return true;
            }
        }
        return false;
    }

}
