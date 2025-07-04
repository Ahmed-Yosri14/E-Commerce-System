public class ExpirationValidationStep implements CheckoutStep {
    @Override
    public void execute(Customer customer, Cart cart) throws Exception {
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().isExpired()) {
                throw new Exception(item.getProduct().getName() + " is expired");
            }
        }
    }
} 