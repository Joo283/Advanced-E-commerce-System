//this class is for the saller to add his market information
//the saller can add his market name, market ID, market address and account number
//the saller can also add his market to the list of all sallers
//this class is responsible for adding the saller to the list of all sallers and checking if the saller already exists
//this class is also responsible for setting the market address and account number


import java.util.ArrayList;
import java.util.Scanner;
import java.util.AbstractMap.SimpleEntry;

public class Saller extends SuperUser {
    private Scanner scanner = new Scanner(System.in);
    public static ArrayList<SimpleEntry<Integer, String>> allSallersID = new ArrayList<>(); // <sallerID, marketName>
    public  ArrayList<Products> productsList = new ArrayList<>();
    private String marketName;
    private String marketAddress;
    private String accountNumber;
    private double balance = 0;

    public Saller() {
    }

    public void addBalance(double balance) {
        this.balance += balance;
    }

    public void setMarketAddress(String marketAddress) {
        this.marketAddress = marketAddress;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getMarketAddress() {
        return marketAddress;
    }

    public String getMarketName() {
        return marketName;
    }

    public  void setNewSeller() {
        System.out.println("Enter your email ");
        String email = scanner.nextLine();
        System.out.println("Enter your password");
        String password = scanner.nextLine();
        System.out.println("Enter your full name");
        String fullName = scanner.nextLine();
        String check = validateUser(email, password, fullName);
        int tryies = 0;
        while (check.startsWith("User already exists") && tryies < 3){
            System.out.println(check + " please enter a new email");
            System.out.println("Enter your email");
            email = scanner.nextLine();
            System.out.println("Enter your password");
            password = scanner.nextLine();
            System.out.println("Enter your full name");
            fullName = scanner.nextLine();
            setFullName(fullName);
            check = validateUser(email,password,getFullName());
            tryies++;
        }
        while (check.startsWith("Invalid email") && tryies < 3){
            System.out.println(check + " please enter a valid email");
            System.out.println("Enter your email");
            email = scanner.nextLine();
            System.out.println("Enter your password");
            password = scanner.nextLine();
            System.out.println("Enter your full name");
            fullName = scanner.nextLine();
            check = validateUser(email,password,fullName);
            tryies++;
        }
        while (check.startsWith("Invalid password") && tryies < 3){
            System.out.println(check + " please enter a valid password");
            System.out.println("Enter your email");
            email = scanner.nextLine();
            System.out.println("Enter your password");
            password = scanner.nextLine();
            System.out.println("Enter your full name");
            fullName = scanner.nextLine();
            check = validateUser(email,password,fullName);
            tryies++;
        } if (check.startsWith("User added successfully")) {
            System.out.println(check + " countinue to enter your market information");
            System.out.println("Enter your market name");
            String marketName = scanner.nextLine();
            System.out.println("Enter your market ID");
            int sallerID = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter your market address");
            String marketAddress = scanner.nextLine();
            setMarketAddress(marketAddress);
            System.out.println("Enter your Bank account number");
            String accountNumber = scanner.nextLine();
            setAccountNumber(accountNumber);
            addSaller(sallerID, marketName);
        }
        else{
            System.out.println("you have exceeded the number of tries please try again later");

        }

    }
    private   void addSaller(int sallerID, String marketName) {
        if (allSallersID.contains(new SimpleEntry<>(sallerID, marketName))) {
            System.out.println("This saller already exists.");
            return;
        }
        allSallersID.add(new SimpleEntry<>(sallerID, marketName));
        System.out.println("Saller added successfully.");
    }
    public void showSellerbalance(){
        System.out.println("Your balance is: " + balance);
    }
    public void addProduct(Products product){
        productsList.add(product);
    }

    public void showSellerProducts(){
        System.out.println("Your products are: ");
        int i = 1;
        for (Products product : productsList) {
            System.out.println(i + " - " +product.getProductName());
            i++;
        }
    }

    public ArrayList<Products> getProductsList() {
        return productsList;
    }
}
