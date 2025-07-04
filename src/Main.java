public class Main {
    public static void main(String[] args) {
        Cheese cheese = new Cheese("Cheese", 100, 10, "2026-06-10", 0.4);
        Biscuits biscuits = new Biscuits("Biscuits", 150, 5, "2026-06-15", 0.7);
        TV tv = new TV("TV", 5000, 3, 7.0);
        MobileScratchCard scratchCard = new MobileScratchCard("Scratch Card", 50, 100);
        Customer alice = new Customer("Alice", 1000);
        Customer bob = new Customer("Bob", 2000);

        // Add 2 cheese to Alice's cart (should succeed)
        try {
            alice.addToCart(cheese, 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Add 1 biscuits to Alice's cart (should succeed)
        try {
            alice.addToCart(biscuits, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Add 3 scratch cards to Alice's cart (should succeed)
        try {
            alice.addToCart(scratchCard, 3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Add 1 cheese to Bob's cart (should succeed)
        try {
            bob.addToCart(cheese, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Add 1 TV to Bob's cart (should succeed)
        try {
            bob.addToCart(tv, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
            alice.removeFromCart(cheese, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        CartPrinter.printCartDetails(alice.getName(), alice.getCart());

        // Remove all biscuits from Alice's cart (should succeed)
        // Expected: Alice's cart now has 1x Cheese, 3x Scratch Card
        try {
            alice.removeAllFromCart(biscuits);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        CartPrinter.printCartDetails(alice.getName(), alice.getCart());

        // Remove TV from Bob's cart (should succeed)
        // Expected: Bob's cart now has 1x Cheese
        try {
            bob.removeFromCart(tv, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        CartPrinter.printCartDetails(bob.getName(), bob.getCart());

        // Try to remove TV from Alice's cart (should fail, not in cart)
        // Expected: Error message
        try {
            alice.removeFromCart(tv, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Try to remove 5 cheese from Alice's cart (should fail, only 1 in cart)
        // Expected: Error message
        try {
            alice.removeFromCart(cheese, 5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Try to remove -1 scratch card from Alice's cart (should fail, negative quantity)
        // Expected: Error message
        try {
            alice.removeFromCart(scratchCard, -1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Try to remove 0 scratch card from Alice's cart (should fail, zero quantity)
        // Expected: Error message
        try {
            alice.removeFromCart(scratchCard, 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Clear Alice's cart (should succeed)
        // Expected: Alice's cart is empty
        try {
            alice.clearCart();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        CartPrinter.printCartDetails(alice.getName(), alice.getCart());

        // Prepare for checkout
        // Add 2 cheese and 1 biscuits to Alice's cart
        try {
            alice.addToCart(cheese, 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            alice.addToCart(biscuits, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Setup services
        ShippingCalculator shippingCalculator = new WeightBasedShippingCalculator();
        ShippingService shippingService = new ShippingService(shippingCalculator);
        CheckoutService checkoutService = new CheckoutService(shippingService);

        // Checkout Alice (should succeed)
        // Expected: Shipment notice and checkout receipt printed to console
        try {
            checkoutService.checkout(alice, alice.getCart());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Try to checkout Alice again with empty cart (should fail)
        // Expected: Error message (cart is empty)
        try {
            checkoutService.checkout(alice, alice.getCart());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}