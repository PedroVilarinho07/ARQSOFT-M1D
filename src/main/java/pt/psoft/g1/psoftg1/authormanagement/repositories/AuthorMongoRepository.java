package pt.psoft.g1.psoftg1.authormanagement.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.psoftg1.authormanagement.factories.AuthorFactory;
import pt.psoft.g1.psoftg1.authormanagement.factories.AuthorNumberFactory;
import pt.psoft.g1.psoftg1.authormanagement.factories.BioFactory;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import pt.psoft.g1.psoftg1.authormanagement.dto.response.AuthorLendingResponseDTO;
import pt.psoft.g1.psoftg1.shared.factories.NameFactory;
import pt.psoft.g1.psoftg1.shared.factories.PhotoFactory;

import java.util.*;
import java.util.stream.Collectors;
@Repository
@Profile("mongo")
public class AuthorMongoRepository implements AuthorRepository {

    private final AuthorFactory authorFactory;
    private final List<Author> staticAuthors = new ArrayList<>();


    public AuthorMongoRepository(AuthorFactory authorFactory) {

        this.authorFactory = authorFactory;

        AuthorNumberFactory authorNumberFactory = new AuthorNumberFactory();
        NameFactory nameFactory = new NameFactory();
        BioFactory bioFactory = new BioFactory();
        PhotoFactory photoFactory = new PhotoFactory();


        staticAuthors.add(new Author("1", "Manuel Antonio Pina",
                "Manuel António Pina foi um jornalista e escritor português, premiado em 2011 com o Prémio Camões",
                null, authorNumberFactory, nameFactory, bioFactory, photoFactory));

        staticAuthors.add(new Author("2", "Antoine de Saint Exupéry",
                "Antoine de Saint-Exupéry nasceu a 29 de junho de 1900 em Lyon...", null,
                authorNumberFactory, nameFactory, bioFactory, photoFactory));

        staticAuthors.add(new Author("3", "Alexandre Pereira",
                "Alexandre Pereira é licenciado e mestre em Engenharia Electrotécnica e de Computadores...", null,
                authorNumberFactory, nameFactory, bioFactory, photoFactory));

        staticAuthors.add(new Author("4", "Filipe Portela",
                "Docente convidado na Escola de Engenharia da Universidade do Minho...", null,
                authorNumberFactory, nameFactory, bioFactory, photoFactory));

        staticAuthors.add(new Author("5", "Ricardo Queirós",
                "Docente na Escola Superior de Media Artes e Design do Politécnico do Porto...", null,
                authorNumberFactory, nameFactory, bioFactory, photoFactory));

        staticAuthors.add(new Author("6", "Freida Mcfadden",
                "Freida McFadden é médica e especialista em lesões cerebrais. Autora de diversos thrillers psicológicos...", null,
                authorNumberFactory, nameFactory, bioFactory, photoFactory));

        staticAuthors.add(new Author("7", "J R R Tolkien",
                "J.R.R. Tolkien nasceu a 3 de Janeiro de 1892, em Bloemfontein...", "authorPhotoTest.jpg",
                authorNumberFactory, nameFactory, bioFactory, photoFactory));

        staticAuthors.add(new Author("8", "Gardner Dozois",
                "Gardner Raymond Dozois foi um autor de ficção científica norte-americano...", null,
                authorNumberFactory, nameFactory, bioFactory, photoFactory));

        staticAuthors.add(new Author("9", "Lisa Tuttle",
                "Lisa Gracia Tuttle é uma autora americana de ficção científica, fantasia e terror...", null,
                authorNumberFactory, nameFactory, bioFactory, photoFactory));

    }

    @Override
    public Optional<Author> findByAuthorNumber(String authorNumber) {
        return staticAuthors.stream()
                .filter(author -> author.getAuthorNumber().toString().equals(authorNumber))
                .findFirst();
    }

    @Override
    public List<Author> searchByNameNameStartsWith(String name) {
        return staticAuthors.stream()
                .filter(author -> author.getName().startsWith(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Author> searchByNameName(String name) {
        return staticAuthors.stream()
                .filter(author -> author.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    @Override
    public Author save(Author author) {
        staticAuthors.add(author);
        return author;
    }

    @Override
    public Iterable<Author> findAll() {
        return new ArrayList<>(staticAuthors);
    }

    @Override
    public Page<AuthorLendingResponseDTO> findTopAuthorByLendings(Pageable pageableRules) {
        List<AuthorLendingResponseDTO> topAuthors = List.of(
                new AuthorLendingResponseDTO("Manuel Antonio Pina", 15L),
                new AuthorLendingResponseDTO("Antoine de Saint Exupéry", 10L),
                new AuthorLendingResponseDTO("Alexandre Pereira", 8L),
                new AuthorLendingResponseDTO("Filipe Portela", 5L),
                new AuthorLendingResponseDTO("Ricardo Queirós", 3L)
        );

        return new PageImpl<>(topAuthors.stream().limit(5).collect(Collectors.toList()), pageableRules, 5);
    }

    @Override
    public void delete(Author author) {
        staticAuthors.removeIf(existingAuthor -> existingAuthor.getAuthorNumber().toString().equals(author.getAuthorNumber().toString()));
    }

    @Override
    public List<Author> findCoAuthorsByAuthorNumber(String authorNumber) {
        return staticAuthors.stream()
                .filter(author -> !author.getAuthorNumber().toString().equals(authorNumber))
                .collect(Collectors.toList());
    }
}
