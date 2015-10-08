package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import productParts.ExceptionHandler;
import productParts.ProductPartsDB;

public class TestDeletions {

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
		testObject.insertPart(1,"HardDrive","500GB Internal HardDrive",199.99);
		testObject = new ProductPartsDB();
	}
	/**
	 * Run this method after each test is run
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		testObject = new ProductPartsDB();
		// Check if something is in the Product table
		// if so delete all data
		if(!testObject.checkIsTableEmpty("PRODUCT")){
			testObject.deleteProductById(1);
		}
		testObject = null;
	}
	
	// Test No: testDeleteByPartsId
	// Objective: Test the deletion of a part from the part table by part id
	// Inputs: partid = 1
	// Expected Output: 1 noting that the database was changed
	@Test
	public void testDeleteByPartsId() throws ExceptionHandler{

		assertEquals(1,testObject.deletePartById(1)); 
	}
	
	// Test No: testDeleteByPartsIdInvalid
	// Objective: Test the deletion of a part from the part table by invalid part id
	// Inputs: partid = 8888
	// Expected Output: 0 noting that no change has happened
	@Test
	public void testDeleteByPartsIdInvalid() throws ExceptionHandler{

		assertEquals(0,testObject.deletePartById(8888)); 
	}
	
	// Test No: testDeleteByProdId
	// Objective: Test the deletion of a product from the product table by prod id
	// Inputs: prodid = 1
	// Expected Output: 1 noting that the database was changed
	@Test
	public void testDeleteByProdId() throws ExceptionHandler{

		assertEquals(1,testObject.deleteProductById(1)); 
	}
	
	// Test No: testDeleteByProdIdInvalid
	// Objective: Test the deletion of a product from the product table by invalid prod id
	// Inputs: prodid = 8888
	// Expected Output: 0 noting that no change has happened
	@Test
	public void testDeleteByProdIdInvalid() throws ExceptionHandler{

		assertEquals(0,testObject.deleteProductById(8888)); 
	}
}
