/*
package pt.psoft.g1.psoftg1.bookmanagement.dataModel;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import pt.psoft.g1.psoftg1.authormanagement.dataModel.AuthorDataModelMongo;

import java.util.List;

@Document(collection = "books")
public class BookDataModelMongo {

    @Id
    private String id;

    @Getter @Setter
    private String isbn;

    @Version
    private Long version;

    @Getter @Setter
    private String title;

    @Getter @Setter
    private String genre;

    @Getter @Setter
    private String description;

    // Agora é uma lista de objetos `AuthorDataModelMongo`
    @Getter @Setter
    private List<AuthorDataModelMongo> authors;

    @Getter @Setter
    private String photoURI;

    @Getter @Setter
    private List<Long> authorNumbers;
    // Construtor principal
    public BookDataModelMongo(String isbn, String title, String genre, String description, List<AuthorDataModelMongo> authors, String photoURI) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.authors = authors;
        this.photoURI = photoURI;
    }

    // Construtor padrão
    protected BookDataModelMongo() {}
}
*/