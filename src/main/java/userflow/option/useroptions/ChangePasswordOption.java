package userflow.option.useroptions;

import dev.user.UserCatalogItem;
import userflow.option.Option;
import userflow.PageManager;

import java.util.Scanner;

public class ChangePasswordOption implements Option {

    String prompt = "Change Password.";

    private static ChangePasswordOption option;

    private ChangePasswordOption() {}

    public static ChangePasswordOption getChangePasswordOption() {
        return (option == null) ? new ChangePasswordOption() : option;
    }
    @Override
    public void execute() {
        PageManager manager = PageManager.getPageManager();
        UserCatalogItem user = manager.getUser();
        System.out.println("What would you like to change your password to?");
        System.out.print("> ");
        String newPassword = new Scanner(System.in).next();
        user.getAddInfo().setPassword(newPassword);
        if(!user.getUser().getEmailAddress().equals(newPassword)) {
            System.out.println("Invalid Password");
        } else {
            System.out.println("Password Changed");
        }
    }

    @Override
    public String toString() {
        return prompt;
    }
}
