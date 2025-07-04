import java.util.List;

public class WeightBasedShippingCalculator implements ShippingCalculator {
    private static final double SHIPPING_RATE_PER_KG = 30.0;

    @Override
    public double calculate(List<ShippableOrderItem> items) {
        double totalWeight = 0;
        for (ShippableOrderItem item : items) {
            totalWeight += item.getItem().getWeight() * item.getQuantity();
        }
        return totalWeight * SHIPPING_RATE_PER_KG;
    }
} 