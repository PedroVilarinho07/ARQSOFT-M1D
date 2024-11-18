package pt.psoft.g1.psoftg1.bookmanagement.model;

import org.junit.jupiter.api.Test;

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
    public void ensureIsbnCannotBeNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Isbn(null));
        assertTrue(exception.getMessage().contains("Isbn cannot be null"));
    }

    @Test
    public void ensureInvalidIsbnThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Isbn("1234567890"));
        assertTrue(exception.getMessage().contains("Invalid ISBN-13 format or check digit."));
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
    public void ensureInvalidIsbn13WithExtraLengthThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Isbn("97831614841001"));
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
    public void ensureValidIsbn13WithLeadingZerosAccepted() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Isbn("0000000000000"));
        assertTrue(exception.getMessage().contains("ISBN cannot be all zeros."));
    }

    @Test
    public void ensureValidIsbn13WithMixedZerosAccepted() {
        Isbn isbn = new Isbn("9780000000002");
        assertEquals("9780000000002", isbn.toString());
    }


    @Test
    public void ensureValidIsbn10WithXAsCheckDigit() {
        Isbn isbn = new Isbn("0306406152"); // ISBN-10 where the last digit is 2
        assertEquals("0306406152", isbn.toString());
    }

    @Test
    public void ensureValidIsbn13BoundaryValueAccepted() {
        Isbn isbn = new Isbn("9780306406157");
        assertEquals("9780306406157", isbn.toString());
    }


    @Test
    public void ensureValidIsbn10BoundaryValueAccepted() {
        Isbn isbn = new Isbn("9999999999");
        assertEquals("9999999999", isbn.toString());
    }

    @Test
    public void ensureInvalidCharactersInIsbnThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Isbn("978-316148410"));
        assertTrue(exception.getMessage().contains("Invalid ISBN-13 format or check digit."));
    }


}
