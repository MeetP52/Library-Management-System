package userflow.option;

import userflow.PageManager;

public class CartPageOption implements Option {

    String prompt = "Goto Cart Page";

    private static CartPageOption option;

    private CartPageOption() {}

    public static CartPageOption getCartPageOption() {
        return (option == null) ? new CartPageOption() : option;
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
