package pt.psoft.g1.psoftg1.genremanagement.dataModel.sql;

import jakarta.persistence.*;
        import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "GENRE", uniqueConstraints = {
        @UniqueConstraint(name = "uc_genre_name", columnNames = {"GENRE"})
})
public class GenreDataModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GENRE_ID")
    private Long pk;

    @Column(name = "GENRE", nullable = false, length = 100)
    @NotNull
    @NotBlank(message = "Genre name cannot be blank")
    @Size(min = 1, max = 100, message = "Genre name must be between 1 and 100 characters")
    private String genre;

    public GenreDataModel() {}

    public GenreDataModel(String genre) {
        this.genre = genre;
    }
}

