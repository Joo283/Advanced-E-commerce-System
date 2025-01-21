import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Scanner;

public class normalCustomer extends SuperUser {
    Scanner scanner = new Scanner(System.in);
    private final ArrayList<Products> cart = new ArrayList<>();
    private static final ArrayList<String> allPaymentCards= new ArrayList<>();
    private double money;

    public void setNewUser() {
        int tryies = 0;


        System.out.print("Enter your email ; ");
        String email = scanner.nextLine();
        System.out.print("Enter your password : ");
        String password = scanner.nextLine();
        String check = validateUser(email, password);

        while (!check.startsWith("User added successfully") && tryies < 3) {
            System.out.println(check);

            if (check.startsWith("User already exists")) {
                System.out.println("Please enter a new email");
            } else if (check.startsWith("Invalid email")) {
                System.out.println("Please enter a valid email");
            } else if (check.startsWith("Invalid password")) {
                System.out.println("Please enter a valid password");
            }

            System.out.print("Enter your email : ");
            email = scanner.nextLine();
            System.out.print("Enter your password : ");
            password = scanner.nextLine();
            check = validateUser(email, password);
            tryies++;
        }

        if (check.startsWith("User added successfully")) {
            System.out.print("Enter your full name : ");
            String fullName = scanner.nextLine();
            setFullName(fullName);
            confirmAddingUser(email, password, fullName);
            System.out.println(check + " Continue to enter your remaining information");

            System.out.print("Enter your username : ");
            String username = scanner.nextLine();
            setUsername(username);

            setPhoneNumberWithRetries();

            System.out.print("Enter your address : ");
            String address = scanner.nextLine();
            setAddress(address);

            System.out.print("Enter your city : ");
            String city = scanner.nextLine();
            setCity(city);

            System.out.print("Enter your postal code : ");
            int postalCode = scanner.nextInt();
            setPostalCode(postalCode);

            setPaymentMethod();
            setMoney();
        } else {
            System.out.print("You have exceeded the maximum number of attempts. Please try again later.");
        }
    }

    private void setPhoneNumberWithRetries() {
        int tryies = 0;
        System.out.print("Enter your phone number : ");
        String phoneNumber = scanner.nextLine();

        while (!setPhoneNumber(phoneNumber) && tryies < 3) {
            System.out.print("Invalid phone number. Please try again : ");
            phoneNumber = scanner.nextLine();
            tryies++;
        }

        if (tryies >= 3) {
            System.out.print("Failed to set phone number after " + 3 + " attempts");
        }
    }

    public void setPaymentMethod() {
        System.out.print("Please enter your card Number : ");
        while (!setPaymentMethod(scanner.nextLine())) {
            System.out.print("Please enter your card Number : ");
        }
    }

    public boolean setPaymentMethod(@NotNull String paymentMethod) {
        if (paymentMethod.length() == 16 && paymentMethod.matches("[0-9]+") && !allPaymentCards.contains(paymentMethod)) {
            allPaymentCards.add(paymentMethod);
            return true;
        } else {
            System.out.println("Invalid payment method please enter a 16 digit number");
            return false;
        }
    }

    public void setMoney() {
        System.out.print("Please enter your balance : ");
        this.money = scanner.nextDouble();
    }

    public void addToCart(@NotNull Products product, int quantity) {
        if (product.getProductQuantity() < quantity) {
            System.out.println("The quantity you entered is not available");
            return;
        }
        for (int i = 0; i < quantity; i++) {
            cart.add(product);
        }
        System.out.println("Added to cart: " + product.getProductName());
    }

    public void removeFromCart(Products product) {
        if(!cart.contains(product)){
            System.out.println("Product not found in cart");
            return;
        }
        cart.remove(product);
        System.out.println("Removed from cart 1 of : " + product.getProductName());
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }
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
    private void updateProductQuantity(@NotNull Products product){
        product.setProductQuantity(product.getProductQuantity()-1);
    }

    public void checkout() {
        double total = calculateTotal();
        if (total > money) {
            System.out.println("Insufficient funds");
        } else {
            money -= total;
            System.out.println("Payment successful your remaining balance is " + money);
            for (Products product : cart){
                updateProductQuantity(product);
                product.getSaller().addBalance(product.getProductPrice());
            }
            System.out.println("the products will be delivered to you within 3 days at this address : " + getAddress());
            System.out.println("Thank you for shopping with us");


        }
    }
    public void clearCart(){
        cart.clear();
    }

    public ArrayList<Products> getCart() {
        return cart;
    }

    public void setReview(Products product, String review) {
        product.setReviews(review);
    }
}
