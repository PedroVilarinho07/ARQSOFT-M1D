package pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.psoftg1.lendingmanagement.factories.LendingFactory;
import pt.psoft.g1.psoftg1.lendingmanagement.model.Lending;
import pt.psoft.g1.psoftg1.lendingmanagement.repositories.LendingRepository;
import pt.psoft.g1.psoftg1.shared.services.Page;
import pt.psoft.g1.psoftg1.bookmanagement.model.Isbn;
import pt.psoft.g1.psoftg1.readermanagement.model.ReaderNumber;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Profile("mongo")
public class LendingMongoRepository implements LendingRepository {

    private final LendingFactory lendingFactory;
    private final List<Lending> staticLendings = new ArrayList<>();

    public LendingMongoRepository(LendingFactory lendingFactory) {
        this.lendingFactory = lendingFactory;
        initializeStaticData();
    }

    private void initializeStaticData() {
        // Utilizando LendingFactory para criar objetos Lending completos
        staticLendings.add(lendingFactory.newLendingBootstrap(
                "9789720706386",
                "2024/1",
                30,
                100,
                LocalDate.of(2024, 1, 1),
                null
        ));
        staticLendings.add(lendingFactory.newLendingBootstrap(
                "9789723716160",
                "2024/2",
                30,
                100,
                LocalDate.of(2024, 2, 1),
                null
        ));
        staticLendings.add(lendingFactory.newLendingBootstrap(
                "9789895612864",
                "2024/3",
                30,
                100,
                LocalDate.of(2024, 3, 1),
                null
        ));
    }

    @Override
    public Optional<Lending> findByLendingNumber(String lendingNumber) {
        return staticLendings.stream()
                .filter(lending -> lending.getLendingNumber().toString().equals(lendingNumber))
                .findFirst();
    }

    @Override
    public List<Lending> listByReaderNumberAndIsbn(String readerNumber, String isbn) {
        return staticLendings.stream()
                .filter(lending -> lending.getReaderNumber().toString().equals(readerNumber) &&
                        lending.getIsbn().toString().equals(isbn))
                .collect(Collectors.toList());
    }

    @Override
    public int getCountFromCurrentYear() {
        int currentYear = LocalDate.now().getYear();
        return (int) staticLendings.stream()
                .filter(lending -> lending.getStartDate().getYear() == currentYear)
                .count();
    }

    @Override
    public List<Lending> listOutstandingByReaderNumber(String readerNumber) {
        return staticLendings.stream()
                .filter(lending -> lending.getReaderNumber().toString().equals(readerNumber) &&
                        lending.getReturnedDate() == null)
                .collect(Collectors.toList());
    }

    @Override
    public Double getAverageDuration() {
        return staticLendings.stream()
                .filter(lending -> lending.getReturnedDate() != null)
                .mapToDouble(lending -> lending.getStartDate().until(lending.getReturnedDate()).getDays())
                .average()
                .orElse(0.0);
    }

    @Override
    public Double getAvgLendingDurationByIsbn(String isbn) {
        return staticLendings.stream()
                .filter(lending -> lending.getIsbn().toString().equals(isbn) && lending.getReturnedDate() != null)
                .mapToDouble(lending -> lending.getStartDate().until(lending.getReturnedDate()).getDays())
                .average()
                .orElse(0.0);
    }

    @Override
    public List<Lending> getOverdue(Page page) {
        LocalDate now = LocalDate.now();
        return staticLendings.stream()
                .filter(lending -> lending.getReturnedDate() == null && lending.getLimitDate().isBefore(now))
                .skip((long) (page.getNumber() - 1) * page.getLimit())
                .limit(page.getLimit())
                .collect(Collectors.toList());
    }

    @Override
    public List<Lending> searchLendings(Page page, String readerNumber, String isbn, Boolean returned, LocalDate startDate, LocalDate endDate) {
        return staticLendings.stream()
                .filter(lending -> (readerNumber == null || lending.getReaderNumber().toString().equals(readerNumber)) &&
                        (isbn == null || lending.getIsbn().toString().equals(isbn)) &&
                        (returned == null || (returned ? lending.getReturnedDate() != null : lending.getReturnedDate() == null)) &&
                        (startDate == null || !lending.getStartDate().isBefore(startDate)) &&
                        (endDate == null || !lending.getStartDate().isAfter(endDate)))
                .skip((long) (page.getNumber() - 1) * page.getLimit())
                .limit(page.getLimit())
                .collect(Collectors.toList());
    }

    @Override
    public Lending save(Lending lending) {
        staticLendings.add(lending);
        return lending;
    }

    @Override
    public void delete(Lending lending) {
        staticLendings.removeIf(existingLending -> existingLending.getLendingNumber().toString().equals(lending.getLendingNumber().toString()));
    }
}
