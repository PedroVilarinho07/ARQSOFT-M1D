package pt.psoft.g1.psoftg1.bookmanagement.factories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import pt.psoft.g1.psoftg1.genremanagement.model.Genre;
import pt.psoft.g1.psoftg1.shared.model.domainService.IIdGenerator;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookFactory {

    public Book newBook(String isbn, String title, String description, String genre, List<Author> authors, String photoURI){
        return new Book(isbn, title, description, new Genre(genre), authors, photoURI);
    }
}
