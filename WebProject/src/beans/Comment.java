package beans;

public class Comment {
	private User guest;
	private String idApartment;
	private String text;
	private int rating;
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(User guest, String idApartment, String text, int rating) {
		super();
		this.guest = guest;
		this.idApartment = idApartment;
		this.text = text;
		this.rating = rating;
	}

	public User getGuest() {
		return guest;
	}

	public void setGuest(User guest) {
		this.guest = guest;
	}

	public String getIdApartment() {
		return idApartment;
	}

	public void setIdApartment(String idApartment) {
		this.idApartment = idApartment;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
}
