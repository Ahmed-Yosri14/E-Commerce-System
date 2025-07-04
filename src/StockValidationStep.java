public class StockValidationStep implements CheckoutStep {
    @Override
    public void execute(Customer customer, Cart cart) throws Exception {
        for (CartItem item : cart.getItems()) {
            if (item.getQuantity() > item.getProduct().getQuantity()) {
                throw new Exception(item.getProduct().getName() + " is out of stock");
            }
        }
    }
} 