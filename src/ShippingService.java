import java.util.List;

public class ShippingService {
    public static double ship(List<ShippableOrderItem> orderItems) {
        double totalWeight = 0;
        for (ShippableOrderItem orderItem : orderItems) {
            ShippableItem item = orderItem.getItem();
            int qty = orderItem.getQuantity();
            totalWeight += item.getWeight() * qty;
        }
        ShippingNoticePrinter.printShipmentNotice(orderItems, totalWeight);
        return totalWeight;
    }
} 