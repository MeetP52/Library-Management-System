package userFlow.CartPage;

import userFlow.Option.*;
import userFlow.Options;

import java.util.HashMap;
import java.util.Map;

public class CartPageOptions implements Options {
    Map<Integer, Option> cartPageOptions = new HashMap<>();

    static CartPageOptions options;

    private CartPageOptions() {
        cartPageOptions.put(0, LogOutOption.getLogOutOption());
        cartPageOptions.put(1, HomePageOption.getHomePageOption());
        cartPageOptions.put(2, SearchPageOption.getSearchPageOption());
        cartPageOptions.put(3, UserProfileOption.gerUserProfileOption());
        cartPageOptions.put(4, CheckoutOption.getCheckOutOption());
    }

    public static CartPageOptions getCartPageOptions() {
        return (options == null) ? new CartPageOptions() : options;
    }


    @Override
    public void execute() {

    }

    @Override
    public void display() {
        cartPageOptions.forEach((k,v) -> {
            System.out.println("Press " + k + " : " + v);
        });
    }

    @Override
    public Map<Integer, Option> getOptions() {
        return cartPageOptions;
    }
}
