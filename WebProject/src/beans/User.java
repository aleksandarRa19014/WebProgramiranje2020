package beans;

public class User {

	private String userName;
	private String password;
	private String name;
	private String lastName;
	private Role role;
	private Gender gander;
	private boolean isDeleted;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String userName, String password, String name, String lastName, Role role, Gender gander,
			boolean isDeleted) {
		super();
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.role = role;
		this.gander = gander;
		this.isDeleted = isDeleted;
	}
	
	public User(String userName, String password, String name, String lastName, String role, String gander, String isDeleted) {

		this.userName = userName;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		if(role.equals("admin")){
			this.role = Role.admin;
		}else if(role.equals("host")) {
			this.role = Role.host;
		}else {
			this.role = Role.guest;
		}
		if(gander.equals("male") ) {
			this.gander = Gender.male;
		}else {
			this.gander = Gender.female;
		}
		if(isDeleted.equals("true")) {
			this.isDeleted = true;
		}else {
			this.isDeleted = false;
		}
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Gender getGander() {
		return gander;
	}

	public void setGander(Gender gander) {
		this.gander = gander;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return userName + ";" + password + ";" + name + ";" + lastName
				+ ";" + role + ";" + gander + ";" + isDeleted +";";
	}

	
}
