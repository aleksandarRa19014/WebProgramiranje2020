package beans;

import java.util.UUID;

public class Amenity {
	

	    private UUID id;
	    private String name;
	    private Boolean deleted;

	    public Amenity() {
	        super();
	        this.setDeleted(false);
	        this.id = UUID.randomUUID();
	    }
	    

		public Amenity(String name, Boolean deleted) {
			super();
			this.id = UUID.randomUUID();
			this.name = name;
			this.deleted = deleted;
		}
	    
	    

		public UUID getId() {
			return id;
		}

		public void setId(UUID id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Boolean getDeleted() {
			return deleted;
		}

		public void setDeleted(Boolean deleted) {
			this.deleted = deleted;
		}




}
