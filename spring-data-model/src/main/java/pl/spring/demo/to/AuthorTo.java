package pl.spring.demo.to;

public class AuthorTo implements IdAware {
	private Long id;
	private String firstName;
	private String lastName;

	
	public AuthorTo() {
	}

	public AuthorTo(Long id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public Long getId() {
		return null;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
