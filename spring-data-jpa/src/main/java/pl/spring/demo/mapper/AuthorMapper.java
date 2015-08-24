package pl.spring.demo.mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

public class AuthorMapper {

	public static Set<AuthorEntity> map2Entities(String authorTos) {
		String[] authors = authorTos.split(", ");
		Function<String,AuthorEntity> authorParser = s -> new AuthorEntity(s.split(" ")[0], s.split(" ")[1]);
		return Arrays.stream(authors).map(authorParser).collect(Collectors.toSet());
	}
	
	public static String map2String(Set<AuthorEntity> authors) {
		return authors.stream().map(AuthorEntity::toString).collect(Collectors.joining(", "));
	}

    public static AuthorTo map(AuthorEntity authorEntity) {
        if (authorEntity != null) {
            return new AuthorTo(authorEntity.getId(), authorEntity.getFirstName(), authorEntity.getLastName());
        }
        return null;
    }

    public static AuthorEntity map(AuthorTo authorTo) {
        if (authorTo != null) {
            return new AuthorEntity(authorTo.getId(), authorTo.getFirstName(), authorTo.getLastName());
        }
        return null;
    }
	
	public static Set<AuthorEntity> map2Entity(Set<AuthorTo> authorsTo) {
		return authorsTo.stream().map(AuthorMapper::map).collect(Collectors.toSet());
	}
	
	public static Set<AuthorTo> map2To(Set<AuthorEntity> authorsEntity) {
		return authorsEntity.stream().map(AuthorMapper::map).collect(Collectors.toSet());
	}

}
