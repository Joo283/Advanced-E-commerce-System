//this class is a subclass of SuperUser and it is used to create a new user account for a normal customer
//it has a method called setNewUser that takes the user input and validates it and then stores it in the allUsers arraylist
//this class is responsible for creating a new user account for a normal customer


import java.util.Map;
import java.util.Scanner;

public class normalCustomer extends SuperUser {
    Scanner scanner = new Scanner(System.in);
    public  void setNewUser() {
        System.out.println("Enter your email");
        String email = scanner.nextLine();
        System.out.println("Enter your password");
        String password = scanner.nextLine();
        System.out.println("Enter your full name");
        String fullName = scanner.nextLine();
        setFullName(fullName);
        String check = validateUser(email,password,getFullName());
        while (check.startsWith("User already exists")){
            System.out.println(check + " please enter a new email");
            System.out.println("Enter your email");
            email = scanner.nextLine();
            System.out.println("Enter your password");
            password = scanner.nextLine();
            System.out.println("Enter your full name");
            fullName = scanner.nextLine();
            check = validateUser(email,password,fullName);
        }
        while (check.startsWith("Invalid email")){
            System.out.println(check + " please enter a valid email");
            System.out.println("Enter your email");
            email = scanner.nextLine();
            System.out.println("Enter your password");
            password = scanner.nextLine();
            System.out.println("Enter your full name");
            fullName = scanner.nextLine();
            check = validateUser(email,password,fullName);
        }
        while (check.startsWith("Invalid password")){
            System.out.println(check + " please enter a valid password");
            System.out.println("Enter your email");
            email = scanner.nextLine();
            System.out.println("Enter your password");
            password = scanner.nextLine();
            System.out.println("Enter your full name");
            fullName = scanner.nextLine();
            check = validateUser(email,password,fullName);
        } if (check.startsWith("User added successfully")) {
            System.out.println(check + " countinue to enter your remaining information");
        }
        else{
            System.out.println("Invalid input");

        }

        System.out.println("Enter your username");
        String username = scanner.nextLine();
        setUsername(username);

        System.out.println("Enter your phone number");
        String phoneNumber = scanner.nextLine();
        while (!setPhoneNumber(phoneNumber)) {
            phoneNumber = scanner.nextLine();
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
    }





}
