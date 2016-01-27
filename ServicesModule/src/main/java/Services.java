import java.util.List;

public class Services {

	static DAOClass db = new DAOClass();

	public Services() {
		super();
	}
	
	static List<Category> getAllCategories() {
		List<Category> categories = db.getAllCategories();
		return categories;
	}
	
}
