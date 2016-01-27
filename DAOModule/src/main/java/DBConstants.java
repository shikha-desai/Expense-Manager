
public class DBConstants {

	//JDBC driver name and db url
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost/";

	//Database credentials
	public static final String USER = "root";
	public static final String PASS = "password";
	
	//Database Name
	public static final String DB_NAME = "expense_manager";
	
	//Create database
	public static final String CREATE_DB = "CREATE DATABASE IF NOT EXISTS " + DB_NAME + ";";
	
	//Use database
	public static final String USE_DB = "USE " + DB_NAME + ";";
	
	//Table Names
	public static final String CATEGORY_TABLE = "category_table";
	public static final String EXPENSE_TABLE = "expense_table";
	
	//Column Names
	public static final String ID = "id";
	public static final String TIMESTAMP = "timestamp";
	public static final String DESCRIPTION = "description";
	public static final String AMOUNT_SPENT = "amount";
	public static final String CATEGORY_ID = "category_id";
	public static final String NAME = "name";
	
	//Create Tables
	public static final String CREATE_EXPENSE_TABLE = "CREATE TABLE IF NOT EXISTS " + EXPENSE_TABLE +"("
			+ ID + " int AUTO_INCREMENT PRIMARY KEY, "
			+ CATEGORY_ID + " int, "
			+ TIMESTAMP + " varchar(25), "
			+ DESCRIPTION + " varchar(255), "
			+ AMOUNT_SPENT + " float, "
			+ "FOREIGN KEY (" + CATEGORY_ID + ") REFERENCES " + CATEGORY_TABLE + "(" + ID + ")"
			+ ");";
	public static final String CREATE_CATEGORY_TABLE = "CREATE TABLE IF NOT EXISTS " + CATEGORY_TABLE +"("
			+ ID + " int AUTO_INCREMENT PRIMARY KEY, "
			+ NAME + " varchar(50) UNIQUE"
			+ ");";
	
	//Delete all rows from table with rollback memory
	public static final String DELETE_FROM_EXPENSE_TABLE = "DELETE FROM " + EXPENSE_TABLE + ";";
	public static final String DELETE_FROM_CATEGORY_TABLE = "DELETE FROM " + CATEGORY_TABLE + ";";
	
	//Delete all data from table
	public static final String TRUNCATE_EXPENSE_TABLE = "TRUNCATE TABLE " + EXPENSE_TABLE + ";";
	public static final String TRUNCATE_CATEGORY_TABLE = "TRUNCATE TABLE " + CATEGORY_TABLE + ";";
	
	//Delete entire table
	public static final String DROP_EXPENSE_TABLE = "DROP TABLE " + EXPENSE_TABLE + ";";
	public static final String DROP_CATEGORY_TABLE = "DROP TABLE " + CATEGORY_TABLE + ";";
	
}
