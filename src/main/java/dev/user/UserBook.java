package dev.user;


import dev.book.Book;

import java.time.LocalDate;
import java.util.Objects;

public class UserBook {
    private Book book;
    private LocalDate pickUpDate;
    private LocalDate dueDate;

    public UserBook () {};

    public UserBook(Book book) {
        this.book = book;
        this.pickUpDate = LocalDate.now();
        this.dueDate = LocalDate.now().plusMonths(1);
    }

    public UserBook(Book book,LocalDate dueDate) {
        this.book = book;
        this.pickUpDate = LocalDate.now();
        this.dueDate = dueDate;
    }

    protected UserBook(UserBook userBook) {
        this.book = userBook.book.copy("deep");
        this.pickUpDate = LocalDate.of(userBook.pickUpDate.getYear(),userBook.pickUpDate.getMonth(),userBook.pickUpDate.getDayOfMonth());
        this.dueDate = LocalDate.of(userBook.dueDate.getYear(),userBook.dueDate.getMonth(),userBook.dueDate.getDayOfMonth());
    }


    public boolean setdueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return true;
    }

    public LocalDate getpickUpDate() {
        return pickUpDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Book getBook() {
        return book;
    }

    public UserBook copy(String type) {
        UserBook userBook;
        if(type.equals("deep")) {
            return userBook = new UserBook(this);
        }
        return userBook = this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBook userBook = (UserBook) o;
        return Objects.equals(book, userBook.book) && Objects.equals(pickUpDate, userBook.pickUpDate) && Objects.equals(dueDate, userBook.dueDate);
    }

    @Override
    public String toString() {
        return "UserBook { " +
                "\n\t book=" + book +
                ",\n\t pickUpDate=" + pickUpDate +
                ",\n\t dueDate=" + dueDate + "\n\t" +
                '}';
    }
}
