package userFlow.Option;

import dev.user.User;
import dev.user.UserBook;
import dev.user.UserCatalogItem;
import userFlow.CartPage.CartPageOptions;
import userFlow.Options;
import userFlow.PageManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class CheckoutOption implements Option {

    String prompt = "Checkout cart.";

    private static CheckoutOption option;

    private CheckoutOption() {};

    public static CheckoutOption getCheckOutOption() {
        return (option == null) ? new CheckoutOption() : option;
    }

    @Override
    public void execute() {
        PageManager manager =  PageManager.getPageManager();
        UserCatalogItem user = manager.getUser();
        if(!user.getAddInfo().getCart().checkout(user)) {
            System.out.println("Issue with checkout.");
        } else {
            System.out.println("Checkout Successful.");
            int index = 0;
            System.out.println("Books checked out:");
            for(UserBook book : user.getAddInfo().getUserBooks()) {
                index++;
                System.out.println(index + ": " + book.getBook() + "\n\t" + "Pickup Date: " + book.getPickUpDate() + "\n\t" + "Due Date: " + book.getDueDate());
            }
            getAdditionalInput(user);
        }
    }

    private void getAdditionalInput(UserCatalogItem user) {
        String input;
        System.out.println("Would you like to change any of the due dates for any of your books?");
        Scanner scanner = new Scanner(System.in);
        input = scanner.next();
        List<UserBook> books = user.getAddInfo().getUserBooks();
        if(input.equalsIgnoreCase("y")|| input.equalsIgnoreCase("yes")) {
            int index = -1;
            while (index != 0) {
                System.out.println("Which book(s) would you like to change the due date for? (Press 0 to quit");
                 index = scanner.nextInt();
                if (index == 0) {
                    continue;
                } else if (index <= books.size()) {
                    System.out.println("This is the current due date: " + books.get(index-1).getDueDate());
                    System.out.println("What would you like to set the date to? (format: yyyy-MM-dd)");
                    input = scanner.next();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate newDueDate = LocalDate.parse(input, formatter);
                    if (newDueDate.isBefore(LocalDate.now())) {
                        System.out.println("This is an incorrect due date.");
                    } else if (newDueDate.isEqual(LocalDate.now())) {
                        System.out.println("This is an incorrect due date.");
                    } else {
                        books.get(index-1).setDueDate(newDueDate);
                        System.out.println("New Due date." + books.get(index-1).getDueDate());
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return prompt;
    }
}