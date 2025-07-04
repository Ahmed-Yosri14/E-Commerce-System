import model.*;
import service.CheckoutService;
import util.CartPrinter;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Create product catalog
        ProductCatalog productCatalog = new ProductCatalog();
        Product cheese = new BaseProduct("Cheese", 100, 10);
        cheese = new ExpiringProductDecorator(cheese, LocalDate.parse("2026-06-10"));
        cheese = new ShippableProductDecorator(cheese, 0.4);
        productCatalog.addProduct(cheese);

        Product biscuits = new BaseProduct("Biscuits", 150, 5);
        biscuits = new ExpiringProductDecorator(biscuits, LocalDate.parse("2026-06-15"));
        biscuits = new ShippableProductDecorator(biscuits, 0.7);
        productCatalog.addProduct(biscuits);

        Product tv = new BaseProduct("TV", 5000, 3);
        tv = new ShippableProductDecorator(tv, 7.0);
        productCatalog.addProduct(tv);

        Product scratchCard = new BaseProduct("Scratch Card", 50, 100);
        productCatalog.addProduct(scratchCard);

        Customer alice = new Customer("Alice", 1000);
        Customer bob = new Customer("Bob", 2000);

        // Add 2 cheese to Alice's cart (should succeed)
        try {
            alice.addToCart(productCatalog.getProduct("Cheese"), 2);
        } catch (Exception e) {
            System.out.println("Attempted: Add 2 cheese to Alice's cart");
            System.out.println("Failed: " + e.getMessage());
            System.out.println("=".repeat(50));
        }

        // Add 1 biscuits to Alice's cart (should succeed)
        try {
            alice.addToCart(productCatalog.getProduct("Biscuits"), 1);
        } catch (Exception e) {
            System.out.println("Attempted: Add 1 biscuits to Alice's cart");
            System.out.println("Failed: " + e.getMessage());
            System.out.println("=".repeat(50));
        }

        // Add 3 scratch cards to Alice's cart (should succeed)
        try {
            alice.addToCart(productCatalog.getProduct("Scratch Card"), 3);
        } catch (Exception e) {
            System.out.println("Attempted: Add 3 scratch cards to Alice's cart");
            System.out.println("Failed: " + e.getMessage());
            System.out.println("=".repeat(50));
        }

        // Add 1 cheese to Bob's cart (should succeed)
        try {
            bob.addToCart(productCatalog.getProduct("Cheese"), 1);
        } catch (Exception e) {
            System.out.println("Attempted: Add 1 cheese to Bob's cart");
            System.out.println("Failed: " + e.getMessage());
            System.out.println("=".repeat(50));
        }

        // Add 1 TV to Bob's cart (should succeed)
        try {
            bob.addToCart(productCatalog.getProduct("TV"), 1);
        } catch (Exception e) {
            System.out.println("Attempted: Add 1 TV to Bob's cart");
            System.out.println("Failed: " + e.getMessage());
            System.out.println("=".repeat(50));
        }

        // Print initial cart state for Alice
        // Expected: Alice's cart contains 2x Cheese, 1x Biscuits, 3x Scratch Card
        CartPrinter.printCartDetails(alice.getName(), alice.getCart());

        // Print initial cart state for Bob
        // Expected: Bob's cart contains 1x Cheese, 1x TV
        CartPrinter.printCartDetails(bob.getName(), bob.getCart());

        // Remove 1 cheese from Alice's cart (should succeed)
        // Expected: Alice's cart now has 1x Cheese, 1x Biscuits, 3x Scratch Card
        try {
            alice.removeFromCart(productCatalog.getProduct("Cheese"), 1);
        } catch (Exception e) {
            System.out.println("Attempted: Remove 1 cheese from Alice's cart");
            System.out.println("Failed: " + e.getMessage());
            System.out.println("=".repeat(50));
        }
        CartPrinter.printCartDetails(alice.getName(), alice.getCart());

        // Remove all biscuits from Alice's cart (should succeed)
        // Expected: Alice's cart now has 1x Cheese, 3x Scratch Card
        try {
            alice.removeAllFromCart(productCatalog.getProduct("Biscuits"));
        } catch (Exception e) {
            System.out.println("Attempted: Remove all biscuits from Alice's cart");
            System.out.println("Failed: " + e.getMessage());
            System.out.println("=".repeat(50));
        }
        CartPrinter.printCartDetails(alice.getName(), alice.getCart());

        // Remove TV from Bob's cart (should succeed)
        // Expected: Bob's cart now has 1x Cheese
        try {
            bob.removeFromCart(productCatalog.getProduct("TV"), 1);
        } catch (Exception e) {
            System.out.println("Attempted: Remove TV from Bob's cart");
            System.out.println("Failed: " + e.getMessage());
            System.out.println("=".repeat(50));
        }
        CartPrinter.printCartDetails(bob.getName(), bob.getCart());

        // Try to remove TV from Alice's cart (should fail, not in cart)
        // Expected: Error message
        try {
            alice.removeFromCart(productCatalog.getProduct("TV"), 1);
        } catch (Exception e) {
            System.out.println("Attempted: Remove TV from Alice's cart");
            System.out.println("Failed: " + e.getMessage());
            System.out.println("=".repeat(50));
        }

        // Try to remove 5 cheese from Alice's cart (should fail, only 1 in cart)
        // Expected: Error message
        try {
            alice.removeFromCart(productCatalog.getProduct("Cheese"), 5);
        } catch (Exception e) {
            System.out.println("Attempted: Remove 5 cheese from Alice's cart");
            System.out.println("Failed: " + e.getMessage());
            System.out.println("=".repeat(50));
        }

        // Try to remove -1 scratch card from Alice's cart (should fail, negative quantity)
        // Expected: Error message
        try {
            alice.removeFromCart(productCatalog.getProduct("Scratch Card"), -1);
        } catch (Exception e) {
            System.out.println("Attempted: Remove -1 scratch card from Alice's cart");
            System.out.println("Failed: " + e.getMessage());
            System.out.println("=".repeat(50));
        }

        // Try to remove 0 scratch card from Alice's cart (should fail, zero quantity)
        // Expected: Error message
        try {
            alice.removeFromCart(productCatalog.getProduct("Scratch Card"), 0);
        } catch (Exception e) {
            System.out.println("Attempted: Remove 0 scratch card from Alice's cart");
            System.out.println("Failed: " + e.getMessage());
            System.out.println("=".repeat(50));
        }

        // Clear Alice's cart (should succeed)
        // Expected: Alice's cart is empty
        try {
            alice.clearCart();
        } catch (Exception e) {
            System.out.println("Attempted: Clear Alice's cart");
            System.out.println("Failed: " + e.getMessage());
            System.out.println("=".repeat(50));
        }
        CartPrinter.printCartDetails(alice.getName(), alice.getCart());

        // Prepare for checkout
        // Add 2 cheese and 1 biscuits to Alice's cart
        try {
            alice.addToCart(productCatalog.getProduct("Cheese"), 2);
        } catch (Exception e) {
            System.out.println("Attempted: Add 2 cheese to Alice's cart (for checkout)");
            System.out.println("Failed: " + e.getMessage());
            System.out.println("=".repeat(50));
        }
        try {
            alice.addToCart(productCatalog.getProduct("Biscuits"), 1);
        } catch (Exception e) {
            System.out.println("Attempted: Add 1 biscuits to Alice's cart (for checkout)");
            System.out.println("Failed: " + e.getMessage());
            System.out.println("=".repeat(50));
        }

        // Setup services
        CheckoutService checkoutService = new CheckoutService();

        // Checkout Alice (should succeed)
        // Expected: Shipment notice and checkout receipt printed to console
        try {
            checkoutService.checkout(alice, alice.getCart());
        } catch (Exception e) {
            System.out.println("Attempted: Checkout Alice");
            System.out.println("Failed: " + e.getMessage());
            System.out.println("=".repeat(50));
        }

        // Try to checkout Alice again with empty cart (should fail)
        // Expected: Error message (cart is empty)
        try {
            checkoutService.checkout(alice, alice.getCart());
        } catch (Exception e) {
            System.out.println("Attempted: Checkout Alice with empty cart");
            System.out.println("Failed: " + e.getMessage());
            System.out.println("=".repeat(50));
        }
    }
}