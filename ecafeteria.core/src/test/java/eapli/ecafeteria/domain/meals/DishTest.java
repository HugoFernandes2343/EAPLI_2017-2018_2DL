package eapli.ecafeteria.domain.meals;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import eapli.framework.domain.Designation;
import eapli.framework.domain.Money;
import static org.junit.Assert.*;
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

    @Test(expected = IllegalStateException.class)
    public void testDishTypeMustNotBeNull() {
        System.out.println("must have an Dish type");
        Dish instance = new Dish(null, prego, aNutricionalInfo, Money.euros(8));
    }

    @Test(expected = IllegalStateException.class)
    public void testNameMustNotBeNull() {
        System.out.println("must have an name");
        Dish instance = new Dish(peixe, null, aNutricionalInfo, Money.euros(5));
    }

    @Test(expected = IllegalStateException.class)
    public void testNutricionalInfoMustNotBeNull() {
        System.out.println("must have an Nutricional Info");
        Dish instance = new Dish(peixe, prego, null, Money.euros(7));
    }

    /**
     * Test of is method, of class Dish.
     */
    @Test
    public void testIs() {
        System.out.println("Attest 'is' method - Normal Behaviour");
        Dish instance = new Dish(peixe, prego, aNutricionalInfo, Money.euros(5));
        boolean expResult = true;
        boolean result = instance.is(prego);
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
