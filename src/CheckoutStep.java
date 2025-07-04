public interface CheckoutStep {
    void execute(Customer customer, Cart cart) throws Exception;
} 