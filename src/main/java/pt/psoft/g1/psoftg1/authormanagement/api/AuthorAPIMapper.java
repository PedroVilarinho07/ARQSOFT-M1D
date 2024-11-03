package pt.psoft.g1.psoftg1.authormanagement.api;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pt.psoft.g1.psoftg1.authormanagement.dto.response.AuthorCoAuthorBooksResponseDTO;
import pt.psoft.g1.psoftg1.authormanagement.dto.response.AuthorResponseDTO;
import pt.psoft.g1.psoftg1.authormanagement.dto.response.CoAuthorResponseDTO;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import pt.psoft.g1.psoftg1.bookmanagement.dto.response.BookShortResponseDTO;
import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import pt.psoft.g1.psoftg1.shared.api.MapperInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public abstract class AuthorAPIMapper extends MapperInterface {

    @Mapping(target = "_links", expression = "java(mapLinks(author))")
    public abstract AuthorResponseDTO toAuthorDTO(Author author);

    public abstract List<AuthorResponseDTO> toAuthorDTO(List<Author> authors);

    @Mapping(target = "_links", source = "author", qualifiedByName = "mapAuthorLinks")
    @Mapping(target = "books", source = "books", qualifiedByName = "toBookShortList")
    public abstract CoAuthorResponseDTO toCoAuthorDTO(Author author, List<Book> books);

    @Named(value = "toBookShort")
    @Mapping(target = "_links", source = ".", qualifiedByName = "mapBookShortLink")
    public abstract BookShortResponseDTO toBookShortDTO(Book book);

    @Named(value = "toBookShortList")
    @IterableMapping(qualifiedByName = "toBookShort")
    public abstract List<BookShortResponseDTO> toBookShortDTO(List<Book> books);

    public abstract AuthorCoAuthorBooksResponseDTO toAuthorCoAuthorBooksDTO(Author author, List<CoAuthorResponseDTO> coauthors);

    @Named(value = "mapAuthorLinks")
    public Map<String, Object> mapLinks(final Author author){
        String authorUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/authors/")
                .path(author.getAuthorNumber().toString())
                .toUriString();

        String booksByAuthorUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/authors/")
                .path(author.getAuthorNumber().toString())
                .path("/books")
                .toUriString();

        Map<String, Object> links = new HashMap<>();

        links.put("author", authorUri);
        links.put("photo", generatePhotoUrl(author));
        links.put("booksByAuthor", booksByAuthorUri);

        return links;
    }

    protected String generatePhotoUrl(Author author) {
        String authorNumber = author.getAuthorNumber().toString();
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/authors/{authorNumber}/photo").buildAndExpand(authorNumber).toUri().toString();
    }

    @Named(value = "mapBookShortLink")
    public String mapShortBookLink(final Book book) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/books/")
                .path(book.getIsbn())
                .toUriString();
    }

}
