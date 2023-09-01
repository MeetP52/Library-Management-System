package dev.lpa;

import dev.admin.Admin;
import dev.admin.AdminCatalog;
import dev.book.BookCatalog;
import dev.user.*;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello World");
        User user = new User(
                "Some",
                "User",
                "meet@patel.com",
                "3334445555",
                "United States",
                "New York",
                "Elm"
        );
        AddInfo info = new AddInfo(
                "P@ssw0rd123",
                SecurityQuestion.IN_WHAT_CITY_WERE_YOU_BORN,
                "New York City"
                );

        User user2 = new User(
                "Alice",
                "Johnson",
                "alice@example.com",
                "5556667777",
                "United States",
                "California",
                "Los Angeles"
        );
        AddInfo info2 = new AddInfo(
                "Secur3P@ss",
                SecurityQuestion.WHAT_IS_YOUR_FAVORITE_BOOK,
                "The Great Gatsby"
        );

        Admin admin = new Admin(user,info);
        Admin admin2 = new Admin(user2,info2);
        AdminCatalog catalog = AdminCatalog.getAdminCatalog();
        catalog.addAdminUser(admin);
        catalog.addAdminUser(admin2);
        catalog.removeAdminUser(admin2);
        catalog.storeAdminCatalogData();

        UserCatalogItem userInfo = new UserCatalogItem(user,info);
        UserCatalog userCatalog = UserCatalog.getUserCatalog();
        userCatalog.storeUserCatalogData();

        BookCatalog bookCatalog = BookCatalog.getBookCatalog();
        System.out.println(bookCatalog);
        bookCatalog.storeBookCatalogData();

    }
}
