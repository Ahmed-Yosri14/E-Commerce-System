import java.util.List;

public class ShippingService {
    private ShippingCalculator calculator;

    public ShippingService(ShippingCalculator calculator) {
        this.calculator = calculator;
    }

    public double ship(List<ShippableOrderItem> orderItems) {
        double shippingCost = calculator.calculate(orderItems);
        ShippingNoticePrinter.printShipmentNotice(orderItems, shippingCost);
        return shippingCost;
    }
} 