package userFlow.Option.UserOptions;

import dev.user.UserCatalogItem;
import userFlow.Option.Option;
import userFlow.PageManager;

import java.util.Scanner;

public class ChangePasswordOption implements Option {

    String prompt = "Change Password.";

    static ChangePasswordOption option;

    private ChangePasswordOption() {};

    public static ChangePasswordOption getChangePasswordOption() {
        return (option == null) ? new ChangePasswordOption() : option;
    }
    @Override
    public void execute() {
        PageManager manager = PageManager.getPageManager();
        UserCatalogItem user = manager.getUser();
        System.out.println("What would you like to change your password to?");
        String newPassword = new Scanner(System.in).next();
        user.getAddInfo().setPassword(newPassword);
        if(!user.getUser().getEmailAddress().equals(newPassword)) {
            System.out.println("Invalid Password");
        } else {
            System.out.println("Password Changed");
        }
    }
}
