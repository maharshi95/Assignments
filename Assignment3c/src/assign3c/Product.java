package assign3c;

public class Product implements Comparable<Product> {
	private String product_code;
	private int category_id;
	private String name;
	private String description;
	private double buy_price;
	private double sell_price;
	private int quantity;
	public String cat_des;
	
	public Product(String[] rec) {
		product_code = rec[12];
		name = rec[15];
		description = rec[16].replace('\n', ' ');
		buy_price = parseDouble(rec[18]);
		sell_price = parseDouble(rec[14]);
		quantity = parseInt(rec[17]);
		cat_des = rec[19];
		if(cat_des.equals("NULL"))
				cat_des = "Unknown";
	}
	
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
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
	public double getBuy_price() {
		return buy_price;
	}
	public void setBuy_price(double buy_price) {
		this.buy_price = buy_price;
	}
	public double getSell_price() {
		return sell_price;
	}
	public void setSell_price(float sell_price) {
		this.sell_price = sell_price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Product [product_code=" + product_code + ", name=" + name + ", description=" + description
				+ ", buy_price=" + buy_price + ", sell_price=" + sell_price + ", quantity=" + quantity + "]";
	}
	@Override
	public int compareTo(Product o) {
		return product_code.compareTo(o.product_code);
	}
	
	private static double parseDouble(String s) {
		double f = 0.0;
		try {
			f = Float.parseFloat(s);
		} catch(NumberFormatException e) {
			
		}
		return f;
	}
	
	private static int parseInt(String s) {
		int i = 0;
		try {
			i = Integer.parseInt(s);
		} catch(NumberFormatException e) {
			
		}
		return i;
	}
	
}
