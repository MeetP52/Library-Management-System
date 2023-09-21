package userflow.option.adminoptions;

import dev.book.Book;
import dev.book.BookCatalog;
import userflow.option.Option;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AddNewBookOption implements Option {

    String prompt = "Add Book to catalog.";

    private static AddNewBookOption option;

    private AddNewBookOption() {}

    public static AddNewBookOption getAddNewBookOption() {
        return (option == null) ? (option = new AddNewBookOption()) : option;
    }
    @Override
    public void execute() {
        String title;
        String series;
        String description;
        String input = "";
        Set<String> authors = new HashSet<>();
        Set<String> genres = new HashSet<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the title for this book.");
        System.out.print("> ");
        title = scanner.nextLine();
        System.out.println("Enter the name of the series for this book.");
        System.out.print("> ");
        series = scanner.nextLine();
        System.out.println("Enter the description for this book.");
        System.out.print("> ");
        description = scanner.nextLine();
        System.out.println("Enter the name of the author(s) for this book. Press 0 to quit");
        while(!input.equalsIgnoreCase("0")) {
            System.out.print("> ");
            input = scanner.nextLine();
            if(input.equalsIgnoreCase("0")) {
                break;
            } else {
                if(!authors.add(input)) {
                    System.out.println("You already added this author.");
                }
            }
        }
        System.out.println("Enter the name of the genre(s) for this book. Press 0 to quit");
        while(!input.equalsIgnoreCase("0")) {
            System.out.print("> ");
            input = scanner.nextLine();
            if(input.equalsIgnoreCase("0")) {
                break;
            } else {
                if(!genres.add(input)) {
                    System.out.println("You already added this genre.");
                }
            }
        }
        Book book = new Book(title,series,description,authors,genres);
        BookCatalog catalog = BookCatalog.getBookCatalog();
        if(!catalog.addBook(book)) {
            System.out.println("Issue with adding book to catalog.");
        } else {
            System.out.println("Book added.");
        }
    }

    @Override
    public String toString() {
        return prompt;
    }
}
