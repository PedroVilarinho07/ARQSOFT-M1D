package pt.psoft.g1.psoftg1.bookmanagement.model;

import org.junit.jupiter.api.Test;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import pt.psoft.g1.psoftg1.genremanagement.model.Genre;
import pt.psoft.g1.psoftg1.bookmanagement.dto.request.UpdateBookRequest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookTest {

    private final String validIsbn = "9783161484100";
    private final String validTitle = "A Great Book";
    private final String validDescription = "A detailed description of the book.";
    private final Genre validGenre = mock(Genre.class);
    private final List<Author> validAuthors = List.of(mock(Author.class));
    private final String genreName = "Fiction";
    private final List<String> authorNames = List.of("Author One");

    @Test
    public void ensureGenreNotNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Book(validIsbn, validTitle, validDescription, null, validAuthors, null));
        assertTrue(exception.getMessage().contains("Genre cannot be null"));
    }

    @Test
    public void ensureAuthorListNotEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Book(validIsbn, validTitle, validDescription, validGenre, Collections.emptyList(), null));
        assertTrue(exception.getMessage().contains("Author list is empty"));
    }

    @Test
    public void testBookCreationWithValidData() {
        Book book = new Book(validIsbn, validTitle, validDescription, validGenre, validAuthors, null);
        assertNotNull(book);
        assertEquals(validTitle, book.getTitle().toString());
        assertEquals(validDescription, book.getDescription());
        assertEquals(validIsbn, book.getIsbn());
        assertEquals(validGenre, book.getGenre());
        assertEquals(validAuthors, book.getAuthors());
    }

    @Test
    public void testBookCreationFailsWithNullTitle() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Book(validIsbn, null, validDescription, validGenre, validAuthors, null)
        );
        assertTrue(exception.getMessage().contains("Title cannot be null"));
    }

    @Test
    public void testApplyPatchUpdatesFields() {
        when(validGenre.toString()).thenReturn(genreName);
        when(validAuthors.get(0).toString()).thenReturn(authorNames.get(0));

        Book book = new Book(validIsbn, validTitle, validDescription, validGenre, validAuthors, null);

        UpdateBookRequest request = new UpdateBookRequest("9783161484100", "New Title", genreName, authorNames, "New Description");

        book.applyPatch(1L, request);

        assertEquals("New Title", book.getTitle().toString());
        assertEquals("New Description", book.getDescription());
        assertEquals(genreName, book.getGenre().toString());
        assertEquals(authorNames.get(0), book.getAuthors().get(0).toString());
    }

    @Test
    public void testRemovePhoto() {
        Book book = new Book(validIsbn, validTitle, validDescription, validGenre, validAuthors, "photoUri.jpg");
        assertNotNull(book.getPhoto());

        book.removePhoto(1L);

        assertNull(book.getPhoto());
    }

    @Test
    public void testApplyPatchUpdatesOnlyTitle() {
        Book book = new Book(validIsbn, validTitle, validDescription, validGenre, validAuthors, null);

        UpdateBookRequest request = new UpdateBookRequest(validIsbn, "Updated Title", null, Collections.emptyList(), null);

        book.applyPatch(1L, request);

        assertEquals("Updated Title", book.getTitle().toString());
        assertEquals(validDescription, book.getDescription());
    }

    @Test
    public void testApplyPatchWithNullGenreAndAuthors() {
        Book book = new Book(validIsbn, validTitle, validDescription, validGenre, validAuthors, null);

        UpdateBookRequest request = new UpdateBookRequest(validIsbn, "New Title", null, Collections.emptyList(), "New Description");

        book.applyPatch(1L, request);

        assertEquals("New Title", book.getTitle().toString());
        assertEquals("New Description", book.getDescription());
        assertEquals(validGenre, book.getGenre());
        assertEquals(validAuthors, book.getAuthors());
    }

    @Test
    public void testBookIsbnCannotBeUpdated() {
        Book book = new Book(validIsbn, validTitle, validDescription, validGenre, validAuthors, null);

        UpdateBookRequest request = new UpdateBookRequest("1111111111", "New Title", genreName, authorNames, "New Description");

        book.applyPatch(1L, request);

        assertEquals(validIsbn, book.getIsbn());
        assertEquals("New Title", book.getTitle().toString());
        assertEquals("New Description", book.getDescription());
    }

    @Test
    public void testGetGenreAndAuthorsReturnExpectedValues() {
        Book book = new Book(validIsbn, validTitle, validDescription, validGenre, validAuthors, null);

        assertEquals(validGenre, book.getGenre());
        assertEquals(validAuthors, book.getAuthors());
    }

    @Test
    public void testGettersReturnExpectedValues() {
        Book book = new Book(validIsbn, validTitle, validDescription, validGenre, validAuthors, null);

        assertEquals(validIsbn, book.getIsbn());
        assertEquals(validTitle, book.getTitle().toString());
        assertEquals(validDescription, book.getDescription());
    }

    @Test
    public void testDefaultValuesWhenOptionalFieldsAreNotSet() {
        Book book = new Book(validIsbn, validTitle, null, validGenre, validAuthors, null);

        assertEquals(validIsbn, book.getIsbn());
        assertEquals(validTitle, book.getTitle().toString());
        assertNull(book.getDescription());
        assertNull(book.getPhoto());
    }
}
