package userFlow.Option;

import userFlow.PageManager;
import userFlow.SearchPage.SearchPageOptions;

public class SearchPageOption implements Option {

    String prompt = "Goto Search Page";
    static SearchPageOption option;

    private SearchPageOption() {}

    public static SearchPageOption getSearchPageOption() {
        return (option == null) ? new SearchPageOption() : option;
    }
    @Override
    public void execute() {
        PageManager manager = PageManager.getPageManager();
        manager.setCurrentPage("Search");
    }

    @Override
    public String toString() {
        return  prompt;
    }
}
