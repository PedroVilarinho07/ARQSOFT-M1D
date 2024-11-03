/*
package pt.psoft.g1.psoftg1.bookmanagement.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pt.psoft.g1.psoftg1.bookmanagement.dataModel.BookDataModelMongo;

import java.util.Collection;
import java.util.List;

public interface BookMongoSpringRepository extends MongoRepository<BookDataModelMongo, String> {
    List<BookDataModelMongo> findByGenre(String genre);
    List<BookDataModelMongo> findByTitle(String title);

    // Nova query para buscar livros com base em uma lista de authorNumbers
    List<BookDataModelMongo> findByAuthorNumbersIn(List<Long> authorNumbers);

    Collection<Object> findByAuthorsContaining(String authorName);

    Collection<Object> findByAuthorsAuthorNumber(Long authorNumber);

    List<BookDataModelMongo> findByAuthorsNameContaining(String name);


}
*/