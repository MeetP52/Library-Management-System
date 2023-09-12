package dev.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.cart.Cart;

import java.util.*;

public class AddInfo {
    private String password;
    private SecurityQuestion securityQuestion;
    private String securityAnswer;
    private List<UserBook> userBooks;
    private Cart cart;
    private int strikes;
    public AddInfo () {
        password = "";
        securityAnswer = "";
        userBooks = new LinkedList<>();
        cart = new Cart();
        strikes = 0;
    }

    public AddInfo(String password, SecurityQuestion securityQuestion, String securityAnswer) {
        if(!this.setPassword(password)) {
            this.password = "Invalid Password.";
        }
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.userBooks = new LinkedList<>();
        this.cart = new Cart();
        this.strikes = 0;
    }

    @JsonCreator
    public AddInfo(@JsonProperty("password") String password,
                   @JsonProperty("securityQuestion") SecurityQuestion securityQuestion,
                   @JsonProperty("securityAnswer") String securityAnswer,
                   @JsonProperty("userBooks") List<UserBook> userBooks,
                   @JsonProperty("cart") Cart cart,
                   @JsonProperty("strikes") int strikes) {
        if (!this.setPassword(password)) {
            this.password = "Invalid Password.";
        }
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.userBooks = (userBooks == null) ? new LinkedList<>() : userBooks;
        this.cart = (cart == null) ? new Cart() : cart;
        this.strikes = strikes;
    }

    protected AddInfo(AddInfo addInfo) {
        if (!this.setPassword(addInfo.password)) {
            this.password = "Invalid Password.";
        }
        this.securityQuestion = addInfo.securityQuestion;
        this.securityAnswer = addInfo.securityAnswer;
        this.userBooks = new LinkedList<>(addInfo.userBooks);
        this.cart = new Cart(addInfo.cart);
        this.strikes = addInfo.strikes;
    }

    public boolean setPassword(String password) {
        if(tryPassword(password)) {
            this.password = password;
            return true;
        }
        return false;
    }

    public boolean tryPassword(String password) {
        if(password == null || password.isBlank()) return false;
        String passwordRegularExpression = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&*!])[A-Za-z\\d@#$%^&*!]{8,}$";
        if(!password.matches(passwordRegularExpression)) {
            System.out.println("Invalid Password, Make use of Upper and Lower case characters, as well as numbers and special symbols. Minimum password length is 8.");
            return false;
        }
        return true;
    }
    public boolean setSecurityQuestion(SecurityQuestion securityQuestion) {
        this.securityQuestion = securityQuestion;
        return true;
    }

    public boolean setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
        return true;
    }

    public boolean setUserBooks(List<UserBook> userBooks) {
        this.userBooks = userBooks;
        return true;
    }

    public boolean setStrikes(int strikes) {
        this.strikes = strikes;
        return true;
    }

    public String getPassword() {
        return password;
    }

    public SecurityQuestion getSecurityQuestion() {
        return securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public List<UserBook> getUserBooks() {
        return userBooks;
    }

    public int getStrikes() {
        return strikes;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        AddInfo addInfo = (AddInfo) o;
//
//        if (!getPassword().equals(addInfo.getPassword())) return false;
//        if (getSecurityQuestion() != addInfo.getSecurityQuestion()) return false;
//        return getSecurityAnswer().equals(addInfo.getSecurityAnswer());
//    }

    public AddInfo copy(String type) {
        if(type.equals("deep")) {
            return  new AddInfo(this);
        }
        return this;
    }

    @Override
    public String toString() {
        return "AddInfo {" +
                "\n\t\t password = '" + password + '\'' +
                ",\n\t\t securityQuestion = " + securityQuestion +
                ",\n\t\t securityAnswer = '" + securityAnswer + '\'' +
                ",\n\t\t userBooks = " + userBooks +
                ",\n\t\t cart = " + cart +
                ",\n\t\t strikes = " + strikes + "\n\t\t" +
                '}';
    }
}
