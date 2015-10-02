package test;

import productParts.ExceptionHandler;
import productParts.ProductPartsDB;
import junit.framework.TestCase;

/**
 * Test Driven Development Tests for the ProductPartsDB Class
 * @author Rory Nee
 */

public class ProductPartsDBTest extends TestCase {

	ProductPartsDB testObject = null;
	
	/**
	 * Set up the testObject before every test is run
	 */
	protected void setUp() {
		try {
			testObject = new ProductPartsDB();
		} catch (ExceptionHandler e) {
			fail("DBIsConnected Error: Db Not Connected\n"+e.getClass()+" - "+e.getMessage());
		}
	}
	
	// Test No: PPT001
	// Objective: Test that a productPartsDB Object exists
	// Inputs: NONE
	// Expected Output: testObject is not null
	public void testPPDBT001(){
		
		assertNotNull(testObject);	 

	}
	
	// Test No: PPT002
	// Objective: Test that a productPartsDB connects to the database is valid
	// Inputs: call the testObject
	// Expected Output: testObject connects to the database
	public void testPPDBT002(){

		try {
			assertEquals(true, testObject.dbIsConnected());
		} catch (ExceptionHandler e) {
			fail(e.getMessage());
		} 

	}
	
	// Test No: PPT003
	// Objective: Test that a number is a valid database product id
	// Inputs: Table name: "product" Id number: 2
	// Expected Output: true
	public void testPPDBT003(){

		try {
			assertEquals(true, testObject.validId("product",2));
		} catch (ExceptionHandler e) {
			fail(e.getMessage());
		} 

	}
	
	// Test No: PPT004
	// Objective: Test that a number is not a valid database product id
	// Inputs: Table name: "product" Id number: 8888888
	// Expected Output: false
	public void testPPDBT004(){

		try {
			assertEquals(false, testObject.validId("product",88888888));
		} catch (ExceptionHandler e) {
			fail(e.getMessage());
		} 

	}

	// Test No: PPT005
	// Objective: Test that a number is not a valid (minus) database product id
	// Inputs: Table name: "product" Id number: -8888888
	// Expected Output: false
	public void testPPDBT005(){

		try {
			assertEquals(false, testObject.validId("parts",-8888888));
		} catch (ExceptionHandler e) {
			fail(e.getMessage());
		} 

	}	
	
	// Test No: PPT006
	// Objective: Test that a the wrong table name is entered
	// Inputs: Table name: "wrongtable" Id number: 8888888
	// Expected Output: false
	public void testPPDBT006(){

		try {
			assertEquals(false, testObject.validId("wrongtable",8888888));
			fail("Exception expected .....");
		} catch (ExceptionHandler e) {
			assertEquals("Valid Id: Database Error", new String(e.getMessage().substring(0, 24)));
		} 

	}
	

	
}
