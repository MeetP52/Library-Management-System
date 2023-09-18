package userflow.cartpage;

import dev.user.UserCatalogItem;
import userflow.Window;

public class CartPageWindow implements Window {

    private static CartPageWindow window;

    private CartPageWindow() {}

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

