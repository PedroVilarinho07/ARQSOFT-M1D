package pt.psoft.g1.psoftg1.lendingmanagement.model;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LendingNumberTest {

    private final String validLendingNumber = "LendingNumber";

    @Test
    public void ensureLendingNotNullNumberTest(){
        String expectedMessage = "Lending number cannot be null";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new LendingNumber(null));
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ensureLendingNumberIsCreatedTest(){
        LendingNumber ln = new LendingNumber(validLendingNumber);
        assertEquals(validLendingNumber, ln.toString());
    }


    @Test
    public void ensureLendingNumberNotNullTest(){
        assertThrows(IllegalArgumentException.class, () -> new LendingNumber(null));
    }

    //Old Tests

    @Test
    public void ensureLendingNumberIsSetWithStringTest() {
        final var ln = new LendingNumber("2024/1");
        assertEquals("2024/1", ln.toString());
    }

    @Test
    public void ensureLendingNumberIsSetWithSequentialTest() {
        final LendingNumber ln = new LendingNumber(1);
        assertNotNull(ln);
        assertEquals(LocalDate.now().getYear() + "/1", ln.toString());
    }

    @Test
    public void ensureLendingNumberIsSetWithYearAndSequentialTest() {
        final LendingNumber ln = new LendingNumber(2024,1);
        assertNotNull(ln);
    }

    @Test
    public void ensureSequentialCannotBeNegativeTest() {
        assertThrows(IllegalArgumentException.class, () -> new LendingNumber(2024,-1));
    }

    @Test
    public void ensureYearCannotBeInTheFutureTest() {
        assertThrows(IllegalArgumentException.class, () -> new LendingNumber(LocalDate.now().getYear()+1,1));
    }

}