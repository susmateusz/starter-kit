package pl.spring.demo.dao.impl;

public class BookSearchCriteria {

	private String title;
	private String authors;
	private String libraryName;
	
	

	public BookSearchCriteria(String title, String authors, String libraryName) {
		this.title = title;
		this.authors = authors;
		this.libraryName = libraryName;
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

}
