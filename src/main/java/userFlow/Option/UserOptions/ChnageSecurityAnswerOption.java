package userFlow.Option.UserOptions;

import dev.user.UserCatalogItem;
import userFlow.Option.Option;
import userFlow.PageManager;

import java.util.Scanner;

public class ChnageSecurityAnswerOption implements Option {
    String prompt = "Change Security Answer.";

    static ChnageSecurityAnswerOption option;

    private ChnageSecurityAnswerOption() {}

    public static ChnageSecurityAnswerOption getChnageSecurityAnswerOption() {
        return (option == null) ? new ChnageSecurityAnswerOption() : option;
    }
    @Override
    public void execute() {
        PageManager manager = PageManager.getPageManager();
        UserCatalogItem user = manager.getUser();
        System.out.println("What would you like to change your security answer to?");
        System.out.print("> ");
        String newSecurityAnswer = new Scanner(System.in).next();
        user.getAddInfo().setSecurityAnswer(newSecurityAnswer);
        if(!user.getAddInfo().getSecurityAnswer().equals(newSecurityAnswer)) {
            System.out.println("Invalid Security Answer");
        } else {
            System.out.println("Security Answer Changed");
        }
    }

    @Override
    public String toString() {
        return prompt;
    }
}
