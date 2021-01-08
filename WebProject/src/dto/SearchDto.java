package dto;

public class SearchDto {
	private String place;
	private String startDate;
	private String endDate;
	private int minPrice;
	private int maxPrice;
	private int minRoomNo;
	private int maxRoomNo;
	private int personNo;
    
	public SearchDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchDto(String place, String startDate, String endDate, int minPrice, int maxPrice, int minRoomNo,
			int maxRoomNo, int personNo) {
		super();
		this.place = place;
		this.startDate = startDate;
		this.endDate = endDate;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.minRoomNo = minRoomNo;
		this.maxRoomNo = maxRoomNo;
		this.personNo = personNo;
	}
	
	

	

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
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

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public int getMinRoomNo() {
		return minRoomNo;
	}

	public void setMinRoomNo(int minRoomNo) {
		this.minRoomNo = minRoomNo;
	}

	public int getMaxRoomNo() {
		return maxRoomNo;
	}

	public void setMaxRoomNo(int maxRoomNo) {
		this.maxRoomNo = maxRoomNo;
	}

	public int getPersonNo() {
		return personNo;
	}

	public void setPersonNo(int personNo) {
		this.personNo = personNo;
	}
	
	
    
    

}
