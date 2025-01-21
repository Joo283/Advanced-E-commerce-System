// This file contains the class that represents the seller in the system.
// The class has methods to add balance, show the seller's balance, set the market address, set the account number, get the market name, get the products list, set a new seller, add a product, show the seller's products, and add the seller to the list.
// The class has a constructor that initializes the products list.
//simply this class is used to create a seller object and set the seller information and methods to handle the seller information and transactions



import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.AbstractMap.SimpleEntry;

public class Saller extends SuperUser {
    private static final Scanner scanner = new Scanner(System.in);
    public static final ArrayList<SimpleEntry<Integer, String>> allSallersID = new ArrayList<>(); // <sallerID, marketName>
    private static final ArrayList<String> allAccounts = new ArrayList<>();
    private final ArrayList<Products> productsList = new ArrayList<>();
    private String marketName;
    private String marketAddress;
    private String accountNumber;
    private double balance;

    // Constructor
    public Saller() {
    }
    // Methods to handle balance
    public void addBalance(double balance) {
        this.balance += balance;

    }
    public void showSellerBalance(String accountNumber) {
        if(this.accountNumber.equals(accountNumber))
            System.out.println("Your balance is: " + this.balance);
        else
            System.out.println("Invalid account number.");
    }

    // Setter and Getter methods for market details
    public void setMarketAddress(String marketAddress) {
        this.marketAddress = marketAddress;
    }

    public String getMarketAddress() {
        return marketAddress;
    }
    public boolean setAccountNumber(@NotNull String accountNumber) {
        if (accountNumber.length() == 14 && accountNumber.matches("[0-9]+") && !allAccounts.contains(accountNumber)) {
            this.accountNumber = accountNumber;
            allAccounts.add(accountNumber);
            return true;
        } else {
            System.out.println("Invalid account number. Please enter a valid account number(14 digit number).");
            return false;
        }
    }
    public String getMarketName() {
        return marketName;
    }

    public ArrayList<Products> getProductsList() {
        return productsList;
    }
    public void setNewSeller() {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        String validationMessage = validateUser(email, password);
        int attempts = 0;

        while (!validationMessage.equals("User added successfully") && attempts < 3) {
            System.out.println(validationMessage);
            System.out.print("Enter your email: ");
            email = scanner.nextLine();
            System.out.print("Enter your password: ");
            password = scanner.nextLine();
            validationMessage = validateUser(email, password);
            attempts++;
        }

        if (validationMessage.equals("User added successfully")) {
            System.out.print("Enter your full name: ");
            String fullName = scanner.nextLine();
            setFullName(fullName);
            confirmAddingUser(email, password, fullName);
            System.out.println(validationMessage);
            addMarketInformation();
        } else {
            System.out.println("You have exceeded the maximum number of tries. Please try again later.");
        }
    }
    private void addMarketInformation() {
        System.out.print("Enter your market name: ");
        this.marketName = scanner.nextLine();

        System.out.print("Enter your market ID: ");
        int sellerID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter your market address: ");
        setMarketAddress(scanner.nextLine());

        System.out.print("Enter your bank account number: ");
        while (!setAccountNumber(scanner.nextLine())) {
            System.out.print("Enter your bank account number: ");
        }

        addSellerToList(sellerID, marketName);
    }
    private void addSellerToList(int sellerID, String marketName) {
        SimpleEntry<Integer, String> sellerEntry = new SimpleEntry<>(sellerID, marketName);
        if (allSallersID.contains(sellerEntry)) {
            System.out.println("This seller already exists.");
        } else {
            allSallersID.add(sellerEntry);
            System.out.println("Seller added successfully.");
        }
    }
    public void addProduct(Products product) {
        productsList.add(product);
    }

    public void showSellerProducts() {
        System.out.println("Your products are: ");
        productsList.forEach(product -> System.out.println(product.getProductName() + " and the product ID is: " + product.getProductID() + "and the quantity is: " + product.getProductQuantity()));
    }

}
