//this class is a subclass of SuperUser and it is used to create a new user account for a normal customer
//it has a method called setNewUser that takes the user input and validates it and then stores it in the allUsers arraylist
//this class is responsible for creating a new user account for a normal customer


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;
import java.util.PrimitiveIterator;
import java.util.Scanner;

public class normalCustomer extends SuperUser {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Products> cart = new ArrayList<>();
    private String paymentMethod;
    private double money;
    public  void setNewUser() {
        System.out.println("Enter your email");
        String email = scanner.nextLine();
        System.out.println("Enter your password");
        String password = scanner.nextLine();
        System.out.println("Enter your full name");
        String fullName = scanner.nextLine();
        setFullName(fullName);
        String check = validateUser(email,password,getFullName());
        int tryies = 0;
        if (tryies >= 3){
            System.out.println("You have exceeded the number of tries");
            return;
        }
        while (check.startsWith("User already exists") && tryies < 3){
            System.out.println(check + " please enter a new email");
            System.out.println("Enter your email");
            email = scanner.nextLine();
            System.out.println("Enter your password");
            password = scanner.nextLine();
            System.out.println("Enter your full name");
            fullName = scanner.nextLine();
            check = validateUser(email,password,fullName);
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
            System.out.println(check + " countinue to enter your remaining information");
            System.out.println("Enter your username");
            String username = scanner.nextLine();
            setUsername(username);

            System.out.println("Enter your phone number");
            String phoneNumber = scanner.nextLine();
            while (!setPhoneNumber(phoneNumber) && tryies < 3) {
                phoneNumber = scanner.nextLine();
                tryies++;
            }
            System.out.println("Enter your address");
            String address = scanner.nextLine();
            setAddress(address);
            System.out.println("Enter your city");
            String city = scanner.nextLine();
            setCity(city);
            System.out.println("Enter your postal code");
            int postalCode = scanner.nextInt();
            setPostalCode(postalCode);
            System.out.println("Enter your Payment information");
            setPaymentMethod();
            setMoney();
        }
        else{
            System.out.println("you have exceeded the number of tries please try again later");

        }


    }

    public void setPaymentMethod() {
        System.out.println("Please enter your card Number : ");
        while (!setPaymentMethod(scanner.nextLine())) {
            System.out.println("Please enter your card Number : ");
        }

    }

    public boolean setPaymentMethod(@NotNull String paymentMethod) {
        if (paymentMethod.length() == 16) {
            this.paymentMethod = paymentMethod;
            return true;
        } else {
            System.out.println("Invalid payment method please enter a 16 digit number");
            return false;
        }
    }

    public void setMoney() {
        System.out.println("Please enter your balance : ");
        double money = scanner.nextDouble();
        this.money = money;
    }

    public void addToCart(Products product , int quantity) {
        for (int i = 0; i < quantity; i++) {
            cart.add(product);
        }
    }

    public void removeFromCart(Products product) {
        cart.remove(product);
    }

    public void viewCart() {
        for (Products product : cart) {
            System.out.println(product.getProductName());
        }
        System.out.println("Total price: " + calculateTotal());
    }
    private double calculateTotal() {
        double total = 0;
        for (Products product : cart) {
            total += product.getProductPrice();
        }
        return total;
    }

    public double checkout() {
        double total = 0;
        for (Products product : cart) {
            total += product.getProductPrice();
        }
        if (total > money) {
            System.out.println("Insufficient funds");
        } else {
            money -= total;
            System.out.println("Payment successful your remaining balance is " + money);
        }
        return total;
    }

    public void setReview(Products product, String review) {
        Products.setReviews(review);
    }

}
