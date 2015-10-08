package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import productParts.ExceptionHandler;
import productParts.ProductPartsDB;

public class TestInsertProduct {

	ProductPartsDB testObject = null;
	/**
	 * Run this method before each test is run.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
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

	// Test No: testInsertProd
	// Objective: Test Insertion into the product database.
	// Inputs: name - 'ASUS' description - 'X501U-XX039H 15.6 Laptop - White'
	// Expected Output: 1 noting that the database was changed
	@Test
	public void testInsertProd() throws ExceptionHandler{

		assertEquals(1, testObject.insertProduct("ASUS","X501U-XX039H 15.6 Laptop - White"));
	}
}
