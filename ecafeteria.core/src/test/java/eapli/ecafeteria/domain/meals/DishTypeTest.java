/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.dishes.DishType;
import org.junit.*;
import static org.junit.Assert.assertEquals;

/**
 * @author mcn
 */
public class DishTypeTest {

    public DishTypeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void ensureDishTypeHasAnAcronymAndDescription() {
        new DishType("Veg", "Vegetarian");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAcronymMustNotBeEmpty() {
        System.out.println("must have non-empty acronym");
        new DishType("", "vegetarian dish");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAcronymMustNotBeNull() {
        System.out.println("must have an acronym");
        new DishType(null, "vegetarian dish");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDescriptionMustNotBeEmpty() {
        System.out.println("must have non-empty description");
        new DishType("veg1", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDescriptionMustNotBeNull() {
        System.out.println("must have a description");
        new DishType("veg1", null);
    }

    /**
     * Test of isActive method, of class DishType.
     */
    @Test
    public void testIsActive() {
        System.out.println("isActive - normal behaviour");

        System.out.println("Attest 'is' method - Normal Behaviour");
        final String acronym = "vege001";
        final DishType instance = new DishType(acronym, "vegetarian dish");
        final boolean expResult = true;
        final boolean result = instance.is(acronym);
        assertEquals(expResult, result);
    }

    /**
     * Test of toogleState method, of class DishType.
     */
    @Test
    public void testChangeDishTypeState() {
        System.out.println("changeDishTypeState");
        final DishType instance = new DishType("vege005", "vegetarian dish");
        instance.toogleState();
        final boolean expResult = false;
        final boolean result = instance.isActive();
        assertEquals(expResult, result);
    }

    /**
     * Test of changeDescriptionTo method, of class DishType.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testchangeDescriptionToMustNotBeNull() {
        System.out.println("ChangeDescriptionTo -New description must not be null");
        final DishType instance = new DishType("vege005", "vegetarian dish");
        instance.changeDescriptionTo(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testchangeDescriptionToMustNotBeEmpty() {
        System.out.println("ChangeDescriptionTo -New description must not be empty");
        final DishType instance = new DishType("vege005", "vegetarian dish");
        instance.changeDescriptionTo("");
    }

    @Test
    public void testChangeDescriptionTo() {
        System.out.println("attest changeDescriptionTo");
        final DishType instance = new DishType("vege005", "vegetarian dish");
        final String newDescription = "new description";
        instance.changeDescriptionTo(newDescription);
        final String expResult = newDescription;
        final String result = instance.description();
        assertEquals(expResult, result);
    }

    /**
     * Test of id method, of class DishType.
     */
    @Test
    public void testId() {
        System.out.println("id");
        final String acronym = "veg";
        final DishType instance = new DishType(acronym, "vegetarian dish");
        final boolean expResult = true;
        final boolean result = acronym.equals(instance.id());
        assertEquals(expResult, result);

    }

    /**
     * Test of is method, of class DishType.
     */
    @Test
    public void testIs() {
        System.out.println("Attest is method");
        final String id = "veg";
        final String description = "vegetarian dish";
        final DishType instance = new DishType(id, description);
        final boolean expResult = true;
        final boolean result = instance.is(id);
        assertEquals(expResult, result);
    }
}
