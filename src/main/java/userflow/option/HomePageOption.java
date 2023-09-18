package userflow.option;

import userflow.PageManager;

public class HomePageOption implements Option {
    String prompt = "Goto Home Page";

    private static HomePageOption option;

    private HomePageOption() {}

    public static HomePageOption getHomePageOption() {
        return (option == null) ? new HomePageOption() : option;
    }

    @Override
    public void execute() {
        PageManager.setCurrentPage("Home");
    }

    @Override
    public String toString() {
        return prompt;
    }
}
