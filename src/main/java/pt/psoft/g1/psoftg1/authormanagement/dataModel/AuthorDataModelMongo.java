/*
package pt.psoft.g1.psoftg1.authormanagement.dataModel;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "authors")
public class AuthorDataModelMongo {

    @Id
    private String id;

    @Getter @Setter
    private Long authorNumber;

    @Version
    private Long version;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String bio;

    @Getter @Setter
    private String photoURI;

    // Construtores
    public AuthorDataModelMongo(Long authorNumber, String name, String bio, String photoURI) {
        this.authorNumber = authorNumber;
        this.name = name;
        this.bio = bio;
        this.photoURI = photoURI;
    }

    protected AuthorDataModelMongo() {
    }
}
*/