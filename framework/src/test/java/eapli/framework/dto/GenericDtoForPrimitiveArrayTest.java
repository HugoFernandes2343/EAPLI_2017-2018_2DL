/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.framework.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Array;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class GenericDtoForPrimitiveArrayTest {

    private static int INT_FIELD_VALUE = 3;
    private static final WithPrimitiveArrayClass subject = new WithPrimitiveArrayClass(INT_FIELD_VALUE);
    private static GenericDTO instance;

    public GenericDtoForPrimitiveArrayTest() {
    }

    @BeforeClass
    public static void setUpClass() {
	System.out.println("GenericDtoForPrimitiveArrayTest");

	instance = GenericDTO.of(subject);

	System.out.println("===========");
	System.out.println(instance);
	System.out.println(instance.type());
	for (final Map.Entry<String, Object> e : instance.entrySet()) {
	    System.out.println("[" + e.getKey() + "] => [" + e.getValue() + "]");
	}
	System.out.println("===========");
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

    private static class WithPrimitiveArrayClass {
	private final int[] data;

	public WithPrimitiveArrayClass(int n) {
	    this.data = new int[n];
	    for (int i = 0; i < n; i++) {
		this.data[i] = i;
	    }
	}
    }

    @Test
    public void ensureType() {
	System.out.println("ensureType");

	assertEquals("Name of type is incorrect", subject.getClass().getName(), instance.type());
    }

    @Test
    public void ensureDTOHas1Field() {
	System.out.println("ensureDTOHas1Field");

	assertEquals("Number of fields is incorrect", 1, instance.size());
    }

    @Test
    public void ensureArrayFieldIsArray() {
	System.out.println("ensureArrayFieldIsArray");

	assertTrue("'data' is not an array", instance.get("data").getClass().isArray());
    }

    @Test
    public void ensureArrayFieldHasAllMembers() {
	System.out.println("ensureArrayFieldHasAllMembers");

	assertEquals("'data' list is missing elements", INT_FIELD_VALUE, Array.getLength(instance.get("data")));
    }

    @Test
    public void ensureArrayFieldIsTransformed() {
	System.out.println("ensureArrayFieldIsTransformed");

	int i = 0;
	for (final int e : (int[]) (instance.get("data"))) {
	    assertEquals("ith member is incorrect", i, e);
	    i++;
	}
    }
}
