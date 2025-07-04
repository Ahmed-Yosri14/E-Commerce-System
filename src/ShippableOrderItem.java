public class ShippableOrderItem {
    private ShippableItem item;
    private int quantity;

    public ShippableOrderItem(ShippableItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public ShippableItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
} 