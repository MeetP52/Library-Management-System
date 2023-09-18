package dev.lpa;

import dev.user.*;
import userflow.PageManager;


public class Main {
    public static void main(String[] args) {
        UserCatalogItem user = null;
        int loginAttempts = 0;

        PageManager manager = PageManager.getPageManager();
        while(loginAttempts < 3) {
            if(user == null) {
                user = PageManager.displayLogInPage();
                if(user == null) {
                    System.out.println("Attempt Failed, try again.");
                    loginAttempts++;
                } else {
                    System.out.println("Signing Successful.");
                }
            } else {
                manager.displayCurrentPage();
            }
        }
    }
}
