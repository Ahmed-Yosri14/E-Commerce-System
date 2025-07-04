import java.util.*;

public class CheckoutService {
    private static final double SHIPPING_RATE_PER_KG = 30.0;

    public void checkout(Customer customer, Cart cart) throws Exception {
        if (cart.isEmpty()) {
            throw new Exception("Cart is empty");
        }
        double subtotal = 0;
        List<ShippableOrderItem> shippableOrderItems = new ArrayList<>();
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
                shippableOrderItems.add(new ShippableOrderItem(
                    new ShippableItem(product.getName(), product.shippingStrategy.getWeight()),
                    qty
                ));
            }
        }
        double shipping = 0;
        if (!shippableOrderItems.isEmpty()) {
            double totalWeight = ShippingService.ship(shippableOrderItems);
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
        ReceiptPrinter.printReceipt(customer.getName(), customer.getBalance(), cart, subtotal, shipping, total);
    }
} 