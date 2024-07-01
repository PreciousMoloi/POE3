/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poe3;
import javax.swing.JOptionPane;

/**
 *
 * @author Tumi
 */
public class Poe3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    
        String[] nameAndSurname = registerAndLogin();
        String name = nameAndSurname[0];
        String surname = nameAndSurname[1];
        Task taskManager = new Task();
        taskManager.runTaskManagement(name, surname);
    }

    public static String[] registerAndLogin() {
        JOptionPane.showMessageDialog(null, "\tWelcome To Authentic Registration!");

        boolean isLoggedIn = false;
        String name;
        String surname;

        name = JOptionPane.showInputDialog(null, "\tEnter Name:");
        surname = JOptionPane.showInputDialog(null, "\tEnter Surname:");

        String username = JOptionPane.showInputDialog(null,
                "Enter a username (5 or fewer characters, containing an underscore):");
        String password = JOptionPane.showInputDialog(null,
                "Enter a password (8 or more characters, containing a digit, capital letter, and special character):");

        Login newUser = new Login(username, password);
        String registrationStatus = newUser.registerUser();

        if (registrationStatus.contains("successful")) {
            JOptionPane.showMessageDialog(null, registrationStatus);
        } else {
            JOptionPane.showMessageDialog(null, "Registration failed. " + registrationStatus);
            return registerAndLogin(); // Recursively call until successful registration
        }

        while (!isLoggedIn) {
            String enteredUsername = JOptionPane.showInputDialog(null, "Enter your username:");
            String enteredPassword = JOptionPane.showInputDialog(null, "Enter your password:");

            Login existingUser = new Login(enteredUsername, enteredPassword);
            boolean loginStatus = existingUser.loginUser();

            if (loginStatus) {
                isLoggedIn = true;
                JOptionPane.showMessageDialog(null, existingUser.returnLoginStatus(true));
            } else {
                JOptionPane.showMessageDialog(null, existingUser.returnLoginStatus(false));
            }


    }
    JOptionPane.showMessageDialog(null,"welcome"+name+""+surname+",it is great to see you again!");
    return new String[]{name,surname};
    
    }
}
