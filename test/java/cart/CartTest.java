package cart;

import dev.book.Book;
import dev.book.BookCatalog;
import dev.book.BookCatalogItem;
import dev.cart.Cart;
import dev.user.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    private Cart cart;
    private BookCatalog catalog;
    private UserCatalogItem userCatalogItem;
    private User user;
    private AddInfo addInfo;

    @BeforeEach
    void setUp() {
        user = new User("John", "Doe", "john@example.com", "123-456-7890", "USA", "NY", "New York");
        addInfo = new AddInfo("Password123", SecurityQuestion.WHAT_IS_YOUR_MOTHERS_MAIDEN_NAME, "Answer123");
        userCatalogItem = new UserCatalogItem(user, addInfo);
        cart = new Cart();
        userCatalogItem.getAddInfo().setCart(cart);
        catalog = BookCatalog.getBookCatalog();
    }
    @Test
    void testCheckoutCart() {
        Book book1 = createBook("Book X", "Series A", "Description 1", new HashSet<>(Arrays.asList("Author 1")), new HashSet<>(Arrays.asList("Genre 1")));
        Book book2 = createBook("Book Y", "Series B", "Description 2", new HashSet<>(Arrays.asList("Author 2")), new HashSet<>(Arrays.asList("Genre 2")));

        catalog.addBook(book1);
        catalog.addBook(book2);

        cart.addBooks(Arrays.asList(book1, book2));

        assertTrue(userCatalogItem.getAddInfo().getCart().checkout(userCatalogItem));

        List<UserBook> userBooks = userCatalogItem.getAddInfo().getUserBooks();
        assertEquals(2, userBooks.size());
        assertTrue(userBooks.contains(new UserBook(book1)));
        assertTrue(userBooks.contains(new UserBook(book2)));
        assertTrue(cart.getBooks().isEmpty());
        catalog.removeBook(book1);
        catalog.removeBook(book2);
    }

    @Test
    void testAddBooksWithInsufficientQuantity() {
        Book book1 = createBook("Book X", "Series A", "Description 1", new HashSet<>(Arrays.asList("Author 1")), new HashSet<>(Arrays.asList("Genre 1")));
        Book book2 = createBook("Book Y", "Series B", "Description 2", new HashSet<>(Arrays.asList("Author 2")), new HashSet<>(Arrays.asList("Genre 2")));

        BookCatalogItem bookCatalogItem1 = new BookCatalogItem(book1,0);
        BookCatalogItem bookCatalogItem2 = new BookCatalogItem(book2,0);


        assertFalse(cart.addBooks(Arrays.asList(book1, book2)));

        List<Book> cartBooks = cart.getBooks();
        assertTrue(cartBooks.isEmpty());
    }

    @Test
    void testRemoveNonExistingBooks() {
        Book book1 = createBook("Book X", "Series A", "Description 1", new HashSet<>(Arrays.asList("Author 1")), new HashSet<>(Arrays.asList("Genre 1")));
        Book book2 = createBook("Book Y", "Series B", "Description 2", new HashSet<>(Arrays.asList("Author 2")), new HashSet<>(Arrays.asList("Genre 2")));

        assertFalse(cart.removeBooks(Arrays.asList(book1, book2)));
        assertTrue(cart.getBooks().isEmpty());
    }

    @Test
    void testShallowCopyCart() {
        List<Book> initialBooks = Arrays.asList(
                createBook("Book X", "Series A", "Description 1", new HashSet<>(Arrays.asList("Author 1")), new HashSet<>(Arrays.asList("Genre 1"))),
                createBook("Book Y", "Series B", "Description 2", new HashSet<>(Arrays.asList("Author 2")), new HashSet<>(Arrays.asList("Genre 2")))
        );
        cart.setBooks(initialBooks);

        Cart copiedCart = cart.copy("shallow");

        assertSame(cart, copiedCart);
        assertSame(cart.getBooks(), copiedCart.getBooks());
    }

    private Book createBook(String title, String series, String description, Set<String> authors, Set<String> genres) {
        Book book = new Book();
        book.setTitle(title);
        book.setSeries(series);
        book.setDescription(description);
        book.setAuthors(authors);
        book.setGenres(genres);

        return book;
    }
}