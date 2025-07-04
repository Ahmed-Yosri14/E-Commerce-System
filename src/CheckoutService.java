import java.util.*;

public class CheckoutService {
    private ShippingService shippingService;
    private List<CheckoutStep> steps = new ArrayList<>();

    public CheckoutService(ShippingService shippingService) {
        this.shippingService = shippingService;
        steps.add(new StockValidationStep());
        steps.add(new ExpirationValidationStep());
        // Add more steps as needed
    }

    public void checkout(Customer customer, Cart cart) throws Exception {
        for (CheckoutStep step : steps) {
            step.execute(customer, cart);
        }
        double subtotal = 0;
        List<ShippableOrderItem> shippableOrderItems = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int qty = item.getQuantity();
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
            shipping = shippingService.ship(shippableOrderItems);
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