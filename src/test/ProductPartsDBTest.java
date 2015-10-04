/**
 * Test Driven Development Tests for the ProductPartsDB Class
 * @author Rory Nee
 */
package test;
import productParts.ExceptionHandler;
import productParts.Product;
import productParts.ProductPartsDB;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ProductPartsDBTest {

	ProductPartsDB testObject = null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		try {
			testObject = new ProductPartsDB();
		} catch (ExceptionHandler e) {
			fail("DBIsConnected Error: Db Not Connected\n"+e.getClass()+" - "+e.getMessage());
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	
	// Test No: PPT001
	// Objective: Test that a productPartsDB Object exists
	// Inputs: NONE
	// Expected Output: testObject is not null
	@Test
	public void testPPDBT001(){
		
		assertNotNull(testObject);	 

	}
	
	// Test No: PPT002
	// Objective: Test that a productPartsDB connects to the database is valid
	// Inputs: call the testObject
	// Expected Output: testObject connects to the database
	@Test
	public void testPPDBT002(){

		try {
			assertEquals(true, testObject.dbIsConnected());
		} catch (ExceptionHandler e) {
			fail(e.getMessage());
		} 

	}
	
	// Test No: PPT003
	// Objective: Test that a number is not a valid database product id
	// Inputs: Table name: "product" Id number: 8888888
	// Expected Output: false
	@Test
	public void testPPDBT003(){

		try {
			assertEquals(false, testObject.validId("product",88888888));
		} catch (ExceptionHandler e) {
			fail(e.getMessage());
		} 

	}

	// Test No: PPT004
	// Objective: Test that a number is not a valid (minus) database product id
	// Inputs: Table name: "product" Id number: -8888888
	// Expected Output: false
	@Test
	public void testPPDBT004(){

		try {
			assertEquals(false, testObject.validId("parts",-8888888));
		} catch (ExceptionHandler e) {
			fail(e.getMessage());
		} 

	}	
	
	// Test No: PPT005
	// Objective: Test that a the wrong table name is entered
	// Inputs: Table name: "wrongtable" Id number: 8888888
	// Expected Output: false
	@Test
	public void testPPDBT005(){

		try {
			assertEquals(false, testObject.validId("wrongtable",8888888));
			fail("Exception expected .....");
		} catch (ExceptionHandler e) {
			assertEquals("Valid Id: Database Error", new String(e.getMessage().substring(0, 24)));
		} 

	}
	
	// Test No: PPT006
	// Objective: Test that that the printProduct function returns no data and
	// is equal to 0 as there is no data available
	// Inputs: 
	// Expected Output: 0 i.e. no data
	@Test
	public void testPPDBT006(){

		try {
			assertEquals(0, testObject.printProducts().size());
		} catch (ExceptionHandler e) {
			fail("Exception expected .....");
		} 

	}
	
	// Test No: PPT007
	// Objective: Test that that the printParts function returns no data and
	// is equal to 0 as there is no data available
	// Inputs: 
	// Expected Output: 0 i.e. no data
	@Test
	public void testPPDBT007(){

		try {
			assertEquals(0, testObject.printParts().size());
		} catch (ExceptionHandler e) {
			fail("Exception expected .....");
		} 

	}
	
	// Test No: PPT008
	// Objective: Test Insertion into the product database.
	// Inputs: name - 'ASUS' description - 'X501U-XX039H 15.6 Laptop - White'
	// Expected Output: 1 noting that the database was changed
	@Test
	public void testPPDBT008() {

		try {
			assertEquals(1, testObject.insertProduct("ASUS","X501U-XX039H 15.6 Laptop - White"));
		} catch (ExceptionHandler e) {
			fail("Exception expected .....");
		} 

	}
	
	// Test No: PPT0009
	// Objective: Test that a number is a valid database product id
	// Inputs: Table name: "product" Id number: 1
	// Expected Output: true
	@Test
	public void testPPDBT009(){

		try {
			assertEquals(true, testObject.validId("product",1));
		} catch (ExceptionHandler e) {
			fail(e.getMessage());
		} 

	}
	
	// Test No: PPT010
	// Objective: Test that that the printProduct function prints data
	// Inputs: 
	// Expected Output: array of products with the first equaling 
	// Product [prodid= 1, name= "ASUS", description= "X501U-XX039H 15.6 Laptop - White"]
	@Test
	public void testPPDBT010(){

		// Improve the test later with insert and delete
		// Also check for null
		Product actualProduct = new Product(1,"ASUS","X501U-XX039H 15.6 Laptop - White");
		
		try {
			assertEquals(actualProduct.toString(),testObject.printProducts().get(0).toString());
		} catch (ExceptionHandler e) {
			fail("Exception expected .....");
		} 

	}
	
	/********************* Test after insert/delete has been developed ***********************************************
	 * need to use before and after so empty table tests work 006,007
	 * Test need to be added when insert/delete have been developed
	 * 
	 * - Test Insertion into the parts database.
	 * - Test that that the printParts function prints data
	 * - search by id
	 * 
	 * handy SQL statements
	 * - ALTER TABLE Product ALTER COLUMN prod_id RESTART WITH 1
	 * - delete from product where prod_id = 1
	 */

}
