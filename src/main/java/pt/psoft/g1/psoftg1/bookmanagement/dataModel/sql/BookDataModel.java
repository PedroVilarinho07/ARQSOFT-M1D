package pt.psoft.g1.psoftg1.bookmanagement.dataModel.sql;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import pt.psoft.g1.psoftg1.authormanagement.dataModel.sql.AuthorDataModel;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "BOOK", uniqueConstraints = {
        @UniqueConstraint(name = "uc_book_isbn", columnNames = {"ISBN"})
})
public class BookDataModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_ID")
    private Long pk;

    @Column(name = "ISBN", length = 16, nullable = false)
    @Size(min = 10, max = 13)
    private String isbn;

    @Column(name = "TITLE", length = 128, nullable = false)
    @NotNull
    @Size(max = 128)
    private String title;

    @Column(name = "GENRE_Id")
    private String genre;

    @Column(name = "DESCRIPTION", length = 4096)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "BOOK_AUTHORS",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_NUMBER", referencedColumnName = "AUTHOR_NUMBER")
    )
    private List<AuthorDataModel> authors;

    @Column(name = "PHOTO_URI", length = 1024)
    private String photoURI;

    public BookDataModel() {}

    public BookDataModel(String isbn, String title, String description, String genre, List<AuthorDataModel> authors, String photoURI) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.authors = authors;
        this.photoURI = photoURI;
    }
}

