/*
package pt.psoft.g1.psoftg1.bookmanagement.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.psoft.g1.psoftg1.authormanagement.dataModel.AuthorDataModelMongo;
import pt.psoft.g1.psoftg1.authormanagement.services.AuthorService;
import pt.psoft.g1.psoftg1.bookmanagement.dataModel.BookDataModelMongo;
import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMongoMapper {

    private final AuthorService authorService;

    @Autowired
    public BookMongoMapper(AuthorService authorService) {
        this.authorService = authorService;
    }

    public BookDataModelMongo toDataModel(Book book) {
        // Extrai os números dos autores como List<Long> para persistência
        List<Long> authorNumbers = book.getAuthors().stream()
                .map(Author::getAuthorNumber)
                .collect(Collectors.toList());

        return new BookDataModelMongo(
                book.getIsbn(),
                book.getTitle().getTitle(),
                book.getGenre().toString(),
                book.getDescription(),
                authorNumbers, // Persistência em formato Long
                book.getPhoto() != null ? book.getPhoto().getPhotoFile() : null
        );
    }

    public Book toDomainModel(BookDataModelMongo dataModel) {
        // Recupera os autores a partir de seus números (List<Long>) usando o AuthorService
        List<Author> authors = authorService.findAuthorsByNumbers(dataModel.getAuthorNumbers()).stream()
                .map(this::toDomainAuthor)
                .collect(Collectors.toList());

        // Cria o objeto Book injetando o gênero e a lista de autores convertida
        return new Book(
                dataModel.getIsbn(),
                dataModel.getTitle(),
                dataModel.getDescription(),
                null,  // O gênero será injetado posteriormente
                authors,  // Lista de autores convertida do AuthorService
                dataModel.getPhotoURI()
        );
    }

    private Author toDomainAuthor(AuthorDataModelMongo authorData) {
        return new Author(
                authorData.getAuthorNumber(),
                authorData.getName(),
                authorData.getBio(),
                authorData.getPhotoURI()
        );
    }
}
*/