import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Orders_mangments implements Serializable {
    private static final long serialVersionUID = 1L;

    public ArrayList<Products> allProducts = new ArrayList<>();

    // Method to add a product
    public void addProduct(Products product, Saller saller, Scanner scanner) {
        System.out.println("Please enter the product name: ");
        String productName = scanner.nextLine();
        product.setProductName(productName);
        System.out.println("Please enter the product description: ");
        String productDescription = scanner.nextLine();
        product.setProductDescription(productDescription);

        // Handle product price input with validation
        double productPrice = 0;
        boolean validPrice = false;
        while (!validPrice) {
            try {
                System.out.println("Please enter the product price: ");
                productPrice = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline character
                validPrice = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for the product price.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        product.setProductPrice(productPrice);

        // Handle product quantity input with validation
        int productQuantity = 0;
        boolean validQuantity = false;
        while (!validQuantity) {
            try {
                System.out.println("Please enter the product quantity: ");
                productQuantity = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                validQuantity = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for the product quantity.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        product.setProductQuantity(productQuantity);

        allProducts.add(product);
        product.setShopName(saller.getMarketName());
        product.setProductId();
        System.out.println("Product added successfully and the product ID is: " + product.getProductID());
        saller.addProduct(product);
        product.setSeller(saller);
    }

    // Method to remove a product
    public void removeProduct(int productID, @NotNull Saller saller) {
        Products product = allProducts.get(productID);
        if (product.getSaller() == saller && saller.getProductsList().contains(product)) {
            allProducts.remove(product);
            saller.getProductsList().remove(product);
            System.out.println("Product removed successfully ");
        } else {
            System.out.println("Product not found");
        }
    }

    // Method to show all products
    public void showAllProducts() {
        if (allProducts.isEmpty()) {
            System.out.println("No products found.");
            return;
        }
        for (Products product : allProducts) {
            System.out.println(product.getProductName() + " and the product ID is: " + product.getProductID());
        }
    }

    // Method to show product details
    public void showProductDetails(@NotNull Products product) {
        if (!allProducts.contains(product)) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println("Product Name: " + product.getProductName());
        System.out.println("Shop Name: " + product.getShopName());
        System.out.println("Product Description: " + product.getProductDescription());
        System.out.println("Product ID: " + product.getProductID());
        System.out.println("Product Price: " + product.getProductPrice() + " $");
        System.out.println("Product Quantity: " + product.getProductQuantity());
        showProductReviews(product);
    }

    // Method to show product reviews
    private void showProductReviews(Products product) {
        System.out.println("Product reviews: ");
        int i = 1;
        if (product.getReviews().isEmpty()) {
            System.out.println("No reviews found");
            return;
        }
        for (String review : product.getReviews()) {
            System.out.println(i + " - " + review);
            i++;
        }
        System.out.println("Total reviews: " + (i - 1));
    }

    // Method to show product reviews for a seller
    public void showProductReviewsForSeller(int productID, Saller saller) {
        Products product = allProducts.get(productID);
        if (product.getSaller() == saller && saller.getProductsList().contains(product)) {
            showProductReviews(product);
        } else {
            System.out.println("Product not found");
        }
    }

    // Method to handle checkout for normal customers
    public void addTheMoneyToSellerAccountAndCheckOut(@NotNull Products product, @NotNull normalCustomer customer, Scanner scanner) {
        if (product.getProductQuantity() == 0 || !allProducts.contains(product)) {
            System.out.println("The product is out of stock");
            return;
        }
        String chekForpay = customer.checkout();

        int choice = 0;
        boolean validChoice = false;
        while (!validChoice) {
            try {
                System.out.println("If you want to add a review, choose 1 or 0 to exit");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                validChoice = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number (1 or 0).");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        while (choice != 0) {
            int productID = 0;
            boolean validProductID = false;
            while (!validProductID) {
                try {
                    System.out.println("Please enter the product ID: ");
                    productID = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    validProductID = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number for the product ID.");
                    scanner.nextLine(); // Clear the invalid input
                }
            }

            System.out.println("Please enter your review: ");
            String review = scanner.nextLine();
            if (productID < 0 || productID >= allProducts.size() || !allProducts.contains(allProducts.get(productID))) {
                System.out.println("Product not found");
            } else if (!customer.getCart().contains(allProducts.get(productID)) || chekForpay.equals("Payment not done successfully")) {
                System.out.println("You can't review a product you didn't buy");
            } else {
                allProducts.get(productID).setReviews(review);
                System.out.println("Review added successfully");
            }

            validChoice = false;
            while (!validChoice) {
                try {
                    System.out.println("If you want to add another review, choose 1 or 0 to exit");
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    validChoice = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number (1 or 0).");
                    scanner.nextLine(); // Clear the invalid input
                }
            }
        }
        customer.clearCart();
    }

    // Method to handle checkout for premium customers
    public void addTheMoneyToSellerAccountAndCheckOutPremium(@NotNull Products product, @NotNull Premium_Customer customer, Scanner scanner) {
        if (product.getProductQuantity() == 0 || !allProducts.contains(product)) {
            System.out.println("The product is out of stock");
            return;
        }
        String checkForPayment = customer.checkout();

        int choice = 0;
        boolean validChoice = false;
        while (!validChoice) {
            try {
                System.out.println("If you want to add a review, choose 1 or 0 to exit");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                validChoice = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number (1 or 0).");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        while (choice != 0) {
            int productID = 0;
            boolean validProductID = false;
            while (!validProductID) {
                try {
                    System.out.println("Please enter the product ID: ");
                    productID = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    validProductID = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number for the product ID.");
                    scanner.nextLine(); // Clear the invalid input
                }
            }

            System.out.println("Please enter your review: ");
            String review = scanner.nextLine();
            if (productID < 0 || productID >= allProducts.size() || !allProducts.contains(allProducts.get(productID))) {
                System.out.println("Product not found");
            } else if (!customer.getCart().contains(allProducts.get(productID)) || checkForPayment.equals("Payment not done successfully")) {
                System.out.println("You can't review a product you didn't buy");
            } else {
                allProducts.get(productID).setReviews(review);
                System.out.println("Review added successfully");
            }

            validChoice = false;
            while (!validChoice) {
                try {
                    System.out.println("If you want to add another review, choose 1 or 0 to exit");
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    validChoice = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number (1 or 0).");
                    scanner.nextLine(); // Clear the invalid input
                }
            }
        }
        customer.clearCart();
    }

    // Method to update product information
    public void updateProductInformation(Scanner scanner, int productID, Saller saller) {
        Products product = allProducts.get(productID);
        if (product.getSaller() == saller && saller.getProductsList().contains(product)) {
            int choice = 0;
            boolean validChoice = false;
            while (!validChoice) {
                try {
                    System.out.println("Please choose what you want to update: ");
                    System.out.println("1- Product Name");
                    System.out.println("2- Product Description");
                    System.out.println("3- Product Price");
                    System.out.println("4- Product Quantity");
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    validChoice = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number (1-4).");
                    scanner.nextLine(); // Clear the invalid input
                }
            }

            switch (choice) {
                case 1:
                    System.out.println("Please enter the new product name: ");
                    String productName = scanner.nextLine();
                    product.setProductName(productName);
                    break;
                case 2:
                    System.out.println("Please enter the new product description: ");
                    String productDescription = scanner.nextLine();
                    product.setProductDescription(productDescription);
                    break;
                case 3:
                    double productPrice = 0;
                    boolean validPrice = false;
                    while (!validPrice) {
                        try {
                            System.out.println("Please enter the new product price: ");
                            productPrice = scanner.nextDouble();
                            scanner.nextLine(); // Consume the newline character
                            validPrice = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number for the product price.");
                            scanner.nextLine(); // Clear the invalid input
                        }
                    }
                    product.setProductPrice(productPrice);
                    break;
                case 4:
                    int productQuantity = 0;
                    boolean validQuantity = false;
                    while (!validQuantity) {
                        try {
                            System.out.println("Please enter the new product quantity: ");
                            productQuantity = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character
                            validQuantity = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number for the product quantity.");
                            scanner.nextLine(); // Clear the invalid input
                        }
                    }
                    product.setProductQuantity(productQuantity);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } else {
            System.out.println("Product not found");
        }
    }

    // Method to save Orders_mangments object to a file
    public void saveToFile(String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load Orders_mangments object from a file
    public static Orders_mangments loadFromFile(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            Orders_mangments orders = (Orders_mangments) inputStream.readObject();
            return orders;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}