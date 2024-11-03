package pt.psoft.g1.psoftg1.bookmanagement.model;

import org.junit.jupiter.api.Test;
import pt.psoft.g1.psoftg1.bookmanagement.model.Description;

import static org.junit.jupiter.api.Assertions.*;

public class DescriptionTest {

    private final String validDescription = "This is a description";

    @Test
    public void ensureDescriptionNotExceedsMaxLength() {
        String longDescription = "a".repeat(4097);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Description(longDescription));
        assertTrue(exception.getMessage().contains("Description has a maximum of 4096 characters"));
    }

    @Test
    public void ensureDescriptionCanBeNullOrBlank() {
        Description description = new Description(null);
        assertNull(description.toString());

        description = new Description("");
        assertNull(description.toString());
    }

    @Test
    public void ensureValidDescriptionIsSet() {
        Description description = new Description(validDescription);
        assertEquals(validDescription, description.toString());
    }

    @Test
    public void ensureDescriptionTrimsWhitespace() {
        Description description = new Description("   " + validDescription + "   ");
        assertEquals(validDescription, description.toString()); // Deve remover espaços em branco
    }
/*
    @Test
    public void ensureDescriptionHandlesSpecialCharacters() {
        String specialCharactersDescription = "This description includes special characters: !@#$%^&*()";
        String expectedSanitizedDescription = "This description includes special characters: !&#64;#$%^&amp;*()";
        Description description = new Description(specialCharactersDescription);
        assertEquals(expectedSanitizedDescription, description.toString()); // Espera a versão sanitizada
    }
*/

    /*
    @Test
    public void ensureDescriptionSanitizesHtml() {
        String htmlDescription = "<script>alert('Hello');</script>This is a safe description.";
        Description description = new Description(htmlDescription);
        assertEquals("This is a safe description.", description.toString());
    }
*/
    @Test
    public void ensureDescriptionAllowsExactMaxLength() {
        String exactMaxLengthDescription = "a".repeat(4096); // Descrição com exatamente 4096 caracteres
        Description description = new Description(exactMaxLengthDescription);
        assertEquals(exactMaxLengthDescription, description.toString()); // Deve permitir a descrição de comprimento máximo
    }

    @Test
    public void ensureDescriptionRejectsExcessWhitespaceOnly() {
        // Descrição com apenas espaços em branco, deve ser tratada como nula
        Description description = new Description("     ");
        assertNull(description.toString());
    }

}
