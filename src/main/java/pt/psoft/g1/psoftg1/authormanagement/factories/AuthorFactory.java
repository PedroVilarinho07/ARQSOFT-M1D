package pt.psoft.g1.psoftg1.lendingmanagement.factories;

import lombok.RequiredArgsConstructor;
import pt.psoft.g1.psoftg1.bookmanagement.model.Isbn;
import pt.psoft.g1.psoftg1.lendingmanagement.model.Lending;
import pt.psoft.g1.psoftg1.lendingmanagement.model.LendingNumber;
import pt.psoft.g1.psoftg1.readermanagement.model.ReaderNumber;
import pt.psoft.g1.psoftg1.shared.model.domainService.IIdGenerator;

import java.time.LocalDate;


@RequiredArgsConstructor
public class LendingFactory {

    private final IIdGenerator idGeneratorService;

    public String lendingValue(String isbn, String readerNumber) {
        return isbn + readerNumber + LocalDate.now();
    }

    public Lending newLending(Isbn isbn, ReaderNumber readerNumber, int lendingDuration, int fineValuePerDayInCents){
        return new Lending(isbn, readerNumber, new LendingNumber(idGeneratorService.generateId(lendingValue(isbn.toString(), readerNumber.toString()))), lendingDuration, fineValuePerDayInCents);
    }

    public Lending newLendingBootstrap(Isbn isbn, ReaderNumber readerNumber, int lendingDuration, int fineValuePerDayInCents, LocalDate startDate, LocalDate returnedDate){
        Lending lending = newLending(isbn, readerNumber, lendingDuration, fineValuePerDayInCents);
        Lending.newBootstrappingLending(lending, startDate, returnedDate, lendingDuration);
        return lending;
    }
}
