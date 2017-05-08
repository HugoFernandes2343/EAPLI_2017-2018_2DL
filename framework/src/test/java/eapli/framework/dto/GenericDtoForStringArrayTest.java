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
public class GenericDtoForStringArrayTest {

    private static String STRING_FIELD_VALUE = "abc";
    private static int INT_FIELD_VALUE = 3;
    private static final WithPrimitiveArrayClass subject = new WithPrimitiveArrayClass(INT_FIELD_VALUE,
	    STRING_FIELD_VALUE);
    private static GenericDTO instance;

    public GenericDtoForStringArrayTest() {
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
	private final String[] data;

	public WithPrimitiveArrayClass(int n, String seed) {
	    data = new String[n];
	    for (int i = 0; i < n; i++) {
		data[i] = seed + i;
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
    public void ensureArrayFieldIsList() {
	System.out.println("ensureArrayFieldIsList");

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
	for (final String e : (String[]) (instance.get("data"))) {
	    assertEquals("ith member is incorrect", STRING_FIELD_VALUE + i, e);
	    i++;
	}
    }
}
