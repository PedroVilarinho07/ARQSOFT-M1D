package pt.psoft.g1.psoftg1.bookmanagement.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import pt.psoft.g1.psoftg1.bookmanagement.dto.request.UpdateBookRequest;
import pt.psoft.g1.psoftg1.genremanagement.model.Genre;
import pt.psoft.g1.psoftg1.shared.model.EntityWithPhoto;

import java.util.ArrayList;
import java.util.List;

public class Book extends EntityWithPhoto {

    Isbn isbn;

    @Getter
    @NotNull
    Title title;

    @Getter
    @NotNull
    Genre genre;

    @Getter
    private List<Author> authors = new ArrayList<>();

    @Embedded
    Description description;

    private void setTitle(String title) {this.title = new Title(title);}

    private void setIsbn(String isbn) {
        this.isbn = new Isbn(isbn);
    }

    private void setDescription(String description) {this.description = new Description(description); }

    private void setGenre(Genre genre) {this.genre = genre; }

    private void setAuthors(List<Author> authors) {this.authors = authors; }

    public String getDescription(){ return this.description.toString(); }

    public Book(String isbn, String title, String description, Genre genre, List<Author> authors, String photoURI) {
        setTitle(title);
        setIsbn(isbn);
        if(description != null)
            setDescription(description);
        if(genre==null)
            throw new IllegalArgumentException("Genre cannot be null");
        setGenre(genre);
        if(authors == null)
            throw new IllegalArgumentException("Author list is null");
        if(authors.isEmpty())
            throw new IllegalArgumentException("Author list is empty");

        setAuthors(authors);
        setPhotoInternal(photoURI);
    }

    protected Book() {
        // got ORM only
    }

    public void removePhoto(long desiredVersion) {
        setPhotoInternal(null);
    }

    public void applyPatch(final Long desiredVersion, UpdateBookRequest request) {

        String title = request.getTitle();
        String description = request.getDescription();
        Genre genre = request.getGenreObj();
        List<Author> authors = request.getAuthorObjList();
        String photoURI = request.getPhotoURI();
        if(title != null) {
            setTitle(title);
        }

        if(description != null) {
            setDescription(description);
        }

        if(genre != null) {
            setGenre(genre);
        }

        if(authors != null) {
            setAuthors(authors);
        }

        if(photoURI != null)
            setPhotoInternal(photoURI);

    }

    public String getIsbn(){
        return this.isbn.toString();
    }
}
