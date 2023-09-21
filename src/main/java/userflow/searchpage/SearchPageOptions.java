package userflow.searchpage;

import userflow.PageManager;
import userflow.option.*;
import userflow.Options;
import userflow.option.otheroptions.LogOutOption;
import userflow.option.otheroptions.SearchOption;
import userflow.option.pageoption.AdminPageOption;
import userflow.option.pageoption.CartPageOption;
import userflow.option.pageoption.HomePageOption;
import userflow.option.pageoption.UserProfileOption;

import java.util.HashMap;
import java.util.Map;

public class SearchPageOptions implements Options {

    Map<Integer, Option> optionMap = new HashMap<>();
    static SearchPageOptions options;

    private SearchPageOptions() {
        optionMap.put(0, LogOutOption.getLogOutOption());
        optionMap.put(1, HomePageOption.getHomePageOption());
        optionMap.put(2, UserProfileOption.gerUserProfileOption());
        optionMap.put(3, CartPageOption.getCartPageOption());
        optionMap.put(4, SearchOption.getSearchOption());
        if(PageManager.getPageManager().getIsAdmin()) {
            optionMap.put(5, AdminPageOption.getAdminPageOption());
        }
    }

    public static SearchPageOptions getSearchPageOptions() {
        return (options == null) ? (options = new SearchPageOptions()) : options;
    }

    @Override
    public void display() {
        optionMap.forEach((k, v) -> System.out.println("Press " + k + " : " + v));
    }

    @Override
    public Map<Integer, Option> getOptions() {
        return optionMap;
    }
}
