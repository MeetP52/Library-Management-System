package userFlow.Option;

import userFlow.PageManager;

public class CartPageOption implements Option {

    String prompt = "Goto Cart Page";

    static CartPageOption option;

    private CartPageOption() {}

    public static CartPageOption getCartPageOption() {
        return (option == null) ? new CartPageOption() : option;
    }
    @Override
    public void execute() {
        PageManager manager = PageManager.getPageManager();
        manager.setCurrentPage("Cart");
    }

    @Override
    public String toString() {
        return prompt;
    }
}
