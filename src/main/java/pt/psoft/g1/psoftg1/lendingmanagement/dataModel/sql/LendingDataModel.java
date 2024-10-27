package pt.psoft.g1.psoftg1.lendingmanagement.dataModel.sql;

import jakarta.persistence.*;
        import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "LENDING", uniqueConstraints = {
        @UniqueConstraint(name = "uc_lending_number", columnNames = {"LENDING_NUMBER"})
})
public class LendingDataModel {

    @Id
    @Column(name = "LENDING_ID")
    private Long pk;

    @Column(name = "LENDING_NUMBER", length = 32, nullable = false, unique = true)
    @Size(min = 6, max = 32)
    private String lendingNumber;

    @Column(name = "ISBN", nullable = false, length = 13)
    @NotNull
    private String isbn;

    @Column(name = "READER_NUMBER", nullable = false, length = 32)
    @NotNull
    private String readerNumber;

    @NotNull
    @Column(name = "START_DATE", nullable = false, updatable = false)
    private LocalDate startDate;

    @NotNull
    @Column(name = "LIMIT_DATE", nullable = false)
    private LocalDate limitDate;

    @Column(name = "RETURNED_DATE")
    private LocalDate returnedDate;

    @Column(name = "COMMENTARY", length = 1024)
    @Size(max = 1024)
    private String commentary;

    @Column(name = "FINE_VALUE_PER_DAY", nullable = false)
    private int fineValuePerDayInCents;

    public LendingDataModel() {}

    public LendingDataModel(String lendingNumber, String isbn, String readerNumber, LocalDate startDate, LocalDate limitDate, Integer fineValuePerDayInCents) {
        this.lendingNumber = lendingNumber;
        this.isbn = isbn;
        this.readerNumber = readerNumber;
        this.startDate = startDate;
        this.limitDate = limitDate;
        this.fineValuePerDayInCents = fineValuePerDayInCents;
    }
}

