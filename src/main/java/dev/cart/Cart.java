package dev.cart;

import dev.book.Book;
import dev.book.BookCatalogItem;

import java.util.List;
import java.util.Set;

public class Cart {
    private Set<Book> books;

    public Cart() {};

    public Cart(Set<Book> books) {
        this.books = books;
    }

    public Cart(Cart cart) {
        if(!this.equals(cart)) {
            this.books = Set.copyOf(cart.books);
        }
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public boolean addBooks(List<BookCatalogItem> bookList) {
        for(BookCatalogItem book : bookList) {
            books.add(book.getBook());
        }
        return true;
    }

    public boolean removeBooks(List<BookCatalogItem> bookList) {
        for(BookCatalogItem book : bookList) {
            books.remove(book.getBook());
        }
        return true;
    }

    public void cechkout() {};

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
