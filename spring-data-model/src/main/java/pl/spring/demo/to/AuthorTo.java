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
		return id;
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

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof AuthorTo))
			return false;
		AuthorTo other = (AuthorTo) obj;
		return this.getFirstName().equals(other.getFirstName())
				&& this.getLastName().equals(other.getLastName());
	}

}
