package userFlow.Option.UserOptions;

import dev.user.SecurityQuestion;
import dev.user.UserCatalogItem;
import userFlow.Option.Option;
import userFlow.PageManager;

import java.util.Scanner;

public class ChangeSecurityQuestionOption implements Option {
    String prompt = "Change Security Question.";

    static ChangeSecurityQuestionOption option;

    private ChangeSecurityQuestionOption() {};

    public static ChangeSecurityQuestionOption getChangeSecurityQuestionOption() {
        return (option == null) ? new ChangeSecurityQuestionOption() : option;
    }
    @Override
    public void execute() {
        PageManager manager = PageManager.getPageManager();
        UserCatalogItem user = manager.getUser();
        System.out.println("What would you like to change your security question to?");
        System.out.println("Here are your options: ");
        int index = 0;
        SecurityQuestion[] questions = SecurityQuestion.values();
        for(SecurityQuestion q : questions) {
            index++;
            System.out.println(index + ": " + q);

        }
        System.out.print("> ");
        index = new Scanner(System.in).nextInt();
        if(index <= SecurityQuestion.values().length) {
            user.getAddInfo().setSecurityQuestion(questions[index-1]);
            System.out.println("Security Question changed successfully.");
        }
        System.out.println("Would you like to give a new answer as well?");
        String input = new Scanner(System.in).next();
        if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
            ChnageSecurityAnswerOption option = ChnageSecurityAnswerOption.getChnageSecurityAnswerOption();
            option.execute();
        }
    }
}
