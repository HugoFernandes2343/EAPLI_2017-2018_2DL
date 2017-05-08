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

import eapli.util.Collections;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class GenericDtoForCollectionTest {

    private static String STRING_FIELD_VALUE = "abc";
    private static int INT_FIELD_VALUE = 3;
    private static int N_SAMPLES = 5;
    private static List<SimpleClass> subject;
    private static Iterable<GenericDTO> instance;

    public GenericDtoForCollectionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
	System.out.println("GenericDtoForCollectionClassTest");

	subject = new ArrayList<>();
	for (int i = 0; i < N_SAMPLES; i++) {
	    final SimpleClass sample = new SimpleClass(STRING_FIELD_VALUE + i, INT_FIELD_VALUE + i);
	    subject.add(sample);
	}

	instance = GenericDTO.ofMany(subject);

	System.out.println("===========");
	System.out.println(instance);
	for (final GenericDTO o : instance) {
	    for (final Map.Entry<String, Object> e : o.entrySet()) {
		System.out.println("[" + e.getKey() + "] => [" + e.getValue() + "]");
	    }
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
	    stringField = s;
	    intField = i;
	}
    }

    @Test
    public void ensureListHasAllMembers() {
	System.out.println("ensureListHasAllMembers");

	final int n = Collections.size(instance);
	assertEquals("list is missing elements", N_SAMPLES, n);
    }

    @Test
    public void ensureListFieldIsTransformed() {
	System.out.println("ensureListFieldIsTransformed");

	int i = 0;
	for (final GenericDTO e : instance) {
	    assertEquals("'intField' is incorrect", INT_FIELD_VALUE + i, e.get("intField"));
	    assertEquals("'stringField' is incorrect", STRING_FIELD_VALUE + i, e.get("stringField"));

	    i++;
	}
	assertTrue(true);
    }
}
