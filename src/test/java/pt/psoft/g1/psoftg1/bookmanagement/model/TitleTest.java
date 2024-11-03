package pt.psoft.g1.psoftg1.bookmanagement.model;

import org.junit.jupiter.api.Test;
import pt.psoft.g1.psoftg1.bookmanagement.model.Title;

import static org.junit.jupiter.api.Assertions.*;

public class TitleTest {

    private final String validTitle = "A Book Title";

    @Test
    public void ensureTitleNotExceedsMaxLength() {
        String longTitle = "a".repeat(129);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Title(longTitle));
        assertTrue(exception.getMessage().contains("Title has a maximum of 128 characters"));
    }

    @Test
    public void ensureTitleCannotBeNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Title(null));
        assertTrue(exception.getMessage().contains("Title cannot be null"));
    }

    @Test
    public void ensureTitleCannotBeBlank() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Title(" "));
        assertTrue(exception.getMessage().contains("Title cannot be blank"));
    }

    @Test
    public void ensureValidTitleIsSet() {
        Title title = new Title(validTitle);
        assertEquals(validTitle, title.toString());
    }
}
