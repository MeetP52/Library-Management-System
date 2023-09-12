package userFlow;

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
    Scanner scanner = new Scanner(System.in);
    static int currentPage = 1;

    static private UserCatalogItem user;

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
        System.out.println("Are you a new user?");
        username = scanner.next();
        if(username.equalsIgnoreCase("y") || username.equalsIgnoreCase("yes")) {
            CreateNewUserScript();
        }
        System.out.print("Enter a username: ");
        // username = scanner.next();
        username = "meet@patel.com";
        System.out.print("Enter a password: ");
        // password = scanner.next();
        password = "P@ssw0rd123";
        return user = authenticate(username,password);
    }

    private void CreateNewUserScript() {
        User _user = new User();
        AddInfo info = new AddInfo();
        String input;
        System.out.println("Please Enter your first name.");
        input = scanner.next();
        _user.setFirstName(input);
        System.out.println("Please Enter your last name.");
        input = scanner.next();
        _user.setLastName(input);
        System.out.println("Please Enter your email address.");
        input = scanner.next();
        while(!_user.setEmailAddress(input)) {
            System.out.println("Try again");
            input = scanner.next();
        }
        System.out.println("Please Enter your phone number.");
        input = scanner.next();
        while(!_user.setPhoneNumber(input)) {
            System.out.println("Try again");
            input = scanner.next();
        }
        System.out.println("Please Enter your current country.");
        input = scanner.next();
        _user.setCountry(input);
        System.out.println("Please Enter your current state.");
        input = scanner.next();
        _user.setState(input);
        System.out.println("Please Enter your current city.");
        input = scanner.next();
        _user.setCity(input);

        System.out.println("\n\nGreat! Now lets add some security.");
        System.out.println("Please a passowrd.");
        input = scanner.next();
        while(!info.setPassword(input)) {
            System.out.println("Try again");
            input = scanner.next();
        }
        System.out.println("Now lets choose a security question");
        System.out.println("Here are your options: ");
        int index = 0;
        SecurityQuestion[] questions = SecurityQuestion.values();
        for(SecurityQuestion q : questions) {
            index++;
            System.out.println(index + ": " + q);

        }
        System.out.print("> ");
        index = new Scanner(System.in).nextInt();
        if(index <= SecurityQuestion.values().length) {
            user.getAddInfo().setSecurityQuestion(questions[index-1]);
            System.out.println("Security Question Added.");
        }
        System.out.println("Now lets add a security answer");
        input = scanner.next();
        info.setSecurityAnswer(input);
        System.out.println("Each user within the system must have a unique email address, which is how we identify users.\n" +
                "If this email already exists unfortunately you will have to create a new unique email address.");
        UserCatalog catalog = UserCatalog.getUserCatalog();
        while (catalog.findUser(input.hashCode()) == null) {
            System.out.println("Sorry it looks like someone already makes use of this email address, you must come with you another unique email address");
            input = scanner.next();
            while(!_user.setEmailAddress(input)) {
                System.out.println("Try again");
                input = scanner.next();
            }
        }
        System.out.println("Success, your profile is being created right now.");
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
        int input;
        System.out.print("> ");
        input = scanner.nextInt();
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
