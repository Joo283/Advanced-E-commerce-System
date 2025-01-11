import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean begin = true;

        ArrayList<normalCustomer> normalCustomers = new ArrayList<>();
        ArrayList<Premium_Customer> premiumCustomers = new ArrayList<>();
        ArrayList<Saller> sellersList = new ArrayList<>();

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
                            System.out.println(loginResult);
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
                                System.out.println("Welcome back! Your market name is: " + marketName);
                                System.out.println("Please enter your email: ");
                                email = scanner.nextLine();
                                System.out.println("Please enter your password: ");
                                password = scanner.nextLine();
                                loginResult = SuperUser.checkUser(email, password);
                                System.out.println(loginResult);
                            }
                            break;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

                case 2:
                    System.out.println("Enter 1 to sign up as a premium user:");
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
