package userflow.adminpage;

import userflow.Options;
import userflow.option.*;
import userflow.option.adminoptions.AddNewBookOption;
import userflow.option.adminoptions.RemoveBookOption;
import userflow.option.adminoptions.RemoveUserStrikesOption;

import java.util.HashMap;
import java.util.Map;

public class AdminPageOptions implements Options {
    Map<Integer,Option> optionMap = new HashMap<>();

    private static AdminPageOptions options;

    private AdminPageOptions() {
        optionMap.put(0, LogOutOption.getLogOutOption());
        optionMap.put(1, HomePageOption.getHomePageOption());
        optionMap.put(2, SearchPageOption.getSearchPageOption());
        optionMap.put(3, CartPageOption.getCartPageOption());
        optionMap.put(4, UserProfileOption.gerUserProfileOption());
        optionMap.put(5, RemoveUserStrikesOption.getRemoveUserStrikesOption());
        optionMap.put(6, AddNewBookOption.getAddNewBookOption());
        optionMap.put(7, RemoveBookOption.getRemoveBookOption());
    }

    public static AdminPageOptions getAdminPageOptions() {
        return (options == null) ? new AdminPageOptions() : options;
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
