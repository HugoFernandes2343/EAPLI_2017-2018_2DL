/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.dishes.NutricionalInfo;
import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.framework.domain.Designation;
import eapli.framework.domain.money.Money;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author 1161569
 */
public class MealTest {
    
    private Material material;
    private Lot lot;
    private Meal meal;
    private DishType peixe;
    private Dish dish;
    private MealType lunch;
    private NutricionalInfo aNutricionalInfo;
    private final Designation prego = Designation.valueOf("Prego");
    
    public MealTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        material=new Material("eggs","This his an turkey egg");
        lot=new Lot("0x01",material);
        peixe = new DishType("Peixe", "Peixe");
        aNutricionalInfo = new NutricionalInfo(10, 11);
        lunch= new MealType(MealType.MealTypes.LUNCH);
        dish = new Dish(peixe, prego, aNutricionalInfo, Money.euros(8));
        meal=new Meal(dish, lunch, Calendar.getInstance(), null);
        meal.registerLot(lot);
    }
    
    @After
    public void tearDown() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLotRegistrationDuplication() {
        System.out.println("must not have lot already registered");
        meal.registerLot(lot);
    }
}
