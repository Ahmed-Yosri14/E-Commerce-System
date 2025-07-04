# E-Commerce-System

## Overview

This project is a console-based e-commerce system designed as a solution for the Fawry Rise Journey Full Stack Development Internship Challenge. It demonstrates clean architecture, SOLID principles, and covers all required business scenarios for a simple e-commerce workflow.

---

## Features

- **Product Management:**  
  Define products with name, price, quantity, and optionally expiration and shipping requirements.
- **Expiration Handling:**  
  Some products (e.g., Cheese, Biscuits) can expire; others (e.g., TV, Mobile Scratch Card) do not.
- **Shipping Handling:**  
  Some products require shipping and provide weight; others do not.
- **Cart Management:**  
  Customers can add/remove products to/from their cart, with quantity checks.
- **Checkout Process:**  
  - Validates cart (not empty, not expired, not out of stock, sufficient balance).
  - Calculates subtotal, shipping, and total.
  - Deducts from customer balance and updates product stock.
  - Prints shipment notice and checkout receipt to the console.
- **Error Handling:**  
  Handles all edge cases with clear error messages.

---

## Project Structure

```
src/
  Main.java                    # Entry point, contains usage scenarios and output
  Product.java                 # Abstract product class
  Cheese.java, Biscuits.java, TV.java, MobileScratchCard.java  # Product types
  ExpirationStrategy.java      # Expiration strategy interface
  DateExpirationStrategy.java, NoExpirationStrategy.java       # Expiration strategies
  ShippingStrategy.java        # Shipping strategy interface
  WeightShippingStrategy.java, NoShippingStrategy.java         # Shipping strategies
  Cart.java                    # Cart management
  CartItem.java                # Cart item (product + quantity)
  Customer.java                # Customer data and cart
  CheckoutService.java         # Handles checkout process
  CheckoutStep.java            # Interface for extensible checkout steps
  StockValidationStep.java, ExpirationValidationStep.java      # Checkout validation steps
  ShippingCalculator.java      # Interface for shipping calculation
  WeightBasedShippingCalculator.java                           # Shipping calculation by weight
  ShippingService.java         # Handles shipping logic
  ShippableItem.java           # Interface for shippable items
  ShippableOrderItem.java      # Shippable item + quantity
  CartPrinter.java             # Prints cart details
  ReceiptPrinter.java          # Prints checkout receipt
  ShippingNoticePrinter.java   # Prints shipment notice
```

---

## How to Run

1. **Compile the project:**  
   Make sure you have Java (JDK 8+) installed.
   ```sh
   javac src/*.java
   ```

2. **Run the main class:**  
   ```sh
   java -cp src Main
   ```

---

## Example Usage & Output

The `Main.java` file demonstrates all major features and edge cases.  
Example scenario:

```java
// Add 2 cheese to Alice's cart
alice.addToCart(cheese, 2);
// Add 1 biscuits to Alice's cart
alice.addToCart(biscuits, 1);
// Checkout Alice
checkoutService.checkout(alice, alice.getCart());
```

**Expected Console Output:**
```
** Shipment notice **
2x Cheese
1x Biscuits
400g
700g
Total package weight 1.1kg
==================================================
** Checkout receipt for Alice **
2x Cheese
200
1x Biscuits
150
----------------------
Subtotal         350
Shipping         33
Amount           383
Customer balance 617
END.
==================================================
```

Error cases (e.g., trying to remove more items than in cart, or checkout with insufficient balance) are also demonstrated and print clear messages.

---

## Design & Extensibility

- **SOLID Principles:**  
  The codebase is structured to follow SOLID principles for maintainability and extensibility.
- **Strategy Pattern:**  
  Used for expiration and shipping logic.
- **Pipeline/Chain of Responsibility:**  
  Used for extensible checkout validation steps.
- **Dependency Injection:**  
  Services and strategies are injected for flexibility and testability.

---

## Assumptions

- Product quantities are decremented only after successful checkout.
- Expired products cannot be purchased.
- Shipping is calculated only for shippable items.
- All output is to the console.

---

## How to Extend

- **Add new product types:**  
  Create a new subclass of `Product`.
- **Add new expiration or shipping logic:**  
  Implement the respective strategy interface.
- **Add new checkout validation:**  
  Implement `CheckoutStep` and add to `CheckoutService`.
- **Change shipping calculation:**  
  Implement `ShippingCalculator` and inject into `ShippingService`.

---

## Author

- Solution for Fawry Rise Journey Full Stack Development Internship Challenge