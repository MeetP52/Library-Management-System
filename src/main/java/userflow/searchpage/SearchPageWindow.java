package userflow.searchpage;

import dev.user.UserCatalogItem;
import userflow.Window;

public class SearchPageWindow implements Window {

    private static SearchPageWindow window;
    private SearchPageWindow() {}

    public static SearchPageWindow getSearchPageWindow() {
        return (window == null) ? (window = new SearchPageWindow()) : window;
    }

    @Override
    public void display(UserCatalogItem user) {
        System.out.println("Welcome to the search engine page.");
    }
}
