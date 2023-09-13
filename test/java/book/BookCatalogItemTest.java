package book;

import dev.book.Book;
import dev.book.BookCatalogItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookCatalogItemTest {
    private BookCatalogItem catalogItem;
    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book("Title", "Series", "Description", null, null);
        catalogItem = new BookCatalogItem(book, 10);
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(catalogItem);
        assertEquals(book, catalogItem.getBook());
        assertEquals(10, catalogItem.getQuantity());
    }

    @Test
    void testParameterizedConstructor() {
        BookCatalogItem newItem = new BookCatalogItem(book, 5);
        assertEquals(book, newItem.getBook());
        assertEquals(5, newItem.getQuantity());
    }

    @Test
    void testCopyConstructor() {
        BookCatalogItem copiedItem = catalogItem.copy("deep");
        assertNotSame(catalogItem, copiedItem);
        assertEquals(catalogItem, copiedItem);
        assertNotSame(catalogItem.getBook(), copiedItem.getBook()); // Deep copy of Book
    }

    @Test
    void testAddQuantity() {
        assertTrue(catalogItem.addQuantity(5));
        assertEquals(15, catalogItem.getQuantity());
        assertFalse(catalogItem.addQuantity(10)); // Exceeds maximum quantity
        assertEquals(15, catalogItem.getQuantity()); // Quantity should remain unchanged
    }

    @Test
    void testRemoveQuantity() {
        assertTrue(catalogItem.removeQuantity(5));
        assertEquals(5, catalogItem.getQuantity());
        assertFalse(catalogItem.removeQuantity(10)); // Exceeds current quantity
        assertEquals(5, catalogItem.getQuantity()); // Quantity should remain unchanged
        assertFalse(catalogItem.removeQuantity(-1)); // Invalid quantity
        assertEquals(5, catalogItem.getQuantity()); // Quantity should remain unchanged
    }

    @Test
    void testEqualsAndHashCode() {
        BookCatalogItem newItem = new BookCatalogItem(book, 10);
        assertEquals(catalogItem, newItem); // Check for equality based on book
        assertEquals(catalogItem.hashCode(), newItem.hashCode()); // Check that hash codes are equal
    }
}