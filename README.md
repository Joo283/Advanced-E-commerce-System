# E-Commerce System

This project is a simple e-commerce system implemented in Java. It includes functionalities for managing sellers,customers, and products. The system provides a basic structure to handle user accounts, shopping carts, product reviews, and transactions between customers and sellers.

## Project Structure

### Classes

1. Premium\_Customer

   - Represents a premium customer who gets additional benefits like a 30% discount on all products.
   - Key features:
     - Create and manage a premium user account.
     - Add and remove products from the shopping cart.
     - View the shopping cart.
     - Calculate the total price and apply discounts.
     - Set reviews for products.
     - Handle payment methods and account balance.

2. **Products**

   - Represents a product in the system.
   - Key features:
     - Store and manage product details (name, description, price, quantity, and reviews).
     - Add and retrieve product reviews.
     - Link each product to its seller.

3. **Saller**

   - Represents a seller who manages their market and products.
   - Key features:
     - Create and manage a seller account.
     - Add products to the system.
     - View the list of products added by the seller.
     - Handle account details and balance updates from sales.

4. **Main**

   - Responsible for coordinating the work of the entire program.
   - Key features:
     - Search methods to locate users during the sign-in process.
     - Loops to enhance the program's ability to perform multiple actions in a single session.
     - Case statements to handle processes and transitions effectively.

5. **SuperUser**

   - A superclass responsible for creating and managing users.
   - Key features:
     - Validate emails and passwords to ensure correct formats.
     - Check if a user already exists in the system.
     - Manage user information, including full name, email, phone number, address, and city.
     - Print user details for verification and display.
     - Provide a base for inheritance, enabling specialized user types like `Saller` and `Premium_Customer` to extend this class.

6. **normalCustomer**

   - Represents a regular customer who can perform basic e-commerce activities.
   - Key features:
     - Create and manage a user account.
     - Add and remove products from the shopping cart.
     - View the shopping cart and calculate the total price.
     - Checkout and process payments.
     - Leave reviews for products.
     - Clear the shopping cart after checkout.
     - Set payment methods and manage account balance.

7. **Orders\_mangments**

   - Responsible for managing the products and orders in the system.
   - Key features:
     - Add new products and assign them to sellers.
     - Remove products from the system.
     - Show all available products or detailed information about a specific product.
     - Display product reviews, including reviews for a specific seller.
     - Process customer and premium customer checkouts, updating seller accounts and stock quantities.
     - Allow customers to leave reviews for purchased products.
     - Update product information, such as name, description, price, or quantity.

## Key Focus

This project is designed to apply Object-Oriented Programming (OOP) concepts such as inheritance and polymorphism. These principles are demonstrated throughout the system to ensure modularity, reusability, and extensibility of the code.

## Key Features

- **User Management**: Create and validate user accounts for both sellers and customers.
- **Product Management**: Add, update, and retrieve product details.
- **Shopping Cart**: Manage a customer's cart and calculate the total price with discounts for premium customers.
- **Transactions**: Process payments and update account balances for sellers and customers.
- **Product Reviews**: Allow customers to leave reviews for products.

## Setup

1. Clone this repository:
   ```bash
   git clone <repository_url>
   ```
2. Open the project in your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse).
3. Ensure you have Java installed (JDK 8 or higher).
4. Compile and run the main class to start using the system.

## Usage

1. **For Sellers**:

   - Create an account and add your market information.
   - Add products to your market.
   - View your product list and manage your account balance.

2. **For Customers**:

   - Create a premium account (requires a minimum balance of \$20).
   - Browse and add products to your cart.
   - Checkout and pay for the items in your cart.
   - Leave reviews for purchased products.

## Future Enhancements

- Add a user interface for easier interaction.
- Implement a database for persistent data storage.
- Introduce advanced features like search, filters, and analytics.

## Contributions

Feel free to contribute to this project by submitting pull requests or reporting issues. For major changes, please open an issue first to discuss your ideas.

## License

This project is licensed under the [MIT License](LICENSE).

---

For any inquiries, please contact the repository maintainer.

