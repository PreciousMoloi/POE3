/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poe3;

/**
 *
 * @author Tumi
 */
public class Login extends users {
 public Login(String username, String password) {
        super(username, password);
    }

    public String registerUser() {
        if (!checkUserName()) {
            return "Invalid username format. Must contain an underscore (_) and be no more than 5 characters.";
        } else if (!checkPasswordComplexity()) {
            return "Invalid password format. Must be at least 8 characters long and contain a capital letter, a number, and a special character.";
        } else {
            return "Registration successful!";
        }
    }

    public boolean loginUser() {
        return this.username.equals(username) && this.password.equals(password);
    }

    public String returnLoginStatus(boolean successful) {
        if (successful) {
            return "Login successful!";
        } else {
            return "Login failed. Please check your username and password and try again.";
        }
    }
}   

