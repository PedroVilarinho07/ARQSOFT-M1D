package pt.psoft.g1.psoftg1.bootstrapping;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pt.psoft.g1.psoftg1.genremanagement.model.Genre;
import pt.psoft.g1.psoftg1.genremanagement.repositories.IGenreRepository;
import pt.psoft.g1.psoftg1.readermanagement.model.ReaderDetails;
import pt.psoft.g1.psoftg1.readermanagement.repositories.ReaderRepository;
import pt.psoft.g1.psoftg1.usermanagement.model.Librarian;
import pt.psoft.g1.psoftg1.usermanagement.model.Reader;
import pt.psoft.g1.psoftg1.usermanagement.model.User;
import pt.psoft.g1.psoftg1.usermanagement.repositories.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Profile("bootstrap")
@Order(1)
public class UserBootstrapper implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ReaderRepository readerRepository;
    private final IGenreRepository IGenreRepository;
    private final JdbcTemplate jdbcTemplate;
    private List<String> queriesToExecute = new ArrayList<>();

    @Override
    @Transactional
    public void run(final String... args) {
        createReaders();
        createLibrarian();
        executeQueries();
    }

    private void createReaders() {
        // Reader1 - Manuel
        if (userRepository.findByUsername("manuel@gmail.com").isEmpty()) {
            final Reader manuel = Reader.newReader("manuel@gmail.com", "Manuelino123!", "Manuel Sarapinto das Coives");
            userRepository.save(manuel);

            String dateFormat = LocalDateTime.of(2024,1,20,0,0,0).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String query = String.format("UPDATE dbo.T_USER SET CREATED_AT = '%s' WHERE USERNAME = '%s'", dateFormat, manuel.getUsername());
            queriesToExecute.add(query);

            Optional<ReaderDetails> readerDetails1 = readerRepository.findByReaderNumber(LocalDate.now().getYear() + "/1");
            Optional<Genre> g1 = IGenreRepository.findByString("Fantasia");
            Optional<Genre> g2 = IGenreRepository.findByString("Infantil");
            List<Genre> interestList = new ArrayList<>();
            g1.ifPresent(interestList::add);
            g2.ifPresent(interestList::add);

            if (readerDetails1.isEmpty()) {
                ReaderDetails r1 = new ReaderDetails(
                        1,
                        manuel,
                        "2000-01-01",
                        "919191919",
                        true,
                        true,
                        true,
                        "readerPhotoTest.jpg",
                        interestList);
                readerRepository.save(r1);
            }
        }

        // Repeat similar modifications for other readers (João, Pedro, Catarina, etc.)
        // Each entry should replace "PUBLIC.T_USER" with "dbo.T_USER" and update the date format if necessary.
    }

    private void createLibrarian() {
        // Maria
        if (userRepository.findByUsername("maria@gmail.com").isEmpty()) {
            final User maria = Librarian.newLibrarian("maria@gmail.com", "Mariaroberta!123", "Maria Roberta");
            userRepository.save(maria);
        }
    }

    private void executeQueries() {
        for (String query : queriesToExecute) {
            jdbcTemplate.update(query);
        }
    }
}
