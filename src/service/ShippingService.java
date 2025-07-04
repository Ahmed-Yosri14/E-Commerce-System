package service;

import model.ShippableItem;
import util.ShippingNoticePrinter;

import java.util.List;

public class ShippingService {
    private static final double SHIPPING_RATE_PER_KG = 30.0;

    public double ship(List<ShippableItem> items, List<Integer> quantities) {
        double totalWeight = 0;
        for (int i = 0; i < items.size(); i++) {
            ShippableItem item = items.get(i);
            int qty = quantities.get(i);
            totalWeight += item.getWeight() * qty;
        }
        double shippingCost = totalWeight * SHIPPING_RATE_PER_KG;
        ShippingNoticePrinter.printShipmentNotice(items, quantities, shippingCost);
        return shippingCost;
    }
} 