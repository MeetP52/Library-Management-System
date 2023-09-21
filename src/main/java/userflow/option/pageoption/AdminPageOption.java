package userflow.option.pageoption;

import userflow.PageManager;
import userflow.option.Option;

public class AdminPageOption implements Option {
    String prompt = "Goto Admin Page.";

    private static AdminPageOption option;
    private AdminPageOption() {}

    public static AdminPageOption getAdminPageOption() {
        return (option == null) ? (option = new AdminPageOption()) : option;
    }
    @Override
    public void execute() {
        PageManager.setCurrentPage("Admin");
    }

    @Override
    public String toString() {
        return prompt;
    }
}
