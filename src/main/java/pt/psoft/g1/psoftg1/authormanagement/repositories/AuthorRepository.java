package pt.psoft.g1.psoftg1.authormanagement.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pt.psoft.g1.psoftg1.authormanagement.dto.response.AuthorLendingResponseDTO;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;

import java.util.List;
import java.util.Optional;

public interface IAuthorRepository {

    Optional<Author> findByAuthorNumber(String authorNumber);
    List<Author> searchByNameNameStartsWith(String name);
    List<Author> searchByNameName(String name);
    Author save(Author author);
    Iterable<Author> findAll();
    Page<AuthorLendingResponseDTO> findTopAuthorByLendings (Pageable pageableRules);
    void delete(Author author);
    List<Author> findCoAuthorsByAuthorNumber(String authorNumber);

}
