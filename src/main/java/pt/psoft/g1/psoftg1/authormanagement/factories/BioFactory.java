package pt.psoft.g1.psoftg1.authormanagement.factories;

import org.springframework.stereotype.Component;
import pt.psoft.g1.psoftg1.authormanagement.model.Bio;

@Component
public class BioFactory {
    public Bio newBio(String bio)
    {
        return new Bio(bio);
    }
}
