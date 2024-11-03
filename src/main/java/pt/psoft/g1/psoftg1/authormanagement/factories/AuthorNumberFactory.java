package pt.psoft.g1.psoftg1.authormanagement.factories;

import org.springframework.stereotype.Component;
import pt.psoft.g1.psoftg1.authormanagement.model.AuthorNumber;

@Component
public class AuthorNumberFactory {

    public AuthorNumber newAuthorNumber(String authorNumber)
    {
        return new AuthorNumber(authorNumber);
    }
}
