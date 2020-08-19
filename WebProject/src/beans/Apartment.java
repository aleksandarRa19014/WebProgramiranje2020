package beans;

import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;


public class Apartment {
	
	private UUID id;
	private String nameApartment;
	private TypeApartment typeApart;
	private int numRoom;
	private int numOfGuests;
	private Location location;
	private Date relaseDate;
	private Collection<Date> availabilityByDates;
	private User host;
	private Collection<Comment> comments;
	private Collection<String> pathToImgs;
	private double price;
	private LocalTime checkInTime = LocalTime.of(14, 0, 0, 0);
	private LocalTime chackOutTime = LocalTime.of(10, 0, 0, 0);
	private StatusApartment status;
	private Collection<Amenity> amenites;
	
	@JsonBackReference
	private Collection<Reservation> reservations;
	
	public Apartment() {
		super();
		this.id = UUID.randomUUID();
		// TODO Auto-generated constructor stub
	}

	public Apartment(String nameApartment, TypeApartment typeApart, int numRoom, int numOfGuests, Location location,
			Date relaseDate, Collection<Date> availabilityByDates, User host, Collection<Comment> comments,
			Collection<String> pathToImgs, double price, LocalTime checkInTime, LocalTime chackOutTime,
			StatusApartment status, Collection<Amenity> amenites, Collection<Reservation> reservations) {
		super();
		
		this.id = UUID.randomUUID();
		
		this.nameApartment = nameApartment;
		this.typeApart = typeApart;
		this.numRoom = numRoom;
		this.numOfGuests = numOfGuests;
		this.location = location;
		this.relaseDate = relaseDate;
		this.availabilityByDates = availabilityByDates;
		this.host = host;
		this.comments = comments;
		this.pathToImgs = pathToImgs;
		this.price = price;
		this.checkInTime = checkInTime;
		this.chackOutTime = chackOutTime;
		this.status = status;
		this.amenites = amenites;
		this.reservations = reservations;
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

	public Date getRelaseDate() {
		return relaseDate;
	}

	public void setRelaseDate(Date relaseDate) {
		this.relaseDate = relaseDate;
	}

	public Collection<Date> getAvailabilityByDates() {
		return availabilityByDates;
	}

	public void setAvailabilityByDates(Collection<Date> availabilityByDates) {
		this.availabilityByDates = availabilityByDates;
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

}
