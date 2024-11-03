package pt.psoft.g1.psoftg1.authormanagement.tests.model;

import org.junit.jupiter.api.Test;
import pt.psoft.g1.psoftg1.authormanagement.model.AuthorNumber;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorNumberTest {

    private final String validAuthorNumber = "authorNumber";

    @Test
    public void ensureAuthorNumberMustNotBeNullTest() {
        String expectedMessage = "Author Number cannot be null";
        Exception exception = assertThrows(IllegalArgumentException.class, () ->new AuthorNumber(null));
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ensureBioMustNotBeBlankTest() {
        String expectedMessage = "Author Number cannot be blank";
        Exception exception = assertThrows(IllegalArgumentException.class, () ->new AuthorNumber(""));
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ensureBioIsSetTest() {
        final var authorNumber = new AuthorNumber(validAuthorNumber);
        assertEquals(validAuthorNumber, authorNumber.toString());
    }

    @Test
    void testProtectedConstructorWithReflection() throws Exception {
        Constructor<AuthorNumber> constructor = AuthorNumber.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        AuthorNumber authorNumber = constructor.newInstance();
        assertNotNull(authorNumber);
    }
}
