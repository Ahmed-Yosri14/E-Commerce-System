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

        // Test new cart validation features
        System.out.println("\n=== Testing New Cart Validation Features ===");
        
        // Test 1: Try to add negative quantity (should fail)
        try {
            alice.addToCart(productCatalog.getProduct("Cheese"), -1);
        } catch (Exception e) {
            System.out.println("Test 1 - Add negative quantity:");
            System.out.println("Failed: " + e.getMessage());
            System.out.println("=".repeat(50));
        }
        
        // Test 2: Try to add zero quantity (should fail)
        try {
            alice.addToCart(productCatalog.getProduct("Cheese"), 0);
        } catch (Exception e) {
            System.out.println("Test 2 - Add zero quantity:");
            System.out.println("Failed: " + e.getMessage());
            System.out.println("=".repeat(50));
        }
        
        // Test 3: Try to add more than available stock (should fail)
        try {
            alice.addToCart(productCatalog.getProduct("Cheese"), 15); // Only 10 available
        } catch (Exception e) {
            System.out.println("Test 3 - Add more than available stock:");
            System.out.println("Failed: " + e.getMessage());
            System.out.println("=".repeat(50));
        }
        
        // Test 4: Try to add to existing cart item that would exceed stock
        try {
            alice.addToCart(productCatalog.getProduct("Cheese"), 2); // Add 2 to cart
            alice.addToCart(productCatalog.getProduct("Cheese"), 9); // Try to add 9 more (total 11 > 10 available)
        } catch (Exception e) {
            System.out.println("Test 4 - Add to existing cart exceeding stock:");
            System.out.println("Failed: " + e.getMessage());
            System.out.println("=".repeat(50));
        }
        
        // Test 5: Create an expired product and try to add it
        Product expiredCheese = new BaseProduct("Expired Cheese", 50, 5);
        expiredCheese = new ExpiringProductDecorator(expiredCheese, LocalDate.parse("2020-01-01")); // Expired date
        expiredCheese = new ShippableProductDecorator(expiredCheese, 0.3);
        productCatalog.addProduct(expiredCheese);
        
        try {
            alice.addToCart(expiredCheese, 1);
        } catch (Exception e) {
            System.out.println("Test 5 - Add expired product:");
            System.out.println("Failed: " + e.getMessage());
            System.out.println("=".repeat(50));
        }
        
        // Test 6: Test checkout with product that expires between add and checkout
        System.out.println("\n=== Testing Checkout with Expired Product ===");
        
        // Create a product that expires today
        Product todayExpiringCheese = new BaseProduct("Today Expiring Cheese", 75, 3);
        todayExpiringCheese = new ExpiringProductDecorator(todayExpiringCheese, LocalDate.now()); // Expires today
        todayExpiringCheese = new ShippableProductDecorator(todayExpiringCheese, 0.4);
        productCatalog.addProduct(todayExpiringCheese);
        
        // Add it to cart (should succeed if not expired yet)
        try {
            alice.addToCart(todayExpiringCheese, 1);
            System.out.println("Test 6 - Added today expiring cheese to cart successfully");
        } catch (Exception e) {
            System.out.println("Test 6 - Failed to add today expiring cheese:");
            System.out.println("Failed: " + e.getMessage());
        }
        
        // Try to checkout (should fail if product has expired)
        try {
            checkoutService.checkout(alice, alice.getCart());
        } catch (Exception e) {
            System.out.println("Test 6 - Checkout with expired product in cart:");
            System.out.println("Failed: " + e.getMessage());
            System.out.println("=".repeat(50));
        }
    }
}