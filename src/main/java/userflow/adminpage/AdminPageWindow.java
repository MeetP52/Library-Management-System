package userflow.adminpage;

import dev.user.UserCatalogItem;
import userflow.Window;

public class AdminPageWindow implements Window {

    private static AdminPageWindow window;
    private AdminPageWindow() {}
    public static AdminPageWindow getAdminPageWindow() {
        return (window == null) ? (window = new AdminPageWindow()) : window;
    }
    @Override
    public void display(UserCatalogItem user) {
        System.out.println("Welcome to the admin page.");
    }
}
