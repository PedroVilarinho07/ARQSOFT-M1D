package pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.psoftg1.authormanagement.factories.AuthorFactory;
import pt.psoft.g1.psoftg1.bookmanagement.dto.request.BookCountDTO;
import pt.psoft.g1.psoftg1.bookmanagement.dto.request.SearchBooksQuery;
import pt.psoft.g1.psoftg1.bookmanagement.factories.BookFactory;
import pt.psoft.g1.psoftg1.bookmanagement.mappers.BookSQLMapper;
import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import pt.psoft.g1.psoftg1.bookmanagement.repositories.BookRepository;
import pt.psoft.g1.psoftg1.bookmanagement.dataModel.sql.BookDataModel;
import pt.psoft.g1.psoftg1.bootstrapping.Entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Profile("sql")
@RequiredArgsConstructor
public class BookSQLRepo implements BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final AuthorFactory authorFactory;

    private final BookFactory bookFactory;

    @Override
    public List<Book> findByGenre(String genre) {
        return findXBooksByGenre(genre, null);
    }

    @Override
    public List<Book> findXBooksByGenre(String genre, Integer numberOfBooks) {
        if (numberOfBooks != null) {
            return Entities.getBooks().stream()
                    .filter(book -> book.getGenre().toString().equalsIgnoreCase(genre))
                    .limit(numberOfBooks)
                    .collect(Collectors.toList());
        } else {
            TypedQuery<BookDataModel> query = entityManager.createQuery(
                    "SELECT b FROM BookDataModel b WHERE b.genre = :genre", BookDataModel.class);
            query.setParameter("genre", genre);

            List<BookDataModel> bookDataModels = query.getResultList();
            return BookSQLMapper.convertListToDomainAuthor(bookFactory, authorFactory, bookDataModels);
        }
    }

    @Override
    public List<Book> findByTitle(String title) {
        return List.of();
    }

    @Override
    public List<Book> findByAuthorName(String authorName) {
        return List.of();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.empty();
    }

    @Override
    public Page<BookCountDTO> findTop5BooksLent(LocalDate oneYearAgo, Pageable pageable) {
        return null;
    }

    @Override
    public List<Book> findBooksByAuthorNumber(String authorNumber) {
        return List.of();
    }

    @Override
    public List<Book> searchBooks(pt.psoft.g1.psoftg1.shared.services.Page page, SearchBooksQuery query) {
        return List.of();
    }

    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public void delete(Book book) {

    }
/*
    @Override
    public List<Book> findByTitle(String title) {
        TypedQuery<BookDataModel> query = entityManager.createQuery(
                "SELECT b FROM BookDataModel b WHERE b.title = :title", BookDataModel.class);
        query.setParameter("title", title);
        List<BookDataModel> bookDataModels = query.getResultList();
        return BookSQLMapper.convertListToDomainAuthor(bookFactory, authorFactory, bookDataModels);
    }

    @Override
    public List<Book> findByAuthorName(String authorName) {
        TypedQuery<BookDataModel> query = entityManager.createQuery(
                "SELECT b FROM BookDataModel b JOIN b.authors a WHERE a.name = :authorName", BookDataModel.class);
        query.setParameter("authorName", authorName);
        List<BookDataModel> bookDataModels = query.getResultList();
        return BookSQLMapper.convertListToDomainAuthor(bookFactory, authorFactory, bookDataModels);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        TypedQuery<BookDataModel> query = entityManager.createQuery(
                "SELECT b FROM BookDataModel b WHERE b.isbn = :isbn", BookDataModel.class);
        query.setParameter("isbn", isbn);
        BookDataModel bookDataModel = query.getResultList().getFirst();
        return Optional.ofNullable(BookSQLMapper.convertToDomainBook(bookFactory, authorFactory, bookDataModel));
    }

    @Override
    public Page<BookCountDTO> findTop5BooksLent(LocalDate oneYearAgo, Pageable pageable) {
        // Assuming a query exists to calculate top books lent based on count
        TypedQuery<BookDataModel> query = entityManager.createQuery(
                "SELECT new pt.psoft.g1.psoftg1.bookmanagement.dto.request.BookCountDTO(b.isbn, COUNT(l)) " +
                        "FROM BookDataModel b JOIN LendingDataModel l ON l.isbn = b.isbn " +
                        "WHERE l.startDate >= :oneYearAgo GROUP BY b.isbn ORDER BY COUNT(l) DESC",
                BookDataModel.class);
        query.setParameter("oneYearAgo", oneYearAgo);
        query.setMaxResults(5);
        List<BookDataModel> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, resultList.size());
    }

    @Override
    public List<Book> findBooksByAuthorNumber(String authorNumber) {
        TypedQuery<BookDataModel> query = entityManager.createQuery(
                "SELECT b FROM BookDataModel b JOIN b.authors a WHERE a.authorNumber = :authorNumber", BookDataModel.class);
        query.setParameter("authorNumber", authorNumber);
        return query.getResultList().stream()
                .map(this::toBook)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> searchBooks(pt.psoft.g1.psoftg1.shared.services.Page page, SearchBooksQuery query) {
        String jpql = "SELECT b FROM BookDataModel b WHERE 1=1";
        if (query.getTitle() != null) jpql += " AND b.title LIKE :title";
        if (query.getGenre() != null) jpql += " AND b.genreId = :genreId";
        if (query.getAuthorName() != null) jpql += " AND EXISTS (SELECT a FROM b.authors a WHERE a.name = :authorName)";

        TypedQuery<BookDataModel> q = entityManager.createQuery(jpql, BookDataModel.class);
        if (query.getTitle() != null) q.setParameter("title", "%" + query.getTitle() + "%");
        if (query.getGenre() != null) q.setParameter("genreId", query.getGenre());
        if (query.getAuthorName() != null) q.setParameter("authorName", query.getAuthorName());

        q.setFirstResult(page.getOffset());
        q.setMaxResults(page.getSize());

        return q.getResultList().stream()
                .map(this::toBook)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Book save(Book book) {
        BookDataModel dataModel = toDataModel(book);
        entityManager.persist(dataModel);
        entityManager.flush();
        return toBook(dataModel);
    }

    @Override
    @Transactional
    public void delete(Book book) {
        BookDataModel dataModel = entityManager.find(BookDataModel.class, book.getIsbn());
        if (dataModel != null) {
            entityManager.remove(dataModel);
        }
    }

    // Helper methods for mapping between Book and BookDataModel
    private Book toBook(BookDataModel dataModel) {
        // Transform BookDataModel into Book
        return new Book(dataModel.getIsbn(), dataModel.getTitle(), dataModel.getDescription(),
                new Genre(dataModel.getGenreId()),
                dataModel.getAuthors().stream().map(this::toAuthor).collect(Collectors.toList()),
                dataModel.getPhotoURI());
    }

    private BookDataModel toDataModel(Book book) {
        // Transform Book into BookDataModel
        List<AuthorDataModel> authorDataModels = book.getAuthors().stream()
                .map(this::toAuthorDataModel)
                .collect(Collectors.toList());
        return new BookDataModel(book.getIsbn(), book.getTitle().toString(), book.getDescription(),
                book.getGenre().getId(), authorDataModels,
                book.getPhotoURI());
    }

    private AuthorDataModel toAuthorDataModel(Author author) {
        // Convert Author to AuthorDataModel as needed
        // Assume AuthorDataModel has appropriate fields
        return new AuthorDataModel(author.getAuthorNumber(), author.getName(), author.getBio(), author.getPhotoURI());
    }

    private Author toAuthor(AuthorDataModel dataModel) {
        // Convert AuthorDataModel back to Author
        return new Author(dataModel.getAuthorNumber(), dataModel.getName(), dataModel.getBio(), dataModel.getPhotoURI());
    }

 */
}
