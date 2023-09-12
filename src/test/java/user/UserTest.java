package user;

import dev.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("John", "Doe", "johndoe@example.com", "123-456-7890", "Country", "State", "City");
    }

    @Test
    void testConstructor() {
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("johndoe@example.com", user.getEmailAddress());
        assertEquals("123-456-7890", user.getPhoneNumber());
        assertEquals("Country", user.getCountry());
        assertEquals("State", user.getState());
        assertEquals("City", user.getCity());
    }

    @Test
    void testSetEmailAddress() {
        user.setEmailAddress("newemail@example.com");
        assertEquals("newemail@example.com", user.getEmailAddress());
    }

    @Test
    void testSetInvalidEmailAddress() {
        // Attempt to set an invalid email address
        user.setEmailAddress("invalid_email");
        // Make sure the email address remains unchanged
        assertEquals("johndoe@example.com", user.getEmailAddress());
    }

    @Test
    void testSetPhoneNumber() {
        user.setPhoneNumber("(987) 654-3210");
        assertEquals("(987) 654-3210", user.getPhoneNumber());
    }

    @Test
    void testSetInvalidPhoneNumber() {
        // Attempt to set an invalid phone number
        user.setPhoneNumber("invalid_phone");
        // Make sure the phone number remains unchanged
        assertEquals("123-456-7890", user.getPhoneNumber());
    }

    @Test
    void testCopyUser() {
        User copy = user.copy("deep");
        assertNotSame(user, copy); // Check if a deep copy was created
        assertEquals(user, copy); // Check if the content of the copied user is the same
    }

    @Test
    void testCopyUserShallow() {
        User copy = user.copy("shallow");
        assertSame(user, copy); // Check if a shallow copy was created (same reference)
    }
}