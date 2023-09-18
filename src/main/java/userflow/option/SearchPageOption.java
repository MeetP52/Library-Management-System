package userflow.option;

import userflow.PageManager;

public class SearchPageOption implements Option {

    String prompt = "Goto Search Page";
    private static SearchPageOption option;

    private SearchPageOption() {}

    public static SearchPageOption getSearchPageOption() {
        return (option == null) ? new SearchPageOption() : option;
    }
    @Override
    public void execute() {
        PageManager.setCurrentPage("Search");
    }

    @Override
    public String toString() {
        return  prompt;
    }
}
