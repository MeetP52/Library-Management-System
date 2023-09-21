package userflow.adminpage;

import userflow.Options;
import userflow.option.*;
import userflow.option.adminoptions.AddNewBookOption;
import userflow.option.adminoptions.RemoveBookOption;
import userflow.option.adminoptions.RemoveUserOption;
import userflow.option.adminoptions.RemoveUserStrikesOption;
import userflow.option.otheroptions.LogOutOption;
import userflow.option.pageoption.CartPageOption;
import userflow.option.pageoption.HomePageOption;
import userflow.option.pageoption.SearchPageOption;
import userflow.option.pageoption.UserProfileOption;

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
        optionMap.put(5, AddNewBookOption.getAddNewBookOption());
        optionMap.put(6, RemoveBookOption.getRemoveBookOption());
        optionMap.put(7, RemoveUserOption.getRemoveUserOption());
        optionMap.put(8, RemoveUserStrikesOption.getRemoveUserStrikesOption());
    }

    public static AdminPageOptions getAdminPageOptions() {
        return (options == null) ? (options = new AdminPageOptions()) : options;
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
