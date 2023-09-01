package dev.user;

import dev.cart.Cart;

import java.util.*;

public class AddInfo {
    private String password;
    private SecurityQuestion securityQuestion;
    private String securityAnswer;
    private Set<UserBook> userBooks;
    private Cart cart;
    private int strikes = 0;
    public AddInfo () {
        password = "";
        securityAnswer = "";
        userBooks = new HashSet<>();
        cart = new Cart();
        strikes = 0;
    };

    public AddInfo(String password, SecurityQuestion securityQuestion, String securityAnswer) {
        if(!this.setPassword(password)) {
            System.out.println("Invalid password");
        }
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.userBooks = new HashSet<>();
        this.cart = new Cart();
        this.strikes = 0;
    }

    protected AddInfo(AddInfo addInfo) {
        if(!this.equals(addInfo)) {
            if (!this.setPassword(addInfo.password)) {
                System.out.println("Invalid password");
            }
            this.securityQuestion = addInfo.securityQuestion;
            this.securityAnswer = addInfo.securityAnswer;
            this.userBooks = (addInfo.userBooks == null) ? new HashSet<>() : Set.copyOf(addInfo.userBooks);
            this.cart = addInfo.cart.copy("deep");
            this.strikes = addInfo.strikes;
        }
    }

    public boolean setPassword(String password) {
        if(tryPassword(password)) {
            this.password = password;
            return true;
        }
        return false;
    }

    public boolean tryPassword(String password) {
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

    public boolean setUserBooks(Set<UserBook> userBooks) {
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

    public Set<UserBook> getUserBooks() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddInfo addInfo = (AddInfo) o;

        if (!getPassword().equals(addInfo.getPassword())) return false;
        if (getSecurityQuestion() != addInfo.getSecurityQuestion()) return false;
        return getSecurityAnswer().equals(addInfo.getSecurityAnswer());
    }

    public AddInfo copy(String type) {
        AddInfo addInfo;
        if(type.equals("deep")) {
            return addInfo = new AddInfo(this);
        }
        return addInfo = this;
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
