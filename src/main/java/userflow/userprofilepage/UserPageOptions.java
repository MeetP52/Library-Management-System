package userflow.userprofilepage;

import userflow.PageManager;
import userflow.option.*;
import userflow.option.otheroptions.CheckoutOption;
import userflow.option.otheroptions.LogOutOption;
import userflow.option.pageoption.AdminPageOption;
import userflow.option.pageoption.HomePageOption;
import userflow.option.pageoption.SearchPageOption;
import userflow.option.useroptions.*;
import userflow.Options;

import java.util.HashMap;
import java.util.Map;

public class UserPageOptions implements Options {

    Map<Integer,Option> optionMap = new HashMap<>();

    static UserPageOptions options;

    private UserPageOptions() {
        optionMap.put(0, LogOutOption.getLogOutOption());
        optionMap.put(1, HomePageOption.getHomePageOption());
        optionMap.put(2, SearchPageOption.getSearchPageOption());
        optionMap.put(3, CheckoutOption.getCheckOutOption());
        optionMap.put(4, ChangeFirstNameOption.getChangeFirstNameOption());
        optionMap.put(5, ChangeLastNameOption.getChangeLastNameOption());
        optionMap.put(6, ChangeEmailAddressOption.getChangeEmailAddressOption());
        optionMap.put(7, ChnageSecurityAnswerOption.getChnageSecurityAnswerOption());
        optionMap.put(8, ChangeSecurityQuestionOption.getChangeSecurityQuestionOption());
        optionMap.put(9, ChangePasswordOption.getChangePasswordOption());
        if(PageManager.getPageManager().getIsAdmin()) {
            optionMap.put(10, AdminPageOption.getAdminPageOption());
        }
    }

    public static UserPageOptions getUserPageOptions() {
        return (options == null) ? (options = new UserPageOptions()) : options;
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
