package pt.psoft.g1.psoftg1.bookmanagement.model.domainService;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import pt.psoft.g1.psoftg1.bookmanagement.repositories.BookRepository;
import pt.psoft.g1.psoftg1.genremanagement.dto.request.RecommendationBooksRequestDTO;
import pt.psoft.g1.psoftg1.genremanagement.model.Genre;
import pt.psoft.g1.psoftg1.genremanagement.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Profile("recommendationY")
public class BookRecommendationY implements BookRecommendation {

    private GenreRepository genreRepository;
    private BookRepository bookRepository;

    @Override
    public List<Book> bookRecommendations(RecommendationBooksRequestDTO requestDTO) {
        List<Genre> genres = genreRepository.getGenreWithMostLendings(requestDTO.getNumberOfGenres());
        List<Book> books = new ArrayList<>();
        for (Genre genre : genres) {
            books.addAll(bookRepository.findXBooksByGenre(genre.toString(), requestDTO.getNumberOfBooks()));
        }
        return books;
    }
}
