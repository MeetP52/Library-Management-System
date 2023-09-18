package userflow.cartpage;

import userflow.option.*;
import userflow.Options;

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
    }

    public static CartPageOptions getCartPageOptions() {
        return (options == null) ? new CartPageOptions() : options;
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
