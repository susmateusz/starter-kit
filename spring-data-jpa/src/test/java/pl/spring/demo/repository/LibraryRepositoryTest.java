package pl.spring.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.LibraryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class LibraryRepositoryTest {

	@Autowired
	private LibraryRepository libraryRepository;

	@Test
	public void shouldFindLibraryById() {
		// given
		final long libraryId = 15;
		// when
		LibraryEntity libraryEntity = libraryRepository.findOne(libraryId);
		// then
		assertNotNull(libraryEntity);
		assertEquals("Biblioteka PWr filia nr 1",libraryEntity.getName());
	}
	
	@Test
	public void shouldReturnNullLibraryById() {
		// given
		final long libraryId = 150;
		// when
		LibraryEntity libraryEntity = libraryRepository.findOne(libraryId);
		// then
		assertNull(libraryEntity);
	}
	
	@Test
	public void shouldFindLibraryByName() {
		// given
		final String libraryName = "mbp";
		// when
		List<LibraryEntity> librariesEntity = libraryRepository.findLibraryByName(libraryName);
		// then
		assertNotNull(librariesEntity);
		assertFalse(librariesEntity.isEmpty());
		assertEquals("MBP filia nr 1", librariesEntity.get(0).getName());
		assertEquals("MBP filia nr 2", librariesEntity.get(1).getName());
	}
	
	@Test
	public void shouldNotFindAnyLibraryByName() {
		// given
		final String libraryName = "yyy";
		// when
		List<LibraryEntity> librariesEntity = libraryRepository.findLibraryByName(libraryName);
		// then
		assertNotNull(librariesEntity);
		assertTrue(librariesEntity.isEmpty());
	}

}
