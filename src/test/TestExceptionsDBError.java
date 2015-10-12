package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import productParts.ExceptionHandler;
import productParts.Part;
import productParts.ProductPartsDB;
import productParts.TotalProducts;

public class TestExceptionsDBError {

	private static Connection con = null;
	private static Statement stmt = null;
	public static ProductPartsDB testObject = null;
	
	/**
	 * Run this method before any test is run
	 * @throws Exception
	 */
	@BeforeClass
	public static void beforeAllTestsRun() throws Exception{
		// Delete both tables so as to force exceptions
		Class.forName("org.h2.Driver");
		con = DriverManager.getConnection("jdbc:h2:./extras/db_products_parts");
		stmt = con.createStatement();
		stmt.executeUpdate("DROP TABLE Product");
		stmt.executeUpdate("DROP TABLE Parts");
		con.close();
		stmt.close();
	}
	/**
	 * Run this method after all the tests are won
	 * @throws Exception
	 */
	@AfterClass
	public static void afterAllTestsRun() throws Exception{
		// Recreate both tables so as to force exceptions
		Class.forName("org.h2.Driver");
		con = DriverManager.getConnection("jdbc:h2:./extras/db_products_parts");
		stmt = con.createStatement();
		stmt.executeUpdate("CREATE TABLE Product ("+
							"Prod_ID INTEGER AUTO_INCREMENT,"+
							"Name VARCHAR(50) NOT NULL,"+
							"Description VARCHAR(50) NOT NULL,"+
							"PRIMARY KEY(Prod_ID))");
		stmt.executeUpdate("CREATE TABLE Parts ("+
							"Part_ID INTEGER AUTO_INCREMENT,"+
							"Prod_ID INTEGER(10),"+
							"Name VARCHAR(50) NOT NULL,"+
							"Description VARCHAR(50) NOT NULL,"+
							"Cost DECIMAL(10, 2) NOT NULL,"+
							"PRIMARY KEY(Part_ID,Prod_ID),"+
							"FOREIGN KEY (Prod_ID) REFERENCES Product (Prod_ID))");
		con.close();
		stmt.close();
	}
	
	@Before
	public void setUp() throws Exception {
		testObject = new ProductPartsDB();
	}

	@After
	public void tearDown() throws Exception {
		testObject = null;
	}

	// Test No: testProdListErrors
	// Objective: Test that the printProduct function returns an error
	// Inputs: 
	// Expected Output:  Exception thrown. An error of type 'Print Products' expected
	@Test(expected= ExceptionHandler.class)
	public void testProdListErrors() throws ExceptionHandler{

		try {
			assertEquals(0, testObject.printProducts().size());
			fail("Exception expected .....");
		} catch (ExceptionHandler e) {
			// Assert that the message form the error is the right error message
			assertEquals("Print Products", new String(e.getMessage().substring(0, 14)));
			// throw the error to be caught by the @Test notation
			throw e;
		}
	}
	
	// Test No: testPartsListErrors
	// Objective: Test that the printParts function returns an error
	// Inputs: 
	// Expected Output: Exception thrown. An error of type 'Print Parts' expected
	@Test(expected= ExceptionHandler.class)
	public void testPartsListErrors() throws ExceptionHandler{

		try {
			assertEquals(0, testObject.printParts().size());
			fail("Exception expected .....");
		} catch (ExceptionHandler e) {
			// Assert that the message form the error is the right error message
			assertEquals("Print Parts", new String(e.getMessage().substring(0, 11)));
			// throw the error to be caught by the @Test notation
			throw e;
		}
	}
	
	// Test No: testInsertProdErrors
	// Objective: Test Insertion into the product database for an error.
	// Inputs: name - 'ASUS' description - 'X501U-XX039H 15.6 Laptop - White'
	// Expected Output: Exception thrown. An error of type 'Insertion Product' expected
	@Test(expected= ExceptionHandler.class)
	public void testInsertProdErrors() throws ExceptionHandler{

		try {
			assertEquals(1, testObject.insertProduct("ASUS","X501U-XX039H 15.6 Laptop - White"));
			fail("Exception expected .....");
		} catch (ExceptionHandler e) {
			// Assert that the message form the error is the right error message
			assertEquals("Insertion Product", new String(e.getMessage().substring(0, 17)));
			// throw the error to be caught by the @Test notation
			throw e;
		}
	}
	
	
	// Test No: testTotalCostOfProdPartsErrors
	// Objective: Test Total cost of Products and Parts that should return an error 
	// Inputs:
	// Expected Output: Exception thrown. An error of type 'Print Total' expected
	@Test(expected= ExceptionHandler.class)
	public void testTotalCostOfProdPartsErrors() throws ExceptionHandler{
	
		try {
			TotalProducts actualTotProduct = new TotalProducts(1,"ASUS","X501U-XX039H 15.6 Laptop - White",2,499.98);
			assertEquals(actualTotProduct.toString(),testObject.printTotalProducts().get(0).toString());
			fail("Exception expected .....");
		} catch (ExceptionHandler e) {
			// Assert that the message form the error is the right error message
			assertEquals("Print Total", new String(e.getMessage().substring(0, 11)));
			// throw the error to be caught by the @Test notation
			throw e;
		}		
		
	} 
	
	// Test No: testSearchByIdProdErrors
	// Objective: Test search by product id with a valid product id returns an error
	// Inputs: prodid = 1
	// Expected Output: Exception thrown. An error of type 'Print Total' expected
	@Test(expected= ExceptionHandler.class)
	public void testSearchByIdProdErrors() throws ExceptionHandler{

		try {
			//create the test object
			Part actualPart = new Part(1,1,"HardDrive","500GB Internal HardDrive",199.99);
			assertEquals(actualPart.toString(),testObject.searchProductById(1).get(0).toString());
			fail("Exception expected .....");
		} catch (ExceptionHandler e) {
			// Assert that the message form the error is the right error message
			assertEquals("Search Product", new String(e.getMessage().substring(0, 14)));
			// throw the error to be caught by the @Test notation
			throw e;
		}
	}
	
	// Test No: testAdditionOfPartsByIdErrors
	// Objective: Test the addition of the cost of parts for of a chosen product id gives an error
	// Inputs: prodid = 1
	// Expected Output: Exception thrown. An error of type 'Sum Parts By Product Id' expected
	@Test(expected= ExceptionHandler.class)
	public void testAdditionOfPartsByIdErrors() throws ExceptionHandler{

		try {
			assertEquals(499.98,testObject.sumPartsByProductId(1),0.01);
			fail("Exception expected .....");
		} catch (ExceptionHandler e) {
			// Assert that the message form the error is the right error message
			assertEquals("Sum Parts By Product Id", new String(e.getMessage().substring(0, 23)));
			// throw the error to be caught by the @Test notation
			throw e;
		}
	}
	
	// Test No: testDeleteByPartsIdErrors
	// Objective: Test the deletion of a part from the part table by part id gives an error
	// Inputs: partid = 1
	// Expected Output: Exception thrown. An error of type 'Delete Part By Id Error' expected
	@Test(expected= ExceptionHandler.class)
	public void testDeleteByPartsIdErrors() throws ExceptionHandler{
		
		try {
			assertEquals(1,testObject.deletePartById(2));
			fail("Exception expected .....");
		} catch (ExceptionHandler e) {
			// Assert that the message form the error is the right error message
			assertEquals("Delete Part By Id Error", new String(e.getMessage().substring(0, 23)));
			// throw the error to be caught by the @Test notation
			throw e;
		}
	}
	
	// Test No: testDeleteByProdIdErrors
	// Objective: Test the deletion of a product from the product table by prod id to give an error
	// Inputs: prodid = 1
	// Expected Output: Exception thrown. An error of type 'Delete Product By Id Error' expected
	@Test(expected= ExceptionHandler.class)
	public void testDeleteByProdIdErrors() throws ExceptionHandler{

		try {
			assertEquals(1,testObject.deleteProductById(1));
			fail("Exception expected .....");
		} catch (ExceptionHandler e) {
			// Assert that the message form the error is the right error message
			assertEquals("Delete Product By Id Error", new String(e.getMessage().substring(0, 26)));
			// throw the error to be caught by the @Test notation
			throw e;
		}
	}

}
