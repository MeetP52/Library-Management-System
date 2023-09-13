package userFlow.Option.UserOptions;

import dev.user.UserCatalogItem;
import userFlow.Option.Option;
import userFlow.PageManager;

import java.util.Scanner;

public class ChangeLastNameOption implements Option {
    String prompt = "Change Last Name.";

    static ChangeLastNameOption option;

    private ChangeLastNameOption() {}

    public static ChangeLastNameOption getChangeLastNameOption() {
        return (option == null) ? new ChangeLastNameOption() : option;
    }
    @Override
    public void execute() {
        PageManager manager = PageManager.getPageManager();
        UserCatalogItem user = manager.getUser();
        System.out.println("What would you like to change your last name to?");
        System.out.print("> ");
        String newName = new Scanner(System.in).next();
        user.getUser().setLastName(newName);
        System.out.println("New Last Name: " + user.getUser().getLastName());
    }

    @Override
    public String toString() {
        return prompt;
    }
}
