package pt.psoft.g1.psoftg1.lendingmanagement.factories;

import lombok.RequiredArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pt.psoft.g1.psoftg1.bookmanagement.model.Isbn;
import pt.psoft.g1.psoftg1.lendingmanagement.model.Lending;
import pt.psoft.g1.psoftg1.lendingmanagement.model.LendingNumber;
import pt.psoft.g1.psoftg1.readermanagement.model.ReaderNumber;
import pt.psoft.g1.psoftg1.shared.model.domainService.IIdGenerator;

import java.time.LocalDate;


@RequiredArgsConstructor
@Component
public class LendingFactory {

    private final IIdGenerator idGeneratorService;

    public String lendingValue(String isbn, String readerNumber) {
        return isbn + readerNumber + LocalDate.now();
    }

    public Lending newLending(String isbn, String readerNumber, int lendingDuration, int fineValuePerDayInCents){
        return new Lending(new Isbn(isbn), new ReaderNumber(readerNumber), new LendingNumber(idGeneratorService.generateId(lendingValue(isbn, readerNumber))), lendingDuration, fineValuePerDayInCents);
    }

    public Lending newLendingBootstrap(String isbn, String readerNumber, int lendingDuration, int fineValuePerDayInCents, LocalDate startDate, LocalDate returnedDate){
        Lending lending = newLending(isbn, readerNumber, lendingDuration, fineValuePerDayInCents);
        Lending.newBootstrappingLending(lending, startDate, returnedDate, lendingDuration);
        return lending;
    }
}
