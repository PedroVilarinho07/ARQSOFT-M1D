package pt.psoft.g1.psoftg1.bookmanagement.model;

import org.junit.jupiter.api.Test;
import pt.psoft.g1.psoftg1.bookmanagement.model.Isbn;

import static org.junit.jupiter.api.Assertions.*;

public class IsbnTest {

    @Test
    public void ensureValidIsbn10Accepted() {
        Isbn isbn = new Isbn("0136091814");
        assertEquals("0136091814", isbn.toString());
    }

    @Test
    public void ensureValidIsbn13Accepted() {
        Isbn isbn = new Isbn("9783161484100");
        assertEquals("9783161484100", isbn.toString());
    }

    @Test
    public void ensureInvalidIsbnThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Isbn("1234567890"));
        assertTrue(exception.getMessage().contains("Invalid ISBN-13 format or check digit."));
    }

    @Test
    public void ensureIsbnCannotBeNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Isbn(null));
        assertTrue(exception.getMessage().contains("Isbn cannot be null"));
    }

    @Test
    public void ensureInvalidIsbnWithLettersThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Isbn("97831A148410"));
        assertTrue(exception.getMessage().contains("Invalid ISBN-13 format or check digit."));
    }

    @Test
    public void ensureIsbnWithIncorrectLengthThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Isbn("123456789"));
        assertTrue(exception.getMessage().contains("Invalid ISBN-13 format or check digit."));
    }

    @Test
    public void ensureValidIsbn10WithXAccepted() {
        Isbn isbn = new Isbn("0306406152");
        assertEquals("0306406152", isbn.toString());
    }

    @Test
    public void ensureInvalidIsbn10WithIncorrectCheckDigitThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Isbn("0306406153"));
        assertTrue(exception.getMessage().contains("Invalid ISBN-13 format or check digit."));
    }

    @Test
    public void ensureInvalidIsbn13WithIncorrectCheckDigitThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Isbn("9783161484101"));
        assertTrue(exception.getMessage().contains("Invalid ISBN-13 format or check digit."));
    }

    @Test
    public void ensureValidIsbn13WithLeadingZerosAccepted() {
        Isbn isbn = new Isbn("0000000000000");
        assertEquals("0000000000000", isbn.toString());
    }

}
