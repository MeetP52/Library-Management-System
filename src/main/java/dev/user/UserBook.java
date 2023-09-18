package dev.user;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.book.Book;

import java.time.LocalDate;
import java.util.Objects;

public class UserBook {
    private Book book;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    public UserBook () {}

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

    @JsonCreator
    public UserBook(@JsonProperty("book") Book book,
                    @JsonProperty("pickUpDate") LocalDate pickUpDate,
                    @JsonProperty("dueDate") LocalDate dueDate) {
        this.book = book;
        this.pickUpDate = pickUpDate;
        this.dueDate = dueDate;
    }

    protected UserBook(UserBook userBook) {
        this.book = userBook.book.copy("deep");
        this.pickUpDate = LocalDate.of(userBook.pickUpDate.getYear(),userBook.pickUpDate.getMonth(),userBook.pickUpDate.getDayOfMonth());
        this.dueDate = LocalDate.of(userBook.dueDate.getYear(),userBook.dueDate.getMonth(),userBook.dueDate.getDayOfMonth());
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public UserBook copy(String type) {
        if(type.equals("deep")) {
            return  new UserBook(this);
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBook userBook = (UserBook) o;
        return Objects.equals(book, userBook.book) && Objects.equals(pickUpDate, userBook.pickUpDate) && Objects.equals(dueDate, userBook.dueDate);
    }

    @Override
    public int hashCode() {
        int result = getBook().hashCode();
        result = 31 * result + getPickUpDate().hashCode();
        result = 31 * result + getDueDate().hashCode();
        return result;
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
