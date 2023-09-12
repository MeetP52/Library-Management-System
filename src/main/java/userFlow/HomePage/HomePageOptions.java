package userFlow.HomePage;

import userFlow.Option.*;
import userFlow.Options;

import java.util.HashMap;
import java.util.Map;

public class HomePageOptions implements Options {

    Map<Integer, Option> homePageOptions = new HashMap<>();

    static HomePageOptions options;

    private HomePageOptions() {
        homePageOptions.put(0,LogOutOption.getLogOutOption());
        homePageOptions.put(1,SearchPageOption.getSearchPageOption());
        homePageOptions.put(2,UserProfileOption.gerUserProfileOption());
        homePageOptions.put(3,CartPageOption.getCartPageOption());

    }

    public static HomePageOptions getHomePageOptions() {
        return (options == null) ? new HomePageOptions() : options;
    }

    @Override
    public void execute() {

    }

    @Override
    public void display() {
        homePageOptions.forEach((k,v) -> {
            System.out.println("Press " + k + " : " + v);
        });
    }

    @Override
    public Map<Integer, Option> getOptions() {
        return homePageOptions;
    }
}
