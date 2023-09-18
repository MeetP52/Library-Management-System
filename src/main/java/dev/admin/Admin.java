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
    private User adminUser;
    private AddInfo info;

    public Admin() {}

    @JsonCreator
    public Admin(@JsonProperty("user") User user,
                 @JsonProperty("info") AddInfo info) {
        this.adminUser = user;
        this.info = info;
    }

    public Admin(UserCatalogItem item) {
        this.adminUser = item.getUser().copy("deep");
        this.info = item.getAddInfo().copy("deep");
    }

    protected Admin(Admin adminUser) {
        this.adminUser = adminUser.adminUser.copy("deep");
    }

    public User getUser() {
        return adminUser;
    }

    public void setUser(User user) {
        this.adminUser = user;
    }

    public AddInfo getInfo() {
        return info;
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

        Admin admin = (Admin) o;

        return getUser().equals(admin.getUser());
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
        for(User user : users) {
            if(!catalog.removeUser(catalog.findUser(user))) {
                System.out.println("Error removing "+ user.getFirstName() + " " + user.getLastName() +" from catalog.");
                return false;
            }
        }
        System.out.println("Success in removing user(s)");
        return true;
    }

    public boolean addUserToCatalog(List<UserCatalogItem> users) {
        UserCatalog catalog = UserCatalog.getUserCatalog();
        for(UserCatalogItem user : users) {
            if(!catalog.addUser(user)) {
                System.out.println("Error adding "+ user.getUser().getFirstName() + " " + user.getUser().getLastName() +" to catalog.");
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
                "\n\t admin = " + adminUser +
                ",\n\t info=" + info + "\n\t" +
                '}';
    }
}
