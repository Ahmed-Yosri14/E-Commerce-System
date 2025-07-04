public class StockValidationStep implements CheckoutStep {
    @Override
    public void execute(Customer customer, Cart cart) throws Exception {
        if (cart.isEmpty()) {
            throw new Exception("Cart is empty");
        }
        for (CartItem item : cart.getItems()) {
            if (item.getQuantity() > item.getProduct().getQuantity()) {
                throw new Exception(item.getProduct().getName() + " is out of stock");
            }
        }
    }
} 