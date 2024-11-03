package pt.psoft.g1.psoftg1.shared.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NameTest {

    private final String validName = "Some name";

    @Test
    public void ensureNameMustNotBeNullTest() {
        String expectedMessage = "Name cannot be null";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Name(null));
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ensureNameMustNotBeBlankTest() {
        String expectedMessage = "Name cannot be blank";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Name(""));
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ensureNameMustOnlyBeAlphanumericTest() {
        String expectedMessage = "Name can only contain alphanumeric characters";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Name("Ricardo!"));
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ensureNameIsSetTest() {
        final var name = new Name(validName);
        assertEquals(validName, name.toString());
    }

    @Test
    public void ensureNameIsChangedTest() {
        String otherName = "Some other name";
        final var name = new Name(validName);
        name.setName(otherName);
        assertEquals(otherName, name.toString());
    }

    @Test
    public void ensureNameIsNotChangedTest() {
        String otherName = "";
        String expectedMessage = "Name cannot be blank";
        Name name = new Name(validName);
        Exception exception = assertThrows(IllegalArgumentException.class, () ->name.setName(otherName));
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}