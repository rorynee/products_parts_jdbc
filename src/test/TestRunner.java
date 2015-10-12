package test;
/**
 * The class runs the tests suite
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

// Set up the tests that need to be run
@RunWith(Suite.class)
@SuiteClasses({
				TestNoDataSetUP.class,
				TestInsertProduct.class,
				TestInsertPart.class,
				TestDeleteUpdate.class,
				TestValidatingData.class,
				TestExceptionsDBError.class
			 })

public class TestRunner {

}

/********************************************************************
 * 
 * handy SQL statements for resetting the database is test failure
 * - ALTER TABLE Product ALTER COLUMN prod_id RESTART WITH 1
 * - delete from product where prod_id = 1
 * - delete from product
 * - ALTER TABLE Parts ALTER COLUMN part_id RESTART WITH 1
 * - delete from parts where part_id = 1
 * - delete from parts
 */
