package userFlow.UserProfilePage;

import userFlow.CartPage.CartPageOptions;
import userFlow.Option.*;
import userFlow.Option.UserOptions.*;
import userFlow.Options;

import java.util.HashMap;
import java.util.Map;

public class UserPageOptions implements Options {

    Map<Integer,Option> userPageOptions = new HashMap<>();

    static UserPageOptions options;

    private UserPageOptions() {
        userPageOptions.put(0, LogOutOption.getLogOutOption());
        userPageOptions.put(1, HomePageOption.getHomePageOption());
        userPageOptions.put(2, SearchPageOption.getSearchPageOption());
        userPageOptions.put(3, CheckoutOption.getCheckOutOption());
        userPageOptions.put(4, ChangeFirstNameOption.getChangeFirstNameOption());
        userPageOptions.put(5, ChangeLastNameOption.getChangeLastNameOption());
        userPageOptions.put(6, ChangeEmailAddressOption.getChangeEmailAddressOption());
        userPageOptions.put(7, ChnageSecurityAnswerOption.getChnageSecurityAnswerOption());
        userPageOptions.put(8, ChnageSecurityAnswerOption.getChnageSecurityAnswerOption());
        userPageOptions.put(9, ChangePasswordOption.getChangePasswordOption());
    }

    public static UserPageOptions getUserPageOptions() {
        return (options == null) ? new UserPageOptions() : options;
    }
    @Override
    public void execute() {

    }

    @Override
    public void display() {
        userPageOptions.forEach((k,v) -> {
            System.out.println("Press " + k + " : " + v);
        });
    }

    @Override
    public Map<Integer, Option> getOptions() {
        return userPageOptions;
    }
}
