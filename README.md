# Product and Parts JDBC - TDD
## Description
Using Test Driven Development (TDD), develop a user interface for the following ER diagram and application description.
An ER diagram has been developed to store information about generic products and all the parts that it is made up of. This system can deal with any product that is made up of several parts; therefore you can model any enterprise you want.
## ![alt text][erdiag]
[erdiag]: https://raw.github.com/rorynee/products_parts_jdbc/master/extras/erdiagram.bmp "Er Diagram"
## Tasks
* a - You must first create the relational database that reflects the above ER diagram.
* b - Create an interface using Java that can do the following:
    1. Insert new records into either relation
    2. Update existing records in either relation
    3. Delete records in either relation (care should be taken, that all parts are deleted for a product before the product itself can be deleted).
    4. List all available products along with its total cost and the number of parts it contains.
    5. Given a product, list all of its parts and also its total cost.
* c - A custom error handler should be used
* d - User a code coverage tool
* e - Develop a simple console app to call the above commands and that would take input from a user

## Technologies Used
* Test Driven Development
* Java
* JUnit 4
* JDBC
* MySQL Database
* H2 embedded database

## Testing against the H2 Database
To test the connection with the database I have chosen to use the H2 embedded database. I have done this as it is better to test against a test database rather than the real database so as to preserve the data integrity. Both a H2 and a SQL database use the same SQL statements structure so this is a good fit.

### Configuring H2 Database
Please uncomment the following lines of code in the ProductPartsDB class under variable declaration and in the 
init_db() method in the 'H2 Database Configurations' section. Please note by doing this the 
'SQL Database Configurations' now need to be commented.

```sh
	// Declaration of file to be used 
	private static String configFileName = "dbconfigTest.properties";
```
```sh
	Class.forName(props.getProperty("jdbcdriverTest"));
	con = DriverManager.getConnection(props.getProperty("urlTest"));
```

### Configuring SQL Database
Please uncomment the following lines of code in the ProductPartsDB class under variable declaration and in the 
init_db() method in the 'SQL Database Configurations' section. Please note by doing this the 
'H2 Database Configurations' now need to be commented.

```sh
	// Declaration of file to be used
	private static String configFileName = "dbconfig.properties";
```
```sh
	Class.forName(props.getProperty("jdbcdriver"));
	con = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));
	
```

## Run Tests
To run the tests please execute the TestRunner class in the test folder.

## Test Results including code coverage data
The diagram below shows the test results on the left and the code coverage results on the right
  
![alt text][test]
[test]: https://raw.github.com/rorynee/products_parts_jdbc/master/extras/testCodeCoverage.PNG "Test Results"

## Extra Resources
Located in the Extra folder is 
* MySql connector JAR
* Database dump file as per part a of the Tasks
* H2 embedded database JAR
* Db_schema_no_data_h2 – database schema for H2 database
