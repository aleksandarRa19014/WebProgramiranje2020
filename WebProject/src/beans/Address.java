package beans;

public class Address {
	private String street;
	private int streetNumber;
	private int zipCode;
	private String place;
	
	
	
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Address(String street, int streetNumber, int zipCode, String place) {
		super();
		this.street = street;
		this.streetNumber = streetNumber;
		this.zipCode = zipCode;
		this.place = place;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public int getStreetNumber() {
		return streetNumber;
	}


	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}


	public int getZipCode() {
		return zipCode;
	}


	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}
	
	
	
	
}
