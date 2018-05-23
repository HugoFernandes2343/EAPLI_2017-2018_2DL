/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.reservations;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.dishes.NutricionalInfo;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.domain.Designation;
import eapli.framework.domain.money.Money;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hugo
 */
public class ReservationTest {
    
    private DishType peixe;
    private Dish dish;
    private MealType lunch;
    private MealType dinner;
    private Meal dinnerMealLessThen16;
    private Meal lunchMealLessThen10;
    private Meal dinnerMealMoreThen16;
    private Meal lunchMealMoreThen10;
    private Menu menu;
    private Reservation dinnerReserv;
    private Reservation lunchReserv;
    private NutricionalInfo aNutricionalInfo;
    private final Designation prego = Designation.valueOf("Prego");
    private Calendar endDate;
    
    
    public ReservationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        peixe = new DishType("Peixe", "Peixe");
        aNutricionalInfo = new NutricionalInfo(10, 11);
        lunch= new MealType(MealType.MealTypes.LUNCH);
        dinner= new MealType(MealType.MealTypes.DINNER);
        endDate = Calendar.getInstance();
        endDate.set(Calendar.DAY_OF_MONTH,29);
        dish = new Dish(peixe, prego, aNutricionalInfo, Money.euros(8));
        lunchMealLessThen10=new Meal(dish, lunch, Calendar.getInstance(), null);
        dinnerMealLessThen16=new Meal(dish, dinner, Calendar.getInstance(), null);
        lunchMealMoreThen10=new Meal(dish, lunch, endDate, null);
        dinnerMealMoreThen16=new Meal(dish, dinner, endDate, null);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testLunchReservationCancellationInLessThen10Hours() {
        lunchReserv=new Reservation("2018/05/23//1",lunchMealLessThen10,null);
        Money value= lunchReserv.acquireRefundValue();
        Money expected=Money.euros(4);
        assertEquals(value.amount(),expected.amount(),0.00);
    }
    
    @Test
    public void testDinnerReservationCancellationInLessThen16Hours() {
        dinnerReserv=new Reservation("2018/05/23//1",dinnerMealLessThen16,null);
        Money value=dinnerReserv.acquireRefundValue();
        Money expected=Money.euros(4);
        assertEquals(value.amount(),expected.amount(),0.00);
    }
    
    @Test
    public void testLunchReservationCancellationInMoreThen10Hours() {
        lunchReserv=new Reservation("2018/05/26//1",lunchMealMoreThen10,null);
        Money value= lunchReserv.acquireRefundValue();
        Money expected=Money.euros(8);
        assertEquals(value.amount(),expected.amount(),0.00);
    }
    
    @Test
    public void testDinnerReservationCancellationInMoreThen16Hours() {
        dinnerReserv=new Reservation("2018/05/26//1",dinnerMealMoreThen16,null);
        Money value=dinnerReserv.acquireRefundValue();
        Money expected=Money.euros(8);
        assertEquals(value.amount(),expected.amount(),0.00);
    }
    
}
