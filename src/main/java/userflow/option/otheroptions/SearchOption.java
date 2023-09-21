package userflow.option.otheroptions;

import dev.book.Book;
import dev.book.BookCatalog;
import dev.user.UserCatalogItem;
import userflow.PageManager;
import userflow.option.Option;

import java.util.*;

public class SearchOption implements Option {

    String prompt = "Search for books";

    private static SearchOption option;
    Scanner scanner = new Scanner(System.in);

    private SearchOption() {}

    public static SearchOption getSearchOption() {
        return (option == null) ? (option = new SearchOption()) : option;
    }
    @Override
    public void execute() {
        String input;
        BookCatalog catalog = BookCatalog.getBookCatalog();
        List<Book> books;
        do {
            System.out.println("What would you like to search? (search by: title, series, author, or genre). Press 'q' to quit.");
            System.out.print("> ");
            input = scanner.next();
            if(input.equalsIgnoreCase("q")) {
                break;
            } else {
                books = catalog.findBook(input);
                if (books.isEmpty()) {
                    System.out.println("There are no results for " + input);
                } else {
                    int index = 1;
                    for (Book book : books) {
                        System.out.println(index + ":\t " + book);
                        index++;
                    }
                    selectBooks(books);
                }
            }
        } while (books.isEmpty());
    }

    private void selectBooks(List<Book> books) {
        String input;
        int bookNumber = -1;
        List<Book> cartBooks = new LinkedList<>();
        System.out.println("Would you like to select books to add to your cart?");
        System.out.print("> ");
        input = scanner.next();
        UserCatalogItem user = PageManager.getPageManager().getUser();
        if(user.getAddInfo().getStrikes() >= 3) {
            System.out.println("You cannot checkout any books until your strikes are removed. Contact customer support.");
        } else {
            if (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("yes")) {
                return;
            }
            System.out.println("Which books would you like to add? Input one at a time. Enter 0 to quit");
            while (true) {
                try {
                    System.out.print("> ");
                    bookNumber = scanner.nextInt();
                    if (bookNumber == 0) {
                        break;
                    } else if (bookNumber <= books.size()) {
                        cartBooks.add(books.get(bookNumber - 1));
                    } else {
                        System.out.println("Issues adding book at index: " + bookNumber);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Try again.");
                    scanner.nextLine();
                }
            }
            System.out.println("These are your selected books:");
            cartBooks.forEach(System.out::println);
            System.out.println("Would you like to commit this transaction?");
            System.out.print("> ");
            input = scanner.next();
            if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes") && (!addBookToCart(cartBooks))) {
                System.out.println("Issues adding books to your cart.");

            }
        }
    }

    private boolean addBookToCart(List<Book> books) {
        PageManager manage = PageManager.getPageManager();
        return manage.getUser().getAddInfo().getCart().addBooks(books);
    }


    @Override
    public String toString() {
        return prompt;
    }
}
