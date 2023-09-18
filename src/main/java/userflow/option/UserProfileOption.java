package userflow.option;

import userflow.PageManager;

public class UserProfileOption implements Option {

    String prompt = "Goto User Profile Page";

    private static UserProfileOption option;

    private UserProfileOption() {}

    public static UserProfileOption gerUserProfileOption() {
        return (option == null) ? new UserProfileOption() : option;
    }

    @Override
    public void execute() {
        PageManager.setCurrentPage("User");
    }

    @Override
    public String toString() {
        return prompt;
    }
}
