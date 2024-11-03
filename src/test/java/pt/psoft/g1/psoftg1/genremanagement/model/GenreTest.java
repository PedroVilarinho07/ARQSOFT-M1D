package pt.psoft.g1.psoftg1.genremanagement.model;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class GenreTest {

    @Test
    public void testGenreCreationWithValidName() {
        Genre genre = new Genre("Science Fiction");
        assertEquals("Science Fiction", genre.toString());
    }

    @Test
    public void testGenreCannotBeNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Genre(null));
        assertTrue(exception.getMessage().contains("Genre cannot be null"));
    }

    @Test
    public void testGenreCannotBeBlank() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Genre(""));
        assertTrue(exception.getMessage().contains("Genre cannot be blank"));
    }


    @Test
    public void testGenreToStringMethod() {
        Genre genre = new Genre("Fantasy");
        assertEquals("Fantasy", genre.toString());
    }

    @Test
    public void testGenreToStringWithDifferentCases() {
        Genre genre = new Genre("Drama");
        assertEquals("Drama", genre.toString());
        assertNotEquals("drama", genre.toString()); // Testing case sensitivity
    }

    @Test
    public void testGenreCreationWithTrailingWhitespace() {
        Genre genre = new Genre("  Mystery  ");
        assertEquals("  Mystery  ", genre.toString()); // Whitespace should be preserved
    }

    @Test
    public void testGenreTrimmedComparisonIgnoresWhitespace() {
        Genre genre = new Genre("Mystery");
        assertEquals("Mystery", genre.toString().trim()); // Ensure whitespace doesn't affect content
    }

    @Test
    public void testGenreToStringWithMixedCasing() {
        Genre genre = new Genre("ThRiLlEr");
        assertEquals("ThRiLlEr", genre.toString());
    }

    @Test
    public void testGenreEqualityWithSameGenre() {
        Genre genre1 = new Genre("Adventure");
        Genre genre2 = new Genre("Adventure");
        assertEquals(genre1.toString(), genre2.toString());
    }

    @Test
    public void testGenreNonEqualityWithDifferentGenre() {
        Genre genre1 = new Genre("Adventure");
        Genre genre2 = new Genre("Action");
        assertNotEquals(genre1.toString(), genre2.toString());
    }

    @Test
    public void testGenreWithMaximumAllowedLength() {
        String maxGenreName = "A".repeat(100); // Exactly 100 characters
        Genre genre = new Genre(maxGenreName);
        assertEquals(maxGenreName, genre.toString());
    }

    @Test
    public void testGenreUppercaseToString() {
        Genre genre = new Genre("history");
        assertEquals("HISTORY", genre.toString().toUpperCase(Locale.ROOT));
    }

    @Test
    public void testGenreLowercaseToString() {
        Genre genre = new Genre("DRAMA");
        assertEquals("drama", genre.toString().toLowerCase(Locale.ROOT));
    }

    @Test
    public void testGenreHandlesEdgeCaseCharacters() {
        Genre genre = new Genre("Sci-Fi & Fantasy");
        assertEquals("Sci-Fi & Fantasy", genre.toString());
    }

    @Test
    public void testGenreHashCodeConsistency() {
        Genre genre1 = new Genre("Adventure");
        Genre genre2 = new Genre("Adventure");
        assertEquals(genre1.hashCode(), genre2.hashCode());
    }


    @Test
    public void testGenreEqualityWithSameObject() {
        Genre genre = new Genre("Comedy");
        assertEquals(genre, genre); // Testing equality with itself
    }

    @Test
    public void testGenreInequalityWithNull() {
        Genre genre = new Genre("Drama");
        assertNotEquals(null, genre);
    }

    @Test
    public void testGenreInequalityWithDifferentClass() {
        Genre genre = new Genre("Adventure");
        String differentClassObject = "NotAGenre";
        assertNotEquals(differentClassObject, genre);
    }
}
