package userFlow.SearchPage;

import userFlow.Option.*;
import userFlow.Options;

import java.util.HashMap;
import java.util.Map;

public class SearchPageOptions implements Options {

    Map<Integer, Option> searchPageOptions = new HashMap<>();
    static SearchPageOptions options;

    private SearchPageOptions() {
        searchPageOptions.put(0, LogOutOption.getLogOutOption());
        searchPageOptions.put(1, HomePageOption.getHomePageOption());
        searchPageOptions.put(2, UserProfileOption.gerUserProfileOption());
        searchPageOptions.put(3, CartPageOption.getCartPageOption());
        searchPageOptions.put(4, SearchOption.getSearchOption());
    }

    public static SearchPageOptions getSearchPageOptions() {
        return (options == null) ? new SearchPageOptions() : options;
    }

    @Override
    public void execute() {

    }

    @Override
    public void display() {
        searchPageOptions.forEach((k,v) -> {
            System.out.println("Press " + k + " : " + v);
        });
    }

    @Override
    public Map<Integer, Option> getOptions() {
        return searchPageOptions;
    }
}
