import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Scanner;

public class Orders_mangments {
    Scanner scanner = new Scanner(System.in);
    public   ArrayList<Products> allProducts = new ArrayList<>();
    public void addProduct(@NotNull Products product, @NotNull Saller saller){
        System.out.println("Please enter the product name: ");
        String productName = scanner.nextLine();
        product.setProductName(productName);
        System.out.println("Please enter the product description: ");
        String productDescription = scanner.nextLine();
        product.setProductDescription(productDescription);
        System.out.println("Please enter the product price: ");
        double productPrice = scanner.nextDouble();
        scanner.nextLine();
        product.setProductPrice(productPrice);
        System.out.println("Please enter the product quantity: ");
        int productQuantity = scanner.nextInt();
        scanner.nextLine();
        product.setProductQuantity(productQuantity);
        allProducts.add(product);
        product.setShopName(saller.getMarketName());
        product.setProductId();
        System.out.println("Product added successfully and the product ID is: " + product.getProductID());
        saller.addProduct(product);
        product.setSaller(saller);
    }
    public void removeProduct(@NotNull Products product , @NotNull Saller saller){
        if (product.getSaller() ==  saller && saller.getProductsList().contains(product)){
            allProducts.remove(product);
            System.out.println("Product removed successfully ");
        }
        else{
            System.out.println("Product not found");
        }

    }
    public void showAllProducts(){
        if (allProducts.isEmpty()){
            System.out.println("No products found.");
            return;
        }
        for (Products product : allProducts){
            System.out.println(product.getProductName() + " and the product ID id : " + product.getProductID());
        }
    }
    public void showProductDetails(@NotNull Products product){
        if (!allProducts.contains(product)){
            System.out.println("Product not found.");
            return;
        }

        System.out.println("Product Name: " + product.getProductName());
        System.out.println("shop Name: " + product.getShopName());
        System.out.println("Product Description: " + product.getProductDescription());
        System.out.println("Product ID: " + product.getProductID());
        System.out.println("Product Price: " + product.getProductPrice() + " $");
        System.out.println("Product Quantity: " + product.getProductQuantity());
        showProductReviews(product);
    }

    private void showProductReviews(Products product){
        System.out.println("Product reviews: ");
        int i = 1;
        if(product.getReviews().isEmpty()){
            System.out.println("No reviews found");
            return;
        }
        for (String review : product.getReviews()){
            System.out.println( i + " - " + review);
            i++;
        }
        System.out.println("Total reviews: " + (i-1));
    }
    public void addTheMoneyToSellerAccountAndCheckOut(@NotNull Products product, @NotNull normalCustomer customer){
       if(product.getProductQuantity() == 0 || !allProducts.contains(product)) {
           System.out.println("The product is out of stock");
           return;
       }
        product.getSaller().addBalance(customer.checkout());
        System.out.println("if you want to add a review choose 1 or 0 to exit");
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice != 0){
            System.out.println("Please enter the product ID: ");
            int productID = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Please enter your review: ");
            String review = scanner.nextLine();
            if (productID < 0 || productID > allProducts.size() || !allProducts.contains(allProducts.get(productID))){
                System.out.println("Product not found");
            }
            else if(!customer.getCart().contains(allProducts.get(productID))){
                System.out.println("You can't review a product you didn't buy");
            }
            else {
                allProducts.get(productID).setReviews(review);
                System.out.println("Review added successfully");
            }
            System.out.println("if you want to add another review choose 1 or 0 to exit");
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        customer.clearCart();

    }
    public void addTheMoneyToSellerAccountAndCheckOutPremium(@NotNull Products product, @NotNull Premium_Customer customer){
        if(product.getProductQuantity() == 0 || !allProducts.contains(product)){
            System.out.println("The product is out of stock");
            return;
        }
        product.getSaller().addBalance(customer.checkout());
        System.out.println("if you want to add a review choose 1 or 0 to exit");
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice != 0){
            System.out.println("Please enter the product ID: ");
            int productID = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Please enter your review: ");
            String review = scanner.nextLine();
            if (productID < 0 || productID > allProducts.size() || !allProducts.contains(allProducts.get(productID))){
                System.out.println("Product not found");
            }
            else if(!customer.getCart().contains(allProducts.get(productID))){
                System.out.println("You can't review a product you didn't buy");
            }
            else {
                allProducts.get(productID).setReviews(review);
                System.out.println("Review added successfully");
            }
            System.out.println("if you want to add another review choose 1 or 0 to exit");
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        customer.clearCart();

    }










}
