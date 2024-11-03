package pt.psoft.g1.psoftg1.genremanagement.model;

import lombok.Getter;

public class Genre {

    private final int GENRE_MAX_LENGTH = 100;

    @Getter
    private String genre;

    protected Genre(){}

    public Genre(String genre) {
        setGenre(genre);
    }

    private void setGenre(String genre) {
        if(genre == null)
            throw new IllegalArgumentException("Genre cannot be null");
        if(genre.isBlank())
            throw new IllegalArgumentException("Genre cannot be blank");
        if(genre.length() > GENRE_MAX_LENGTH)
            throw new IllegalArgumentException("Genre has a maximum of 4096 characters");
        this.genre = genre;
    }

    public String toString() {
        return genre;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Genre other = (Genre) obj;
        return genre.equals(other.genre);
    }

    @Override
    public int hashCode() {
        return genre.hashCode();
    }
}
