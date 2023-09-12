package userFlow.CartPage;

import dev.user.UserCatalogItem;
import userFlow.Window;

public class CartPageWindow implements Window {

    static private CartPageWindow window;

    private CartPageWindow() {};

    public static CartPageWindow getCartPageWindow() {
        return (window == null) ? new CartPageWindow() : window;
    }

    @Override
    public void display(UserCatalogItem user) {
        System.out.println("Welcome to your cart.");
        if(user.getAddInfo().getCart().getBooks().isEmpty()) {
            System.out.println("Cart: Empty");
        } else {
            System.out.println("Cart: \n\n" + user.getAddInfo().getCart().getBooks());
        }
    }
}

