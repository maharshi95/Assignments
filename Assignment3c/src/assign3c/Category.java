package assign3c;

public class Category implements Comparable<Category>{
	private String name;
	private String description;
	
	public Category(String[] rec) {
		name = "";
		description = rec[19];
		if(description.equals("NULL"))
				description = "Unknown";
	}
	
	@Override
	public String toString() {
		return "Category [name=" + name + ", description=" + description + "]";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public boolean equals(Object obj) {
		return toString().equals(obj.toString());
	}
	
	@Override
	public int compareTo(Category o) {
		return toString().compareTo(o.toString());
	}
}
