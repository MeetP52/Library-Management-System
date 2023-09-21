package dev.admin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.book.Book;
import dev.book.BookCatalog;
import dev.user.AddInfo;
import dev.user.User;
import dev.user.UserCatalog;
import dev.user.UserCatalogItem;

import java.util.List;

public class Admin {
    private User user;
    private AddInfo info;

    public Admin() {}

    @JsonCreator
    public Admin(@JsonProperty("user") User user,
                 @JsonProperty("info") AddInfo info) {
        this.user = user;
        this.info = info;
    }

    public Admin(UserCatalogItem item) {
        this.user = item.getUser().copy("deep");
        this.info = item.getAddInfo().copy("deep");
    }

    protected Admin(Admin user) {
        this.user = user.user.copy("deep");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AddInfo getInfo() {
        return this.info;
    }

    public void setInfo(AddInfo info) {
        this.info = info;
    }

    protected Admin copy(String type) {
        if(type.equals("deep")) {
            return new Admin(this);
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin1 = (Admin) o;

        return getUser().equals(admin1.getUser());
    }

    @Override
    public int hashCode() {
        if(getUser().hashCode() == 0) {
            System.out.println("Hashcode result is 0");
            return -1;
        }
        return getUser().hashCode();
    }

    public boolean removeBookToCatalog(List<Book> books) {
        BookCatalog catalog = BookCatalog.getBookCatalog();
        for(Book book : books) {
            if(!catalog.removeBook(book)) {
                System.out.println("Error removing "+ book.getTitle() +" from catalog.");
                return false;
            }
        }
        System.out.println("Success in removing book(s)");
        return true;
    }

    public boolean addBookToCatalog(List<Book> books) {
        BookCatalog bookCatalog = BookCatalog.getBookCatalog();
        for(Book book : books) {
            if(!bookCatalog.addBook(book)) {
                System.out.println("Error adding "+ book.getTitle() +" to catalog.");
                return false;
            }
        }
        System.out.println("Success in adding book(s)");
        return true;
    }

    public boolean removeUserFromCatalog(List<User> users) {
        UserCatalog catalog = UserCatalog.getUserCatalog();
        for(User user1 : users) {
            if(!catalog.removeUser(catalog.findUser(user1))) {
                System.out.println("Error removing "+ user1.getFirstName() + " " + user1.getLastName() +" from catalog.");
                return false;
            }
        }
        System.out.println("Success in removing user(s)");
        return true;
    }

    public boolean addUserToCatalog(List<UserCatalogItem> users) {
        UserCatalog catalog = UserCatalog.getUserCatalog();
        for(UserCatalogItem user1 : users) {
            if(!catalog.addUser(user1)) {
                System.out.println("Error adding "+ user1.getUser().getFirstName() + " " + user1.getUser().getLastName() +" to catalog.");
                return false;
            }
        }
        System.out.println("Success in adding user(s)");
        return true;
    }

    public boolean setUserPassword(User user,String newPassword) {
        UserCatalog catalog = UserCatalog.getUserCatalog();
        if(catalog.findUser(user) != null) {
            catalog.findUser(user).getAddInfo().setPassword(newPassword);
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "Admin {" +
                "\n\t admin = " + user +
                ",\n\t info=" + info + "\n\t" +
                '}';
    }
}
