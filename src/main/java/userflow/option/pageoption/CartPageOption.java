package userflow.option.pageoption;

import userflow.PageManager;
import userflow.option.Option;

public class CartPageOption implements Option {

    String prompt = "Goto Cart Page";

    private static CartPageOption option;

    private CartPageOption() {}

    public static CartPageOption getCartPageOption() {
        return (option == null) ? (option = new CartPageOption()) : option;
    }
    @Override
    public void execute() {
        PageManager.setCurrentPage("Cart");
    }

    @Override
    public String toString() {
        return prompt;
    }
}
