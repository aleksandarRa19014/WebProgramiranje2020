package beans;

public class Amenity {

	    private Long id;
	    private String name;
	    private Boolean deleted;

	    public Amenity() {
	        super();
	        this.setDeleted(false);
	    }

	    public Amenity(Long id, String name) {
	        super();
	        this.id = id;
	        this.name = name;
	        this.setDeleted(false);
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public void delete() {
	        this.setDeleted(true);
	    }

	    public Boolean getDeleted() {
	        return deleted;
	    }

	    public void setDeleted(Boolean deleted) {
	        this.deleted = deleted;
	    }
}
