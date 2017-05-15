/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.domain.cafeteria;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author arocha
 */
public class OrganicUnitTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    public OrganicUnitTest() {
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
        new OrganicUnit("", "Instituto", "Porto");
    }

    @Test(expected = IllegalStateException.class)
    public void testAcronymMustNotBeNull() {
        System.out.println("must have an acronym");
        new OrganicUnit(null, "Instituto", "Porto");
    }

    /**
     * Test of is method, of class OrganicUnit.
     */
    @Test
    public void testIs() {
        System.out.println("Attest 'is' method - Normal Behaviour");
        final String id = "ISEP";
        final OrganicUnit instance = new OrganicUnit(id, "Instituto", "Porto");
        final boolean expResult = true;
        final boolean result = instance.is(id);
        assertEquals(expResult, result);
    }
}
