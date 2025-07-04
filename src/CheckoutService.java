import java.util.*;

public class CheckoutService {
    private static final double SHIPPING_RATE_PER_KG = 30.0;

    public void checkout(Customer customer, Cart cart) throws Exception {
        if (cart.isEmpty()) {
            throw new Exception("Cart is empty");
        }
        double subtotal = 0;
        List<ShippableItem> shippableItems = new ArrayList<>();
        List<Integer> shippableQuantities = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int qty = item.getQuantity();
            if (qty > product.getQuantity()) {
                throw new Exception(product.getName() + " is out of stock");
            }
            if (product.isExpired()) {
                throw new Exception(product.getName() + " is expired");
            }
            subtotal += product.getPrice() * qty;
            if (product.isShippable()) {
                shippableItems.add(new ShippableItem(product.getName(), product.shippingStrategy.getWeight()));
                shippableQuantities.add(qty);
            }
        }
        double shipping = 0;
        if (!shippableItems.isEmpty()) {
            double totalWeight = ShippingService.ship(shippableItems, shippableQuantities);
            shipping = totalWeight * SHIPPING_RATE_PER_KG;
        }
        double total = subtotal + shipping;
        if (customer.getBalance() < total) {
            throw new Exception("Insufficient balance");
        }
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            product.setQuantity(product.getQuantity() - item.getQuantity());
        }
        customer.deductBalance(total);
        System.out.println();
        System.out.println("=".repeat(50));
        System.out.println("** Checkout receipt for " + customer.getName() + " **");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %s\n", item.getQuantity(), item.getProduct().getName());
            System.out.printf("%.0f\n", item.getProduct().getPrice() * item.getQuantity());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal         %.0f\n", subtotal);
        System.out.printf("Shipping         %.0f\n", shipping);
        System.out.printf("Amount           %.0f\n", total);
        System.out.printf("Customer balance %.0f\n", customer.getBalance());
        System.out.println("END.");
        System.out.println("=".repeat(50));
        System.out.println();
    }
} 