import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class normalCustomer extends SuperUser implements Serializable {
    private final ArrayList<Products> cart = new ArrayList<>();
    private static final ArrayList<String> allPaymentCards = new ArrayList<>();
    private double money;

    public void setNewUser(@NotNull Scanner scanner) {
        int tries = 0;

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        String check = validateUser(email, password);

        while (!check.startsWith("User added successfully") && tries < 3) {
            System.out.println(check);

            if (check.startsWith("User already exists")) {
                System.out.println("Please enter a new email");
            } else if (check.startsWith("Invalid email")) {
                System.out.println("Please enter a valid email");
            } else if (check.startsWith("Invalid password")) {
                System.out.println("Please enter a valid password");
            }

            System.out.print("Enter your email: ");
            email = scanner.nextLine();
            System.out.print("Enter your password: ");
            password = scanner.nextLine();
            check = validateUser(email, password);
            tries++;
        }

        if (check.startsWith("User added successfully")) {
            System.out.print("Enter your full name: ");
            String fullName = scanner.nextLine();
            setFullName(fullName);
            confirmAddingUser(email, password, fullName);
            System.out.println(check + " Continue to enter your remaining information");

            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            setUsername(username);

            setPhoneNumberWithRetries(new Scanner(System.in));

            System.out.print("Enter your address: ");
            String address = scanner.nextLine();
            setAddress(address);

            System.out.print("Enter your city: ");
            String city = scanner.nextLine();
            setCity(city);

            // Handle postal code input with validation
            int postalCode = 0;
            boolean validPostalCode = false;
            while (!validPostalCode) {
                try {
                    System.out.print("Enter your postal code: ");
                    postalCode = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    setPostalCode(postalCode);
                    validPostalCode = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number for the postal code.");
                    scanner.nextLine(); // Clear the invalid input
                }
            }

            setPaymentMethod(new Scanner(System.in));
            setMoney(new Scanner(System.in));
        } else {
            System.out.print("You have exceeded the maximum number of attempts. Please try again later.");
        }
    }

    private void setPhoneNumberWithRetries(@NotNull Scanner scanner) {
        int tries = 0;
        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();

        while (!setPhoneNumber(phoneNumber) && tries < 3) {
            System.out.print("Invalid phone number. Please try again: ");
            phoneNumber = scanner.nextLine();
            tries++;
        }

        if (tries >= 3) {
            System.out.print("Failed to set phone number after " + 3 + " attempts");
        }
    }

    public void setPaymentMethod(@NotNull Scanner scanner) {
        System.out.print("Please enter your card number: ");
        while (!setPaymentMethod(scanner.nextLine())) {
            System.out.print("Please enter your card number: ");
        }
    }

    public boolean setPaymentMethod(@NotNull String paymentMethod) {
        if (paymentMethod.length() == 16 && paymentMethod.matches("[0-9]+") && !allPaymentCards.contains(paymentMethod)) {
            allPaymentCards.add(paymentMethod);
            return true;
        } else {
            System.out.println("Invalid payment method. Please enter a 16-digit number.");
            return false;
        }
    }

    public void setMoney(Scanner scanner) {
        boolean validMoney = false;
        while (!validMoney) {
            try {
                System.out.print("Please enter your balance: ");
                this.money = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline character
                validMoney = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for the balance.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    public void addToCart(@NotNull Products product, int quantity) {
        if (product.getProductQuantity() < quantity) {
            System.out.println("The quantity you entered is not available.");
            return;
        }
        for (int i = 0; i < quantity; i++) {
            cart.add(product);
        }
        System.out.println("Added to cart: " + product.getProductName());
    }

    public void removeFromCart(Products product) {
        if (!cart.contains(product)) {
            System.out.println("Product not found in cart.");
            return;
        }
        cart.remove(product);
        System.out.println("Removed from cart 1 of: " + product.getProductName());
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
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

    private void updateProductQuantity(@NotNull Products product) {
        product.setProductQuantity(product.getProductQuantity() - 1);
    }

    public String checkout() {
        double total = calculateTotal();
        if (total > money) {
            System.out.println("Insufficient funds.");
            return "Payment not done successfully";
        } else {
            money -= total;
            System.out.println("Payment successful. Your remaining balance is: " + money);
            for (Products product : cart) {
                updateProductQuantity(product);
                product.getSaller().addBalance(product.getProductPrice());
            }
            System.out.println("The products will be delivered to you within 3 days at this address: " + getAddress());
            System.out.println("Thank you for shopping with us.");
        }
        return "Payment successful";
    }

    public void clearCart() {
        cart.clear();
    }

    public ArrayList<Products> getCart() {
        return cart;
    }

    public void setReview(Products product, String review) {
        product.setReviews(review);
    }
}