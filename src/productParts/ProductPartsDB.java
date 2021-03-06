package productParts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 * The Product Parts Database class is used to connect to the database.
 * This class is also used when any action needs to be performed on the database.
 * @author Rory Nee
 *
 */
public class ProductPartsDB {

	// Private variable declaration
	private static Connection con = null;
	private static Statement stmt = null;
	private static PreparedStatement preStmt = null;
	private static ResultSet rs = null;
	
	/** Choose between Production or test file names **/
	
	private static String configFileName = "dbconfig.properties";
	//private static String configFileName = "dbconfigTest.properties";
	
	/****/
	
	// Declare an array list of Products 
	private static ArrayList<Product> myListProducts = null;
	private static ArrayList<TotalProducts> myListTotalProducts = null;
	// Declare an array list of Parts 
	private static ArrayList<Part> myListParts = null;
	
	/**
	 * This is the constructor for the Product Parts Database class
	 * @throws ExceptionHandler
	 */
	public ProductPartsDB() throws ExceptionHandler{
			// Initialization of the database
			init_db();
	}
	/**
	 * The following method connects this java program to our mysql database.
	 * For security reasons the database configuration details are stored separately
	 * @throws ExceptionHandler
	 */
	private static void init_db() throws ExceptionHandler
	{
		// get database configuration details form the properties file
		Properties props = loadFileProperties(configFileName);
		
		try
		{
			/*  SQL Database Configurations - change configFileName to production filename */
			// dynamically loads the driver
			Class.forName(props.getProperty("jdbcdriver"));
			// connect to the database using the URL, name & password
			con = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));
			/*   ---------------   */
			
			
			/*  H2 Database Configurations  */
			// dynamically loads the driver
	//		Class.forName(props.getProperty("jdbcdriverTest"));
			// connect to the database using the URL, name & password
	//		con = DriverManager.getConnection(props.getProperty("urlTest"));
			/*   ---------------   */
		}
		catch(Exception e)
		{
			throw new ExceptionHandler("Database Connection Error: Failed to connect to database\n"+e.getClass()+" - "+e.getMessage());
		}
	}
	/**
	 * @param props
	 * @throws ExceptionHandler
	 */
	public static Properties loadFileProperties(String filename) throws ExceptionHandler {
		Properties props = new Properties();
		try {
			// Load the data into the file input stream
			props.load(new FileInputStream(filename));
		} catch (FileNotFoundException e) {
			throw new ExceptionHandler("DBConfig Error: File not found\n"+e.getClass()+" - "+e.getMessage());
		} catch (IOException e) {
			throw new ExceptionHandler("DBConfig Error: IO Exception\n"+e.getClass()+" - "+e.getMessage());
		}
		return props;
	}
	/**
	 * The function tests the connection to the database
	 * @return Boolean 	true - is still connected
	 * 					false - is not connected
	 * @throws ExceptionHandler
	 */
	public boolean dbIsConnected() throws ExceptionHandler {

		try {
			boolean hasValidConn = con.isValid(10);
			return hasValidConn;
		} catch (SQLException e) {
			throw new ExceptionHandler("DBIsConnected Error: Db Not Connected\n"+e.getClass()+" - "+e.getMessage());
		}
		
	}
	/**
	 * This method checks that num is present in the database. It does this by counting the number of row associated to it.  
	 * If there are one or more than one it is valid(true). If it is zero it is invalid(false). 
	 * @param tableName - The name of the table to check 
	 * @param idnum - num represents the value inputed by the user - database id. 
	 * @return true (is in the database)or false (not in the database)
	 * @throws ExceptionHandler 
	 */
	public boolean validId(String tableName, int idnum) throws ExceptionHandler {
		
		try
		{	
			if(tableName.equalsIgnoreCase("product")){
			//counting the number of row associated with 'idnum' using a prepared statement
			preStmt = con.prepareStatement("select count(*) as Total from "+tableName+" where Prod_ID = ?");
			}else{
				//counting the number of row associated with 'idnum' using a prepared statement
				preStmt = con.prepareStatement("select count(*) as Total from "+tableName+" where Part_ID = ?");
			}
			preStmt.setInt(1,idnum);
			// Declare a result set passing the results of the query
			rs = preStmt.executeQuery();
			rs.next();
			// Access the total variable
			int valid = rs.getInt("Total");
			// Access the valid variable
			if(valid > 0)
			{
				return true; // if valid return true
			}
			return false; // if invalid return false
			
		}
		catch (SQLException sqle)
		{
			throw new ExceptionHandler("Valid Id: Database Error\n"+sqle.getClass()+" - "+sqle.getMessage());
		}
		finally {
			// Close the connections
			closePreStmtConnections();
		}
	}
	
	/**
	 * The method prints all the product details form the product table 
	 * @return ArrayList<Product> - Returns an array list of Product objects
	 * @throws ExceptionHandler
	 */
	public ArrayList<Product> printProducts() throws ExceptionHandler{
		
		try
		{
			// Declare an array list of Products 
			myListProducts = new ArrayList<Product>();
			// Creates a Statement object which allows us to send commands to the database.
			stmt = con.createStatement();
			// Declare a result set passing the results of the query 
			rs = stmt.executeQuery("select * from product;");

			while (rs.next()) {	 // prints out all the rows of the table
				Product curProduct = new Product();
				// Add the data to a new Product object
				curProduct.setProdid(rs.getInt("Prod_ID"));
				curProduct.setName(rs.getString("Name"));
				curProduct.setDescription(rs.getString("Description"));
				// Add the object to the list
				myListProducts.add(curProduct);
			}
		}
		catch(SQLException sqle)
		{
			throw new ExceptionHandler("Print Products: Database Error\n"+sqle.getClass()+" - "+sqle.getMessage());
		}
		finally {
			// Close the connections
			closeStmtConnections();
		}
		// Return the data
		return myListProducts;
	}
	/**
	 * The method prints all the product details form the product table 
	 * @return ArrayList<Part> - Returns an array list of Product objects
	 * @throws ExceptionHandler
	 */
	public ArrayList<Part> printParts() throws ExceptionHandler{
		
		try
		{
			// Declare an array list of Parts 
			myListParts = new ArrayList<Part>();
			// Creates a Statement object which allows us to send commands to the database.
			stmt = con.createStatement();
			// Declare a result set passing the results of the query 
			rs = stmt.executeQuery("select * from parts;");

			while (rs.next()) {	 // prints out all the rows of the table
				Part curPart = new Part();
				// Add the data to a new Part object
				curPart.setPartid(rs.getInt("Part_ID"));
				curPart.setProdid(rs.getInt("Prod_ID"));
				curPart.setName(rs.getString("Name"));
				curPart.setDescription(rs.getString("Description"));
				curPart.setCost(rs.getDouble("Cost"));
				// Add the object to the list
				myListParts.add(curPart);
			}
		}
		catch(SQLException sqle)
		{
			throw new ExceptionHandler("Print Parts: Database Error\n"+sqle.getClass()+" - "+sqle.getMessage());
		}
		finally {
			// Close the connections
			closeStmtConnections();
		}
		// Return the data
		return myListParts;
	}
	/**
	 * The method insert a new product into the product table
	 * @param name - Product name
	 * @param description - Product description 
	 * @return 1 or 0 - 1 database changed, 0 database not changed
	 * @throws ExceptionHandler
	 */
	public int insertProduct(String name, String description) throws ExceptionHandler{
		
		try
		{	//Insert the new product into the database
			preStmt = con.prepareStatement("INSERT INTO Product VALUES(null,?,?)");
			preStmt.setString(1,name);
			preStmt.setString(2,description);
			// Declare a result set passing the results of the query
			return preStmt.executeUpdate();			
		}
		catch (SQLException sqle)
		{
			throw new ExceptionHandler("Insertion Product Error: Database Error\n"+sqle.getClass()+" - "+sqle.getMessage());
		}finally {
			// Close the connections
			closePreStmtConnections();
		}	
	}
	/**
	 * The method insert a new part into the parts table
	 * @param prodid - A current product id
	 * @param name - Name of the the part 
	 * @param description - Description of the part
	 * @param cost - Cost of the part
	 * @return 1 or 0 - 1 database changed, 0 database not changed
	 * @throws ExceptionHandler
	 */
	public int insertPart(int prodid, String name, String description, double cost) throws ExceptionHandler{
		try
		{	//Insert the new part into the database
			preStmt = con.prepareStatement("INSERT INTO Parts VALUES(null,?,?,?,?)");
			preStmt.setInt(1,prodid);
			preStmt.setString(2,name);
			preStmt.setString(3,description);
			preStmt.setDouble(4,cost);
			// Declare a result set passing the results of the query
			return preStmt.executeUpdate();			
		}
		catch (SQLException sqle)
		{
			throw new ExceptionHandler("Insertion Parts Error: Database Error\n"+sqle.getClass()+" - "+sqle.getMessage());
		}finally {
			// Close the connections
			closePreStmtConnections();
		}
	}
	
	/**
	 * The method prints all the product details form the product table that includes
	 * the number of associated parts and their total costs 
	 * @return ArrayList<Product> - Returns an array list of Product objects
	 * @throws ExceptionHandler
	 */
	public ArrayList<TotalProducts> printTotalProducts() throws ExceptionHandler{
		
		String statmentString = "select product.Prod_ID, product.Name, product.Description," +
				" count(parts.Part_ID) as Num_Of_Parts, sum(parts.cost) as Total_Cost_of_Parts" +
				" from product join parts where product.Prod_ID = Parts.Prod_ID group by parts.Prod_ID;";
		
		try
		{
			// Declare an array list of Products 
			myListTotalProducts = new ArrayList<TotalProducts>();
			// Creates a Statement object which allows us to send commands to the database.
			stmt = con.createStatement();
			// Declare a result set passing the results of the query 
			rs = stmt.executeQuery(statmentString);

			while (rs.next()) {	 // prints out all the rows of the table
				// Add the data to a new Product object
				TotalProducts curTotProducts = new TotalProducts(
					rs.getInt("Prod_ID"),
					rs.getString("Name"),
					rs.getString("Description"),
					rs.getInt("Num_Of_Parts"),
					rs.getDouble("Total_Cost_of_Parts")
				);
				myListTotalProducts.add(curTotProducts);
			}
		}
		catch(SQLException sqle)
		{
			throw new ExceptionHandler("Print Total Products: Database Error\n"+sqle.getClass()+" - "+sqle.getMessage());
		}
		finally {
			// Close the connections
			closeStmtConnections();
		}
		// Return the data
		return myListTotalProducts;
	}
	/**
	 * This method shows all available parts for a given product id
	 * @param prodid - Given product id to search for
	 * @return ArrayList<Part> - List of available parts for the given id
	 * @throws ExceptionHandler
	 */
	public ArrayList<Part> searchProductById(int prodid) throws ExceptionHandler {
		
		try
		{
			// Declare an array list of Parts 
			myListParts = new ArrayList<Part>();
			// Creates a prepared Statement object which allows us to send commands to the database.
			preStmt = con.prepareStatement("select parts.Prod_ID, parts.Part_ID, parts.name, parts.Description, parts.cost from"+
					" product join parts where product.Prod_ID = Parts.Prod_ID and Parts.Prod_ID = ?");
			preStmt.setInt(1,prodid);
			// Declare a result set passing the results of the query 
			rs = preStmt.executeQuery();

			while (rs.next()) {	 // prints out all the rows of the table
				Part curPart = new Part();
				// Add the data to a new Part object
				curPart.setPartid(rs.getInt("Part_ID"));
				curPart.setProdid(rs.getInt("Prod_ID"));
				curPart.setName(rs.getString("name"));
				curPart.setDescription(rs.getString("Description"));
				curPart.setCost(rs.getDouble("Cost"));
				// Add the object to the list
				myListParts.add(curPart);
			}
		}
		catch(SQLException sqle)
		{
			throw new ExceptionHandler("Search Product By Id Error: Database Error\n"+sqle.getClass()+" - "+sqle.getMessage());
		}
		finally {
			// Close the connections
			closePreStmtConnections();
		}
		// Return the data
		return myListParts;
	}
	
	/**
	 * This is a public helper method to close all database connection 
	 * that use a prepared statement. If they are open they will be closed.
	 * This is also used by a user class to close the 'checkIsTableEmpty()' method.   
	 */
	public void closePreStmtConnections(){
		// Close the connections
	    try { rs.close(); } catch (Exception e) {};
	    try { preStmt.close(); } catch (Exception e) {};
	    try { con.close(); } catch (Exception e) {};
	}
	
	/**
	 * This is a private helper method to close all database connection 
	 * that use a prepared statement. If they are open they will be closed   
	 */
	private void closeStmtConnections(){
		// Close the connections
	    try { rs.close(); } catch (Exception e) {};
	    try { stmt.close(); } catch (Exception e) {};
	    try { con.close(); } catch (Exception e) {};
	}
	/**
	 * this method sums up the cost of parts for of a chosen product id
	 * @param prodid - Given product id
	 * @return double - calculated cost of parts
	 * @throws ExceptionHandler
	 */
	public double sumPartsByProductId(int prodid) throws ExceptionHandler {
		try
		{	//Set up the select statement to be used
			preStmt = con.prepareStatement("select sum(cost) as Total from parts where Prod_ID = ?");
			preStmt.setInt(1,prodid);
			// Declare a result set passing the results of the query
			rs = preStmt.executeQuery();
			rs.next();
			return rs.getDouble("Total");			
		}
		catch (SQLException sqle)
		{
			throw new ExceptionHandler("Sum Parts By Product Id Error: Database Error\n"+sqle.getClass()+" - "+sqle.getMessage());
		}finally {
			// Close the connections
			closePreStmtConnections();
		}
	}
	/**
	 * This function deletes a part form the parts table by passing it a valid part id number
	 * @param partid - A valid part id number
	 * @return 1 or 0 - 1 database changed, 0 database not changed
	 * @throws ExceptionHandler
	 */
	public int deletePartById(int partid) throws ExceptionHandler{
		try
		{	//Set up the select statement to be used
			preStmt = con.prepareStatement("DELETE FROM Parts WHERE Part_ID= ?");
			preStmt.setInt(1,partid);
			// passing the results of the query
			int success = preStmt.executeUpdate();
			
			if(checkIsTableEmpty("PARTS")){
				preStmt = con.prepareStatement("ALTER TABLE Parts ALTER COLUMN part_id RESTART WITH 1");
		    	preStmt.executeUpdate();
			}
			return success;			
		}
		catch (SQLException sqle)
		{
			throw new ExceptionHandler("Delete Part By Id Error: Database Error\n"+sqle.getClass()+" - "+sqle.getMessage());
		}finally {
			// Close the connections
			closePreStmtConnections();
		}
	}
	/**
	 * This function deletes a product form the product table by passing it a valid product id number.
	 * Also it will delete any associated parts of the chosen product.
	 * @param prodid - A valid product id number
	 * @return 1 or 0 - 1 database changed, 0 database not changed
	 * @throws ExceptionHandler
	 */
	public int deleteProductById(int prodid) throws ExceptionHandler {
		try
		{	//Set up the select statement to be used
			preStmt = con.prepareStatement("DELETE FROM Parts WHERE Prod_ID= ?");
			preStmt.setInt(1,prodid);
			// passing the results of the query
			// The result here can either by 0 or 1 if there is parts available
			// Not all products have parts so no real need to check it.
			// If there was an error then the catch statement would catch the error
			preStmt.executeUpdate();
			
			if(checkIsTableEmpty("PARTS")){
				preStmt = con.prepareStatement("ALTER TABLE Parts ALTER COLUMN part_id RESTART WITH 1");
		    	preStmt.executeUpdate();
			}
			
			//Set up the select statement to be used
			preStmt = con.prepareStatement("DELETE FROM Product WHERE Prod_ID= ?");
			preStmt.setInt(1,prodid);
			// Deletion result will be passed back
			// passing the results of the query
			int success = preStmt.executeUpdate();
		
			if(checkIsTableEmpty("PRODUCT")){
				preStmt = con.prepareStatement("ALTER TABLE Product ALTER COLUMN prod_id RESTART WITH 1");
		    	preStmt.executeUpdate();
			}
			return success;
		}
		catch (SQLException sqle)
		{
			throw new ExceptionHandler("Delete Product By Id Error: Database Error\n"+sqle.getClass()+" - "+sqle.getMessage());
		}finally {
			// Close the connections
			closePreStmtConnections();
		}
	}
    /**
	 * This method checks if the database is empty or not. This connection to the database should be
	 * closed using the "closePreStmtConnections()" method implicitly. 
	 * @param tableName - The chosen table to check
	 * @return - True (empty) false (It is not empty)
	 * @throws ExceptionHandler
	 */
	public boolean checkIsTableEmpty(String tableName) throws ExceptionHandler {
		// This connection is not closed as it is used by the ProductPartsDB class
		// and a user can also call it. There for the 'closePreStmtConnections()' method
		// should be called implicitly by the user class and not by the ProductPartsDB class
		// As it closes itself automatically
		try
		{	//Set up the select statement to be used
			preStmt = con.prepareStatement("select count(*) as Total from "+tableName);
			rs = preStmt.executeQuery();
			rs.next();
		    if(rs.getInt("Total") == 0){
		    	return true;
		    }else{
		    	return false;
		    }
		}
		catch (SQLException sqle)
		{
			throw new ExceptionHandler("Check Is Table Empty Error: Database Error\n"+sqle.getClass()+" - "+sqle.getMessage());
		}
	}
	/**
	 * This method update a product based on a product object.
	 * The values that are as marked as null will not be changed.
	 * @param updatingProduct - Product Object to be changed. 
	 * 							Null noting no change of product attribute.
	 * @return 1 or 0 - 1 database changed, 0 database not changed 
	 * @throws ExceptionHandler
	 */
	public int updateProduct(Product updatingProduct) throws ExceptionHandler{
		
		try
		{	//verify the chosen data to be changed
			if(updatingProduct.getName() != null && updatingProduct.getDescription() == null ){
				//Set up the select statement to be used
				preStmt = con.prepareStatement("UPDATE Product SET Name = ? WHERE Prod_ID = ?");
				preStmt.setString(1,updatingProduct.getName());
				preStmt.setInt(2,updatingProduct.getProdid());
			}else if(updatingProduct.getDescription() != null && updatingProduct.getName() == null ){
				//Set up the select statement to be used
				preStmt = con.prepareStatement("UPDATE Product SET Description = ? WHERE Prod_ID = ?");
				preStmt.setString(1,updatingProduct.getDescription());
				preStmt.setInt(2,updatingProduct.getProdid());
			}else{	
				// If this was an else branch the data could be set to null if both name
				// and description are null. This is why the further else if.
				//Set up the select statement to be used
				preStmt = con.prepareStatement("UPDATE Product SET Name = ?, Description = ? WHERE Prod_ID = ?");
				preStmt.setString(1,updatingProduct.getName());
				preStmt.setString(2,updatingProduct.getDescription());
				preStmt.setInt(3,updatingProduct.getProdid());
			}
			// Declare a result set passing the results of the query
			return preStmt.executeUpdate();			
		}
		catch (SQLException sqle)
		{
			throw new ExceptionHandler("Update Product Error: Database Error\n"+sqle.getClass()+" - "+sqle.getMessage());
		}
		catch (NullPointerException npx)
		{
			throw new ExceptionHandler("Update Product Error: Database Error\n"+npx.getClass()+" - "+npx.getMessage());
		}
		finally {
			// Close the connections
			closePreStmtConnections();
		}
	}
	/**
	 * This method update a part based on a part object.
	 * The values that are as marked as null will not be changed this includes the 'Cost' attribute set to -1.
	 * @param updatingProduct - Part Object to be changed. 
	 * 							Null noting no change of part attribute.
	 * 							-1 noting no change to the 'Cost' attribute.
	 * 							if 'Cost' is set to '(Double) null' a null point exception will be thrown.
	 * @return 1 or 0 - 1 database changed, 0 database not changed 
	 * @throws ExceptionHandler
	 */
	public int updatePart(Part updatingPart) throws ExceptionHandler{
		try
		{	//verify the chosen data to be changed
			if(updatingPart.getName() != null && updatingPart.getDescription() == null && updatingPart.getCost() == -1){
				//Set up the select statement to be used
				preStmt = con.prepareStatement("UPDATE Parts SET Name = ? WHERE Part_ID = ?");
				preStmt.setString(1,updatingPart.getName());
				preStmt.setInt(2,updatingPart.getPartid());
			}else if(updatingPart.getName() == null && updatingPart.getDescription() != null && updatingPart.getCost() == -1){
				//Set up the select statement to be used
				preStmt = con.prepareStatement("UPDATE Parts SET Description = ? WHERE Part_ID = ?");
				preStmt.setString(1,updatingPart.getDescription());
				preStmt.setInt(2,updatingPart.getPartid());
			}else if(updatingPart.getName() == null && updatingPart.getDescription() == null && updatingPart.getCost() != -1){
				//Set up the select statement to be used
				preStmt = con.prepareStatement("UPDATE Parts SET Cost = ? WHERE Part_ID = ?");
				preStmt.setDouble(1,updatingPart.getCost());
				preStmt.setInt(2,updatingPart.getPartid());
			}else if(updatingPart.getName() != null && updatingPart.getDescription() != null && updatingPart.getCost() == -1){
				//Set up the select statement to be used
				preStmt = con.prepareStatement("UPDATE Parts SET Name = ?, Description = ? WHERE Part_ID = ?");
				preStmt.setString(1,updatingPart.getName());
				preStmt.setString(2,updatingPart.getDescription());
				preStmt.setInt(3,updatingPart.getPartid());
			}else if(updatingPart.getName() == null && updatingPart.getDescription() != null && updatingPart.getCost() != -1){
				//Set up the select statement to be used
				preStmt = con.prepareStatement("UPDATE Parts SET Description = ?, Cost = ? WHERE Part_ID = ?");
				preStmt.setString(1,updatingPart.getDescription());
				preStmt.setDouble(2,updatingPart.getCost());
				preStmt.setInt(3,updatingPart.getPartid());
			}else if(updatingPart.getName() != null && updatingPart.getDescription() == null && updatingPart.getCost() != -1){
				//Set up the select statement to be used
				preStmt = con.prepareStatement("UPDATE Parts SET Name= ?, Cost= ? WHERE Part_ID = ?");
				preStmt.setString(1,updatingPart.getName());
				preStmt.setDouble(2,updatingPart.getCost());
				preStmt.setInt(3,updatingPart.getPartid());
			}else if(updatingPart.getName() != null && updatingPart.getDescription() != null && updatingPart.getCost() != -1){
				//Set up the select statement to be used
				preStmt = con.prepareStatement("UPDATE Parts SET Name= ?,Description = ?, Cost= ? WHERE Part_ID = ?");
				preStmt.setString(1,updatingPart.getName());
				preStmt.setString(2,updatingPart.getDescription());
				preStmt.setDouble(3,updatingPart.getCost());
				preStmt.setInt(4,updatingPart.getPartid());
			}
			 
			// Declare a result set passing the results of the query
			return preStmt.executeUpdate();			
		}
		catch (SQLException sqle)
		{
			throw new ExceptionHandler("Update Parts Error: Database Error\n"+sqle.getClass()+" - "+sqle.getMessage());
		}
		catch (NullPointerException npx)
		{
			throw new ExceptionHandler("Update Parts Error: Database Error\n"+npx.getClass()+" - "+npx.getMessage());
		}
		finally {
			// Close the connections
			closePreStmtConnections();
		}
	}
	/**
	 * This method changes the association of a part to a product if the product is in the table.
	 * @param partid - Part id of the part to change the association of. 
	 * @param prodid - Product id of the product to be associated to.
	 * @return 1 or 0 - 1 database changed, 0 database not changed 
	 * @throws ExceptionHandler
	 */
	public int changePartAssociation(int partid, int prodid) throws ExceptionHandler{
		try
		{	//Set up the select statement to be used
			preStmt = con.prepareStatement("UPDATE Parts SET Prod_ID = ? WHERE Part_ID = ?");
			preStmt.setInt(1,prodid);
			preStmt.setInt(2,partid);
						
			// Declare a result set passing the results of the query
			return preStmt.executeUpdate();			
		}
		catch (SQLException sqle)
		{
			throw new ExceptionHandler("Change Part Association Error: Database Error\n"+sqle.getClass()+" - "+sqle.getMessage());
		}
		finally {
			// Close the connections
			closePreStmtConnections();
		}
	}
}
