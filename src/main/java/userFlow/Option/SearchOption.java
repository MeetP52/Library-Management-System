package userFlow.Option;

import dev.book.Book;
import dev.book.BookCatalog;
import dev.user.UserCatalogItem;
import userFlow.PageManager;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SearchOption implements Option {

    String prompt = "Search for books";

    static SearchOption option;
    Scanner scanner = new Scanner(System.in);

    private SearchOption() {}

    public static SearchOption getSearchOption() {
        return (option == null) ? new SearchOption() : option;
    }
    @Override
    public void execute() {
        String input;
        BookCatalog catalog = BookCatalog.getBookCatalog();
        System.out.println("What would you like to search? (search by: title, series, author, or genre).");
        input = scanner.next();
        List<Book> books = catalog.findBook(input);
        if(books.isEmpty()) {
            System.out.println("There are no results for " + input);
        } else {
            int index = 1;
            for(Book book : books) {
                System.out.println(index + ":\t " + book);
                index++;
            }
        }
        selectBooks(books);
    }

    private void selectBooks(List<Book> books) {
        String input;
        int bookNumber = -1;
        List<Book> cartBooks = new LinkedList<>();
        System.out.println("Would you like to select books to add to your cart?");
        input = scanner.next();
        if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
            System.out.println("Which books would you like to add? Input one at a time. Enter 0 to quit");
            while (bookNumber != 0) {
                bookNumber = scanner.nextInt();
                if(bookNumber == 0) {
                    continue;
                } else if (bookNumber <= books.size()) {
                    cartBooks.add(books.get(bookNumber - 1));
                } else {
                    System.out.println("Issues adding book at index: " + bookNumber);
                }
            }
            System.out.println("These are your selected books:");
            for(Book book: cartBooks) {
                System.out.println(book);
            }
            System.out.println("Would you like to commit this transaction?");
            input = scanner.next();
            if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                if(!checkoutBooks(cartBooks)) {
                    System.out.println("Issues adding books to your cart.");
                }
            }

        }
    }

    private boolean checkoutBooks(List<Book> books) {
        PageManager manage = PageManager.getPageManager();
        UserCatalogItem user = manage.getUser();
        return user.getAddInfo().getCart().addBooks(books);
    }


    @Override
    public String toString() {
        return prompt;
    }
}
