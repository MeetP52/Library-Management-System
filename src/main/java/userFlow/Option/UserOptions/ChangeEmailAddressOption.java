package userFlow.Option.UserOptions;

import dev.user.UserCatalogItem;
import userFlow.Option.Option;
import userFlow.PageManager;

import java.util.Scanner;

public class ChangeEmailAddressOption implements Option {
    String prompt = "Change Email Address.";

    static ChangeEmailAddressOption option;

    private ChangeEmailAddressOption() {};

    public static ChangeEmailAddressOption getChangeEmailAddressOption() {
        return (option == null) ? new ChangeEmailAddressOption() : option;
    }
    @Override
    public void execute() {
        PageManager manager = PageManager.getPageManager();
        UserCatalogItem user = manager.getUser();
        System.out.println("What would you like to change your email address to?");
        String newEmailAddress = new Scanner(System.in).next();
        String oldEmailAddress = user.getUser().getEmailAddress();
        user.getUser().setEmailAddress(newEmailAddress);
        if(!user.getUser().getEmailAddress().equals(newEmailAddress)) {
            System.out.println("Invalid Email Address");
            user.getUser().setEmailAddress(oldEmailAddress);
        } else {
            System.out.println("New Email Address: " + user.getUser().getEmailAddress());
        }
    }
}
