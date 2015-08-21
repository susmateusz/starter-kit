package pl.spring.demo.mapper;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import pl.spring.demo.entity.AuthorEntity;

public class AuthorMapper {

	public static Set<AuthorEntity> map2Entities(String authorTos) {
		String[] authors = authorTos.split(", ");
		Function<String,AuthorEntity> authorParser = s -> new AuthorEntity(s.split(" ")[0], s.split(" ")[1]);
		return Arrays.stream(authors).map(authorParser).collect(Collectors.toSet());
	}
	
	public static String map2String(Set<AuthorEntity> authors) {
		return authors.stream().map(AuthorEntity::toString).collect(Collectors.joining(", "));
	}

}
