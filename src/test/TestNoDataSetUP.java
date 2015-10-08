package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import productParts.ExceptionHandler;
import productParts.ProductPartsDB;

public class TestNoDataSetUP {

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
		testObject = null;
	}

	
	// Test No: testObjNotNull
	// Objective: Test that a productPartsDB Object exists
	// Inputs: NONE
	// Expected Output: testObject is not null
	@Test
	public void testObjNotNull(){
		
		assertNotNull(testObject);	 

	}
	
	// Test No: testConnection
	// Objective: Test that a productPartsDB connects to the database is valid
	// Inputs: call the testObject
	// Expected Output: testObject connects to the database
	@Test
	public void testConnection() throws ExceptionHandler{

		assertEquals(true, testObject.dbIsConnected());
	}
	
	// Test No: testNotValidIDPos
	// Objective: Test that a number is not a valid database product id
	// Inputs: Table name: "product" Id number: 8888888
	// Expected Output: false
	@Test
	public void testNotValidIDPos() throws ExceptionHandler{

		assertEquals(false, testObject.validId("product",88888888)); 
	}

	// Test No: testNotValidIDNeg
	// Objective: Test that a number is not a valid (minus) database product id
	// Inputs: Table name: "product" Id number: -8888888
	// Expected Output: false
	@Test
	public void testNotValidIDNeg() throws ExceptionHandler{

		assertEquals(false, testObject.validId("parts",-8888888));
	}	
	
	// Test No: testWrongTableName
	// Objective: Test that a the wrong table name is entered
	// Inputs: Table name: "wrongtable" Id number: 8888888
	// Expected Output: Exception thrown. An error of type 'Valid Id: Database Error' expected
	@Test(expected= ExceptionHandler.class)
	public void testWrongTableName() throws ExceptionHandler{

		try {
			assertEquals(false, testObject.validId("wrongtable",8888888));
			fail("Exception expected .....");
		} catch (ExceptionHandler e) {
			// Assert that the message form the error is the right error message
			assertEquals("Valid Id: Database Error", new String(e.getMessage().substring(0, 24)));
			// throw the error to be caught by the @Test notation
			throw e;
		} 

	}
	
	// Test No: testEmptyProdList
	// Objective: Test that the printProduct function returns no data and
	// is equal to 0 as there is no data available
	// Inputs: 
	// Expected Output: 0 i.e. no data
	@Test
	public void testEmptyProdList() throws ExceptionHandler{

		assertEquals(0, testObject.printProducts().size());
	}
	
	// Test No: testEmptyPartsList
	// Objective: Test that the printParts function returns no data and
	// is equal to 0 as there is no data available
	// Inputs: 
	// Expected Output: 0 i.e. no data
	@Test
	public void testEmptyPartsList() throws ExceptionHandler{

		assertEquals(0, testObject.printParts().size());
	}

	// Test No: testEmptyProdTable
	// Objective: Test that the product table is empty
	// Inputs: table name - PRODUCT
	// Expected Output: true
	@Test
	public void testEmptyProdTable() throws ExceptionHandler{

		assertEquals(true, testObject.checkIsTableEmpty("PRODUCT"));
		// Explicitly calling the close connection method
		testObject.closePreStmtConnections();
	}
	
	// Test No: testEmptyPartsTable
	// Objective: Test that the parts table is empty
	// Inputs: table name - parts
	// Expected Output: true
	@Test
	public void testEmptyPartsTable() throws ExceptionHandler{

		assertEquals(true, testObject.checkIsTableEmpty("parts"));
		// Explicitly calling the close connection method
		testObject.closePreStmtConnections();
	}
	
	// Test No: testEmptyPartsTableInvalidName
	// Objective: Test that the method gives an error if given an invalid name
	// Inputs: table name - WrongNameOfTable
	// Expected Output: true
	@Test(expected= ExceptionHandler.class)
	public void testEmptyPartsTableInvalidName() throws ExceptionHandler{
		
		try {
			assertEquals(true, testObject.checkIsTableEmpty("WrongNameOfTable"));
			// Explicitly calling the close connection method
			testObject.closePreStmtConnections();
			fail("Exception expected .....");
		} catch (ExceptionHandler e) {
			// Assert that the message form the error is the right error message
			assertEquals("Check Is Table Empty Error", new String(e.getMessage().substring(0, 26)));
			// throw the error to be caught by the @Test notation
			throw e;
		} 
		
	}
	
}
