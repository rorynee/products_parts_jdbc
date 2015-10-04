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
	private static String configFileName = "dbconfig.properties";
	
	/**
	 * This is the constructor for the Product Parts Database class
	 * @throws ExceptionHandler
	 */
	public ProductPartsDB() throws ExceptionHandler{
		try {
			// Initialization of the database
			init_db();
		} catch (ExceptionHandler e) {
			throw new ExceptionHandler("Initialization Error: Failed to create connection\n"+e.getMessage());
		}
	}
	/**
	 * The following method connects this java program to our mysql database.
	 * For security reasons the database configuration details are stored separately
	 * @throws ExceptionHandler
	 */
	private static void init_db() throws ExceptionHandler
	{
		// get database configuration details form the properties file
		Properties props = new Properties();
		try {
			// Load the data into the file input stream
			props.load(new FileInputStream(configFileName));
		} catch (FileNotFoundException e) {
			throw new ExceptionHandler("DBConfig Error: File not found\n"+e.getClass()+" - "+e.getMessage());
		} catch (IOException e) {
			throw new ExceptionHandler("DBConfig Error: IO Exception\n"+e.getClass()+" - "+e.getMessage());
		}
		
		try
		{
			/*  Main Database Settings   */
			// dynamically loads the driver
//			Class.forName(props.getProperty("jdbcdriver"));
			// connect to the database using the URL, name & password
//			con = DriverManager.getConnection(props.getProperty("url"),
//												props.getProperty("username"),
//												props.getProperty("password"));
			/*  Test Database Settings  */
			// dynamically loads the driver
			Class.forName(props.getProperty("jdbcdriverTest"));
			// connect to the database using the URL, name & password
			con = DriverManager.getConnection(props.getProperty("urlTest"));

			//stmt = con.createStatement();	// Creates a Statement object which allows us to send commands to the database.
		}
		catch(Exception e)
		{
			throw new ExceptionHandler("Database Connection Error: Failed to connect to database\n"+e.getClass()+" - "+e.getMessage());
		}
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
		{	//counting the number of row associated with 'idnum' using a prepared statment
			preStmt = con.prepareStatement("select count(*) as Total from "+tableName+" where Prod_ID = ?");
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
		    try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (preStmt != null) preStmt.close(); } catch (Exception e) {};
		    try { if (con != null) con.close(); } catch (Exception e) {};
		}
	}
	
	/**
	 * The method prints all the product details form the product table 
	 * @return ArrayList<Product> - Returns an array list of Product objects
	 * @throws ExceptionHandler
	 */
	public ArrayList<Product> printProducts() throws ExceptionHandler{
		
		// Declare an array list of Products 
		ArrayList<Product> myList = new ArrayList<Product>();

		try
		{
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
				myList.add(curProduct);
			}
		}
		catch(SQLException sqle)
		{
			throw new ExceptionHandler("Print Products: Database Error\n"+sqle.getClass()+" - "+sqle.getMessage());
		}
		finally {
			// Close the connections
		    try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (stmt != null) stmt.close(); } catch (Exception e) {};
		    try { if (con != null) con.close(); } catch (Exception e) {};
			
		}
		// Return the data
		return myList;
	}
	/**
	 * The method prints all the product details form the product table 
	 * @return ArrayList<Part> - Returns an array list of Product objects
	 * @throws ExceptionHandler
	 */
	public ArrayList<Part> printParts() throws ExceptionHandler{
		
		// Declare an array list of Parts 
		ArrayList<Part> myList = new ArrayList<Part>();

		try
		{
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
				myList.add(curPart);
			}
		}
		catch(SQLException sqle)
		{
			throw new ExceptionHandler("Print Parts: Database Error\n"+sqle.getClass()+" - "+sqle.getMessage());
		}
		finally {
			// Close the connections
		    try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (stmt != null) stmt.close(); } catch (Exception e) {};
		    try { if (con != null) con.close(); } catch (Exception e) {};
			
		}
		// Return the data
		return myList;
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
			int rows = preStmt.executeUpdate();
			return rows;			
		}
		catch (SQLException sqle)
		{
			throw new ExceptionHandler("Insertion Product Error: Database Error\n"+sqle.getClass()+" - "+sqle.getMessage());
		}finally {
			// Close the connections
		    try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (preStmt != null) preStmt.close(); } catch (Exception e) {};
		    try { if (con != null) con.close(); } catch (Exception e) {};
		}	
	}
}
