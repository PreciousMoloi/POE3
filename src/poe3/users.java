/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poe3;

/**
 *user registration class for the POE application.
 * Handles login
 * @author Tumi
 */
public class users {protected String username;
    protected String password;

    /**
     * Constructor for creating a user with a username and password.
     * @param username The username to be set for the user.
     * @param password The password to be set for the user.
     */
    public users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Checks if the username meets the specified criteria.
     * The username must contain an underscore and be no longer than 5 characters.
     * @return true if the username meets the criteria, false otherwise.
     */
    public boolean checkUserName() {
        boolean containsUnderscore = false;
        for (int i = 0; i < username.length(); i++) {
            if (username.charAt(i) == '_') {
                containsUnderscore = true;
            }
        }
        return containsUnderscore && username.length() <= 5;
    }

    /**
     * Checks if the password meets the specified complexity requirements.
     * The password must be at least 8 characters long and contain at least one uppercase letter,
     * one digit, and one special character from the set {!@#$%^&*()}.
     * @return true if the password meets the complexity requirements, false otherwise.
     */
    public boolean checkPasswordComplexity() {
        boolean hasUpperCase = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if ("!@#$%^&*()".indexOf(c) != -1) {
                hasSpecialCharacter = true;
            }
        }
        return password.length() >= 8 && hasUpperCase && hasNumber && hasSpecialCharacter;
    }
}
    
    
    

