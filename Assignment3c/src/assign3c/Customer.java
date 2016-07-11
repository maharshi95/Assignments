package assign3c;

public class Customer implements Comparable<Customer> {
	
	public static final int[] indices = new int[]{0,1,2,3};
	
	private String email_id;
	private String company_name;
	private String first_name;
	private String last_name;
	private String contact;
	private String street1;
	private int current_add_code;
	
	public Customer(String[] rec) {
		company_name = rec[0];
		first_name = rec[1];
		last_name = rec[2];
		contact = rec[3];
		email_id = first_name.toLowerCase() + "_" + last_name.toLowerCase() + "@gmail.com";
		street1 = rec[4];
	}
	
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public int getCurrent_add_code() {
		return current_add_code;
	}
	public void setCurrent_add_code(int current_add_code) {
		this.current_add_code = current_add_code;
	}
	
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}
	
	@Override
	public String toString() {
		return "Customer [email_id=" + email_id + ", company_name=" + company_name + ", first_name=" + first_name
				+ ", last_name=" + last_name + ", contact=" + contact + ", current_add_code=" + current_add_code + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return toString().equals(obj.toString());
	}
	
	@Override
	public int compareTo(Customer o) {
		return toString().compareTo(o.toString());
	}
}
