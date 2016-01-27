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
	public void testInsertExpense() {
		DAOClass db = new DAOClass();
		Category category = new Category("Charity");
		Expense expense = new Expense(category, Instant.now(), "Charity Test", 500);
		boolean res = db.insertExpense(expense);
		assertEquals(res,true);		
	}

}
