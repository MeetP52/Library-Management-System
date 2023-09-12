package book;

import dev.book.Book;
import dev.book.BookCatalog;
import dev.book.BookCatalogItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookCatalogTest {
    private BookCatalog catalog;

    @BeforeEach
    void setUp() {
        catalog = new BookCatalog();
    }

    @Test
    void testAddAndFindBook() {
        Book book = new Book("Title", "Series", "Description", null, null);
        assertTrue(catalog.addBook(book));
        int bookId = catalog.findBookId(book);
        Book foundBook = catalog.findBook(bookId);
        assertNotNull(foundBook);
        assertEquals(book, foundBook);
    }

    @Test
    void testRemoveBook() {
        Book book = new Book("Title", "Series", "Description", null, null);
        catalog.addBook(book);
        int bookId = catalog.findBookId(book);
        assertTrue(catalog.removeBook(book));
        assertNull(catalog.findBook(bookId));
    }

    @Test
    void testCheckout() {
        Book book = new Book("Title", "Series", "Description", null, null);
        catalog.addBook(book);
        int bookId = catalog.findBookId(book);
        assertTrue(catalog.checkout(book));
        BookCatalogItem catalogItem = catalog.findBookItem(book);
        assertEquals(19, catalogItem.getQuantity()); // Default quantity is 20
    }

    @Test
    void testFindBookByTitle() {
        Book book1 = new Book("Title1", "Series", "Description", null, null);
        Book book2 = new Book("Title2", "Series", "Description", null, null);
        catalog.addBook(book1);
        catalog.addBook(book2);

        List<Book> foundBooks = catalog.findBook("Title1");
        assertNotNull(foundBooks);
        assertEquals(1, foundBooks.size());
        assertEquals(book1, foundBooks.get(0));
    }

    @Test
    void testStoreBookCatalogData() {
        assertTrue(catalog.storeBookCatalogData());
        // You can add assertions to check if data was stored successfully in a file
    }

    // Add more test cases as needed to cover other methods and scenarios in the BookCatalog class.
}