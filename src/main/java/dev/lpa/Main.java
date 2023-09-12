package dev.lpa;


import dev.admin.Admin;
import dev.admin.AdminCatalog;
import dev.book.Book;
import dev.book.BookCatalog;
import dev.user.*;
import userFlow.PageManager;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        UserCatalog userCatalog = UserCatalog.getUserCatalog();
        BookCatalog bookCatalog = BookCatalog.getBookCatalog();
        AdminCatalog adminCatalog = AdminCatalog.getAdminCatalog();
        UserCatalogItem user = null;

        String userName, password;
        Scanner scanner = new Scanner(System.in);
        int loginAttempts = 0;

        PageManager manager = PageManager.getPageManager();
        while(loginAttempts < 3) {
            if(user == null) {
                user = manager.displayLogInPage();
                System.out.println("Invalid Username or Password");
//                final String os = System.getProperty("os.name");
//
//                if (os.contains("Windows"))
//                {
//                    Runtime.getRuntime().exec("cls");
//                }
//                else
//                {
//                    Runtime.getRuntime().exec("clear");
//                }
                loginAttempts++;
            } else {
                manager.displayCurrentPage(user);
            }
        }
    }
}
