package dev.cart;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.book.Book;
import dev.book.BookCatalog;
import dev.book.BookCatalogItem;
import dev.user.UserBook;
import dev.user.UserCatalogItem;

import java.util.LinkedList;
import java.util.List;

public class Cart {
    private List<Book> books;

    public Cart() {
        books = new LinkedList<>();
    };

    @JsonCreator
    public Cart(@JsonProperty("books") List<Book> books) {
        this.books = (books == null) ? new LinkedList<>() : books;
    }

    public Cart(Cart cart) {
        if(!this.equals(cart)) {
            this.books = List.copyOf(cart.books);
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public boolean addBooks(List<Book> bookList) {
        BookCatalog catalog = BookCatalog.getBookCatalog();
        if(!setup(bookList,1)) return false;
        for(Book book : bookList) {
            books.add(catalog.findBook(book));
        }
        return true;
    }

    public boolean removeBooks(List<Book> bookList) {
        BookCatalog catalog = BookCatalog.getBookCatalog();
        if(!setup(bookList,20)) return false;
        for(Book book : bookList) {
            books.remove(catalog.findBook(book));
        }
        return true;
    }

    public boolean cechkout(UserCatalogItem user) {
        if(!setup(books,1)) {
            return false;
        }
        BookCatalog catalog = BookCatalog.getBookCatalog();
        for(Book book : books) {
            if(catalog.checkout(book)) {
                user.getAddInfo().getUserBooks().add(new UserBook(book));
            }
        }
        books.clear();
        return true;
    }

    private boolean setup(List<Book> bookList, int minQuanity) {
        BookCatalog catalog = BookCatalog.getBookCatalog();
        BookCatalogItem item;
        for(Book book : bookList) {
            item = catalog.findBookItem(book);
            if(item == null) {
                return false;
            } else {
                if(item.getQuantity() < minQuanity) {
                    return false;
                }
            }
        }
        return true;
    }


    public Cart copy(String type) {
        if(type.equals("deep")) {
            return new Cart(this);
        }
        return this;
    }

    @Override
    public String toString() {
        return "Cart {" +
                "\n\tbooks = " + books + "\n" +
                '}';
    }
}
