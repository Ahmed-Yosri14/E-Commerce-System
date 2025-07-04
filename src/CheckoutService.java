import java.util.*;

public class CheckoutService {
    private ShippingService shippingService = new ShippingService();
    private List<CheckoutStep> steps = new ArrayList<>();

    public CheckoutService() {
        steps.add(new StockValidationStep());
        steps.add(new ExpirationValidationStep());
        // Add more steps as needed
    }

    public void checkout(Customer customer, Cart cart) throws Exception {
        if (cart.isEmpty()) {
            throw new Exception("Cart is empty");
        }
        for (CheckoutStep step : steps) {
            step.execute(customer, cart);
        }
        double subtotal = 0;
        List<Shippable> shippableItems = new ArrayList<>();
        List<Integer> shippableQuantities = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int qty = item.getQuantity();
            subtotal += product.getPrice() * qty;
            if (product.isShippable()) {
                shippableItems.add(new ShippableItem(product.getName(), product.shippingStrategy.getWeight()));
                shippableQuantities.add(qty);
            }
        }
        double shipping = 0;
        if (!shippableItems.isEmpty()) {
            shipping = shippingService.ship(shippableItems, shippableQuantities);
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
        cart.clear();
    }
} 