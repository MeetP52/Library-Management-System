package book;

import dev.book.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(book);
        assertNotNull(book.getAuthors());
        assertNotNull(book.getGenres());
        assertTrue(book.getAuthors().isEmpty());
        assertTrue(book.getGenres().isEmpty());
    }

    @Test
    void testParameterizedConstructor() {
        Set<String> authors = new HashSet<>();
        authors.add("Author 1");
        Set<String> genres = new HashSet<>();
        genres.add("Genre 1");

        book = new Book("Title", "Series", "Description", authors, genres);

        assertEquals("Title", book.getTitle());
        assertEquals("Series", book.getSeries());
        assertEquals("Description", book.getDescription());
        assertTrue(book.getAuthors().contains("Author 1"));
        assertTrue(book.getGenres().contains("Genre 1"));
    }

    @Test
    void testCopyConstructor() {
        Set<String> authors = new HashSet<>();
        authors.add("Author 1");
        Set<String> genres = new HashSet<>();
        genres.add("Genre 1");

        book = new Book("Title", "Series", "Description", authors, genres);
        Book copiedBook = book.copy("deep");

        assertNotSame(book, copiedBook);
        assertEquals(book, copiedBook); // Check for equality

        // Modify copied book's authors and genres, should not affect the original book
        copiedBook.getAuthors().add("Author 2");
        copiedBook.getGenres().add("Genre 2");

        assertNotEquals(book, copiedBook); // Check that modification did not affect the original book
    }

    @Test
    void testEqualsAndHashCode() {
        Set<String> authors1 = new HashSet<>();
        authors1.add("Author 1");
        Set<String> genres1 = new HashSet<>();
        genres1.add("Genre 1");

        Set<String> authors2 = new HashSet<>();
        authors2.add("Author 1");
        Set<String> genres2 = new HashSet<>();
        genres2.add("Genre 1");

        Book book1 = new Book("Title", "Series", "Description", authors1, genres1);
        Book book2 = new Book("Title", "Series", "Description", authors2, genres2);

        assertEquals(book1, book2); // Check for equality based on content
        assertEquals(book1.hashCode(), book2.hashCode()); // Check that hash codes are equal
    }
}