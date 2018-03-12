package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.dishes.NutricionalInfo;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.framework.domain.Designation;
import eapli.framework.domain.money.Money;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author AntónioRocha
 */
public class DishTest {

    private DishType peixe;
    private NutricionalInfo aNutricionalInfo;
    private final Designation prego = Designation.valueOf("Prego");

    public DishTest() {
    }

    @Before
    public void setUp() {
        peixe = new DishType("Peixe", "Peixe");
        aNutricionalInfo = new NutricionalInfo(10, 11);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDishTypeMustNotBeNull() {
        System.out.println("must have an Dish type");
        @SuppressWarnings("unused")
        final Dish instance = new Dish(null, prego, aNutricionalInfo, Money.euros(8));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNameMustNotBeNull() {
        System.out.println("must have an name");
        @SuppressWarnings("unused")
        final Dish instance = new Dish(peixe, null, aNutricionalInfo, Money.euros(5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNutricionalInfoMustNotBeNull() {
        System.out.println("must have an Nutricional Info");
        @SuppressWarnings("unused")
        final Dish instance = new Dish(peixe, prego, null, Money.euros(7));
    }

    /**
     * Test of is method, of class Dish.
     */
    @Test
    public void testIs() {
        System.out.println("Attest 'is' method - Normal Behaviour");
        final Dish instance = new Dish(peixe, prego, aNutricionalInfo, Money.euros(5));
        final boolean expResult = true;
        final boolean result = instance.is(prego);
        assertEquals(expResult, result);
    }

    /**
     * Test of changeNutricionalInfoTo method, of class Dish.
     *
     * PRP - 29.mar.2017
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureCannotChangeNutricionalInfoToNull() {
        System.out.println("ChangeNutricionalInfoTo -New nutricional info must not be null");

        final Dish Dishinstance = new Dish(peixe, prego, new NutricionalInfo(1, 1), Money.euros(7));
        Dishinstance.changeNutricionalInfoTo(null);
    }

    /**
     * Tests of changePriceTo method, of class Dish.
     *
     * PRP - 29.mar.2017
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureCannotChangePriceToNull() {
        System.out.println("ChangePriceTo -New price info must not be null");

        final Dish Dishinstance = new Dish(peixe, prego, new NutricionalInfo(1, 1), Money.euros(7));
        Dishinstance.changePriceTo(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCannotChangePriceToNegative() {
        System.out.println("ChangePriceTo -New price can nt be negativel");

        final Dish Dishinstance = new Dish(peixe, prego, new NutricionalInfo(1, 1), Money.euros(1));
        Dishinstance.changePriceTo(Money.euros(-1));
    }
}
