/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.framework.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
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
public class GenericDtoForCollectionClassTest {

    private static String STRING_FIELD_VALUE = "abc";
    private static int INT_FIELD_VALUE = 3;
    private static final SimpleClass sample = new SimpleClass(STRING_FIELD_VALUE, INT_FIELD_VALUE);
    private static final WithListClass subject = new WithListClass(INT_FIELD_VALUE, sample);
    private static GenericDTO instance;

    public GenericDtoForCollectionClassTest() {
    }

    @BeforeClass
    public static void setUpClass() {
	System.out.println("GenericDtoForCollectionClassTest");

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

    @SuppressWarnings("unused")
    private static class SimpleClass {
	private final String stringField;
	private final int intField;

	public SimpleClass(String s, int i) {
	    this.stringField = s;
	    this.intField = i;
	}
    }

    private static class WithListClass {
	private final int intField;
	private final List<SimpleClass> data = new ArrayList<SimpleClass>();

	public WithListClass(int n, SimpleClass c) {
	    this.intField = n;
	    for (int i = 0; i < this.intField; i++) {
		this.data.add(c);
	    }
	}
    }

    @Test
    public void ensureType() {
	System.out.println("ensureType");

	assertEquals("Name of type is incorrect", subject.getClass().getName(), instance.type());
    }

    @Test
    public void ensureDTOHas2Fields() {
	System.out.println("ensureDTOHas2Fields");

	assertEquals("Name of type is incorrect", 2, instance.size());
    }

    @Test
    public void ensureListFieldIsList() {
	System.out.println("ensureListFieldIsList");

	assertTrue("'data' is not a List", List.class.isAssignableFrom(instance.get("data").getClass()));
    }

    @Test
    public void ensureListFieldHasAllMembers() {
	System.out.println("ensureListFieldHasAllMembers");

	assertEquals("'data' list is missing elements", INT_FIELD_VALUE, ((List<?>) (instance.get("data"))).size());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void ensureListFieldIsTransformed() {
	System.out.println("ensureListFieldIsTransformed");

	for (final GenericDTO e : (List<GenericDTO>) (instance.get("data"))) {
	    assertEquals("'intField' is incorrect", INT_FIELD_VALUE, e.get("intField"));
	    assertEquals("'stringField' is incorrect", STRING_FIELD_VALUE, e.get("stringField"));
	}
	assertTrue(true);
    }

    @Test
    public void ensureIntFieldIsTransformed() {
	System.out.println("ensureIntFieldIsTransformed");

	assertEquals("'intField' is incorrectly transformed", INT_FIELD_VALUE, instance.get("intField"));
    }
}
