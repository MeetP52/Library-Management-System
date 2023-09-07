package admin;

import dev.admin.Admin;
import dev.admin.AdminCatalog;
import dev.user.AddInfo;
import dev.user.SecurityQuestion;
import dev.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminCatalogTest {
    private AdminCatalog adminCatalog;

    @BeforeEach
    void setUp() {
        adminCatalog = AdminCatalog.getAdminCatalog();
    }

    @Test
    void testAddAdminUser() {
        // Create a test admin user
        User adminUser = new User("Admin", "User", "admin@example.com", "123-456-7890", "USA", "NY", "New York");
        AddInfo adminInfo = new AddInfo("AdminPassword123", SecurityQuestion.WHAT_IS_YOUR_MOTHERS_MAIDEN_NAME, "AdminAnswer123");
        Admin admin = new Admin(adminUser, adminInfo);

        // Add the admin user to the catalog
        assertTrue(adminCatalog.addAdminUser(admin));

        // Check if the admin user is in the catalog
        Admin retrievedAdmin = adminCatalog.findAdmin(admin);
        assertNotNull(retrievedAdmin);
        assertEquals(admin, retrievedAdmin);
        assertTrue(adminCatalog.removeAdminUser(admin));
    }

    @Test
    void testRemoveAdminUser() {
        // Create a test admin user
        User adminUser = new User("Admin", "User", "admin@example.com", "123-456-7890", "USA", "NY", "New York");
        AddInfo adminInfo = new AddInfo("AdminPassword123", SecurityQuestion.WHAT_IS_YOUR_MOTHERS_MAIDEN_NAME, "AdminAnswer123");
        Admin admin = new Admin(adminUser, adminInfo);

        // Add the admin user to the catalog
        assertTrue(adminCatalog.addAdminUser(admin));

        // Remove the admin user from the catalog
        assertTrue(adminCatalog.removeAdminUser(admin));

        // Check if the admin user is removed
        assertNull(adminCatalog.findAdmin(admin));
    }
}