import java.util.List;

public class ShippingNoticePrinter {
    public static void printShipmentNotice(List<Shippable> items, List<Integer> quantities, double totalWeight) {
        System.out.println();
        System.out.println("=".repeat(50));
        System.out.println("** Shipment notice **");
        for (int i = 0; i < items.size(); i++) {
            Shippable item = items.get(i);
            int qty = quantities.get(i);
            System.out.printf("%dx %s\n", qty, item.getName());
            for (int j = 0; j < qty; j++) {
                System.out.printf("%.0fg\n", item.getWeight() * 1000);
            }
        }
        System.out.printf("Total package weight %.1fkg\n", totalWeight / 30.0); // Divide by rate to get weight
        System.out.println("=".repeat(50));
        System.out.println();
    }
} 