package userflow.option.otheroptions;

import dev.admin.AdminCatalog;
import dev.book.BookCatalog;
import dev.user.UserCatalog;
import userflow.option.Option;

public class LogOutOption implements Option {

    String prompt = "LogOut";

    static LogOutOption option;
    BookCatalog bookCatalog = BookCatalog.getBookCatalog();
    UserCatalog userCatalog = UserCatalog.getUserCatalog();

    AdminCatalog adminCatalog = AdminCatalog.getAdminCatalog();

    private LogOutOption() {}

    public static LogOutOption getLogOutOption() {
        return (option == null) ? (option = new LogOutOption()) : option;
    }

    @Override
    public void execute() {
        bookCatalog.storeBookCatalogData();
        userCatalog.storeUserCatalogData();
        adminCatalog.storeAdminCatalogData();
        System.exit(0);
    }

    @Override
    public String toString() {
        return prompt;
    }
}
