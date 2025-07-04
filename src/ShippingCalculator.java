import java.util.List;

public interface ShippingCalculator {
    double calculate(List<ShippableOrderItem> items);
} 