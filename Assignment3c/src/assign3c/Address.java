package assign3c;

public class Address implements Comparable<Address>{
	
	public static final int[] indices = new int[]{4,5,6,7,9,8,3};
	
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	private String mobile;
	
	public Address(String[] rec) {
		mobile = rec[3];
		street1 = rec[4];
		street2 = rec[5];
		city = rec[6];
		state = rec[7];
		zipcode = rec[8];
		country = rec[9];
	}
	
	public String getStreet1() {
		return street1;
	}
	public void setStreet1(String street1) {
		this.street1 = street1;
	}
	public String getStreet2() {
		return street2;
	}
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "Address [street1=" + street1 + ", street2=" + street2 + ", city=" + city + ", state=" + state
				+ ", country=" + country + ", zipcode=" + zipcode + ", mobile=" + mobile + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		return toString().equals(obj.toString());
	}
	
	@Override
	public int compareTo(Address o) {
		return toString().compareTo(o.toString());
	}
}
