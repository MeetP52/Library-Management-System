package userFlow.SearchPage;

import dev.user.UserCatalogItem;
import userFlow.Window;

public class SearchPageWindow implements Window {

    static SearchPageWindow window;
    private SearchPageWindow() {}

    public static SearchPageWindow getSearchPageWindow() {
        return (window == null) ? new SearchPageWindow() : window;
    }

    @Override
    public void display(UserCatalogItem user) {
        System.out.println("Welcome to the search engine page.");
    }
}
