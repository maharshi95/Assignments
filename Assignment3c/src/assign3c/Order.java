package assign3c;

public class Order implements Comparable<Order> {
	private String cust_name;
	private String order_date;
	private String product_code;
	private int quantity;
	private String status;
	private double price;
	
	public static Order parseOrder(String[] rec) {
		Order o = new Order();
		o.cust_name = rec[0];
		o.order_date = rec[10];
		o.status = rec[11];
		o.product_code = rec[12];
		o.quantity = parseInt(rec[13]);
		o.price = parseDouble(rec[14]);
		if(o.cust_name.equals("NULL") || o.order_date.equals("NULL") || o.status.equals("NULL") || o.quantity == 0 || o.price == 0)
			o =null;
		return o;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Order [cust_name=" + cust_name + ", order_date=" + order_date + ", product_name=" + product_code
				+ ", quantity=" + quantity + ", status=" + status + ", price=" + price + "]";
	}
	
	@Override
	public int compareTo(Order o) {
		return toString().compareTo(o.toString());
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
