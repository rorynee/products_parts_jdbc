package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import productParts.ExceptionHandler;
import productParts.Part;
import productParts.Product;
import productParts.ProductPartsDB;

public class TestDeleteUpdate {

	ProductPartsDB testObject = null;
	/**
	 * Run this method before each test is run.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		testObject = new ProductPartsDB();
		testObject.insertProduct("ASUS","X501U-XX039H 15.6 Laptop - White");
		// previous object closed, Need to instantiate a new object
		testObject = new ProductPartsDB();
		testObject.insertProduct("ADVENT","Monza T100 15.6 Laptop - Blue");
		
		testObject = new ProductPartsDB();
		testObject.insertPart(1,"HardDrive","500GB Internal HardDrive",199.99);
		testObject = new ProductPartsDB();
		testObject.insertPart(1,"Platform","Windows 8 ultimate",299.99);
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
			testObject = new ProductPartsDB();
			testObject.deleteProductById(2);
		}
		testObject = null;
	}
	
	// Test No: testDeleteByPartsId
	// Objective: Test the deletion of a part from the part table by part id
	// Inputs: partid = 1
	// Expected Output: 1 noting that the database was changed
	@Test
	public void testDeleteByPartsId() throws ExceptionHandler{

		assertEquals(1,testObject.deletePartById(2)); 
	}

	// Test No: testDeleteByPartsEmptyTable
	// Objective: Test the two deletions of a part from the parts table by part id.
	//			  When the table is empty is the table reset to 1 for the next item is inputted 
	// Inputs: 	partid = 1, partid = 2, 
	//			Inserted part: prod_id = 1, name - 'HardDrive' description - '500GB Internal HardDrive', cost 199.99
	// Expected Output: 1 noting that the database was changed twice, 
						
	@Test
	public void testDeleteByPartsEmptyTable() throws ExceptionHandler{

		assertEquals(1,testObject.deletePartById(1));
		testObject = new ProductPartsDB();
		assertEquals(1,testObject.deletePartById(2));
		testObject = new ProductPartsDB();
		assertEquals(1,testObject.insertPart(1,"HardDrive","500GB Internal HardDrive",199.99));
		testObject = new ProductPartsDB();
		Part actualPart = new Part(1,1,"HardDrive","500GB Internal HardDrive",199.99);
		assertEquals(actualPart.getPartid(),testObject.searchProductById(1).get(0).getPartid());
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
	
	// Test No: testUpdateProductName
	// Objective: Test the updating of a product based on the inputed product object name.
	// Inputs: 	product id = 1, 
	//			product name = ACER,
	//			Product Description = null
	// Expected Output: product name to be changed to ACER
	@Test
	public void testUpdateProduct() throws ExceptionHandler{
		// Set up the Product objects
		Product updatingProduct = new Product(1,"ACER",null);
		Product checkProduct = new Product(1,"ACER","X501U-XX039H 15.6 Laptop - White");
		
		// Assert that they have been changed and are changed on the database 
		assertEquals(1,testObject.updateProduct(updatingProduct));
		// previous object closed, Need to instantiate a new object
		testObject = new ProductPartsDB();
		assertEquals(checkProduct.toString(),testObject.printProducts().get(0).toString());
 
	}
	
	// Test No: testUpdateProductDescription
	// Objective: Test the updating of a product based on the inputed product object description. 
	// Inputs: 	product id = 1, 
	//			product name = null,
	//			Product Description = X501U-XX039H 15.6 Laptop - Red
	// Expected Output: product name to be changed to "X501U-XX039H 15.6 Laptop - Red"
	@Test
	public void testUpdateProductDescription() throws ExceptionHandler{
		// Set up the Product objects
		Product updatingProduct = new Product(1,null,"X501U-XX039H 15.6 Laptop - Red");
		Product checkProduct = new Product(1,"ASUS","X501U-XX039H 15.6 Laptop - Red");
		
		// Assert that they have been changed and are changed on the database 
		assertEquals(1,testObject.updateProduct(updatingProduct));
		// previous object closed, Need to instantiate a new object
		testObject = new ProductPartsDB();
		assertEquals(checkProduct.toString(),testObject.printProducts().get(0).toString());
 
	}
	
	// Test No: testUpdateProductFull
	// Objective: Test the updating of a product based on the inputed full Product object. 
	// Inputs: 	product id = 1, 
	//			product name = ACER,
	//			Product Description = X501U-XX039H 15.6 Laptop - Red
	// Expected Output: for 'checkProduct' to be equal to the Product stored on the database
	@Test
	public void testUpdateProductFull() throws ExceptionHandler{
		// Set up the Product objects
		Product updatingProduct = new Product(1,"ACER","X501U-XX039H 15.6 Laptop - Red");
		Product checkProduct = new Product(1,"ACER","X501U-XX039H 15.6 Laptop - Red");
		
		// Assert that they have been changed and are changed on the database 
		assertEquals(1,testObject.updateProduct(updatingProduct));
		// previous object closed, Need to instantiate a new object
		testObject = new ProductPartsDB();
		assertEquals(checkProduct.toString(),testObject.printProducts().get(0).toString());
 
	}
	
	// Test No: testUpdateProductNoId
	// Objective: Test the updating of a product based on the inputed Product object with no id. 
	// Inputs: 	product id = null, 
	//			product name = ACER,
	//			Product Description = X501U-XX039H 15.6 Laptop - Red
	// Expected Output: Null point exception
	@Test(expected=NullPointerException.class)
	public void testUpdateProductNoId() throws ExceptionHandler{
		
			// Set up the Product objects
			Product updatingProduct = new Product((Integer) null,"ACER","X501U-XX039H 15.6 Laptop - Red");
			// Assert that they have been changed and are changed on the database 
			assertEquals(1,testObject.updateProduct(updatingProduct)); 
	}
	
	// Test No: testUpdateProductNoDetails
	// Objective: Test the updating of a product based on the inputed Product object with no details. 
	// Inputs: 	product id = 1, 
	//			product name = null,
	//			Product Description = null
	// Expected Output: An error of type 'Update Product Error' expected
	@Test(expected=ExceptionHandler.class)
	public void testUpdateProductNoDetails() throws ExceptionHandler{
		// Set up the Product objects
		Product updatingProduct = new Product(1,null,null);
		
		try {
			// Assert that they have been changed and are changed on the database 
			assertEquals(1,testObject.updateProduct(updatingProduct));
			fail("Exception expected .....");
		} catch (ExceptionHandler e) {
			// Assert that the message form the error is the right error message
			assertEquals("Update Product Error", new String(e.getMessage().substring(0, 20)));
			// throw the error to be caught by the @Test notation
			throw e;
		} 
	}
	
	// Test No: testUpdateProductNull
	// Objective: Test the updating of a product based on the inputed Product object as null. 
	// Inputs: updatingProduct = null
	// Expected Output: An error of type 'Update Product Error' expected
	@Test(expected=ExceptionHandler.class)
	public void testUpdateProductNull() throws ExceptionHandler{
		// Set up the Product objects
		Product updatingProduct = null;
		
		try {
			// Assert that they have been changed and are changed on the database 
			assertEquals(1,testObject.updateProduct(updatingProduct));
			fail("Exception expected .....");
		} catch (ExceptionHandler e) {
			// Assert that the message form the error is the right error message
			assertEquals("Update Product Error", new String(e.getMessage().substring(0, 20)));
			// throw the error to be caught by the @Test notation
			throw e;
		} 
	}
	
	// Test No: testUpdatePartsName
	// Objective: Test the updating of a part based on the inputed part object name.
	// Inputs:  part id = 2, 
	//			product id = 1, 
	//			part name = System,
	//			part Description = null
	//			part cost = -1
	// Expected Output: part name to be changed to 'System'
	@Test
	public void testUpdatePartsName() throws ExceptionHandler{
		// Set up the Product objects
		Part updatingPart = new Part(2,1,"System",null,-1);
		Part checkPart = new Part(2,1,"System","Windows 8 ultimate",299.99);
		
		// Assert that they have been changed and are changed on the database 
		assertEquals(1,testObject.updatePart(updatingPart));
		// previous object closed, Need to instantiate a new object
		testObject = new ProductPartsDB();
		assertEquals(checkPart.toString(),testObject.printParts().get(1).toString());
 
	}
	
	// Test No: testUpdatePartsDescription
	// Objective: Test the updating of a part based on the inputed part object description. 
	// Inputs:  part id = 2, 
	//			product id = 1, 
	//			part name = null,
	//			part Description = Windows 10,
	//			part cost = -1
	// Expected Output: part description to be changed to 'Windows 10'
	@Test
	public void testUpdatePartsDescription() throws ExceptionHandler{
		// Set up the Product objects
		Part updatingPart = new Part(2,1,null,"Windows 10",-1);
		Part checkPart = new Part(2,1,"Platform","Windows 10",299.99);
		
		// Assert that they have been changed and are changed on the database 
		assertEquals(1,testObject.updatePart(updatingPart));
		// previous object closed, Need to instantiate a new object
		testObject = new ProductPartsDB();
		assertEquals(checkPart.toString(),testObject.printParts().get(1).toString());
 
	}
	
	// Test No: testUpdatePartsCost
	// Objective: Test the updating of a part based on the inputed part object cost. 
	// Inputs:  part id = 2, 
	//			product id = 1, 
	//			part name = null,
	//			part Description = null,
	//			part cost = 399.99
	// Expected Output: part cost to be changed to 399.99
	@Test
	public void testUpdatePartsCost() throws ExceptionHandler{
		// Set up the Product objects
		Part updatingPart = new Part(2,1,null,null,399.99);
		Part checkPart = new Part(2,1,"Platform","Windows 8 ultimate",399.99);
		
		// Assert that they have been changed and are changed on the database 
		assertEquals(1,testObject.updatePart(updatingPart));
		// previous object closed, Need to instantiate a new object
		testObject = new ProductPartsDB();
		assertEquals(checkPart.toString(),testObject.printParts().get(1).toString());
 
	}
	
	// Test No: testUpdatePartsDescName
	// Objective: Test the updating of a part based on the inputed part object description and name. 
	// Inputs:  part id = 2, 
	//			product id = 1, 
	//			part name = System,
	//			part Description = Windows 10,
	//			part cost = -1
	// Expected Output: part description to be changed to 'Windows 10' and name to 'system'
	@Test
	public void testUpdatePartsDescName() throws ExceptionHandler{
		// Set up the Product objects
		Part updatingPart = new Part(2,1,"System","Windows 10",-1);
		Part checkPart = new Part(2,1,"System","Windows 10",299.99);
		
		// Assert that they have been changed and are changed on the database 
		assertEquals(1,testObject.updatePart(updatingPart));
		// previous object closed, Need to instantiate a new object
		testObject = new ProductPartsDB();
		assertEquals(checkPart.toString(),testObject.printParts().get(1).toString());
 
	}
	
	// Test No: testUpdatePartsNameCost
	// Objective: Test the updating of a part based on the inputed part object cost and name. 
	// Inputs:  part id = 2, 
	//			product id = 1, 
	//			part name = System,
	//			part Description = null,
	//			part cost = 399.99
	// Expected Output: part cost to be changed to 399.99 and name to 'system'
	@Test
	public void testUpdatePartsNameCost() throws ExceptionHandler{
		// Set up the Product objects
		Part updatingPart = new Part(2,1,"System",null,399.99);
		Part checkPart = new Part(2,1,"System","Windows 8 ultimate",399.99);
		
		// Assert that they have been changed and are changed on the database 
		assertEquals(1,testObject.updatePart(updatingPart));
		// previous object closed, Need to instantiate a new object
		testObject = new ProductPartsDB();
		assertEquals(checkPart.toString(),testObject.printParts().get(1).toString());
 
	}
	
	// Test No: testUpdatePartsDescCost
	// Objective: Test the updating of a part based on the inputed part object description and cost. 
	// Inputs:  part id = 2, 
	//			product id = 1, 
	//			part name = null,
	//			part Description = Windows 10,
	//			part cost = 399.99
	// Expected Output: part description to be changed to 'Windows 10' and cost to be 399.99
	@Test
	public void testUpdatePartsDescCost() throws ExceptionHandler{
		// Set up the Product objects
		Part updatingPart = new Part(2,1,null,"Windows 10",399.99);
		Part checkPart = new Part(2,1,"Platform","Windows 10",399.99);
		
		// Assert that they have been changed and are changed on the database 
		assertEquals(1,testObject.updatePart(updatingPart));
		// previous object closed, Need to instantiate a new object
		testObject = new ProductPartsDB();
		assertEquals(checkPart.toString(),testObject.printParts().get(1).toString());
 
	}
	
	// Test No: testUpdatePartsNoProdId
	// Objective: Test the updating of a part based on invalid inputed part object product id. 
	// Inputs:  part id = 2, 
	//			product id = null, 
	//			part name = Platform,
	//			part Description = "Windows 8 ultimate"
	//			part cost = 299.99
	// Expected Output: Null point exception
	@Test(expected=NullPointerException.class)
	public void testUpdatePartsNoProdId() throws ExceptionHandler{
			
			// Set up the Product objects
			Part updatingPart = new Part(2,(Integer) null,"Platform","Windows 8 ultimate",299.99);
			
			// Assert that they have been changed and are changed on the database 
			assertEquals(1,testObject.updatePart(updatingPart));
	}
	
	// Test No: testUpdatePartsNoPartId
	// Objective: Test the updating of a part based on invalid inputed part object part id. 
	// Inputs:  part id = null, 
	//		    product id = 1, 
	//			part name = Platform,
	//			part Description = "Windows 8 ultimate"
	//			part cost = 299.99
	// Expected Output: Null point exception
	@Test(expected=NullPointerException.class)
	public void testUpdatePartsNoPartId() throws ExceptionHandler{
			
			// Set up the Product objects
			Part updatingPart = new Part((Integer) null,1,"Platform","Windows 8 ultimate",299.99);
			
			// Assert that they have been changed and are changed on the database 
			assertEquals(1,testObject.updatePart(updatingPart));
	}
	
	// Test No: testUpdatePartsCostNull
	// Objective: Test the updating of a part based on invalid inputed part object cost. 
	// Inputs:  part id = 2, 
	//			product id = 1, 
	//			part name = Platform, 
	//			part Description = "Windows 8 ultimate"
	//			part Cost = null
	// Expected Output: Null point exception
	@Test(expected=NullPointerException.class)
	public void testUpdatePartsCostNull() throws ExceptionHandler{
			
			// Set up the Product objects
			Part updatingPart = new Part(2,1,"Platform","Windows 8 ultimate",(Double) null);
			
			// Assert that they have been changed and are changed on the database 
			assertEquals(1,testObject.updatePart(updatingPart));
	}
	
	// Test No: testUpdatePartsNullDetails
	// Objective: Test the updating of a part based on invalid inputed part object details all null. 
	// Inputs:  part id = 2, 
	//			product id = 1, 
	//			part name = null,
	//			part Description = null,
	//			part cost = -1
	// Expected Output: Exception Handler exception 'Update Parts Error' expected
	@Test(expected=ExceptionHandler.class)
	public void testUpdatePartsNullDetails() throws ExceptionHandler{
			
			// Set up the Product objects
			Part updatingPart = new Part(2,1,null,null,-1);
		try {
				// Assert that they have been changed and are changed on the database 
				assertEquals(0,testObject.updatePart(updatingPart));
				fail("Exception expected .....");
			} catch (ExceptionHandler e) {
				// Assert that the message form the error is the right error message
				assertEquals("Update Parts Error", new String(e.getMessage().substring(0, 18)));
				// throw the error to be caught by the @Test notation
				throw e;
			}
	}
	
	// Test No: testUpdatePartsNull
	// Objective: Test the updating of a part based on a null inputed part object. 
	// Inputs: updatingPart = null
	// Expected Output: Exception Handler exception 'Update Parts Error' expected
	@Test(expected=ExceptionHandler.class)
	public void testUpdatePartsNull() throws ExceptionHandler{
			
			// Set up the Product objects
			Part updatingPart = null;
			try {
				// Assert that they have been changed and are changed on the database 
				assertEquals(0,testObject.updatePart(updatingPart));
				fail("Exception expected .....");
			} catch (ExceptionHandler e) {
				// Assert that the message form the error is the right error message
				assertEquals("Update Parts Error", new String(e.getMessage().substring(0, 18)));
				// throw the error to be caught by the @Test notation
				throw e;
			}
	}
	
	// Test No: testUpdatePartsFullNull
	// Objective: Test the updating of a part based on the full inputed part object as null parts.
	// Inputs:  part id = 2, 
	//			product id = 1, 
	//			part name = null,
	//			part Description = null,
	//			part cost = -1
	// Expected Output: part description to be changed to 'Windows 10' and cost to be 399.99
	@Test
	public void testUpdatePartsFullNull() throws ExceptionHandler{
		// Set up the Product objects
		Part updatingPart = new Part(2,1,"System","Windows 10",399.99);
		Part checkPart = new Part(2,1,"System","Windows 10",399.99);
		
		// Assert that they have been changed and are changed on the database 
		assertEquals(1,testObject.updatePart(updatingPart));
		// previous object closed, Need to instantiate a new object
		testObject = new ProductPartsDB();
		assertEquals(checkPart.toString(),testObject.printParts().get(1).toString());
 
	}
	/***/
	
	
	
	// Test No: testChangePartAssociation 
	// Objective: 	Test the change part association method. This method associates a part 
	// 				with a product
	// Inputs:  part id = 2, 
	//			product id = 2,
	// Expected Output: 1 - the database has changed, new product returned as an association of a 
	//					different product i.e. prod id 2. 
	@Test
	public void testChangePartAssociation() throws ExceptionHandler{
		//create the test object
	    Part actualPart = new Part(2,2,"Platform","Windows 8 ultimate",299.99);
		
	    // Assert that they have been changed and are changed on the database 
		assertEquals(1,testObject.changePartAssociation(2,2));
		// previous object closed, Need to instantiate a new object
		testObject = new ProductPartsDB();
		assertEquals(actualPart.toString(),testObject.searchProductById(2).get(0).toString());
 
	}
	
	// Test No: testChangePartAssoInvalPart
	// Objective: 	Test the change part association method. This method associates a part  
	// 				with a product. Test invalid part id.
	// Inputs:  part id = 88888, 
	//			product id = 1,
	// Expected Output: 0 no change to the database
	@Test
	public void testChangePartAssoInvalPart() throws ExceptionHandler{
		
		// Assert that they have been changed and are changed on the database 
		assertEquals(0,testObject.changePartAssociation(88888,1));
 
	}
	
	// Test No: testChangePartAssoInvalProd
	// Objective: 	Test the change part association method. This method associates a part  
	// 				with a product. Test invalid prod id.
	// Inputs:  part id = 2, 
	//			product id = 88888,
	// Expected Output: Exception Handler exception 'Change Part Association Error' expected
	@Test(expected=ExceptionHandler.class)
	public void testChangePartAssoInvalProd() throws ExceptionHandler{
		
		try {
			// Assert that they have been changed and are changed on the database 
			assertEquals(0,testObject.changePartAssociation(2,88888));
			fail("Exception expected .....");
		} catch (ExceptionHandler e) {
			// Assert that the message form the error is the right error message
			assertEquals("Change Part Association Error", new String(e.getMessage().substring(0, 29)));
			// throw the error to be caught by the @Test notation
			throw e;
		}
 
	}
	
	// Test No: testChangePartAssoInvalPAndP
	// Objective: 	Test the change part association method. This method associates a part  
	// 				with a product. Test invalid part id and prod id.
	// Inputs:  part id = 88888, 
	//			product id = 88888,
	// Expected Output: 0 no change to the database
	@Test
	public void testChangePartAssoInvalPAndP() throws ExceptionHandler{
		
		// Assert that they have been changed and are changed on the database 
		assertEquals(0,testObject.changePartAssociation(88888,88888));
 
	}
	
	// Test No: testChangePartAssoNullPart
	// Objective: 	Test the change part association method. This method associates a part  
	// 				with a product. Test invalid part id.
	// Inputs:  part id = null, 
	//			product id = 1,
	// Expected Output: Null Pointer Exception Expected
	@Test(expected=NullPointerException.class)
	public void testChangePartAssoNullPart() throws ExceptionHandler{
			
		// Assert that they have been changed and are changed on the database 
		assertEquals(0,testObject.changePartAssociation((Integer) null,88888));
	}

	// Test No: testChangePartAssoNullProd
	// Objective: 	Test the change part association method. This method associates a part  
	// 				with a product. Test invalid prod id.
	// Inputs:  part id = 2, 
	//			product id = null,
	// Expected Output: Null Pointer Exception Expected
	@Test(expected=NullPointerException.class)
	public void testChangePartAssoNullProd() throws ExceptionHandler{
			
		// Assert that they have been changed and are changed on the database 
		assertEquals(0,testObject.changePartAssociation(2,(Integer) null));
	}
	// test re allocation of parts
	
}
