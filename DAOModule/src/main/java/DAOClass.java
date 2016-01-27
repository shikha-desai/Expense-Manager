import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		if(con != null) {
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
		
		if(con == null) {
			this.connectDB();
		}
		
		stmt = null;
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
		
		try {
			stmt.executeUpdate(DBConstants.CREATE_EXPENSE_TABLE);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		try {
			stmt.executeUpdate(DBConstants.CREATE_CATEGORY_TABLE);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		
		this.addCategories();
		
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
	
	boolean addCategories() {
		return true;
	}
	
	boolean insertCategory(Category category) {
		
		String sql = "INSERT INTO " + DBConstants.CATEGORY_TABLE + " VALUES ('" + category.name + "');";
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/*boolean insertExpense(Expense expense) {
		
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
			
		}
		else {
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
		return true;
	}*/
	
	boolean closeDB() {
		
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

}
