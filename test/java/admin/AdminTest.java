package admin;

import dev.admin.Admin;
import dev.book.Book;
import dev.book.BookCatalog;
import dev.user.AddInfo;
import dev.user.SecurityQuestion;
import dev.user.User;
import dev.user.UserCatalogItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
class AdminTest {
    private Admin admin;

    @BeforeEach
    void setUp() {
        // Create a test admin user
        User adminUser = new User("Admin", "User", "admin@example.com", "123-456-7890", "USA", "NY", "New York");
        AddInfo adminInfo = new AddInfo("AdminPassword123", SecurityQuestion.WHAT_IS_YOUR_MOTHERS_MAIDEN_NAME, "AdminAnswer123");
        UserCatalogItem adminUserCatalogItem = new UserCatalogItem(adminUser, adminInfo);
        admin = new Admin(adminUserCatalogItem);
    }

    @Test
    void testRemoveBookFromCatalog() {
        // Create some test books
        Book book1 = new Book("Book 1", "Series 1", "Description for Book 1",
                Set.of("Author 1"), Set.of("Genre 1", "Genre 2"));
        Book book2 = new Book("Book 2", "Series 2", "Description for Book 2",
                Set.of("Author 2"), Set.of("Genre 1", "Genre 3"));

        BookCatalog bookCatalog = BookCatalog.getBookCatalog();

        // Add books to the catalog
        assertTrue(bookCatalog.addBook(book1));
        assertTrue(bookCatalog.addBook(book2));

        // Remove books from the catalog using Admin
        List<Book> booksToRemove = new ArrayList<>();
        booksToRemove.add(book1);
        booksToRemove.add(book2);

        assertTrue(admin.removeBookToCatalog(booksToRemove));

        // Check if the books are removed
        assertNull(bookCatalog.findBook(book1));
        assertNull(bookCatalog.findBook(book2));
    }

    @Test
    void testAddBookToCatalog() {
        // Create some test books
        Book book1 = new Book("Book 1", "Series 1", "Description for Book 1",
                Set.of("Author 1"), Set.of("Genre 1", "Genre 2"));
        Book book2 = new Book("Book 2", "Series 2", "Description for Book 2",
                Set.of("Author 2"), Set.of("Genre 1", "Genre 3"));

        List<Book> booksToAdd = new ArrayList<>();
        booksToAdd.add(book1);
        booksToAdd.add(book2);

        // Add books to the catalog using Admin
        assertTrue(admin.addBookToCatalog(booksToAdd));

        // Check if the books are in the catalog
        BookCatalog bookCatalog = BookCatalog.getBookCatalog();
        assertNotNull(bookCatalog.findBook(book1));
        assertNotNull(bookCatalog.findBook(book2));
    }

}