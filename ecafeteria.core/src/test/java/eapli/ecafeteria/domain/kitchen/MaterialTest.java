/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author mcn
 */
public class MaterialTest {

    public MaterialTest() {
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

    @Test(expected = IllegalStateException.class)
    public void testAcronymMustNotBeEmpty() {
        System.out.println("must have non-empty acronym");
        new Material("", "");
    }

    @Test(expected = IllegalStateException.class)
    public void testAcronymMustNotBeNull() {
        System.out.println("must have an acronym");
        new Material(null, "");
    }

    @Test
    public void testChangeDescriptionTo() {
        System.out.println("attest changeDescriptionTo");
        final Material instance = new Material("eggs", "Eggs (chicken, duck)");
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
        final String acronym = "eggs";
        final Material instance = new Material(acronym, "eggs");
        final boolean expResult = true;
        final boolean result = acronym.equals(instance.id());
        assertEquals(expResult, result);
    }

    /**
     * Test of is method, of class DishType.
     */
    @Test
    public void testIs() {
        System.out.println("Test is method");
        final String id = "eggs";
        final String description = "eggs";
        final Material instance = new Material(id, description);
        final boolean expResult = true;
        final boolean result = instance.is(id);
        assertEquals(expResult, result);
    }
}
