import java.time.Instant;

public class Expense {

	Integer id;
	Category category;
	Instant timestamp;
	String description;
	Float amountSpent;
	
	public Expense(Integer id, Category category, Instant timestamp, String description, Float amountSpent) {
		super();
		this.id = id;
		this.category = category;
		this.timestamp = timestamp;
		this.description = description;
		this.amountSpent = amountSpent;
	}
	public Expense(Category category, Instant timestamp, String description, Float amountSpent) {
		super();
		this.id = 0;
		this.category = category;
		this.timestamp = timestamp;
		this.description = description;
		this.amountSpent = amountSpent;
	}
	
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Instant getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getAmountSpent() {
		return amountSpent;
	}
	public void setAmountSpent(Float amountSpent) {
		this.amountSpent = amountSpent;
	}
	
}
