public class TV extends Product {
    public TV(String name, double price, int quantity, double weight) {
        super(
                name,
                price,
                quantity,
                new NoExpirationStrategy(),
                new WeightShippingStrategy(weight)
        );
    }
} 