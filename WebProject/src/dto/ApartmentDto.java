package dto;

import java.util.Collection;

import beans.Location;

import beans.User;

public class ApartmentDto {
	private String nameApartment;
	private String typeApart;
	private String numRoom;
	private String numOfGuests;
	


	private String startDate;
	private String endDate;
	
	private User host;
	private Collection<String> images;
	private String price;
	private String checkInTime;
	private String chackOutTime;
	private String status;
	private Collection<String> amenites;
	
	private Location location;
	
	
	public ApartmentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Location getLocation() {
		return location;
	}



	public void setLocation(Location location) {
		this.location = location;
	}



	public String getNameApartment() {
		return nameApartment;
	}

	public void setNameApartment(String nameApartment) {
		this.nameApartment = nameApartment;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
