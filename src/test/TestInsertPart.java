package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import productParts.ExceptionHandler;
import productParts.ProductPartsDB;

public class TestInsertPart {

	ProductPartsDB testObject = null;
	/**
	 * Run this method before each test is run.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		testObject = new ProductPartsDB();
		testObject.insertProduct("ASUS","X501U-XX039H 15.6 Laptop - White");
		testObject = new ProductPartsDB();
	}
	/**
	 * Run this method after each test is run
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		testObject = new ProductPartsDB();
		if(!testObject.checkIsTableEmpty("PRODUCT")){
			testObject.deleteProductById(1);
		}
		testObject = null;
	}
	
	// Test No: testInsertParts
	// Objective: Test Insertion into the parts database.
	// Inputs: prod_id = 1, name - 'HardDrive' description - '500GB Internal HardDrive', cost 199.99
	// Expected Output: 1 noting that the database was changed
	@Test
	public void testInsertParts() throws ExceptionHandler{

		assertEquals(1, testObject.insertPart(1,"HardDrive","500GB Internal HardDrive",199.99));
	}
}
