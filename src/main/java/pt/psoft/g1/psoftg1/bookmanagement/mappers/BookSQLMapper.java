package pt.psoft.g1.psoftg1.bookmanagement.mappers;

import pt.psoft.g1.psoftg1.authormanagement.dataModel.sql.AuthorDataModel;
import pt.psoft.g1.psoftg1.authormanagement.factories.AuthorFactory;
import pt.psoft.g1.psoftg1.authormanagement.mappers.AuthorSQLMapper;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import pt.psoft.g1.psoftg1.bookmanagement.dataModel.sql.BookDataModel;
import pt.psoft.g1.psoftg1.bookmanagement.factories.BookFactory;
import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import pt.psoft.g1.psoftg1.genremanagement.model.Genre;

import java.util.ArrayList;
import java.util.List;

public class BookSQLMapper {

    public static Book convertToDomainBook(BookFactory bookFactory, AuthorFactory authorFactory, BookDataModel dataModel) {
        Book book = bookFactory.newBook(
                dataModel.getIsbn(),
                dataModel.getTitle(),
                dataModel.getDescription(),
                dataModel.getGenre(),
                AuthorSQLMapper.convertListToDomainAuthor(authorFactory, dataModel.getAuthors()),
                dataModel.getPhotoURI()
        );
        return book;
    }

    public static List<Book> convertListToDomainAuthor(BookFactory bookFactory, AuthorFactory authorFactory, List<BookDataModel> books) {
        List<Book> newBooks = new ArrayList<>();
        for (BookDataModel bookDataModel : books) {
            Book book = convertToDomainBook(bookFactory, authorFactory, bookDataModel);  // Assuming newAuthor method accepts AuthorDataModel and returns Author
            newBooks.add(book);
        }
        return newBooks;
    }

}
