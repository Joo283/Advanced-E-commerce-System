import org.jetbrains.annotations.NotNull;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public int searchForNormalCustomer(String email, String password, @NotNull ArrayList<normalCustomer> normalCustomers) {
        for (int i = 0; i < normalCustomers.size(); i++) {
            if (normalCustomers.get(i).getEmail().equals(email) && normalCustomers.get(i).getPassword().equals(password)) {
                return i;
            }
        }
        return -1;
    }
    public int searchForPremiumCustomer(String email, String password, @NotNull ArrayList<Premium_Customer> premiumCustomers) {
        for (int i = 0; i < premiumCustomers.size(); i++) {
            if (premiumCustomers.get(i).getEmail().equals(email) && premiumCustomers.get(i).getPassword().equals(password)) {
                return i;
            }
        }
        return -1;
    }

    public int searchForSeller(String email, String password, @NotNull ArrayList<Saller> sellersList) {
        for (int i = 0; i < sellersList.size(); i++) {
            if (sellersList.get(i).getEmail().equals(email) && sellersList.get(i).getPassword().equals(password)) {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean begin = true;
        Orders_mangments orders_mangments = new Orders_mangments();
        ArrayList<normalCustomer> normalCustomers = new ArrayList<>();
        ArrayList<Premium_Customer> premiumCustomers = new ArrayList<>();
        ArrayList<Saller> sellersList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Products product = new Products();
            product.setProductName("product" + (i + 1));
            product.setProductDescription("product" + (i + 1) + " description");
            product.setProductPrice(10 + i * 5);
            product.setProductQuantity(10 + i * 2);
            product.setProductId();
            orders_mangments.allProducts.add(product);
        }





        while (begin) {
            System.out.println("Enter 1 to sign in:");
            System.out.println("Enter 2 to sign up:");
            System.out.println("Enter 3 to exit:");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter 1 to sign in as customer:");
                    System.out.println("Enter 2 to sign in as seller:");
                    int choice1 = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice1) {
                        case 1:
                            System.out.println("Enter your email: ");
                            String email = scanner.nextLine();
                            System.out.println("Enter your password:");
                            String password = scanner.nextLine();
                            String loginResult = SuperUser.checkUser(email, password);
                            if (loginResult.equals("Invalid email or password")) {
                                System.out.println("User not found. Please try again.");
                                break;
                            }
                            int index = -1;
                            boolean registered = false;
                            boolean premium = false;
                            if(new Main().searchForNormalCustomer(email, password, normalCustomers) != -1){
                                index = new Main().searchForNormalCustomer(email, password, normalCustomers);
                                System.out.println("Hello " + normalCustomers.get(index).getFullName() + " welcome back!");
                                registered = true;
                            }
                            else if(new Main().searchForPremiumCustomer(email, password, premiumCustomers) != -1){
                                index = new Main().searchForPremiumCustomer(email, password, premiumCustomers);
                                System.out.println("Hello " + premiumCustomers.get(index).getFullName() + " welcome back!");
                                registered = true;
                                premium = true;
                            }
                            else{
                                System.out.println("You are not a registered customer.");
                            }
                            boolean turnOn = true;
                            if(registered && !premium) {
                                System.out.println("Chose one of the following options: ");
                                int quantity = 0;
                                while (turnOn) {
                                System.out.println("1 - to show all products");
                                System.out.println("2 - to view product details");
                                System.out.println("3 - to add a product to the cart");
                                System.out.println("4 - to remove a product from the cart");
                                System.out.println("5 - to view the cart");
                                System.out.println("6 - to checkout");
                                System.out.println("7 - to exit");
                                int choice3 = scanner.nextInt();
                                scanner.nextLine();

                                switch (choice3) {

                                    case 1:
                                        orders_mangments.showAllProducts();
                                        break;
                                    case 2:
                                        System.out.print("Enter the product ID: ");
                                        int productID = scanner.nextInt();
                                        scanner.nextLine();
                                        if(productID >= orders_mangments.allProducts.size()){
                                            System.out.println("Product not found.");
                                            break;
                                        }
                                        orders_mangments.showProductDetails(orders_mangments.allProducts.get(productID));

                                        break;
                                    case 3:
                                        System.out.print("Enter the product ID: ");
                                        productID = scanner.nextInt();
                                        if (productID >= orders_mangments.allProducts.size()){
                                            System.out.println("Product not found.");
                                            break;
                                        }
                                        System.out.print("Enter the quantity: ");
                                        quantity = scanner.nextInt();
                                        scanner.nextLine();
                                        normalCustomers.get(index).addToCart(orders_mangments.allProducts.get(productID), quantity);
                                        break;
                                    case 4:
                                        System.out.println("Enter the product ID: ");
                                        productID = scanner.nextInt();
                                        if (productID >= orders_mangments.allProducts.size()){
                                            System.out.println("Product not found.");
                                            break;
                                        }
                                        normalCustomers.get(index).removeFromCart(orders_mangments.allProducts.get(productID));
                                        break;
                                    case 5:
                                        normalCustomers.get(index).viewCart();
                                        break;
                                    case 6:
                                        if(normalCustomers.get(index).getCart().isEmpty()){
                                            System.out.println("Your cart is empty.");
                                        }
                                        else
                                        {
                                            orders_mangments.addTheMoneyToSellerAccountAndCheckOut(orders_mangments.allProducts.get(index), normalCustomers.get(index));
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
                                break;
                            }
                            else if(registered && premium) {
                                System.out.println("Chose one of the following options: ");
                                int quantity = 0;
                                while (turnOn) {
                                    System.out.println("1 - to show all products");
                                    System.out.println("2 - to view product details");
                                    System.out.println("3 - to add a product to the cart");
                                    System.out.println("4 - to remove a product from the cart");
                                    System.out.println("5 - to view the cart");
                                    System.out.println("6 - to checkout");
                                    System.out.println("7 - to exit");
                                    int choice3 = scanner.nextInt();
                                    scanner.nextLine();
                                    switch (choice3) {
                                        case 1:
                                            orders_mangments.showAllProducts();
                                            break;
                                        case 2:
                                            System.out.print("Enter the product ID: ");
                                            int productID = scanner.nextInt();
                                            if (orders_mangments.allProducts.get(productID) == null){
                                                System.out.println("Product not found.");
                                                break;
                                            }
                                            orders_mangments.showProductDetails(orders_mangments.allProducts.get(productID));
                                            break;
                                        case 3:
                                            System.out.print("Enter the product ID: ");
                                            productID = scanner.nextInt();
                                            if (productID >= orders_mangments.allProducts.size()){
                                                System.out.println("Product not found.");
                                                break;
                                            }
                                            System.out.print("Enter the quantity: ");
                                            quantity = scanner.nextInt();
                                            premiumCustomers.get(index).addToCart(orders_mangments.allProducts.get(productID), quantity);
                                            break;
                                        case 4:
                                            System.out.print("Enter the product ID: ");
                                            productID = scanner.nextInt();
                                            if (productID >= orders_mangments.allProducts.size()){
                                                System.out.println("Product not found.");
                                                break;
                                            }
                                            premiumCustomers.get(index).removeFromCart(orders_mangments.allProducts.get(productID));
                                            break;
                                        case 5:
                                            premiumCustomers.get(index).viewCart();
                                            break;
                                        case 6:
                                            if(premiumCustomers.get(index).getCart().isEmpty()){
                                                System.out.println("Your cart is empty.");
                                            }
                                            else {
                                                orders_mangments.addTheMoneyToSellerAccountAndCheckOutPremium(orders_mangments.allProducts.get(index), premiumCustomers.get(index));
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
                                break;
                            }
                            break;
                        case 2:
                            System.out.println("Enter your Market ID: ");
                            int sellerID = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Enter your market name: ");
                            String marketName = scanner.nextLine();
                            scanner.nextLine();
                            AbstractMap.SimpleEntry<Integer, String> sellerIdPair = new AbstractMap.SimpleEntry<>(sellerID, marketName);
                            if (!Saller.allSallersID.contains(sellerIdPair)) {
                                System.out.println("You are not a registered seller.");
                            } else {
                                System.out.println("Please enter your email: ");
                                email = scanner.nextLine();
                                System.out.println("Please enter your password: ");
                                password = scanner.nextLine();
                                loginResult = SuperUser.checkUser(email, password);
                                if (loginResult.equals("Invalid email or password")) {
                                    System.out.println("User not found. Please try again.");
                                    break;
                                }
                                index = new Main().searchForSeller(email, password, sellersList);
                                System.out.println("Welcome back! Your full name is: " + sellersList.get(index).getFullName());
                            }
                            break;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

                case 2:
                    System.out.println("Enter 1 to sign up as a premium user and this service will coast you 20$ :");
                    System.out.println("Enter 2 to sign up as a normal user:");
                    System.out.println("Enter 3 to sign up as a seller:");
                    int choice2 = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice2) {
                        case 1:
                            Premium_Customer premiumCustomer = new Premium_Customer();
                            premiumCustomer.setNewUser();
                            premiumCustomers.add(premiumCustomer);
                            break;

                        case 2:
                            normalCustomer normalCustomer = new normalCustomer();
                            normalCustomer.setNewUser();
                            normalCustomers.add(normalCustomer);
                            break;

                        case 3:
                            Saller seller = new Saller();
                            seller.setNewSeller();
                            sellersList.add(seller);


                            break;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

                case 3:
                    System.out.println("List of Normal Users:");
                    for (normalCustomer customer : normalCustomers) {
                        System.out.println("Email: " + customer.getEmail());
                        System.out.println("Password: " + customer.getPassword());
                    }

                    System.out.println("List of Premium Users:");
                    for (Premium_Customer customer : premiumCustomers) {
                        System.out.println("Email: " + customer.getEmail());
                        System.out.println("Password: " + customer.getPassword());
                    }

                    begin = false;
                    System.out.println("Exiting the application. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

       scanner.close();
    }
}
