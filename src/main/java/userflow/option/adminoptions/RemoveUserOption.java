package userflow.option.adminoptions;

import dev.user.UserCatalog;
import dev.user.UserCatalogItem;
import userflow.option.Option;

import java.util.Scanner;

public class RemoveUserOption implements Option {
    String prompt = "Remove User";

    private static RemoveUserOption option;

    private RemoveUserOption() {}

    public static RemoveUserOption getRemoveUserOption() {
        return (option == null) ? (option = new RemoveUserOption()) : option;
    }
    @Override
    public void execute() {
        UserCatalog catalog = UserCatalog.getUserCatalog();
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Do you have a specific user in mind?");
        System.out.print("> ");
        input = scanner.nextLine();
        if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
            System.out.println("Please give the email address of this user. Press 0 to quit.");
            System.out.print("> ");
            input = scanner.nextLine();
            if(catalog.findUser(input.hashCode()) == null) {
                System.out.println("User not found.");
                do {
                    System.out.println("Try Again.");
                    System.out.print("> ");
                    input = scanner.nextLine();
                    if(input.equalsIgnoreCase("0")) {
                        return;
                    }
                } while (catalog.findUser(input.hashCode()) == null);
                if(catalog.findUser(input.hashCode()) != null) {
                    System.out.println("User Found.");
                    if (catalog.removeUser(catalog.findUser(input.hashCode()))) {
                        System.out.println("User Removed.");
                    } else {
                        System.out.println("Issue removing user.");
                    }
                }
            }
        } else {
            System.out.println("Here is a list of all the users in the system.");
            for (UserCatalogItem user : catalog.getUsersList()) {
                System.out.println(user.getUser().getEmailAddress());
            }
            System.out.println("Enter the email address of the person you would like to remove from the system. Press 0 to quit.");
            System.out.print("> ");
            input = scanner.nextLine();
            if (catalog.findUser(input.hashCode()) != null) {
                System.out.println("User Found.");
                if (catalog.removeUser(catalog.findUser(input.hashCode()))) {
                    System.out.println("User Removed.");
                } else {
                    System.out.println("Error in removing user.");
                }
            }
        }
    }

    @Override
    public String toString() {
        return prompt;
    }
}
