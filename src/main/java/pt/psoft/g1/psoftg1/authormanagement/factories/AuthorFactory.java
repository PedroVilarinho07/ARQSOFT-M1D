package pt.psoft.g1.psoftg1.authormanagement.factories;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import pt.psoft.g1.psoftg1.bookmanagement.model.Isbn;
import pt.psoft.g1.psoftg1.lendingmanagement.model.Lending;
import pt.psoft.g1.psoftg1.lendingmanagement.model.LendingNumber;
import pt.psoft.g1.psoftg1.readermanagement.model.ReaderNumber;
import pt.psoft.g1.psoftg1.shared.model.domainService.IIdGenerator;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class AuthorFactory {

    private final IIdGenerator idGeneratorService;

    public Author newAuthor(String name, String bio, String photoURI){
        return new Author(idGeneratorService.generateId(name), name, bio, photoURI);
    }
}
