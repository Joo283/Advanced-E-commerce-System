import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Scanner;

public class Premium_Customer extends SuperUser {
    private static final ArrayList<Integer> allPremiumCustomersID = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<String> allPaymentCards = new ArrayList<>();
    private int premiumCustomerID;
    private final ArrayList<Products> cart = new ArrayList<>();
    private double money;

    public Premium_Customer() {
    }
    private void setPremiumCustomerID(int premiumCustomerID) {
        this.premiumCustomerID = premiumCustomerID;
    }

    public int getPremiumCustomerID() {
        return premiumCustomerID;
    }

    public double getMoney() {
        return money;
    }

    public void generatePremiumID() {
        int id;
        do {
            id = (int) (Math.random() * 1000);
        } while (allPremiumCustomersID.contains(id));
        allPremiumCustomersID.add(id);
        setPremiumCustomerID(id);
    }

    public void setNewUser() {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        setPaymentMethod();
        setMoney();
        generatePremiumID();

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

        if (validationMessage.equals("User added successfully") && confirmPremiumCustomer()) {
            System.out.print("Enter your full name : ");
            String fullName = scanner.nextLine();
            setFullName(fullName);
            confirmAddingUser(email,password,fullName);
            System.out.println(validationMessage + " countinue to enter your remaining information");
            System.out.print("Enter your username : ");
            String username = scanner.nextLine();
            setUsername(username);

            System.out.print("Enter your phone number: ");
            String phoneNumber = scanner.nextLine();
            while (!setPhoneNumber(phoneNumber) && attempts < 3) {
                phoneNumber = scanner.nextLine();
                attempts++;
            }
            System.out.print("Enter your address:");
            String address = scanner.nextLine();
            setAddress(address);
            System.out.print("Enter your city:");
            String city = scanner.nextLine();
            setCity(city);
            System.out.print("Enter your postal code:");
            int postalCode = scanner.nextInt();
            setPostalCode(postalCode);

        } else {
            System.out.println("user not added successfully");
        }
    }

    private boolean confirmPremiumCustomer() {
        if (money >= 20) {
            System.out.println("You are now a Premium Customer! Your premium ID is " + getPremiumCustomerID());
            System.out.println("You will receive a 30% discount on all products.");
            money -= 20;
            return true;
        } else {
            System.out.println("You need at least $20 to become a Premium Customer please try again later.");
            allPremiumCustomersID.remove((Integer) premiumCustomerID);
            return false;
        }
    }

    public void setPaymentMethod() {
        System.out.print("Enter your card number (16 digits): ");
        while (!setPaymentMethod(scanner.nextLine())) {
            System.out.print("Invalid input. Please enter a valid 16-digit card number: ");
        }
    }

    public boolean setPaymentMethod(@NotNull String paymentMethod) {
        if (paymentMethod.length() == 16 && paymentMethod.matches("[0-9]+") && !allPaymentCards.contains(paymentMethod)) {
            allPaymentCards.add(paymentMethod);
            return true;
        } else {
            return false;
        }
    }

    public void setMoney() {
        System.out.print("Enter your balance: ");
        this.money = scanner.nextDouble();
        scanner.nextLine();
    }

    public void addToCart(@NotNull Products product, int quantity) {
        if (product.getProductQuantity() < quantity) {
            System.out.println("Not enough stock available.");
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
        System.out.println("Removed from cart 1 of : " + product.getProductName());
    }

    public void viewCart() {
        if(cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        for (Products product : cart) {
            System.out.println(product.getProductName());
        }
    }

    public double calculateTotal() {
        return cart.stream().mapToDouble(Products::getProductPrice).sum();
    }

    public double checkout() {
        double total = calculateTotal();
        System.out.println("Your total is: $" + total);
        double discount = 0.3;
        double discountedTotal = total * (1 - discount);
        System.out.println("After a 30% discount: $" + discountedTotal);

        if (money >= discountedTotal) {
            money -= discountedTotal;
            System.out.println("Payment successful! Remaining balance: $" + money);
            for (Products product : cart){
                updateProductQuantity(product);
            }
        } else {
            System.out.println("Insufficient funds. Please add more money to your account.");
        }
        return total;
    }
    private void updateProductQuantity(@NotNull Products product){
        product.setProductQuantity(product.getProductQuantity()-1);
    }

    public void setReview(Products product, String review) {
        Products.setReviews(review);
    }
    public ArrayList<Products> getCart() {
        return cart;
    }

}
