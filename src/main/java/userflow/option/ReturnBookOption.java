package userflow.option;

import dev.book.BookCatalog;
import dev.user.UserBook;
import dev.user.UserCatalogItem;
import userflow.PageManager;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ReturnBookOption implements Option{

    String prompt = "Return book(s)";

    private static ReturnBookOption option;

    private ReturnBookOption() {}

    public static ReturnBookOption getReturnBookOption() {
        return (option == null) ? new ReturnBookOption() : option;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        UserCatalogItem user = PageManager.getPageManager().getUser();
        BookCatalog catalog = BookCatalog.getBookCatalog();
        List<UserBook> books = user.getAddInfo().getUserBooks();
        if(user.getAddInfo().getUserBooks().isEmpty()) {
            System.out.println("It seems that we do not have a record of you checking out any books.");
        } else {
            int index = 0;
            for(UserBook book : books) {
                index++;
                System.out.println(index + ": " + book);
            }
            System.out.println("Enter the number for the book you would like to return. Enter 0 to quit.");
            while(index != 0) {
                try {
                    System.out.print("> ");
                    index = scanner.nextInt();
                    if(index == 0) {
                        break;
                    } else if (index <= books.size()){
                        if(catalog.findBookItem(books.get(index-1).getBook()).getQuantity() <= 19) {
                            if(catalog.findBookItem(books.get(index-1).getBook()).addQuantity(1)) {
                                if(user.getAddInfo().getUserBooks().remove(index) != null) {
                                    System.out.println("Book returned.");
                                }
                            }
                        }
                    } else {
                        System.out.println("Problem with index, try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Try Again");
                    scanner.nextLine();
                }
            }
        }
    }

    @Override
    public String toString() {
        return prompt;
    }
}
