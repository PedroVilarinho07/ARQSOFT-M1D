package pt.psoft.g1.psoftg1.lendingmanagement.factories;

import org.springframework.stereotype.Component;
import pt.psoft.g1.psoftg1.lendingmanagement.model.LendingNumber;

@Component
public class LendingNumberFactory {
    public LendingNumber newLendingNumber(String lendingNumber)
    {
        return new LendingNumber(lendingNumber);
    }
}
