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

        books.add(new Book("978-3-16-148410-0", "Juvenile Adventure", "An exciting adventure for juveniles.", getGenres().get(0), defaultAuthors, "/photos/juvenile.jpg"));
        books.add(new Book("978-1-23-456789-7", "Space Odyssey", "A thrilling journey through space.", getGenres().get(0), defaultAuthors, "/photos/scifi.jpg"));
        books.add(new Book("978-0-19-852663-6", "Magic Realm", "A fantasy world full of mysteries.", getGenres().get(0), defaultAuthors, "/photos/fantasy.jpg"));
        books.add(new Book("978-0-306-40615-7", "The Great Mystery", "A mysterious case that needs solving.", getGenres().get(1), defaultAuthors, "/photos/mystery.jpg"));
        books.add(new Book("978-0-12-345678-9", "The Last Chase", "A suspenseful thriller that keeps you on edge.", getGenres().get(1), defaultAuthors, "/photos/thriller.jpg"));
        books.add(new Book("978-1-40-289462-6", "Love Beyond Borders", "A romantic story of love overcoming obstacles.", getGenres().get(1), defaultAuthors, "/photos/romance.jpg"));
        books.add(new Book("978-1-25-016456-1", "The Haunted Manor", "A horror story that chills the spine.", getGenres().get(1), defaultAuthors, "/photos/horror.jpg"));
        books.add(new Book("978-1-44-946984-9", "Historical Tales", "Stories set in significant historical events.", getGenres().get(2), defaultAuthors, "/photos/historical.jpg"));
        books.add(new Book("978-0-307-58893-6", "Famous Lives", "Biographies of famous personalities.", getGenres().get(2), defaultAuthors, "/photos/biography.jpg"));
        books.add(new Book("978-0-14-311233-6", "Health Essentials", "Guidance for a healthier life.", getGenres().get(2), defaultAuthors, "/photos/health.jpg"));
        books.add(new Book("978-0-553-38110-2", "World Explorer", "Travel stories from around the world.", getGenres().get(2), defaultAuthors, "/photos/travel.jpg"));
        books.add(new Book("978-0-439-12948-8", "Children's Wonderland", "A collection of stories for children.", getGenres().get(2), defaultAuthors, "/photos/children.jpg"));
        books.add(new Book("978-0-06-025665-4", "Teen Journeys", "Young adult stories of self-discovery.", getGenres().get(2), defaultAuthors, "/photos/youngadult.jpg"));
        books.add(new Book("978-0-525-42397-5", "Adventures Unknown", "Exciting adventures from distant lands.", getGenres().get(3), defaultAuthors, "/photos/adventure.jpg"));
        books.add(new Book("978-0-06-227823-3", "Comic Craze", "A collection of popular comics.", getGenres().get(3), defaultAuthors, "/photos/comics.jpg"));

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
