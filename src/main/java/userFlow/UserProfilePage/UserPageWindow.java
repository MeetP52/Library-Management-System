package userFlow.UserProfilePage;

import dev.user.UserCatalogItem;
import userFlow.Window;

public class UserPageWindow implements Window {

    static UserPageWindow window;

    private UserPageWindow() {};

    public static UserPageWindow getUserPageWindow() {
        return (window == null) ? new UserPageWindow() : window;
    }
    @Override
    public void display(UserCatalogItem user) {
        System.out.println("Welcome to your profile.");
        System.out.println("First Name: " + user.getUser().getFirstName());
        System.out.println("Last Name" + user.getUser().getLastName());
        System.out.println("Email Address: " + user.getUser().getEmailAddress());
        System.out.println("Phone Number: " + user.getUser().getPhoneNumber());
        System.out.println("Country: " + user.getUser().getCountry());
        System.out.println("State: " + user.getUser().getState());
        System.out.println("City: " + user.getUser().getCity());
        System.out.println("Strikes: " + user.getAddInfo().getStrikes());
    }
}
