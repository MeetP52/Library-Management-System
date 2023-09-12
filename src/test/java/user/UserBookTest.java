package user;

import dev.book.Book;
import dev.user.User;
import dev.user.UserBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserBookTest {
    private UserBook userBook;

    @BeforeEach
    void setUp() {
        Book book = new Book("Book1", "Series1", "Description1", null, null);
        userBook = new UserBook(book);
    }

    @Test
    void testConstructorWithDefaultDates() {
        assertNotNull(userBook.getBook());
        assertNotNull(userBook.getPickUpDate());
        assertNotNull(userBook.getDueDate());
    }

    @Test
    void testConstructorWithDueDate() {
        Book book = new Book("Book2", "Series2", "Description2", null, null);
        LocalDate dueDate = LocalDate.of(2023, 9, 30);
        UserBook userBookWithDueDate = new UserBook(book, dueDate);

        assertNotNull(userBookWithDueDate.getBook());
        assertNotNull(userBookWithDueDate.getPickUpDate());
        assertEquals(dueDate, userBookWithDueDate.getDueDate());
    }

    @Test
    void testCopyUserBookDeep() {
        UserBook copy = userBook.copy("deep");
        assertNotSame(userBook, copy); // Check if a deep copy was created
        assertEquals(userBook, copy); // Check if the content of the copied UserBook is the same
    }

    @Test
    void testCopyUserBookShallow() {
        UserBook copy = userBook.copy("shallow");
        assertSame(userBook, copy); // Check if a shallow copy was created (same reference)
    }

    @Test
    void testEquals() {
        Book book1 = new Book("Book1", "Series1", "Description1", null, null);
        UserBook userBook1 = new UserBook(book1);
        UserBook userBook2 = new UserBook(userBook1.getBook());

        assertEquals(userBook1, userBook2);
    }

    @Test
    void testNotEquals() {
        Book book1 = new Book("Book1", "Series1", "Description1", null, null);
        UserBook userBook1 = new UserBook(book1);

        Book book2 = new Book("Book2", "Series2", "Description2", null, null);
        UserBook userBook2 = new UserBook(book2);

        assertNotEquals(userBook1, userBook2);
    }

    @Test
    void testToString() {
        String expectedString = "UserBook { " +
                "\n\t book=" + userBook.getBook() +
                ",\n\t pickUpDate=" + userBook.getPickUpDate() +
                ",\n\t dueDate=" + userBook.getDueDate() + "\n\t" +
                '}';
        assertEquals(expectedString, userBook.toString());
    }
}