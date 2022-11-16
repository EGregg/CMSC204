/*
 * Author: Edward Gregg
 * Class: CMSC 204
 * Project: Assignment 4
 * Due Date: Nov ??, 2022
 *
 * 
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * This is the test file for the CourseDBManager
 * which is implemented from the CourseDBManagerInterface
 * 
 */
public class CourseDBManagerTest_STUDENT {
  private CourseDBManagerInterface database =  new CourseDBManager();

  /**
   * Create an instance of CourseDBManager
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
		database = new CourseDBManager();
  }

  /**
   * Set database reference to null
   * @throws Exception
   */
  @After
  public void tearDown() throws Exception {
		database = null;
  }

  /**
   * Test for the add method
   */
  @Test
  public void testAddToDB() {
		try {
			database.add("CMSC203",30522,1,"SC450","Homer Simpson");
			database.add("CMSC203",30535,4,"SC450","Clark Kent");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
  }

  /**
   * Test for the showAll method
   */
  @Test
  public void testShowAll() {
		database.add("CMSC203",30504,4,"SC450","Joey Bag-O-Donuts");
		database.add("CMSC203",30503,4,"SC450","Jill B. Who-Dunit");
		database.add("CMSC204",30559,4,"SC450","BillyBob Jones");
		ArrayList<String> list = database.showAll();
		assertEquals(list.get(0),"\nCourse:CMSC204 CRN:30559 Credits:4 Instructor:BillyBob Jones Room:SC450");
	 	assertEquals(list.get(1),"\nCourse:CMSC203 CRN:30503 Credits:4 Instructor:Jill B. Who-Dunit Room:SC450");
		assertEquals(list.get(2),"\nCourse:CMSC203 CRN:30504 Credits:4 Instructor:Joey Bag-O-Donuts Room:SC450");
  }

  /**
   * Test for the read method
   */
  @Test
  public void testRead() {
		try {
			File inputFile = new File("Test1.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("CMSC203 30504 4 SC450 Joey Bag-O-Donuts");
			inFile.print("CMSC204 30503 4 SC450 Jill B. Who-Dunit");
			
			inFile.close();
			database.readFile(inputFile);
			assertEquals("CMSC203",database.get(30504).getID());
			assertEquals("CMSC204",database.get(30503).getID());
			assertEquals("SC450",database.get(30503).getRoomNum());
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
  }

}
