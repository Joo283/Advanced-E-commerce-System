import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // Helper method to get valid integer input
    public static int getValidIntInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean begin = true;

        // Load all data at the start of the program
        Orders_mangments orders_mangments = Orders_mangments.loadFromFile("orders_data.sep");
        if (orders_mangments == null) {
            orders_mangments = new Orders_mangments(); // Create a new instance if loading fails
        }

        ArrayList<normalCustomer> normalCustomers = loadData("normalCustomers.sep");
        ArrayList<Premium_Customer> premiumCustomers = loadData("premiumCustomers.sep");
        ArrayList<Saller> sellersList = loadData("sellersList.sep");

        // Main loop
        while (begin) {
            System.out.println("Enter 1 to sign in:");
            System.out.println("Enter 2 to sign up:");
            System.out.println("Enter 3 to exit:");
            int choice = getValidIntInput(scanner, "");
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Sign-in logic
                    System.out.println("Enter 1 to sign in as customer:");
                    System.out.println("Enter 2 to sign in as seller:");
                    System.out.println("Enter 3 to exit:");
                    int choice1 = getValidIntInput(scanner, "");
                    scanner.nextLine();

                    switch (choice1) {
                        case 1:
                            // Customer sign-in
                            System.out.println("Enter your email: ");
                            String email = scanner.nextLine();
                            System.out.println("Enter your password:");
                            String password = scanner.nextLine();

                            int index = -1;
                            boolean registered = false;
                            boolean premium = false;
                            index = searchForNormalCustomer(email, password, normalCustomers);
                            if (index != -1) {
                                System.out.println("Hello " + normalCustomers.get(index).getFullName() + " welcome back!");
                                registered = true;
                            } else {
                                // Check if the user is a premium customer
                                index = searchForPremiumCustomer(email, password, premiumCustomers);
                                if (index != -1) {
                                    System.out.println("Hello " + premiumCustomers.get(index).getFullName() + " welcome back!");
                                    registered = true;
                                    premium = true;
                                } else {
                                    System.out.println("You are not a registered customer.");
                                }
                            }

                            if (registered) {
                                // Customer menu logic
                                boolean turnOn = true;
                                while (turnOn) {
                                    System.out.println("1 - to show all products");
                                    System.out.println("2 - to view product details");
                                    System.out.println("3 - to add a product to the cart");
                                    System.out.println("4 - to remove a product from the cart");
                                    System.out.println("5 - to view the cart");
                                    System.out.println("6 - to checkout");
                                    System.out.println("7 - to exit");
                                    int choice3 = getValidIntInput(scanner, "");
                                    scanner.nextLine();

                                    switch (choice3) {
                                        case 1:
                                            orders_mangments.showAllProducts();
                                            break;
                                        case 2:
                                            System.out.print("Enter the product ID: ");
                                            int productID = getValidIntInput(scanner, "");
                                            scanner.nextLine();
                                            if (productID >= orders_mangments.allProducts.size()) {
                                                System.out.println("Product not found.");
                                                break;
                                            }
                                            orders_mangments.showProductDetails(orders_mangments.allProducts.get(productID));
                                            break;
                                        case 3:
                                            System.out.print("Enter the product ID: ");
                                            productID = getValidIntInput(scanner, "");
                                            if (productID >= orders_mangments.allProducts.size()) {
                                                System.out.println("Product not found.");
                                                break;
                                            }
                                            System.out.print("Enter the quantity: ");
                                            int quantity = getValidIntInput(scanner, "");
                                            scanner.nextLine();
                                            if (premium) {
                                                premiumCustomers.get(index).addToCart(orders_mangments.allProducts.get(productID), quantity);
                                            } else {
                                                normalCustomers.get(index).addToCart(orders_mangments.allProducts.get(productID), quantity);
                                            }
                                            break;
                                        case 4:
                                            System.out.print("Enter the product ID: ");
                                            productID = getValidIntInput(scanner, "");
                                            if (productID >= orders_mangments.allProducts.size()) {
                                                System.out.println("Product not found.");
                                                break;
                                            }
                                            if (premium) {
                                                premiumCustomers.get(index).removeFromCart(orders_mangments.allProducts.get(productID));
                                            } else {
                                                normalCustomers.get(index).removeFromCart(orders_mangments.allProducts.get(productID));
                                            }
                                            break;
                                        case 5:
                                            if (premium) {
                                                premiumCustomers.get(index).viewCart();
                                            } else {
                                                normalCustomers.get(index).viewCart();
                                            }
                                            break;
                                        case 6:
                                            if (premium) {
                                                if (premiumCustomers.get(index).getCart().isEmpty()) {
                                                    System.out.println("Your cart is empty.");
                                                } else {
                                                    orders_mangments.addTheMoneyToSellerAccountAndCheckOutPremium(orders_mangments.allProducts.get(index), premiumCustomers.get(index), new Scanner(System.in));
                                                }
                                            } else {
                                                if (normalCustomers.get(index).getCart().isEmpty()) {
                                                    System.out.println("Your cart is empty.");
                                                } else {
                                                    orders_mangments.addTheMoneyToSellerAccountAndCheckOut(orders_mangments.allProducts.get(index), normalCustomers.get(index), new Scanner(System.in));
                                                }
                                            }
                                            break;
                                        case 7:
                                            turnOn = false;
                                            break;
                                        default:
                                            System.out.println("Invalid choice. Please try again.");
                                            break;
                                    }
                                }
                            }
                            break;
                        case 2:
                            // Seller sign-in
                            System.out.print("Enter your email: ");
                            email = scanner.nextLine();
                            System.out.print("Enter your password: ");
                            password = scanner.nextLine();

                            index = searchForSeller(email, password, sellersList);
                            if (index != -1) {
                                System.out.println("Welcome back! Your full name is: " + sellersList.get(index).getFullName());
                                boolean turnOnSeller = true;
                                while (turnOnSeller) {
                                    System.out.println("1 - to add a product");
                                    System.out.println("2 - to view your products");
                                    System.out.println("3 - to view your balance");
                                    System.out.println("4 - to remove a product");
                                    System.out.println("5 - to update a product information");
                                    System.out.println("6 - to view product reviews");
                                    System.out.println("7 - to exit");
                                    int choice4 = getValidIntInput(scanner, "");
                                    scanner.nextLine();

                                    switch (choice4) {
                                        case 1:
                                            Products product = new Products();
                                            orders_mangments.addProduct(product, sellersList.get(index), new Scanner(System.in));
                                            break;
                                        case 2:
                                            sellersList.get(index).showSellerProducts();
                                            break;
                                        case 3:
                                            System.out.println("Please enter your bank account number: ");
                                            String bankAccount = scanner.nextLine();
                                            sellersList.get(index).showSellerBalance(bankAccount);
                                            break;
                                        case 4:
                                            System.out.println("Enter the product ID: ");
                                            int productID = getValidIntInput(scanner, "");
                                            scanner.nextLine();
                                            orders_mangments.removeProduct(productID, sellersList.get(index));
                                            break;
                                        case 5:
                                            System.out.println("Enter the product ID: ");
                                            productID = getValidIntInput(scanner, "");
                                            scanner.nextLine();
                                            orders_mangments.updateProductInformation(new Scanner(System.in), productID, sellersList.get(index));
                                            break;
                                        case 6:
                                            System.out.println("Enter the product ID: ");
                                            productID = getValidIntInput(scanner, "");
                                            scanner.nextLine();
                                            orders_mangments.showProductReviewsForSeller(productID, sellersList.get(index));
                                            break;
                                        case 7:
                                            turnOnSeller = false;
                                            break;
                                        default:
                                            System.out.println("Invalid choice. Please try again.");
                                            break;
                                    }
                                }
                            } else {
                                System.out.println("You are not a registered seller.");
                            }
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

                case 2:
                    // Sign-up logic
                    System.out.println("Enter 1 to sign up as a premium user:");
                    System.out.println("Enter 2 to sign up as a normal user:");
                    System.out.println("Enter 3 to sign up as a seller:");
                    System.out.println("Enter 4 to exit:");
                    int choice2 = getValidIntInput(scanner, "");
                    scanner.nextLine();

                    switch (choice2) {
                        case 1:
                            Premium_Customer premiumCustomer = new Premium_Customer();
                            premiumCustomer.setNewUser(new Scanner(System.in));
                            premiumCustomers.add(premiumCustomer);
                            saveData("premiumCustomers.sep", premiumCustomers);
                            break;
                        case 2:
                            normalCustomer normalCustomer = new normalCustomer();
                            normalCustomer.setNewUser(new Scanner(System.in));
                            normalCustomers.add(normalCustomer);
                            saveData("normalCustomers.sep", normalCustomers);
                            break;
                        case 3:
                            Saller seller = new Saller();
                            seller.setNewSeller(new Scanner(System.in));
                            sellersList.add(seller);
                            saveData("sellersList.sep", sellersList);
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

                case 3:
                    // Exit logic
                    System.out.println("Exiting the application. Goodbye!");
                    begin = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        // Save all data before exiting
        orders_mangments.saveToFile("orders_data.sep");
        saveData("normalCustomers.sep", normalCustomers);
        saveData("premiumCustomers.sep", premiumCustomers);
        saveData("sellersList.sep", sellersList);

        scanner.close();
    }

    // Method to search for a normal customer
    public static int searchForNormalCustomer(String email, String password, @NotNull ArrayList<normalCustomer> normalCustomers) {
        for (int i = 0; i < normalCustomers.size(); i++) {
            if (normalCustomers.get(i).getEmail().equals(email) && normalCustomers.get(i).getPassword().equals(password)) {
                return i;
            }
        }
        return -1;
    }

    // Method to search for a premium customer
    public static int searchForPremiumCustomer(String email, String password, @NotNull ArrayList<Premium_Customer> premiumCustomers) {
        for (int i = 0; i < premiumCustomers.size(); i++) {
            if (premiumCustomers.get(i).getEmail().equals(email) && premiumCustomers.get(i).getPassword().equals(password)) {
                return i;
            }
        }
        return -1;
    }

    // Method to search for a seller
    public static int searchForSeller(String email, String password, @NotNull ArrayList<Saller> sellersList) {
        for (int i = 0; i < sellersList.size(); i++) {
            if (sellersList.get(i).getEmail().equals(email) && sellersList.get(i).getPassword().equals(password)) {
                return i;
            }
        }
        return -1;
    }

    // Method to save data to a file
    private static <T extends Serializable> void saveData(String filename, ArrayList<T> list) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load data from a file
    private static <T extends Serializable> ArrayList<T> loadData(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            ArrayList<T> list = (ArrayList<T>) inputStream.readObject();
            return list;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>(); // Return an empty list if loading fails
        }
    }
}