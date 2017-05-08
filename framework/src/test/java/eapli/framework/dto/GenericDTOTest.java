/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.framework.dto;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class GenericDTOTest {

    public GenericDTOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
	System.out.println("GenericDTO");

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

    @Test(expected = IllegalArgumentException.class)
    public void ensureTypeIsNotNull() {
	System.out.println("ensureTypeIsNotNull");
	new GenericDTO(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTypeIsNotEmpty() {
	System.out.println("ensureTypeIsNotEmpty");
	new GenericDTO("");
    }
}
