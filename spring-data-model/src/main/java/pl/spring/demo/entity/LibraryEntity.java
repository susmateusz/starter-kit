package pl.spring.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LIBRARY")
public class LibraryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="name",nullable = false, length = 50)
	private String name;
	@OneToMany(cascade=CascadeType.REMOVE,mappedBy="library")
//	@JoinTable(name = "LIBRARY_BOOK", joinColumns = @JoinColumn(name = "library_id") , inverseJoinColumns = @JoinColumn(name = "book_id") )
	private Set<BookEntity> books = new HashSet<>();

	public LibraryEntity() {
	}

	public LibraryEntity(Long id, String name) {
		this.id = id;
		this.name = name;
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

}
