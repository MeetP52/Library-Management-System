package userFlow;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import dev.user.*;
import userFlow.CartPage.CartPageOptions;
import userFlow.CartPage.CartPageWindow;
import userFlow.HomePage.HomePageOptions;
import userFlow.HomePage.HomePageWindow;
import userFlow.SearchPage.SearchPageOptions;
import userFlow.SearchPage.SearchPageWindow;
import userFlow.UserProfilePage.UserPageOptions;
import userFlow.UserProfilePage.UserPageWindow;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class PageManager {

    private class Page {
        private final Window window;
        private final Options options;

        public Page(Window window, Options options) {
            this.window = window;
            this.options = options;
        }
    }

    Map<Integer, Page> pages = new HashMap<>();
    Scanner next = new Scanner(System.in);
    Scanner nextLine = new Scanner(System.in);
    static int currentPage = 1;

    static private UserCatalogItem user = null;

    static PageManager manager;

    private PageManager() {
        pages.put(1,new Page(HomePageWindow.getHomePageWindow(),HomePageOptions.getHomePageOptions()));
        pages.put(2,new Page(SearchPageWindow.getSearchPageWindow(),SearchPageOptions.getSearchPageOptions()));
        pages.put(3,new Page(CartPageWindow.getCartPageWindow(), CartPageOptions.getCartPageOptions()));
        pages.put(4,new Page(UserPageWindow.getUserPageWindow(), UserPageOptions.getUserPageOptions()));
    }

    public static PageManager getPageManager() {
        return (manager == null) ? new PageManager() : manager;
    }

    public UserCatalogItem displayLogInPage() {
        String username, password;
        System.out.println("Welcome to the Library Management Tool");
        if(user == null) {
            System.out.println("Are you a new user?");
            System.out.print("> ");
            username = next.next();
            if (username.equalsIgnoreCase("y") || username.equalsIgnoreCase("yes")) {
                CreateNewUserScript();
            } else {
                System.out.print("Enter a username: ");
                System.out.print("> ");
                username = next.next();
                // username = "meet@patel.com";
                System.out.print("Enter a password: ");
                System.out.print("> ");
                password = next.next();
                return user = authenticate(username,password);
            }
        }
        System.out.print("Enter a username: ");
        System.out.print("> ");
        username = next.next();
        // username = "meet@patel.com";
        System.out.print("Enter a password: ");
        System.out.print("> ");
        password = next.next();
        // password = "P@ssw0rd123";
        return user = authenticate(username,password);
    }

    private void CreateNewUserScript() {
        User _user = new User();
        AddInfo info = new AddInfo();
        String input;
        System.out.println("Please Enter your first name.");
        System.out.print("> ");
        input = next.next();
        _user.setFirstName(input);
        System.out.println("Please Enter your last name.");
        System.out.print("> ");
        input = next.next();
        _user.setLastName(input);
        System.out.println("Please Enter your email address.");
        System.out.print("> ");
        input = next.next();
        while(!_user.setEmailAddress(input)) {
            System.out.println("Try again");
            input = next.next();
        }
        System.out.println("Please Enter your phone number.");
        System.out.print("> ");
        input = next.next();
        while(!_user.setPhoneNumber(input)) {
            System.out.println("Try again");
            input = next.next();
        }
        System.out.println("Please Enter your current country.");
        System.out.print("> ");
        input = nextLine.nextLine();
        _user.setCountry(input);
        System.out.println("Please Enter your current state.");
        System.out.print("> ");
        input = nextLine.nextLine();
        _user.setState(input);
        System.out.println("Please Enter your current city.");
        System.out.print("> ");
        input = nextLine.nextLine();
        _user.setCity(input);

        System.out.println("\n\nGreat! Now lets add some security.");
        System.out.println("Please a password.");
        System.out.print("> ");
        input = next.next();
        while(!info.setPassword(input)) {
            System.out.println("Try again");
            System.out.print("> ");
            input = next.next();
        }
        System.out.println("Now lets choose a security question");
        System.out.println("Here are your options: ");
        int index = 0;
        SecurityQuestion[] questions = SecurityQuestion.values();
        for(SecurityQuestion q : questions) {
            index++;
            System.out.println(index + ": " + q);

        }
        boolean hasInt = false;
        while(!hasInt) {
            try {
                System.out.print("> ");
                index = next.nextInt();
                hasInt = true;
            } catch (InputMismatchException e) {
                hasInt = false;
                System.out.println("There was an issue trying to understand your input try again.");
                next.nextLine();
            }
        }
        if(index <= SecurityQuestion.values().length) {
            info.setSecurityQuestion(questions[index-1]);
            System.out.println("Security Question Added.");
        }
        System.out.println("Now lets add a security answer");
        System.out.print("> ");
        input = nextLine.nextLine();
        info.setSecurityAnswer(input);
        System.out.println("\n\n\nEach user within the system must have a unique email address, which is how we identify users.\n" +
                "If your email already exists within the system unfortunately you will have to create a new unique email address.");
        UserCatalog catalog = UserCatalog.getUserCatalog();
        while (catalog.findUser(_user.hashCode()) != null) {
            System.out.println("Sorry it looks like someone already makes use of this email address, you must come with you another unique email address");
            System.out.print("Try again >");
            input = next.next();
            while(!_user.setEmailAddress(input)) {
                System.out.println("Try again");
                System.out.print("> ");
                input = next.next();
            }
        }
        System.out.println("Success, your profile is being created right now.");
        user = new UserCatalogItem(_user,info);
        catalog.addUser(user);
        System.out.println("Lets sign in one more time to make sure that it works.");
    }

    private UserCatalogItem authenticate(String username, String password) {
        UserCatalog userCatalog = UserCatalog.getUserCatalog();
        user = userCatalog.findUser(username.hashCode());
        if(user == null) {
            return null;
        } else {
            if(user.getAddInfo().getPassword().equals(password)) {
                return user;
            } else {
                System.out.println("Invalid Username or Password.");
                return null;
            }
        }
    }

    public void displayCurrentPage(UserCatalogItem user) {
        pages.get(currentPage).window.display(user);
        System.out.println("What would you like to do?");
        pages.get(currentPage).options.display();
        takeInput();
    }

    private void takeInput() {
        int input = 0;
        boolean hasInt = false;

        while(!hasInt) {
            try {
                System.out.print("> ");
                input = next.nextInt();
               hasInt = true;
            } catch (InputMismatchException e) {
                hasInt = false;
                System.out.println("There was an issue trying to understand your input try again,.");
                next.nextLine();
            }
        }
        pages.get(currentPage).options.getOptions().get(input).execute();
    }

    public void setCurrentPage(String page) {
        switch (page.toLowerCase()) {
            case "search" -> currentPage = 2;
            case "cart" -> currentPage = 3;
            case "user" -> currentPage = 4;
            default -> currentPage = 1;
        }
    }

    public UserCatalogItem getUser() {
        return user;
    }

}
