package pt.psoft.g1.psoftg1.bookmanagement.model.domainService;

import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import pt.psoft.g1.psoftg1.genremanagement.dto.request.RecommendationBooksRequestDTO;

import java.util.List;

public interface BookRecommendation {
    List<Book> bookRecommendations(RecommendationBooksRequestDTO requestDTO);
}
