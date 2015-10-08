package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import productParts.ExceptionHandler;
import productParts.Part;
import productParts.Product;
import productParts.ProductPartsDB;
import productParts.TotalProducts;

public class TestValidatingData {

	public static ProductPartsDB testObject = null;
	/**
	 * Run this method before any test is run
	 * @throws Exception
	 */
	@BeforeClass
	public static void beforeAllTestsRun() throws Exception{
			testObject = new ProductPartsDB();
			testObject.insertProduct("ASUS","X501U-XX039H 15.6 Laptop - White");
			testObject = new ProductPartsDB();
			testObject.insertPart(1,"HardDrive","500GB Internal HardDrive",199.99);
			testObject = new ProductPartsDB();
			testObject.insertPart(1,"Platform","Windows 8 ultimate",299.99);
			testObject = null;
	}
	/**
	 * Run this method after all the tests are won
	 * @throws Exception
	 */
	@AfterClass
	public static void afterAllTestsRun() throws Exception{
		testObject = new ProductPartsDB();
		testObject.deleteProductById(1);
		testObject = null;
	}
	
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

	// Test No: testValidIdProd
	// Objective: Test that a number is a valid database product id
	// Inputs: Table name: "product" Id number: 1
	// Expected Output: true
	@Test
	public void testValidIdProd() throws ExceptionHandler{

		assertEquals(true, testObject.validId("product",1));
	}
	
	// Test No: testReturnedDataProd
	// Objective: Test that that the printProduct function prints data
	// Inputs: 
	// Expected Output: array of products with the first equaling 
	// Product [prodid= 1, name= "ASUS", description= "X501U-XX039H 15.6 Laptop - White"]
	@Test
	public void testReturnedDataProd() throws ExceptionHandler{

		//create the test object
		Product actualProduct = new Product(1,"ASUS","X501U-XX039H 15.6 Laptop - White");
		
		assertEquals(actualProduct.toString(),testObject.printProducts().get(0).toString());
	}
	// Test No: testInvalidInsertionId
	// Objective: Test Insertion into the parts database with an invalid id number.
	// Inputs: prod_id = 88888888, name - 'HardDrive' description - '500GB Internal HardDrive', cost 199.99
	// Expected Output: Exception thrown. An error of type 'Insertion Parts Error: Database Error' expected
	@Test(expected=ExceptionHandler.class)
	public void testInvalidInsertionId() throws ExceptionHandler{

		try {
			assertEquals(1, testObject.insertPart(88888888,"HardDrive","500GB Internal HardDrive",199.99));
			fail("Exception expected .....");
		} catch (ExceptionHandler e) {
			// Assert that the message form the error is the right error message
			assertEquals("Insertion Parts Error: Database Error", new String(e.getMessage().substring(0, 37)));
			// throw the error to be caught by the @Test notation
			throw e;
		} 

	}
	
	// Test No: testValidIdParts
	// Objective: Test that a number is a valid database parts id
	// Inputs: Table name: "parts" Id number: 1
	// Expected Output: true
	@Test
	public void testValidIdParts() throws ExceptionHandler{

		assertEquals(true, testObject.validId("parts",1));
	}
	
	// Test No: testReturnedDataParts
	// Objective: Test that that the printParts function prints data
	// Inputs: 
	// Expected Output: array of products with the first equaling 
	// Part [partid= 1, prodid= 1, name= "HardDrive", description= "500GB Internal HardDrive", cost= 199.99]
	@Test
	public void testReturnedDataParts() throws ExceptionHandler{

		//create the test object
		Part actualPart = new Part(1,1,"HardDrive","500GB Internal HardDrive",199.99);

		assertEquals(actualPart.toString(),testObject.printParts().get(0).toString());
	}
	
	// Test No: testTotalCostOfProdParts
	// Objective: Shows all available Products and Parts that are in the database 
	// along with its total cost and the number of parts it contains
	// Inputs:
	// Expected Output: List is parts including costs and number of parts i.e.
	// Product [prodid= 1, name= "ASUS", description= "X501U-XX039H 15.6 Laptop - White", numOfParts= 2, totalCostOfParts= 499.98]
	@Test
	public void testTotalCostOfProdParts() throws ExceptionHandler{
	
		TotalProducts actualTotProduct = new TotalProducts(1,"ASUS","X501U-XX039H 15.6 Laptop - White",2,499.98);
		assertEquals(actualTotProduct.toString(),testObject.printTotalProducts().get(0).toString());	
	} 
	
	// Test No: testSearchByIdProd
	// Objective: Test search by product id with a valid product id
	// Inputs: prodid = 1
	// Expected Output: List of parts associated to a product id of 1 where the following part
	// is first in the list
	// Part [partid= 1, prodid= 1, name= "HardDrive", description= "500GB Internal HardDrive", cost= 199.99]
	@Test
	public void testSearchByIdProd() throws ExceptionHandler{

		//create the test object
		Part actualPart = new Part(1,1,"HardDrive","500GB Internal HardDrive",199.99);

		assertEquals(actualPart.toString(),testObject.searchProductById(1).get(0).toString());
	}

	// Test No: testInvalidSearchByIdProd
	// Objective: Test search by product id with a invalid product id
	// Inputs: prodid = 8888
	// Expected Output: 0 as there is not product with id 8888 
	@Test
	public void testInvalidSearchByIdProd() throws ExceptionHandler{

		assertEquals(0,testObject.searchProductById(8888).size()); 
	}

	// Test No: testAdditionOfPartsById
	// Objective: Test the addition of the cost of parts for of a chosen product id
	// Inputs: prodid = 1
	// Expected Output: 499.98
	@Test
	public void testAdditionOfParts() throws ExceptionHandler{

		assertEquals(499.98,testObject.sumPartsByProductId(1),0.01); 
	}
	
	// Test No: testInvalidAdditionOfPartsById
	// Objective: Test the addition of the cost of parts for of a chosen invalid product id
	// Inputs: prodid = 8888
	// Expected Output: 0
	@Test
	public void testInvalidAdditionOfPartsById() throws ExceptionHandler{

		assertEquals(0,testObject.sumPartsByProductId(8888),0.01); 
	}
	
	// Test No: testProdTableNotEmpty
	// Objective: Test that the product table is not empty
	// Inputs: table name - PRODUCT
	// Expected Output: false
	@Test
	public void testProdTableNotEmpty() throws ExceptionHandler{

		assertEquals(false, testObject.checkIsTableEmpty("PRODUCT"));
		// Explicitly calling the close connection method
		testObject.closePreStmtConnections();
	}
	
	// Test No: testPartsTableNotEmpty
	// Objective: Test that the parts table is not empty
	// Inputs: table name - parts
	// Expected Output: false
	@Test
	public void testEmptyPartsTable() throws ExceptionHandler{

		assertEquals(false, testObject.checkIsTableEmpty("parts"));
		// Explicitly calling the close connection method
		testObject.closePreStmtConnections();
	}
}
