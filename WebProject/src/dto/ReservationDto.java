package dto;

import java.util.Date;

public class ReservationDto {
	
	private String idApartment;
	private String  bookingDate;
	private int numOfNights;
	private String text;
	
	public ReservationDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReservationDto(String bookingDate, int numOfNights , String text, String id) {
		super();
		this.bookingDate = bookingDate;
		this.numOfNights = numOfNights;

		this.text = text;
		this.idApartment = id;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public int getNumOfNights() {
		return numOfNights;
	}
	public void setNumOfNights(int numOfNights) {
		this.numOfNights = numOfNights;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIdApartment() {
		return idApartment;
	}
	public void setIdApartment(String idApartment) {
		this.idApartment = idApartment;
	}
	

}
