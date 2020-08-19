package dto;

import java.util.Collection;
import beans.Location;

import beans.User;

public class ApartmentDto {
	private String nameApartment;
	private String typeApart;
	private String numRoom;
	private String numOfGuests;
	
	private String street;
	
	private String zipCode;
	private String place;

	
	private String relaseDate;
	private User host;
	private Collection<String> images;
	private String price;
	private String checkInTime;
	private String chackOutTime;
	private String status;
	private Collection<String> amenites;
	
	public ApartmentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApartmentDto(String idApartment, String typeApart, String numRoom, String numOfGuests, String street,
			String zipCode, String place, String relaseDate, User host, Collection<String> images, String price,
			String checkInTime, String chackOutTime, String status, Collection<String> amenites) {
		super();
		this.nameApartment = idApartment;
		this.typeApart = typeApart;
		this.numRoom = numRoom;
		this.numOfGuests = numOfGuests;
		this.street = street;
		this.zipCode = zipCode;
		this.place = place;
		this.relaseDate = relaseDate;
		this.host = host;
		this.images = images;
		this.price = price;
		this.checkInTime = checkInTime;
		this.chackOutTime = chackOutTime;
		this.status = status;
		this.amenites = amenites;
	}

	public String getNameApartment() {
		return nameApartment;
	}

	public void setNameApartment(String idApartment) {
		this.nameApartment = idApartment;
	}

	public String getTypeApart() {
		return typeApart;
	}

	public void setTypeApart(String typeApart) {
		this.typeApart = typeApart;
	}

	public String getNumRoom() {
		return numRoom;
	}

	public void setNumRoom(String numRoom) {
		this.numRoom = numRoom;
	}

	public String getNumOfGuests() {
		return numOfGuests;
	}

	public void setNumOfGuests(String numOfGuests) {
		this.numOfGuests = numOfGuests;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getRelaseDate() {
		return relaseDate;
	}

	public void setRelaseDate(String relaseDate) {
		this.relaseDate = relaseDate;
	}

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}

	public Collection<String> getImages() {
		return images;
	}

	public void setImages(Collection<String> images) {
		this.images = images;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}

	public String getChackOutTime() {
		return chackOutTime;
	}

	public void setChackOutTime(String chackOutTime) {
		this.chackOutTime = chackOutTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Collection<String> getAmenites() {
		return amenites;
	}

	public void setAmenites(Collection<String> amenites) {
		this.amenites = amenites;
	}
    
	
}
