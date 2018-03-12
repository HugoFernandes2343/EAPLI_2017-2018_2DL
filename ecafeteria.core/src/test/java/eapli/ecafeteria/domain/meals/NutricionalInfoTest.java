package eapli.ecafeteria.domain.meals;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import eapli.ecafeteria.domain.dishes.NutricionalInfo;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author AntónioRocha
 */
public class NutricionalInfoTest {

    public NutricionalInfoTest() {
    }

    /**
     * Test of calories method, of class NutricionalInfo.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCaloriesMustNotBeNull() {
        System.out.println("calories must not be null");
        @SuppressWarnings("unused")
        final NutricionalInfo instance = new NutricionalInfo(null, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCaloriesMustNotBeNegative() {
        System.out.println("calories must not be negative");
        @SuppressWarnings("unused")
        final NutricionalInfo instance = new NutricionalInfo(-1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaltMustNotBeNull() {
        System.out.println("Salt must not be null");
        @SuppressWarnings("unused")
        final NutricionalInfo instance = new NutricionalInfo(0, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaltMustNotBeNegative() {
        System.out.println("Salt must not be negative");
        @SuppressWarnings("unused")
        final NutricionalInfo instance = new NutricionalInfo(0, -6);
    }

    @Test
    public void testNormalBehaviour() {
        System.out.println("normal behaviour");
        @SuppressWarnings("unused")
        final NutricionalInfo instance = new NutricionalInfo(3, 5);
    }

    @Test
    public void testCalories() {
        System.out.println("calories");
        final NutricionalInfo instance = new NutricionalInfo(3, 5);
        final Integer expResult = 3;
        assertEquals(expResult, instance.calories());
    }
}
