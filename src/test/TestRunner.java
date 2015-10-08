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
				TestDeletions.class,
				TestValidatingData.class
			 })

public class TestRunner {

}

/********************************************************************
 * update needed to be finished
 * codecoverage  
 * handy SQL statements
 * - ALTER TABLE Product ALTER COLUMN prod_id RESTART WITH 1
 * - delete from product where prod_id = 1
 * - ALTER TABLE Parts ALTER COLUMN part_id RESTART WITH 1
 * - delete from parts where part_id = 1
 */
