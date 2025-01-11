// This class is a subclass of SuperUser class. It is used to create a premium customer object. It has a method to check if a user is a premium user or not. It also has a method to generate a premium ID for a premium user. It has a method to set a new user as a premium user. It has a method to get the premium ID of a premium user.
// This class is responsible for creating a new user account for a premium customer
// This class is responsible for checking if a user is a premium user or not
// This class is responsible for generating a premium ID for a premium user
// This class is responsible for getting the premium ID of a premium user




import java.util.ArrayList;
import java.util.Scanner;

public class Premium_Customer  extends  SuperUser{
    private static ArrayList<Integer> allPremiumCustomersID = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    private int premuimCustomarID;
    private void setPremuimCustomarID(int premuimCustomarID) {
        this.premuimCustomarID = premuimCustomarID;
    }

    public int getPremuimCustomarID() {
        return premuimCustomarID;
    }
    public static void checkPremiumUsers() {
        System.out.println("Enter your premium ID : ");
        if(allPremiumCustomersID.contains(scanner.nextInt())){
            System.out.println("You are a premium user");
        }
        else{
            System.out.println("You are not a premium user.");
        }
    }
    public void genereatePremiumID(){
        int id = (int) (Math.random() * 1000);
        while (allPremiumCustomersID.contains(id)){
            id = (int) (Math.random() * 1000);
        }
        allPremiumCustomersID.add(id);
        setPremuimCustomarID(id);
    }

    public  void setNewUser() {
        System.out.println("Enter your email");
        String email = scanner.nextLine();
        System.out.println("Enter your password");
        String password = scanner.nextLine();
        System.out.println("Enter your full name");
        String fullName = scanner.nextLine();
        genereatePremiumID();
        String check = validateUser(email,password,(fullName +  " you are a Premium Customer" + " your premium ID is " + this.premuimCustomarID));
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
            setFullName(fullName);
            check = validateUser(email,password,getFullName());
        } if (check.startsWith("User added successfully")) {
            System.out.println(check +" you are a Premium Customer" + " your premium ID is " + getPremuimCustomarID());
        }
        else{
            System.out.println("Invalid input");

        }
        System.out.println("Please enter your remaining information");

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
