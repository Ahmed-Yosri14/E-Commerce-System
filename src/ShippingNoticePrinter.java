import java.util.List;

public class ShippingNoticePrinter {
    public static void printShipmentNotice(List<ShippableOrderItem> orderItems, double totalWeight) {
        System.out.println();
        System.out.println("=".repeat(50));
        System.out.println("** Shipment notice **");
        for (ShippableOrderItem orderItem : orderItems) {
            ShippableItem item = orderItem.getItem();
            int qty = orderItem.getQuantity();
            System.out.printf("%dx %s\n", qty, item.getName());
            for (int j = 0; j < qty; j++) {
                System.out.printf("%.0fg\n", item.getWeight() * 1000);
            }
        }
        System.out.printf("Total package weight %.1fkg\n", totalWeight);
        System.out.println("=".repeat(50));
        System.out.println();
    }
} 