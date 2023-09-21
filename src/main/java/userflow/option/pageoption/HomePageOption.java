package userflow.option.pageoption;

import userflow.PageManager;
import userflow.option.Option;

public class HomePageOption implements Option {
    String prompt = "Goto Home Page";

    private static HomePageOption option;

    private HomePageOption() {}

    public static HomePageOption getHomePageOption() {
        return (option == null) ? (option = new HomePageOption()) : option;
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
