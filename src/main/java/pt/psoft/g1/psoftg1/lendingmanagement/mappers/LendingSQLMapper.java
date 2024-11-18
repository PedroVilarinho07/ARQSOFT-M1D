package pt.psoft.g1.psoftg1.lendingmanagement.mappers;

import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import pt.psoft.g1.psoftg1.lendingmanagement.dataModel.sql.LendingDataModel;
import pt.psoft.g1.psoftg1.lendingmanagement.factories.LendingFactory;
import pt.psoft.g1.psoftg1.lendingmanagement.model.Lending;

import java.time.temporal.ChronoUnit;

@AllArgsConstructor
@Component
public class LendingSQLMapper {

    private LendingFactory lendingFactory;

    public LendingDataModel convertToDataModelLending(Lending lending) {
        if (lending == null) return null;
        LendingDataModel dataModel = new LendingDataModel();
        dataModel.setLendingNumber(lending.getLendingNumber());
        dataModel.setIsbn(lending.getIsbn().toString());
        dataModel.setReaderNumber(lending.getReaderNumber().toString());
        dataModel.setStartDate(lending.getStartDate());
        dataModel.setLimitDate(lending.getLimitDate());
        dataModel.setReturnedDate(lending.getReturnedDate());
        dataModel.setCommentary(lending.getCommentary());
        dataModel.setFineValuePerDayInCents(lending.getFineValuePerDayInCents());

        return dataModel;
    }

    public Lending convertToDomainLending(LendingDataModel dataModel) {
        if (dataModel == null) return null;
        return lendingFactory.newLendingWithLendingNumber(dataModel.getLendingNumber(), dataModel.getIsbn(), dataModel.getReaderNumber(), (int) ChronoUnit.DAYS.between(dataModel.getStartDate(), dataModel.getLimitDate()), dataModel.getFineValuePerDayInCents(), dataModel.getStartDate(), dataModel.getReturnedDate());
    }
}
