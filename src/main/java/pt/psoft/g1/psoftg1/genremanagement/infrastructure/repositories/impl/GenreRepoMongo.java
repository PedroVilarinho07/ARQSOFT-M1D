package pt.psoft.g1.psoftg1.genremanagement.infrastructure.repositories.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.psoftg1.bookmanagement.dto.request.GenreBookCountDTO;
import pt.psoft.g1.psoftg1.bootstrapping.Entities;
import pt.psoft.g1.psoftg1.genremanagement.dto.GenreLendingsDTO;
import pt.psoft.g1.psoftg1.genremanagement.dto.GenreLendingsPerMonthDTO;
import pt.psoft.g1.psoftg1.genremanagement.model.Genre;
import pt.psoft.g1.psoftg1.genremanagement.repositories.GenreRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("mongo")
public class GenreRepoMongo implements GenreRepository {

    private final List<Genre> staticGenres;

    public GenreRepoMongo() {
        staticGenres = new ArrayList<>();
        staticGenres.add(new Genre("Fantasia"));
        staticGenres.add(new Genre("Informação"));
        staticGenres.add(new Genre("Romance"));
        staticGenres.add(new Genre("Infantil"));
        staticGenres.add(new Genre("Thriller"));
    }

    @Override
    public Iterable<Genre> findAll() {
        return List.copyOf(staticGenres);
    }

    @Override
    public Optional<Genre> findByString(String genreName) {
        return staticGenres.stream()
                .filter(genre -> genre.toString().equalsIgnoreCase(genreName)) // Usando toString() como identificador do nome
                .findFirst();
    }

    @Override
    public Genre save(Genre genre) {
        staticGenres.add(genre);
        return genre;
    }

    @Override
    public Page<GenreBookCountDTO> findTop5GenreByBookCount(Pageable pageable) {
        // Simulando contagem de livros para os gêneros estáticos
        List<GenreBookCountDTO> genreBookCountDTOs = List.of(
                new GenreBookCountDTO("Fantasia", 50L),
                new GenreBookCountDTO("Informação", 30L),
                new GenreBookCountDTO("Romance", 25L),
                new GenreBookCountDTO("Infantil", 20L),
                new GenreBookCountDTO("Thriller", 15L)
        );

        return new PageImpl<>(genreBookCountDTOs, pageable, genreBookCountDTOs.size());
    }


    @Override
    public List<GenreLendingsDTO> getAverageLendingsInMonth(LocalDate month, pt.psoft.g1.psoftg1.shared.services.Page page) {
        // Simulando valores médios de empréstimos para cada gênero
        return List.of(
                new GenreLendingsDTO("Fantasia", 10.5),
                new GenreLendingsDTO("Informação", 7.8),
                new GenreLendingsDTO("Romance", 5.3),
                new GenreLendingsDTO("Infantil", 8.1),
                new GenreLendingsDTO("Thriller", 6.4)
        );
    }

    @Override
    public List<GenreLendingsPerMonthDTO> getLendingsPerMonthLastYearByGenre() {
        // Simulando valores de empréstimos mensais para cada gênero ao longo de um ano
        List<GenreLendingsPerMonthDTO> lendingsPerMonth = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            lendingsPerMonth.add(new GenreLendingsPerMonthDTO(
                    2023, month, List.of(
                    new GenreLendingsDTO("Fantasia", 10 + month),
                    new GenreLendingsDTO("Informação", 8 + month),
                    new GenreLendingsDTO("Romance", 5 + month),
                    new GenreLendingsDTO("Infantil", 7 + month),
                    new GenreLendingsDTO("Thriller", 6 + month)
            )));
        }
        return lendingsPerMonth;
    }

    @Override
    public List<GenreLendingsPerMonthDTO> getLendingsAverageDurationPerMonth(LocalDate startDate, LocalDate endDate) {
        // Simulando valores de duração média de empréstimos por mês para um intervalo de datas
        List<GenreLendingsPerMonthDTO> averageDurations = new ArrayList<>();
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusMonths(1)) {
            averageDurations.add(new GenreLendingsPerMonthDTO(
                    date.getYear(), date.getMonthValue(), List.of(
                    new GenreLendingsDTO("Fantasia", 15.0),
                    new GenreLendingsDTO("Informação", 12.5),
                    new GenreLendingsDTO("Romance", 9.0),
                    new GenreLendingsDTO("Infantil", 10.2),
                    new GenreLendingsDTO("Thriller", 8.7)
            )));
        }
        return averageDurations;
    }

    @Override
    public List<Genre> getGenreWithMostLendings(int numberOfGenres) {
        return new ArrayList<>(Entities.getGenres().subList(0, numberOfGenres));
    }

    @Override
    public void delete(Genre genre) {
        staticGenres.removeIf(g -> g.toString().equalsIgnoreCase(genre.toString())); // Usando toString() como identificador do nome
    }
}
