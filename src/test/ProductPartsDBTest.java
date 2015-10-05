/**
 * Test Driven Development Tests for the ProductPartsDB Class
 * @author Rory Nee
 */
package test;
import productParts.ExceptionHandler;
import productParts.Part;
import productParts.Product;
import productParts.ProductPartsDB;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ProductPartsDBTest{

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
	public void testPPDBT002() throws ExceptionHandler{

		assertEquals(true, testObject.dbIsConnected());
	}
	
	// Test No: PPT003
	// Objective: Test that a number is not a valid database product id
	// Inputs: Table name: "product" Id number: 8888888
	// Expected Output: false
	@Test
	public void testPPDBT003() throws ExceptionHandler{

		assertEquals(false, testObject.validId("product",88888888)); 
	}

	// Test No: PPT004
	// Objective: Test that a number is not a valid (minus) database product id
	// Inputs: Table name: "product" Id number: -8888888
	// Expected Output: false
	@Test
	public void testPPDBT004() throws ExceptionHandler{

		assertEquals(false, testObject.validId("parts",-8888888));
	}	
	
	// Test No: PPT005
	// Objective: Test that a the wrong table name is entered
	// Inputs: Table name: "wrongtable" Id number: 8888888
	// Expected Output: Exception thrown. An error of type 'Valid Id: Database Error' expected
	@Test(expected= ExceptionHandler.class)
	public void testPPDBT005() throws ExceptionHandler{

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
	
	// Test No: PPT006
	// Objective: Test that that the printProduct function returns no data and
	// is equal to 0 as there is no data available
	// Inputs: 
	// Expected Output: 0 i.e. no data
	@Test
	@Ignore
	public void testPPDBT006() throws ExceptionHandler{

		assertEquals(0, testObject.printProducts().size());
	}
	
	// Test No: PPT007
	// Objective: Test that that the printParts function returns no data and
	// is equal to 0 as there is no data available
	// Inputs: 
	// Expected Output: 0 i.e. no data
	@Test
	@Ignore
	public void testPPDBT007() throws ExceptionHandler{

		assertEquals(0, testObject.printParts().size());
	}
	
	// Test No: PPT008
	// Objective: Test Insertion into the product database.
	// Inputs: name - 'ASUS' description - 'X501U-XX039H 15.6 Laptop - White'
	// Expected Output: 1 noting that the database was changed
	@Test
	@Ignore
	public void testPPDBT008() throws ExceptionHandler{

		assertEquals(1, testObject.insertProduct("ASUS","X501U-XX039H 15.6 Laptop - White"));
	}
	
	// Test No: PPT0009
	// Objective: Test that a number is a valid database product id
	// Inputs: Table name: "product" Id number: 1
	// Expected Output: true
	@Test
	public void testPPDBT009() throws ExceptionHandler{

		assertEquals(true, testObject.validId("product",1));
	}
	
	// Test No: PPT010
	// Objective: Test that that the printProduct function prints data
	// Inputs: 
	// Expected Output: array of products with the first equaling 
	// Product [prodid= 1, name= "ASUS", description= "X501U-XX039H 15.6 Laptop - White"]
	@Test
	public void testPPDBT010() throws ExceptionHandler{

		//create the test object
		Product actualProduct = new Product(1,"ASUS","X501U-XX039H 15.6 Laptop - White");
		
		assertEquals(actualProduct.toString(),testObject.printProducts().get(0).toString());
	}

	// Test No: PPT011
	// Objective: Test Insertion into the parts database.
	// Inputs: prod_id = 1, name - 'HardDrive' description - '500GB Internal HardDrive', cost 199.99
	// Expected Output: 1 noting that the database was changed
	@Test
	@Ignore
	public void testPPDBT011() throws ExceptionHandler{

		assertEquals(1, testObject.insertPart(1,"HardDrive","500GB Internal HardDrive",199.99));
	}
	
	// Test No: PPT012
	// Objective: Test Insertion into the parts database with an invalid id number.
	// Inputs: prod_id = 8888, name - 'HardDrive' description - '500GB Internal HardDrive', cost 199.99
	// Expected Output: Exception thrown. An error of type 'Insertion Parts Error: Database Error' expected
	@Test(expected=ExceptionHandler.class)
	public void testPPDBT012() throws ExceptionHandler{

		try {
			assertEquals(1, testObject.insertPart(8888,"HardDrive","500GB Internal HardDrive",199.99));
			fail("Exception expected .....");
		} catch (ExceptionHandler e) {
			// Assert that the message form the error is the right error message
			assertEquals("Insertion Parts Error: Database Error", new String(e.getMessage().substring(0, 37)));
			// throw the error to be caught by the @Test notation
			throw e;
		} 

	}
	
	// Test No: PPT013
	// Objective: Test that a number is a valid database parts id
	// Inputs: Table name: "parts" Id number: 1
	// Expected Output: true
	@Test
	public void testPPDBT013() throws ExceptionHandler{

		assertEquals(true, testObject.validId("parts",1));
	}
	
	// Test No: PPT014
	// Objective: Test that that the printParts function prints data
	// Inputs: 
	// Expected Output: array of products with the first equaling 
	// Part [partid= 1, prodid= 1, name= "HardDrive", description= "500GB Internal HardDrive", cost= 199.99]
	@Test
	public void testPPDBT014() throws ExceptionHandler{

		//create the test object
		Part actualPart = new Part(1,1,"HardDrive","500GB Internal HardDrive",199.99);

		assertEquals(actualPart.toString(),testObject.printParts().get(0).toString());
	}
	
	// Test No: PPT015
	// Objective: Shows all available Products and Parts that are in the database 
	// along with its total cost and the number of parts it contains
	// Inputs:
	// Expected Output: List is parts including costs and number of parts i.e.
	// Product [prodid= 1, name= "ASUS", description= "X501U-XX039H 15.6 Laptop - White", numOfParts= 1, totalCostOfParts= 199.99]
	@Test
	public void testPPDBT015() throws ExceptionHandler{
	
		Product resultProduct = testObject.printTotalProducts().get(0);
		assertEquals(1,resultProduct.getProdid());
		assertEquals(1,resultProduct.getNumOfParts());
		assertEquals(199.99,resultProduct.getTotalCostOfParts(), 0.01);
	} 
	
	// Test No: PPT016
	// Objective: Test search by product id with a valid product id
	// Inputs: prodid = 1
	// Expected Output: List of parts associated to a product id of 1 where the following part
	// is first in the list
	// Part [partid= 1, prodid= 1, name= "HardDrive", description= "500GB Internal HardDrive", cost= 199.99]
	@Test
	public void testPPDBT016() throws ExceptionHandler{

		//create the test object
		Part actualPart = new Part(1,1,"HardDrive","500GB Internal HardDrive",199.99);

		assertEquals(actualPart.toString(),testObject.searchProductById(1).get(0).toString());
	}

	// Test No: PPT017
	// Objective: Test search by product id with a invalid product id
	// Inputs: prodid = 8888
	// Expected Output: 0 as there is not product with id 8888 
	@Test
	public void testPPDBT017() throws ExceptionHandler{

		assertEquals(0,testObject.searchProductById(8888).size()); 
	}

	// Test No: PPT018
	// Objective: Test the addition of the cost of parts for of a chosen product id
	// Inputs: prodid = 1
	// Expected Output: 199.99
	@Test
	public void testPPDBT018() throws ExceptionHandler{

		assertEquals(199.99,testObject.sumPartsByProductId(1),0.01); 
	}
	
	// Test No: PPT019
	// Objective: Test the addition of the cost of parts for of a chosen invalid product id
	// Inputs: prodid = 8888
	// Expected Output: 0
	@Test
	public void testPPDBT019() throws ExceptionHandler{

		assertEquals(0,testObject.sumPartsByProductId(8888),0.01); 
	}
	
	/********************* Test after insert/delete has been developed ***********************************************
	 * need to use before and after so empty table tests work 006,007
	 * All Tests need run after insert/delete have been developed
	 * 
	 * handy SQL statements
	 * - ALTER TABLE Product ALTER COLUMN prod_id RESTART WITH 1
	 * - delete from product where prod_id = 1
	 * - ALTER TABLE Parts ALTER COLUMN part_id RESTART WITH 1
	 * - delete from parts where part_id = 1
	 */

}
