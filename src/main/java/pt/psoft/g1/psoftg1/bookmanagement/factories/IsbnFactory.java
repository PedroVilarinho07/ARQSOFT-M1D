package pt.psoft.g1.psoftg1.bookmanagement.factories;

import org.springframework.stereotype.Component;
import pt.psoft.g1.psoftg1.bookmanagement.model.Isbn;

@Component
public class IsbnFactory {

    public Isbn newIsbn(String isbn)
    {
        return new Isbn(isbn);
    }

}
