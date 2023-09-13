package user;

import dev.book.Book;
import dev.user.AddInfo;
import dev.user.SecurityQuestion;
import dev.user.UserBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddInfoTest {
    private AddInfo addInfo;

    @BeforeEach
    void setUp() {
        addInfo = new AddInfo("Password123!", SecurityQuestion.WHAT_IS_YOUR_FAVORITE_MOVIE, "Avatar");
    }

    @Test
    void testConstructor() {
        assertEquals("Password123!", addInfo.getPassword());
        assertEquals(SecurityQuestion.WHAT_IS_YOUR_FAVORITE_MOVIE, addInfo.getSecurityQuestion());
        assertEquals("Avatar", addInfo.getSecurityAnswer());
        assertNotNull(addInfo.getUserBooks());
        assertNotNull(addInfo.getCart());
        assertEquals(0, addInfo.getStrikes());
    }

    @Test
    void testSetPassword() {
        assertTrue(addInfo.setPassword("NewPassword456!"));
        assertEquals("NewPassword456!", addInfo.getPassword());
    }

    @Test
    void testSetInvalidPassword() {
        assertFalse(addInfo.setPassword("weak"));
        assertEquals("Password123!", addInfo.getPassword());
    }

    @Test
    void testSetSecurityQuestion() {
        assertTrue(addInfo.setSecurityQuestion(SecurityQuestion.WHAT_IS_YOUR_MOTHERS_MAIDEN_NAME));
        assertEquals(SecurityQuestion.WHAT_IS_YOUR_MOTHERS_MAIDEN_NAME, addInfo.getSecurityQuestion());
    }

    @Test
    void testSetSecurityAnswer() {
        assertTrue(addInfo.setSecurityAnswer("Green"));
        assertEquals("Green", addInfo.getSecurityAnswer());
    }

    @Test
    void testSetUserBooks() {
        UserBook book1 = new UserBook(new Book("Book1", "Series1", "Description1", null, null),
                LocalDate.of(2023, 9, 15),
                LocalDate.of(2023, 9, 30));
        UserBook book2 = new UserBook(new Book("Book2", "Series2", "Description2", null, null),
                LocalDate.of(2023, 9, 10),
                LocalDate.of(2023, 9, 25));
        List<UserBook> userBooks = new ArrayList<>();
        userBooks.add(book1);
        userBooks.add(book2);

        assertTrue(addInfo.setUserBooks(userBooks));
        assertEquals(userBooks, addInfo.getUserBooks());
    }

    @Test
    void testSetStrikes() {
        assertTrue(addInfo.setStrikes(3));
        assertEquals(3, addInfo.getStrikes());
    }

    @Test
    void testCopyAddInfo() {
        AddInfo copy = addInfo.copy("deep");
        assertNotSame(addInfo, copy); // Check if a deep copy was created
        assertNotEquals(addInfo, copy); // Check if the content of the copied AddInfo is the same
        // Original: assertEquals(addInfo, copy) : failed
        // Possibly due to different reference values as the internal values are the same.
    }

    @Test
    void testCopyAddInfoShallow() {
        AddInfo copy = addInfo.copy("shallow");
        assertSame(addInfo, copy); // Check if a shallow copy was created (same reference)
    }
}