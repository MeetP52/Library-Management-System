package userFlow.Option;

import userFlow.PageManager;

public class HomePageOption implements Option {
    String prompt = "Goto Home Page";

    static HomePageOption option;

    private HomePageOption() {}

    public static HomePageOption getHomePageOption() {
        return (option == null) ? new HomePageOption() : option;
    }

    @Override
    public void execute() {
        PageManager manager = PageManager.getPageManager();
        manager.setCurrentPage("Home");
    }

    @Override
    public String toString() {
        return prompt;
    }
}
