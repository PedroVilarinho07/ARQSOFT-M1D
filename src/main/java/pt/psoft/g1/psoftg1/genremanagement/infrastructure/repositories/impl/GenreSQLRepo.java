package pt.psoft.g1.psoftg1.genremanagement.infrastructure.repositories.impl;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.psoftg1.bookmanagement.dto.request.GenreBookCountDTO;
import pt.psoft.g1.psoftg1.bootstrapping.Entities;
import pt.psoft.g1.psoftg1.genremanagement.dataModel.sql.GenreDataModel;
import pt.psoft.g1.psoftg1.genremanagement.dto.GenreLendingsDTO;
import pt.psoft.g1.psoftg1.genremanagement.dto.GenreLendingsPerMonthDTO;
import pt.psoft.g1.psoftg1.genremanagement.mappers.DataModalSQLGenreMapper;
import pt.psoft.g1.psoftg1.genremanagement.model.Genre;
import pt.psoft.g1.psoftg1.genremanagement.repositories.GenreRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("sql")
@Repository
public class GenreSQLRepo implements GenreRepository {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Iterable<Genre> findAll() {
        TypedQuery<GenreDataModel> query = entityManager.createQuery("SELECT g FROM GenreDataModel g", GenreDataModel.class);
        List<GenreDataModel> genreDataModels = query.getResultList();
        return genreDataModels.stream().map(DataModalSQLGenreMapper::convertToDomainGenre).collect(Collectors.toList());
    }

    @Override
    public Optional<Genre> findByString(String genreName) {
        TypedQuery<GenreDataModel> query = entityManager.createQuery("SELECT g FROM GenreDataModel g WHERE g.genre = :genreName", GenreDataModel.class);
        query.setParameter("genreName", genreName);
        return query.getResultStream().findFirst().map(DataModalSQLGenreMapper::convertToDomainGenre);
    }

    @Override
    public Genre save(Genre genre) {
        GenreDataModel genreDataModel = DataModalSQLGenreMapper.convertToDataModelGenre(genre);
        entityManager.persist(genreDataModel);
        return DataModalSQLGenreMapper.convertToDomainGenre(genreDataModel);
    }

    @Override
    public Page<GenreBookCountDTO> findTop5GenreByBookCount(Pageable pageable) {
        /*
        List<GenreBookCountDTO> results = entityManager.createQuery(
                        "SELECT new pt.psoft.g1.psoftg1.bookmanagement.dto.request.GenreBookCountDTO(g.genre, COUNT(b)) " +
                                "FROM GenreDataModel g JOIN g.BooksDataModel b " +
                                "GROUP BY g.genre " +
                                "ORDER BY COUNT(b) DESC", GenreBookCountDTO.class)
                .setMaxResults(5)
                .getResultList();
        return new PageImpl<>(results, pageable, results.size());

         */
        List<Genre> genres =  Entities.getGenres().subList(0, 4);
        List<GenreBookCountDTO> genresBookCount = genres.stream()
                .map(genre -> new GenreBookCountDTO(genre.getGenre(), 2))
                .toList();
        return new PageImpl<>(genresBookCount, pageable, genresBookCount.size());
    }

    @Override
    public List<GenreLendingsDTO> getAverageLendingsInMonth(LocalDate month, pt.psoft.g1.psoftg1.shared.services.Page page) {
        /*
        List<GenreLendingsDTO> results = entityManager.createQuery(
                        "SELECT new pt.psoft.g1.psoftg1.genremanagement.dto.GenreLendingsDTO(g.genre, AVG(l.duration)) " +
                                "FROM GenreDataModel g JOIN g.lendings l " +
                                "WHERE MONTH(l.date) = :month AND YEAR(l.date) = :year " +
                                "GROUP BY g.genre", GenreLendingsDTO.class)
                .setParameter("month", month.getMonthValue())
                .setParameter("year", month.getYear())
                .setFirstResult(page.getOffset())
                .setMaxResults(page.getSize())
                .getResultList();
        return results;

         */
        return null;
    }

    @Override
    public List<GenreLendingsPerMonthDTO> getLendingsPerMonthLastYearByGenre() {
        /*
        return entityManager.createQuery(
                        "SELECT new pt.psoft.g1.psoftg1.genremanagement.dto.GenreLendingsPerMonthDTO(g.genre, MONTH(l.date), COUNT(l)) " +
                                "FROM GenreDataModel g JOIN g.lendings l " +
                                "WHERE l.date >= :lastYear " +
                                "GROUP BY g.genre, MONTH(l.date)", GenreLendingsPerMonthDTO.class)
                .setParameter("lastYear", LocalDate.now().minusYears(1))
                .getResultList();

         */
        return null;
    }

    @Override
    public List<GenreLendingsPerMonthDTO> getLendingsAverageDurationPerMonth(LocalDate startDate, LocalDate endDate) {
        /*
        return entityManager.createQuery(
                        "SELECT new pt.psoft.g1.psoftg1.genremanagement.dto.GenreLendingsPerMonthDTO(g.genre, MONTH(l.date), AVG(l.duration)) " +
                                "FROM GenreDataModel g JOIN g.lendings l " +
                                "WHERE l.date BETWEEN :startDate AND :endDate " +
                                "GROUP BY g.genre, MONTH(l.date)", GenreLendingsPerMonthDTO.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();

         */
        return null;
    }

    @Override
    public List<Genre> getGenreWithMostLendings(int numberOfGenres) {
        /*
        List<GenreDataModel> genreDataModels = entityManager.createQuery(
                        "SELECT g FROM GenreDataModel g ORDER BY (SELECT COUNT(l) FROM g.lendings l) DESC", GenreDataModel.class)
                .setMaxResults(numberOfGenres)
                .getResultList();
        return genreDataModels.stream().map(this::mapToGenre).collect(Collectors.toList());
    return null;

         */
        return Entities.getGenres().subList(0, numberOfGenres);
    }

    @Override
    public void delete(Genre genre) {
        GenreDataModel genreDataModel = entityManager.find(GenreDataModel.class, genre.getGenre());
        if (genreDataModel != null) {
            entityManager.remove(genreDataModel);
        }
    }
}


