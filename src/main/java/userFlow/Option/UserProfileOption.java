package userFlow.Option;

import dev.user.User;
import userFlow.PageManager;

public class UserProfileOption implements Option {

    String prompt = "Goto User Profile Page";

    static UserProfileOption option;

    private UserProfileOption() {}

    public static UserProfileOption gerUserProfileOption() {
        return (option == null) ? new UserProfileOption() : option;
    }

    @Override
    public void execute() {
        PageManager manager = PageManager.getPageManager();
        manager.setCurrentPage("User");
    }

    @Override
    public String toString() {
        return prompt;
    }
}
