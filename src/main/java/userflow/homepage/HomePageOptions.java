package userflow.homepage;

import userflow.PageManager;
import userflow.option.*;
import userflow.Options;
import userflow.option.otheroptions.LogOutOption;
import userflow.option.otheroptions.ReturnBookOption;
import userflow.option.pageoption.AdminPageOption;
import userflow.option.pageoption.CartPageOption;
import userflow.option.pageoption.SearchPageOption;
import userflow.option.pageoption.UserProfileOption;

import java.util.HashMap;
import java.util.Map;

public class HomePageOptions implements Options {

    Map<Integer, Option> optionMap = new HashMap<>();

    static HomePageOptions options;

    private HomePageOptions() {
        optionMap.put(0, LogOutOption.getLogOutOption());
        optionMap.put(1, SearchPageOption.getSearchPageOption());
        optionMap.put(2, UserProfileOption.gerUserProfileOption());
        optionMap.put(3, CartPageOption.getCartPageOption());
        optionMap.put(4, ReturnBookOption.getReturnBookOption());
        if(PageManager.getPageManager().getIsAdmin()) {
            optionMap.put(5, AdminPageOption.getAdminPageOption());
        }

    }

    public static HomePageOptions getHomePageOptions() {
        return (options == null) ? (options = new HomePageOptions()) : options;
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
