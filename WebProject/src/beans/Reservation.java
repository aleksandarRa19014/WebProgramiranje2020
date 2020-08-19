package beans;

import java.util.Date;

public class Reservation {
	
	private String idApartment;
	private Date  bookingDate;
	private int numOfNights;
	private double price;
	private String text;
	private User guest;
	
	private StatusReser statusReser;
	
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(String idApartment, Date bookingDate, int numOfNights, double price, String text, User guest,
			StatusReser statusReser) {
		super();
		this.idApartment = idApartment;
		this.bookingDate = bookingDate;
		this.numOfNights = numOfNights;
		this.price = price;
		this.text = text;
		this.guest = guest;
		this.statusReser = statusReser;
	}

	public String getIdApartment() {
		return idApartment;
	}

	public void setIdApartment(String idApartment) {
		this.idApartment = idApartment;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
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
	
	
}
