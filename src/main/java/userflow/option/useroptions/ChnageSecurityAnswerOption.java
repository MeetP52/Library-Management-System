package userflow.option.useroptions;

import dev.user.UserCatalogItem;
import userflow.option.Option;
import userflow.PageManager;

import java.util.Scanner;

public class ChnageSecurityAnswerOption implements Option {
    String prompt = "Change Security Answer.";

    private static ChnageSecurityAnswerOption option;

    private ChnageSecurityAnswerOption() {}

    public static ChnageSecurityAnswerOption getChnageSecurityAnswerOption() {
        return (option == null) ? (option = new ChnageSecurityAnswerOption()) : option;
    }
    @Override
    public void execute() {
        PageManager manager = PageManager.getPageManager();
        UserCatalogItem user = manager.getUser();
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to change your security answer to?");
        System.out.print("> ");
        String oldSecurityAnswer = user.getAddInfo().getSecurityAnswer();
        String newSecurityAnswer = scanner.next();
        user.getAddInfo().setSecurityAnswer(newSecurityAnswer);
        while (!user.getAddInfo().getSecurityAnswer().equals(newSecurityAnswer)) {
            System.out.println("Try Again. Press 0 to exit.");
            System.out.print("> ");
            newSecurityAnswer = scanner.nextLine();
            if(newSecurityAnswer.equalsIgnoreCase("0")) {
                user.getAddInfo().setSecurityAnswer(oldSecurityAnswer);
                break;
            }
            user.getAddInfo().setSecurityAnswer(newSecurityAnswer);
        }
    }

    @Override
    public String toString() {
        return prompt;
    }
}
