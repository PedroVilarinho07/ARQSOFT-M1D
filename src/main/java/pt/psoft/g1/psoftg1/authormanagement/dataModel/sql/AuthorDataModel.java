package pt.psoft.g1.psoftg1.authormanagement.dataModel.sql;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "AUTHOR")
public class AuthorDataModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK")
    private Long pk;

    @Column(name = "AUTHOR_NUMBER", unique = true, nullable = false)
    private String authorNumber;

    @Column(name = "NAME", nullable = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @Column(name = "BIO", nullable = false, length = 4096)
    @NotNull
    @Size(min = 1, max = 4096)
    private String bio;

    @Column(name = "PHOTO_URI", length = 1024)
    private String photoURI;

    public AuthorDataModel() {
    }

    public AuthorDataModel(String authorNumber, String name, String bio, String photoURI) {
        this.authorNumber = authorNumber;
        this.name = name;
        this.bio = bio;
        this.photoURI = photoURI;
    }
}
