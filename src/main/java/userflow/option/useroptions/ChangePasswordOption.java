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
        return (option == null) ? ( option = new ChangePasswordOption()) : option;
    }
    @Override
    public void execute() {
        PageManager manager = PageManager.getPageManager();
        UserCatalogItem user = manager.getUser();
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to change your password to?");
        System.out.print("> ");
        String oldPassword = user.getAddInfo().getPassword();
        String newPassword = scanner.nextLine();
        user.getAddInfo().setPassword(newPassword);
        while(!user.getAddInfo().getPassword().equals(newPassword)) {
            System.out.println("Try Again. Press 0 to exit.");
            System.out.print("> ");
            newPassword = scanner.nextLine();
            if(newPassword.equalsIgnoreCase("0")) {
                user.getAddInfo().setPassword(oldPassword);
                break;
            }
            user.getAddInfo().setPassword(newPassword);
        }
    }

    @Override
    public String toString() {
        return prompt;
    }
}
