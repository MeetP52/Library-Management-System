package userflow.option.adminoptions;

import dev.book.BookCatalog;
import dev.book.BookCatalogItem;
import userflow.option.Option;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class RemoveBookOption implements Option {
    String prompt = "Remove book from catalog.";

    private static RemoveBookOption option;
    private RemoveBookOption() {}
    public static RemoveBookOption getRemoveBookOption() {
        return (option == null) ? new RemoveBookOption() : option;
    }
    @Override
    public void execute() {
        BookCatalog catalog = BookCatalog.getBookCatalog();
        List<BookCatalogItem> books = catalog.getBookList();
        Scanner scanner = new Scanner(System.in);
        int index = 0;
        for(BookCatalogItem book : books) {
            index++;
            System.out.println(index + ": " + book.getBook());
        }
        List<Integer> booksToRemove = new LinkedList<>();
        System.out.println("Enter all books you wish to remove from catalog. Press 0 to exit.");
        while(index != 0) {
            try {
                index = scanner.nextInt();
                if(index == 0) {
                    break;
                }
                if(index <= books.size()) {
                    booksToRemove.add(index);
                }
            } catch (InputMismatchException e) {
                System.out.println("Try again");
                scanner.nextLine();
            }
        }
        for (Integer integer : booksToRemove) {
            if(catalog.findBookItem(books.get(integer-1).getBook()).getQuantity() < 20) {
                System.out.println("There are still copies of this book that are not returned, to remove a book, ensure all copies are returned.");
            } else {
                if(catalog.removeBook(books.get(integer-1).getBook())) {
                    System.out.println("Book Removed.");
                } else {
                    System.out.println("Issue with removing book.");
                }
            }
        }

    }
}
