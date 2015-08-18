package pl.spring.demo.to;

import java.util.Objects;

public class BookTo {
	private Long id;
	private String title;
	private String authors;
	private String libraryName;

	public BookTo() {
	}

	public BookTo(Long id, String title, String authors) {
		this(id,title,authors,"");
	}
	public BookTo(Long id, String title, String authors, String libraryName) {
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.libraryName = libraryName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}
	
	public String getLibraryName() {
		return libraryName;
	}
	
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BookTo other = (BookTo) obj;
		return Objects.equals(this.getAuthors(), other.getAuthors())
				&& Objects.equals(this.getTitle(), other.getTitle()) && Objects.equals(this.getId(), other.getId());
	}

}
