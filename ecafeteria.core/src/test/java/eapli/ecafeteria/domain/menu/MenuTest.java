///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package eapli.ecafeteria.domain.menu;
//
//import eapli.ecafeteria.domain.meals.Meal;
//import java.util.Calendar;
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author jsant
// */
//public class MenuTest {
//    
//    public MenuTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of publishedMenu method, of class Menu.
//     */
//    @Test
//    public void testPublishedMenu() {
//        System.out.println("publishedMenu");
//        Menu instance = new Menu();
//        boolean expResult = false;
//        boolean result = instance.publishedMenu();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of workingMenu method, of class Menu.
//     */
//    @Test
//    public void testWorkingMenu() {
//        System.out.println("workingMenu");
//        Menu instance = new Menu();
//        boolean expResult = false;
//        boolean result = instance.workingMenu();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of listMeals method, of class Menu.
//     */
//    @Test
//    public void testListMeals() {
//        System.out.println("listMeals");
//        Menu instance = new Menu();
//        List<Meal> expResult = null;
//        List<Meal> result = instance.listMeals();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of containsMeal method, of class Menu.
//     */
//    @Test
//    public void testContainsMeal() {
//        System.out.println("containsMeal");
//        Meal m = null;
//        Menu instance = new Menu();
//        boolean expResult = false;
//        boolean result = instance.containsMeal(m);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addMeal method, of class Menu.
//     */
//    @Test
//    public void testAddMeal() {
//        System.out.println("addMeal");
//        Meal meal = null;
//        Menu instance = new Menu();
//        boolean expResult = false;
//        boolean result = instance.addMeal(meal);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of removeMeal method, of class Menu.
//     */
//    @Test
//    public void testRemoveMeal() {
//        System.out.println("removeMeal");
//        Meal meal = null;
//        Menu instance = new Menu();
//        boolean expResult = false;
//        boolean result = instance.removeMeal(meal);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of startDate method, of class Menu.
//     */
//    @Test
//    public void testStartDate() {
//        System.out.println("startDate");
//        Menu instance = new Menu();
//        Calendar expResult = null;
//        Calendar result = instance.startDate();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of finishDate method, of class Menu.
//     */
//    @Test
//    public void testFinishDate() {
//        System.out.println("finishDate");
//        Menu instance = new Menu();
//        Calendar expResult = null;
//        Calendar result = instance.finishDate();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of hashCode method, of class Menu.
//     */
//    @Test
//    public void testHashCode() {
//        System.out.println("hashCode");
//        Menu instance = new Menu();
//        int expResult = 0;
//        int result = instance.hashCode();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of id method, of class Menu.
//     */
//    @Test
//    public void testId() {
//        System.out.println("id");
//        Menu instance = new Menu();
//        Long expResult = null;
//        Long result = instance.id();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of compareID method, of class Menu.
//     */
//    @Test
//    public void testCompareID() {
//        System.out.println("compareID");
//        Long id = null;
//        Menu instance = new Menu();
//        boolean expResult = false;
//        boolean result = instance.compareID(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of equals method, of class Menu.
//     */
//    @Test
//    public void testEquals() {
//        System.out.println("equals");
//        Object object = null;
//        Menu instance = new Menu();
//        boolean expResult = false;
//        boolean result = instance.equals(object);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class Menu.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Menu instance = new Menu();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of sameAs method, of class Menu.
//     */
//    @Test
//    public void testSameAs() {
//        System.out.println("sameAs");
//        Object other = null;
//        Menu instance = new Menu();
//        boolean expResult = false;
//        boolean result = instance.sameAs(other);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//}
