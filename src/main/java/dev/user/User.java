package dev.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class User {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String country;
    private String state;
    private String city;

    public User() {}

    @JsonCreator
    public User(@JsonProperty("firstName") String firstName,
                @JsonProperty("lastName") String lastName,
                @JsonProperty("emailAddress") String emailAddress,
                @JsonProperty("phoneNumber") String phoneNumber,
                @JsonProperty("country") String country,
                @JsonProperty("state") String state,
                @JsonProperty("city") String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        if(!this.setEmailAddress(emailAddress)) {
            this.emailAddress = "Invalid Email Address.";
        }
        if(!this.setPhoneNumber(phoneNumber)) {
            this.phoneNumber = "Invalid Phone Number";
        }
        this.country = country;
        this.state = state;
        this.city = city;
    }

    public User(User user) {
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        if(!this.setEmailAddress(user.emailAddress)) {
            this.emailAddress = "Invalid Email Address.";
        }
        if(!this.setPhoneNumber(user.phoneNumber)) {
            this.phoneNumber = "Invalid Phone Number";
        }
        this.country = user.country;
        this.state = user.state;
        this.city = user.city;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public boolean setEmailAddress(String emailAddress) {
        if(validateEmailAddress(emailAddress)) {
            this.emailAddress = emailAddress;
            return true;
        }
        return false;
    }

    public boolean validateEmailAddress(String emailAddress) {
        if(emailAddress == null || emailAddress.isBlank()) return false;
        String emailAddressExpression = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,}";
        if (!emailAddress.matches(emailAddressExpression)) {
            System.out.println("Invalid email address, example: example2023@r44.co");
            return false;
        }
        return true;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean setPhoneNumber(String phoneNumber) {
        if(validatePhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
            return true;
        }
        return false;
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        if(phoneNumber == null || phoneNumber.isBlank()) return false;
        String phoneNumberExpression = "^(?:\\+\\d{1,2}\\s?)?(?:\\(\\d{3}\\)|\\d{3})[-\\s]?\\d{3}[-\\s]?\\d{4}$";
        if(!phoneNumber.matches(phoneNumberExpression)) {
            System.out.println("Invalid phone number, example:\n" +
                    "+1 123-456-7890\n" +
                    "(123) 456-7890\n" +
                    "123 456 7890\n" +
                    "123-456-7890");
            return false;
        }
        return true;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User copy(String type) {
        if(type.equals("deep")) {
            return new User(this);
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getEmailAddress(), user.getEmailAddress());
    }

    @Override
    public int hashCode() {
        return getEmailAddress().hashCode();
    }

    //    public void viewBooks() {
//        UserCatalog userCatalog = UserCatalog.getUserCatalog();
//        if(userCatalog.findUser(this) != null) {
//            userCatalog.findUser(this).getAddInfo().getUserBooks().toString();
//        } else {
//            System.out.println("Error finding user.");
//        }
//    }


    @Override
    public String toString() {
        return "User {\n\t\t" +
                " firstName = '" + firstName + '\'' +
                ",\n\t\t lastName='" + lastName + '\'' +
                ",\n\t\t emailAddress='" + emailAddress + '\'' +
                ",\n\t\t phoneNumber='" + phoneNumber + '\'' +
                ",\n\t\t country='" + country + '\'' +
                ",\n\t\t state='" + state + '\'' +
                ",\n\t\t city='" + city + '\'' + "\n\t\t" +
                '}';
    }
}
