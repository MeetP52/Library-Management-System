package userflow.option.adminoptions;

import dev.user.UserCatalog;
import dev.user.UserCatalogItem;
import userflow.option.Option;

import java.util.List;
import java.util.Scanner;


public class RemoveUserStrikesOption implements Option {
    String prompt = "Remove Strikes";

    private static RemoveUserStrikesOption option;

    private RemoveUserStrikesOption() {}

    public static RemoveUserStrikesOption getRemoveUserStrikesOption() {
        return (option == null) ? ( option = new RemoveUserStrikesOption()) : option;
    }
    @Override
    public void execute() {
        UserCatalog catalog = UserCatalog.getUserCatalog();
        List<UserCatalogItem> users = catalog.getUsersList();
        Scanner scanner = new Scanner(System.in);
        users.forEach(user -> {
            if(user.getAddInfo().getStrikes() == 3) {
                System.out.println(user.getUser().getEmailAddress() + ": " + user.getAddInfo().getStrikes());
                System.out.print("> ");
                String input = scanner.nextLine();
                if(input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
                    user.getAddInfo().setStrikes(0);
                    System.out.println("Strikes set to 0");
                }
            }
        });
    }

    @Override
    public String toString() {
        return prompt;
    }
}
