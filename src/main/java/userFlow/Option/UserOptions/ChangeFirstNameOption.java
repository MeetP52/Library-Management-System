package userFlow.Option.UserOptions;

import dev.user.UserCatalogItem;
import userFlow.Option.Option;
import userFlow.PageManager;

import java.util.Scanner;

public class ChangeFirstNameOption implements Option {

    String prompt = "Change First Name.";

    static ChangeFirstNameOption option;

    private ChangeFirstNameOption() {}

    public static ChangeFirstNameOption getChangeFirstNameOption() {
        return (option == null) ? new ChangeFirstNameOption() : option;
    }
    @Override
    public void execute() {
        PageManager manager = PageManager.getPageManager();
        UserCatalogItem user = manager.getUser();
        System.out.println("What would you like to change your first name to?");
        System.out.print("> ");
        String newName = new Scanner(System.in).next();
        user.getUser().setFirstName(newName);
        System.out.println("New First Name: " + user.getUser().getFirstName());
    }

    @Override
    public String toString() {
        return prompt;
    }
}
