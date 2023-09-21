package userflow.option.pageoption;

import userflow.PageManager;
import userflow.option.Option;

public class SearchPageOption implements Option {

    String prompt = "Goto Search Page";
    private static SearchPageOption option;

    private SearchPageOption() {}

    public static SearchPageOption getSearchPageOption() {
        return (option == null) ? (option = new SearchPageOption()) : option;
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
