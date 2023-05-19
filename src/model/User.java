package model;

public class User {

	private Integer id;
	private String firstname;
	private String lastname;
	private String personalD;
	private String email;
	private String username;
	private String password;
	private Integer numTickets;
	
	

	

	public User(Integer id, String firstname, String lastname, String personalD, String email, String username,
			String password, Integer numTickets) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.personalD = personalD;
		this.email = email;
		this.username = username;
		this.password = password;
		this.numTickets = numTickets;
	}
	
	

	public User(String firstname, String lastname, String personalD, String email, String username, String password,
			Integer numTickets) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.personalD = personalD;
		this.email = email;
		this.username = username;
		this.password = password;
		this.numTickets = numTickets;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNumTickets(Integer numTickets) {
		this.numTickets = numTickets;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPersonalD() {
		return personalD;
	}

	public void setPersonalD(String personalD) {
		this.personalD = personalD;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getNumTickets() {
		return numTickets;
	}
	
	public void setNumTickets(int numTickets) {
		this.numTickets = numTickets;
	}
	
	
	
}
