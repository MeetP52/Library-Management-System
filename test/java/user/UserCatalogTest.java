package user;

import dev.user.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserCatalogTest {
    private UserCatalog userCatalog;

    @BeforeEach
    public void setUp() {
        // Initialize a new UserCatalog before each test
        userCatalog = UserCatalog.getUserCatalog();
    }

    @Test
    public void testAddUser() {
        // Create a new user
        User user = new User("John", "Doe", "john2@example.com", "123-456-7890", "USA", "CA", "Los Angeles");
        AddInfo addInfo = new AddInfo("Password123", SecurityQuestion.WHAT_IS_YOUR_MOTHERS_MAIDEN_NAME, "Answer123");
        UserCatalogItem userCatalogItem = new UserCatalogItem(user, addInfo);

        // Add the user to the catalog
        assertTrue(userCatalog.addUser(userCatalogItem));

        // Try adding the same user again (should return false)
        assertFalse(userCatalog.addUser(userCatalogItem));
    }

    @Test
    public void testRemoveUser() {
        // Create a new user
        User user = new User("Alice", "Smith", "alice2@example.com", "123-456-7890", "USA", "NY", "New York");
        AddInfo addInfo = new AddInfo("Password123", SecurityQuestion.WHAT_IS_YOUR_MOTHERS_MAIDEN_NAME, "Answer123");
        UserCatalogItem userCatalogItem = new UserCatalogItem(user, addInfo);

        // Add the user to the catalog
        assertTrue(userCatalog.addUser(userCatalogItem));

        // Remove the user from the catalog
        assertTrue(userCatalog.removeUser(userCatalogItem));

        // Try removing the same user again (should return false)
        assertFalse(userCatalog.removeUser(userCatalogItem));
    }


    @Test
    public void testFindUser() {
        // Create a new user
        User user = new User("Bob", "Johnson", "bob@example.com", "987-654-3210", "Canada", "ON", "Toronto");
        AddInfo addInfo = new AddInfo("Password456", SecurityQuestion.WHAT_IS_YOUR_FAVORITE_MOVIE, "Answer456");
        UserCatalogItem userCatalogItem = new UserCatalogItem(user, addInfo);

        // Add the user to the catalog
        assertTrue(userCatalog.addUser(userCatalogItem));

        // Find the user by email address
        UserCatalogItem foundUser = userCatalog.findUser(user);
        assertNotNull(foundUser);
        assertEquals(user.getEmailAddress(), foundUser.getUser().getEmailAddress());

        // Find the user by user ID
        foundUser = userCatalog.findUser(userCatalogItem.hashCode());
        assertNotNull(foundUser);
        assertEquals(user.getEmailAddress(), foundUser.getUser().getEmailAddress());
    }

    @Test
    public void testFindNonExistentUser() {
        // Create a new user
        User user = new User("Eve", "Brown", "eve@example.com", "555-555-5555", "UK", "England", "London");
        AddInfo addInfo = new AddInfo("Password789", SecurityQuestion.WHAT_IS_YOUR_FAVORITE_BOOK, "Answer789");
        UserCatalogItem userCatalogItem = new UserCatalogItem(user, addInfo);

        // Try to find a user that doesn't exist
        UserCatalogItem foundUser = userCatalog.findUser(user);
        assertNull(foundUser);

        foundUser = userCatalog.findUser(userCatalogItem.hashCode());
        assertNull(foundUser);
    }

}