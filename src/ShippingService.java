import java.util.List;

public class ShippingService {
    public static double ship(List<ShippableItem> items, List<Integer> quantities) {
        System.out.println();
        System.out.println("=".repeat(50));
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        for (int i = 0; i < items.size(); i++) {
            ShippableItem item = items.get(i);
            int qty = quantities.get(i);
            System.out.printf("%dx %s\n", qty, item.getName());
            for (int j = 0; j < qty; j++) {
                System.out.printf("%.0fg\n", item.getWeight() * 1000);
            }
            totalWeight += item.getWeight() * qty;
        }
        System.out.printf("Total package weight %.1fkg\n", totalWeight);
        System.out.println("=".repeat(50));
        System.out.println();
        return totalWeight;
    }
} 