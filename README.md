# E-Commerce-System

## Overview

This project is a console-based e-commerce system designed for the Fawry Rise Journey Full Stack Development Internship Challenge. It demonstrates clean architecture, SOLID principles, and covers all required business scenarios for a simple e-commerce workflow.

---

## Features

- **model.Product Management:**  
  Products have name, price, quantity, and can be extended with expiration and shipping features using the Decorator pattern.
- **Expiration Handling:**  
  Products that can expire are wrapped with `model.ExpiringProductDecorator`.
- **Shipping Handling:**  
  Products that require shipping are wrapped with `model.ShippableProductDecorator`.
- **model.Cart Management:**  
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
  model.Product.java                 # model.Product interface (Decorator pattern)
  model.BaseProduct.java             # Core product data
  model.ExpiringProductDecorator.java# Adds expiration logic to a product
  model.ShippableProductDecorator.java# Adds shipping logic to a product
  model.ProductCatalog.java          # Manages product storage and lookup
  model.Cart.java                    # model.Cart management
  model.CartItem.java                # model.Cart item (product + quantity)
  model.Customer.java                # model.Customer data and cart
  service.CheckoutService.java         # Handles checkout process
  service.ShippingService.java         # Handles shipping logic
  model.ShippableItem.java           # Used for shipping output
  util.CartPrinter.java             # Prints cart details
  util.ReceiptPrinter.java          # Prints checkout receipt
  util.ShippingNoticePrinter.java   # Prints shipment notice
```

---

## Clean Codebase

- **No unused or legacy files:** All code is up-to-date and relevant. All previous strategy, validation, or legacy files have been removed.
- **Decorator pattern only:** All product features (expiration, shipping) are implemented using the Decorator pattern. There are no subclasses or strategy classes for products.
- **All files are used:** Every file in `src/` is referenced in the main flow or by other classes.

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
model.Customer balance 617
END.
==================================================
```

Error cases (e.g., trying to remove more items than in cart, or checkout with insufficient balance) are also demonstrated and print clear messages.

---

## Design & Extensibility

- **SOLID Principles:**  
  The codebase is structured to follow SOLID principles for maintainability and extensibility.
- **Decorator Pattern:**  
  Used for expiration and shipping logic, allowing dynamic composition of product features.
- **Separation of Concerns:**  
  Business logic, presentation, and data management are well separated.

---

## Assumptions

- model.Product quantities are decremented only after successful checkout.
- Expired products cannot be purchased.
- Shipping is calculated only for shippable items.
- All output is to the console.

---

## How to Extend

- **Add new product types:**  
  Create a new `model.BaseProduct` and wrap with decorators as needed.
- **Add new product features:**  
  Implement a new decorator and wrap products with it.
- **Add new checkout validation:**  
  Add logic to `service.CheckoutService` as needed.

---

## Project Status

- **All code is up-to-date, clean, and free of unused files.**
- **All product features use the Decorator pattern.**
- **No legacy or strategy files remain.**

---

## Author

- Solution for Fawry Rise Journey Full Stack Development Internship Challenge