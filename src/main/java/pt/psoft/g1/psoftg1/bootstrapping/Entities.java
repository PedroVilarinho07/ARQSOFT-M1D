package pt.psoft.g1.psoftg1.bootstrapping;

import pt.psoft.g1.psoftg1.authormanagement.factories.AuthorNumberFactory;
import pt.psoft.g1.psoftg1.authormanagement.factories.BioFactory;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import pt.psoft.g1.psoftg1.genremanagement.model.Genre;
import pt.psoft.g1.psoftg1.shared.factories.NameFactory;
import pt.psoft.g1.psoftg1.shared.factories.PhotoFactory;

import java.util.ArrayList;
import java.util.List;

public class Entities {
    public static List<Book> getBooks() {
        List<Book> books = new ArrayList<>();

        List<Author> defaultAuthors = List.of(new Author("Author Name", "name", "bio", null, new AuthorNumberFactory(), new NameFactory(), new BioFactory(), new PhotoFactory())); // Replace with actual Author details as necessary

        books.add(new Book("9783161484100", "Juvenile Adventure", "An exciting adventure for juveniles.", getGenres().get(0), defaultAuthors, "/photos/juvenile.jpg"));
        books.add(new Book("9781234567897", "Space Odyssey", "A thrilling journey through space.", getGenres().get(0), defaultAuthors, "/photos/scifi.jpg"));
        books.add(new Book("9780198526636", "Magic Realm", "A fantasy world full of mysteries.", getGenres().get(0), defaultAuthors, "/photos/fantasy.jpg"));
        books.add(new Book("9780306406157", "The Great Mystery", "A mysterious case that needs solving.", getGenres().get(1), defaultAuthors, "/photos/mystery.jpg"));
        books.add(new Book("9780123456789", "The Last Chase", "A suspenseful thriller that keeps you on edge.", getGenres().get(1), defaultAuthors, "/photos/thriller.jpg"));
        books.add(new Book("9781402894626", "Love Beyond Borders", "A romantic story of love overcoming obstacles.", getGenres().get(1), defaultAuthors, "/photos/romance.jpg"));
        books.add(new Book("9781250164561", "The Haunted Manor", "A horror story that chills the spine.", getGenres().get(1), defaultAuthors, "/photos/horror.jpg"));
        books.add(new Book("9781449469849", "Historical Tales", "Stories set in significant historical events.", getGenres().get(2), defaultAuthors, "/photos/historical.jpg"));
        books.add(new Book("9780307588936", "Famous Lives", "Biographies of famous personalities.", getGenres().get(2), defaultAuthors, "/photos/biography.jpg"));
        books.add(new Book("9780143112336", "Health Essentials", "Guidance for a healthier life.", getGenres().get(2), defaultAuthors, "/photos/health.jpg"));
        books.add(new Book("9780553381102", "World Explorer", "Travel stories from around the world.", getGenres().get(2), defaultAuthors, "/photos/travel.jpg"));
        books.add(new Book("9780439129488", "Children's Wonderland", "A collection of stories for children.", getGenres().get(2), defaultAuthors, "/photos/children.jpg"));
        books.add(new Book("9780060256654", "Teen Journeys", "Young adult stories of selfdiscovery.", getGenres().get(2), defaultAuthors, "/photos/youngadult.jpg"));
        books.add(new Book("9780525423975", "Adventures Unknown", "Exciting adventures from distant lands.", getGenres().get(3), defaultAuthors, "/photos/adventure.jpg"));
        books.add(new Book("9780062278233", "Comic Craze", "A collection of popular comics.", getGenres().get(3), defaultAuthors, "/photos/comics.jpg"));

        return books;
    }

    public static List<Genre> getGenres() {
        List<Genre> genres = new ArrayList<>();

        genres.add(new Genre("Juvenile"));
        genres.add(new Genre("Science Fiction"));
        genres.add(new Genre("Children"));
        genres.add(new Genre("Fantasy"));
        genres.add(new Genre("Mystery"));
        genres.add(new Genre("Thriller"));
        genres.add(new Genre("Romance"));
        genres.add(new Genre("Horror"));
        genres.add(new Genre("Historical"));
        genres.add(new Genre("Biography"));
        genres.add(new Genre("Health"));
        genres.add(new Genre("Travel"));
        genres.add(new Genre("Children"));
        genres.add(new Genre("Young Adult"));
        genres.add(new Genre("Adventure"));
        genres.add(new Genre("Comics"));

        return genres;
    }
}
