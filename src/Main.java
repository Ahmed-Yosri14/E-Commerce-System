public class Main {
    public static void main(String[] args) {
        Cheese cheese = new Cheese("Cheese", 100, 10, "2026-06-10", 0.4);
        Biscuits biscuits = new Biscuits("Biscuits", 150, 5, "2026-06-15", 0.7);
        TV tv = new TV("TV", 5000, 3, 7.0);
        MobileScratchCard scratchCard = new MobileScratchCard("Scratch Card", 50, 100);
        Customer alice = new Customer("Alice", 1000);
        Customer bob = new Customer("Bob", 2000);
        try {
            alice.addToCart(cheese, 2);
            alice.addToCart(biscuits, 1);
            alice.addToCart(scratchCard, 3);
            bob.addToCart(cheese, 1);
            bob.addToCart(tv, 1);
        } catch (Exception e) {
            System.out.println();
            System.out.println("=".repeat(50));
            System.out.println(e.getMessage());
            System.out.println("=".repeat(50));
            System.out.println();
        }

        System.out.println("=== Initial Cart State ===");
        CartPrinter.printCartDetails(alice.getName(), alice.getCart());
        CartPrinter.printCartDetails(bob.getName(), bob.getCart());
        try {
            System.out.println("=== Testing Remove Functionality ===");
            alice.removeFromCart(cheese, 1);
            System.out.println("Removed 1 cheese from Alice's cart");
            CartPrinter.printCartDetails(alice.getName(), alice.getCart());
            alice.removeAllFromCart(biscuits);
            System.out.println("Removed all biscuits from Alice's cart");
            CartPrinter.printCartDetails(alice.getName(), alice.getCart());
            bob.removeFromCart(tv, 1);
            System.out.println("Removed TV from Bob's cart");
            CartPrinter.printCartDetails(bob.getName(), bob.getCart());

        } catch (Exception e) {
            System.out.println();
            System.out.println("=".repeat(50));
            System.out.println(e.getMessage());
            System.out.println("=".repeat(50));
            System.out.println();
        }
        try {
            System.out.println("=== Testing Remove Error Cases ===");
            alice.removeFromCart(tv, 1);
        } catch (Exception e) {
            System.out.println();
            System.out.println("=".repeat(50));
            System.out.println(e.getMessage());
            System.out.println("=".repeat(50));
            System.out.println();
        }
        try {
            alice.removeFromCart(cheese, 5);
        } catch (Exception e) {
            System.out.println();
            System.out.println("=".repeat(50));
            System.out.println(e.getMessage());
            System.out.println("=".repeat(50));
            System.out.println();
        }

        try {
            alice.removeFromCart(scratchCard, -1);

        } catch (Exception e) {
            System.out.println();
            System.out.println("=".repeat(50));
            System.out.println(e.getMessage());
            System.out.println("=".repeat(50));
            System.out.println();
        }

        try {
            alice.removeFromCart(scratchCard, 0);
        } catch (Exception e) {
            System.out.println();
            System.out.println("=".repeat(50));
            System.out.println(e.getMessage());
            System.out.println("=".repeat(50));
            System.out.println();
        }
        try {
            System.out.println("=== Testing Clear Cart ===");
            alice.clearCart();
            System.out.println("Cleared Alice's cart");
            CartPrinter.printCartDetails(alice.getName(), alice.getCart());

        } catch (Exception e) {
            System.out.println();
            System.out.println("=".repeat(50));
            System.out.println(e.getMessage());
            System.out.println("=".repeat(50));
            System.out.println();
        }

        CheckoutService checkoutService = new CheckoutService();
        try {
            checkoutService.checkout(alice, alice.getCart());
        } catch (Exception e) {
            System.out.println();
            System.out.println("=".repeat(50));
            System.out.println(e.getMessage());
            System.out.println("=".repeat(50));
            System.out.println();
        }
    }
}