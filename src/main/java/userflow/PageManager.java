package userflow;

import dev.admin.Admin;
import dev.admin.AdminCatalog;
import dev.user.*;
import userflow.adminpage.AdminPageOptions;
import userflow.adminpage.AdminPageWindow;
import userflow.cartpage.CartPageOptions;
import userflow.cartpage.CartPageWindow;
import userflow.homepage.HomePageOptions;
import userflow.homepage.HomePageWindow;
import userflow.searchpage.SearchPageOptions;
import userflow.searchpage.SearchPageWindow;
import userflow.userprofilepage.UserPageOptions;
import userflow.userprofilepage.UserPageWindow;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class PageManager {

    private record Page(Window window, Options options) {
    }

    private static final Map<Integer, Page> pages = new HashMap<>();
    static Scanner next = new Scanner(System.in);
    static Scanner nextLine = new Scanner(System.in);
    static int currentPage = 1;
    static boolean isAdmin = false;
    private static UserCatalogItem user = null;
    private static PageManager manager;

    private PageManager() {
    }

    private static void setup() {
        pages.put(1,new Page(HomePageWindow.getHomePageWindow(),HomePageOptions.getHomePageOptions()));
        pages.put(2,new Page(SearchPageWindow.getSearchPageWindow(),SearchPageOptions.getSearchPageOptions()));
        pages.put(3,new Page(CartPageWindow.getCartPageWindow(), CartPageOptions.getCartPageOptions()));
        pages.put(4,new Page(UserPageWindow.getUserPageWindow(), UserPageOptions.getUserPageOptions()));
        pages.put(5,new Page(AdminPageWindow.getAdminPageWindow(),AdminPageOptions.getAdminPageOptions()));
    }

    public static PageManager getPageManager() {
        if(manager == null) {
            manager = new PageManager();
        }
        return manager;
    }

    public static UserCatalogItem displayLogInPage() {
        String username;
        String password;
        System.out.println("Welcome to the Library Management Tool");
        if(user == null) {
            System.out.println("Are you a new user?");
            System.out.print("> ");
            username = next.next();
            if (username.equalsIgnoreCase("y") || username.equalsIgnoreCase("yes")) {
                createNewUserScript();
            } else {
                System.out.print("Enter a username: ");
                System.out.print("> ");
                username = next.next();
                System.out.print("Enter a password: ");
                System.out.print("> ");
                password = next.next();
                user = authenticate(username,password);
                return user;
            }
        }
        System.out.print("Enter a username: ");
        System.out.print("> ");
        username = next.next();
        System.out.print("Enter a password: ");
        System.out.print("> ");
        password = next.next();
        user = authenticate(username,password);
        return user;
    }

    private static void createNewUserScript() {
        String tryAgain = "Try again";
        User tempUser = new User();
        AddInfo info = new AddInfo();
        String input;
        System.out.println("Please Enter your first name.");
        System.out.print("> ");
        input = next.next();
        tempUser.setFirstName(input);
        System.out.println("Please Enter your last name.");
        System.out.print("> ");
        input = next.next();
        tempUser.setLastName(input);
        System.out.println("Please Enter your email address.");
        System.out.print("> ");
        input = next.next();
        while(!tempUser.setEmailAddress(input)) {
            System.out.println(tryAgain);
            input = next.next();
        }
        System.out.println("Please Enter your phone number.");
        System.out.print("> ");
        input = next.next();
        while(!tempUser.setPhoneNumber(input)) {
            System.out.println(tryAgain);
            input = next.next();
        }
        System.out.println("Please Enter your current country.");
        System.out.print("> ");
        input = nextLine.nextLine();
        tempUser.setCountry(input);
        System.out.println("Please Enter your current state.");
        System.out.print("> ");
        input = nextLine.nextLine();
        tempUser.setState(input);
        System.out.println("Please Enter your current city.");
        System.out.print("> ");
        input = nextLine.nextLine();
        tempUser.setCity(input);

        System.out.println("\n\nGreat! Now lets add some security.");
        System.out.println("Please a password.");
        System.out.print("> ");
        input = next.next();
        while(!info.setPassword(input)) {
            System.out.println(tryAgain);
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
        String prompt = """
                Each user within the system must have a unique email address, which is how we identify users.
                If your email already exists within the system unfortunately you will have to create a new unique email address.
                """;
        System.out.println(prompt);
        UserCatalog catalog = UserCatalog.getUserCatalog();
        while (catalog.findUser(tempUser.hashCode()) != null) {
            System.out.println("Sorry it looks like someone already makes use of this email address, you must come with you another unique email address");
            System.out.print("Try again >");
            input = next.next();
            while(!tempUser.setEmailAddress(input)) {
                System.out.println(tryAgain);
                System.out.print("> ");
                input = next.next();
            }
        }
        System.out.println("Success, your profile is being created right now.");
        user = new UserCatalogItem(tempUser,info);
        catalog.addUser(user);
        System.out.println("Lets sign in one more time to make sure that it works.");
    }

    private static UserCatalogItem authenticate(String username, String password) {
        UserCatalog userCatalog = UserCatalog.getUserCatalog();
        AdminCatalog adminCatalog = AdminCatalog.getAdminCatalog();
        user = userCatalog.findUser(username.hashCode());
        Admin tempAdmin;
        if(user == null) {
            tempAdmin = adminCatalog.findAdmin(username.hashCode());
            if(tempAdmin != null) {
                if(tempAdmin.getInfo().getPassword().equals(password)) {
                    // Note get method returns references to their respective object. Changing the user will affect the admin as well.
                    user = new UserCatalogItem(tempAdmin.getUser(), tempAdmin.getInfo());
                    isAdmin = true;
                    setup();
                    return user;
                } else {
                    System.out.println("Invalid Username or Password");
                    return null;
                }
            }
        } else {
            if(user.getAddInfo().getPassword().equals(password)) {
                setup();
                return user;
            } else {
                System.out.println("Invalid Username or Password.");
                return null;
            }
        }
        return user;
    }

    public void displayCurrentPage() {
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
                System.out.println("There was an issue trying to understand your input try again,.");
                next.nextLine();
            }
        }
        pages.get(currentPage).options.getOptions().get(input).execute();
    }

    public static void setCurrentPage(String page) {
        switch (page.toLowerCase()) {
            case "search" -> currentPage = 2;
            case "cart" -> currentPage = 3;
            case "user" -> currentPage = 4;
            case "admin" -> currentPage = 5;
            default -> currentPage = 1;
        }
    }

    public UserCatalogItem getUser() {
        return user;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

}
