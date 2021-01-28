package beans;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;


public class Apartment {
	
	private UUID id;
	private String nameApartment;
	private TypeApartment typeApart;
	private int numRoom;
	private int numOfGuests;
	private Location location;
	private List<LocalDate> datesForRent = new ArrayList<LocalDate>();
	private List<LocalDate> datesAvailable = new ArrayList<LocalDate>();
	private User host;
	private Collection<Comment> comments;
	private Collection<String> pathToImgs;
	private double price;
	private LocalTime checkInTime = LocalTime.of(14, 0, 0, 0);
	private LocalTime chackOutTime = LocalTime.of(10, 0, 0, 0);
	private StatusApartment status;
	private Collection<Amenity> amenites;
	
	private Boolean deleted;
	
	@JsonBackReference
	private Collection<Reservation> reservations = new ArrayList<Reservation>();
	
	
	
	public Apartment() {
		super();
		this.id = UUID.randomUUID();
		this.deleted = false;
		// TODO Auto-generated constructor stub
	}

	public Apartment(UUID id, String nameApartment, TypeApartment typeApart, int numRoom, int numOfGuests,
			Location location, List<LocalDate> datesForRent, List<LocalDate> datesAvailable, User host,
			Collection<Comment> comments, Collection<String> pathToImgs, double price, LocalTime checkInTime,
			LocalTime chackOutTime, StatusApartment status, Collection<Amenity> amenites,
			Collection<Reservation> reservations, Boolean deleted) {
		super();
		this.id = id;
		this.nameApartment = nameApartment;
		this.typeApart = typeApart;
		this.numRoom = numRoom;
		this.numOfGuests = numOfGuests;
		this.location = location;
		this.datesForRent = datesForRent;
		this.datesAvailable = datesAvailable;
		this.host = host;
		this.comments = comments;
		this.pathToImgs = pathToImgs;
		this.price = price;
		this.checkInTime = checkInTime;
		this.chackOutTime = chackOutTime;
		this.status = status;
		this.amenites = amenites;
		this.reservations = reservations;
		this.deleted = deleted;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNameApartment() {
		return nameApartment;
	}

	public void setNameApartment(String nameApartment) {
		this.nameApartment = nameApartment;
	}

	public TypeApartment getTypeApart() {
		return typeApart;
	}

	public void setTypeApart(TypeApartment typeApart) {
		this.typeApart = typeApart;
	}

	public int getNumRoom() {
		return numRoom;
	}

	public void setNumRoom(int numRoom) {
		this.numRoom = numRoom;
	}

	public int getNumOfGuests() {
		return numOfGuests;
	}

	public void setNumOfGuests(int numOfGuests) {
		this.numOfGuests = numOfGuests;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<LocalDate> getDatesForRent() {
		return datesForRent;
	}

	public void setDatesForRent(List<LocalDate> datesForRent) {
		this.datesForRent = datesForRent;
	}

	public List<LocalDate> getDatesAvailable() {
		return datesAvailable;
	}

	public void setDatesAvailable(List<LocalDate> datesAvailable) {
		this.datesAvailable = datesAvailable;
	}

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}

	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

	public Collection<String> getPathToImgs() {
		return pathToImgs;
	}

	public void setPathToImgs(Collection<String> pathToImgs) {
		this.pathToImgs = pathToImgs;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalTime getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(LocalTime checkInTime) {
		this.checkInTime = checkInTime;
	}

	public LocalTime getChackOutTime() {
		return chackOutTime;
	}

	public void setChackOutTime(LocalTime chackOutTime) {
		this.chackOutTime = chackOutTime;
	}

	public StatusApartment getStatus() {
		return status;
	}

	public void setStatus(StatusApartment status) {
		this.status = status;
	}

	public Collection<Amenity> getAmenites() {
		return amenites;
	}

	public void setAmenites(Collection<Amenity> amenites) {
		this.amenites = amenites;
	}

	public Collection<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Collection<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	
    
	

}
