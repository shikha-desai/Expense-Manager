import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;

public class DAOClass {
	
	Connection con;
	Statement stmt;
	String sql;
		
	public DAOClass() {
		super();
		this.connectDB();
		this.initDB();
	}

	boolean connectDB() {
		if(con == null) {
			try {
				Class.forName(DBConstants.JDBC_DRIVER);
				
				try {
					con = DriverManager.getConnection(DBConstants.DB_URL, DBConstants.USER, DBConstants.PASS);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}

		return true;
	}
	
	boolean initDB() {
		
		boolean res;
		
		if(con == null) {
			res = this.connectDB();
			if(res == false) {
				return false;
			}
		}

		res = this.createAndUseDatabase();
		if(res == false)	return false;		
		
		res = this.createCategoryTable();
		if(res == false)	return false;
		
		res = this.createExpenseTable();
		if(res == false)	return false;
		
		res = this.addCategories();
		if(res == false) {
			return false;
		}
		
		res = this.closeStmt();
		if(res == false)	return false;
		
		return true;
	}
	
	boolean createAndUseDatabase() {
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		try {
			stmt.executeUpdate(DBConstants.CREATE_DB);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		try {
			stmt.execute(DBConstants.USE_DB);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		boolean res = this.closeStmt();
		if(res == false)	return false;
		
		return true;
	}
	
	boolean createCategoryTable() {
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(DBConstants.CREATE_CATEGORY_TABLE);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		
		boolean res = this.closeStmt();
		if(res == false)	return false;
		
		return true;
	}
	
	boolean createExpenseTable() {
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(DBConstants.CREATE_EXPENSE_TABLE);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		
		boolean res = this.closeStmt();
		if(res == false)	return false;
		
		return true;
	}
	
	boolean addCategories() {
		Path path = Paths.get("src", "main", "resources" , "Categories.txt");
		
		try {
			Stream<String> stream = Files.lines(path);		
			stream.forEach((String name) -> this.insertCategory(new Category(name)));
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		
		boolean res = this.closeStmt();
		if(res == false)	return false;
		
		return true;
	}
	
	boolean insertCategory(Category category) {
		
		String sql = "INSERT IGNORE INTO " + DBConstants.CATEGORY_TABLE + "(" + DBConstants.NAME 
				+ ") VALUES ('" + category.name + "');";
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		boolean res = this.closeStmt();
		if(res == false)	return false;
		
		return true;
	}
	
	boolean deleteCategory(Category category) {
		
		String sql = "DELETE FROM " + DBConstants.CATEGORY_TABLE + " WHERE " + DBConstants.NAME 
				+ "='" + category.name + "';";
		try {
			stmt = con.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		boolean res = this.closeStmt();
		if(res == false)	return false;
		
		return true;
	}
	
	
	boolean insertExpense(Expense expense) {
		
		try {
			stmt = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		
		String sql = "SELECT " + DBConstants.ID + " FROM " + DBConstants.CATEGORY_TABLE
				+ " WHERE " + DBConstants.NAME + "='" + expense.category.name + "';";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt(DBConstants.ID);
				expense.category.id = id; 
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		
		if(expense.category.id != 0) {
			sql = "INSERT INTO " + DBConstants.EXPENSE_TABLE + "("
		+ DBConstants.CATEGORY_ID + ", " + DBConstants.TIMESTAMP + ", "
					+ DBConstants.DESCRIPTION + ", "
		+ DBConstants.AMOUNT_SPENT + ") VALUES("
		+ expense.category.id + ", '" + expense.timestamp +"', '"
		+ expense.description + "', " + expense.amountSpent + ");";
			
			try {
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		else {
			return false;
		}
		
		boolean res = this.closeStmt();
		if(res == false)	return false;
		
		return true;
	}
	
	boolean deleteExpenseWithId(Integer id) {
		
		String sql = "DELETE FROM " + DBConstants.EXPENSE_TABLE + " WHERE " + DBConstants.ID 
				+ "=" + id + ";";
		try {
			stmt = con.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		boolean res = this.closeStmt();
		if(res == false)	return false;
		
		return true;
	}
	
	boolean deleteExpenseWithCategory(Category category) {
		
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		String sql = "SELECT " + DBConstants.ID + " FROM " + DBConstants.CATEGORY_TABLE
				+ " WHERE " + DBConstants.NAME + "='" + category.name + "';";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt(DBConstants.ID);
				category.id = id; 
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		
		sql = "DELETE FROM " + DBConstants.EXPENSE_TABLE + " WHERE " + DBConstants.CATEGORY_ID
				+ "=" + category.id + ";";
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		boolean res = this.closeStmt();
		if(res == false)	return false;
		
		return true;
	}
	
	boolean clearExpenseTable() {
		
		try {
			stmt = con.createStatement();
			stmt.execute(DBConstants.DELETE_FROM_EXPENSE_TABLE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	boolean clearCategoryTable() {
		
		try {
			stmt = con.createStatement();
			stmt.execute(DBConstants.DELETE_FROM_CATEGORY_TABLE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	boolean clearAllTables() {
		
		boolean res;
		
		res = this.clearExpenseTable();
		if(res == false)	return false;
		res = this.clearCategoryTable();
		if(res == false)	return false;
		
		return true;		
	}
	
	boolean removeExpenseTable() {
		
		try {
			stmt = con.createStatement();
			stmt.execute(DBConstants.DROP_EXPENSE_TABLE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	boolean removeCategoryTable() {
		
		try {
			stmt = con.createStatement();
			stmt.execute(DBConstants.DROP_CATEGORY_TABLE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	boolean removeAllTables() {
		
		boolean res;
		
		res = this.removeExpenseTable();
		if(res == false)	return false;
		res = this.removeCategoryTable();
		if(res == false)	return false;
		
		return true;		
	}
	
	boolean closeStmt() {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	boolean closeCon() {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	boolean closeDB() {
		
		boolean res;
		
		res = this.closeStmt();
		if(res == false)	return false;
		res = this.closeCon();
		if(res == false)	return false;
		
		return true;
	}

}
