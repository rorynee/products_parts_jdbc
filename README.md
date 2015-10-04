# Product and Parts JDBC - TDD
## Description
Using Test Driven Development (TDD), develop a user interface for the following ER diagram and application description.
An ER diagram has been developed to store information about generic products and all the parts that it is made up of. This system can deal with any product that is made up of several parts; therefore you can model any enterprise you want.
## ![alt text][logo]
[logo]: https://raw.github.com/rorynee/products_parts_jdbc/master/extras/erdiagram.bmp "Er Diagram"
## Tasks
* a - You must first create the relational database that reflects the above ER diagram.
* b - Create an interface using Java that can do the following:
    1. Insert new records into either relation
    2. Update existing records in either relation
    3. Delete records in either relation (care should be taken, that all parts are deleted for a product before the product itself can be deleted).
    4. List all available products along with its total cost and the number of parts it contains.
    5. Given a product, list all of its parts and also its total cost.
* c - A custom error handler should be used

## Technologies Used
* Test Driven Development
* Java
* JDBC
* MySQL Database
* Er Diagrams
* H2 embedded database

## Testing against the H2 DB
To test the connection with the database I have chosen to use the H2 embedded database. I have done this as it is better to test against a test database rather than the real database so as to preserve the data integrity. Both a H2 and a SQL database use the same SQL statements structure so this is a good fit.

## Extra Resources
Located in the Extra folder is 
* MySql connector JAR
* Database dump file as per part a of the Tasks
* H2 embedded database JAR
* Db_schema_no_data_h2 – database schema for H2 database
