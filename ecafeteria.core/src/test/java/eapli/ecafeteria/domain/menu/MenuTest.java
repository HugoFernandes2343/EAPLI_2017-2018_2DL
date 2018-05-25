/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.menu;

import eapli.ecafeteria.application.menu.RegisterMenuController;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.dishes.NutricionalInfo;
import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.domain.Designation;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author jsant
 */
public class MenuTest {
    
    private Material material;
    private Lot lot;
    private Meal meal;
    private DishType peixe;
    private Dish dish;
    private MealType lunch;
    private NutricionalInfo aNutricionalInfo;
    private final Designation prego = Designation.valueOf("Prego");
    private final Calendar startDate= Calendar.getInstance();
    private Calendar  finishDate = (Calendar) startDate.clone();
    
    @Before
    public void setUp() throws DataConcurrencyException, DataIntegrityViolationException {
        Calendar startDate= Calendar.getInstance();
        Calendar  finishDate = (Calendar) startDate.clone();
        finishDate.add(Calendar.DAY_OF_MONTH, 6);
        material=new Material("eggs","This his an turkey egg");
        lot=new Lot("0x01",material);
        peixe = new DishType("Peixe", "Peixe");
        aNutricionalInfo = new NutricionalInfo(10, 11);
        lunch= new MealType(MealType.MealTypes.LUNCH);
        dish = new Dish(peixe, prego, aNutricionalInfo, Money.euros(8));
        meal=new Meal(dish, lunch, Calendar.getInstance(), null);
        meal.registerLot(lot);
        
        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testStartDateNull() throws DataConcurrencyException, DataIntegrityViolationException {
        Menu m= new Menu(null, finishDate, Designation.valueOf("Menu David"));    
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testEndingDateNull() throws DataConcurrencyException, DataIntegrityViolationException {
        Menu m= new Menu(startDate, null, Designation.valueOf("Menu David"));    
    }
    
     @Test(expected = java.lang.IllegalArgumentException.class)
    public void testNameNull() throws DataConcurrencyException, DataIntegrityViolationException {
        Menu m= new Menu(startDate, finishDate, null);    
    }
    
    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testMenuStartingDayBeforeToday() throws DataConcurrencyException, DataIntegrityViolationException {
        Calendar beforeToday = (Calendar) startDate.clone();
        beforeToday.add(Calendar.DAY_OF_MONTH, -7);
        Menu m= new Menu(beforeToday, finishDate, Designation.valueOf("Menu David"));    
    }
    
    
    
}
