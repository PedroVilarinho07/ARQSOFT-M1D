package pt.psoft.g1.psoftg1.lendingmanagement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import pt.psoft.g1.psoftg1.bookmanagement.factories.IsbnFactory;
import pt.psoft.g1.psoftg1.bookmanagement.model.Isbn;
import pt.psoft.g1.psoftg1.lendingmanagement.factories.LendingNumberFactory;
import pt.psoft.g1.psoftg1.lendingmanagement.model.Lending;
import pt.psoft.g1.psoftg1.lendingmanagement.model.LendingNumber;
import pt.psoft.g1.psoftg1.readermanagement.factories.ReaderNumberFactory;
import pt.psoft.g1.psoftg1.readermanagement.model.ReaderNumber;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@PropertySource({"classpath:config/library.properties"})
public class LendingTest {

    private final String validIsbn = "isbn";
    private final String validReaderNumber = "ReaderNumber";
    private final String validLendingNumber = "LendingNumber";


    @Value("${lendingDurationInDays}")
    private int lendingDurationInDays;
    @Value("${fineValuePerDayInCents}")
    private int fineValuePerDayInCents;

    @BeforeAll
    public static void setup(){
    }

    public Lending createLending() {
        IsbnFactory doubleIsbnFactory = mock(IsbnFactory.class);
        ReaderNumberFactory doubleReaderNumberFactory = mock(ReaderNumberFactory.class);
        LendingNumberFactory doubleLendingNumberFactory = mock(LendingNumberFactory.class);

        Isbn isbnDouble = mock(Isbn.class);
        ReaderNumber readerNumberDouble = mock(ReaderNumber.class);
        LendingNumber lendingNumberDouble = mock(LendingNumber.class);

        when(doubleIsbnFactory.newIsbn(validIsbn)).thenReturn(isbnDouble);
        when(doubleReaderNumberFactory.newReaderNumber(null)).thenReturn(readerNumberDouble);
        when(doubleLendingNumberFactory.newLendingNumber(validLendingNumber)).thenReturn(lendingNumberDouble);

        return new Lending(validIsbn, validReaderNumber, validLendingNumber, lendingDurationInDays, fineValuePerDayInCents, doubleIsbnFactory, doubleReaderNumberFactory, doubleLendingNumberFactory);
    }

    @Test
    public void ensureIsbnNotNullTest(){
        //Arrange
        String expectedMessage = "Isbn cannot be null";
        IsbnFactory doubleIsbnFactory = mock(IsbnFactory.class);
        ReaderNumberFactory doubleReaderNumberFactory = mock(ReaderNumberFactory.class);
        LendingNumberFactory doubleLendingNumberFactory = mock(LendingNumberFactory.class);

        ReaderNumber readerNumberDouble = mock(ReaderNumber.class);
        LendingNumber lendingNumberDouble = mock(LendingNumber.class);

        when(doubleIsbnFactory.newIsbn(null)).thenThrow(new IllegalArgumentException(expectedMessage));
        when(doubleReaderNumberFactory.newReaderNumber(validReaderNumber)).thenReturn(readerNumberDouble);
        when(doubleLendingNumberFactory.newLendingNumber(validLendingNumber)).thenReturn(lendingNumberDouble);

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Lending(null, validReaderNumber, validLendingNumber, lendingDurationInDays, fineValuePerDayInCents, doubleIsbnFactory, doubleReaderNumberFactory, doubleLendingNumberFactory));

        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ensureLendingNumberNotNullTest(){
        //Arrange
        String expectedMessage = "Lending Number cannot be null";
        IsbnFactory doubleIsbnFactory = mock(IsbnFactory.class);
        ReaderNumberFactory doubleReaderNumberFactory = mock(ReaderNumberFactory.class);
        LendingNumberFactory doubleLendingNumberFactory = mock(LendingNumberFactory.class);

        Isbn isbnDouble = mock(Isbn.class);
        ReaderNumber readerNumberDouble = mock(ReaderNumber.class);

        when(doubleIsbnFactory.newIsbn(validIsbn)).thenReturn(isbnDouble);
        when(doubleReaderNumberFactory.newReaderNumber(validReaderNumber)).thenReturn(readerNumberDouble);
        when(doubleLendingNumberFactory.newLendingNumber(null)).thenThrow(new IllegalArgumentException(expectedMessage));

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Lending(validIsbn, validReaderNumber, null, lendingDurationInDays, fineValuePerDayInCents, doubleIsbnFactory, doubleReaderNumberFactory, doubleLendingNumberFactory));

        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ensureReaderNumberNotNullTest(){
        //Arrange
        String expectedMessage = "Reader Number cannot be null";
        IsbnFactory doubleIsbnFactory = mock(IsbnFactory.class);
        ReaderNumberFactory doubleReaderNumberFactory = mock(ReaderNumberFactory.class);
        LendingNumberFactory doubleLendingNumberFactory = mock(LendingNumberFactory.class);

        Isbn isbnDouble = mock(Isbn.class);
        LendingNumber lendingNumberDouble = mock(LendingNumber.class);

        when(doubleIsbnFactory.newIsbn(validIsbn)).thenReturn(isbnDouble);
        when(doubleReaderNumberFactory.newReaderNumber(null)).thenThrow(new IllegalArgumentException(expectedMessage));
        when(doubleLendingNumberFactory.newLendingNumber(validLendingNumber)).thenReturn(lendingNumberDouble);

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Lending(validIsbn, null, validLendingNumber, lendingDurationInDays, fineValuePerDayInCents, doubleIsbnFactory, doubleReaderNumberFactory, doubleLendingNumberFactory));

        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ensureLimitDateIsCorrectTest(){
        Lending lending = createLending();
        LocalDate expectLimitDate = lending.getStartDate().plusDays(lendingDurationInDays);
        assertEquals(lending.getLimitDate(), expectLimitDate);
    }

    @Test
    public void testSetReturnedTest(){
        String comment = "comment";
        Lending lending = createLending();
        lending.setReturned(comment);

        assertEquals(LocalDate.now(), lending.getReturnedDate());
        assertEquals(lending.getCommentary(), comment);
    }

    @Test
    public void ensureCantReturnTwoTimesTest() {
        String expectedMessage = "Book has already been returned!";
        String comment = "comment";
        Lending lending = createLending();
        lending.setReturned(comment);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> lending.setReturned(comment));

        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGetDaysDelayedTest(){
        Lending lending = createLending();
        assertEquals(0, lending.getDaysDelayed());
    }

    @Test
    public void testGetDaysUntilReturnTest(){
        Lending lending = createLending();
        assertEquals(Optional.of(lendingDurationInDays), lending.getDaysUntilReturn());
    }

    @Test
    public void testGetDaysOverDueTest(){
        Lending lending = createLending();
        assertEquals(Optional.empty(), lending.getDaysOverdue());
    }

    @Test
    public void testGetStartDateTest() {
        Lending lending = createLending();
        assertEquals(LocalDate.now(), lending.getStartDate());
    }

    @Test
    public void testGetLimitDateTest() {
        Lending lending = createLending();
        assertEquals(LocalDate.now().plusDays(lendingDurationInDays), lending.getLimitDate());
    }

    @Test
    public void testGetReturnedDateTest() {
        Lending lending = createLending();
        assertNull(lending.getReturnedDate());
    }

    @Test
    public void getDaysOverdueTransparentTest(){
        IsbnFactory doubleIsbnFactory = mock(IsbnFactory.class);
        ReaderNumberFactory doubleReaderNumberFactory = mock(ReaderNumberFactory.class);
        LendingNumberFactory doubleLendingNumberFactory = mock(LendingNumberFactory.class);

        Isbn isbnDouble = mock(Isbn.class);
        ReaderNumber readerNumberDouble = mock(ReaderNumber.class);
        LendingNumber lendingNumberDouble = mock(LendingNumber.class);

        when(doubleIsbnFactory.newIsbn(validIsbn)).thenReturn(isbnDouble);
        when(doubleReaderNumberFactory.newReaderNumber(null)).thenReturn(readerNumberDouble);
        when(doubleLendingNumberFactory.newLendingNumber(validLendingNumber)).thenReturn(lendingNumberDouble);

        Lending lendingSpy = spy(new Lending(validIsbn, validReaderNumber, validLendingNumber, lendingDurationInDays, fineValuePerDayInCents, doubleIsbnFactory, doubleReaderNumberFactory, doubleLendingNumberFactory));

        lendingSpy.getDaysOverdue();

        verify(lendingSpy, times(1)).getDaysDelayed();
    }

}


