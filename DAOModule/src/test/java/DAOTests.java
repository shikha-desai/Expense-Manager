import static org.junit.Assert.*;

import java.time.Instant;

import org.junit.Test;

public class DAOTests {

	@Test
	public void testConnectDB() {
		DAOClass db = new DAOClass();
		boolean res = db.connectDB();
		assertEquals(res,true);		
	}
	
	@Test
	public void testClearAllTables() {
		DAOClass db = new DAOClass();
		boolean res = db.clearAllTables();
		assertEquals(res,true);		
	}
	
	@Test
	public void testremoveAllTables() {
		DAOClass db = new DAOClass();
		boolean res = db.removeAllTables();
		assertEquals(res,true);		
	}
	
	@Test
	public void testCloseDB() {
		DAOClass db = new DAOClass();
		boolean res = db.closeDB();
		assertEquals(res,true);		
	}
	
	@Test
	public void testInitDB() {
		DAOClass db = new DAOClass();
		db.connectDB();
		boolean res = db.initDB();
		assertEquals(res,true);		
	}
	
	@Test
	public void testAddCategories() {
		DAOClass db = new DAOClass();
		boolean res = db.addCategories();
		assertEquals(res,true);		
	}
	
	
	@Test
	public void testInsertCategory() {
		DAOClass db = new DAOClass();
		Category category = new Category("Charity");
		boolean res = db.insertCategory(category);
		assertEquals(res,true);		
	}
	
	
	@Test
	public void testDeleteCategory() {
		DAOClass db = new DAOClass();
		Category category = new Category("Transport");
		boolean res = db.deleteCategory(category);
		assertEquals(res,true);		
	}
	
	@Test
	public void testInsertExpense() {
		DAOClass db = new DAOClass();
		Category category = new Category("Charity");
		Expense expense = new Expense(category, Instant.now(), "Charity Test", (float)500);
		boolean res = db.insertExpense(expense);
		assertEquals(res,true);		
	}
	
	@Test
	public void testDeleteExpenseWithId() {
		DAOClass db = new DAOClass();
		Category category = new Category("Charity");
		Expense expense = new Expense(category, Instant.now(), "Charity Test", (float)500);
		boolean res = db.insertExpense(expense);
		res = db.deleteExpenseWithId(1);
		assertEquals(res,true);		
	}
	
	@Test
	public void testDeleteExpenseWithCategory() {
		DAOClass db = new DAOClass();
		Category category = new Category("Charity");
		Expense expense = new Expense(category, Instant.now(), "Charity Test", (float)500);
		boolean res = db.insertExpense(expense);
		res = db.deleteExpenseWithCategory(category);
		assertEquals(res,true);		
	}

}
