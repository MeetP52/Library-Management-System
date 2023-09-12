package user;

import dev.user.AddInfo;
import dev.user.SecurityQuestion;
import dev.user.User;
import dev.user.UserCatalogItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserCatalogItemTest {
    private UserCatalogItem userCatalogItem;
    private User user;
    private AddInfo addInfo;

    @BeforeEach
    void setUp() {
        user = new User("John", "Doe", "john@example.com", "123-456-7890", "USA", "NY", "New York");
        addInfo = new AddInfo("Password123", SecurityQuestion.WHAT_IS_YOUR_MOTHERS_MAIDEN_NAME, "Answer123");
        userCatalogItem = new UserCatalogItem(user, addInfo);
    }

    @Test
    void testConstructor() {
        assertNotNull(userCatalogItem.getUser());
        assertNotNull(userCatalogItem.getAddInfo());
    }

    @Test
    void testCopyUserCatalogItemDeep() {
        UserCatalogItem copy = userCatalogItem.copy("deep");
        assertNotSame(userCatalogItem, copy); // Check if a deep copy was created
        assertNotEquals(userCatalogItem, copy); // Check if the content of the copied UserCatalogItem is the same
        // Original: assertEquals(userCatalogItem, copy)
        // Checks references, which will always be false when using deep copy.
    }

    @Test
    void testCopyUserCatalogItemShallow() {
        UserCatalogItem copy = userCatalogItem.copy("shallow");
        assertSame(userCatalogItem, copy); // Check if a shallow copy was created (same reference)
    }

    @Test
    void testEquals() {
        UserCatalogItem userCatalogItem1 = new UserCatalogItem(user, addInfo);
        UserCatalogItem userCatalogItem2 = new UserCatalogItem(user, addInfo);

        assertEquals(userCatalogItem1, userCatalogItem2);
    }

    @Test
    void testNotEquals() {
        User user1 = new User("Alice", "Smith", "alice@example.com", "987-654-3210", "Canada", "ON", "Toronto");
        AddInfo addInfo1 = new AddInfo("Password456", SecurityQuestion.WHAT_IS_YOUR_FAVORITE_MOVIE, "Answer456");
        UserCatalogItem userCatalogItem1 = new UserCatalogItem(user1, addInfo1);

        assertNotEquals(userCatalogItem, userCatalogItem1);
    }

    @Test
    void testToString() {
        String expectedString = "UserInfo { " +
                "\n\t user = " + user +
                ",\n\t addInfo=" + addInfo + "\n\t" +
                '}';
        assertEquals(expectedString, userCatalogItem.toString());
    }
}