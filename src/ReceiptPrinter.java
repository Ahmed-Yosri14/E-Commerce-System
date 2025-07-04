public class ReceiptPrinter {
    public static void printReceipt(String customerName, double customerBalance, Cart cart, double subtotal, double shipping, double total) {
        System.out.println();
        System.out.println("=".repeat(50));
        System.out.println("** Checkout receipt for " + customerName + " **");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %s\n", item.getQuantity(), item.getProduct().getName());
            System.out.printf("%.0f\n", item.getProduct().getPrice() * item.getQuantity());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal         %.0f\n", subtotal);
        System.out.printf("Shipping         %.0f\n", shipping);
        System.out.printf("Amount           %.0f\n", total);
        System.out.printf("Customer balance %.0f\n", customerBalance);
        System.out.println("END.");
        System.out.println("=".repeat(50));
        System.out.println();
    }
} 