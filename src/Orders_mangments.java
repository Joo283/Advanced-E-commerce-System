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
        for (Products product : allProducts){
            System.out.println(product.getProductName() + " and the product ID id : " + product.getProductID());
        }
    }
    public void showProductDetails(@NotNull Products product){

        System.out.println("Product Name: " + product.getProductName());
        System.out.println("shop Name: " + product.getShopName());
        System.out.println("Product Description: " + product.getProductDescription());
        System.out.println("Product ID: " + product.getProductID());
        System.out.println("Product Price: " + product.getProductPrice());
        System.out.println("Product Quantity: " + product.getProductQuantity());
        showProductReviews(product);
    }

    private void showProductReviews(Products product){
        int i = 1;
        for (String review : Products.getReviews()){
            System.out.println( i + " - " + review);
        }
    }
    public void addTheMoneyToSellerAccountAndCheckOut(@NotNull Products product, @NotNull normalCustomer customer){
       product.getSaller().addBalance(customer.checkout());
    }







}
