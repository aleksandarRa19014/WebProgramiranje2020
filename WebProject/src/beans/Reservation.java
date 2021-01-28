package beans;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class Reservation {
	
	private UUID idRes;
	private Apartment reservedApart;
	private LocalDate  bookingDate;
	private int numOfNights;
	private double price;
	private String text;
	private User guest;
	
	private StatusReser statusReser;
	
	public Reservation() {
		super();
		this.idRes = UUID.randomUUID();
		// TODO Auto-generated constructor stub
	}

	public Reservation(UUID idRes, LocalDate bookingDate, int numOfNights, double price, String text, User guest,
			StatusReser statusReser,  Apartment reservedApart) {
		super();
		this.idRes = idRes;
		this.bookingDate = bookingDate;
		this.numOfNights = numOfNights;
		this.price = price;
		this.text = text;
		this.guest = guest;
		this.statusReser = statusReser;
		this.setReservedApart(reservedApart);
	}
	
	

	public UUID getIdRes() {
		return idRes;
	}

	public void setIdRes(UUID idRes) {
		this.idRes = idRes;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate date) {
		this.bookingDate = date;
	}

	public int getNumOfNights() {
		return numOfNights;
	}

	public void setNumOfNights(int numOfNights) {
		this.numOfNights = numOfNights;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getGuest() {
		return guest;
	}

	public void setGuest(User guest) {
		this.guest = guest;
	}

	public StatusReser getStatusReser() {
		return statusReser;
	}

	public void setStatusReser(StatusReser statusReser) {
		this.statusReser = statusReser;
	}

	public Apartment getReservedApart() {
		return reservedApart;
	}

	public void setReservedApart(Apartment reservedApart) {
		this.reservedApart = reservedApart;
	}
	
	
}
