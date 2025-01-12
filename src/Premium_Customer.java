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
    private ArrayList<Products> cart = new ArrayList<>();
    private String paymentMethod;
    private double money;
    private double discount = 0.3;

    Premium_Customer(){
    }

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
        int tryies = 0;
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
            setFullName(fullName);
            check = validateUser(email,password,getFullName());
            tryies++;
        } if (check.startsWith("User added successfully")) {
            System.out.println(check + " countinue to enter your remaining information ");
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
            System.out.println("Please enter your payment information ");
            setPaymentMethod();
            setMoney();

        }
        else{
            System.out.println("Invalid input");

        }

        if(money < 20){
            System.out.println("You need to have at least 20$ to be a premium customer");
            SuperUser.removeUser(this.getEmail(),this.getPassword());
        }
        else
            System.out.println("Now you are a Premium Customer" + " and your premium ID is " + getPremuimCustomarID());
    }


    public void setPaymentMethod() {
        System.out.println("Please enter your card Number : ");
        while (!setPaymentMethod(scanner.nextLine())) {
            System.out.println("Please enter your card Number : ");
        }

    }

    public boolean setPaymentMethod(String paymentMethod) {
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
    }

    public double calculateTotal() {
        double total = 0;
        for (Products product : cart) {
            total += product.getProductPrice();
        }
        return total;
    }

    public double checkout() {
        System.out.println("Your total is " + calculateTotal());
        System.out.println("Because you are a premium customer you will get a discount of 30%");
        System.out.println("Your total after discount is " + (calculateTotal() - (calculateTotal() * discount)));
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
