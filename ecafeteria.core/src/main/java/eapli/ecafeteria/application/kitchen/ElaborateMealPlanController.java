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
import eapli.ecafeteria.persistence.MealPlanItemRepository;
import eapli.ecafeteria.persistence.MealPlanRepository;

import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

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
    private final MealPlanItemRepository mealPlanItemRepository = PersistenceContext.repositories().mealPlanItemRepository();
    private List<Menu> listaMenu = new ArrayList<>();
    private List<MealPlan> listMealPlan = new ArrayList<>();
    private Menu menu;

    public List<Menu> fetchAvailableMenus() {
        listaMenu = new ArrayList<>((Collection<? extends Menu>) listMenuService.allMenus());
        return listaMenu;
    }

    public List<MealPlan> findAllMealPlanInProgress() {
        listMealPlan = (List<MealPlan>) repository.findAllMealPlanInProgress();
        return listMealPlan;
    }

    public boolean verificarID(long id) {

        for (Menu m : listaMenu) {
            long valor = m.id();
            if (valor == id) {
                System.out.println("\nExiste!!!!!!!\n");
                return true;
            }
        }
        System.out.println("\nNão Existe!!!\n");
        return false;
    }

    public Menu selectMenu(long id) {
        menu = listMenuService.findByID(id);
        return menu;
    }

    public List<Meal> getMealsfromMenu() {
        List<Meal> listMeals = new ArrayList<Meal>();
        listMeals = (List<Meal>) PersistenceContext.repositories().meals().findMealOneMenu(menu);
        return listMeals;
    }

    public MealPlan createElaborateMealplan() throws DataConcurrencyException, DataIntegrityViolationException {
        MealPlan mealplan = new MealPlan(menu);
        repository.save(mealplan);
        return mealplan;
    }

    public void insertQuantityMeal(int quantidade, Meal meal, MealPlan mp) throws DataConcurrencyException, DataIntegrityViolationException {
        MealPlanItem mealPlanItem = new MealPlanItem(mp, meal, quantidade);
        mealPlanItemRepository.save(mealPlanItem);

    }

}
