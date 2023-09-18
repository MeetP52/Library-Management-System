package userflow.homepage;

import dev.user.UserBook;
import dev.user.UserCatalogItem;
import userflow.Window;

import java.time.LocalDate;

public class HomePageWindow implements Window {

    private static HomePageWindow window;

    private HomePageWindow() {}

    public static HomePageWindow getHomePageWindow() {
        return (window == null) ? new HomePageWindow() : window;
    }

    @Override
    public void display(UserCatalogItem user) {
        System.out.println("Here are all the books you have:");
        if(user.getAddInfo().getUserBooks().isEmpty()) {
            System.out.println("Your currently haven't checked out any books.");
            if(user.getAddInfo().getCart().getBooks().isEmpty()) {
                System.out.println("Your cart is also empty.");
            } else {
                System.out.println(user.getAddInfo().getCart().getBooks());
            }

        } else {
            System.out.println("Your Books: \n\n" + user.getAddInfo().getUserBooks());
            for(UserBook book : user.getAddInfo().getUserBooks()) {
                if(book.getDueDate().isBefore(LocalDate.now())) {
                    System.out.println("This book:" + book.getBook() + "\nIs overdue, please return this book.");
                    user.getAddInfo().addStrikes(1);
                }
            }
            if(user.getAddInfo().getCart().getBooks().isEmpty()) {
                System.out.println("Your cart is empty.");
            } else {
                System.out.println("Cart: \n\n" + user.getAddInfo().getCart().getBooks());
            }
        }
    }
}
