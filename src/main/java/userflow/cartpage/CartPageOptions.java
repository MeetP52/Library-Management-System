package userflow.cartpage;

import userflow.PageManager;
import userflow.option.*;
import userflow.Options;
import userflow.option.otheroptions.CheckoutOption;
import userflow.option.otheroptions.LogOutOption;
import userflow.option.pageoption.AdminPageOption;
import userflow.option.pageoption.HomePageOption;
import userflow.option.pageoption.SearchPageOption;
import userflow.option.pageoption.UserProfileOption;

import java.util.HashMap;
import java.util.Map;

public class CartPageOptions implements Options {
    Map<Integer, Option> optionMap = new HashMap<>();

    static CartPageOptions options;

    private CartPageOptions() {
        optionMap.put(0, LogOutOption.getLogOutOption());
        optionMap.put(1, HomePageOption.getHomePageOption());
        optionMap.put(2, SearchPageOption.getSearchPageOption());
        optionMap.put(3, UserProfileOption.gerUserProfileOption());
        optionMap.put(4, CheckoutOption.getCheckOutOption());
        if(PageManager.getPageManager().getIsAdmin()) {
            optionMap.put(5, AdminPageOption.getAdminPageOption());
        }
    }

    public static CartPageOptions getCartPageOptions() {
        return (options == null) ? (options = new CartPageOptions()) : options;
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
