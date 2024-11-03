package pt.psoft.g1.psoftg1.readermanagement.factories;

import org.springframework.stereotype.Component;
import pt.psoft.g1.psoftg1.readermanagement.model.ReaderNumber;

@Component
public class ReaderNumberFactory {
    public ReaderNumber newReaderNumber(String readerNumber)
    {
        return new ReaderNumber(readerNumber);
    }
}
