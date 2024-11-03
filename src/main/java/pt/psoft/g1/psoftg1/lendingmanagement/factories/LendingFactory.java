package pt.psoft.g1.psoftg1.lendingmanagement.factories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pt.psoft.g1.psoftg1.bookmanagement.factories.IsbnFactory;
import pt.psoft.g1.psoftg1.lendingmanagement.model.Lending;
import pt.psoft.g1.psoftg1.readermanagement.factories.ReaderNumberFactory;
import pt.psoft.g1.psoftg1.shared.model.domainService.IIdGenerator;

import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class LendingFactory {

    private final IIdGenerator idGeneratorService;
    private final LendingNumberFactory lendingNumberFactory;
    private final IsbnFactory isbnFactory;
    private final ReaderNumberFactory readerNumberFactory;

    public String lendingValue(String isbn, String readerNumber) {
        return isbn + readerNumber + LocalDate.now();
    }

    public Lending newLending(String isbn, String readerNumber, int lendingDuration, int fineValuePerDayInCents){
        return new Lending(isbn, readerNumber, idGeneratorService.generateId(lendingValue(isbn, readerNumber)), lendingDuration, fineValuePerDayInCents, isbnFactory, readerNumberFactory, lendingNumberFactory);
    }

    public Lending newLendingBootstrap(String isbn, String readerNumber, int lendingDuration, int fineValuePerDayInCents, LocalDate startDate, LocalDate returnedDate){
        Lending lending = newLending(isbn, readerNumber, lendingDuration, fineValuePerDayInCents);
        Lending.newBootstrappingLending(lending, startDate, returnedDate, lendingDuration);
        return lending;
    }
}
