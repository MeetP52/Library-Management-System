package dev.lpa;

import dev.user.*;
import userFlow.PageManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        UserCatalogItem user = null;
        int loginAttempts = 0;

        PageManager manager = PageManager.getPageManager();
        while(loginAttempts < 3) {
            if(user == null) {
                user = manager.displayLogInPage();
                if(user == null) {
                    System.out.println("Attempt Failed, try again.");
                    loginAttempts++;
                } else {
                    System.out.println("Signing Successful.");
                }
            } else {
                manager.displayCurrentPage(user);
            }
        }
    }
}
