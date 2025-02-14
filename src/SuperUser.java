// This class is responsible for creating and managing users
//this is a super class for all different types of users
//this class is responsible for checking if a user is already exists or not
//this class is responsible for validating the email and password
//this class is responsible for checking if the email and password are correct or not
//this class is responsible for setting the user's full name, email, password, role, phone number, address, postal code, and city
//this class is responsible for printing the user's information


import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SuperUser  implements Serializable {
    private static final Map<Pair<String, String>, String> allUsers = new HashMap<>(); // Pair<email, password> -> role
    private static final ArrayList<String> emails = new ArrayList<>();
    private String username;
    private  String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private int postalCode;
    private String city;

    public SuperUser() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean setPhoneNumber(@NotNull String phoneNumber) {
        if (phoneNumber.length() == 13 && phoneNumber.startsWith("+20")) {
            this.phoneNumber = phoneNumber;
            return true;

        } else {
            System.out.println("Invalid phone number format (must start with +20 and 10 numbers after it)" );
            System.out.println("Please enter a valid phone number : ");
            return false;
        }
    }

    public void setPostalCode(int postalCode) {
        if (postalCode >= 1000 && postalCode <= 9999) {
            this.postalCode = postalCode;
        } else {
            System.out.println("Invalid postal code");
        }
    }

    public void setFullName(@NotNull String fullName) {
        if (fullName.length() >= 5 && fullName.length() <= 50 && fullName.contains(" ")) {
            this.fullName = fullName;
        } else {
            System.out.println("Invalid full name");
        }
    }

    public String getFullName() {
        return fullName;
    }

    public int getNumberOfUsers() {
        return allUsers.size();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public boolean checkValidEmail(@NotNull String email) {
        return email.contains("@") && email.contains(".") && email.length() > 8 && email.length() < 50 &&
                !email.contains(" ") && email.endsWith(".com");
    }

    public boolean checkValidPassword(@NotNull String password) {
        return password.length() > 8 && password.matches(".*\\d.*") && password.matches(".*[A-Z].*") &&
                password.length() < 50;
    }

    public void printUser() {
        System.out.println("Username: " + this.username);
        System.out.println("Full Name: " + this.fullName);
        System.out.println("Email: " + this.email);
        System.out.println("Phone Number: " + this.phoneNumber);
        System.out.println("Address: " + this.address);
        System.out.println("City: " + this.city);
        System.out.println("Postal Code: " + this.postalCode);
    }

    public String validateUser(String email, String password) {
        Pair<String, String> userPair = new Pair<>(email, password);


        if (allUsers.containsKey(userPair) || emails.contains(email)) {
            return "User already exists";
        }

        if (!checkValidEmail(email)) {
            return "Invalid email";
        }

        if (!checkValidPassword(password)) {
            return "Invalid password";
        }
        return "User added successfully";
    }
    public void confirmAddingUser(String email, String password, String fullName) {
        Pair<String, String> userPair = new Pair<>(email, password);
        allUsers.put(userPair, fullName);
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        emails.add(email);
    }

    @NotNull
    public static String checkUser(String email, String password) {
        Pair<String, String> userPair = new Pair<>(email, password);

        if (allUsers.containsKey(userPair)) {
            return "Hello "  + allUsers.get(userPair);
        }
        return "Invalid email or password";
    }

}
