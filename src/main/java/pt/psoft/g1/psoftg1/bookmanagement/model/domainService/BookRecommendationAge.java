package pt.psoft.g1.psoftg1.bookmanagement.model.domainService;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import pt.psoft.g1.psoftg1.bookmanagement.repositories.BookRepository;
import pt.psoft.g1.psoftg1.exceptions.NotFoundException;
import pt.psoft.g1.psoftg1.genremanagement.dto.request.RecommendationBooksRequestDTO;
import pt.psoft.g1.psoftg1.genremanagement.repositories.GenreRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Profile("recommendationAge")
public class BookRecommendationAge implements BookRecommendation {

    private GenreRepository genreRepository;
    private BookRepository bookRepository;

    @Override
    public List<Book> bookRecommendations(RecommendationBooksRequestDTO r) {
        String genre;
        if (r.getAge() < 10) {
            genre = "children";
        } else if (r.getAge() < 18) {
            genre = "juvenile";
        } else {
            genre = bookRepository.getMostLentGenreByReader(r.getReaderNumber());
            if (genre == null) {
                throw new NotFoundException("No genre found for the reader's lending history.");
            }
        }
        return bookRepository.findXBooksByGenre(genre, r.getNumberOfBooks());
    }
}
