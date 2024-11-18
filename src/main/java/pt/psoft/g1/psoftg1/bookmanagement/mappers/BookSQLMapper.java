package pt.psoft.g1.psoftg1.bookmanagement.mappers;

import pt.psoft.g1.psoftg1.authormanagement.dataModel.sql.AuthorDataModel;
import pt.psoft.g1.psoftg1.authormanagement.factories.AuthorFactory;
import pt.psoft.g1.psoftg1.authormanagement.mappers.AuthorSQLMapper;
import pt.psoft.g1.psoftg1.bookmanagement.dataModel.sql.BookDataModel;
import pt.psoft.g1.psoftg1.bookmanagement.factories.BookFactory;
import pt.psoft.g1.psoftg1.bookmanagement.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookSQLMapper {

    public static BookDataModel convertToDataModelBook(Book book) {
        if (book == null) {
            return null;
        }

        List<AuthorDataModel> authorDataModels = book.getAuthors().stream()
                .map(author -> new AuthorDataModel(
                        author.getAuthorNumber().toString(),
                        author.getName(),
                        author.getBio(),
                        author.getPhoto() != null ? author.getPhoto().getPhotoFile() : null ))
                .collect(Collectors.toList());

        BookDataModel bookDataModel = new BookDataModel();
        bookDataModel.setIsbn(book.getIsbn());
        bookDataModel.setTitle(book.getTitle());
        bookDataModel.setDescription(book.getDescription());
        bookDataModel.setGenre(book.getGenre().getGenre());
        bookDataModel.setAuthors(authorDataModels);
        bookDataModel.setPhotoURI(book.getPhoto() != null ? book.getPhoto().getPhotoFile() : null);

        return bookDataModel;
    }

    public static Book convertToDomainBook(BookFactory bookFactory, AuthorFactory authorFactory, BookDataModel dataModel) {
        return bookFactory.newBook(
                dataModel.getIsbn(),
                dataModel.getTitle(),
                dataModel.getDescription(),
                dataModel.getGenre(),
                AuthorSQLMapper.convertListToDomainAuthor(authorFactory, dataModel.getAuthors()),
                dataModel.getPhotoURI()
        );
    }

    public static List<Book> convertListToDomainAuthor(BookFactory bookFactory, AuthorFactory authorFactory, List<BookDataModel> books) {
        List<Book> newBooks = new ArrayList<>();
        for (BookDataModel bookDataModel : books) {
            Book book = convertToDomainBook(bookFactory, authorFactory, bookDataModel);
            newBooks.add(book);
        }
        return newBooks;
    }

}
