//this class is used to create products objects
//it has methods to set and get the product name, description, price, quantity, ID, shop name, reviews, and seller
//it has a method to add reviews to the product
//it has a method to get the reviews of the product
//simply it is a class to create products objects


import java.util.ArrayList;

public class Products {
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productQuantity;
    private int productID;
    private static int productCounter = 0;
    private String shopName;
    public  ArrayList<String> reviews = new ArrayList<>();
    private Saller saller = new Saller();
    private static int ID = 0;

    public Products() {
    }

    public void setSeller(Saller saller) {
        this.saller = saller;
    }

    public Saller getSaller() {
        return saller;
    }

    public  void setReviews(String review) {
        reviews.add(review);
    }

    public ArrayList<String> getReviews() {
        return reviews;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopName() {
        return shopName;
    }

    public static void setProductCounter(int productCounter) {
        Products.productCounter = productCounter;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    public void setProductId() {
        this.productID = ID;
        ID++;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public static int getProductCounter() {
        return productCounter;
    }

    public int getProductID() {
        return productID;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductName() {
        return productName;
    }

}
