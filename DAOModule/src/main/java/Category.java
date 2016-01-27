
public class Category {

	Integer id;
	String name;
	
	public Category(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Category(String name) {
		super();
		this.id = 0;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
