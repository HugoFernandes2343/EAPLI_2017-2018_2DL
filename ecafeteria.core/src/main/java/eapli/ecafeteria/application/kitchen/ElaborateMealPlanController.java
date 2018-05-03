/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.application.menu.ListMenuService;
import eapli.ecafeteria.domain.Kitchen.MealPlan;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.kitchen.MealPlanItem;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.Designation;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Paulo Jorge
 */
public class ElaborateMealPlanController implements Controller {

    private final ListMenuService listMenuService = new ListMenuService();
    private final MealPlanRepository repository = PersistenceContext.repositories().mealPlan();

    private List<Menu> listaMenu = new ArrayList<>();
    private List<MealPlanItem> listMealPlanItem = new ArrayList<>();
    private Menu menu = new Menu(Calendar.getInstance(), Calendar.getInstance());

    public List<Menu> fetchAvailableMenus() {
        menu.setID(1);
        listaMenu.add(menu);
        // listaMenu = new ArrayList<>((Collection<? extends Menu>) listMenuService.allMenus());
        return listaMenu;
    }

    public boolean verificarID(long id) {

        for (Menu m : listaMenu) {
            long valor = m.id();
            if (valor == id) {
                System.out.println("\nExiste!!!!!!!\n");
                return true;
            }
        }
        return false;
    }

    public List<Meal> getMealsfromMenu(long id) {
        List<Meal> listMeals = new ArrayList<Meal>();
        DishType type1 = new DishType("dish1", "descrição1");
        Money money1 = new Money(1, Currency.getInstance(Locale.ITALY));
        DishType type2 = new DishType("dish2", "descrição2");
        Money money2 = new Money(2, Currency.getInstance(Locale.ITALY));
        DishType type3 = new DishType("dish3", "descrição3");
        Money money3 = new Money(3, Currency.getInstance(Locale.ITALY));
        Meal meal1 = new Meal(new Dish(type1, Designation.valueOf("tipo1"), money1), new MealType(MealType.MealTypes.LUNCH), Calendar.getInstance(), menu);
        Meal meal2 = new Meal(new Dish(type1, Designation.valueOf("tipo2"), money1), new MealType(MealType.MealTypes.LUNCH), Calendar.getInstance(), menu);
        Meal meal3 = new Meal(new Dish(type1, Designation.valueOf("tipo3"), money1), new MealType(MealType.MealTypes.LUNCH), Calendar.getInstance(), menu);
        listMeals.add(meal1);
        listMeals.add(meal2);
        listMeals.add(meal3);

        //  listMeals = (List<Meal>) PersistenceContext.repositories().meals().findMealOneMenu(id);
        return listMeals;
    }

    public void insertQuantityMeal(int quantidade, Meal meal) {
        MealPlanItem mealPlanItem = new MealPlanItem(meal, quantidade);
        listMealPlanItem.add(mealPlanItem);
    }

    public MealPlan createElaborateMealplan() throws DataConcurrencyException, DataIntegrityViolationException {
        MealPlan mealplan = new MealPlan(listMealPlanItem);
        MealPlan mp = repository.save(mealplan);
        return mealplan;
    }

}
